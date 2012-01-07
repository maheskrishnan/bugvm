/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nullvm.rt.bro;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.nullvm.rt.VM;
import org.nullvm.rt.bro.annotation.Bridge;
import org.nullvm.rt.bro.annotation.Library;

/**
 *
 * @version $Id$
 */
public class Bro {
    public static final boolean IS_DARWIN = System.getProperty("os.name", "").matches("(?i).*(mac|darwin).*");
    public static final boolean IS_LINUX = System.getProperty("os.name", "").toLowerCase().contains("linux");
    public static final boolean IS_I386 = System.getProperty("os.arch", "").toLowerCase().contains("i386");
    public static final boolean IS_X86_64 = System.getProperty("os.arch", "").matches("(?i).*(amd64|x86.64).*");
    public static final boolean IS_ARM = System.getProperty("os.arch", "").toLowerCase().contains("arm");
    public static final boolean IS_64_BIT = IS_X86_64;
    public static final boolean IS_32_BIT = !IS_X86_64;

    private static final Map<Class<? extends Runtime>, Runtime> runtimes = 
        new HashMap<Class<? extends Runtime>, Runtime>();
    
    public static void bind() {
        bind(VM.getStackClasses(0, 1)[0]);
    }
    
    public static void bind(Class<?> c) {
        org.nullvm.rt.bro.annotation.Runtime runtime = c.getAnnotation(org.nullvm.rt.bro.annotation.Runtime.class);
        Runtime runtimeImpl = getRuntime(runtime == null ? CRuntime.class : runtime.value());
        Library library = c.getAnnotation(Library.class);
        if (library != null) {
            runtimeImpl.loadLibrary(library);
        }
        for (Method method : c.getDeclaredMethods()) {
            Bridge bridge = method.getAnnotation(Bridge.class);
            if (bridge != null) {
                long f = runtimeImpl.resolveBridge(library, bridge, method);
                bind(method, f);
            }
        }
    }
    
    private static native void bind(Method method, long function);
    
    public static void addSearchPath(String path) {
        Runtime.addSearchPath(path);
    }
    
    private static Runtime getRuntime(Class<? extends Runtime> runtimeClass) {
        synchronized (runtimes) {
            Runtime runtime = runtimes.get(runtimeClass);
            if (runtime == null) {
                try {
                    runtime = runtimeClass.newInstance();
                } catch (Exception e) {
                    throw (UnsatisfiedLinkError) new UnsatisfiedLinkError(
                            "Failed to create Runtime").initCause(e);
                }
                runtimes.put(runtimeClass,runtime);
            }
            return runtime;
        }
    }
}
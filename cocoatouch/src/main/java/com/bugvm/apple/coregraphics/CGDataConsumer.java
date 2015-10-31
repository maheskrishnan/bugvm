/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package com.bugvm.apple.coregraphics;

/*<imports>*/
import com.bugvm.apple.corefoundation.CFType;
import com.bugvm.apple.foundation.NSData;
import com.bugvm.apple.foundation.NSURL;
import com.bugvm.objc.*;
import com.bugvm.rt.*;
import com.bugvm.rt.annotation.*;
import com.bugvm.rt.bro.*;
import com.bugvm.rt.bro.annotation.*;
import com.bugvm.rt.bro.ptr.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreGraphics")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGDataConsumer/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CGDataConsumerPtr extends Ptr<CGDataConsumer, CGDataConsumerPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CGDataConsumer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CGDataConsumer() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGDataConsumerGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGDataConsumerCreateWithURL", optional=true)
    public static native @com.bugvm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGDataConsumer create(NSURL url);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CGDataConsumerCreateWithCFData", optional=true)
    public static native @com.bugvm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CGDataConsumer create(NSData data);
    /*</methods>*/
}
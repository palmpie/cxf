/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cxf.jaxrs.sse.ext;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.ext.JAXRSServerFactoryCustomizationExtension;
import org.apache.cxf.jaxrs.sse.SseContextProvider;
import org.apache.cxf.jaxrs.sse.atmosphere.SseAtmosphereEventSinkContextProvider;
import org.apache.cxf.transport.AbstractTransportFactory;
import org.apache.cxf.transport.sse.SseHttpTransportFactory;

public class SseTransportCustomizationExtension implements JAXRSServerFactoryCustomizationExtension {
    @Override
    public void customize(final JAXRSServerFactoryBean bean) {
        bean.setTransportId(SseHttpTransportFactory.TRANSPORT_ID);
        bean.setProvider(new SseContextProvider());
        bean.setProvider(new SseAtmosphereEventSinkContextProvider());
        
        final Bus bus = bean.getBus();
        if (bus != null) {
            bus.setProperty(AbstractTransportFactory.PREFERRED_TRANSPORT_ID, SseHttpTransportFactory.TRANSPORT_ID);
        }
    }
}

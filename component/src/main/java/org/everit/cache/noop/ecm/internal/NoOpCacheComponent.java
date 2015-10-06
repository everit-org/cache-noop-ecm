/*
 * Copyright (C) 2011 Everit (http://everit.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.cache.noop.ecm.internal;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.everit.cache.CacheConstants;
import org.everit.cache.noop.NoOpConcurrentMap;
import org.everit.cache.noop.ecm.NoOpCacheConstants;
import org.everit.osgi.ecm.annotation.Activate;
import org.everit.osgi.ecm.annotation.Component;
import org.everit.osgi.ecm.annotation.ConfigurationPolicy;
import org.everit.osgi.ecm.annotation.Deactivate;
import org.everit.osgi.ecm.annotation.ManualService;
import org.everit.osgi.ecm.annotation.attribute.StringAttribute;
import org.everit.osgi.ecm.annotation.attribute.StringAttributes;
import org.everit.osgi.ecm.component.ComponentContext;
import org.everit.osgi.ecm.extender.ECMExtenderConstants;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;

import aQute.bnd.annotation.headers.ProvideCapability;

/**
 * ECM component for {@link NoOpConcurrentMap}.
 *
 * @param <K>
 *          the type of keys maintained by this map
 * @param <V>
 *          the type of mapped values
 */
@Component(componentId = NoOpCacheConstants.SERVICE_FACTORYPID,
    configurationPolicy = ConfigurationPolicy.IGNORE, label = "Everit NoOp Cache")
@ProvideCapability(ns = ECMExtenderConstants.CAPABILITY_NS_COMPONENT,
    value = ECMExtenderConstants.CAPABILITY_ATTR_CLASS + "=${@class}")
@StringAttributes({
    @StringAttribute(attributeId = Constants.SERVICE_DESCRIPTION,
        defaultValue = NoOpCacheConstants.DEFAULT_SERVICE_DESCRIPTION,
        priority = NoOpCacheComponent.P1_SERVICE_DESCRIPTION, label = "Service Description",
        description = "The description of this component configuration. It is used to easily "
            + "identify the service registered by this component."),
    @StringAttribute(attributeId = CacheConstants.ATTR_CACHE_DRIVER_NAME,
        defaultValue = NoOpCacheConstants.CACHE_DRIVER_NAME,
        priority = NoOpCacheComponent.P2_CACHE_DRIVER_NAME, label = "Cache driver name",
        description = "The name of the cache driver.") })
@ManualService({ Map.class, ConcurrentMap.class })
public class NoOpCacheComponent<K, V> {

  public static final int P1_SERVICE_DESCRIPTION = 1;

  public static final int P2_CACHE_DRIVER_NAME = 2;

  private ServiceRegistration<?> cacheSR;

  /**
   * Component activator method.
   */
  @Activate
  public void activate(final ComponentContext<NoOpCacheComponent> componentContext) {
    Dictionary<String, Object> serviceProperties =
        new Hashtable<>(componentContext.getProperties());

    cacheSR = componentContext.registerService(
        new String[] {
            NoOpCacheConstants.OBJECT_CLASS_CONCURRENT_MAP.getName(),
            CacheConstants.OBJECT_CLASS_MAP.getName() },
        new NoOpConcurrentMap<K, V>(),
        serviceProperties);
  }

  /**
   * Component deactivate method.
   */
  @Deactivate
  public void deactivate() throws Exception {
    if (cacheSR != null) {
      cacheSR.unregister();
    }
  }

}

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
package org.everit.cache.noop.ecm;

import java.util.concurrent.ConcurrentMap;

/**
 * Constants for No Op Cache.
 */
public final class NoOpCacheConstants {

  public static final String CACHE_DRIVER_NAME = "noop";

  public static final String DEFAULT_SERVICE_DESCRIPTION = "No-operation cache";

  @SuppressWarnings("rawtypes")
  public static final Class<ConcurrentMap> OBJECT_CLASS_CONCURRENT_MAP = ConcurrentMap.class;

  public static final String SERVICE_FACTORYPID = "org.everit.cache.noop.ecm.NoOpCacheComponent";

  private NoOpCacheConstants() {
  }

}

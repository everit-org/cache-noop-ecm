/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.org)
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
package org.everit.cache.noop.ecm.tests;

import java.util.Map;

import org.everit.osgi.dev.testrunner.TestRunnerConstants;
import org.everit.osgi.ecm.annotation.Component;
import org.everit.osgi.ecm.annotation.Service;
import org.everit.osgi.ecm.annotation.ServiceRef;
import org.everit.osgi.ecm.annotation.attribute.StringAttribute;
import org.everit.osgi.ecm.annotation.attribute.StringAttributes;
import org.everit.osgi.ecm.extender.ExtendComponent;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test for NoOp Cache Component.
 */
@ExtendComponent
@Component(componentId = "NoOpCacheComponentTest")
@StringAttributes({
    @StringAttribute(attributeId = TestRunnerConstants.SERVICE_PROPERTY_TESTRUNNER_ENGINE_TYPE,
        defaultValue = "junit4"),
    @StringAttribute(attributeId = TestRunnerConstants.SERVICE_PROPERTY_TEST_ID,
        defaultValue = "NoOpCacheComponentTest") })
@Service(NoOpCacheComponentTest.class)
public class NoOpCacheComponentTest {

  private Map<?, ?> cache;

  @ServiceRef(defaultValue = "(cache.driver.name=noop)")
  public void setCache(final Map<?, ?> cache) {
    this.cache = cache;
  }

  @Test
  public void testThatComponentIsAlive() {
    Assert.assertNotNull("noop cache is not binded.", cache);
  }
}

/*
 * Copyright 2019-present Facebook, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.facebook.litho;

import com.facebook.litho.config.ComponentsConfiguration;

/**
 * A specific pool for HostComponent. It'll hopefully go away once we integrate more consistent
 * unmounting behavior for HostComponent into Litho.
 */
public class HostComponentMountContentPool extends DefaultMountContentPool {

  public HostComponentMountContentPool(int maxSize, boolean sync) {
    super("HostComponent", maxSize, sync);
  }

  @Override
  public void release(Object item) {
    final ComponentHost host = (ComponentHost) item;

    if (ComponentsConfiguration.clearComponentHostPressState) {
      host.setPressed(false);
    }

    super.release(item);
  }
}
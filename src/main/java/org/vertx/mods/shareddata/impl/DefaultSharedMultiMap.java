package org.vertx.mods.shareddata.impl;

import org.vertx.mods.shareddata.SharedMultiMap;

public class DefaultSharedMultiMap<K, V> extends SharedMultiMap<K, V> {

  public DefaultSharedMultiMap(String mapName) {
    super(new DefaultSharedMultiMapImpl<K, V>(), mapName);
  }

  @Override
  public void destroy() {
    /* non-op */
  }
}

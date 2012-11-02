package org.vertx.mods.shareddata.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.vertx.java.core.shareddata.SharedMap;

class DefaultSharedMap<K, V> extends SharedMap<K, V> {

  public DefaultSharedMap(String mapName) {
    super(new ConcurrentHashMap<K, V>(), mapName);
  }

  @Override
  public void destroy() {
    /* non-op */
  }
}

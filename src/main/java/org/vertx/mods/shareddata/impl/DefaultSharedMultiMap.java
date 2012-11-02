package org.vertx.mods.shareddata.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.vertx.java.core.shareddata.MultiMap;
import org.vertx.java.core.shareddata.SharedMultiMap;

public class DefaultSharedMultiMap<K, V> extends SharedMultiMap<K, V> {

  public DefaultSharedMultiMap(String mapName) {
    super(new DefaultSharedMultiMapImpl<K, V>(), mapName);
  }

  @Override
  public void destroy() {
    /* non-op */
  }
}

package org.vertx.mods.shareddata.impl.hazelcast;

import org.vertx.mods.shareddata.SharedMultiMap;

public class HazelcastSharedMultiMap<K, V> extends SharedMultiMap<K, V> {

  private final HazelcastSharedMultiMapImpl<K, V> multimap;

  public HazelcastSharedMultiMap(HazelcastSharedMultiMapImpl<K, V> multimap, String mapName) {
    super(multimap, mapName);
    this.multimap = multimap;
  }

  @Override
  protected void destroy() {
    this.multimap.destroy();
  }
}

package org.vertx.mods.shareddata.impl.hazelcast;

import org.vertx.mods.shareddata.SharedMap;

import com.hazelcast.core.IMap;

public class HazelcastSharedMap<K, V> extends SharedMap<K, V> {

  private final IMap<K,V> map;

  public HazelcastSharedMap(IMap<K,V> map, String setName) {
    super(map, setName);
    this.map = map;
  }

  @Override
  protected void destroy() {
    this.map.destroy();
  }

}

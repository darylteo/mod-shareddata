package org.vertx.mods.shareddata.impl.hazelcast;

import org.vertx.java.core.hazelcast.HazelcastClusterManager;
import org.vertx.java.core.shareddata.SharedData;
import org.vertx.java.core.shareddata.SharedList;
import org.vertx.java.core.shareddata.SharedMap;
import org.vertx.java.core.shareddata.SharedMultiMap;
import org.vertx.java.core.shareddata.SharedSet;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IList;
import com.hazelcast.core.IMap;
import com.hazelcast.core.ISet;

public class HazelcastSharedData extends SharedData {

  private static final String INTERNAL_LIST_HAZELCAST_PREFIX = "_shareddata.lists";
  private static final String INTERNAL_SET_HAZELCAST_PREFIX = "_shareddata.sets";
  private static final String INTERNAL_MAP_HAZELCAST_PREFIX = "_shareddata.maps";
  private static final String INTERNAL_MULTIMAP_HAZELCAST_PREFIX = "_shareddata.multimaps";

  public final HazelcastInstance hazelcast;

  public HazelcastSharedData() {
    this.hazelcast = HazelcastClusterManager.getHazelcast();
  }

  @Override
  protected <K, V> SharedMap<K, V> createSharedMap(String mapName, int timeToLive) {
    IMap<K, V> map = this.hazelcast.getMap(String.format("%s.%s", INTERNAL_MAP_HAZELCAST_PREFIX, mapName));
    return new HazelcastSharedMap<>(map, mapName);
  }

  @Override
  protected <K, V> SharedMultiMap<K, V> createSharedMultiMap(String mapName, int timeToLive) {
    HazelcastSharedMultiMapImpl<K, V> multimap = new HazelcastSharedMultiMapImpl<K, V>((com.hazelcast.core.MultiMap<K, V>) this.hazelcast.getMultiMap(String.format("%s.%s", INTERNAL_MULTIMAP_HAZELCAST_PREFIX, mapName)));
    return new HazelcastSharedMultiMap<>(multimap, mapName);
  }

  @Override
  protected <E> SharedSet<E> createSharedSet(String setName, int timeToLive) {
    ISet<E> set = this.hazelcast.getSet(String.format("%s.%s", INTERNAL_SET_HAZELCAST_PREFIX, setName));
    return new HazelcastSharedSet<>(set, setName);
  }

  @Override
  protected <E> SharedList<E> createSharedList(String listName, int timeToLive) {
    IList<E> list = this.hazelcast.getList(String.format("%s.%s", INTERNAL_LIST_HAZELCAST_PREFIX, listName));
    return new HazelcastSharedList<>(list, listName);
  }
}
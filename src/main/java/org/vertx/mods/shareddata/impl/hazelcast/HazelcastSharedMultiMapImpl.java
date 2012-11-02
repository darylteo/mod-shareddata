package org.vertx.mods.shareddata.impl.hazelcast;

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

import org.vertx.mods.shareddata.MultiMap;

public class HazelcastSharedMultiMapImpl<K, V> implements MultiMap<K, V> {

  private final com.hazelcast.core.MultiMap<K, V> multiMap;

  public HazelcastSharedMultiMapImpl(com.hazelcast.core.MultiMap<K, V> multiMap) {
    this.multiMap = multiMap;
  }

  @Override
  public void clear() {
    this.multiMap.clear();
  }

  @Override
  public boolean containsKey(K k) {
    return this.multiMap.containsKey(k);
  }

  @Override
  public boolean containsValue(Object o) {
    return this.multiMap.containsValue(o);
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    return this.multiMap.entrySet();
  }

  @Override
  public Collection<V> get(K k) {
    return this.multiMap.get(k);
  }

  @Override
  public boolean isEmpty() {
    return this.multiMap.size() == 0;
  }

  @Override
  public Set<K> keySet() {
    return this.multiMap.keySet();
  }

  @Override
  public boolean put(K k, V v) {
    return this.multiMap.put(k,v);
  }

  @Override
  public Collection<V> remove(Object o) {
    return this.multiMap.remove(o);
  }

  @Override
  public int size() {
    return this.multiMap.size();
  }

  @Override
  public Collection<V> values() {
    return this.multiMap.values();
  }

  @Override
  public boolean remove(Object o1, Object o2) {
    return this.multiMap.remove(o1,o2);
  }

  @Override
  public boolean containsEntry(K k, V v) {
    return this.multiMap.containsEntry(k, v);
  }

  @Override
  public int valueCount(K k) {
    return this.multiMap.valueCount(k);
  }

  public void destroy() {
    this.multiMap.destroy();
  }

}

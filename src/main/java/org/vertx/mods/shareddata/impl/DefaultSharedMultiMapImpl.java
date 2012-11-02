package org.vertx.mods.shareddata.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.vertx.mods.shareddata.MultiMap;

class DefaultSharedMultiMapImpl<K, V> implements MultiMap<K, V> {
  public ConcurrentMap<K, ConcurrentMap<V, Object>> maps = new ConcurrentHashMap<>();

  private static final Object O = "wibble";

  @Override
  public void clear() {
    maps.clear();
  }

  @Override
  public boolean containsKey(K k) {
    return maps.containsKey(k);
  }

  @Override
  public boolean containsValue(Object o) {
    for (ConcurrentMap<V, Object> map : maps.values()) {
      if (map.containsValue(o)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    Set<Entry<K, V>> result = new HashSet<>();

    for (Entry<K, ConcurrentMap<V, Object>> entry : maps.entrySet()) {
      ConcurrentMap<V, Object> values = entry.getValue();
      K key = entry.getKey();

      for (V value : values.keySet()) {
        result.add(new BasicEntry(key, value));
      }
    }

    return result;
  }

  @Override
  public Collection<V> get(K k) {
    if (!maps.containsKey(k)) {
      return null;
    }

    return maps.get(k).keySet();
  }

  @Override
  public boolean isEmpty() {
    return maps.isEmpty();
  }

  @Override
  public Set<K> keySet() {
    return maps.keySet();
  }

  @Override
  public boolean put(K k, V v) {
    ConcurrentMap<V, Object> map = maps.get(k);

    if (map == null) {
      map = new ConcurrentHashMap<>();
      maps.put(k, map);
    }

    if (map.containsKey(v)) {
      return false;
    }

    map.put(v, O);

    return true;
  }

  @Override
  public Collection<V> remove(Object o) {
    return this.maps.remove(o).keySet();
  }

  @Override
  public int size() {
    return this.maps.size();
  }

  @Override
  public Collection<V> values() {
    Set<V> values = new HashSet<>();
    for (ConcurrentMap<V, Object> v : this.maps.values()) {
      values.addAll(v.keySet());
    }

    return values;
  }

  @Override
  public boolean remove(Object o1, Object o2) {
    ConcurrentMap<V, Object> values = this.maps.get(o1);

    if (values == null) {
      return false;
    }

    return values.remove(o2) != null;
  }

  @Override
  public boolean containsEntry(K k, V v) {
    ConcurrentMap<V, Object> values = this.maps.get(k);

    if (values == null) {
      return false;
    }

    return values.containsKey(v);
  }

  @Override
  public int valueCount(K k) {
    ConcurrentMap<V, Object> values = this.maps.get(k);

    if (values == null) {
      return 0;
    }

    return values.size();
  }

  private static class BasicEntry<K, V> implements Map.Entry<K, V> {

    private final K key;
    private V value;

    public BasicEntry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public K getKey() {
      return this.key;
    }

    @Override
    public V getValue() {
      return this.value;
    }

    @Override
    public V setValue(V value) {
      this.value = value;
      return value;
    }

  }
}
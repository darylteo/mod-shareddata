package org.vertx.mods.shareddata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.logging.impl.LoggerFactory;

public abstract class SharedMultiMap<K, V> implements MultiMap<K, V> {

  private static final Logger log = LoggerFactory.getLogger(SharedData.class);

  private final MultiMap<K, V> impl;
  private final String mapName;

  private static final Object O = "wibble";

  public SharedMultiMap(MultiMap<K, V> impl, String mapName) {
    this.impl = impl;
    this.mapName = mapName;
  }

  @Override
  public void clear() {
    this.impl.clear();
  }

  @Override
  public boolean containsKey(K k) {
    return this.impl.containsKey(k);
  }

  @Override
  public boolean containsValue(Object o) {
    return this.impl.containsValue(o);
  }

  @Override
  public Set<Entry<K, V>> entrySet() {
    Set<Entry<K, V>> entries = this.impl.entrySet();
    Set<Entry<K, V>> copied = new HashSet<>();

    for (Entry<K, V> entry : entries) {
      copied.add(new SharedDataEntry<>(entry));
    }

    return entries;
  }

  @Override
  public Collection<V> get(K k) {
    return copyCollection(this.impl.get(k));
  }

  @Override
  public boolean isEmpty() {
    return this.impl.isEmpty();
  }

  @Override
  public Set<K> keySet() {
    Set<K> keys = this.impl.keySet();
    Set<K> copied = new HashSet<>();

    for (K value : keys) {
      copied.add(Checker.copyIfRequired(value));
    }

    return copied;
  }

  @Override
  public boolean put(K k, V v) {
    Checker.checkType(k);
    Checker.checkType(v);
    return this.impl.put(k, v);
  }

  @Override
  public Collection<V> remove(Object o) {
    return copyCollection(this.impl.remove(o));
  }

  @Override
  public int size() {
    return this.impl.size();
  }

  @Override
  public Collection<V> values() {
    return copyCollection(this.impl.values());
  }

  @Override
  public boolean remove(Object o1, Object o2) {
    return this.impl.remove(o1, o2);
  }

  @Override
  public boolean containsEntry(K k, V v) {
    return this.impl.containsEntry(k, v);
  }

  @Override
  public int valueCount(K k) {
    return this.impl.valueCount(k);
  }

  private <E> Collection<E> copyCollection(Collection<E> collection) {
    if (collection == null) {
      return null;
    }

    Collection<E> copied = new ArrayList<>();

    for (E value : collection) {
      copied.add(Checker.copyIfRequired(value));
    }

    return copied;
  }

  @Override
  public boolean equals(Object o) {
    return impl.equals(o);
  }

  @Override
  public int hashCode() {
    return impl.hashCode();
  }

  protected abstract void destroy();
}

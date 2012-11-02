package org.vertx.mods.shareddata.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DefaultSharedSetImpl<E> implements Set<E> {

  private final ConcurrentMap<E, Object> map = new ConcurrentHashMap<>();
  private static final Object O = "wibble";

  @Override
  public boolean add(E value) {
    return this.map.put(value, O) != null;
  }

  @Override
  public boolean addAll(Collection<? extends E> values) {
    boolean changed = false;
    for (E value : values) {
      changed = changed || this.map.put(value, O) == null;
    }

    return changed;
  }

  @Override
  public void clear() {
    this.map.clear();
  }

  @Override
  public boolean contains(Object value) {
    return this.map.containsKey(value);
  }

  @Override
  public boolean containsAll(Collection<?> values) {
    return this.map.keySet().containsAll(values);
  }

  @Override
  public boolean isEmpty() {
    return this.map.isEmpty();
  }

  @Override
  public Iterator<E> iterator() {
    return this.map.keySet().iterator();
  }

  @Override
  public boolean remove(Object value) {
    return this.map.remove(value) != null;
  }

  @Override
  public boolean removeAll(Collection<?> values) {
    boolean changed = false;
    for (Object value: values) {
      changed = changed || this.map.remove(value) != null;
    }
    return changed;
  }

  @Override
  public boolean retainAll(Collection<?> values) {
    boolean changed = false;

    for (E key : this.map.keySet()) {
      if(values.contains(key)){
        changed = true;
        this.map.remove(key);
      }
    }

    return changed;
  }

  @Override
  public int size() {
    return this.map.size();
  }

  @Override
  public Object[] toArray() {
    return this.map.keySet().toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return this.map.keySet().toArray(a);
  }

  @Override
  public boolean equals(Object o) {
    return this.map.equals(o);
  }

  @Override
  public int hashCode() {
    return this.map.hashCode();
  }
}

/*
 * Copyright 2011-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vertx.mods.shareddata;

import org.vertx.java.core.logging.Logger;
import org.vertx.java.core.logging.impl.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public abstract class SharedMap<K, V> implements ConcurrentMap<K, V> {

  private static final Logger log = LoggerFactory.getLogger(SharedMap.class);

  private final ConcurrentMap<K, V> impl;
  private final String mapName;

  public SharedMap(ConcurrentMap<K, V> map, String mapName) {
    this.impl = map;
    this.mapName = mapName;
  }

  public V putIfAbsent(K k, V v) {
    Checker.checkType(k);
    Checker.checkType(v);
    return impl.putIfAbsent(k, v);
  }

  public boolean remove(Object o, Object o1) {
    return impl.remove(o, o1);
  }

  public boolean replace(K k, V v, V v1) {
    Checker.checkType(v1);
    return impl.replace(k, v, v1);
  }

  public V replace(K k, V v) {
    Checker.checkType(v);
    V ret = impl.replace(k, v);
    return Checker.copyIfRequired(ret);
  }

  public int size() {
    return impl.size();
  }

  public boolean isEmpty() {
    return impl.isEmpty();
  }

  public boolean containsKey(Object o) {
    return impl.containsKey(o);
  }

  public boolean containsValue(Object o) {
    return impl.containsValue(o);
  }

  public V get(Object o) {
    return Checker.copyIfRequired(impl.get(o));
  }

  public V put(K k, V v) {
    Checker.checkType(k);
    Checker.checkType(v);
    return impl.put(k, v);
  }

  public V remove(Object o) {
    return Checker.copyIfRequired(impl.remove(o));
  }

  public void putAll(Map<? extends K, ? extends V> map) {
    for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
      Checker.checkType(entry.getKey());
      Checker.checkType(entry.getValue());
      this.impl.put(entry.getKey(), entry.getValue());
    }
  }

  public void clear() {
    impl.clear();
  }

  public Set<K> keySet() {
    Set<K> copied = new HashSet<>();
    for (K k : impl.keySet()) {
      copied.add(Checker.copyIfRequired(k));
    }
    return copied;
  }

  public Collection<V> values() {
    Collection<V> copied = new ArrayList<>();
    for (V v : impl.values()) {
      copied.add(Checker.copyIfRequired(v));
    }
    return copied;
  }

  public Set<Map.Entry<K, V>> entrySet() {
    Set<Map.Entry<K, V>> entries = new HashSet<>();
    for (Map.Entry<K, V> entry : impl.entrySet()) {
      entries.add(new SharedDataEntry<>(entry));
    }
    return entries;
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

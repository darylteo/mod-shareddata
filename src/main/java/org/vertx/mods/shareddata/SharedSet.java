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

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public abstract class SharedSet<E> implements Set<E> {

  private final Set<E> impl;
  private final String setName;

  public SharedSet(Set<E> impl, String setName){
     this.impl = impl;
     this.setName = setName;
  }

  public int size() {
    return impl.size();
  }

  public boolean isEmpty() {
    return impl.isEmpty();
  }

  public boolean contains(Object value) {
    return impl.contains(value);
  }

  public Iterator<E> iterator() {
    return impl.iterator();
  }

  public Object[] toArray() {
    return impl.toArray();
  }

  public <T> T[] toArray(T[] a) {
    return impl.toArray(a);
  }

  public boolean add(E e) {
    Checker.checkType(e);
    return impl.add(e);
  }

  public boolean remove(Object value) {
    return impl.remove(value);
  }

  public boolean containsAll(Collection<?> values) {
    return impl.containsAll(values);
  }

  public boolean addAll(Collection<? extends E> values) {
    for (E value : values){
      Checker.checkType(value);
    }

    return this.impl.addAll(values);
  }

  public boolean retainAll(Collection<?> values) {
    return impl.retainAll(values);
  }

  public boolean removeAll(Collection<?> values) {
    return impl.removeAll(values);
  }

  public void clear() {
    impl.clear();
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

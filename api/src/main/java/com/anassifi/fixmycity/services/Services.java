package com.anassifi.fixmycity.services;

import java.util.List;

public interface Services<T> {
    List<T> getAll();

    T get(Long id);

    T add(T t);

    T update(T t);

    boolean delete(Long id);
}

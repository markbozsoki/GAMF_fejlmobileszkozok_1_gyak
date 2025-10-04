package com.nje.mobileszkozokprojekt.data.repository.interfaces;

import java.util.List;

public interface IRepository<T> {

    List<T> getAll();

    T getById(String id);

    /**
     * Returns true on success, returns false on error
     */
    boolean insert(T entity);

    /**
     * Returns true on success, returns false on error
     */
    boolean update(T entity);

    /**
     * Returns true on success, returns false on error
     */
    boolean delete(T entity);
}

package com.nje.mobileszkozokprojekt.data.dao;

public interface DefaultDao<T> {

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

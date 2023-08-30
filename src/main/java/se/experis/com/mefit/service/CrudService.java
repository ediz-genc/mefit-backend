package se.experis.com.mefit.service;

import java.util.Set;

public interface CrudService<T, ID> {
    T findById(ID id);

    Set<T> findAll();

    T add(T entity);

    T update(ID id, T entity);

    void delete(ID id);
}

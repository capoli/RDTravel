package com.realdolmen.rdtravel.persistence;

import com.realdolmen.rdtravel.domain.AbstractEntity;

import java.util.List;

public interface CrudEJBService {
    List<AbstractEntity> findAll(Class type);

    AbstractEntity findById(Class type, Long id);

    AbstractEntity create(AbstractEntity entity);

    void deleteById(Class type, Long id);

    AbstractEntity update(AbstractEntity entity);
}

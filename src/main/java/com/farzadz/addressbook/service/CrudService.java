package com.farzadz.addressbook.service;

import java.util.List;

public interface CrudService<T, ID> {

  T create(T entity);

  T findById(ID id);

  List<T> findAll();

  T updateById(ID id, T entity);

  void deleteById(ID id);

}

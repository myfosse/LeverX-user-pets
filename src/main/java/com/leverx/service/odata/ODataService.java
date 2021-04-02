package com.leverx.service.odata;

import java.util.List;

import org.apache.olingo.odata2.api.exception.ODataNotFoundException;

/** @author Andrei Yahorau */
public interface ODataService<T> {

  Object getRelatedData(final Object sourceData, final String targetEntityName) throws ODataNotFoundException;

  T findById(final long id);

  List<T> findAll();

  void deleteById(final long id);

  void save(final T odataEntity);

  T getODataObject();
}
package com.leverx.config.odata;

import org.apache.olingo.odata2.annotation.processor.core.ListsProcessor;
import org.apache.olingo.odata2.annotation.processor.core.datasource.AnnotationValueAccess;
import org.apache.olingo.odata2.annotation.processor.core.datasource.DataSource;
import org.apache.olingo.odata2.annotation.processor.core.datasource.ValueAccess;
import org.apache.olingo.odata2.annotation.processor.core.edm.AnnotationEdmProvider;
import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.edm.provider.EdmProvider;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataContext;

public class ODataUserPetsServiceFactory extends org.apache.olingo.odata2.api.ODataServiceFactory {

  private static final String MODEL_PACKAGE = "com.leverx.model.odata";

  @Override
  public ODataService createService(final ODataContext ctx) throws ODataException {

    EdmProvider edmProvider = new AnnotationEdmProvider(MODEL_PACKAGE);
    ValueAccess valueAccess = new AnnotationValueAccess();
    DataSource dataSource = new ODataUserPetsSource();

    return createODataSingleProcessorService(
        edmProvider, new ListsProcessor(dataSource, valueAccess));
  }
}

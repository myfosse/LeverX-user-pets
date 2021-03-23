package com.leverx.config.odata;

import static org.apache.olingo.odata2.api.processor.ODataContext.HTTP_SERVLET_REQUEST_OBJECT;

import static com.leverx.config.odata.EntityManagerFilter.EM_REQUEST_ATTRIBUTE;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
import org.springframework.stereotype.Component;

@Component
public class UserPetsODataJPAServiceFactory extends ODataJPAServiceFactory {

  public UserPetsODataJPAServiceFactory() {
    setDetailErrors(true);
  }

  @Override
  public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {

    ODataJPAContext oDataJPAContext = getODataJPAContext();
    ODataContext oDataContext = oDataJPAContext.getODataContext();
    HttpServletRequest request =
        (HttpServletRequest) oDataContext.getParameter(HTTP_SERVLET_REQUEST_OBJECT);
    EntityManager entityManager = (EntityManager) request.getAttribute(EM_REQUEST_ATTRIBUTE);

    oDataJPAContext.setEntityManager(entityManager);
    oDataJPAContext.setPersistenceUnitName("default");
    oDataJPAContext.setContainerManaged(true);

    return oDataJPAContext;
  }
}

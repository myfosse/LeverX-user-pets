package com.leverx.config.odata;

import javax.ws.rs.Path;

import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.rest.ODataRootLocator;

@Path("/")
public class UserPetsODataRootLocator extends ODataRootLocator {

  private final UserPetsODataJPAServiceFactory serviceFactory;

  public UserPetsODataRootLocator(final UserPetsODataJPAServiceFactory serviceFactory) {
    this.serviceFactory = serviceFactory;
  }

  @Override
  public ODataServiceFactory getServiceFactory() {
    return this.serviceFactory;
  }
}

package com.leverx.config.odata;

import static com.leverx.constant.controller.ControllerConstant.ENDPOINT_ODATA;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath(ENDPOINT_ODATA)
public class JerseyConfig extends ResourceConfig {

  public JerseyConfig(final UserPetsODataJPAServiceFactory oDataJPAServiceFactory,
                      final EntityManagerFactory entityManagerFactory) {
    register(new UserPetsODataRootLocator(oDataJPAServiceFactory));
    register(new EntityManagerFilter(entityManagerFactory));
  }
}

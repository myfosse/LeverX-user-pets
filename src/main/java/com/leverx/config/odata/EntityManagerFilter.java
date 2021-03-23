package com.leverx.config.odata;

import static javax.ws.rs.HttpMethod.GET;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

@Provider
public class EntityManagerFilter implements ContainerRequestFilter, ContainerResponseFilter {

  public static final String EM_REQUEST_ATTRIBUTE =
      EntityManagerFilter.class.getName() + "_ENTITY_MANAGER";
  private final EntityManagerFactory entityManagerFactory;

  @Context
  private HttpServletRequest httpServletRequest;

  public EntityManagerFilter(final EntityManagerFactory entityManagerFactory) {
    this.entityManagerFactory = entityManagerFactory;
  }

  @Override
  public void filter(final ContainerRequestContext containerRequestContext) {

    EntityManager entityManager = this.entityManagerFactory.createEntityManager();
    httpServletRequest.setAttribute(EM_REQUEST_ATTRIBUTE, entityManager);
    if (!GET.equalsIgnoreCase(containerRequestContext.getMethod())) {
      entityManager.getTransaction().begin();
    }
  }

  @Override
  public void filter(
      final ContainerRequestContext containerRequestContext,
      final ContainerResponseContext containerResponseContext) {

    EntityManager entityManager =
        (EntityManager) httpServletRequest.getAttribute(EM_REQUEST_ATTRIBUTE);
    if (!GET.equalsIgnoreCase(containerRequestContext.getMethod())) {
      EntityTransaction entityTransaction = entityManager.getTransaction();
      if (entityTransaction.isActive() && !entityTransaction.getRollbackOnly()) {
        entityTransaction.commit();
      }
    }

    entityManager.close();
  }
}

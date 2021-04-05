package com.leverx.config.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppContext implements ApplicationContextAware {

  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(final ApplicationContext appContext) throws BeansException {
    applicationContext = appContext;
  }

  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }
}

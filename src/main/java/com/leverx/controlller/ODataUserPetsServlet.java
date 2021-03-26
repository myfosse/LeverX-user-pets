package com.leverx.controlller;

import static com.leverx.constant.controller.ControllerConstants.ENDPOINT_ODATA;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import org.apache.olingo.odata2.core.servlet.ODataServlet;

@WebServlet(
    name = "appServlet",
    initParams = {
      @WebInitParam(
          name = "org.apache.olingo.odata2.service.factory",
          value = "com.leverx.config.odata.ODataUserPetsServiceFactory")
    },
    urlPatterns = ENDPOINT_ODATA + "/*",
    loadOnStartup = 1)
public class ODataUserPetsServlet extends ODataServlet {}

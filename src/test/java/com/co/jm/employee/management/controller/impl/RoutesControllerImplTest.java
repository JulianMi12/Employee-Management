package com.co.jm.employee.management.controller.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.view.RedirectView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class RoutesControllerImplTest {

  @InjectMocks
  private RoutesControllerImpl routesController;

  @Test
  void redirectToHome_ShouldReturnCorrectRedirectView() {
    RedirectView result = routesController.redirectToHome();

    assertNotNull(result);
    assertEquals("/jm/employee-management/home", result.getUrl());
  }

  @Test
  void forwardToAngular_ShouldReturnCorrectPath() {
    String result = routesController.forwardToAngular();

    assertNotNull(result);
    assertEquals("forward:/index.html", result);
  }
}

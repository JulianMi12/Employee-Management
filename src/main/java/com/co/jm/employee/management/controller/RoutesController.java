package com.co.jm.employee.management.controller;

import org.springframework.web.servlet.view.RedirectView;

public interface RoutesController {

  RedirectView redirectToHome();

  String forwardToAngular();
}

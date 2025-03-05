package com.co.jm.employee.management.controller.impl;

import com.co.jm.employee.management.controller.RoutesController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RoutesControllerImpl implements RoutesController {

  @Override
  @GetMapping({"/jm", "/jm/employee-management"})
  public RedirectView redirectToHome() {
    return new RedirectView("/jm/employee-management/home");
  }

  @Override
  @RequestMapping("/jm/employee-management/{path:[^\\.]*}")
  public String forwardToAngular() {
    return "forward:/index.html";
  }
}

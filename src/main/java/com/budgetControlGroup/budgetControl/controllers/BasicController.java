<<<<<<< HEAD:src/main/java/com/budgetControlGroup/budgetControl/controllers/BasicController.java
package com.budgetControlGroup.budgetControl.controllers;
//
=======
package com.budgetControlGroup.budgetControl.testController;

>>>>>>> initial-budget-page:src/main/java/com/budgetControlGroup/budgetControl/testController/BasicController.java
import java.util.concurrent.atomic.AtomicLong;

import com.budgetControlGroup.budgetControl.models.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @RequestMapping("/greeting")
  public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    return new Greeting(counter.incrementAndGet(),
        String.format(template, name));
  }
}
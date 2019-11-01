package com.budgetControlGroup.budgetControl.controllers;

import com.budgetControlGroup.budgetControl.Models.Budget;
import com.budgetControlGroup.budgetControl.models.User;
import com.budgetControlGroup.budgetControl.workflows.BudgetCreateWorkflow;
import com.budgetControlGroup.budgetControl.workflows.LoginWorkflow;
import com.budgetControlGroup.budgetControl.workflows.RegisterWorkflow;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
@RequestMapping("/budgetCreateController")
@RestController


public class BudgetCreateController {
    @Autowired
    public BudgetCreateController(BudgetCreateWorkflow budgetCreateWorkflow) {
        this.budgetCreateWorkflow = budgetCreateWorkflow;
    }

    private BudgetCreateWorkflow budgetCreateWorkflow;

    @RequestMapping(method = RequestMethod.PUT, value="/budgetCreate")
    public Budget create_budget(@RequestBody Budget budget) {
        System.out.println("Here in the create budget endpoint method\n");
        return budgetCreateWorkflow.create_budget(budget);
    }

    @RequestMapping(method = RequestMethod.PUT, value="/budgetCheck")
    public int budget_check() {
        System.out.println("---Here in the create check endpoint method\n");
        return 0;
    }

}

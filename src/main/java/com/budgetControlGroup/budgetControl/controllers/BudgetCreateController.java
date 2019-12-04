package com.budgetControlGroup.budgetControl.controllers;

import com.budgetControlGroup.budgetControl.Models.Budget;
import com.budgetControlGroup.budgetControl.Models.Category_List;
import com.budgetControlGroup.budgetControl.workflows.BudgetCreateWorkflow;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequestMapping("/budgetCreateController")
@RestController


public class BudgetCreateController {
    @Autowired
    public BudgetCreateController(BudgetCreateWorkflow budgetCreateWorkflow) {
        this.budgetCreateWorkflow = budgetCreateWorkflow;
    }

    private BudgetCreateWorkflow budgetCreateWorkflow;

    @RequestMapping(method = RequestMethod.POST, value="/budgetCreate")
    public Budget create_budget(@RequestBody Budget budget) {
        System.out.println("---Here in the create budget endpoint method\n");
        return budgetCreateWorkflow.create_budget(budget);
    }

    @RequestMapping(method = RequestMethod.POST, value="/CatCreate")
    public Category_List create_cat(@RequestBody Category_List cats) {
        System.out.println("------Here in the cat create endpoint method----\n");
        //cats.setUser(3);
        System.out.println(cats.toString());
        System.out.println("---------Leaving create_cat method-------\n\n\n\n\n");
        budgetCreateWorkflow.create_cat(cats);
        return cats;
    }

    @RequestMapping(method = RequestMethod.GET, value="/budgetCheck")
    public String budget_check() {
        System.out.println("---Here in the create check endpoint method\n");
        return "barely alive sippy cup";
    }

}

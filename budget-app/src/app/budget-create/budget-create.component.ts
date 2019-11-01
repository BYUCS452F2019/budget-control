import { Component, OnInit } from '@angular/core';
import { Budget } from '../classes/budget';
import { BudgetCreateService } from '../services/create_budget.service';
import * as uuid from 'uuid';


@Component({
  selector: 'app-budget-create',
  templateUrl: './budget-create.component.html',
  styleUrls: ['./budget-create.component.css']
})
export class BudgetCreateComponent implements OnInit {
  result_budget: Budget;
  constructor(private budgetCreateService: BudgetCreateService) { }

  ngOnInit() {
  }

  createBudget(budgetName: string, budgetDescr: string, startDate: string, endDate: string, totalIn: number, totalOut: number): void {
    var category_list = [];
    //var category_income = [];
    var category_expense = [];
    const budget_id = uuid.v4();
    const user_id = uuid.v4();
    console.log(budget_id);
    console.log(user_id);
  	console.log(budgetName);
    console.log(budgetDescr);
    console.log(startDate);
    console.log(endDate);
    console.log(totalIn);
    console.log(totalOut);

    let budget_obj = new Budget();
    budget_obj.budget_id = budget_id;
    budget_obj.description = budgetDescr;
    budget_obj.end_date = endDate;
    budget_obj.name = budgetName;
    budget_obj.start_date = startDate;
    budget_obj.total_expense = totalOut.toString();
    budget_obj.total_income = totalIn.toString();
    budget_obj.user_id = user_id;

    console.log("Here are the category names\n");
    var cat_names = (document.getElementsByClassName("name_list") as HTMLCollectionOf<HTMLTextAreaElement>);

    for(var i=0; i<cat_names.length; i++) {
      //console.log(cat_names[i].value);
      category_list.push(cat_names[i].value);
    }

    // console.log("Here are the category incomes\n");
    // var incomes = (document.getElementsByClassName("income_list") as HTMLCollectionOf<HTMLTextAreaElement>);
    // for(var i=0; i<incomes.length; i++) {
    //   //console.log(incomes[i].value);
    //   category_income.push(incomes[i].value);
    // }

    console.log("Here are the category expenses\n");
    var expenses = (document.getElementsByClassName("expense_list") as HTMLCollectionOf<HTMLTextAreaElement>);
    for(var i=0; i<expenses.length; i++) {
      //console.log(expenses[i].value);
      category_expense.push(expenses[i].value);
    }

    console.log(category_list);
    //console.log(category_income);
    console.log(category_expense);

    this.sendBudget(budget_obj);

  	return;
  }

  sendBudget(this, budget_obj: Budget): void {
    console.log("----in sendBudget Method----")
    console.log(budget_obj);
    this.budgetCreateService.createBudget(budget_obj)
      .subscribe(result => this.result_budget = result);
    console.log("after subscribe");
    return;
  }

  addMore(e): void {
    e.preventDefault();
    var dynamicDiv = document.getElementById("dynamicDiv");

    var newline1 = document.createElement("BR")
    //var newline2 = document.createElement("BR")
    var newline3 = document.createElement("BR")
    var newline4 = document.createElement("BR")

    var catName = document.createElement("input");
    catName.setAttribute("class", "name_list");
    catName.setAttribute("type", "text");
    catName.setAttribute("placeholder", "Category Name");

    // var catIncome = document.createElement("input");
    // catIncome.setAttribute("class", "income_list");
    // catIncome.setAttribute("type", "number");
    // catIncome.setAttribute("placeholder", "Projected Income");

    var catExpense = document.createElement("input");
    catExpense.setAttribute("class", "expense_list");
    catExpense.setAttribute("type", "number");
    catExpense.setAttribute("placeholder", "Projected Expense");

    dynamicDiv.appendChild(catName);
    dynamicDiv.appendChild(newline1);
    //dynamicDiv.appendChild(catIncome);
    //dynamicDiv.appendChild(newline2);
    dynamicDiv.appendChild(catExpense);
    dynamicDiv.appendChild(newline3);
    dynamicDiv.appendChild(newline4);

  }

}

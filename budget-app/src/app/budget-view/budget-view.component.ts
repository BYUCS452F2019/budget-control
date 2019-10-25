import { Component, OnInit, Input } from '@angular/core';
import { Budget } from '../classes/budget';
import { BudgetService } from '../services/budget.service';

@Component({
  selector: 'app-budget-view',
  templateUrl: './budget-view.component.html',
  styleUrls: ['./budget-view.component.css']
})
export class BudgetViewComponent implements OnInit {

  budget: Budget;

  constructor(private budgetService: BudgetService) { }

  ngOnInit() {
  }

  getBudget(budget_id: string): void {
    console.log("called with " + budget_id)
    this.budgetService.getBudget(budget_id)
        .subscribe(budget => this.budget = budget);
  }

}

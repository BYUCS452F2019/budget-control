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

  getBudget(user_id: string): void {
    this.budgetService.getBudgetByUserId(user_id)
        .subscribe(result => this.budget = result);
  }

}

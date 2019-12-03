import { Component, OnInit, ViewChild } from '@angular/core';
import { formatCurrency } from '@angular/common';
import { Budget } from '../classes/budget';
import { BudgetService } from '../services/budget.service';
import { TransactionViewComponent } from '../transaction-view/transaction-view.component';
import { Globals } from '../Globals'

@Component({
  selector: 'app-budget-view',
  templateUrl: './budget-view.component.html',
  styleUrls: ['./budget-view.component.css']
})
export class BudgetViewComponent implements OnInit {

  @ViewChild('transactions', {static: false}) set content(content: TransactionViewComponent) {
    this.transactionView = content
  }
  transactionView: TransactionViewComponent;
  budget: Budget;
  total_income: number = 0;
  total_expense: number = 0;
  income_display: string;
  expense_display: string;

  constructor(private budgetService: BudgetService, private globals: Globals) { }

  ngOnInit() {
    this.getBudget(String(this.globals.user.userId));
  }

  getBudget(user_id: string): void {
    console.log(user_id)
    this.budgetService.getBudgetByUserId(String(user_id))
        .subscribe(result => {
          this.budget = result;
        });
  }

  getTotals(): [number, number] {
    var total_income: number = 0;
    var total_expense: number = 0;

    this.transactionView.transactions.forEach(element => {
      var amount: number = Number(element.amount.replace(/[^0-9.-]+/g,""));
      if (amount > 0) {
        total_expense += amount;
      } else {
        total_income += amount * -1;
      }
    });

    return [total_expense, total_income];
  }

  ngAfterViewChecked() {
    if (this.transactionView != undefined && this.transactionView.transactions != undefined) {
      // There may be a better way to do this...
      setTimeout(() => {
        var totals: [number, number] = this.getTotals();
        this.income_display = formatCurrency(totals[0], 'en', '$', 'USD');
        this.expense_display = formatCurrency(totals[1], 'en', '$', 'USD');
      })
    }
  }

}

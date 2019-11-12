import { Component, OnInit, ViewChild, ElementRef} from '@angular/core';
import { Budget } from '../classes/budget';
import { BudgetService } from '../services/budget.service';
import { TransactionViewComponent } from '../transaction-view/transaction-view.component';

@Component({
  selector: 'app-budget-view',
  templateUrl: './budget-view.component.html',
  styleUrls: ['./budget-view.component.css']
})
export class BudgetViewComponent implements OnInit {

  @ViewChild('transactions', {static: false}) set content(content: ElementRef) {
    this.transactionView = content
  }
  transactionView: ElementRef;
  budget: Budget;
  total_income: number = 0;
  total_expense: number = 0;

  constructor(private budgetService: BudgetService) { }

  ngOnInit() {
  }

  getBudget(user_id: string): void {
    this.budgetService.getBudgetByUserId(user_id)
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
        this.total_expense = totals[0];
        this.total_income = totals[1];
      })
    }
  }

}

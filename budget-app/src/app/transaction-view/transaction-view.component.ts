import { Component, OnInit } from '@angular/core';
import { Transaction } from '../classes/transaction';
import { TransactionService } from '../services/transaction.service';
import { AddTransactionViewComponent } from '../transaction-view/add-transaction-view.component'
import { MatDialog, MatDialogConfig } from '@angular/material'

@Component({
  selector: 'app-transaction-view',
  templateUrl: './transaction-view.component.html',
  styleUrls: ['./transaction-view.component.css']
})

export class TransactionViewComponent implements OnInit {
  transactions: Transaction[];

  constructor(private transactionService: TransactionService, private dialog: MatDialog) {}

  ngOnInit(){
    this.getTransactions('1'); //Default user_id
  }

  getTransactions(user_id: string): void {
    this.transactionService.getTransactions(user_id)
      .subscribe(result => this.transactions = result);
  }

  addTransaction(): void {
    console.log('Called addTransaction method!!!');
    let dialogRef = this.dialog.open(AddTransactionViewComponent, new MatDialogConfig());

    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog result: ${result}');
    })

  }
}

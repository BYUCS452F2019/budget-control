import { Component, OnInit } from '@angular/core';
import { Transaction } from '../classes/transaction';
import { TransactionService } from '../services/transaction.service';
import { AddTransactionViewComponent } from '../transaction-view/add-transaction-view.component'
import { MatDialog, MatDialogConfig } from '@angular/material'
import { FormControl, FormGroup, FormBuilder } from '@angular/forms'
import { TransactionRequest } from '../classes/transactionRequest';

@Component({
  selector: 'app-transaction-view',
  templateUrl: './transaction-view.component.html',
  styleUrls: ['./transaction-view.component.css']
})

export class TransactionViewComponent implements OnInit {
  transactions: Transaction[];
  transaction: TransactionRequest;
  addForm: FormGroup;

  constructor(private transactionService: TransactionService, private formBuilder: FormBuilder) {
    this.addForm = formBuilder.group({
                     budget_id: '',
                     cat_id: '',
                     amount: '',
                     date: '',
                     description: ''
                   });
  }

  onSubmit(){
    console.log(this.addForm.value);
    this.transaction = this.addForm.value;
    this.addRealTransaction(this.transaction);
    this.revert();
  }

  revert(){
    this.addForm.reset();
  }

  ngOnInit(){
    this.getTransactions('1'); //Default user_id
  }

  getTransactions(user_id: string): void {
    this.transactionService.getTransactions(user_id)
      .subscribe(result => this.transactions = result);
  }

  addRealTransaction(transaction: TransactionRequest): void {
    this.transactionService.addTransaction(transaction)
      .subscribe(result => console.log(result));
  }
}

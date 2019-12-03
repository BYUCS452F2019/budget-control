import { Component, OnInit } from '@angular/core';
import { Transaction } from '../classes/transaction';
import { TransactionService } from '../services/transaction.service';
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
  budgets: Budget[];
  categories: SingleCategory[];
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
    this.getBudgets('1'); //Default user_id
    this.getCategories('1'); //Default user_id
  }

  getTransactions(user_id: string): void {
    this.transactionService.getTransactions(user_id)
      .subscribe(result => this.transactions = result);
  }

  getCategories(user_id: string): void{
    this.transactionService.getCategories(user_id)
      .subscribe(result => this.categories = result);
  }

  getBudgets(user_id: string): void{
    this.transactionService.getBudgets(user_id)
      .subscribe(result => this.budgets = result);
  }

  addRealTransaction(transaction: TransactionRequest): void {
    transaction.user_id = '1'; //For now
    this.transactionService.addTransaction(transaction)
      .subscribe(result => console.log(result));
  }
}

import { Component, OnInit } from '@angular/core';
import { Transaction } from '../classes/transaction';
import { Budget } from '../classes/budget';
import { SingleCategory } from '../classes/singleCategory';
import { TransactionService } from '../services/transaction.service';
import { FormControl, FormGroup, FormBuilder } from '@angular/forms';
import { TransactionRequest } from '../classes/transactionRequest';
import { Category } from '../classes/category';
import { Globals } from '../Globals';

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

  constructor(private transactionService: TransactionService, private formBuilder: FormBuilder, private globals: Globals) {
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
    this.getTransactions(String(this.globals.user.userId)); //Default user_id
    this.getBudgets(String(this.globals.user.userId)); //Default user_id
    this.getCategories(String(this.globals.user.userId)); //Default user_id
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
    this.transactionService.addTransaction(transaction)
      .subscribe(result => console.log(result));
  }
}

import { Injectable } from '@angular/core';
import { Transaction } from '../classes/transaction'
import { TransactionRequest } from '../classes/transactionRequest'
import { TransactionResult } from '../classes/transactionResult'
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Budget } from '../classes/budget'
import { Category } from '../classes/category'

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  //getServerUrl: string = 'http://ec2-13-58-63-23.us-east-2.compute.amazonaws.com:8080/transaction/id?user_id='
  //postServerUrl: string = 'http://ec2-13-58-63-23.us-east-2.compute.amazonaws.com:8080/transaction/add?user_id='
  getServerUrl: string = 'http://localhost:8080/transaction/id?user_id='
  getBudgetsUrl: string = 'http://localhost:8080/transaction/budgets?user_id='
  getCategoriesUrl: string = 'http://localhost:8080/transaction/categories?user_id='
  postServerUrl: string = 'http://localhost:8080/transaction/add?user_id='
  constructor(private http: HttpClient) {}

  getTransactions(user_id: string): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(this.getServerUrl.concat(user_id));
  }

  getBudgets(user_id: string): Observable<Budget[]> {
    return this.http.get<Budget[]>(this.getBudgetsUrl.concat(user_id));
  }

  getCategories(user_id: string): Observable<Category[]> {
    return this.http.get<Category[]>(this.getCategoriesUrl.concat(user_id));
  }

  addTransaction(transaction: TransactionRequest): Observable<TransactionResult> {
    return this.http.get<TransactionResult>(this.postServerUrl.concat(transaction.user_id)
      .concat('&budget_id=').concat(transaction.budget_id)
      .concat('&cat_id=').concat(transaction.cat_id)
      .concat('&amount=').concat(transaction.amount)
      .concat('&date=').concat(transaction.date)
      .concat('&description=').concat(transaction.description));
  }
}

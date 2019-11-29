import { Injectable } from '@angular/core';
import { Transaction } from '../classes/transaction'
import { TransactionRequest } from '../classes/transactionRequest'
import { TransactionResult } from '../classes/transactionResult'
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  //getServerUrl: string = 'http://ec2-13-58-63-23.us-east-2.compute.amazonaws.com:8080/transaction/id?user_id='
  //postServerUrl: string = 'http://ec2-13-58-63-23.us-east-2.compute.amazonaws.com:8080/transaction/add?budget_id='
  getServerUrl: string = 'http://localhost:8080/transaction/id?user_id='
  postServerUrl: string = 'http://localhost:8080/transaction/add?budget_id='
  constructor(private http: HttpClient) {}

  getTransactions(user_id: string): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(this.getServerUrl.concat(user_id));
  }

  addTransaction(transaction: TransactionRequest): Observable<TransactionResult> {
    return this.http.get<TransactionResult>(this.postServerUrl.concat(transaction.budget_id)
      .concat('&cat_id=').concat(transaction.cat_id)
      .concat('&amount=').concat(transaction.amount)
      .concat('&date=').concat(transaction.date)
      .concat('&description=').concat(transaction.description));
  }
}

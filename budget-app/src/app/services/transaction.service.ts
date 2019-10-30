import { Injectable } from '@angular/core';
import { Transaction } from '../classes/transaction'
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  serverUrl: string = 'http://localhost:8080/transaction/id?user_id='
  constructor(private http: HttpClient) {}

  getTransactions(user_id: string): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(this.serverUrl.concat(user_id));
  }
}

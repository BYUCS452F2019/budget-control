import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Budget } from '../classes/budget';

@Injectable({
  providedIn: 'root'
})
export class BudgetCreateService {
  serverUrl: string = 'http://localhost:8080/budgetCreate'
  constructor(private http: HttpClient) {}

  createBudget(budget: Budget): Observable<Budget> {
    return this.http.post<Budget>(this.serverUrl, budget);
  }
}

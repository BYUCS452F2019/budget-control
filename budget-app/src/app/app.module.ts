import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'
import { RouterModule, Routes } from '@angular/router'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BudgetViewComponent } from './budget-view/budget-view.component';
import { TransactionViewComponent } from './transaction-view/transaction-view.component';
import { MatDialogModule } from '@angular/material'
import { AddTransactionViewComponent } from './transaction-view/add-transaction-view.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule, MatInputModule } from '@angular/material'
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { UserAuthComponent } from './user-auth/user-auth.component';

const appRoutes: Routes = [
  { path: 'budget', component: BudgetViewComponent },
  { path: 'transaction', component: TransactionViewComponent },
  { path:'', component:UserAuthComponent}

]

@NgModule({
  declarations: [
    AppComponent,
    BudgetViewComponent,
    UserAuthComponent,
    TransactionViewComponent,
    AddTransactionViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes
    ),
    MatDialogModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule
  ],
  entryComponents: [
    AddTransactionViewComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

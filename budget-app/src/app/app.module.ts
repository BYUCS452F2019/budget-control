import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'
import { RouterModule, Routes } from '@angular/router'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BudgetViewComponent } from './budget-view/budget-view.component';
import { TransactionViewComponent } from './transaction-view/transaction-view.component';
import { BudgetCreateComponent } from './budget-create/budget-create.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { UserAuthComponent } from './user-auth/user-auth.component';
import { Globals } from './Globals';

const appRoutes: Routes = [
  { path: 'budget', component: BudgetViewComponent },
  { path: 'transaction', component: TransactionViewComponent },
  { path: '', component:UserAuthComponent },
  { path: 'createBudget', component: BudgetCreateComponent }
]

@NgModule({
  declarations: [
    AppComponent,
    BudgetViewComponent,
    UserAuthComponent,
    TransactionViewComponent,
    BudgetCreateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes
    ),
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule
  ],
  entryComponents: [],
  providers: [
    Globals
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

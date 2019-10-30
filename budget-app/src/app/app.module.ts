import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http'
import { RouterModule, Routes } from '@angular/router'

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BudgetViewComponent } from './budget-view/budget-view.component';
import { UserAuthComponent } from './user-auth/user-auth.component';

const appRoutes: Routes = [
  { path: 'budget', component: BudgetViewComponent},
  { path:'', component:UserAuthComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    BudgetViewComponent,
    UserAuthComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes
    )
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

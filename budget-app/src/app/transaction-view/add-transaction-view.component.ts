import { Component, OnInit, Inject } from '@angular/core';
import { Transaction } from '../classes/transaction';
import { TransactionService } from '../services/transaction.service';
import { MAT_DIALOG_DATA, MatDialogRef } from "@angular/material";
import { FormGroup, FormBuilder } from "@angular/forms"

@Component({
  selector: 'app-add-transaction-view',
  templateUrl: './add-transaction-view.component.html',
  styleUrls: ['./add-transaction-view.component.css']
})

export class AddTransactionViewComponent implements OnInit {

  budget_id: string;
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AddTransactionViewComponent>,
    @Inject(MAT_DIALOG_DATA) data) {
      //this.budget_id = data.budget_id;
    }
  ngOnInit(){
    this.form = this.fb.group({
      //this.budget_id: "15";
    })
  }

  save() {
    this.dialogRef.close('pizza!');
  }

  close() {
    this.dialogRef.close();
  }
}

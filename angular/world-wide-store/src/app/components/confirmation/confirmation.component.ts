import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

export interface ConfirmationComponentData {
  message: string;
  yesText: string;
  notText: string;
}

@Component({
  selector: 'confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent implements OnInit {

  constructor(public instance: MatDialogRef<ConfirmationComponent>
    , @Inject(MAT_DIALOG_DATA) public data: ConfirmationComponentData) {
  }

  ngOnInit():void {
  }

  yes() {
    this.instance.close(true);
  }

  not() {
    this.instance.close(false);
  }

}
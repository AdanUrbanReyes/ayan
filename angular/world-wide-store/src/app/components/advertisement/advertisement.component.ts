import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

export interface AdvertisementComponentData {
  title: string;
  message: string;
  continueText: string;
}

@Component({
  selector: 'advertisement',
  templateUrl: './advertisement.component.html',
  styleUrls: ['./advertisement.component.css']
})
export class AdvertisementComponent implements OnInit {

  constructor(public instance: MatDialogRef<AdvertisementComponent>
    , @Inject(MAT_DIALOG_DATA) public data: AdvertisementComponentData) {
  }

  ngOnInit(): void {
  }

  continue() {
    this.instance.close();
  }

}
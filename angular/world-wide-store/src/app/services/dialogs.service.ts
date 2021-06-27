import { ComponentType } from '@angular/cdk/portal';
import { Injectable } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AdvertisementComponent } from '../components/advertisement/advertisement.component';
import { ConfirmationComponent } from '../components/confirmation/confirmation.component';

@Injectable({
    providedIn: 'root'
})
export class DialogsService {

  constructor(public dialog: MatDialog) {
  }

  confirm(message: string, callback : any, yesText = 'Yes', notText = 'Not'): void {
    let d = this.dialog.open(ConfirmationComponent, {
      width: '500px',
      disableClose: true,
      data: { message: message, yesText: yesText, notText: notText }
    });
    d.afterClosed().subscribe(callback);
  }

  advertise(message: string): void {
    this.dialog.open(AdvertisementComponent, {
      width: '500px',
      data: { titulo: 'Advertisement', message: message, continueText: 'Continue' }
    });
  }

  component(component: ComponentType<unknown>, settings: MatDialogConfig<any>, callback? : any) {
    let d = this.dialog.open(component, settings);
    if (callback) {
      d.afterClosed().subscribe(callback);
    }
  }

}
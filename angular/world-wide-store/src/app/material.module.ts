import { NgModule } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatCardModule } from '@angular/material/card';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE } from '@angular/material/core';
import { MatTableModule } from '@angular/material/table';
import { MatSortModule } from '@angular/material/sort';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatBottomSheetModule } from '@angular/material/bottom-sheet';

const COMPONENTS=[
  MatButtonModule
  , MatIconModule
  , MatDialogModule
  , MatToolbarModule
  , MatMenuModule
  , MatCardModule
  , MatSidenavModule
  , MatExpansionModule
  , MatFormFieldModule
  , MatSelectModule
  , MatInputModule
  , MatDatepickerModule
  , MatTableModule
  , MatSortModule
  , MatPaginatorModule
  , MatBottomSheetModule
];

export const MY_FORMATS = {
  parse: {
    dateInput: 'YYYY-MM-DD'
  },
  display: {
    dateInput: 'YYYY-MM-DD',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY'
  }
};

@NgModule({
  imports: [COMPONENTS]
  , providers : [
    {provide: MAT_DATE_LOCALE, useValue: 'en-US'}
    , {provide: MAT_DATE_FORMATS, useValue: MY_FORMATS}
  ]
  , exports: [COMPONENTS]
})
export class MaterialModule{}
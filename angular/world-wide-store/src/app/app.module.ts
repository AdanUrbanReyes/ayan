import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoadingComponent } from './components/loading/loading.component';
import { LoadingInterceptor } from './interceptors/loading.interceptor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module';
import { AdvertisementComponent } from './components/advertisement/advertisement.component';
import { ConfirmationComponent } from './components/confirmation/confirmation.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent
    , routingComponents
    , LoadingComponent
    , AdvertisementComponent
    , ConfirmationComponent
  ]
  , imports: [
    BrowserModule
    , AppRoutingModule
    , BrowserAnimationsModule
    , MaterialModule
    , FormsModule
    , ReactiveFormsModule
  ]
  , providers: [
    { provide: HTTP_INTERCEPTORS, useClass: LoadingInterceptor, multi: true }
  ]
  , bootstrap: [AppComponent]
})
export class AppModule { }
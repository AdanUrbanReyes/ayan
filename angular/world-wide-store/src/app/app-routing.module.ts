import { NgModule } from '@angular/core';
import { Routes, RouterModule, Router } from '@angular/router';
import { NotFoundPageComponent } from './components/not-found-page/not-found-page.component';
import { HomeComponent } from './components/home/home.component';

const routes: Routes = [
  { path: '', component: HomeComponent }
  , { path: '**', component: NotFoundPageComponent }
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ]
  , exports:[RouterModule]
})
export class AppRoutingModule { }

export const routingComponents = [
  HomeComponent
];
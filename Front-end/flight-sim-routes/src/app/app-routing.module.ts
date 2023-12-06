import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FlightsComponent } from './flights/flights.component';

const routes: Routes = [
  {
    path:'',
    component: HomeComponent
  },
  {
    path:'flights',
    component: FlightsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

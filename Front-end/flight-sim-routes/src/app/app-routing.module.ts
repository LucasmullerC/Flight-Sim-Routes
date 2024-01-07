import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { FlightsComponent } from './pages/flights/flights.component';
import { BannerFlightsComponent } from './pages/flights/pages/banner-flights/banner-flights.component';
import { GenerateFlightsComponent } from './pages/flights/pages/generate-flights/generate-flights.component';
import { SchedulesComponent } from './pages/schedules/schedules.component';
import { AboutComponent } from './pages/about/about.component';

const routes: Routes = [
  {
    path:'',
    component: HomeComponent
  },
  {
    path:'flights',
    component: FlightsComponent,
    children: [
      { path: '',  component: BannerFlightsComponent},
      { path: 'randomdatabase', component: GenerateFlightsComponent },
      { path: 'realflightsdata', component: GenerateFlightsComponent },
    ]
  },
  {
    path:'schedules',
    component: SchedulesComponent,
    runGuardsAndResolvers: 'always',
  },
  {
    path:'about',
    component: AboutComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{onSameUrlNavigation: 'reload'})],
  exports: [RouterModule]
})
export class AppRoutingModule { }

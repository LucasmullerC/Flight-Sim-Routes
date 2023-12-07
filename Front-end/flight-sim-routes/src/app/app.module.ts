import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './layout/sidebar/sidebar.component';
import { HomeComponent } from './pages/home/home.component';
import { DonateComponent } from './components/donate/donate.component';
import { BannerComponent } from './components/banner/banner.component';
import { ContactmeComponent } from './components/contactme/contactme.component';
import { FlightsComponent } from './pages/flights/flights.component';
import { HeaderComponent } from './layout/header/header.component';
import { GenerateFlightsComponent } from './pages/flights/pages/generate-flights/generate-flights.component';
import { BannerFlightsComponent } from './pages/flights/pages/banner-flights/banner-flights.component';
import { FlightsFormComponent } from './forms/flights-form/flights-form.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    HomeComponent,
    DonateComponent,
    BannerComponent,
    ContactmeComponent,
    FlightsComponent,
    HeaderComponent,
    GenerateFlightsComponent,
    BannerFlightsComponent,
    FlightsFormComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

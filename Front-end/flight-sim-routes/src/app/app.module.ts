import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {MatSliderModule} from '@angular/material/slider';
import { CommonModule } from '@angular/common';

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
import { ResultTableComponent } from './components/result-table/result-table.component';3
import { SchedulesComponent } from './pages/schedules/schedules.component';
import { SelectFormComponent } from './pages/schedules/pages/select-form/select-form.component';
import { CustomInputComponent } from './forms/custom-input/custom-input.component';
import { CustomSelectComponent } from './forms/custom-select/custom-select.component';
import { CustomLabelComponent } from './forms/custom-label/custom-label.component';

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
    ResultTableComponent,
    SchedulesComponent,
    SelectFormComponent,
    CustomInputComponent,
    CustomSelectComponent,
    CustomLabelComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatSliderModule,
    CommonModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import {MatSliderModule} from '@angular/material/slider';
import {MatSelectModule} from '@angular/material/select';
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
import { SubfleetsFormComponent } from './pages/schedules/pages/subfleets-form/subfleets-form.component';
import { AirportsFormComponent } from './pages/schedules/pages/airports-form/airports-form.component';
import { HelpDemandsComponent } from './components/help-demands/help-demands.component';
import { GeneralFormComponent } from './pages/schedules/pages/general-form/general-form.component';
import { ThankyouComponent } from './pages/schedules/pages/thankyou/thankyou.component';
import { DatabaseErrorComponent } from './components/database-error/database-error.component';
import { AboutComponent } from './pages/about/about.component';

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
    CustomLabelComponent,
    SubfleetsFormComponent,
    AirportsFormComponent,
    HelpDemandsComponent,
    GeneralFormComponent,
    ThankyouComponent,
    DatabaseErrorComponent,
    AboutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatSliderModule,
    CommonModule,
    MatSelectModule,
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

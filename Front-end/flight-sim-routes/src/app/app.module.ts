import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { HomeComponent } from './home/home.component';
import { DonateComponent } from './donate/donate.component';
import { BannerComponent } from './banner/banner.component';
import { ContactmeComponent } from './contactme/contactme.component';
import { FlightsComponent } from './flights/flights.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    HomeComponent,
    DonateComponent,
    BannerComponent,
    ContactmeComponent,
    FlightsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

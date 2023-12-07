import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-generate-flights',
  templateUrl: './generate-flights.component.html',
  styleUrls: ['./generate-flights.component.scss']
})
export class GenerateFlightsComponent {
  title: string = 'Flights';
  subtitle: string = '';
  
  formRandomDb = [
    {
      title: 'Departure Airport (ICAO)',
      label: 'KJFK', 
      type: 'textarea',
    },
    {
      title: 'Arrival Airport (ICAO)',
      label: 'KSFO', 
      type: 'textarea'
    },
    {
      title: 'Departure Country',
      label: 'Select', 
      type: 'textarea',
    },
  ]

  formRealFlights = [
    {
      title: 'Departure Airport (ICAO)',
      label: 'KJFK', 
      type: 'textarea',
    },
    {
      title: 'Arrival Airport (ICAO)',
      label: 'KSFO', 
      type: 'textarea'
    }
  ]

  constructor(private router: Router ) {}

  ngOnInit(): void {
    if(this.router.url == '/flights/randomdatabase'){
      this.subtitle = 'Random Database';
    }
    else{
      this.subtitle = 'Real Flights Data';
    }
  }
  
}

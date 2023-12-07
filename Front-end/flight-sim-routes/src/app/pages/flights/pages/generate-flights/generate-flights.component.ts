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
      placeholder: 'KJFK', 
      type: 'textarea',
      min: 0,
      max: 0,
      required: false,
    },
    {
      title: 'Arrival Airport (ICAO)',
      placeholder: 'KSFO', 
      type: 'textarea',
      min: 0,
      max: 0,
      required: false,
    },
    {
      title: 'Departure Country',
      placeholder: 'Select', 
      type: 'country',
      min: 0,
      max: 0,
      required: false,
    },
    {
      title: 'Arrival Country',
      placeholder: 'Select', 
      type: 'country',
      min: 0,
      max: 0,
      required: false,
    },
    {
      title: 'Continous Flights',
      placeholder: 'continous', 
      type: 'range',
      min: 1,
      max: 10,
      required: false,
    },
    {
      title: 'Distance Range (NM)',
      placeholder: 'range', 
      type: 'range',
      min: 1,
      max: 9999,
      required: true,
    },
  ]

  formRealFlights = [
    {
      title: 'Departure Airport (ICAO)',
      placeholder: 'KJFK', 
      type: 'textarea',
    },
    {
      title: 'Arrival Airport (ICAO)',
      placeholder: 'KSFO', 
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

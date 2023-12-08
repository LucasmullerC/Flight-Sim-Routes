import { Component } from '@angular/core';
import { Router } from '@angular/router';

interface FormOption {
  title: string;
  placeholder: string;
  type: string;
  min?: number;
  max?: number;
  rangeNum?:String;
  required: boolean;
}

@Component({
  selector: 'app-generate-flights',
  templateUrl: './generate-flights.component.html',
  styleUrls: ['./generate-flights.component.scss']
})
export class GenerateFlightsComponent {
  title: string = 'Flights';
  subtitle: string = '';

  selectedForm: FormOption[] = [];
  
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
      max: 10000,
      required: true,
    },
  ]

  formRealFlights = [
    {
      title: 'Database',
      placeholder: 'Select', 
      type: 'database',
      min: 0,
      max: 0,
      required: true,
    },
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
      max: 10000,
      required: true,
    },
  ]

  constructor(private router: Router ) {}

  ngOnInit(): void {
    if(this.router.url == '/flights/randomdatabase'){
      this.subtitle = 'Random Database';
      this.selectedForm = this.formRandomDb;
    }
    else{
      this.subtitle = 'Real Flights Data';
      this.selectedForm = this.formRealFlights;
    }
  }
  
}

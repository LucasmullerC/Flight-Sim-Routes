import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CountryService } from 'src/app/country.service';
import { FlightsService } from 'src/app/flights.service';
import { FormFlightsModel } from 'src/app/form-flights-model'; 

@Component({
  selector: 'app-flights-form',
  templateUrl: './flights-form.component.html',
  styleUrls: ['./flights-form.component.scss']
})
export class FlightsFormComponent {
  checkboxValue = false;
  countries: any[] = [];
  flightsForm!: FormGroup;
  minValue: any;
  maxValue: any;
  @Input()
  formOptions: FormFlightsModel[] = [];

  databaseList: String[] =[
    "OpenSky Network"
  ];

  constructor(private formBuilder: FormBuilder,private countryService: CountryService, private flightService: FlightsService) {}

  ngOnInit(): void {
    this.countryService.getAllCountries().subscribe((data) => {
      this.countries = data;
    });
    this.flightsForm = this.formBuilder.group({
      quantity: 1,
      depCountry: [''],
      arrCountry: [''],
      depAirport: '',
      arrAirport: '',
      minDistance: 0,
      maxDistance: 0,
      continuous: false
    });
  }

  onSubmit(): void {
    const formData = this.flightsForm.value;
    console.log(formData);
    this.flightService.getRandomRoute(formData);
  }

  isType(question: FormFlightsModel, type: String): boolean{
    return question.type === type;
  }

  isContinous(question: FormFlightsModel): boolean{
    return question.placeholder === 'continous'
  }

}

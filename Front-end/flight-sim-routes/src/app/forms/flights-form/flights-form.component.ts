import { Component, Input } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import { CountryService } from 'src/app/services/country.service';
import { FlightsService } from 'src/app/services/flights.service';
import { FormFlightsModel } from 'src/app/form-flights-model';

@Component({
  selector: 'app-flights-form',
  templateUrl: './flights-form.component.html',
  styleUrls: ['./flights-form.component.scss'],
})
export class FlightsFormComponent {
  checkboxValue = false;
  countries: any[] = [];
  minValue: any = 1;
  maxValue: any = 9999;
  database: any = '';
  databaseList: String[] = ['OpenSky Network'];

  @Input()
  formOptions: FormFlightsModel[] = [];
  
  @Input() flightsForm!: FormGroup;

  constructor(
    private countryService: CountryService,
    private flightService: FlightsService
  ) {}

  public validationMessages = {
    'airport': [
      { type: 'maxlength', message: 'Maximum length allowed is 4 characters.' },
      { type: 'minlength', message: 'Minimum length required is 4 characters.' },
    ],
  }

  ngOnInit(): void {
    this.countryService.getAllCountries().subscribe((data) => {
      this.countries = data;
    });
  }

  onSubmit(): void {
    if(this.flightsForm.valid){
      const formData = this.flightsForm.value;
      switch(this.flightsForm.value.database) { 
        case "undefined":{
          this.flightService.getFlights(formData,'http://localhost:8080/random-route');
          break;
        }
        case "OpenSky Network":{
          this.flightService.getFlights(formData,'http://localhost:8080/opensky-route');
          break;
        }
        default: { 
          this.flightService.getFlights(formData,'http://localhost:8080/random-route');
       }
      }
    }
  }

  onChildFormControlChange(formControl: string, controlName: string) {
    this.flightsForm.controls[controlName].setValue(formControl);
  }

  getInputValue(value: string, question: FormFlightsModel) {
    const numericValue: number = +value;
    if (numericValue <= question.max! && numericValue >= question.min!) {
      question.rangeNum = value;
    }
  }

  isType(question: FormFlightsModel, type: String): boolean {
    return question.type === type;
  }

  isContinous(question: FormFlightsModel): boolean {
    return question.placeholder === 'continous';
  }
}

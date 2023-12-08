import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CountryService } from 'src/app/country.service';


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
  selector: 'app-flights-form',
  templateUrl: './flights-form.component.html',
  styleUrls: ['./flights-form.component.scss']
})
export class FlightsFormComponent {
  checkboxValue = false;
  countries: any[] = [];
  flightsForm!: FormGroup;
  @Input()
  formOptions: FormOption[] = [];

  databaseList: String[] =[
    "OpenSky Network"
  ];

  constructor(private formBuilder: FormBuilder,private countryService: CountryService) {}

  ngOnInit(): void {
    this.countryService.getAllCountries().subscribe((data) => {
      this.countries = data;
    });
    this.flightsForm = this.formBuilder.group({
      country: [''],
    });
  }

  isContinous(question: FormOption): boolean{
    return question.placeholder === 'continous'
  }

  isRange(question: FormOption): boolean{
    return question.type === 'range'
  }

  isDatabase(question: FormOption): boolean{
    return question.type === 'database'
  }
  
  isCountry(question: FormOption): boolean{
    return question.type === 'country'
  }
  

  getInputValue(value: string,question: FormOption) {
    const numericValue: number = +value;
    if(numericValue <= question.max! && numericValue >= question.min!){
      question.rangeNum = value;
    }
   }
}

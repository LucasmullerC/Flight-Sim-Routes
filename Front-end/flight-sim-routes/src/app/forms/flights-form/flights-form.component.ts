import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { CountryService } from 'src/app/country.service';


interface FormOption {
  title: string;
  placeholder: string;
  type: string;
  min?: number;
  max?: number;
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
  @Input()
  formOptions: FormOption[] = [];

  constructor(private countryService: CountryService) {}

  ngOnInit(): void {
    this.countryService.getAllCountries().subscribe((data) => {
      this.countries = data;
    });
  }

  isContinous(question: FormOption): boolean{
    return question.placeholder === 'continous'
  }

  isCountry(question: FormOption): boolean{
    return question.type === 'country'
  }

}

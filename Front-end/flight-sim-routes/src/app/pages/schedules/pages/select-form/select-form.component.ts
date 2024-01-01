import { Component} from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { CountryService } from 'src/app/services/country.service';
import { ScheduleFormService } from 'src/app/services/schedule-form.service';
import { VirtualAirline } from 'src/app/virtual-airline';

@Component({
  selector: 'app-select-form',
  templateUrl: './select-form.component.html',
  styleUrl: './select-form.component.scss'
})
export class SelectFormComponent {
  countries: any[] = [];
  data!: VirtualAirline;
  dataList:VirtualAirline[]=[];
  errorBtn:boolean=false;
  constructor(private countryService: CountryService,
    private scheduleForm: ScheduleFormService, 
    private formBuilder: FormBuilder,) {}
  
    schedulesForm: FormGroup = this.formBuilder.group({
      airlineName: '',
      airline:['',[
        Validators.required,
        Validators.maxLength(3),
        Validators.minLength(3),
        Validators.pattern('^[a-zA-Z ]*$')]
      ],
      country: ['', Validators.required],
      hubs: '',
    });

    public validationMessages = {
      'airline': [
        { type: 'maxlength', message: 'Maximum length allowed is 3 characters.' },
        { type: 'minlength', message: 'Minimum length required is 3 characters.' },
        { type: 'pattern', message: 'Only letters are allowed in this field.' }
      ],
    }

  ngOnInit(): void {
    this.countryService.getAllCountries().subscribe((data) => {
      this.countries = data;
    });
    this.dataList=this.scheduleForm.getFormDataList();
  }

  onSubmitSelectVirtual(): void {
    const dataStorage = this.scheduleForm.getFormDataList();
    const existingDataIndex = dataStorage.findIndex(item => item.airlineName === this.schedulesForm.value.airlineName);
    this.data = dataStorage[existingDataIndex];
    this.scheduleForm.setAirlineName(this.data.airlineName);
    this.scheduleForm.setFormData(this.data);
  }

  onSubmitCreateNew(): void {
    if(this.schedulesForm.valid){
      const newAirlineData = {
        airlineName:this.schedulesForm.value.airlineName,
        airline: this.schedulesForm.value.airline,
        baseCountry: this.schedulesForm.value.country,
        hubs: this.getHubs(),
      }
      this.scheduleForm.setFormData(newAirlineData);
      this.scheduleForm.setAirlineName(this.schedulesForm.value.airlineName);
    }
    else{
      console.log("errado")
    }
  }

  onFormControlChange(formControl: string, controlName: string) {
    this.schedulesForm.controls[controlName].setValue(formControl);
  }

  getHubs():string[]{
    return this.schedulesForm.value.hubs.replace(/\[|\]/g,'').split(',');
  }
}

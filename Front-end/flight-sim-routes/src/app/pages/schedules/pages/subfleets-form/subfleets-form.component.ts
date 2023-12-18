import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Aircraft } from 'src/app/aircraft';
import { CountryService } from 'src/app/services/country.service';
import { ScheduleFormService } from 'src/app/services/schedule-form.service';
import { TableService } from 'src/app/services/table.service';
import { VirtualAirline } from 'src/app/virtual-airline';

@Component({
  selector: 'app-subfleets-form',
  templateUrl: './subfleets-form.component.html',
  styleUrl: './subfleets-form.component.scss'
})
export class SubfleetsFormComponent {
  dataList:VirtualAirline[]=[];
  countries: any[] = [];
  airlineName:string = '';
  subfleets:Aircraft[] = [];
  table_subfleets:any[] = [];
  data!: VirtualAirline;
  demands: String[] = ['Extreme Demand',"Big Demand","Medium Demand","Low Demand"];
  columns: string[] = [];
  rows: any;

  constructor(private countryService: CountryService,
    private scheduleForm: ScheduleFormService,
    private formBuilder: FormBuilder,private table: TableService) {}

  subfleetsForm: FormGroup = this.formBuilder.group({
    subfleets:'',
    hub:'',
    countries: ['', Validators.required],
    demands:[''],
  });

  ngOnInit(): void {
    this.countryService.getAllCountries().subscribe((data) => {
      this.countries = data;
    });
    this.airlineName = this.scheduleForm.getAirlineName();
  }

  onFormControlChange(formControl: string, controlName: string) {
    this.subfleetsForm.setControl(controlName, new FormControl(formControl));
  }

  onSubmitNext():void{
    if(this.isNextActive()){
      const dataStorage = this.scheduleForm.getFormDataList();
      const existingDataIndex = dataStorage.findIndex(item => item.airlineName === this.airlineName);
      this.data = dataStorage[existingDataIndex];

      const aircraftData = { ...this.data, aircraft: {...this.subfleets}  };
      this.scheduleForm.setFormData(aircraftData);
    }
  }

  addNewSubmit():void{
    this.addToSubfleets();
    this.columns = this.table.buildColumns(this.table_subfleets);
    this.rows = this.table.buildRows(this.table_subfleets);
  }

  private addToSubfleets():void{
    const aircraft = {
      subfleets:this.subfleetsForm.value.subfleets,
      hub:this.subfleetsForm.value.hub,
      countries:this.subfleetsForm.value.countries,
      extremeDemand:this.verifyDensity(this.subfleetsForm.value.demands,"Extreme Demand"),
      bigDemand:this.verifyDensity(this.subfleetsForm.value.demands,"Big Demand"),
      mediumDemand:this.verifyDensity(this.subfleetsForm.value.demands,"Medium Demand"),
      lessDemand:this.verifyDensity(this.subfleetsForm.value.demands,"Low Demand"),
    }

    const aircraft_table = {
      subfleets:this.subfleetsForm.value.subfleets,
      hub:this.subfleetsForm.value.hub,
      countries:this.subfleetsForm.value.countries,
      demands:this.subfleetsForm.value.demands,
    }
    this.subfleets.push(aircraft)
    this.table_subfleets.push(aircraft_table)
  }

  private verifyDensity(demandType:string[],requiredType:string):boolean{
    for(let type of demandType){
      if(type === requiredType){
        return true;
      }
    }
    return false;
  }

  isNextActive():boolean{
    if(this.subfleets.length == 0){
      return false;
    }
    else{
      return true;
    }
  }

}

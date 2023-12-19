import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ScheduleFormService } from 'src/app/services/schedule-form.service';
import { VirtualAirline } from 'src/app/virtual-airline';

@Component({
  selector: 'app-airports-form',
  templateUrl: './airports-form.component.html',
  styleUrl: './airports-form.component.scss'
})
export class AirportsFormComponent {
  airlineName:string = '';
  extremeDemand: string[] = [];
  bigDemand: string[] = [];
  mediumDemand: string[] = [];
  columns: string[] = ['Airport ICAO'];
  rows:any = 
    {
      extremeDemand:[],
      bigDemand:[],
      mediumDemand:[]
    };
  data!: VirtualAirline;
  demands: String[] = ['Extreme Demand',"Big Demand","Medium Demand"];

  constructor(private scheduleForm: ScheduleFormService,
    private formBuilder: FormBuilder){}

  airportsForm: FormGroup = this.formBuilder.group({
    icao:['', Validators.required],
    demands:['', Validators.required],
  });

  ngOnInit(): void {
    this.airlineName = this.scheduleForm.getAirlineName();
    const dataStorage = this.scheduleForm.getFormDataList();
    const existingDataIndex = dataStorage.findIndex(item => item.airlineName === this.airlineName);
    this.data = dataStorage[existingDataIndex];
    this.addToAirportsWithData(this.data?.extremeDemand,'Extreme Demand');
    this.addToAirportsWithData(this.data?.bigDemand,'Big Demand');
    this.addToAirportsWithData(this.data?.mediumDemand,'Medium Demand');
  }

  onFormControlChange(formControl: string, controlName: string) {
    this.airportsForm.setControl(controlName, new FormControl(formControl));
  }

  onSubmitNext():void{
    if(this.isNextActive()){
      const airportData = { ...this.data, extremeDemand: {...this.extremeDemand},bigDemand:{...this.bigDemand},mediumDemand:{...this.mediumDemand} };
      this.scheduleForm.setFormData(airportData);
    }
  }

  addNewSubmit():void{
    this.addToAirports();
  }

  private addToAirports(){
    switch(this.airportsForm.value.demands){
      case 'Extreme Demand':
        this.rows.extremeDemand.push({airport:this.airportsForm.value.icao});
        this.extremeDemand.push(this.airportsForm.value.icao);
        break;
      case 'Big Demand':
        this.rows.bigDemand.push({airport:this.airportsForm.value.icao});
        this.bigDemand.push(this.airportsForm.value.icao);
        break;
      case 'Medium Demand':
        this.rows.mediumDemand.push({airport:this.airportsForm.value.icao});
        this.mediumDemand.push(this.airportsForm.value.icao);
        break;
    }
  }

  private addToAirportsWithData(airports:any[],demand: string){
    if(airports!=null){
    for(let key in airports){
      let airport = airports[key];
      switch(demand){
        case 'Extreme Demand':
          this.rows.extremeDemand.push({airport});
          this.extremeDemand.push(airport);
          break;
        case 'Big Demand':
          this.rows.bigDemand.push({airport});
          this.bigDemand.push(airport);
          break;
        case 'Medium Demand':
          this.rows.mediumDemand.push({airport});
          this.mediumDemand.push(airport);
          break;
      }
    }
  }
  }

  isNextActive():boolean{
    if(this.rows.length == 0){
      return false;
    }
    else{
      return true;
    }
  }
}

import { Component, ElementRef, HostListener } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ScheduleFormService } from 'src/app/services/schedule-form.service';
import { SchedulesService } from 'src/app/services/schedules.service';
import { VirtualAirline } from 'src/app/virtual-airline';

@Component({
  selector: 'app-schedules',
  templateUrl: './schedules.component.html',
  styleUrl: './schedules.component.scss'
})
export class SchedulesComponent {
  activeForm: number = 1;
  constructor(private formService: ScheduleFormService,
    private scheduleService: SchedulesService,
    private scheduleForm: ScheduleFormService,
    private route: ActivatedRoute,private elementRef: ElementRef) {
      this.formService.formCompleted.subscribe(() => {
        this.nextForm();
      });
  }

  nextForm(): void {
    this.activeForm++;
    this.isFinished();
  }

  private isFinished(): void{
    if(this.activeForm == 5){
      const airlineName = this.scheduleForm.getAirlineName();
      const dataStorage = this.scheduleForm.getFormDataList();
      const existingDataIndex = dataStorage.findIndex(item => item.airlineName === airlineName);
      const data = dataStorage[existingDataIndex];

      this.getRequest(data);
    }
  }

  private getRequest(data: any):void{
    this.scheduleService.generateSchedules(data).subscribe(
      (blob: Blob) => {
        const filename = 'schedules.zip';

        this.scheduleService.downloadFile(blob, filename);
      },
      error => {
        console.error('Erro ao gerar e baixar agendamentos:', error);
      }
    );
  }
}

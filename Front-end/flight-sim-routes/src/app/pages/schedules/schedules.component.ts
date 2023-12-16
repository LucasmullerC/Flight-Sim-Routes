import { Component } from '@angular/core';
import { ScheduleFormService } from 'src/app/services/schedule-form.service';

@Component({
  selector: 'app-schedules',
  templateUrl: './schedules.component.html',
  styleUrl: './schedules.component.scss'
})
export class SchedulesComponent {
  activeForm: number = 1;
  constructor(private formService: ScheduleFormService) {
    this.formService.formCompleted.subscribe(() => {
      this.nextForm();
    });
  }

  nextForm(): void {
    this.activeForm++;
  }
}

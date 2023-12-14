import { EventEmitter, Injectable } from '@angular/core';
import { VirtualAirline } from '../virtual-airline';

@Injectable({
  providedIn: 'root'
})
export class ScheduleFormService {
  private formDataList: VirtualAirline[] = [];
  formCompleted = new EventEmitter<void>();

  constructor() {
    this.loadFromLocalStorage();
  }

  getFormDataList(): VirtualAirline[] {
    return this.formDataList;
  }

  setFormData(data: Partial<VirtualAirline>): void {
    const existingDataIndex = this.formDataList.findIndex(item => item.airlineName === data.airlineName);

    if (existingDataIndex !== -1) {
      this.formDataList[existingDataIndex] = { ...this.formDataList[existingDataIndex], ...data };
    } else {
      this.formDataList.push({ ...data } as VirtualAirline);
    }

    this.saveToLocalStorage();
    this.formCompleted.emit();
  }

  private saveToLocalStorage(): void {
    localStorage.setItem('form_data_list', JSON.stringify(this.formDataList));
  }

  private loadFromLocalStorage(): void {
    const storedData = localStorage.getItem('form_data_list');
    if (storedData) {
      this.formDataList = JSON.parse(storedData);
      this.formCompleted.emit();
    }
  }
}

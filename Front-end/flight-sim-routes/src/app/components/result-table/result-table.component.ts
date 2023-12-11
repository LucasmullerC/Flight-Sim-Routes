import { Component, Input} from '@angular/core';

export interface TableRow {
  column1: string;
  column2: string;
  column3: string;
}

@Component({
  selector: 'app-result-table',
  templateUrl: './result-table.component.html',
  styleUrl: './result-table.component.scss'
})
export class ResultTableComponent{
  @Input() columns: string[] = [];
  @Input() rows: any;

  toArray(data: any) {
    return Object.keys(data).map(key => data[key])
  }
}

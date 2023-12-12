import { Component, HostListener, Input} from '@angular/core';

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
export class ResultTableComponent {
  @Input() columns: string[] = [];
  @Input() rows: any[] = [];

  currentPage: number = 1;
  itemsPerPage: number = 0;

  toArray(data: any) {
    return Object.keys(data).map(key => data[key])
  }

  calculateItemsPerPage(): number {
    const tableHeight = document.querySelector('.container')?.clientHeight || 0;
    const rowHeight = 250;
    return Math.floor(tableHeight / rowHeight);
  }

  getPaginatedRows(): any[] {
    this.itemsPerPage = this.calculateItemsPerPage();
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    return this.rows.slice(startIndex, endIndex);
  }

  nextPage() {
    if (this.currentPage < this.totalPages()) {
      this.currentPage++;
    }
  }
  
  prevPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
    }
  }
  
  totalPages(): number {
    return Math.ceil(this.rows.length / this.itemsPerPage);
  }  

  @HostListener('window:resize', ['$event'])
  onResize(event: Event): void {
    this.currentPage = 1;
    this.itemsPerPage = this.calculateItemsPerPage();
  }
  

}

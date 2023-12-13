import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-custom-select',
  templateUrl: './custom-select.component.html',
  styleUrl: './custom-select.component.scss'
})
export class CustomSelectComponent {
  @Input() placeholder: string = '';
  @Input() required: boolean = false;
  @Input() itemList:any[] = [];
  @Input() valueField: string = 'value';
  @Input() labelField: string = 'label';

  @Output() selectChange = new EventEmitter<any>();

  onSelectChange(event: any) {
    const selectedValue = event.target.value;
    this.selectChange.emit(selectedValue);
  }

  getValue(item: any,field: string): any {
    if(this.valueField == ''){
      return item;
    }
    else{
      const fieldNames = field.split('.');
      return fieldNames.reduce((obj, fieldName) => obj && obj[fieldName], item);
    }
  }

  getLabel(item: any,field: string): any {
    if(this.labelField == ''){
      return item
    }
    else{
      const fieldNames = field.split('.');
      return fieldNames.reduce((obj, fieldName) => obj && obj[fieldName], item);
    }
  }
}

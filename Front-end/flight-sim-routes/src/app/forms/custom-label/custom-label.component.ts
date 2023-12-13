import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-custom-label',
  templateUrl: './custom-label.component.html',
  styleUrl: './custom-label.component.scss'
})
export class CustomLabelComponent {
  @Input() title: string = '';
  @Input() required: boolean = false;
}

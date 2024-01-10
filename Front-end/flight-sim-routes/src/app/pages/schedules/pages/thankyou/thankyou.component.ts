import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-thankyou',
  templateUrl: './thankyou.component.html',
  styleUrl: './thankyou.component.scss'
})
export class ThankyouComponent {
  @Input() error: boolean = false;
  @Input() loading: boolean = false;


}

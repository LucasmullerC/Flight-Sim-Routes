import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.scss']
})
export class BannerComponent {
  @Input() imageUrl: string = '';
  @Input() title: string  = '';
  @Input() description: string  = '';
  @Input() buttonText: string = '';
  @Input() buttonIconPath: string = '';
  @Input() routerLink: string = '';
  @Input() isRow: boolean = false;

}

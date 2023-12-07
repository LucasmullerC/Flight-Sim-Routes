import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BannerFlightsComponent } from './banner-flights.component';

describe('BannerFlightsComponent', () => {
  let component: BannerFlightsComponent;
  let fixture: ComponentFixture<BannerFlightsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BannerFlightsComponent]
    });
    fixture = TestBed.createComponent(BannerFlightsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

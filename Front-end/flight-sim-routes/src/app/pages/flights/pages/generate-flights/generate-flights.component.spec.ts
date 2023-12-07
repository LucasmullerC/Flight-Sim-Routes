import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenerateFlightsComponent } from './generate-flights.component';

describe('GenerateFlightsComponent', () => {
  let component: GenerateFlightsComponent;
  let fixture: ComponentFixture<GenerateFlightsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GenerateFlightsComponent]
    });
    fixture = TestBed.createComponent(GenerateFlightsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

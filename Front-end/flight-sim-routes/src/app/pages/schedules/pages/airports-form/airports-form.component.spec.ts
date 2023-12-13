import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AirportsFormComponent } from './airports-form.component';

describe('AirportsFormComponent', () => {
  let component: AirportsFormComponent;
  let fixture: ComponentFixture<AirportsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AirportsFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AirportsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GeneralFormComponent } from './general-form.component';

describe('GeneralFormComponent', () => {
  let component: GeneralFormComponent;
  let fixture: ComponentFixture<GeneralFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GeneralFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GeneralFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

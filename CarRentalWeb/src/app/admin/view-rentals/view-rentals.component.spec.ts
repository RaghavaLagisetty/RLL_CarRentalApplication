import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewRentalsComponent } from './view-rentals.component';

describe('ViewRentalsComponent', () => {
  let component: ViewRentalsComponent;
  let fixture: ComponentFixture<ViewRentalsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewRentalsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewRentalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

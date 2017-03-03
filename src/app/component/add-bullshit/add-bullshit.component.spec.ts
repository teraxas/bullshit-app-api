/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { AddBullshitComponent } from './add-bullshit.component';

describe('AddBullshitComponent', () => {
  let component: AddBullshitComponent;
  let fixture: ComponentFixture<AddBullshitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddBullshitComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddBullshitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

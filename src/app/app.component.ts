import { Component, OnInit } from '@angular/core';
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AddBullshitComponent } from './component/add-bullshit/add-bullshit.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'app works!';

  constructor(private modalService: NgbModal) { }

  openAddBullshitModal() {
    const modalRef = this.modalService.open(AddBullshitComponent);
    modalRef.componentInstance.name = 'Add Bullshit';
  }

  ngOnInit() {
  }

}

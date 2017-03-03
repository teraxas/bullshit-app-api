import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { QuestionService } from '../../service/question.service';
import { NewQuestion } from '../../model/question';

@Component({
  selector: 'app-add-bullshit',
  templateUrl: './add-bullshit.component.html',
  styleUrls: ['./add-bullshit.component.css']
})
export class AddBullshitComponent implements OnInit {

  constructor(private questionService: QuestionService) { }

  submitForm(form: any): void {
    console.log('BS added');
    const newQuestion = this.mapToQuestion(form);
    this.questionService.create(newQuestion);
  }

  private mapToQuestion(form: any): NewQuestion {
    const newQuestion = new NewQuestion;
    newQuestion.bullshit = form.bullshit;
    return newQuestion;
  }

  ngOnInit() { }

}

import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { QuestionService } from '../../service/question.service';
import { Result } from '../../model/result';
import { Question } from '../../model/question';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {

  @Output() worthyUser = new EventEmitter<Result>();

  justLoaded = true;
  question: Question = null;
  lastResponseResult: Result = null;

  constructor(private questionService: QuestionService) {
  }

  ngOnInit() {
    // this.loadQuestion();
  }

  answer(bullshit: boolean) {
    this.questionService.answer(this.question, bullshit)
      .subscribe(result => this.setlastResponseResult(result));
  }

  loadQuestion() {
    this.questionService.getQuestion()
      .subscribe(newQuestion => this.setQuestion(newQuestion));
  }

  setlastResponseResult(result: Result) {
    this.lastResponseResult = result;

    if (this.lastResponseResult.worthyToAddBullshit) {
      this.worthyUser.emit(this.lastResponseResult);
    }
  }

  setQuestion(newQuestion: Question) {
    this.question = newQuestion;
    this.lastResponseResult = null;
    this.justLoaded = false;
  }

}

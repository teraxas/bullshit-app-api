import { Component, OnInit, Output, EventEmitter } from '@angular/core';
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

  private question: Question = null;
  private lastResponseResult: Result = null;

  constructor(private questionService: QuestionService) { }

  ngOnInit() {
    this.loadQuestion();
  }

  answer(bullshit: boolean) {
    this.questionService.answer(this.question, bullshit)
      .subscribe(this.setlastResponseResult);
  }

  loadQuestion() {
    this.questionService.getQuestion()
      .subscribe(this.setQuestion);
  }

  setlastResponseResult(result: Result) {
    this.lastResponseResult = result;
    if (this.lastResponseResult.successfulAnswers === this.lastResponseResult.totalAnswers) {
      this.worthyUser.emit(this.lastResponseResult);
    }
  }

  setQuestion(question: Question) {
    this.question = question;
    this.lastResponseResult = null;
  }

}

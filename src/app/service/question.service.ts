import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { environment } from '../../environments/environment';
import { Question, NewQuestion } from '../model/question';
import { Result } from '../model/result';
import { SerializationHelper } from '../util/serialization.helper';

@Injectable()
export class QuestionService {
  private static readonly answerUrl = environment.apiURL + 'question/answer';
  private static readonly questionUrl = environment.apiURL + 'question/get';
  private static readonly createUrl = environment.apiURL + 'question/create';

  constructor(private http: Http) { }

  getQuestion(): Observable<Question> {
    return this.http.get(QuestionService.questionUrl)
                    .map(this.extractData)
                    .map(obj => SerializationHelper.toInstance(new Question(), obj))
                    ;
  }

  answer(question: Question, bullshit: boolean): Observable<Result> {
    return this.http.post(QuestionService.answerUrl, {id: question.id, answer: bullshit})
                    .map(this.extractData)
                    .map(obj => SerializationHelper.toInstance(new Result(), obj))
                    ;
  }

  create(question: Question): Observable<Question> {
    return this.http.post(QuestionService.createUrl, question)
                    .map(this.extractData)
                    .map(obj => SerializationHelper.toInstance(new Question(), obj))
                    ;
  }

  private extractData(res: Response) {
    return res.text() ? res.json() : null;
  }

}

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
  private answerUrl = environment.apiURL + 'question/answer';
  private questionUrl = environment.apiURL + 'question/get';
  private createUrl = environment.apiURL + 'question/create';

  constructor(private http: Http) { }

  getQuestion(): Observable<Question> {
    return this.http.get(this.questionUrl)
                    .map(this.extractData)
                    .map(obj => SerializationHelper.toInstance(new Question(), obj))
                    .catch(this.handleError);
  }

  answer(question: Question, bullshit: boolean): Observable<Result> {
    return this.http.post(this.answerUrl, {id: question.id, bullshit: bullshit})
                    .map(this.extractData)
                    .map(obj => SerializationHelper.toInstance(new Result(), obj))
                    .catch(this.handleError);
  }

  create(question: Question): Observable<Question> {
    return this.http.post(this.createUrl, question)
                    .map(this.extractData)
                    .map(obj => SerializationHelper.toInstance(new Question(), obj))
                    .catch(this.handleError);
  }

  private extractData(res: Response) {
    console.debug('Response: ',  res);
    let body = res.json();
    return body || { };
  }

  private handleError (error: any) {
    // TODO: Use remote logging ?
    let errMsg: string;
    console.error(errMsg);
    return Observable.throw(errMsg);
  }

}

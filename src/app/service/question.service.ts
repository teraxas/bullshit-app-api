import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';

import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { environment } from '../../environments/environment';
import { Question, NewQuestion } from '../model/question';
import { Result } from '../model/result';

@Injectable()
export class QuestionService {
  private answerUrl = environment.apiURL + 'question/answer';
  private questionUrl = environment.apiURL + 'question/get';
  private createUrl = environment.apiURL + 'question/create';

  constructor(private http: Http) { }

  getQuestion(): Observable<Question> {
    return this.http.get(this.questionUrl)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  answer(question: Question, bullshit: boolean): Observable<Result> {
    return this.http.post(this.answerUrl, {id: question.id, bullshit: bullshit})
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  create(question: Question): Observable<Result> {
    return this.http.post(this.createUrl, question)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body.data || { };
  }

  private handleError (error: Response | any) {
    // TODO: Use remote logging ?
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }

}

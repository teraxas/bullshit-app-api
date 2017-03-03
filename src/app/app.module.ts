import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { QuestionComponent } from './component/question/question.component';
import { QuestionService } from './service/question.service';
import { AddBullshitComponent} from './component/add-bullshit/add-bullshit.component';

@NgModule({
  declarations: [
    AppComponent,
    QuestionComponent,
    AddBullshitComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    NgbModule.forRoot()
  ],
  providers: [
    QuestionService
  ],
  entryComponents: [
    AddBullshitComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

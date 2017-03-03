import { User } from './user';

export class Question {

  id: number;

  question: string;
  creator: User;

  constructor() { }

}

export class NewQuestion extends Question {

  explanation: string;
  bullshit: boolean;

  constructor() {
    super();
  }

}

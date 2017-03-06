export class SerializationHelper {
  static toInstance<T>(obj: T, jsonObj: any): T {
    if (!jsonObj) {
      return null;

    } else if (typeof obj['fromJSON'] === 'function') {
      obj['fromJSON'](jsonObj);

    } else {
      for (const propName in jsonObj) {
        obj[propName] = jsonObj[propName];
      }

    }

    return obj;
  }
}
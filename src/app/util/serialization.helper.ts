export class SerializationHelper {
  static toInstance<T>(obj: T, jsonObj: any): T {
    console.debug(jsonObj);
    if (typeof obj["fromJSON"] === "function") {
      obj["fromJSON"](jsonObj);
    }
    else {
      for (var propName in jsonObj) {
        obj[propName] = jsonObj[propName]
      }
    }

    return obj;
  }
}
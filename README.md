# IT'S NOT CRAP. IT'S BULLSHIT! #

This is a simple app with facts as questions, either true or bullshit. 
I started it with a target of learning some new stuff about AngularJS and Spring Boot.

### How do I get set up? ###

* Get JDK 1.8
* From this point - maven should take care of the rest:
  * Download back-end dependencies
  * Download Node.js
  * Download Bower
  * Use Bower to download front-end dependencies:
    * AngularJS
    * Bootstrap
* To run with PostgreSQL installed - setup environment variable JDBC_DATABASE_URL (Database URL in JDBC format). It's also a standard Heroku Java setup variable.
* To run without PostgreSQL installed - run with "dev" Spring Boot profile - H2 embeded DB will be used as fallback:
```
-Dspring.profiles.active=dev
```

### Contribution guidelines ###

* Writing tests
* Code reviews
* Other guidelines

### Who do I talk to? ###

Karolis Jocevičius - passionate Java developer from Lithuania
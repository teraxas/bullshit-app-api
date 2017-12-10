[![Build Status](https://travis-ci.org/teraxas/bullshit-app-api.svg?branch=master)](https://travis-ci.org/teraxas/bullshit-app-api)
[![Heroku](http://heroku-badge.herokuapp.com/?app=bullshit-app-api&root=swagger-ui.html)](https://bullshit-app.herokuapp.com/)

# IT'S NOT CRAP. IT'S BULLSHIT! #

This is a simple app with facts as questions, either true or bullshit. 
I started it with a target of learning some new stuff about AngularJS, Spring Boot and Javaslang functional library.
After some time rewrote whole thing to use Angular (2) and moved to gradle (previously used maven) - there is a git tag right before this switch.

The app is hosted on Heroku: [bullshit-app-api](https://bullshit-app-api.herokuapp.com/)

Frontend app:

* [bullshit-app on heroku](https://bullshit-app.herokuapp.com/)
* [bullshit-app on bitbucket](https://bitbucket.org/teraxas/bullshit-app-frontend-v2/)
 
## How do I get set up? ###

* Get JDK 1.8
* run ```gradlew bootRun``` to launch tomcat on ```localhost:8080```
* To run with PostgreSQL installed - setup environment variable JDBC_DATABASE_URL (Database URL in JDBC format). It's also a standard Heroku Java setup variable.
* To run without PostgreSQL installed - run with "dev" Spring Boot profile - H2 embeded DB will be used as fallback: ```-Dspring.profiles.active=dev```

## Pipelines

On commit to master, pipelines deploy app automatically to Heroku.

## Docker

Dockerfile and docker-compose.yml allow running application on Docker with Postgres database.
To do that you need:

* Have Docker and Docker Compose installed
* Run ```gradlew buildDocker dockerCompose```

### Swagger

API docs are available.

* Swagger ui: [Swagger ui](https://bullshit-app-api.herokuapp.com/swagger-ui.html)
* Swagger API docs JSON: [api-docs](https://bullshit-app-api.herokuapp.com/v2/api-docs)

### Contribution guidelines ###

* Writing tests
* Code reviews
* Other guidelines

## Who do I talk to? ###

Karolis Jocevičius ([*@kjocevicius*](https://twitter.com/kjocevicius)) - passionate Java developer from Lithuania

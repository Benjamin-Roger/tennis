# Tennis app - Back-End
This app fetches and serves player data consumed by Tennis app front-end.

## Technologies used
- Java 11 
- Spring Boot
- OpenCsv to read CSV file
- Junit for testing

## Scope
This application exposes API that return:
- summaried data about all players
- detailed data about specific players
- general stats

On startup, it fetches and stores data in memory. This data is fetched from:
- l'Atelier's API for general player data
  - on failure, the app will use a local file as fallback
- WTA and ATP data for missing information (birthday/age) from https://github.com/JeffSackmann/tennis_atp and https://github.com/JeffSackmann/tennis_wta

## Launch the application
To start, run the following
```sh
mvn clean install -DskipTests
mvn spring-boot:run
```


## Content & architecture
The app has been build following the principles of clean-architecture: entities, use cases, interface adapters and drivers/framework.

The files are sorted as such:
- /src:
  - /domain:
    - /entity: includes the base entities
    - /useCase: includes the use cases, with their input and output boundaries 
  - /interfaceAdapter: includes the presenters, gateways, controllers
  - /drivers:
    - /repository: code to fetch information from external APIs and allow other service to get player data 
    - /web: code to manage spring boot web server and controllers

The goal of this architecture was to make the core business logic depend as little as possible:
- on the source data from the different APIs, either its format or its sources, the core business logic remains completely independent
- on the library used for the web server, the REST controller contains no logic, it only returns the data in DTO templates.
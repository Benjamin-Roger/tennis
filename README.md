# Tennis app
This app displays tennis player data collected from different sources.
Both back-end and front-end applications have been built on Clean Architecture principles, see the local Readme for more details.

## Launch the application

Make sure to have Docker and docker-compose (with version above 2) installed.

To start the application, run at the root of the repository: 
```shell
docker-compose build
```

Once the images are built, run:
```shell
docker-compose up
```

Open the http://localhost in your browser.

## Architecture
### Back
The back application is made with Java 11. It exposes API giving player data.

The back application gets data about players from differents sources and stores them locally.

The app is packaged by a Dockerfile in /back.

### Front
The front application is made in React with Create React App. It displays:
- a homepage with a list of tennis players
- a detailed player page with the player's statistics
- a statistics page with general data

The app is packaged by a Docker in /front, the files are served by a Dockerized Apache web server.
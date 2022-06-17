# Tennis app - Front End
This app displays data fetched from tennis back-end.

## Scope
This application is made in React with Create-React-App. It displays:
- a homepage with a list of tennis players, that can be filtered by their name and country name
- a detailed player page with the player's statistics
- a statistics page with general data

## Technologies used
- React for UI
- create-react-app to build the static files
- Tailwind for styles
- Eslint to ensure proper code formatting
- framer-motion for animations
- jest for testing

## Launch the application
Create a .env file with the following values to fetch data from the back app API
```markdown
REACT_APP_BASE_API_URL=<BACK_API>
REACT_APP_PLAYER_SUMMARIES_PATH=<PATH_TO_GET_PLAYER_SUMMARIES>
REACT_APP_PLAYER_DETAIL_PATH=<PATH_TO_GET_PLAYER_DETAIL>
REACT_APP_GENERAL_STATS_PATH=<PATH_TO_GET_GENERAL_STATS
```

Example:
```markdown
REACT_APP_BASE_API_URL=http://localhost:8080/
REACT_APP_PLAYER_SUMMARIES_PATH=player/summaries
REACT_APP_PLAYER_DETAIL_PATH=player/%id%
REACT_APP_GENERAL_STATS_PATH=stats
```

To start, run the following
```sh
npm ci && npm start
```

## Content & architecture
The app has been build following the principles of clean-architecture: entities, use cases, interface adapters and drivers/framework.

The files are sorted as such:
- /infra: files used for the Docker image configuration
- /src:
  - /data: files necessary for front-end logic (country name translations, etc.)
  - /domain:
    - /entity: includes the base entity, a player
    - /useCase: different use cases, one for each page in our case (display single player, display filtered player summaries, general stats)
  - /interface-adapter: includes the presenters, gateways, controllers, interactors
  - /drivers:
    - /api: code to fetch information from the API
    - /view: React code to display the UI
    - /service: code used for routing, as various utils

The goal of this architecture was to make the core business logic depend as little as possible:
- on the source data, either its format or its sources
- on the library used for UI, which contains no logic at all, it only displays the data in templates


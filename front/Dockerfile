FROM node:latest AS build
WORKDIR /build

COPY package.json package.json
COPY package-lock.json package-lock.json
RUN npm ci

COPY public/ public
COPY src/ src
COPY tsconfig.json tsconfig.json
COPY tailwind.config.js tailwind.config.js
COPY postcss.config.js postcss.config.js

ENV REACT_APP_BASE_API_URL=http://localhost:8080/
ENV REACT_APP_PLAYER_SUMMARIES_PATH=player/summaries
ENV REACT_APP_PLAYER_DETAIL_PATH=player/%id%
ENV REACT_APP_GENERAL_STATS_PATH=stats

RUN npm run build

FROM httpd:alpine
WORKDIR /usr/local/apache2/htdocs
COPY --from=build /build/build/ .

FROM node:18

WORKDIR /app

COPY package.json package-lock.json* ./  

RUN npm install
RUN npm install -g rimraf

COPY . .

ARG REACT_APP_BASE_URL
ENV REACT_APP_BASE_URL=${REACT_APP_BASE_URL}

ARG WDS_SOCKET_HOST
ARG CHOKIDAR_USEPOLLING
ARG WATCHPACK_POLLING
ENV WDS_SOCKET_HOST=${WDS_SOCKET_HOST}
ENV CHOKIDAR_USEPOLLING=${CHOKIDAR_USEPOLLING}
ENV WATCHPACK_POLLING=${WATCHPACK_POLLING}

RUN rimraf build && rimraf node_modules/.cache

# RUN npm run build

EXPOSE 3000

# CMD ["npm", "start"]

CMD ["npm", "run", "serve"]



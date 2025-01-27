FROM node:20-alpine AS base
WORKDIR /app
COPY package.json package-lock.json ./

RUN npm install

# Stage for development
FROM base AS dev
WORKDIR /app
COPY . .
EXPOSE 3000 
ENV CHOKIDAR_USEPOLLING=true
ENV WATCHPACK_POLLING=true
CMD ["sh", "-c", "npm run clean && npm run dev -- --host"]

# Stage for production
FROM base AS build
WORKDIR /app
COPY . .
RUN npm run clean && npm run build

FROM node:20-alpine AS prod
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install --omit=dev
COPY --from=build /app/dist /app/dist
EXPOSE 3000
CMD ["npm", "run", "start", "--", "--host"]

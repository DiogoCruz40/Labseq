# Install dependencies
FROM node:lts-alpine AS dependencies
WORKDIR /usr/src/app
COPY package.json .
RUN npm install --production --silent

# Build the application
FROM dependencies AS build
WORKDIR /usr/src/app
COPY . .
RUN npm run build --prod

# Create a smaller final image
FROM node:lts-alpine AS final
WORKDIR /usr/src/app
COPY --from=dependencies /usr/src/app/node_modules ./node_modules
COPY --from=build /usr/src/app/dist ./dist
COPY server.js .
COPY server /usr/src/app/server

# EXPOSE 3000

CMD ["npm", "start"]
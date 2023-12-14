# The builder from node image
FROM node:lts-alpine AS builder
# build-time variables
# prod|sandbox its value will be come from outside
# ARG env=prod

RUN apk update && apk add --no-cache make git

# Move our files into directory name "app"
WORKDIR /app
COPY package*.json /app/
RUN cd /app && npm install
COPY .  /app

# Build with $env variable from outside
# RUN cd /app && npm run build:$env
RUN npm run build

# Build a small nginx image with static website
FROM nginx:alpine

RUN rm -rf /usr/share/nginx/html/*
COPY nginx.conf /etc/nginx/nginx.conf
# fixed unshown font icons with mime.types file and ngnix.conf > location ~* .(js|css|ttf|ttc|otf|eot|woff|woff2)$ {...
COPY mime.types /etc/nginx/mime.types
# Copy from builder all files inside /app/dist/pt folder to nginx server path /usr/share/nginx/html
COPY --from=builder /app/dist/frontend/browser /usr/share/nginx/html
CMD ["nginx", "-g", "daemon off;"]
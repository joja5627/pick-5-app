FROM node:8.9.3-alpine
EXPOSE 3000
WORKDIR /app
COPY package.json package-lock.json /app/
ENV NODE_ENV production
RUN npm install --production 
COPY . /app
CMD [ "npm", "start" ]
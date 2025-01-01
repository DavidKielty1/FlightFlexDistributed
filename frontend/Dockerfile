# Use Node.js base image
FROM node:18-slim as build

# Set the working directory
WORKDIR /app

# Copy only package.json and package-lock.json for caching
COPY package.json package-lock.json* ./  

# Install dependencies
RUN npm install

# Copy the rest of the application code
COPY . .

# Build the React app
RUN npm run build

# Use an Nginx image to serve the built files
FROM nginx:alpine

# Copy the build output to the Nginx HTML directory
COPY --from=build /app/build /usr/share/nginx/html

# Expose the React app port
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]

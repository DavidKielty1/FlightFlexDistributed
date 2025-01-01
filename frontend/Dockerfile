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

# # FOR PRODUCTION. Changed for hotlead
# Build the React app
# RUN npm run build

# # FOR PRODUCTION. Changed for hotlead
# Use an Nginx image to serve the built files
# FROM nginx:alpine

# # FOR PRODUCTION. Changed for hotlead
# Copy the build output to the Nginx HTML directory
# COPY --from=build /app/build /usr/share/nginx/html

# # FOR PRODUCTION. Changed for hotlead
# # Expose the React app port
# EXPOSE 80

# # FOR PRODUCTION. Changed for hotlead
# Expose port 3000 (React dev server)
EXPOSE 3000

# # FOR PRODUCTION. Changed for hotlead
# Start Nginx
# CMD ["nginx", "-g", "daemon off;"]

# Default command (will be overridden by `command` in docker-compose.yml)
CMD ["npm", "start"]

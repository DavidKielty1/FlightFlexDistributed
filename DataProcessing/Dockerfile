# Use Python base image
FROM python:3.11-slim

# Set the working directory
WORKDIR /app

# Copy the requirements file and install dependencies
COPY requirements.txt /app/
RUN pip install --no-cache-dir -r requirements.txt

# Copy the application code
COPY . /app/

# Expose the API port (if using FastAPI, for example)
EXPOSE 8000

# Command to run the Python app
CMD ["python", "app.py"]

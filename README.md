# URL Shortener Application

This is a URL Shortener application built with Java, Springboot and PostgreSQL, and deployed on Google Cloud Platform (GCP) using a CI/CD pipeline.

## Overview

The URL Shortener application allows users to shorten long URLs and access them via short, easy-to-remember links.

## Features

- Shorten long URLs
- Redirect to original URLs
- Track usage statistics
- RESTful API for programmatic access

## Architecture

The application follows a microservices architecture and is deployed on GCP. The key components are:

1. **Git Repository**: Stores the application's source code.
2. **Cloud Build**: Automates the build, test, and deployment process.
3. **Docker**: Containerizes the application.
4. **Google Container Registry**: Stores Docker images.
5. **Google Kubernetes Engine (GKE)**: Orchestrates container deployment and scaling.
6. **Load Balancer**: Distributes traffic across multiple instances.


## Prerequisites

- [Docker](https://www.docker.com/get-started)
- [Google Cloud SDK](https://cloud.google.com/sdk/docs/install)
- A GCP account with permissions to use Google Kubernetes Engine, Container Registry, and Cloud Build.

## Installation

1. **Clone the repository:**
    ```sh
    git clone https://github.com/AdrianaVAmancio/url-shortener.git
    cd url-shortener
    ```

2. **Build the Docker image:**
    ```sh
    docker build -t url-shortener .
    ```

3. **Run the Docker container locally:**
    ```sh
    docker run -p 8080:8080 url-shortener
    ```

## Usage

- Access the application at `http://localhost:8080`.
- Use the API to shorten URLs:
    ```sh
    curl -X POST http://localhost:8080/api/shorten -d '{"url": "https://example.com"}' -H "Content-Type: application/json"
    ```

## CI/CD Pipeline

The CI/CD pipeline is set up using Google Cloud Build. The pipeline performs the following steps:

1. **Trigger**: Automatically triggered by a push to the Git repository.
2. **Build**: Builds the Docker image for the application.
3. **Test**: Runs unit and integration tests.
4. **Deploy**: Pushes the Docker image to Google Container Registry and deploys it to GKE.

## Deployment

1. **Configure GCP credentials:**
    Ensure you have the necessary credentials and permissions set up for your GCP project.
    ```sh
    gcloud auth login
    gcloud config set pr

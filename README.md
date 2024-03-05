
# TinyProduct Microservice

## Introduction

TinyProduct is a microservice designed for TinyShop, focusing on managing product data efficiently and effectively. This service is built using Java and is containerized using Docker, making it scalable and easy to deploy.

## Features

- Product management capabilities.
- Containerized for easy deployment and scalability.
- Integrated with Jenkins for continuous integration and delivery.

## Prerequisites

- Java JDK (version specified in `pom.xml`).
- Docker and Docker Compose.
- Jenkins for CI/CD (optional but recommended).

## Setup and Deployment

1. **Clone the Repository**: `git clone https://github.com/johndbs/tinyproduct`.
2. **Build the Docker Container**: Navigate to the project directory and run `docker build -t tinyproduct .`.
3. **Run the Container**: `docker run -d -p 8080:8080 tinyproduct`.

## Usage

After deployment, the service is accessible at `http://localhost:8080/` where you can manage product data as per the API documentation (to be added).

## Contributing

Contributions are welcome. Please fork the repository, make your changes, and submit a pull request.

## Issues and Support

For issues, suggestions, or support, please open an issue in the GitHub repository.

## Contact

For further assistance or inquiries, please open an issue in the repository as the primary method of contact.

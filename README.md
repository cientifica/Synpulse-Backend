### e-Banking Portal
The e-Banking Portal is a REST API that provides a paginated list of money account transactions for a given customer in an arbitrary calendar month. It retrieves transactions from a Kafka topic, calculates the total credit and debit values at the current exchange rate, and returns the results to the client.

##Table of Contents
．Project Description\n
．Features
．Installation
．Usage
．Configuration


##Project Description
The e-Banking Portal is designed to provide a seamless and secure banking experience for customers. It leverages Kafka for real-time transaction processing and integrates with a third-party API to retrieve the current exchange rates. By using this API, customers can view their transaction history and get an overview of their account activity in their preferred currency.

##Features
Retrieves and paginates money account transactions for a given customer.
Calculates the total credit and debit values at the current exchange rate.
Supports authentication using JWT tokens for secure access to the API.
Consumes transactions from a Kafka topic for real-time processing.
Integrates with an external API to retrieve the current exchange rates.
Supports deployment to Kubernetes/OpenShift using Docker containers.

##Installation
To install and set up the e-Banking Portal, follow these steps:

1. Clone the repository: `git clone <repository-url>`
2. Navigate to the project directory: `cd e-banking-portal`
3. Install the dependencies: `npm install`
4. Set up the necessary configuration properties (see Configuration section).
5. Build the Docker image: `docker build -t e-banking-portal` .
6. Deploy the application to Kubernetes/OpenShift (see deployment documentation for specific instructions).

##Usage
To use the e-Banking Portal API, follow these steps:

1. Obtain a JWT token by authenticating with the system.
2. Include the JWT token in the `Authorization` header of your API requests.
3. Make a GET request to the `/api/transactions` endpoint to retrieve the paginated list of transactions.
4. Specify the customer ID and the calendar month in the query parameters to filter the transactions.
5. The response will include the paginated list of transactions, along with the total credit and debit values at the current exchange rate.

##Configuration
The e-Banking Portal requires the following configuration properties:

・Kafka broker URL: Set the `KAFKA_BROKER_URL` environment variable to the URL of the Kafka broker.
．External API URL: Set the `EXTERNAL_API_URL`environment variable to the URL of the external API for exchange rates.
．External API Key: Set the `EXTERNAL_API_KEY`environment variable to the API key for accessing the external API.
．JWT Secret Key: Set the `JWT_SECRET_KEY`environment variable to the secret key used for JWT token signing.

Ensure that you provide the correct values for these configuration properties before running the application.

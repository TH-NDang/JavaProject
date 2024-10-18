# API Documentation

## Customer API
- **GET /api/customers**: Retrieve a list of customers
- **GET /api/customers/{id}**: Retrieve detailed information about a specific customer
- **POST /api/customers**: Create a new customer
- **PUT /api/customers/{id}**: Update customer information
- **DELETE /api/customers/{id}**: Delete a customer

## Project API
- **GET /api/projects**: Retrieve a list of projects
- **GET /api/projects/{id}**: Retrieve detailed information about a specific project
- **POST /api/projects**: Create a new project
- **PUT /api/projects/{id}**: Update project information
- **DELETE /api/projects/{id}**: Delete a project
- **GET /api/projects/{id}/orders**: Retrieve a list of orders for a specific project

## Order API
- **GET /api/orders**: Retrieve a list of orders
- **GET /api/orders/{id}**: Retrieve detailed information about a specific order
- **POST /api/orders**: Create a new order
- **PUT /api/orders/{id}**: Update order information
- **DELETE /api/orders/{id}**: Delete an order
- **GET /api/orders/{id}/services**: Retrieve a list of services for a specific order
- **PUT /api/orders/{id}/status**: Update the status of an order

## Service API
- **GET /api/services**: Retrieve a list of services
- **GET /api/services/{id}**: Retrieve detailed information about a specific service
- **POST /api/services**: Create a new service
- **PUT /api/services/{id}**: Update service information
- **DELETE /api/services/{id}**: Delete a service

## Feedback API
- **GET /api/feedbacks**: Retrieve a list of feedbacks
- **GET /api/feedbacks/{id}**: Retrieve detailed information about a specific feedback
- **POST /api/feedbacks**: Create a new feedback
- **PUT /api/feedbacks/{id}**: Update feedback information
- **DELETE /api/feedbacks/{id}**: Delete a feedback
- **GET /api/customers/{id}/feedbacks**: Retrieve a list of feedbacks for a specific customer

## Rating API
- **GET /api/ratings**: Retrieve a list of ratings
- **GET /api/ratings/{id}**: Retrieve detailed information about a specific rating
- **POST /api/ratings**: Create a new rating
- **PUT /api/ratings/{id}**: Update rating information
- **DELETE /api/ratings/{id}**: Delete a rating
- **GET /api/customers/{id}/ratings**: Retrieve a list of ratings for a specific customer

## Authentication API
- **POST /api/auth/login**: Login
- **POST /api/auth/logout**: Logout
- **POST /api/auth/register**: Register a new account
- **GET /api/auth/me**: Retrieve information about the current user

## Dashboard API
- **GET /api/dashboard/stats**: Retrieve overall statistics
- **GET /api/dashboard/recent-projects**: Retrieve a list of recent projects
- **GET /api/dashboard/recent-orders**: Retrieve a list of recent orders
- **GET /api/dashboard/top-customers**: Retrieve a list of top customers

## Koi Pond API
- **GET /api/koi-ponds**: Retrieve a list of koi ponds
- **GET /api/koi-ponds/{id}**: Retrieve detailed information about a specific koi pond
- **POST /api/koi-ponds**: Create a new koi pond
- **PUT /api/koi-ponds/{id}**: Update koi pond information
- **DELETE /api/koi-ponds/{id}**: Delete a koi pond
- **GET /api/koi-ponds/{id}/maintenance-history**: Retrieve the maintenance history of a koi pond
- **POST /api/koi-ponds/{id}/schedule-maintenance**: Schedule maintenance for a koi pond

## Koi Fish API
- **GET /api/koi-fish**: Retrieve a list of koi fish
- **GET /api/koi-fish/{id}**: Retrieve detailed information about a specific koi fish
- **POST /api/koi-fish**: Add a new koi fish to a pond
- **PUT /api/koi-fish/{id}**: Update koi fish information
- **DELETE /api/koi-fish/{id}**: Remove a koi fish from a pond
- **GET /api/koi-ponds/{id}/koi-fish**: Retrieve a list of koi fish in a specific pond

## Maintenance Service API
- **GET /api/maintenance-services**: Retrieve a list of maintenance services
- **GET /api/maintenance-services/{id}**: Retrieve detailed information about a specific maintenance service
- **POST /api/maintenance-services**: Create a new maintenance service
- **PUT /api/maintenance-services/{id}**: Update maintenance service information
- **DELETE /api/maintenance-services/{id}**: Delete a maintenance service
- **POST /api/maintenance-services/{id}/schedule**: Schedule a maintenance service

## Koi Pond Design API
- **GET /api/pond-designs**: Retrieve a list of koi pond designs
- **GET /api/pond-designs/{id}**: Retrieve detailed information about a specific design
- **POST /api/pond-designs**: Create a new koi pond design
- **PUT /api/pond-designs/{id}**: Update design information
- **DELETE /api/pond-designs/{id}**: Delete a design
- **GET /api/pond-designs/{id}/cost-estimate**: Retrieve cost estimate for a specific design

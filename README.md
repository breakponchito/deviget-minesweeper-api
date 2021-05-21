# deviget-minesweeper-api
api to implement the mine sweeper game

>> Structure of the project

I added the structure of the project following best practice to use modules with maven

>> Dependencies

Then I added the dependencies for my microservice:

- openLiberty: to run microservice
- microprofile:  for the microprofile specification
- jakarta: for the java enterprise specification

>> Configuration

Then adding more configuration for the service:
- server config for liberty and including microprofile dependencies
- persistence.xml for database connection 

>> API creation

Creating base componentes for the api
- Registering service application and define base path
- Creating the model
- Creating the service

Documenting components

Adding unit testing for the service

Adding components for the game

- images
- create starting page
- create form

>> adding to the gcp service using kubernetes engine

fixing issue for cors and removing unnecessary modules from the project

remaining work to do is the persistence

- Ability to start a new game and preserve/resume the old ones
- Persistence
- Ability to support multiple users/accounts
 




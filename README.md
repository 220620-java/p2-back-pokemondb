# Team Synergy Project 2 - PokéPost Web Application
The web application (back end) of Team Synergy's Pokémon database, PokéPost. This will interact with the [PokéAPI](https://pokeapi.co/) to pull Pokémon data and construct a page containing data about a particular Pokémon along with fan comments and fanart. Developed for Revature's training program.

## Description
Using the [PokéAPI](https://pokeapi.co/) API, users will be able to do the following:
* View any Pokémon or get all Pokémon in a particular region.
* Add comments on a Pokémon page for tips or comments and like them.
* Add fanart to a Pokémon page and comment/like them.
* Add Pokémon to a wishlist to keep track of the ones they're trying to find.
* Register and log into an account.

Administrators can moderate comments and fanart and even ban users from posting.

## Getting Started

### Dependencies
* Java 8
* Spring Boot
* PostgreSQL

### Installing
Clone the repository, compile and package with Maven, and deploy on a server:
```
// Clone this repository
git clone https://github.com/220620-java/p2-back-pokemondb.git

// Build the jar file
mvn clean package

// To run the Spring Application
java -jar target/pokemondb-0.0.1-SNAPSHOT.jar
```

### Usage
Deploy this web application on a server and set up the PostgreSQL database using the provided DDL scripts. Use our front end to create a website: [Front End](https://github.com/220620-java/p2-front-pokemondb)

## Authors
Contact us for any support or questions:

__Team Synergy__
* [Colby Tang](https://github.com/colbyktang)
* [Barry Norton](https://github.com/BarritoN78)
* [Dylan Cooley](https://github.com/dcee96)
* [Chan Phengaroune](https://github.com/Zoomo11)

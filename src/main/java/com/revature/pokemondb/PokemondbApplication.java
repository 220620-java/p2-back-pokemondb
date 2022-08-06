package com.revature.pokemondb;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.export.graphite.GraphiteMetricsExportAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.graphql.GraphQlMetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.mongo.MongoMetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.mongo.MongoHealthContributorAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.mongo.MongoReactiveHealthContributorAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.neo4j.Neo4jHealthContributorAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jReactiveDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jReactiveRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.graphql.GraphQlAutoConfiguration;
import org.springframework.boot.autoconfigure.graphql.data.GraphQlQueryByExampleAutoConfiguration;
import org.springframework.boot.autoconfigure.graphql.data.GraphQlQuerydslAutoConfiguration;
import org.springframework.boot.autoconfigure.graphql.data.GraphQlReactiveQueryByExampleAutoConfiguration;
import org.springframework.boot.autoconfigure.graphql.data.GraphQlReactiveQuerydslAutoConfiguration;
import org.springframework.boot.autoconfigure.graphql.reactive.GraphQlWebFluxAutoConfiguration;
import org.springframework.boot.autoconfigure.graphql.rsocket.GraphQlRSocketAutoConfiguration;
import org.springframework.boot.autoconfigure.graphql.security.GraphQlWebFluxSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.graphql.security.GraphQlWebMvcSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.graphql.servlet.GraphQlWebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.groovy.template.GroovyTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoReactiveAutoConfiguration;
import org.springframework.boot.autoconfigure.mustache.MustacheAutoConfiguration;
import org.springframework.boot.autoconfigure.neo4j.Neo4jAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;

// Exclude unused classes from auto configuration to make Spring boot 0.1 seconds faster
@EnableAutoConfiguration(exclude = {
	ThymeleafAutoConfiguration.class,
	GraphQlAutoConfiguration.class,
	GraphQlMetricsAutoConfiguration.class,
	GraphQlQueryByExampleAutoConfiguration.class,
	GraphQlQuerydslAutoConfiguration.class,
	GraphQlRSocketAutoConfiguration.class,
	GraphQlReactiveQueryByExampleAutoConfiguration.class,
	GraphQlReactiveQuerydslAutoConfiguration.class,
	GraphQlWebFluxAutoConfiguration.class,
	GraphQlWebFluxSecurityAutoConfiguration.class,
	GraphQlWebMvcAutoConfiguration.class,
	GraphQlWebMvcSecurityAutoConfiguration.class,
	GraphiteMetricsExportAutoConfiguration.class,
	GroovyTemplateAutoConfiguration.class,
	MongoAutoConfiguration.class,
	MongoDataAutoConfiguration.class,
	MongoHealthContributorAutoConfiguration.class,
	MongoMetricsAutoConfiguration.class,
	MongoReactiveAutoConfiguration.class,
	MongoReactiveDataAutoConfiguration.class,
	MongoReactiveHealthContributorAutoConfiguration.class,
	MongoReactiveRepositoriesAutoConfiguration.class,
	MongoRepositoriesAutoConfiguration.class,
	MustacheAutoConfiguration.class,
	Neo4jDataAutoConfiguration.class,
	Neo4jHealthContributorAutoConfiguration.class,
	Neo4jReactiveDataAutoConfiguration.class,
	Neo4jReactiveRepositoriesAutoConfiguration.class,
	Neo4jRepositoriesAutoConfiguration.class,
	Neo4jAutoConfiguration.class
})
@SpringBootApplication
public class PokemondbApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PokemondbApplication.class, args);

		Scanner keyboard = new Scanner(System.in);
		printLogo ();
		System.out.println("Welcome to the PokePost Spring Application!");
		System.out.print("Press Enter to stop the application: ");
		keyboard.nextLine();
		keyboard.close();
		System.out.println("Exiting the PokePost Spring Application");
		System.exit(0);
	}

	public static void printLogo () {
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader("src\\main\\java\\com\\revature\\pokemondb\\pokepost_logo.txt"));
			for (String line; (line = reader.readLine()) != null;) {
				System.out.println(line); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) try { reader.close(); } catch (IOException ignore) {}
		}
	}
}

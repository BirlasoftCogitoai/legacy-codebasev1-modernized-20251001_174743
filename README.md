```
# Legacy EGP Application

A legacy Java EE (J2EE) enterprise application built with EJB 3.x, JSP/Servlets, and JPA. This multi-module Maven project represents a typical enterprise government portal (EGP) system from the mid-2000s era.

## Architecture

- **egp-core-ejb**: Core business logic with EJB 3.x stateless session beans, JPA entities, and DAOs
- **egp-portal-war**: Web presentation layer with JSP pages and servlets
- **egp-ear**: Enterprise Archive packaging both WAR and EJB JAR modules

## Modernization Strategy

This project will be modernized to adopt the latest best practices and technologies such as Spring Boot, RESTful APIs, and modern front-end frameworks. 

### Key Changes

- Migration from EJB to Spring Boot
- Replace JSP/Servlets with modern front-end frameworks (e.g., React, Angular)
- Use modern ORM (e.g., Hibernate) with JPA
- Improve security with modern authentication and authorization mechanisms
- Implement comprehensive logging and error handling

## Getting Started

To build and run the project:

1. Ensure you have JDK 11 or higher installed.
2. Install Maven 3.6 or higher.
3. Clone the repository.
4. Navigate to the project directory.
5. Use the following commands to build and run the application:

```sh
mvn clean install
mvn spring-boot:run
```

## Documentation

Detailed documentation can be found in the `docs` directory.

## Contributing

Contributions are welcome! Please see the `CONTRIBUTING.md` file for guidelines on how to contribute to this project.

## License

This project is licensed under the MIT License - see the `LICENSE` file for details.
```
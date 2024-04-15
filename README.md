# RickAndMorty

An Android application developed using Kotlin that utilizes the Rick and Morty API to display a list of characters, detailed information for each character, and locations from the TV series. This application adheres to the principles of Clean Architecture, Repository Pattern with UseCases, and MVVM Architecture in the presentation layer. Layout designs are implemented using XML. Additionally, unit tests have been incorporated for testing purposes.


# Demo

![demo rickandmorty](https://github.com/MelanieBenavente/RickAndMorty/assets/120126915/ec7826d3-8785-4dd0-9943-6e51a951bf13)


# Architecture

**What is Clean Architecture?**
Clean Architecture is a software design approach proposed by Robert C. Martin, emphasizing separation of concerns and code maintainability. It provides a clear structure for building applications, keeping business logic independent of external details.
![MVVM architecture](https://github.com/MelanieBenavente/RickAndMorty/assets/120126915/f29ce4a0-7319-4ed5-bbec-cab22f24103c)

**Why Use It? Clean Architecture offers:**

 - Scalability: Facilitates adaptation to changing requirements.
 - Maintainability: Simplifies code comprehension and modification.
 - Unit Testing: Enables more effective and reliable testing.
 - Flexibility: Facilitates component replacement and updates.

**Key Principles:**

 - Separation of Concerns: Keeps business logic separate from external details.
 - Dependency Rule: Higher-level layers do not depend on lower-level ones.
 - SOLID Principles: Promotes modularity, maintainability, and extensibility.
 - Use Cases: Represents application-specific logic, aiding in testing and reuse.
   
**Conclusion:**

Clean Architecture provides a systematic way to build more maintainable and scalable applications, ensuring greater flexibility and testability.

# Layers:

![LAYERS](https://github.com/MelanieBenavente/RickAndMorty/assets/120126915/e18273ee-b17e-403f-9bfc-eaffda26267c)

**DATA:**

Manages data storage and retrieval.
Contains data models specific to network and local database.
Implements repositories that interact with data and mappers to transform between different models.

**DOMAIN:**

It's the core of the application, where the main logic and data models reside.
Independent of other layers such as the user interface or the database.
Includes data models and use cases representing specific actions.

**UI:**

Handles the user interface and user interaction.
Includes views (such as activities or fragments) and viewmodels that handle presentation logic.
Connects domain logic with the user interface to display information to the user appropriately.







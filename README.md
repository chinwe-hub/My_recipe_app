# Recipe Book Application

A console-based Java application that allows users to discover recipes, view cooking instructions, generate shopping lists, save recipes to text files, and submit ratings and reviews.

The project demonstrates object-oriented programming, separation of responsibilities, Java Collections, input validation, file handling, and unit testing through a practical recipe-management application.

## Table of Contents

- [Project Overview](#project-overview)
- [Project Objectives](#project-objectives)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Application Workflow](#application-workflow)
- [How the Features Work](#how-the-features-work)
- [Object-Oriented Programming](#object-oriented-programming)
- [Prerequisites](#prerequisites)
- [Installation and Setup](#installation-and-setup)
- [Running the Application](#running-the-application)
- [Running Tests](#running-tests)
- [File Exports](#file-exports)
- [Input Validation and Error Handling](#input-validation-and-error-handling)
- [Author](#author)

## Project Overview

The Recipe Book Application is a Java console application designed to help users find recipes and prepare for cooking in an organized way.

Users can sign up or log in, browse a collection of predefined recipes, search by recipe name or category, view recipe details, save recipes as text files, generate shopping lists from recipe ingredients, and submit ratings and written reviews.

Recipes are provided as predefined application data. Users can interact with existing recipes but cannot create, edit, or delete the recipe collection through the application.

The project is structured into separate classes and packages to make the code easier to understand, maintain, test, and extend.

## Project Objectives

The main objectives of the project are to:

1. Apply object-oriented programming concepts to a practical Java application.
2. Organize code into classes with clearly defined responsibilities.
3. Implement recipe searching and viewing functionality.
4. Model ingredients as separate objects containing names, quantities, and units.
5. Generate shopping lists from recipe ingredients.
6. Export recipes and shopping lists to text files.
7. Allow users to rate recipes and submit written reviews.
8. Apply relevant SOLID design principles.
9. Practise Java Collections, exception handling, input validation, and file I/O.
10. Use Maven and automated tests to support project building and verification.

## Features

### 1. User Registration and Login

The application provides a basic sign-up and login workflow.

Users can:
- Register an account with the information required by the application.
- Log in using their registered credentials.
- Access the application's main menu after successful authentication.
- Receive an error message when their credentials are invalid.

The authentication workflow is separated from the console interface to keep user interaction and authentication logic independent.

**Note:** Account storage and password protection depend on the current authentication implementation. A production application should use secure password hashing and persistent account storage.

### 2. Browse Recipes

Users can browse the predefined recipe collection.

Recipes contain information such as:
- Recipe ID
- Recipe name
- Category
- Description
- Preparation time
- Cooking time
- Ingredients
- Cooking instructions

Examples of recipe categories include breakfast, main course, soup, vegetarian meals, and salads.

### 3. Search Recipes

Users can search for recipes by name or category.

The search functionality helps users find relevant recipes without manually browsing the entire collection.

Expected search behaviour includes:
- Accepting a search term.
- Matching recipes against the requested name or category.
- Displaying matching recipes.
- Informing users when no matching recipes are found.
- Allowing users to return to the menu or perform another search.

### 4. View Recipe Details

Users can select a recipe to view its full details.

The details include the recipe name, category, description, preparation time, cooking time, ingredients with their quantities and units, and step-by-step cooking instructions.

### 5. Save Recipes to Text Files

Users can export an individual recipe into a `.txt` file for future reference.

The exported recipe can include:
- Recipe title and category
- Description
- Preparation and cooking times
- Ingredient quantities and units
- Cooking instructions

Timestamped filenames can be used to reduce the risk of overwriting previously exported recipes.

### 6. Generate Shopping Lists

The shopping list feature extracts the ingredients needed to prepare a selected recipe.

A generated shopping list focuses on ingredients, quantities, and measurement units rather than the full cooking instructions.

For example:

```text
SHOPPING LIST: JOLLOF RICE

[ ] 3 cups rice
[ ] 4 tomatoes
[ ] 2 onions
[ ] 0.5 cup cooking oil
```

Shopping lists can be exported to text files so users can refer to them while purchasing ingredients.

The shopping list generation logic is separated from file-writing functionality, allowing each responsibility to be maintained independently.

### 7. Rate and Review Recipes

Users can submit a rating and a short written review for a recipe.

The rating uses a scale from 1 to 5, where 1 represents the lowest rating and 5 represents the highest rating.

The review workflow includes:
- Selecting a recipe using its ID.
- Entering a rating between 1 and 5.
- Providing a written review within the application's character limit.
- Receiving confirmation when the review is accepted.

Where supported by the implementation, recipe reviews can be used to calculate an average rating and display feedback associated with a recipe.

Review persistence depends on the storage mechanism implemented in the application. Reviews held only in memory will not survive an application restart.

## Technology Stack

| Technology | Purpose |
|---|---|
| Java | Application logic and object-oriented design |
| Maven | Project management, dependency management, and build automation |
| Java Collections | Managing recipes, ingredients, users, and reviews |
| Java Stream API | Filtering and processing collections where appropriate |
| Java File I/O | Exporting recipes and shopping lists |
| JUnit 5 | Automated unit testing |
| IntelliJ IDEA | Development environment |
| Git and GitHub | Version control and source-code hosting |

The project uses standard Java functionality where possible, keeping the application suitable for learning and demonstrating core Java development skills.

### Responsibilities of the Main Components

| Component | Responsibility |
|---|---|
| `Main` | Starts the application and coordinates the main menu flow |
| `Recipe` | Represents a recipe and its associated information |
| `Ingredient` | Represents an ingredient, including its name, quantity, and unit |
| `User` | Represents an application user |
| `Review` | Represents a recipe rating and written review |
| `SampleRecipes` | Supplies the predefined recipe collection |
| `AuthenticationService` | Handles authentication-related operations |
| `ShoppingListService` | Builds shopping lists from recipe ingredients |
| `AuthenticationView` | Handles console-based sign-up and login interaction |
| `ShoppingListView` | Handles shopping-list menu options and user input |
| `FileExporter` | Writes exported content to text files |

## Application Workflow

The general application workflow is:

```text
Start Application
       |
       v
Welcome Menu
       |
       v
Sign Up or Log In
       |
       v
Main Menu
       |
       +---- Browse Recipes
       |
       +---- Search by Name or Category
       |
       +---- View Recipe Details
       |           |
       |           +---- Save Recipe
       |           |
       |           +---- Rate and Review
       |
       +---- Generate Shopping List
       |           |
       |           +---- Export Shopping List
       |
       +---- Log Out or Exit
```

The exact menu labels depend on the implemented console interface.

## How the Features Work

### Recipe and Ingredient Relationship

A recipe contains a collection of ingredient objects rather than storing every ingredient as an unstructured string.

Conceptually, the relationship is:

```text
Recipe
  |
  +-- Ingredient
  +-- Ingredient
  +-- Ingredient
  |
  +-- Instructions
  +-- Recipe Details
```

An ingredient can contain:

- Name: `rice`
- Quantity: `3`
- Unit: `cups`

This structure makes it easier to display ingredients, validate their values, and generate shopping lists.

### Searching

The search logic checks the recipe collection and returns matching recipes. Search behaviour depends on the implemented matching rules, such as whether partial and case-insensitive matches are supported.

### Shopping List Generation

The shopping list service obtains ingredients from the selected recipe and formats them into a shopping list. The export component then writes the resulting text to a file.

Keeping these operations separate makes the shopping list easier to test and maintain.

### Ratings and Reviews

A review associates user feedback with a recipe. The application validates the rating and review text before accepting the submission.

If reviews are stored in a collection on the recipe object, they remain available only for the lifetime of the relevant in-memory data unless persistence is added.

## Object-Oriented Programming

The application demonstrates the four main pillars of object-oriented programming.

### 1. Encapsulation

Encapsulation protects an object's internal state by keeping fields private and exposing appropriate methods for accessing or modifying that state.

For example, the `Ingredient` class can keep its fields private and validate ingredient names and quantities in its constructor.

### 2. Abstraction

Abstraction hides implementation details behind clear methods and class interfaces.

For example, a shopping list view can call a service method to generate a list without needing to know every detail of how the ingredients are processed.

### 3. Inheritance

Inheritance allows a class to reuse or extend the behaviour of another class.

Inheritance should only be used where a genuine parent-child relationship exists. The presence of multiple classes does not, by itself, mean inheritance is being used.

### 4. Polymorphism

Polymorphism allows objects to be treated through a shared parent type or interface while providing different implementations of behaviour.

Where interfaces or overridden methods are used, they can support flexible and replaceable components.

## Prerequisites

Before running the application, install:

- **Java Development Kit (JDK):** Java 21 is the development environment used for the current project.
- **Apache Maven:** Required to build the project and run its tests.
- **IntelliJ IDEA:** Recommended IDE, although any suitable Java IDE or terminal can be used.
- **Git:** Required if you want to clone the source code from GitHub.

Verify Java and Maven installations:

```bash
java -version
mvn -version
```

The versions displayed should be compatible with the project's `pom.xml`.

## Installation and Setup

### 1. Clone the Repository

If you are setting up the project from GitHub, run:

```bash
git clone https://github.com/chinwe-hub/My_recipe_app.git
```

Move into the project directory:

```bash
cd My_recipe_app
```

Alternatively, open the existing project directory in IntelliJ IDEA.

### 2. Open the Project

In IntelliJ IDEA:

1. Select **File → Open**.
2. Select the project root directory.
3. Open the folder containing `pom.xml`.
4. Allow IntelliJ to load and synchronize the Maven project.
5. Confirm that the JDK is configured correctly under Project Structure.

### 3. Resolve Maven Dependencies

Open the terminal in the project root and run:

```bash
mvn clean install
```

This command cleans previous build output, compiles the project, runs configured tests, and packages the application if the build succeeds.

## Running the Application

### Using IntelliJ IDEA

1. Open `Main.java`.
2. Locate the `main` method.
3. Click the green Run icon beside the method.
4. Interact with the application through the Run console.

### Using Maven

If the project is configured with a Maven execution plugin, use its configured run command. Otherwise, run `Main.java` directly from IntelliJ or use the appropriate Java launch command for the compiled application.

The application is console-based, so menu options and prompts appear in the terminal or IDE Run window.

## Running Tests

The project uses JUnit 5 for automated testing where configured.

Run all Maven tests from the project root:

```bash
mvn clean test
```

To build and test the project:

```bash
mvn clean install
```

Tests can verify behaviours such as:

- Validating ingredient names and quantities.
- Ensuring recipe data is stored correctly.
- Checking how null or invalid values are handled.
- Verifying collection immutability where required.
- Testing search and shopping list logic.
- Validating rating limits and review behaviour.

Only behaviours covered by actual test classes are automatically verified. Add further tests as the application grows.

## File Exports

The application can export recipes and shopping lists as `.txt` files.

Example shopping-list filename:

```text
jollof_rice_shopping_list_20261028_193201_475.txt
```

An example relative path is:

```text
exports/jollof_rice_shopping_list_20261028_193201_475.txt
```

### Where Are Exported Files Stored?

A relative path such as `exports/filename.txt` is resolved from the application's working directory. When running from IntelliJ IDEA, this is normally determined by the **Working directory** configured in the run configuration.

To locate the file:

1. Open IntelliJ IDEA.
2. Select **Run → Edit Configurations**.
3. Select the application's run configuration.
4. Check the working directory.
5. Open that directory in File Explorer and look for `exports`.

The file is created on the local computer where the application is running. It is not automatically saved to Downloads, Documents, or a cloud service.

### Avoiding File Overwrites

Timestamped filenames can help ensure that each export receives a unique name. If the export logic must guarantee that an existing file is never overwritten, it should explicitly use an appropriate file-creation option and handle filename conflicts.

## Input Validation and Error Handling

The application should validate user input before processing it.

Examples include:

- Rejecting blank ingredient names.
- Rejecting ingredient quantities that are not greater than zero.
- Restricting ratings to values between 1 and 5.
- Enforcing the review character limit.
- Handling invalid recipe IDs.
- Handling empty search terms and searches with no matches.
- Handling invalid menu selections.
- Reporting file-writing errors clearly.

Validation helps prevent invalid data from entering the application's models and makes the console experience more reliable.

## Author

**Recipe Book Application**

Developed as a Java project to practise object-oriented programming, clean code organization, file handling, testing, and software design principles.

**Repository:** [My_recipe_app on GitHub](https://github.com/chinwe-hub/My_recipe_app)

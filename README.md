# Inventory Management System

This project started as an end-of-course assessment for a Java Frameworks course. The original version focused on basic product and part management, with products and parts associated directly in a relatively simple structure.

After passing the course, I wanted to modify the app and add more meaningful functionality. I refactored the project into a more realistic inventory and assembly workflow. The main goal was to make the domain model better match the actual scenario: products are assembled from parts, and each product should define a recipe that describes which parts in what quantities are required.

## Refactor Summary

The project was restructured around a clearer domain model:

- **Product** represents a finished item that can be assembled or disassembled.
- **Part** represents inventory used to build products.
- **Recipe** belongs to a product and defines how that product is assembled.
- **RecipeLine** belongs to a recipe and stores associated parts and the quantity of that part required.

This replaced the simpler product/part association with a more explicit recipe-based structure. The new model better supports assembly behavior, inventory checks, and future expansion.

## Features Added

After restructuring the entities and relationships, I implemented several new workflows:

- Product detail views with associated recipe information
- Recipe editing for each product
- Adding and removing parts from a product recipe
- Incrementing and decrementing recipe line quantities
- Assembly of finished products from part inventory
- Disassembly of finished products back into part inventory
- Inventory validation for part minimums and maximums
- Protection against deleting parts that are currently used in recipes
- Success and error messages for assembly/disassembly actions

## Technical Focus

This project emphasizes backend/domain modeling concepts, including:

- Java and Spring Boot
- Spring MVC controllers
- Thymeleaf server-rendered views
- JPA/Hibernate entity relationships
- Service-layer business logic
- Post/Redirect/Get form handling
- Inventory rule validation

## Purpose

The refactor was intended to move the project beyond a basic course submission and turn it into a more meaningful portfolio project. The focus was on practicing realistic backend design: modeling relationships clearly, keeping business rules in the service layer, and building workflows that reflect how an inventory assembly system might actually behave.
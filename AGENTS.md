# Repository Guidelines

## Project Structure & Module Organization
This Gradle-based Spring Boot service keeps runtime code under `src/main/java/com/univirtus/todo/demo`, with the entry point in `DemoApplication.java`. Shared configs and static assets live in `src/main/resources` (`application.properties`, `static/`, `templates/`). Test scaffolding belongs under `src/test/java`, mirroring the package tree; add fixture files in `src/test/resources` when needed.

## Build, Test, and Development Commands
`./gradlew bootRun` starts the dev server with live reload via Spring DevTools. `./gradlew build` compiles with the Java 25 toolchain, runs tests, and produces an executable jar in `build/libs/`. Use `./gradlew test` for faster feedback when iterating on unit or integration tests. Run `./gradlew clean` before reproducing CI issues to reset generated output.

## Coding Style & Naming Conventions
Follow standard Spring Boot patterns: classes in `UpperCamelCase`, methods and fields in `lowerCamelCase`, and constants in `UPPER_SNAKE_CASE`. Keep controllers, services, and repositories in dedicated packages as the domain grows. Use Lombok annotations judiciously and prefer constructor injection. Indent Java code with tabs (matching the existing source), and let your IDE format imports consistently.

## Testing Guidelines
Write JUnit 5 tests under `src/test/java`, naming classes `<Target>Test`. Prefer descriptive `@DisplayName` strings and organise nested cases with `@Nested`. Use Spring Boot’s `@SpringBootTest` for integration coverage and H2 for lightweight datastore scenarios. Aim to exercise new endpoints and services with both happy-path and failure tests before opening a PR.

## Commit & Pull Request Guidelines
Use imperative, scoped commit subjects (`feat: add task entity`, `fix: guard null ids`). Keep commits focused and include context in the body when behaviour changes. PRs should describe intent, list key changes, reference related issues, and attach screenshots or curl samples for API-facing updates. Confirm `./gradlew test` passes locally and note any follow-up work in the PR description.

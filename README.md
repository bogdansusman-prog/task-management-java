# Task Management

A desktop application for managing employees and the tasks assigned to them, built in Java with a Swing GUI.

Built as a university assignment for the **Programming Techniques** course (2026).

## Features

- Add employees and create tasks
- **Two kinds of tasks:**
  - **Simple tasks** have a start and an end hour. Tasks that pass midnight are supported, e.g. 23 → 2 = 3 hours.
  - **Complex tasks** are made of other tasks, simple or complex, nested to any depth.
- Assign tasks to employees and switch a task between *Completed* and *Uncompleted*
- Work duration per employee, counting only completed tasks
- **Statistics:**
  - employees who worked more than 40 hours
  - number of completed and uncompleted tasks for each employee
- Save and load all data to and from a file (`data.ser`) using Java serialization

## Design highlights

- **Composite pattern:** `ComplexTask` holds a list of `Task`s. `estimateDuration()` is polymorphic, so a complex task adds up the durations of its subtasks recursively.
- **Sealed class hierarchy:** `sealed abstract class Task permits SimpleTask, ComplexTask`.
- **Validation:** duplicate employees or tasks are rejected, and a complex task cannot contain itself.
- **Layered packages:**

| Package | Responsibility |
|---|---|
| `model` | `Task`, `SimpleTask`, `ComplexTask`, `Employee` |
| `businesslogic` | `TasksManagement` (employee → tasks map), `Utility` (statistics) |
| `dataaccess` | `SerializationService` (save/load to file) |
| `gui` | Swing interface (`MainFrame`) |

UML diagrams (class, package and use-case, in draw.io format) are in [`src/main/resources`](src/main/resources).

## Tech stack

Java 25 · Swing · Maven · Java Serialization

## Running

```bash
mvn compile
mvn exec:java -Dexec.mainClass="org.example.tests.Main"
```

Or open the project in IntelliJ IDEA and run `org.example.tests.Main`.

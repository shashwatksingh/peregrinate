# Peregrinate — Project Notes for Claude

## Overview
A multi-language DSA (Data Structures & Algorithms) practice repository with implementations of LeetCode problems.

**Root:** `/Users/shashwatksingh/practice/peregrinate/`

```
peregrinate/
├── .claude/
│   └── project-notes.md        ← this file
├── README.md                   (empty)
├── java/                       ← Java module (Maven)
└── go/                         ← Go module
```

---

## Java Module

### Paths
| Purpose          | Path                                                                 |
|------------------|----------------------------------------------------------------------|
| Module root      | `java/`                                                              |
| Build file       | `java/pom.xml`                                                       |
| Source root      | `java/src/main/java/org/shashwatksingh/`                             |
| DSA sources      | `java/src/main/java/org/shashwatksingh/dsa/`                         |
| Test root        | `java/src/test/java/org/shashwatksingh/`                             |
| DSA tests        | `java/src/test/java/org/shashwatksingh/dsa/`                         |
| Surefire reports | `java/target/surefire-reports/`                                      |

### Build & Test
- **Build tool:** Maven (no `mvnw` wrapper in repo; `mvn` is also NOT on shell PATH in this env)
- **Java version:** 25 (compiler source + target both `25`)
- **Run via IDE:** Use the VS Code / Cursor built-in Maven test runner.
- **Run via shell (when `mvn` is on PATH):**
  ```bash
  cd /Users/shashwatksingh/practice/peregrinate/java
  mvn test                          # all tests
  mvn test -Dtest=<TestClassName>   # single test class
  ```
- ⚠️  No `.mvn/wrapper/` directory exists — do NOT rely on any `mvnw` script.

### Dependencies (`pom.xml`)
- **JUnit Jupiter** `5.11.0` (test scope) — only testing library.
- **maven-surefire-plugin** `3.3.1`
- No Mockito, AssertJ, or other libs — use only `org.junit.jupiter.api.Assertions`.

### Package Structure
- Main classes: `org.shashwatksingh.dsa`
- Test classes: `org.shashwatksingh.dsa` (same package, different source tree)

---

## Java Test Conventions (JUnit 5)

**File naming:** `<SourceClassName>Test.java` placed under `java/src/test/java/org/shashwatksingh/dsa/`.

**Standard imports:**
```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
```

**Class structure pattern:**
```java
@DisplayName("<Problem Name> Tests")
class <ClassName>Test {

    private <ClassName> instance;

    @BeforeEach
    void setUp() {
        instance = new <ClassName>();
    }

    // ═══════════════════════════════════════════════════════════
    //  methodName()  — short description, complexity
    // ═══════════════════════════════════════════════════════════
    @Nested
    @DisplayName("methodName() — short description")
    class MethodNameTests {

        @Test
        @DisplayName("Scenario: [input] → expected (note)")
        void testScenarioName() {
            assertEquals(expected, instance.methodName(input));
        }
    }
}
```

**Key rules:**
- One `@Nested` class per public method under test.
- Banner comment (`═══...`) above each `@Nested` block with method name and complexity.
- `@DisplayName` on every `@Test` — format: `"Scenario: [input] → output (optional note)"`.
- Use only `assertEquals` from JUnit 5 — no third-party assertion libraries.
- Inline inputs directly in the assertion call (e.g. `new int[]{...}`).
- Test method names follow `testCamelCaseScenario()` convention.

**Standard scenarios to cover (adapt per problem):**
1. LeetCode provided examples — label explicitly: `"LeetCode Example 1: ..."`
2. Single-element / minimum-size input
3. All-same / uniform values → typically zero/no result
4. Strictly increasing / strictly decreasing sequences
5. Edge case that resets running state (e.g. new minimum mid-array)
6. No valid answer (return 0 or -1)
7. Max constraint boundary values

---

## Go Module

### Paths
| Purpose     | Path                |
|-------------|---------------------|
| Module root | `go/`               |
| Module name | `shashwatksingh/go` |
| Go version  | `1.26.1`            |
| DSA sources | `go/dsa/`           |

**Test convention:** `<file>_test.go` in the same package, using the standard `testing` library. No test files exist yet.

---

## General Notes
- `mvn` is **not** on the shell PATH in this environment — use the VS Code / Cursor IDE test runner.
- No `.mvn/wrapper/` directory exists — do not rely on any `mvnw` script.
- `java/target/surefire-reports/` exists, confirming prior test runs via IDE.

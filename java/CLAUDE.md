# Java module

## Build & Test
- `mvn` is **not** on the shell PATH here — use the VS Code / Cursor IDE test runner, or `/opt/homebrew/bin/mvn` directly.
- No `.mvn/wrapper/` exists — do not rely on any `mvnw` script.
- Single class: `cd java && /opt/homebrew/bin/mvn test -Dtest=<TestClassName>`

## Test Conventions (JUnit 5)

Test files are `<SourceClassName>Test.java` under `java/src/test/java/org/shashwatksingh/dsa/`, same package as the source.

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
- JUnit Jupiter is the only test library — use only `org.junit.jupiter.api.Assertions.assertEquals` (no Mockito, AssertJ, or other assertions).
- One `@Nested` class per public method under test.
- Banner comment (`═══...`) above each `@Nested` block with method name and complexity.
- `@DisplayName` on every `@Test` — format: `"Scenario: [input] → output (optional note)"`.
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

---
name: add-test-cases
description: Write JUnit 5 (Java) or `testing` (Go) test cases for a DSA problem file using the correct expected values, without diagnosing or fixing any bugs in the implementation — let the tests surface breakage for the user to debug themselves. Use when the user asks to add/write test cases for a file and wants to find problems on their own.
---

# Add Test Cases (find breakage, don't diagnose it)

Triggered by prompts like: "read the question and add the test cases. let me figure out
the problems with the code. try to figure out where the code can break."

## What to do

1. **Locate the target file and pick the language.** Default to the file currently open in
   the IDE unless the user names a different one. A `.java` file under `java/` follows the
   Java steps below; a `.go` file under `go/dsa/` follows the **Go variant** section at the
   bottom (steps 3, 4, 6 and 7 apply to both languages). Confirm with the user only if it's genuinely ambiguous
   (e.g. multiple files opened recently, no clear referent).
2. **Check for an existing test file first** (Java: `java/src/test/java/org/shashwatksingh/dsa/<Name>Test.java`;
   Go: `go/dsa/<file>_test.go` next to the source file).
   If one exists, read it before adding anything — don't duplicate coverage.
3. **Read the source file fully**, including the LeetCode problem statement/constraints in
   the header comment (if present). If the header comment is missing or incomplete, recall
   or look up the actual problem statement/constraints for that LeetCode number so expected
   values are grounded in the real problem, not just what the code happens to do.
4. **Hand-trace every public method against the real problem semantics** before writing any
   test. Work out what the *correct* answer is for each case you plan to write — independent
   of what the current implementation actually returns. Do this analysis silently; it's for
   your own confidence in the expected values, not something to narrate to the user.
5. **Write the test file** following this repo's test conventions (see `CLAUDE.md`). For Go,
   use the **Go variant** below instead of the Java bullets. Java:
   - One `@Nested` class per public method under test, with a banner comment above it.
   - `@DisplayName` on the class and every `@Test`, format: `"Scenario: [input] → output (note)"`.
   - Only `org.junit.jupiter.api.Assertions.assertEquals` — no other assertion methods,
     no third-party libraries. For array/collection returns, compare `Arrays.toString(...)`
     of expected vs. actual rather than reaching for `assertArrayEquals`.
   - Cover the standard scenario set, adapted to the problem: LeetCode's own examples,
     minimum-size input, all-same/uniform values, structurally distinct edge cases
     (asymmetric, reversed, crossing a boundary), a case with no valid answer (if
     applicable), and a max-constraint-boundary case sized per the problem's stated limits.
   - Every assertion must encode the **true, correct** expected value per the problem
     statement — never the value the buggy code currently happens to produce.
6. **Do not add `assertThrows` (Java) or panic/`recover` checks (Go) speculatively, and do not label tests `BUG: ...`.** Even if
   your hand-trace tells you a case will crash or return the wrong value, write it as a
   plain equality check on the correct value (`assertEquals` in Java, an `if got != want`
   comparison in Go). The point of this skill is for the failure
   (an assertion mismatch, or an unexpected exception JUnit reports on its own) to be the
   user's first signal — don't pre-empt or narrate the diagnosis in the test itself or in
   your summary.
7. **Do not edit the source file.** No fixes, no refactors, not even ones you're confident
   about — this skill is explicitly test-writing only, in service of letting the user debug
   independently.

## Go variant (files in `go/dsa/`)

- **File:** `<sourceFile>_test.go` in the same directory and `package dsa`, matching the source
  file's name (e.g. `pivotIndex.go` → `pivotIndex_test.go`). Tests can call unexported functions.
- **Library:** the standard `testing` package only (plus `slices` / `reflect.DeepEqual` for
  comparing slices). No testify or other third-party libraries.
- **Structure:** one `Test<FunctionName>` function per function under test, table-driven:
  a `[]struct{ name string; <inputs>; want <type> }` run through `t.Run(tt.name, ...)`.
  If the source file has several approaches (e.g. `twoSum` and `twoSumLoop`), write one
  `Test` function per approach with the same table.
- **Names:** `name` uses the same format as Java's `@DisplayName`:
  `"Scenario: [input] → output (note)"`; label LeetCode's own cases `"LeetCode Example N: ..."`.
- **Shared helpers** live in `go/dsa/testutil_test.go` (`repeatInts(n, v)`, `show(nums)` for
  abbreviating large slices in failure messages). Linked-list / tree helpers live in
  `go/dsa/helpers` (`helpers.NewList`, `helpers.ToSlice`, `helpers.NewTree`, `helpers.Null`);
  import path `shashwatksingh/go/dsa/helpers`.
- **Scenario set:** same as Java — LeetCode examples, minimum size (incl. empty input if the
  constraints allow it), uniform values, structurally distinct edge cases, no-valid-answer,
  and a max-constraint-boundary case sized per the stated limits.
- **Mutation:** if the function mutates its input, pass `slices.Clone(tt.nums)` so the table
  entry stays readable in failure messages.
- **Order-insensitive answers:** sort a clone of the result before comparing.
- **Running (optional):** `cd go && go test ./dsa/...` (`go` is at `/usr/local/go/bin/go`). Only run
  it if the user asks, since in this skill a failing test is meant to be the user's first signal.

## What to say back to the user

Keep it short: name the file you created, how many tests per method, and what scenario
categories they cover. Do **not** reveal which tests you expect to fail or why — that's the
part the user asked to figure out themselves. If asked directly "did you find any bugs?"
it's fine to answer honestly, but don't volunteer it unprompted.

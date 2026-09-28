---
name: add-test-cases
description: Write JUnit 5 test cases for a DSA problem file using the correct expected values, without diagnosing or fixing any bugs in the implementation — let the tests surface breakage for the user to debug themselves. Use when the user asks to add/write test cases for a file and wants to find problems on their own.
---

# Add Test Cases (find breakage, don't diagnose it)

Triggered by prompts like: "read the question and add the test cases. let me figure out
the problems with the code. try to figure out where the code can break."

## What to do

1. **Locate the target file.** Default to the file currently open in the IDE unless the
   user names a different one. Confirm with the user only if it's genuinely ambiguous
   (e.g. multiple files opened recently, no clear referent).
2. **Check for an existing test file first** (`java/src/test/java/org/shashwatksingh/dsa/<Name>Test.java`).
   If one exists, read it before adding anything — don't duplicate coverage.
3. **Read the source file fully**, including the LeetCode problem statement/constraints in
   the header comment (if present). If the header comment is missing or incomplete, recall
   or look up the actual problem statement/constraints for that LeetCode number so expected
   values are grounded in the real problem, not just what the code happens to do.
4. **Hand-trace every public method against the real problem semantics** before writing any
   test. Work out what the *correct* answer is for each case you plan to write — independent
   of what the current implementation actually returns. Do this analysis silently; it's for
   your own confidence in the expected values, not something to narrate to the user.
5. **Write the test file** following this repo's Java test conventions (see `CLAUDE.md`):
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
6. **Do not add `assertThrows` speculatively, and do not label tests `BUG: ...`.** Even if
   your hand-trace tells you a case will crash or return the wrong value, write it as a
   plain `assertEquals(correctValue, actual)`. The point of this skill is for the failure
   (an assertion mismatch, or an unexpected exception JUnit reports on its own) to be the
   user's first signal — don't pre-empt or narrate the diagnosis in the test itself or in
   your summary.
7. **Do not edit the source file.** No fixes, no refactors, not even ones you're confident
   about — this skill is explicitly test-writing only, in service of letting the user debug
   independently.

## What to say back to the user

Keep it short: name the file you created, how many tests per method, and what scenario
categories they cover. Do **not** reveal which tests you expect to fail or why — that's the
part the user asked to figure out themselves. If asked directly "did you find any bugs?"
it's fine to answer honestly, but don't volunteer it unprompted.

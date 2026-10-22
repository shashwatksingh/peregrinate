# Go module

Module name `shashwatksingh/go`; `go` is at `/usr/local/go/bin/go`.

## Layout & conventions
- One problem per file in `go/dsa/`, named in lowerCamelCase after the problem
  (`pivotIndex.go`), with the LeetCode statement in a leading `/* ... */` comment and a
  matching `pivotIndex_test.go`.
- Every file is `package dsa` (a library package — no `main`). Practise by writing tests,
  not `main()` functions. `go/helloworld.go` is the only `package main` file.
- Export (`PivotIndex`) or keep unexported (`twoSum`) as you like; tests live in the same
  package, so both are reachable.
- Linked-list / tree problems use `shashwatksingh/go/dsa/helpers`:
  `helpers.NewList(1,2,3)`, `helpers.ToSlice(head)`, `helpers.NewTree(1, helpers.Null, 2)`.

## Running tests
- `go test ./dsa -run TestPivotIndex` runs one function.
- `go test ./dsa -run 'TestTwoSum/Example_1'` runs one table case (spaces become underscores).

## Test Conventions
- Standard `testing` package only (`slices.Equal` / `reflect.DeepEqual` for slices).
- One `Test<FunctionName>` per function under test (one per approach if a file has several),
  table-driven: `[]struct{ name string; <inputs>; want <type> }` run with `t.Run(tt.name, ...)`.
- `name` format mirrors the Java `@DisplayName`: `"Scenario: [input] → output (note)"`;
  label LeetCode's cases `"LeetCode Example N: ..."`.
- Standard scenarios are the same as Java (examples, minimum size, uniform values,
  edge cases, no valid answer, max constraint boundary).
- Use `repeatInts` / `show` from `go/dsa/testutil_test.go` for large inputs and failure messages.

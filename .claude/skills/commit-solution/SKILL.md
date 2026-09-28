---
name: commit-solution
description: Commit a DSA solution file (and its matching test file, if one exists) to git using this repo's commit message convention "{platform} - {short question description}". Use when the user asks to add/commit/push a solution (and its tests) to GitHub.
---

# Commit Solution (repo commit-message convention)

Triggered by prompts like: "add this and the test file to the github", "commit this
solution", "push this problem up".

## What to do

1. **Identify the target file set.** Default to the source file currently open in the IDE
   (or the one most recently discussed/created in the conversation) unless the user names a
   different one. Look for its matching test file at
   `java/src/test/java/org/shashwatksingh/dsa/<Name>Test.java` (or the equivalent Go
   `<file>_test.go`) and include it automatically if it exists — don't ask, just check and
   add it.
2. **Determine `{code_platform}`.** Read the source file's header comment for a problem
   number/title. This repo is LeetCode-heavy, so default to `leetcode` when the header
   matches that style (a numbered problem + title, e.g. `1658. Minimum Operations to Reduce
   X to Zero`). Only ask the user if the file gives no clue which platform the problem is
   from.
3. **Derive `{short question description}`.** Lowercase the problem's title from the header
   comment, with no trailing punctuation. If the file implements a specific
   variant/technique that the plain title doesn't convey (e.g. a two-pointer approach
   restricted to prefix/suffix removal), append a short clarifying phrase describing that
   nuance — see the worked example below. Keep the whole message on one line, all lowercase.
   - Worked example: title "1658. Minimum Operations to Reduce X to Zero", solution removes
     only prefix/suffix ("edge") elements → `leetcode - minimum operations to reduce x to
     zero using edge elements only`.
4. **Stage only the identified file(s).** Never `git add -A` or `git add .` — this repo
   routinely has unrelated in-progress files (other WIP solutions, scratch files) sitting in
   the working tree. Add the source file and its test file by exact path only.
5. **Verify staging before committing.** Run `git status` after staging and confirm only the
   intended file(s) are listed under "Changes to be committed" — if anything else shows up,
   stop and ask before proceeding.
6. **Commit** with the message `{code_platform} - {short question description}`, passed via
   a heredoc, ending with this session's standard commit attribution line.
7. **Push only if the user's request implies it** (e.g. says "to GitHub", "push", "to the
   remote"). If the current branch has no upstream tracking branch but a same-named branch
   exists on `origin`, use `git push -u origin <branch>` to set up tracking. If the request
   was just "commit this", stop after the commit — don't push unprompted.

## What to say back to the user

Keep it short: the commit message used, which file(s) were included, and whether it was
pushed. No need to restate the full diff or file contents.

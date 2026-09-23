package org.shashwatksingh.dsa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GroupAnagrams Tests")
class GroupAnagramsTest {

    private GroupAnagrams groupAnagrams;

    @BeforeEach
    void setUp() {
        groupAnagrams = new GroupAnagrams();
    }

    // ── Helper: compare two List<List<String>> ignoring inner and outer order ───
    // Sorts each inner group alphabetically, then sorts the outer list by each
    // group's toString() so comparison is fully order-independent.
    private void assertGroupsMatch(List<List<String>> expected, List<List<String>> actual) {
        assertEquals(expected.size(), actual.size(), "Number of anagram groups should match");
        List<List<String>> sortedExpected = expected.stream()
                .map(g -> g.stream().sorted().collect(Collectors.toList()))
                .sorted(Comparator.comparing(List::toString))
                .collect(Collectors.toList());
        List<List<String>> sortedActual = actual.stream()
                .map(g -> g.stream().sorted().collect(Collectors.toList()))
                .sorted(Comparator.comparing(List::toString))
                .collect(Collectors.toList());
        assertEquals(sortedExpected, sortedActual, "Anagram groups should match (order-independent)");
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // bruteForce — O(n²): sorts each string inline, uses a seen[] boolean array
    // ─────────────────────────────────────────────────────────────────────────────
    @Nested
    @DisplayName("bruteForce (O(n²) inline sort + seen[])")
    class BruteForceTests {

        @Test
        @DisplayName("Example 1: [eat,tea,tan,ate,nat,bat] → [[bat],[nat,tan],[ate,eat,tea]]")
        void testExample1() {
            List<List<String>> result = groupAnagrams.bruteForce(
                    new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
            assertGroupsMatch(
                    List.of(List.of("bat"), List.of("nat", "tan"), List.of("ate", "eat", "tea")),
                    result);
        }

        @Test
        @DisplayName("Example 2: [\"\"] → [[\"\"]] (single empty string)")
        void testExample2SingleEmptyString() {
            List<List<String>> result = groupAnagrams.bruteForce(new String[]{""});
            assertGroupsMatch(List.of(List.of("")), result);
        }

        @Test
        @DisplayName("Example 3: [\"a\"] → [[\"a\"]] (single element)")
        void testExample3SingleElement() {
            List<List<String>> result = groupAnagrams.bruteForce(new String[]{"a"});
            assertGroupsMatch(List.of(List.of("a")), result);
        }

        @Test
        @DisplayName("Empty input array → []")
        void testEmptyInput() {
            List<List<String>> result = groupAnagrams.bruteForce(new String[]{});
            assertTrue(result.isEmpty(), "Empty input should return an empty list");
        }

        @Test
        @DisplayName("All words are anagrams of each other → single group: [abc,bca,cab]")
        void testAllAnagrams() {
            List<List<String>> result = groupAnagrams.bruteForce(new String[]{"abc", "bca", "cab"});
            assertGroupsMatch(List.of(List.of("abc", "bca", "cab")), result);
        }

        @Test
        @DisplayName("No two words are anagrams → each word in its own group: [abc,def,ghi]")
        void testNoAnagrams() {
            List<List<String>> result = groupAnagrams.bruteForce(new String[]{"abc", "def", "ghi"});
            assertGroupsMatch(
                    List.of(List.of("abc"), List.of("def"), List.of("ghi")),
                    result);
        }

        @Test
        @DisplayName("Duplicate strings grouped together: [ab,ab] → [[ab,ab]]")
        void testDuplicateStrings() {
            List<List<String>> result = groupAnagrams.bruteForce(new String[]{"ab", "ab"});
            assertGroupsMatch(List.of(List.of("ab", "ab")), result);
        }

        @Test
        @DisplayName("Strings with repeated chars: [aab,baa,aba,xyz] → [[aab,baa,aba],[xyz]]")
        void testRepeatedChars() {
            List<List<String>> result = groupAnagrams.bruteForce(new String[]{"aab", "baa", "aba", "xyz"});
            assertGroupsMatch(
                    List.of(List.of("aab", "baa", "aba"), List.of("xyz")),
                    result);
        }

        @Test
        @DisplayName("Two-element anagram pair: [ab,ba] → [[ab,ba]]")
        void testTwoElementAnagramPair() {
            List<List<String>> result = groupAnagrams.bruteForce(new String[]{"ab", "ba"});
            assertGroupsMatch(List.of(List.of("ab", "ba")), result);
        }

        @Test
        @DisplayName("Multiple groups: [cat,act,dog,god,tac] → [[cat,act,tac],[dog,god]]")
        void testMultipleGroups() {
            List<List<String>> result = groupAnagrams.bruteForce(
                    new String[]{"cat", "act", "dog", "god", "tac"});
            assertGroupsMatch(
                    List.of(List.of("cat", "act", "tac"), List.of("dog", "god")),
                    result);
        }
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // bruteForceOptimized — pre-computes sorted strings but contains a bug:
    //   inner loop adds strs[i] for every matching j instead of strs[j],
    //   so anagram partners are never stored — only the pivot is repeated.
    // ─────────────────────────────────────────────────────────────────────────────
    @Nested
    @DisplayName("bruteForceOptimized (pre-sorted strings — contains grouping BUG)")
    class BruteForceOptimizedTests {

        @Test
        @DisplayName("Empty input array → [] (passes — early-return path)")
        void testEmptyInput() {
            List<List<String>> result = groupAnagrams.bruteForceOptimized(new String[]{});
            assertTrue(result.isEmpty(), "Empty input should return an empty list");
        }

        @Test
        @DisplayName("[\"\"] → [[\"\"]] (passes — single-element path)")
        void testSingleEmptyString() {
            List<List<String>> result = groupAnagrams.bruteForceOptimized(new String[]{""});
            assertGroupsMatch(List.of(List.of("")), result);
        }

        @Test
        @DisplayName("[\"a\"] → [[\"a\"]] (passes — single-element path)")
        void testSingleElement() {
            List<List<String>> result = groupAnagrams.bruteForceOptimized(new String[]{"a"});
            assertGroupsMatch(List.of(List.of("a")), result);
        }

        @Test
        @DisplayName("No anagrams: [abc,def] passes — no cross-group match")
        void testNoAnagramsPassesBecauseNoCrossMatch() {
            List<List<String>> result = groupAnagrams.bruteForceOptimized(new String[]{"abc", "def"});
            assertGroupsMatch(List.of(List.of("abc"), List.of("def")), result);
        }

        @Test
        @DisplayName("BUG: [eat,tea] — strs[i]('eat') added for every j match instead of strs[j]('tea')")
        void testBugAnagramPartnerNotStored() {
            // Bug trace: i=0 ("eat"), j=1 ("tea") matches sorted key "aet".
            // Code does: list.add(strs[i]) → adds "eat" again instead of strs[j]="tea".
            // Expected (correct): [["eat","tea"]]  |  Actual (buggy): [["eat","eat"]]
            List<List<String>> result = groupAnagrams.bruteForceOptimized(new String[]{"eat", "tea"});
            assertNotEquals("tea", result.get(0).get(1),
                    "BUG: adds strs[i] instead of strs[j]; 'tea' is never stored — 'eat' appears twice");
        }

        @Test
        @DisplayName("BUG: Example 1 [eat,tea,tan,ate,nat,bat] — group contents are wrong")
        void testBugExample1GroupContentsAreWrong() {
            // i=0 ("eat"): j=0,1,3 match → adds "eat" 3x | i=2 ("tan"): j=2,4 → "tan" 2x | i=5 → ["bat"]
            List<List<String>> result = groupAnagrams.bruteForceOptimized(
                    new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
            assertEquals(3, result.size(), "Group count is coincidentally correct despite the bug");
            boolean containsAnyPartner = result.stream()
                    .anyMatch(g -> g.contains("tea") || g.contains("ate") || g.contains("nat"));
            assertFalse(containsAnyPartner,
                    "BUG: 'tea', 'ate', 'nat' are never stored — only the pivot strs[i] is repeated");
        }
    }

    // ─────────────────────────────────────────────────────────────────────────────
    // anagramsUsingHashMap — O(n·k log k): correct, optimal HashMap solution
    // ─────────────────────────────────────────────────────────────────────────────
    @Nested
    @DisplayName("anagramsUsingHashMap (HashMap — correct O(n·k log k) solution)")
    class AnagramsUsingHashMapTests {

        @Test
        @DisplayName("Example 1: [eat,tea,tan,ate,nat,bat] → [[bat],[nat,tan],[ate,eat,tea]]")
        void testExample1() {
            List<List<String>> result = groupAnagrams.anagramsUsingHashMap(
                    new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
            assertGroupsMatch(
                    List.of(List.of("bat"), List.of("nat", "tan"), List.of("ate", "eat", "tea")),
                    result);
        }

        @Test
        @DisplayName("Example 2: [\"\"] → [[\"\"]] (single empty string)")
        void testExample2SingleEmptyString() {
            List<List<String>> result = groupAnagrams.anagramsUsingHashMap(new String[]{""});
            assertGroupsMatch(List.of(List.of("")), result);
        }

        @Test
        @DisplayName("Example 3: [\"a\"] → [[\"a\"]] (single element)")
        void testExample3SingleElement() {
            List<List<String>> result = groupAnagrams.anagramsUsingHashMap(new String[]{"a"});
            assertGroupsMatch(List.of(List.of("a")), result);
        }

        @Test
        @DisplayName("Empty input array → []")
        void testEmptyInput() {
            List<List<String>> result = groupAnagrams.anagramsUsingHashMap(new String[]{});
            assertTrue(result.isEmpty(), "Empty input should return an empty list");
        }

        @Test
        @DisplayName("All words are anagrams → single group: [abc,bca,cab]")
        void testAllAnagrams() {
            List<List<String>> result = groupAnagrams.anagramsUsingHashMap(new String[]{"abc", "bca", "cab"});
            assertGroupsMatch(List.of(List.of("abc", "bca", "cab")), result);
        }

        @Test
        @DisplayName("No anagrams → each word in its own group: [abc,def,ghi]")
        void testNoAnagrams() {
            List<List<String>> result = groupAnagrams.anagramsUsingHashMap(new String[]{"abc", "def", "ghi"});
            assertGroupsMatch(List.of(List.of("abc"), List.of("def"), List.of("ghi")), result);
        }

        @Test
        @DisplayName("Duplicate strings grouped together: [ab,ab] → [[ab,ab]]")
        void testDuplicateStrings() {
            List<List<String>> result = groupAnagrams.anagramsUsingHashMap(new String[]{"ab", "ab"});
            assertGroupsMatch(List.of(List.of("ab", "ab")), result);
        }

        @Test
        @DisplayName("Strings with repeated chars: [aab,baa,aba,xyz] → [[aab,baa,aba],[xyz]]")
        void testRepeatedChars() {
            List<List<String>> result = groupAnagrams.anagramsUsingHashMap(
                    new String[]{"aab", "baa", "aba", "xyz"});
            assertGroupsMatch(List.of(List.of("aab", "baa", "aba"), List.of("xyz")), result);
        }

        @Test
        @DisplayName("Two-element anagram pair: [ab,ba] → [[ab,ba]]")
        void testTwoElementAnagramPair() {
            List<List<String>> result = groupAnagrams.anagramsUsingHashMap(new String[]{"ab", "ba"});
            assertGroupsMatch(List.of(List.of("ab", "ba")), result);
        }

        @Test
        @DisplayName("Multiple groups: [cat,act,dog,god,tac] → [[cat,act,tac],[dog,god]]")
        void testMultipleGroups() {
            List<List<String>> result = groupAnagrams.anagramsUsingHashMap(
                    new String[]{"cat", "act", "dog", "god", "tac"});
            assertGroupsMatch(
                    List.of(List.of("cat", "act", "tac"), List.of("dog", "god")),
                    result);
        }

        @Test
        @DisplayName("Larger input: 5 distinct anagram groups correctly separated")
        void testLargerInputGroupCount() {
            // Groups: [abc,bca,cab], [xyz,zyx,yxz], [ab,ba], [a], [b] → 5 groups
            List<List<String>> result = groupAnagrams.anagramsUsingHashMap(
                    new String[]{"abc", "bca", "cab", "xyz", "zyx", "yxz", "ab", "ba", "a", "b"});
            assertEquals(5, result.size(), "Should produce exactly 5 distinct anagram groups");
        }
    }
}

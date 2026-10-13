package org.shashwatksingh.dsa.sortingalgos;

import org.junit.jupiter.api.DisplayName;

@DisplayName("Bubble Sort Tests")
class BubbleSortTest extends SortingAlgorithmTestBase {

    @Override
    protected void sort(int[] arr) {
        new BubbleSort().sort(arr);
    }
}

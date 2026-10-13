package org.shashwatksingh.dsa.sortingalgos;

import org.junit.jupiter.api.DisplayName;

@DisplayName("Selection Sort Tests")
class SelectionSortTest extends SortingAlgorithmTestBase {

    @Override
    protected void sort(int[] arr) {
        new SelectionSort().sort(arr);
    }
}

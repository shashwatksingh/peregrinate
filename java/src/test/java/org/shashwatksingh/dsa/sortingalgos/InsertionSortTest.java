package org.shashwatksingh.dsa.sortingalgos;

import org.junit.jupiter.api.DisplayName;

@DisplayName("Insertion Sort Tests")
class InsertionSortTest extends SortingAlgorithmTestBase {

    @Override
    protected void sort(int[] arr) {
        new InsertionSort().sort(arr);
    }
}

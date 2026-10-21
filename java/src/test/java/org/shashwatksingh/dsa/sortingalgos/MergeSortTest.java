package org.shashwatksingh.dsa.sortingalgos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Merge Sort Tests")
class MergeSortTest extends SortingAlgorithmTestBase {

    private MergeSort instance;

    @BeforeEach
    void setUp() {
        instance = new MergeSort();
    }

    @Override
    protected void sort(int[] arr) {
        instance.sort(arr);
    }
}

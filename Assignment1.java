/*
PRN: 123B1F021
NAME: Shreyash Surendra Desai
DATE: 07/07/2025
ASSIGNMENT 1:
PROBLEM STATEMENT:
Design and implement a sorting algorithm using Merge Sort to efficiently arrange customer orders based on their timestamps.
The solution should handle a large dataset (up to 1 million orders) with minimal computational overhead.
Additionally, analyze the time complexity and compare it with traditional sorting techniques.
*/

//SOLUTION:
class OrderRecord {
    String id;
    long orderTime;

    public OrderRecord(String id, long orderTime) {
        this.id = id;
        this.orderTime = orderTime;
    }
}

public class Assignment1 {

    // Recursive merge sort function
    public static void sortOrders(OrderRecord[] records, int start, int end) {
        if (start >= end) return; // Base case: single element is sorted

        int middle = start + (end - start) / 2; // Prevent overflow

        // Sort left half
        sortOrders(records, start, middle);

        // Sort right half
        sortOrders(records, middle + 1, end);

        // Merge sorted halves
        merge(records, start, middle, end);
    }

    // Merge two sorted subarrays into one
    private static void merge(OrderRecord[] arr, int left, int mid, int right) {
        int sizeLeft = mid - left + 1;
        int sizeRight = right - mid;

        OrderRecord[] leftArr = new OrderRecord[sizeLeft];
        OrderRecord[] rightArr = new OrderRecord[sizeRight];

        for (int i = 0; i < sizeLeft; i++) leftArr[i] = arr[left + i];
        for (int j = 0; j < sizeRight; j++) rightArr[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        // Merge elements in order
        while (i < sizeLeft && j < sizeRight) {
            if (leftArr[i].orderTime <= rightArr[j].orderTime)
                arr[k++] = leftArr[i++];
            else
                arr[k++] = rightArr[j++];
        }

        // Copy any remaining elements
        while (i < sizeLeft) arr[k++] = leftArr[i++];
        while (j < sizeRight) arr[k++] = rightArr[j++];
    }

    // Sample dataset
    public static OrderRecord[] sampleOrders() {
        return new OrderRecord[]{
                new OrderRecord("ORD01", 1700000100000L),
                new OrderRecord("ORD02", 1699999999000L),
                new OrderRecord("ORD03", 1700000200000L),
                new OrderRecord("ORD04", 1699999950000L),
                new OrderRecord("ORD05", 1700000300000L),
                new OrderRecord("ORD06", 1700000000000L),
                new OrderRecord("ORD07", 1699999900000L),
                new OrderRecord("ORD08", 1700000500000L),
                new OrderRecord("ORD09", 1699999850000L),
                new OrderRecord("ORD10", 1700000400000L)
        };
    }

    // Print orders to console
    public static void displayOrders(OrderRecord[] orders) {
        for (OrderRecord o : orders)
            System.out.println("Order ID: " + o.id + " | Timestamp: " + o.orderTime);
    }

    public static void main(String[] args) {
        OrderRecord[] orders = sampleOrders();

        System.out.println("===== CUSTOMER ORDERS BEFORE SORTING =====");
        displayOrders(orders);

        long startTime = System.nanoTime();
        sortOrders(orders, 0, orders.length - 1);
        long endTime = System.nanoTime();

        System.out.println("\n===== CUSTOMER ORDERS AFTER SORTING BY TIMESTAMP =====");
        displayOrders(orders);

        double elapsed = (endTime - startTime) / 1_000_000.0;
        System.out.printf("\nTime Taken: %.3f ms%n", elapsed);
    }
}

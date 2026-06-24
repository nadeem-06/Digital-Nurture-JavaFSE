public class EcommerceSearch {

    public static void main(String[] args) {

        // -- Create product inventory --
        Product[] products = {
            new Product(101, "Laptop",        55000.00),
            new Product(102, "Smartphone",    25000.00),
            new Product(103, "Headphones",     3500.00),
            new Product(104, "Keyboard",       2000.00),
            new Product(105, "Mouse",          1500.00),
            new Product(106, "Monitor",       18000.00),
            new Product(107, "Webcam",         4500.00),
            new Product(108, "USB Hub",        1200.00),
        };

        String searchTerm = "Keyboard";

        // ================================
        // TEST 1: LINEAR SEARCH
        // ================================
        System.out.println("=== Linear Search ===");
        System.out.println("Searching for: " + searchTerm);

        long startTime = System.nanoTime();
        Product linearResult = SearchUtil.linearSearch(products, searchTerm);
        long linearTime = System.nanoTime() - startTime;

        if (linearResult != null) {
            System.out.println("Found: " + linearResult);
        } else {
            System.out.println("Product not found.");
        }
        System.out.println("Time taken: " + linearTime + " ns\n");

        // ================================
        // TEST 2: BINARY SEARCH
        // Must sort first!
        // ================================
        System.out.println("=== Binary Search ===");
        System.out.println("Sorting products by name first...");
        SearchUtil.sortByName(products);

        System.out.println("Sorted order:");
        for (Product p : products) {
            System.out.println("  " + p.getProductName());
        }

        System.out.println("\nSearching for: " + searchTerm);

        startTime = System.nanoTime();
        Product binaryResult = SearchUtil.binarySearch(products, searchTerm);
        long binaryTime = System.nanoTime() - startTime;

        if (binaryResult != null) {
            System.out.println("Found: " + binaryResult);
        } else {
            System.out.println("Product not found.");
        }
        System.out.println("Time taken: " + binaryTime + " ns\n");

        // ================================
        // TEST 3: Search for something
        //         that doesn't exist
        // ================================
        System.out.println("=== Searching for non-existent product ===");
        Product notFound = SearchUtil.binarySearch(products, "Tablet");
        System.out.println("Search result for 'Tablet': " +
                           (notFound != null ? notFound : "Not found"));
    }
}
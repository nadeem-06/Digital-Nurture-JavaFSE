public class SearchUtil {

    public static Product linearSearch(Product[] products, String targetName) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductName()
                           .equalsIgnoreCase(targetName)) {
                return products[i]; 
            }
        }
        return null;
    }

    public static Product binarySearch(Product[] products, String targetName) {
        int left  = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int comparison = products[mid].getProductName()
                                          .compareToIgnoreCase(targetName);

            if (comparison == 0) {
                return products[mid]; 
            } else if (comparison < 0) {
                left = mid + 1;  
            } else {
                right = mid - 1; 
            }
        }
        return null; 
    }

    public static void sortByName(Product[] products) {
        int n = products.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (products[j].getProductName()
                               .compareToIgnoreCase(
                                products[j + 1].getProductName()) > 0) {
                    
                    Product temp   = products[j];
                    products[j]    = products[j + 1];
                    products[j + 1] = temp;
                }
            }
        }
    }
}
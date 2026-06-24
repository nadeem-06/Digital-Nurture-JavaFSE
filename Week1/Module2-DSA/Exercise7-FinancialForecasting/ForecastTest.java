public class ForecastTest {

    public static void main(String[] args) {

        System.out.println("=== Financial Forecasting Tool ===\n");

        double presentValue = 100000.00;
        double growthRate   = 0.08;

        System.out.println("Present Value : Rs." + presentValue);
        System.out.println("Growth Rate   : " + (growthRate * 100) + "% per year\n");

        int[] yearsList = {1, 3, 5, 10};

        System.out.printf("%-10s %-20s %-20s%n", "Years", "Recursive", "Iterative");
        for (int i = 0; i < 52; i++) System.out.print("-");
        System.out.println();

        for (int years : yearsList) {
            double recursive = FinancialForecast
                                   .calculateFutureValue(
                                       presentValue, growthRate, years);
            double iterative = FinancialForecast
                                   .calculateFutureValueIterative(
                                       presentValue, growthRate, years);

            System.out.printf("%-10d Rs.%-17.2f Rs.%-17.2f%n",
                              years, recursive, iterative);
        }

        System.out.println("\n=== Year-by-Year Breakdown (5 years) ===");
        double value = presentValue;
        for (int year = 1; year <= 5; year++) {
            value = value * (1 + growthRate);
            System.out.printf("After Year %d : Rs.%.2f%n", year, value);
        }

        System.out.println("\n=== Edge Cases ===");

        double zeroYears = FinancialForecast
                               .calculateFutureValue(presentValue, growthRate, 0);
        System.out.println("0 years  : Rs." + zeroYears);

        double zeroGrowth = FinancialForecast
                                .calculateFutureValue(presentValue, 0.0, 5);
        System.out.println("0% growth: Rs." + zeroGrowth);
    }
}
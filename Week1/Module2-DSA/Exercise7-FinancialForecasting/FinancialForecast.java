public class FinancialForecast {

    public static double calculateFutureValue(double presentValue,double growthRate,int years) {
        if (years == 0) {
            return presentValue;
        }

        double valueAfterOneYear = presentValue * (1 + growthRate);
        return calculateFutureValue(valueAfterOneYear, growthRate, years - 1);
    }

    public static double calculateFutureValueIterative(double presentValue,double growthRate,int years) {
        double value = presentValue;
        for (int i = 0; i < years; i++) {
            value = value * (1 + growthRate);
        }
        return value;
    }
}
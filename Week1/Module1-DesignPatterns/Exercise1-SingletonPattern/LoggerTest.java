public class LoggerTest {

    public static void main(String[] args) {

        System.out.println("--- Test: Singleton Logger ---\n");

        Logger logger1 = Logger.getInstance();
        logger1.log("Application started");
        logger1.log("User logged in");

        Logger logger2 = Logger.getInstance();
        logger2.log("Processing payment");

        System.out.println("\nlogger1 == logger2 ? " + (logger1 == logger2));

        System.out.println("Total logs via logger1: " + logger1.getLogCount());
        System.out.println("Total logs via logger2: " + logger2.getLogCount());
    }
}
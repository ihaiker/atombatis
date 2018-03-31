package la.renzhen.atombatis.datasource;

public class ContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

    private static String previous;

    public static void setCustomerType(String customerType) {
        contextHolder.set(customerType);
    }

    public static String getCustomerType() {
        return contextHolder.get();
    }

    public static void clearCustomerType() {
        previous = contextHolder.get();
        contextHolder.remove();
    }

    public static String getPreviousCustomerType() {
        return previous;
    }
}
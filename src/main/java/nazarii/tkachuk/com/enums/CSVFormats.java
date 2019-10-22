package nazarii.tkachuk.com.enums;

public enum CSVFormats {

    CUSTOMER("%-15s%-15s%-15s%-15s%n"),
    ORDER("%-15s%-15s%-15s%-15s%-15s%-15s%n"),
    PRODUCT("%-20s%-15s%-15s%-1500s%n");

    private String formatValue;

    CSVFormats(String formatValue) {
        this.formatValue = formatValue;
    }

    public String getFormatValue() {
        return formatValue;
    }
}

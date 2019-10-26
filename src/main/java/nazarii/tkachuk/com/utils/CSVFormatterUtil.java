package nazarii.tkachuk.com.utils;

import nazarii.tkachuk.com.entities.CSVSerializable;
import java.util.List;

public final class CSVFormatterUtil {

    private CSVFormatterUtil() {
        throw new RuntimeException();
    }

    public static String toCSVFormattedString(List<? extends CSVSerializable> list) {
        StringBuilder stringBuilder = new StringBuilder();

        for (CSVSerializable item : list) {
            stringBuilder.append(item.toCSVFormattedString());
        }

        return stringBuilder.toString();
    }

    public static String toCSVString(List<? extends CSVSerializable> list) {
        StringBuilder stringBuilder = new StringBuilder();

        list.forEach(item -> stringBuilder.append(item.toCSVString()));

        return stringBuilder.toString();
    }
}

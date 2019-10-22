package com.utils;

import com.entities.CSVSerializable;
import java.util.List;

public final class CSVFormatterUtil {

    private CSVFormatterUtil() {
        throw new RuntimeException();
    }

    public static String toCSVStringWithFormat(List<? extends CSVSerializable> list) {
        StringBuilder stringBuilder = new StringBuilder();

        for (CSVSerializable item : list) {
            stringBuilder.append(item.toCSVWithFormatString());
        }

        return stringBuilder.toString();
    }

    public static String toCSVStringNoFormat(List<? extends CSVSerializable> list) {
        StringBuilder stringBuilder = new StringBuilder();

        for (CSVSerializable item : list) {
            stringBuilder.append(item.toCSVFileString());
        }

        return stringBuilder.toString();
    }
}

package nazarii.tkachuk.com.utils;

import nazarii.tkachuk.com.entities.CSVSerializable;
import nazarii.tkachuk.com.mappers.CSVMapper;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class FileReaderUtil {

    private FileReaderUtil() {
        throw new UnsupportedOperationException();
    }

    public static <T extends CSVSerializable> List<T> readObjects(String filepath, CSVMapper<T> mapper) {

        List<String> strings = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader(filepath))) {
            while (scanner.hasNextLine()) {

                strings.add(StringUtil.stringTrim(scanner.nextLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//TYT!!!!!!!
        return convertToObjects(strings, mapper);
    }

    private static <T extends CSVSerializable> List<T> convertToObjects(List<String> data, CSVMapper<T> mapper) {
        List<T> list = new ArrayList<>();
        for (String objectData : data) {
            T t = mapper.mapFromCSV(objectData);
            list.add(t);
        }
        return list;
    }
//    public static String readStringFromFile(String filepath) {
//        List<String> strings = new ArrayList<>();
//        StringBuffer stringBuffer = new StringBuffer();
//        try (Scanner scanner = new Scanner(new FileReader(filepath))) {
//
//            while (scanner.hasNextLine()) {
//                strings.add(scanner.nextLine());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (String s : strings) {
//            stringBuffer.append(s + "\n");
//        }
//
//        return stringBuffer.toString();
//
//    }
    public static List<String> readStringFromFile(String filepath) {
        List<String> strings = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader(filepath))) {

            while (scanner.hasNextLine()) {
                strings.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }
}
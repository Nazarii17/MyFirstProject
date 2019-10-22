package com.utils;

import java.io.*;

public final class FileWriterUtil {

    private FileWriterUtil() {
        throw new UnsupportedOperationException();
    }

    public static String createFileIfNotExists(String filePath) {

        try {
            File fileName = new File(filePath);
            if (!fileName.exists()) {
                fileName.createNewFile();
                System.out.println("File " + filePath + " created");
            } else System.out.println("File " + filePath + " already existed");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error" + e);
        }
        return filePath;
    }
    //    public void writeTextToFile(String text) {
//
//        FileWriter fileWriter;
//        BufferedWriter bufferedWriter;
//        PrintWriter printWriter = null;
//        try {
//            fileWriter = new FileWriter(fileURLandName, true);
//            bufferedWriter = new BufferedWriter(fileWriter);
//            printWriter = new PrintWriter(bufferedWriter);
//            printWriter.println(text);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        printWriter.close();
//    }
//    public void writeTextToFile(FileType fileType, Object o, Object[] objects) {
//        FileWriter fileWriter;
//        BufferedWriter bufferedWriter;
//        PrintWriter printWriter = null;
//        try {
//
//            fileWriter = new FileWriter(fileType.fileLocation(fileType), true);
//            bufferedWriter = new BufferedWriter(fileWriter);
//            printWriter = new PrintWriter(bufferedWriter);
//            if (o instanceof Customer) {
//                printWriter.format(FormatterObjects.customerFormat(), objects);
//            } else if (o instanceof Product) {
//                printWriter.format(FormatterObjects.productFormat(), objects);
//            } else if (o instanceof Order) {
//                printWriter.format(FormatterObjects.orderFormat(), objects);
//            } else printWriter.println(o.toString());
////            Formatter fmtFile;
////            fmtFile = new Formatter(new FileOutputStream(fileType.fileLocation(fileType)));
////            fmtFile.format();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        printWriter.close();
//    }
//    public void writeTextToFile(String filePath, List<? extends CSVSerializable> objects, String format) {
//
//        try (PrintWriter writer = new PrintWriter(filePath)) {
//
//            writer.print(CSVFormatterUtil.toCSVWithFormatString(objects, format));
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//    public void writeTextToFile(String filePath, String content) {
//
//        createFileIfNotExists(filePath);
//
//        try (PrintWriter writer = new PrintWriter(filePath)) {
//
//            writer.print(content);//format
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
    public static void  writeTextToFile(String filePath, String content) {

        createFileIfNotExists(filePath);

        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        PrintWriter printWriter = null;
        try {
            fileWriter = new FileWriter(filePath, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
            printWriter.print(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
    }

    public static void  overwriteTextToFile(String filePath, String content) {

        createFileIfNotExists(filePath);

        FileWriter fileWriter;
        BufferedWriter bufferedWriter;
        PrintWriter printWriter = null;
        try {
            fileWriter = new FileWriter(filePath, false);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
            printWriter.print(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
    }
}
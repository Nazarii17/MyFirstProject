package nazarii.tkachuk.com.utils;

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

    public static void writeToFile(String filePath, String content) {

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
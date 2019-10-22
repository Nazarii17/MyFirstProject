package nazarii.tkachuk.com.services;

import nazarii.tkachuk.com.entities.EntityID;
import nazarii.tkachuk.com.entities.Nameble;
import nazarii.tkachuk.com.mappers.CSVMapper;
import nazarii.tkachuk.com.utils.FileReaderUtil;
import nazarii.tkachuk.com.utils.FileWriterUtil;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityIDService {

    public static String getIDFilePath(String filePath) {
        char[] chars = filePath.toCharArray();
        StringBuffer fileIDPath = new StringBuffer(filePath);
        return fileIDPath.insert(chars.length - 4, "ID").toString();
    }

    public static String findMaxID(List<EntityID> list) {
        ArrayList<Integer> IDList = new ArrayList<>();

        if (list.isEmpty()) {
            return "0" + "\n";
        } else for (EntityID id : list) {
            IDList.add(id.getId());
        }
        return Collections.max(IDList).toString() + "\n";
    }

    public static void writeMaxIDFromListToFile(String filePath, List<? extends EntityID> list) {
        findMaxID((List<EntityID>) list);
        FileWriterUtil.overwriteTextToFile(filePath, findMaxID((List<EntityID>) list));
    }

    public static <T extends EntityID> void createFileWithMaxID(String filePath, CSVMapper<T> csvMapper) {

        List<T> list = FileReaderUtil.readObjects(filePath, csvMapper);

        writeMaxIDFromListToFile(getIDFilePath(filePath), list);
    }

    public static int generateIDFromFile(String filePath) {
        int id = Integer.parseInt(FileReaderUtil.readStringFromFile(filePath).trim());
        return id + 1;
    }

    public static <T extends EntityID> Boolean isIDExist(String filePath, Integer id, CSVMapper csvMapper) {
        List<T> IDList = FileReaderUtil.readObjects(filePath, csvMapper);
        for (T t : IDList) {
            if (t.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPriceNotZero(BigDecimal money) {
        //Validate if money not 0
        if (money.compareTo(BigDecimal.ZERO) == 0) {
            return true;
        } else return false;
//        createFileWithMaxID();
//        money.compareTo(BigDecimal.ZERO) == 0 // true
//        new BigDecimal("0.00").compareTo(BigDecimal.ZERO) == 0 // true

    }

    public static <T extends Nameble> boolean isNameExist(String filePath, String name, CSVMapper<T> csvMapper) {

        List<T> list = FileReaderUtil.readObjects(filePath, csvMapper);

        for (T t : list) {
            if (t.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public static Boolean isFileExist(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        }
        return false;
    }
}




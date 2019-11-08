package nazarii.tkachuk.com.services;

import nazarii.tkachuk.com.entities.EntityID;
import nazarii.tkachuk.com.entities.Order;
import nazarii.tkachuk.com.mappers.CSVMapper;
import nazarii.tkachuk.com.utils.FileReaderUtil;
import nazarii.tkachuk.com.utils.FileWriterUtil;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class EntityIDService {

    private String entityFilePath;
    private String entityIdFilePath;

    public EntityIDService() {
    }

    public EntityIDService(String entityFilePath) {
        this.entityFilePath = entityFilePath;
        this.entityIdFilePath = getIDFilePath(entityFilePath);
    }

    public String getIDFilePath(String filePath) {
        return filePath.substring(0, filePath.length() - 4) + "ID.csv";
    }

    private int generateId(String filePath) {
        int id = Integer.parseInt(FileReaderUtil.readStringFromFile(filePath).get(0).trim());
        final Integer nextId = ++id;
        FileWriterUtil.overwriteTextToFile(filePath, nextId.toString());
        return nextId;
    }

    public int generateId() {
        return generateId(entityIdFilePath);
    }

    public <T extends EntityID> Boolean isIDExist(String filePath, Integer id, CSVMapper csvMapper) {
        List<T> IDList = FileReaderUtil.readObjects(filePath, csvMapper);
        return isIDExist(IDList, id);
    }

    public <T extends EntityID> Boolean isIDExist(List<T> entities, Integer id) {
        for (T t : entities) {
            if (t.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPriceNotZero(BigDecimal money) {
        //Validate if money not 0
        if (money.compareTo(BigDecimal.ZERO) == 0) {
            return true;
        } else return false;
    }

    public LocalDate buildDate(Integer year, Integer month, Integer day) {

        if (year < 1945 || year > LocalDate.now().getYear()) {
            throw new RuntimeException("The year \"" + year + "\" is invalid!!!");
        }
        if (month < 0 || month > 12) {
            throw new RuntimeException("The month \"" + month + "\" is invalid!!!");
        }
        if (day < 0 || day > 31) {
            throw new RuntimeException("The day \"" + day + "\" is invalid!!!");
        }
        return LocalDate.of(year, month, day);
    }

    public Boolean isFileExist(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    public void prepareInitialFiles(List<String> filePaths){

        for (String filePath: filePaths ){
            if(!isFileExist(filePath)){
                FileWriterUtil.createFileIfNotExists(filePath);
//                throw new RuntimeException("File \""+ filePath + "\" not found!!!");
            }
            String idFilePath = getIDFilePath(filePath);
            if(!isFileExist(idFilePath)){
                FileWriterUtil.createFileIfNotExists(idFilePath);
            }
        }
    }
}




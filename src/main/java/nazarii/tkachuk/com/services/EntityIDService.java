package nazarii.tkachuk.com.services;

import nazarii.tkachuk.com.entities.EntityID;
import nazarii.tkachuk.com.entities.Nameble;
import nazarii.tkachuk.com.entities.Order;
import nazarii.tkachuk.com.mappers.CSVMapper;
import nazarii.tkachuk.com.utils.FileReaderUtil;
import nazarii.tkachuk.com.utils.FileWriterUtil;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public <T extends Nameble> boolean isNameExist(String filePath, String name, CSVMapper<T> csvMapper) {

        List<T> list = FileReaderUtil.readObjects(filePath, csvMapper);

        for (T t : list) {
            if (t.getName().equals(name)) {
                return true;
            }
        }
        return false;
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

    public void prepareInitialFiles(String customerFilePath, String productFilePath, String orderFilePath){

        List<String> filePaths = new ArrayList<>();
        filePaths.add(customerFilePath);
        filePaths.add(productFilePath);
        filePaths.add(orderFilePath);

        for (String filePath: filePaths ){
            if(!isFileExist(filePath)){
                FileWriterUtil.createFileIfNotExists(filePath);
//                throw new RuntimeException("File \""+ filePath + "\" not found!!!");
            }
        }
    }

    public <T extends Order> Boolean isDateExist(List<T> entities, Integer year, Integer month, Integer day){
        LocalDate checkedDate = buildDate(year,month,day);
        for (T t: entities){
            if (t.getOrderDate().equals(checkedDate)){
                return true;
            }
        }
        return false;
    }
}




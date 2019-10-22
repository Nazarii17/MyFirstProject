package nazarii.tkachuk.com.services;

import nazarii.tkachuk.com.entities.Product;
import nazarii.tkachuk.com.mappers.ProductMapper;
import nazarii.tkachuk.com.utils.CSVFormatterUtil;
import nazarii.tkachuk.com.utils.FileReaderUtil;
import nazarii.tkachuk.com.utils.FileWriterUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ProductService {

    public static Product getProductById(String filePath, int id) {
        List<Product> productList = FileReaderUtil.readObjects(filePath, new ProductMapper());
        Product correctOne = null;
        for (Product product : productList) {
            if (product.getId() == id) {
                correctOne = product;
            }
        }
        return correctOne;
    }

    public static int getIndexById(List<Product> list, int id) {

        Integer correctOne = null;
        for (Product product : list) {
            if (product.getId() == id) {
                correctOne = list.indexOf(product);

            }
        }
        return correctOne;
    }

    public static Product getProductByName(String filePath, String name) {
        List<Product> customerList = FileReaderUtil.readObjects(filePath, new ProductMapper());
        Product correctOne = null;
        for (Product product : customerList) {
            if (product.getName().equals(name)) {
                correctOne = product;
            }
        }
        return correctOne;
    }

    public static Product createNewProduct(String filePath, String name, Double price, String info) {

        if (!EntityIDService.isFileExist(filePath)) {
            FileWriterUtil.createFileIfNotExists(filePath);
        }
        if (EntityIDService.isPriceNotZero(new BigDecimal(price))) {
            throw new RuntimeException("Price can't be zero!!!");
        }
        if (EntityIDService.isNameExist(filePath, name, new ProductMapper())) {
            throw new RuntimeException("The product \"" + name.toUpperCase() + "\" is exist!!!");
        }

        EntityIDService.createFileWithMaxID(filePath, new ProductMapper());

        return new Product(
                EntityIDService.generateIDFromFile(EntityIDService.getIDFilePath(filePath)),
                name,
                new BigDecimal(price).setScale(2, RoundingMode.HALF_EVEN),
                info);
    }

    public static void deleteProductByID(String filePath, int id) {
        List<Product> productList = FileReaderUtil.readObjects(filePath, new ProductMapper());

        productList.remove(getIndexById(productList, id));

        FileWriterUtil.overwriteTextToFile(filePath, CSVFormatterUtil.toCSVStringNoFormat(productList));
    }

    public static void editProductByID(String filePath, int id, String name, Double price, String info) {
        List<Product> productList = FileReaderUtil.readObjects(filePath, new ProductMapper());

        Integer index = null;
        for (Product product : productList) {
            if (product.getId() == id) {
                index = productList.indexOf(product);
            }
            if (EntityIDService.isNameExist(filePath, name, new ProductMapper())) {
                throw new RuntimeException("The product \"" + name.toUpperCase() + "\" is exist!!!");
            }
        }
        productList.set(index, new Product(id, name, new BigDecimal(price).setScale(2, RoundingMode.HALF_EVEN), info));

        FileWriterUtil.overwriteTextToFile(filePath, CSVFormatterUtil.toCSVStringNoFormat(productList));
    }
}

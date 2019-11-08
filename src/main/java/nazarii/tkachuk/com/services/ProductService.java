package nazarii.tkachuk.com.services;

import nazarii.tkachuk.com.entities.Product;
import nazarii.tkachuk.com.mappers.ProductMapper;
import nazarii.tkachuk.com.providers.PropertiesProvider;
import nazarii.tkachuk.com.utils.CSVFormatterUtil;
import nazarii.tkachuk.com.utils.FileReaderUtil;
import nazarii.tkachuk.com.utils.FileWriterUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

public class ProductService {

    private EntityIDService entityIDService;

    private String productFilePath;
    private final String PRODUCT_PROPERTY_NAME = "result.file.name.products";

    public ProductService() {
        productFilePath = PropertiesProvider.getProperty(PRODUCT_PROPERTY_NAME);
        entityIDService = new EntityIDService(productFilePath);
    }

    public String getPRODUCT_FILE_PATH() {
        return productFilePath;
    }

    public Product getProductById(int id) {


        List<Product> productList = FileReaderUtil.readObjects(productFilePath, new ProductMapper());

        if (!entityIDService.isIDExist(productList, id)) {
            throw new RuntimeException("The product with ID № \"" + id + "\" isn't exist!!!\"");
        }
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public Product getProductByName(String name) {
        List<Product> productList = FileReaderUtil.readObjects(productFilePath, new ProductMapper());

        if (!isNameExist(productList, name)) {
            throw new RuntimeException("The product \"" + name + "\" isn't exist!!!\"");
        }
        Product correctOne = null;
        for (Product product : productList) {
            if (product.getName().equals(name)) {
                correctOne = product;
            }
        }
        return correctOne;
    }

    public Product createNewProduct(String name, Double price, String info) {
        Product product = buildNewProduct(name, price, info);
        return save(product);
    }

    private Product buildNewProduct(String name, Double price, String info) {
        validateProductData(name, price);
        return new Product(
                name,
                new BigDecimal(price).setScale(2, RoundingMode.HALF_EVEN),
                info);
    }

    private void validateProductData(String name, Double price) {
        if (entityIDService.isPriceNotZero(new BigDecimal(price))) {
            throw new RuntimeException("Price can't be zero!!!");
        }
        if (isNameExist(productFilePath, name)) {
            throw new RuntimeException("The product \"" + name.toUpperCase() + "\" is exist!!!");
        }
    }

    private Product save(Product product) {
        final int id = entityIDService.generateId();

        product.setId(id);
        FileWriterUtil.writeToFile(productFilePath, product.toCSVString());

        return product;
    }

    public void deleteProductByID(int id) {
        List<Product> productList = FileReaderUtil.readObjects(productFilePath, new ProductMapper());

        boolean removed = productList.removeIf(product -> product.getId().equals(id));

        if (!removed) {
            throw new RuntimeException("\"Removal failed!!!\nThe product with ID \"" + id + "\" isn't exist!!!");
        }

        FileWriterUtil.overwriteTextToFile(productFilePath, CSVFormatterUtil.toCSVString(productList));
        System.out.println("The product with ID № \"" + id + "\" was successfully deleted!!!");
    }

    public void editProductByID(int id, String name, Double price, String info) {
        List<Product> productList = FileReaderUtil.readObjects(productFilePath, new ProductMapper());

//        Product targetProduct = getProductById(id);

        Product targetProduct = productList.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("The product with ID № \"" + id + "\" isn't exist!!!\""));
//        Product targetProduct = null;
//        for (Product product : productList) {
//            if (product.getId() == id) {
//                targetProduct = product;
//            }
//        }
//        if (targetProduct == null) {
//            throw new RuntimeException("Edition failed!!!\n Product with id № " + id + " not found!!!");
//        }
        if (!targetProduct.getName().equals(name)) {
            validateProductData(name, price);
        } else if (price == 0) {
            throw new RuntimeException("Price can't be zero!!!");
        }

        targetProduct.setName(name);
        targetProduct.setPrice(new BigDecimal(price).setScale(2, RoundingMode.HALF_EVEN));
        targetProduct.setInfo(info);
//        Integer index = null;
//        for (Product product : productList) {
//            if (product.getId() == id) {
//                index = productList.indexOf(product);
//            }
//            if (EntityIDService.isNameExist(filePath, name, new ProductMapper())) {
//                throw new RuntimeException("The product \"" + name.toUpperCase() + "\" is exist!!!");
//            }
//        }
//        productList.set(index, new Product(id, name, new BigDecimal(price).setScale(2, RoundingMode.HALF_EVEN), info));
        System.out.println("The product with ID № \"" + id + "\" was successfully edited!!!");
        FileWriterUtil.overwriteTextToFile(productFilePath, CSVFormatterUtil.toCSVString(productList));
    }

    public boolean isNameExist(String filePath, String name) {

        List<Product> list = FileReaderUtil.readObjects(filePath, new ProductMapper());

        for (Product product : list) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNameExist(List<Product> products, String name) {

        for (Product product : products) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}

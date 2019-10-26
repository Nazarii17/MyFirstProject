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

    private EntityIDService entityIDService;

    private final String PRODUCT_FILE_PATH = "products.csv";

    public ProductService() {
        entityIDService = new EntityIDService(PRODUCT_FILE_PATH);
    }

    public String getPRODUCT_FILE_PATH() {
        return PRODUCT_FILE_PATH;
    }

    public Product getProductById(int id) {
        List<Product> productList = FileReaderUtil.readObjects(PRODUCT_FILE_PATH, new ProductMapper());

        if (!entityIDService.isIDExist(productList, id)) {
            throw new RuntimeException("The product with ID № \"" + id + "\" isn't exist!!!\"");
        }
        Product correctOne = null;
        for (Product product : productList) {
            if (product.getId() == id) {
                correctOne = product;
            }
        }
        return correctOne;
    }

    private int getIndexById(List<Product> list, int id) {

        if (!entityIDService.isIDExist(list, id)) {
            throw new RuntimeException("The product with ID № \"" + id + "\" isn't exist!!!\"");
        }
        Integer correctOne = null;
        for (Product product : list) {
            if (product.getId() == id) {
                correctOne = list.indexOf(product);
            }
        }
        return correctOne;
    }

    public Product getProductByName(String name) {
        List<Product> customerList = FileReaderUtil.readObjects(PRODUCT_FILE_PATH, new ProductMapper());

        if (!entityIDService.isNameExist(PRODUCT_FILE_PATH, name, new ProductMapper())) {
            throw new RuntimeException("The product \"" + name + "\" isn't exist!!!\"");
        }
        Product correctOne = null;
        for (Product product : customerList) {
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
        if (entityIDService.isNameExist(PRODUCT_FILE_PATH, name, new ProductMapper())) {
            throw new RuntimeException("The product \"" + name.toUpperCase() + "\" is exist!!!");
        }
    }

    private Product save(Product product) {
        final int id = entityIDService.generateId();

        product.setId(id);
        FileWriterUtil.writeToFile(PRODUCT_FILE_PATH, product.toCSVString());

        return product;
    }

    public void deleteProductByID(int id) {
        List<Product> productList = FileReaderUtil.readObjects(PRODUCT_FILE_PATH, new ProductMapper());
        if (entityIDService.isIDExist(productList, id)) {
            productList.remove(getIndexById(productList, id));
        } else {
            throw new RuntimeException("\"Removal failed!!!\nThe product with ID № \"" + id + "\" isn't exist!!!");
        }
        FileWriterUtil.overwriteTextToFile(PRODUCT_FILE_PATH, CSVFormatterUtil.toCSVString(productList));
        System.out.println("The product with ID № \"" + id + "\" was successfully deleted!!!");
    }

    public void editProductByID(int id, String name, Double price, String info) {
        List<Product> productList = FileReaderUtil.readObjects(PRODUCT_FILE_PATH, new ProductMapper());

        Product targetProduct = getProductById(id);

        if (!targetProduct.getName().equals(name)) {
            validateProductData(name, price);
        } else if (price == 0) {
            throw new RuntimeException("Price can't be zero!!!");
        }
        for (Product product : productList) {
            if (product.getId() == id) {
                targetProduct = product;
            }
        }
        if (targetProduct == null) {
            throw new RuntimeException("Edition failed!!!\n Product with id № " + id + " not found!!!");
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
        FileWriterUtil.overwriteTextToFile(PRODUCT_FILE_PATH, CSVFormatterUtil.toCSVString(productList));
    }
}

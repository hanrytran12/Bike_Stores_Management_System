/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Controller.ManagerProduct;
import Model.Product;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Huy
 */
public class ProductService {

    private final ProductValidationService validationService = new ProductValidationService();
    private final FileService fileService = new FileService();

    public boolean addProduct(ManagerProduct listProduct, String id, String name, String brandId, String categoryId, String modelYear, String listPrice) throws FileNotFoundException {
        if (validationService.checkValidDataToAddProduct(name, brandId, categoryId, modelYear, listPrice)) {
            listProduct.put(id, new Product(id, name, brandId, categoryId, Integer.parseInt(modelYear), Integer.parseInt(listPrice)));
            return true;
        }
        return false;
    }

    public List<Product> searchProduct(ManagerProduct listProduct, List<Product> listProductSearchByName, String name) {
        listProduct.entrySet().stream().filter((entry) -> (entry.getValue().getName().equalsIgnoreCase(name))).forEachOrdered((entry) -> {
            listProductSearchByName.add(entry.getValue());
        });
        return listProductSearchByName;
    }

    public Product updateProduct(Product product, String newName, String newBrandId, String newCategoryId, String newModelYear, String newListPrice) throws FileNotFoundException {
        newName = (newName.isEmpty()) ? product.getName() : newName;
        newBrandId = (newBrandId.isEmpty()) ? product.getBrandId() : newBrandId;
        newCategoryId = (newCategoryId.isEmpty()) ? product.getCategoryId() : newCategoryId;
        newModelYear = (newModelYear.isEmpty()) ? Integer.toString(product.getModelYear()) : newModelYear;
        newListPrice = (newListPrice.isEmpty()) ? Integer.toString(product.getListPrice()) : newListPrice;

        if (validationService.checkValidDataToUpdauteProduct(newBrandId, newCategoryId, newModelYear, newListPrice)) {
            product.setName(newName);
            product.setBrandId(newBrandId);
            product.setCategoryId(newCategoryId);
            product.setModelYear(Integer.parseInt(newModelYear));
            product.setListPrice(Integer.parseInt(newListPrice));
            return product;
        } else {
            return null;
        }
    }

    public void saveToFile(ManagerProduct listProduct) throws IOException {
        fileService.saveToFile(listProduct);
    }

    public List<Product> loadFile() throws FileNotFoundException {
        return fileService.loadFile();
    }
}

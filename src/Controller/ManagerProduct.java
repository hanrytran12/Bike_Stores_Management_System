package Controller;

import Service.Tools;
import Model.Product;
import Service.FileService;
import Service.ProductService;
import Service.ProductValidationService;
import Service.SortService;
import View.ProductView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManagerProduct extends HashMap<String, Product> {

    private int numericalOrder;

    private final ProductService service;
    private final FileService fileService;
    private final SortService sortService;
    private final ProductValidationService validationService = new ProductValidationService();
    private final ProductView view;

    public ManagerProduct() throws FileNotFoundException {
        this.view = new ProductView();
        this.sortService = new SortService();
        this.fileService = new FileService();
        this.service = new ProductService();
        fileService.getData(this);
        numericalOrder = fileService.setNumercialOrder(this);
    }

    public void addProduct() throws FileNotFoundException {
        String id = "S" + Integer.toString(numericalOrder + 1);
        String name = Tools.inputString("Enter name: ");
        String brandId = Tools.inputString("Enter brand_id: ");
        String categoryId = Tools.inputString("Enter category_id: ");
        String modelYear = Tools.inputString("Enter model_year: ");
        String listPrice = Tools.inputString("Enter list_price: ");
        if (service.addProduct(this, id, name, brandId, categoryId, modelYear, listPrice)) {
            numericalOrder++;
            Tools.displayMessage("SUCCESS!");
        } else {
            Tools.displayMessage("FAILURE!");
        }
        Tools.enterToMenu();
    }

    public void searchProduct() {
        String name = Tools.inputString("Enter name: ");
        List<Product> listProductSearchByName = new ArrayList<>();
        listProductSearchByName = service.searchProduct(this, listProductSearchByName, name);
        if (listProductSearchByName.isEmpty()) {
            Tools.displayMessage("HAVE NO ANY PRODUCT");
        } else {
            sortService.sortByModelYearDesc(listProductSearchByName);
            listProductSearchByName.forEach(product -> System.out.println(product));
        }
        Tools.enterToMenu();
    }

    public void updateProduct() throws FileNotFoundException {
        String id = Tools.inputString("Enter id: ");
        if (!this.containsKey(id)) {
            Tools.displayMessage("HAVE NO ANY PRODUCT");
        } else {
            Product product = this.get(id);
            String newName = Tools.inputString("Enter new name: ");
            String newBrandId;
            do{
                newBrandId = Tools.inputString("Enter new brand_id: ");
                if(validationService.checkBrandId(newBrandId))
                   break;
                else
                    Tools.displayMessage("PLEASE TRY AGAIN!");
            } while(true);
            String newCategoryId = Tools.inputString("Enter new category_id: ");
            String newModelYear = Tools.inputString("Enter new model_year: ");
            String newListPrice = Tools.inputString("Enter new list_price: ");
            service.updateProduct(product, newName, newBrandId, newCategoryId, newModelYear, newListPrice);
            if (product != null) {
                Tools.displayMessage("SUCCESS!");
            } else {
                Tools.displayMessage("FAILURE!");
            }
            Tools.enterToMenu();
        }
    }

    public void deleteProduct() {
        String id = Tools.inputString("Enter id: ");
        if (!this.containsKey(id)) {
            Tools.displayMessage("HAVE NO ANY PRODUCT");
        } else {
            view.displayConfirmDelete();
            int select;
            try {
                select = Tools.inputInteger("Choose your select: ");
                switch (select) {
                    case 0: {
                        Tools.displayMessage("SUCCESS!");
                        break;
                    }
                    case 1: {
                        this.remove(id);
                        Tools.displayMessage("SUCCESS!");
                        break;
                    }
                    default: {
                        Tools.displayMessage("FAILURE!");
                    }
                }
            } catch (NumberFormatException e) {
                Tools.displayMessage("FAILURE!");
            }
            Tools.enterToMenu();
        }
    }

    public void saveToFile() throws IOException {
        service.saveToFile(this);
        Tools.displayMessage("SUCCESS!");
        Tools.enterToMenu();
    }

    public void printListFromFile() throws IOException {
        List<Product> listProduct = service.loadFile();
        if (!listProduct.isEmpty()) {
            sortService.sortList(listProduct);
            listProduct.forEach(product -> System.out.println(product));
        }
        Tools.enterToMenu();
    }
}

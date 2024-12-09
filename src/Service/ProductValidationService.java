/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Huy
 */
public class ProductValidationService {

    public boolean checkName(String name) {
        return name.isEmpty();
    }

    public boolean checkBrandId(String brandId) throws FileNotFoundException {
        File file = new File("Brand.txt");
        Scanner readFile = new Scanner(file);
        while (readFile.hasNextLine()) {
            String[] data = readFile.nextLine().split(", ");
            if (brandId.equalsIgnoreCase(data[0])) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCategoryId(String categoryId) throws FileNotFoundException {
        File file = new File("Category.txt");
        Scanner readFile = new Scanner(file);
        while (readFile.hasNextLine()) {
            String[] data = readFile.nextLine().split(", ");
            if (categoryId.equalsIgnoreCase(data[0])) {
                return true;
            }
        }
        return false;
    }

    public boolean checkModelYear(String modelYear) {
        int year;
        try {
            year = Integer.parseInt(modelYear);
            return (year >= 2000 && year <= 2024);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean checkListPrice(String listPrice) {
        int price;
        try {
            price = Integer.parseInt(listPrice);
            return price > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean checkValidDataToAddProduct(String name, String brandId, String categoryId, String modelYear, String listPrice) throws FileNotFoundException {
        return !checkName(name) && checkBrandId(brandId) && checkCategoryId(categoryId) && checkModelYear(modelYear) && checkListPrice(listPrice);
    }

    public boolean checkValidDataToUpdauteProduct(String newBrandId, String newCategoryId, String newModelYear, String newListPrice) throws FileNotFoundException {
        return checkBrandId(newBrandId) && checkCategoryId(newCategoryId) && checkModelYear(newModelYear) && checkListPrice(newListPrice);
    }
}

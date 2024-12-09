/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Controller.ManagerProduct;
import Model.Product;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Huy
 */
public class FileService {

    public void getData(ManagerProduct listProduct) throws FileNotFoundException {
        File file = new File("Product.txt");
        try (Scanner fileRead = new Scanner(file)) {
            while (fileRead.hasNextLine()) {
                String[] data = fileRead.nextLine().split(", ");
                listProduct.put(data[0], new Product(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5])));
            }
        }
    }

    public int setNumercialOrder(ManagerProduct listProduct) {
        if (listProduct.isEmpty()) {
            return 0;
        }
        int numercialOrderMax = Integer.MIN_VALUE;
        for (Map.Entry<String, Product> entry : listProduct.entrySet()) {
            int numercialOrder = Integer.parseInt(entry.getKey().substring(1));
            if (numercialOrder > numercialOrderMax) {
                numercialOrderMax = numercialOrder;
            }
        }
        return numercialOrderMax;
    }

    public List<Product> loadFile() throws FileNotFoundException {
        List<Product> listProduct = new ArrayList<>();
        File file = new File("Product.txt");
        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                String[] data = fileReader.nextLine().split(", ");
                listProduct.add(new Product(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5])));
            }
        }
        return listProduct;
    }

    public void saveToFile(ManagerProduct listProduct) throws IOException {
        File file = new File("Product.txt");
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Map.Entry<String, Product> entry : listProduct.entrySet()) {
                fileWriter.write(entry.getValue().toString() + "\n");
            }
        }
    }
}

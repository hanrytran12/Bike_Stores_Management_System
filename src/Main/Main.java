package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Huy
 */
import Controller.ManagerProduct;
import Service.Tools;
import View.ProductView;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        ProductView view = new ProductView();
        ManagerProduct list = new ManagerProduct();
        do {
            view.displayMenu();
            int select;
            try {
                select = Tools.inputInteger("Enter select: ");
            } catch (NumberFormatException e) {
                Tools.displayMessage("EXIT PROGRAM!");
                return;
            }
            switch (select) {
                case 1: {
                    list.addProduct();
                    break;
                }
                case 2: {
                    list.searchProduct();
                    break;
                }
                case 3: {
                    list.updateProduct();
                    break;
                }
                case 4: {
                    list.deleteProduct();
                    break;
                }
                case 5: {
                    list.saveToFile();
                    break;
                }
                case 6: {
                    list.printListFromFile();
                    break;
                }
                default: {
                    Tools.displayMessage("EXIT PROGRAM!");
                    return;
                }
            }
        } while (true);
    }
}

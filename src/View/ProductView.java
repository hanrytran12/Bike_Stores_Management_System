/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Huy
 */
package View;

import Service.Tools;

public class ProductView {

    public void displayMenu() {
        Tools.displayMessage("============================================"
                + "\n=      BIKE STORES MANAGEMENT SYSTEM       ="
                + "\n============================================"
                + "\n=   1. Add a product                       ="
                + "\n=   2. Search product                      ="
                + "\n=   3. Update product                      ="
                + "\n=   4. Delete product                      ="
                + "\n=   5. Save products to file               ="
                + "\n=   6. Print list products from the file   ="
                + "\n=   Others - Quit                          ="
                + "\n============================================");
    }

    public void displayConfirmDelete() {
        Tools.displayMessage("================================================"
                + "\n=  ARE YOU SURE WANT TO DELETE THIS PRODCUT?   ="
                + "\n=        0: NO         |        1: YES         ="
                + "\n================================================");

    }
}

package Service;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Huy
 */
public class Tools {

    public static void displayMessage(String text) {
        System.out.println(text);
    }

    public static int inputInteger(String text) {
        displayMessage(text);
        Scanner ip = new Scanner(System.in);
        int number = Integer.parseInt(ip.next());
        return number;
    }

    public static String inputString(String text) {
        displayMessage(text);
        Scanner ip = new Scanner(System.in);
        return ip.nextLine();
    }

    public static void enterToMenu() {
        Tools.displayMessage("ENTER to back to the main menu!");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
}

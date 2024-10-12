package main;

import entities.User;
import view.MainMenu;
import entities.Account;
import entities.Book;
public class Main {

    public static User LOGGED_IN_CUSTOMER = null;

    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        mainMenu.initializeData();
        mainMenu.menu();





    }
}





package ro.tuc;

import ro.tuc.GUI.MainController;
import ro.tuc.GUI.MainPage;

public class Main {
    public static void main(String[] args) {
        MainPage mainPage = new MainPage();
        MainController mainController = new MainController(mainPage);
    }
}
package ui;

import Controller.Controller;

public class Main {
    public static MainMenu MAIN_WINDOW;
    public static void main(String[] args) {
//        MAIN_WINDOW = new MainMenu("BuildingServiceCenter", null);
        Controller controller = new Controller();
        controller.start();
    }
}

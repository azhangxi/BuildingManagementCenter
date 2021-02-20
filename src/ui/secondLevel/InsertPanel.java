package ui.secondLevel;

import model.*;
import ui.BuildingOperations;
import ui.MainMenu;
import ui.MyPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.Main.MAIN_WINDOW;

public class InsertPanel extends MyPanels {
    String[] columns;
    String[] data;
    private int startIndexOfTextFields = 2;
    private int code;

    public InsertPanel(MyPanels sourcePanel, String[] columns, int code) {
        super(sourcePanel);
        this.setLayout(null);
        this.columns = columns;
        data = new String[columns.length];
        this.code = code;
        initButtons();
    }

    private void initButtons() {
        initializeBackButton(this);
        int startY = 100;
        int labelX = 50;
        int textX = 200;
        int h = 50;
        int labelW = 150;
        int textW = 500;
        for (String s : columns) {
            JLabel label = new JLabel(s);
            JTextField text = new JTextField();
            label.setBounds(labelX, startY, labelW, h);
            text.setBounds(textX, startY, textW, h);
            startY += 60;
            this.add(label);
            this.add(text);
        }
        initSaveButton();
    }

    private void initSaveButton() {
        JButton save = new JButton("Save");
        save.setBounds(350, 500, 100, 50);
        save.addActionListener(new SaveListener());
        this.add(save);
    }

    class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int j = startIndexOfTextFields;
            for (int i = 0; i < data.length; i++) {
                JTextField textField = (JTextField) InsertPanel.this.getComponent(j);
                data[i] = textField.getText();
                j += 2;
            }
            codeHandler();
            sourcePanel.initTable();
            InsertPanel.this.setVisible(false);
            MAIN_WINDOW.setContentPane(sourcePanel);
            sourcePanel.setVisible(true);
        }

        private void codeHandler() {
            try {
                switch (code) {
                    case 0:
                        Building model = new Building(data[1], data[0]);
                        MainMenu.delegate.insertBuilding(model);
                        break;
                    case 1:
                        DOBandAge DOBandAge = new DOBandAge(data[0], Integer.parseInt(data[1]));
                        MainMenu.delegate.insertDOBandAge(DOBandAge);
                        break;
                    case 2:
                        GarbageRoom garbageRoom = new GarbageRoom(data[0], data[1], data[2]);
                        MainMenu.delegate.insertGarbageRoom(garbageRoom);
                        break;
                    case 3:
                        Landlord landlord = new Landlord(data[0]);
                        MainMenu.delegate.insertLandlord(landlord);
                        break;
                    case 4:
                        Manage manage = new Manage(data[0], data[1]);
                        MainMenu.delegate.insertManage(manage);
                        break;
                    case 5:
                        Manager manager = new Manager(data[0], data[1], data[2]);
                        MainMenu.delegate.insertManager(manager);
                        break;
                    case 6:
                        ParkingSpot parkingSpot = new ParkingSpot(data[0], data[1], data[2], data[3],
                                Integer.parseInt(data[4]), data[5]);
                        MainMenu.delegate.insertParkingSpot(parkingSpot);
                        break;
                    case 7:
                        Postman postman = new Postman(data[0], data[1], data[2]);
                        MainMenu.delegate.insertPostman(postman);
                        break;
                    case 8:
                        Resident resident = new Resident(data[0], data[1], data[2], Integer.parseInt(data[3]));
                        MainMenu.delegate.insertResident(resident);
                        break;
                    case 9:
                        Room room = new Room(Integer.parseInt(data[0]), data[1], data[2]);
                        MainMenu.delegate.insertRoom(room);
                        break;
                    case 10:
                        Serve serve = new Serve(data[0], data[1], data[2]);
                        MainMenu.delegate.insertServe(serve);
                        break;
                    case 11:
                        SINandName sINandName = new SINandName(data[0], data[1]);
                        MainMenu.delegate.insertSINandName(sINandName);
                        break;
                    case 12:
                        Tenant tenant = new Tenant(data[0], Integer.parseInt(data[1]));
                        MainMenu.delegate.insertTenant(tenant);
                        break;
                    case 13:
                        VideoServeillance videoServeillance = new VideoServeillance(data[0],
                                Integer.parseInt(data[1]), data[2]);
                        MainMenu.delegate.insertVideoServeillance(videoServeillance);
                        break;
                    default:
                        MainMenu.makeWarningDialog("SelectionError");
                        break;
                }

            } catch (NumberFormatException e) {
                MainMenu.makeWarningDialog("You need to enter a pure number");
            }

        }
    }

}

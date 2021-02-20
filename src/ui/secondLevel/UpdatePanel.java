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

public class UpdatePanel extends MyPanels {
    String[] columns;
    String[] data;
    private int startIndexOfTextFields = 2;
    private int code;
    private String[] primaryKeyNames;
    private String[] oldValues;

    public UpdatePanel(MyPanels sourcePanel, String[] columns, int code, String[] primaryKeyNames, String[] oldValues) {
        super(sourcePanel);
        this.setLayout(null);
        this.columns = columns;
        this.primaryKeyNames = primaryKeyNames;
        this.oldValues = oldValues;
        data = new String[columns.length];
        this.code = code;
        initButtons();
    }

    private void initButtons() {
        initializeBackButton(this);
        int startY = 100;
        int labelX = 100;
        int textX = 200;
        int h = 50;
        int labelW = 100;
        int textW = 500;
        for (int j = 0; j < columns.length; j++) {
            JLabel label = new JLabel(columns[j]);
            JTextField text = new JTextField();
            label.setBounds(labelX, startY, labelW, h);
            text.setBounds(textX, startY, textW, h);
            startY += 60;
            text.setText(oldValues[j]);
            for (int i = 0; i < primaryKeyNames.length; i++) {
                if (primaryKeyNames[i] == columns[j]) {
                    text.setEditable(false);
                }
            }
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
                JTextField textField = (JTextField) UpdatePanel.this.getComponent(j);
                data[i] = textField.getText();
                j += 2;
            }
            codeHandler();

            sourcePanel.initTable();
            UpdatePanel.this.setVisible(false);
            MAIN_WINDOW.setContentPane(sourcePanel);
            sourcePanel.setVisible(true);
        }

        private void codeHandler() {
            try {
                switch (code) {
                    case 0:
                        MainMenu.delegate.updateBuilding(data[0], data[1]);
                        break;
                    case 2:
                        MainMenu.delegate.updateGarbageRoom(data[0], data[1], data[2]);
                        break;
                    case 5:
                        MainMenu.delegate.updateManager(data[0], data[1], data[2]);
                        break;
                    case 6:
                        MainMenu.delegate.updateParkingSpot(data[0], data[1], data[2], data[3],
                                Integer.parseInt(data[4]), data[5]);
                        break;
                    case 7:
                        MainMenu.delegate.updatePostman(data[0], data[1], data[2]);
                        break;
                    case 8:
                        MainMenu.delegate.updateResident(data[0], data[1], data[2], Integer.parseInt(data[3]));
                        break;
                    case 9:
                        MainMenu.delegate.updateRoom(Integer.parseInt(data[0]), data[1], data[2]);
                        break;
                    case 12:
                        MainMenu.delegate.updateTenant(data[0], Integer.parseInt(data[1]));
                        break;
                    case 13:

                        MainMenu.delegate.updateVideoServeillance(data[0],
                                Integer.parseInt(data[1]), data[2]);
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

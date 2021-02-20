package ui.secondLevel;

import ui.Main;
import ui.MainMenu;
import ui.MyPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import static ui.MainMenu.*;

public class ShowPanel extends MyPanels {
    private int code;
    String[] columnNames;
    private String[] primaryKeyNames;
    boolean updatable = false;
    List<String[]> str;
    private int specialCode;

    String[] specialLabels = new String[]{
            "Show the SIN, owner name and unit number columns of the parking-spot information",
            "List tenants with a monthly rent greater than a certain amount",
            "List the room and parking-spot information of a certain owner",
            "List the managers' Ids and the number of buildings they manage",
            "List the building address and number of postmen, where there is more than one postmen serving in the building",
            "List the building address with the maximum number of video surveillance",
            "List the name of the postmen who serves all buildings listed"
    };

    public ShowPanel(MyPanels sourcePanel) {
        super(sourcePanel);
        this.setLayout(null);
        str = new ArrayList<>();
        initializeBackButton(this);
        JLabel label = new JLabel("Choose a table to show");
        this.add(label);
        label.setBounds(200, 5, 170, 30);
        initComboBox();
        initSpecialOperationComboBox();
        initSpecialOperationButton();
        initButtons();
    }

    private void initComboBox() {
        String[] selectionList = new String[]{"Building", "DOBandAge", "Garbage Room", "Landlord", "Manage", "Manager",
                "Parking Spot", "Postman", "Resident", "Room", "Serve", "SINandName", "Tenant", "Video Serveillance"};
        JComboBox<String> comboBox = new JComboBox<>(selectionList);
        comboBox.setBounds(370, 5, 200, 30);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // 只处理选中的状态

                if (e.getStateChange() == ItemEvent.SELECTED) {
                    code = comboBox.getSelectedIndex();
                    if (code == 1 || code == 3 || code == 4 || code == 10 || code == 11) {
                        updatable = false;
                    } else {
                        updatable = true;
                    }
                    initTable();
                    MainMenu.playButtonClip();
                    System.out.println("Selected: " + comboBox.getSelectedIndex() + " = " + comboBox.getSelectedItem());
                }
            }
        });
        comboBox.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        this.add(comboBox);
        comboBox.setSelectedIndex(-1);
    }



    private void initSpecialOperationComboBox() {
        JComboBox<String> comboBox = new JComboBox<>(specialLabels);
        comboBox.setBounds(70, 70, 500, 30);
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    specialCode = comboBox.getSelectedIndex();
                    MainMenu.playButtonClip();
                    System.out.println("Selected: " + comboBox.getSelectedIndex() + " = " + comboBox.getSelectedItem());
                }
            }
        });
        comboBox.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        this.add(comboBox);
        comboBox.setSelectedIndex(-1);
    }


    private void initSpecialOperationButton() {
        JButton special = new JButton("Operate Special Query");

        special.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenu.playButtonClip();
                if (specialCode == 1) {
                    setVisible(false);
                    Main.MAIN_WINDOW.setContentPane(
                    new SpecialOperationPanel(ShowPanel.this,
                            "Please enter the low bound of the rent of tenants"));
                } else if (specialCode == 2) {
                    setVisible(false);
                    Main.MAIN_WINDOW.setContentPane(
                    new SpecialOperationPanel(ShowPanel.this,
                            "Please enter the Owner name of parking spots"));

                } else {
                    initSpecialTable();
                    MainMenu.playButtonClip();
                }
            }
        });
        special.setBounds(580, 70, 200, 30);
        this.add(special);
    }

    private void initSpecialTable() {
        switch (specialCode) {
            case 0:
                str = delegate.projection();
                break;
            case 3:
                str = delegate.groupBy();
                break;
            case 4:
                str = delegate.groupByHaving();
                break;
            case 5:
                str = delegate.nestedAggregationWithGroupBy();
                break;
            case 6:
                str = delegate.division();
                break;
            default:
                MainMenu.makeWarningDialog("SelectionError");
                break;
        }
        initTableContent(str);
    }

    @Override
    public void initSpecialTable(String input) {
        str = delegate.join(input);
        initTableContent(str);
    }

    @Override
    public void initSpecialTable(int input) {
        str = delegate.selection(input);
        initTableContent(str);
    }

    @Override
    public void initTable() {
        switch (code) {
            case 0:
                primaryKeyNames = new String[]{"Address"};
                str = MainMenu.delegate.showBuilding();
                break;
            case 1:
                primaryKeyNames = new String[]{"Birthday", "Age"};
                str = MainMenu.delegate.showDOBandAge();
                break;
            case 2:
                primaryKeyNames = new String[]{"Id"};
                str =  MainMenu.delegate.showGarbageRoom();
                break;
            case 3:
                primaryKeyNames = new String[]{"SIN"};
                str = MainMenu.delegate.showLandlord();
                break;
            case 4:
                primaryKeyNames = new String[]{"ManagerID", "Address"};
                str = MainMenu.delegate.showManage();
                break;
            case 5:
                primaryKeyNames = new String[]{"Id"};
                str = MainMenu.delegate.showManager();
                break;
            case 6:
                primaryKeyNames = new String[]{"Id"};
                str =  MainMenu.delegate.showParkingSpot();
                break;
            case 7:
                primaryKeyNames = new String[]{"Company", "Id"};
                str = MainMenu.delegate.showPostman();
                break;
            case 8:
                primaryKeyNames = new String[]{"SIN"};
                str =  MainMenu.delegate.showResident();
                break;
            case 9:
                primaryKeyNames = new String[]{"UnitNum", "Address"};
                str = MainMenu.delegate.showRoom();
                break;
            case 10:
                primaryKeyNames = new String[]{"Company", "PostmanID", "Address"};
                str = MainMenu.delegate.showServe();
                break;
            case 11:
                primaryKeyNames = new String[]{"SIN", "OwnerName"};
                str =  MainMenu.delegate.showSINandName();
                break;
            case 12:
                primaryKeyNames = new String[]{"SIN"};
                str = MainMenu.delegate.showTenant();
                break;
            case 13:
                primaryKeyNames = new String[]{"Id"};
                str = MainMenu.delegate.showVideoServeillance();
                break;
            default:
                MainMenu.makeWarningDialog("SelectionError");
                break;
        }
        initTableContent(str);
    }

    private void initTableContent(List<String[]> strings) {
        columnNames = strings.get(0);
        String[][] array = new String[strings.size() - 1][columnNames.length];
        for(int j = 0; j < strings.size() - 1; j++){
            array[j] = strings.get(j+1);
        }

        JTable table = new JTable(array, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(800, 430));
        for(Component c : this.getComponents()) {
            if (c instanceof JScrollPane) {
                this.remove(c);
                break;
            }
        }

        JScrollPane scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        scrollPane.setBounds(0, 100, 800, 430);
    }

    private void initButtons() {
        initializeSingleButton("Insert", LEFT_BUTTON_X, 530);
        initializeSingleButton("Delete", MIDDLE_BUTTON_X, 530);
        initializeSingleButton("Update", RIGHT_BUTTON_X, 530);
    }

    private void initializeSingleButton(String name, int x, int y) {
        JButton button = new JButton(name);
        button.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setLocation(x, y);
        button.addActionListener(new ShowActionListener());
        add(button);
    }

    private void initSpecialButton() {
        JButton specialButton;
    }

    class ShowActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "Insert":
                    MainMenu.playButtonClip();
                    setVisible(false);
                    Main.MAIN_WINDOW.setContentPane(new InsertPanel(ShowPanel.this, columnNames, code));
                    break;
                case "Delete":
                    MainMenu.playButtonClip();
                    delete();
                    break;
                case "Update":
                    if (updatable) {
                        String[] selectedElementInTable = getSelectedElementInTable("You must select an element to update");
                        if (selectedElementInTable != null) {
                            MainMenu.playButtonClip();
                            setVisible(false);
                            Main.MAIN_WINDOW.setContentPane(new UpdatePanel(ShowPanel.this, columnNames,
                                    code, primaryKeyNames, selectedElementInTable));
                        }
                    } else {
                        MainMenu.makeWarningDialog("This Table is not updatable");
                    }
            }

        }
    }



    private void delete() {
        String[] primaryKeys = getPrimaryKeys("You must select an element to delete");
        if(primaryKeys == null) {
            return;
        }
        int i = makeConfirmDialog("Do you confirm to delete selected item");
        if (i == 0) {
            deleteHelper(primaryKeys);
        }
    }

    private void deleteHelper(String[] primaryKeys) {
        switch (code) {
            case 0:
                MainMenu.delegate.deleteBuilding(primaryKeys[0]);
                break;
            case 1:

                MainMenu.delegate.deleteDOBandAge(primaryKeys[0], Integer.parseInt(primaryKeys[1]));
                break;
            case 2:

                MainMenu.delegate.deleteGarbageRoom(primaryKeys[0]);
                break;
            case 3:

                MainMenu.delegate.deleteLandlord(primaryKeys[0]);
                break;
            case 4:

                MainMenu.delegate.deleteManage(primaryKeys[0], primaryKeys[1]);
                break;
            case 5:

                MainMenu.delegate.deleteManager(primaryKeys[0]);
                break;
            case 6:

                MainMenu.delegate.deleteParkingSpot(primaryKeys[0]);
                break;
            case 7:

                MainMenu.delegate.deletePostman(primaryKeys[0], primaryKeys[1]);
                break;
            case 8:

                MainMenu.delegate.deleteResident(primaryKeys[0]);
                break;
            case 9:

                MainMenu.delegate.deleteRoom(Integer.parseInt(primaryKeys[0]), primaryKeys[1]);
                break;
            case 10:

                MainMenu.delegate.deleteServe(primaryKeys[0], primaryKeys[1], primaryKeys[2]);
                break;
            case 11:

                MainMenu.delegate.deleteSINandName(primaryKeys[0], primaryKeys[1]);
                break;
            case 12:

                MainMenu.delegate.deleteTenant(primaryKeys[0]);
                break;
            case 13:

                MainMenu.delegate.deleteVideoServeillance(primaryKeys[0]);
                break;
            default:
                MainMenu.makeWarningDialog("SelectionError");
                break;
        }
        initTable();
    }

    private String[] getSelectedElementInTable(String string) {
        String[] selectedRow = null;
        for (Component c : ShowPanel.this.getComponents()) {
            if (c instanceof JScrollPane) {
                JViewport temp = (JViewport) ((JScrollPane) c).getComponent(0);
                JTable table = (JTable) temp.getComponent(0);
                int index = table.getSelectedRow();
                if (index == -1) {
                    makeWarningDialog(string);
                    return null;
                }
                selectedRow = str.get(index + 1);
            }
        }
        return selectedRow;
//        int[] primaryKeyIndexes = new int[primaryKeyNames.length];
//        String[] pk = new String[primaryKeyNames.length];
//        int k = 0;
//        for (int i = 0; i < columnNames.length; i++) {
//            for (int j = 0; j < primaryKeyNames.length; j++) {
//                if (columnNames[i] == primaryKeyNames[j]) {
//                    primaryKeyIndexes[k++] = i;
//                }
//            }
//        }
//        for (int i = 0; i < primaryKeyIndexes.length; i++) {
//            pk[i] = selectedRow[primaryKeyIndexes[i]];
//        }
//        return pk;
    }

    private String[] getPrimaryKeys(String string) {
        String[] selectedRow = getSelectedElementInTable(string);
        int[] primaryKeyIndexes = new int[primaryKeyNames.length];
        String[] pk = new String[primaryKeyNames.length];
        int k = 0;
        for (int i = 0; i < columnNames.length; i++) {
            for (int j = 0; j < primaryKeyNames.length; j++) {
                if (columnNames[i] == primaryKeyNames[j]) {
                    primaryKeyIndexes[k++] = i;
                }
            }
        }
        for (int i = 0; i < primaryKeyIndexes.length; i++) {
            pk[i] = selectedRow[primaryKeyIndexes[i]];
        }
        return pk;
    }
}

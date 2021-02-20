package ui.secondLevel;

import ui.MainMenu;
import ui.MyPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.Main.MAIN_WINDOW;

public class SpecialOperationPanel extends MyPanels {
    private String prompt;
    private JTextField text;

    public SpecialOperationPanel(MyPanels sourcePanel, String prompt) {
        super(sourcePanel);
        this.prompt = prompt;
        this.setLayout(null);
        initPanel();
    }


    private void initPanel() {
        initializeBackButton(this);
        initSaveButton();
        JLabel label = new JLabel(prompt);
        label.setBounds(100, 100, 600, 100);
        label.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 20));
        add(label);
        text = new JTextField();
        text.setBounds(300, 250, 200, 50);
        this.add(text);
    }

    private void initSaveButton() {
        JButton save = new JButton("Save");
        save.setBounds(350, 500, 100, 50);
        save.addActionListener(new SpecialOperationPanel.SaveListener());
        this.add(save);
    }

    class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MainMenu.playButtonClip();
            if (prompt == "Please enter the low bound of the rent of tenants") {
                try {
                    int rtn = Integer.parseInt(text.getText().trim());
                    sourcePanel.initSpecialTable(rtn);
                    SpecialOperationPanel.this.setVisible(false);
                    MAIN_WINDOW.setContentPane(sourcePanel);
                    sourcePanel.setVisible(true);
                } catch (NumberFormatException err) {
                    MainMenu.makeWarningDialog("You need to enter a pure number");
                }
            } else {
                String str = text.getText().trim();
                sourcePanel.initSpecialTable(str);
                SpecialOperationPanel.this.setVisible(false);
                MAIN_WINDOW.setContentPane(sourcePanel);
                sourcePanel.setVisible(true);
            }

        }
    }
}

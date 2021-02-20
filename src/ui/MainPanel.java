package ui;

import ui.secondLevel.ShowPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static ui.MainMenu.BUTTON_HEIGHT;
import static ui.MainMenu.BUTTON_WIDTH;

public class MainPanel extends MyPanels {

    public MainPanel() {
        super(null);
        setLayout(null);
        JLabel title = new JLabel("Building Service center");
        title.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 24));
        title.setForeground(Color.YELLOW);
        title.setBounds(250, 100, 350, 100);
        this.add(title);
        initializeButtons();
        setVisible(true);
    }

    private void initializeButtons() {
        initializeSingleButton("Welcome", MIDDLE_BUTTON_X - 50, MIDDLE_BUTTON_Y + 10);
    }

    private void initializeSingleButton(String name, int x, int y) {
        JButton button = new JButton(name);
        button.setSize(BUTTON_WIDTH + BUTTON_WIDTH / 2, BUTTON_HEIGHT + BUTTON_HEIGHT / 2);
        button.setLocation(x, y);
        button.addActionListener(new InitializeActionListener());
        add(button);
    }

    class InitializeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainMenu.playButtonClip();
            setVisible(false);
            Main.MAIN_WINDOW.setContentPane(new ShowPanel(MainPanel.this));
        }
    }

}

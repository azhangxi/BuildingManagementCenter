package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static ui.Main.MAIN_WINDOW;
import static ui.MainMenu.BUTTON_HEIGHT;
import static ui.MainMenu.BUTTON_WIDTH;

public abstract class MyPanels extends JPanel {
    protected static final String PATH = new File("").getAbsolutePath();
    protected static final int MIDDLE_BUTTON_Y = 260;
    protected static final int MIDDLE_BUTTON_X = 300;
    protected static final int LEFT_BUTTON_X = 95;
    protected static final int RIGHT_BUTTON_X = 505;
    protected JButton back = new JButton(new ImageIcon(PATH + "/data/back.png"));
    protected MyPanels sourcePanel;
    protected Dimension textDimension = new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT);
    public Image image = new ImageIcon(PATH + "/data/UBC.png").getImage();

    public MyPanels(MyPanels sourcePanel) {
        this.sourcePanel = sourcePanel;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

    protected void initializeBackButton(JComponent thisPanel) {
        back.setBounds(0, 5, 30, 30);
        back.setBorder(null);
        back.addActionListener(new BackAction(thisPanel));
        System.gc();
        thisPanel.add(back);
    }

    protected class BackAction implements ActionListener {
        private JComponent component;

        public BackAction(JComponent component) {
            this.component = component;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            MainMenu.playButtonClip();
            component.setVisible(false);
            MAIN_WINDOW.setContentPane(sourcePanel);
            sourcePanel.setVisible(true);
        }
    }

    public void initTable() {
        //single abstract
    }

    public void initSpecialTable(int input) {
        //single abstract
    }

    public void initSpecialTable(String input){
        //single abstract
    }
}

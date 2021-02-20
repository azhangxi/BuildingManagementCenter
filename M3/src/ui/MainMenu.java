package ui;

import Controller.Controller;
import delegates.BuildingOperationsDelegate;
import jdk.nashorn.internal.scripts.JO;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JFrame {
    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int BUTTON_WIDTH = 200;
    public static final int BUTTON_HEIGHT = 40;
    public static Controller delegate;
    private static boolean enableSounds = false;

    public MainMenu(String title, Controller delegate) throws HeadlessException {
        super(title);
        this.delegate = delegate;
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setContentPane(new MainPanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    public static void makeWarningDialog(String string) {
        JOptionPane pane = new JOptionPane(string);
        playErrorClip();
        JDialog dialog = pane.createDialog(Main.MAIN_WINDOW, "Warning");
        dialog.setVisible(true);
    }

    public static int makeConfirmDialog(String string) {
        return JOptionPane.showConfirmDialog(null, string);
    }

    private static void playErrorClip() {
        File sound = new File(new File("").getAbsolutePath() + "/data/error.wav");
        if (!enableSounds) {
            return;
        }
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void playSuccessClip() {
        File sound = new File(new File("").getAbsolutePath() + "/data/success.wav");
        if (!enableSounds) {
            return;
        }
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void playDeleteClip() {
        File sound = new File(new File("").getAbsolutePath() + "/data/delete.wav");
        if (!enableSounds) {
            return;
        }
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void playButtonClip() {
        File sound = new File(new File("").getAbsolutePath() + "/data/button.wav");
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

}

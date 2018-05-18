import java.awt.*;
import java.text.NumberFormat;
import java.util.*;
import javax.swing.*;
import ufoproducts.util.*;
import ufoproducts.order.*;
import java.io.*;
import java.net.*;
/**
 * TennerGUI: As the class title says, Tenner with a GUI. Implemented mainly in
 * Swing, because JavaFX seems scary. Swing also is guaranteed to be included in
 * all systems, I'm sure. (JavaFX on Linux seems to require the installation of
 * openjfx packages, which may be kind of annoying to managers who want to keep
 * packages down.)
 */
public class TennerGUI {
    //TODO: Actually learn swing, and by extension GUI programming
    public static final float VER = 0.9F;
    public static final String GUI_VER = VER + "gui0.1";
    public static POS pos = new POS();
    static JFrame hmm = new JFrame("Tenner Point-of-sale v" + GUI_VER);
    public static void main(String[] args) {
        hmm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);
        //!!BAD MEME BAD MEME!!
        Container pane = hmm.getContentPane();
        Label emptyLabel = new Label("Welcome to Tenner v" + GUI_VER + ".");
        JButton clickMeDaddy = new JButton("Click me to break!");
        JFormattedTextField typeShite = new JFormattedTextField("login:");
        pane.add(clickMeDaddy, BorderLayout.SOUTH);
        pane.add(emptyLabel, BorderLayout.NORTH);
        pane.add(typeShite, BorderLayout.CENTER);
        hmm.pack();
        hmm.setVisible(true);
        hmm.setMenuBar(new MenuBar());
        hmm.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()) - 24);
        //typeShite.setSize(240, 24); WHY DOESNT THIS WORK IM GONNA OFF MYSELF SDFIUDIJF

    }
}

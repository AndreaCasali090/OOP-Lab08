package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout; 
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

   /*
    * TODO: Starting from the application in mvcio:
    * 
    * 1) Add a JTextField and a button "Browse..." on the upper part of the
    * graphical interface.
    * Suggestion: use a second JPanel with a second BorderLayout, put the panel
    * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
    * 
    * 2) The JTextField should be non modifiable. And, should display the
    * current selected file.
    * 
    * 3) On press, the button should open a JFileChooser. The program should
    * use the method showSaveDialog() to display the file chooser, and if the
    * result is equal to JFileChooser.APPROVE_OPTION the program should set as
    * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
    * then the program should do nothing. Otherwise, a message dialog should be
    * shown telling the user that an error has occurred (use
    * JOptionPane.showMessageDialog()).
    * 
    * 4) When in the controller a new File is set, also the graphical interface
    * must reflect such change. Suggestion: do not force the controller to
    * update the UI: in this example the UI knows when should be updated, so
    * try to keep things separated.
   */
    private static final String TITLE = "My second java graphical interface";
    private final JFrame frame = new JFrame(TITLE);
    public SimpleGUIWithFileChooser() {
    //panel
    final JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout()); 
    //New panel
    final JPanel panel2 = new JPanel();
    panel2.setLayout(new BorderLayout());
    //New JTextfield
    final JTextField field = new JTextField();
    view(field);
    //New button
    final JButton browse = new JButton("Browse.. ");
    //JTextArea
    final JTextArea write = new JTextArea();
    panel.add(panel2, BorderLayout.NORTH);
    panel2.add(field);
    panel2.add(browse, BorderLayout.LINE_END);
    panel.add(write, BorderLayout.CENTER);
    final JButton save = new JButton("Save");
    panel.add(save, BorderLayout.SOUTH);
    frame.setContentPane(panel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Save button press
    save.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent r) {
            try {
                Controller.writeOnFile(write.getText());
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    });
    //Browse button press
    browse.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent b) {
            final JFileChooser fileChooser = new JFileChooser();
            final int n = fileChooser.showSaveDialog(frame);
            if (n == JFileChooser.APPROVE_OPTION) {
                Controller.setCurrentFile(fileChooser.getSelectedFile());
                view(field);
            }
        }
    }); 
    final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    final int sw = (int) screen.getWidth();
    final int sh = (int) screen.getHeight();
    frame.setSize(sw / 2, sh / 2);
    frame.setLocationByPlatform(true);
    frame.setVisible(true);
    }
    private void view(final JTextField field) {
        field.setText(Controller.getPath());
        field.setEditable(false);
    }
    public static void main(final String... args) {
        new SimpleGUIWithFileChooser();
    }
}

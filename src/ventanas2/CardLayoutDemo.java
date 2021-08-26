//coidgo sacado de docs.oracle.com/javase/tutorial/uiswing/examples/layout/CardLayoutDemoProject/src/layout/CardLayoutDemo.java

package ventanas2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CardLayoutDemo implements ItemListener {
    JPanel cards; //a panel that uses CardLayout                    //crea panel cards que usara las cartas
    final static String BUTTONPANEL = "Card with JButtons";
    final static String TEXTPANEL = "Card with JTextField";
    
    public static void main(String[] args) {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {                        //lamado por main
        //Create and set up the window.
        JFrame frame = new JFrame("CardLayoutDemo");                //crea un frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create and set up the content pane.
        CardLayoutDemo demo = new CardLayoutDemo();                 //crea un Layout
        demo.addComponentToPane(frame.getContentPane());            //llama a addComponentToPane
        
        //Display the window.                                       //muestra la ventana
        frame.pack();
        frame.setVisible(true);
    }
    
    public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.
        JPanel comboBoxPane = new JPanel(); //use FlowLayout        //crea panel principal
        String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL };        //crea items que van a ir al combobox
        JComboBox cb = new JComboBox(comboBoxItems);                //crea combobox con los items
        cb.setEditable(false);
        cb.addItemListener(this);
        comboBoxPane.add(cb);                                       //añade combobox al panel principal
        
        //hasta aca cree un panel y le puse el combobox
        
        //Create the "cards".
        JPanel card1 = new JPanel();                                //crea carta1
        card1.add(new JButton("Button 1"));
        card1.add(new JButton("Button 2"));
        card1.add(new JButton("Button 3"));
        
        JPanel card2 = new JPanel();                                //crea carta2
        card2.add(new JTextField("TextField", 20));
        
        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());                       //le asigna a JPanel cards un CardLayout
        cards.add(card1, BUTTONPANEL);                              //añade a cards la carta 1 con ¿el string que lo llamo?
        cards.add(card2, TEXTPANEL);                                //añade a cards la carta 2 con ¿el string que lo llamo?
        
        pane.add(comboBoxPane, BorderLayout.PAGE_START);            //se el añado a contaniner pane todo lo hecho
        pane.add(cards, BorderLayout.CENTER);
    }
    
    @Override
    public void itemStateChanged(ItemEvent evt) {                   //es un listener
        CardLayout cl = (CardLayout)(cards.getLayout());            //se castea cards a un layout
        cl.show(cards, (String)evt.getItem());                      //se muestra el layout
    }
}
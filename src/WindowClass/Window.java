package WindowClass;

import javax.swing.*;
import java.awt.*;

public class Window {
    static Color color = Color.decode("#B7C48B");
    static JFrame window = new JFrame();
    static CardLayout cardLayout = new CardLayout();
    static JPanel main = new JPanel(cardLayout);
    static JPanel defaut = new JPanel();
    static JPanel menu = new JPanel();

    public static void createWindow(){
        createMenuFrame();
        createMainFrame();
        KeyClass.resume = defaut.getName();
        window.setTitle("Main");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 900);
        cardLayout.show(main, defaut.getName());
        window.add(main);
        KeyClass.addFullscreen(window);
        KeyClass.addEscape(window);
        defaut.setFocusable(true);
        window.setVisible(true);
    }
    public static void createMainFrame(){
        defaut.setName("Titlescreen");
        defaut.setLayout(null);
        defaut.add(new JLabel("Page Principale"));
        defaut.setVisible(true);
        main.add(defaut, defaut.getName());
    }
    public static void createMenuFrame(){
        final int value;
        menu.setName("Menu");
        menu.setLayout(null);
        KeyClass.addSlide(menu, "Sound");
        menu.setBackground(color);
        menu.setVisible(false);
        main.add(menu, "Menu");

    }
}


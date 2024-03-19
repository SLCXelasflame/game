package WindowClass;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyClass {
    static Color color = Window.color;
    static JFrame window = Window.window;
    static JPanel menu = Window.menu;
    static JPanel main = Window.main;
    static CardLayout cardLayout = Window.cardLayout;
    static JPanel defaut = Window.defaut;
    public static void addFullscreen(JFrame frame){
        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_F11) {
                    if ((frame.getExtendedState() & JFrame.MAXIMIZED_BOTH) != 0){
                        frame.setExtendedState(JFrame.NORMAL);
                    }
                    else {
                        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        frame.revalidate();
        frame.repaint();
        frame.setFocusable(true);
    }
    private static String findCurrentPanelName(JPanel main) {
        for (Component comp : main.getComponents()) {
            if (comp.isVisible()) {
                return comp.getName();
            }
        }
        return null;
    }
    static String resume;
    public static void addEscape(JFrame frame){
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if(menu.isVisible()){
                        cardLayout.show(main, resume);
                    }
                    else {
                        resume = findCurrentPanelName(main);
                        cardLayout.show(main, menu.getName());
                    }

                    }

            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        frame.revalidate();
        frame.repaint();
        frame.setFocusable(true);

    }

    public static void addSlide(JPanel panel, String name){
        JSlider slide = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 50);
        slide.setUI(new CustomSliderUI(slide));
        slide.setBackground(color);
        slide.setBounds(10,10,100,20);
        slide.setName(name);
        JLabel valueLabel = new JLabel(name + " : " + slide.getValue());
        slide.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!source.getValueIsAdjusting()) {
                    int value = source.getValue();
                    // Met à jour le texte du JLabel de manière asynchrone
                    window.requestFocus();
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            valueLabel.setText(name +" : " + value);
                        }

                    });}
            }
        });


        panel.add(slide);
        valueLabel.setBounds(150, 0, 150, 30);
        panel.add(valueLabel);
        menu.setFocusable(true);

    }

}

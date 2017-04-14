/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tomatotimer.app;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Stas Torgashov  (crimcat@yandex.ru)
 */
public class TomatoTimer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }
    
    private static void createAndShowGUI() {
        try {
            for(javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainDialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        JFrame.setDefaultLookAndFeelDecorated(true);
        
        appFrame = new MainDialogue("Tomato timer");
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.addWindowStateListener((WindowEvent e) -> {
            if(e.getNewState() == JFrame.ICONIFIED) {
                appFrame.setVisible(false);
            }
        });
        appFrame.pack();
        appFrame.setVisible(true);
        
        putApplicationIntoTray();
    }
    
    private static void putApplicationIntoTray() {
        if(SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            
            ImageIcon imageIcon = new ImageIcon(TomatoTimer.class.getClassLoader().getResource("tomatotimer/rc/images/t.png"));
            TrayIcon trayIcon = new TrayIcon(imageIcon.getImage(), "TomatoTimer");
            trayIcon.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(appFrame.getState() == JFrame.NORMAL) {
                        appFrame.setState(JFrame.ICONIFIED);
                        appFrame.setVisible(false);
                    } else {
                        appFrame.setVisible(true);
                        appFrame.setState(JFrame.NORMAL);
                        appFrame.requestFocus();
                        appFrame.toFront();
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            try {
                tray.add(trayIcon);
            } catch(AWTException ex) {
                Logger.getLogger(TomatoTimer.class.getName()).log(Level.SEVERE, null, ex);
            }
            appFrame.setTrayIcon(trayIcon);
        }
    }
    
    private static MainDialogue appFrame;
}

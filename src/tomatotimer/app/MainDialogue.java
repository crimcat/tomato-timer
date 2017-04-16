/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tomatotimer.app;

import java.awt.TrayIcon;
import java.io.IOException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 *
 * @author Stas Torgashov  (crimcat@yandex.ru)
 */
public class MainDialogue extends javax.swing.JFrame {

    /**
     * Creates new form MainDialogue
     * @param name
     */
    public MainDialogue(String name) {
        super(name);
        initComponents();
        setCurrentStateLabel();
        startClocks();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        timerPanel = new javax.swing.JPanel();
        timeCounterTypeLabel = new javax.swing.JLabel();
        timeValueLabel = new javax.swing.JLabel();
        startButton = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        unpredictedPauseButton = new javax.swing.JButton();
        heyIGiveUpButton = new javax.swing.JButton();
        bunchCounterLabel = new javax.swing.JLabel();
        clockLabel = new javax.swing.JLabel();
        settingsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bunchSizeCombo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tomatoDurationCombo = new javax.swing.JComboBox<>();
        breakDurationCombo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        needBeepOnTomatoStart = new javax.swing.JCheckBox();
        needBeepOnBreakStart = new javax.swing.JCheckBox();
        needBeepOnBunchFinish = new javax.swing.JCheckBox();
        helpPanel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        timeCounterTypeLabel.setText("У нас сейчас: помидорка");

        timeValueLabel.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        timeValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeValueLabel.setText("0:00");

        startButton.setText("Работаем!");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        unpredictedPauseButton.setText("Пауза");
        unpredictedPauseButton.setEnabled(false);
        unpredictedPauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unpredictedPauseButtonActionPerformed(evt);
            }
        });

        heyIGiveUpButton.setText("Всё, ухожу!");
        heyIGiveUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                heyIGiveUpButtonActionPerformed(evt);
            }
        });

        bunchCounterLabel.setFont(new java.awt.Font("Courier New", 1, 24)); // NOI18N
        bunchCounterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bunchCounterLabel.setText("-");
        bunchCounterLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("#"));

        clockLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        clockLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clockLabel.setText("00:00");

        javax.swing.GroupLayout timerPanelLayout = new javax.swing.GroupLayout(timerPanel);
        timerPanel.setLayout(timerPanelLayout);
        timerPanelLayout.setHorizontalGroup(
            timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(timerPanelLayout.createSequentialGroup()
                        .addGroup(timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(timeCounterTypeLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(timerPanelLayout.createSequentialGroup()
                                    .addComponent(bunchCounterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(timeValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(clockLabel))
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(timerPanelLayout.createSequentialGroup()
                        .addComponent(unpredictedPauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(heyIGiveUpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        timerPanelLayout.setVerticalGroup(
            timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(timerPanelLayout.createSequentialGroup()
                        .addComponent(timeCounterTypeLabel)
                        .addGroup(timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(timerPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(timeValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(timerPanelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(bunchCounterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(clockLabel))
                .addGap(5, 5, 5)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(startButton)
                .addGap(18, 18, 18)
                .addGroup(timerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unpredictedPauseButton)
                    .addComponent(heyIGiveUpButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Время пошло!", timerPanel);

        jLabel1.setText("Размер грозди: ");

        bunchSizeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5", "6" }));

        jLabel2.setText("помидорки");

        jLabel3.setText("Длительность помидорки: ");

        jLabel4.setText("Длительность перерыва:");

        tomatoDurationCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "30", "35", "40", "45", "50", "55" }));

        breakDurationCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "10", "15", "20", "25" }));

        jLabel5.setText("минут");

        jLabel6.setText("минут");

        needBeepOnTomatoStart.setText("Сигнал начала помидорки");

        needBeepOnBreakStart.setText("Сигнал начала перерыва");

        needBeepOnBunchFinish.setText("Сигнал завершения грозди");

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(3, 3, 3)
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bunchSizeCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tomatoDurationCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(breakDurationCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(settingsPanelLayout.createSequentialGroup()
                        .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addContainerGap(39, Short.MAX_VALUE))))
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(needBeepOnBunchFinish)
                    .addComponent(needBeepOnBreakStart)
                    .addComponent(needBeepOnTomatoStart))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(bunchSizeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tomatoDurationCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(7, 7, 7)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(breakDurationCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(14, 14, 14)
                .addComponent(needBeepOnTomatoStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(needBeepOnBreakStart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(needBeepOnBunchFinish)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Настройки", settingsPanel);

        jLabel7.setText("2017 Stas Torgashov (crimcat@yandex.ru)");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tomatotimer/rc/images/tb.png"))); // NOI18N

        javax.swing.GroupLayout helpPanelLayout = new javax.swing.GroupLayout(helpPanel);
        helpPanel.setLayout(helpPanelLayout);
        helpPanelLayout.setHorizontalGroup(
            helpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helpPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(helpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, helpPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, helpPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(81, 81, 81))))
        );
        helpPanelLayout.setVerticalGroup(
            helpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("?", helpPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        if((null == te) || (TomatoEngine.State.IDLE == te.getCurrentState())) {
            te = new TomatoEngine(
                Integer.parseInt(bunchSizeCombo.getSelectedItem().toString()),
                    Integer.parseInt(tomatoDurationCombo.getSelectedItem().toString()),
                    Integer.parseInt(breakDurationCombo.getSelectedItem().toString()));
            te.setMinuteElapsedCallback(() -> {
                progressBar.setValue(progressBar.getValue() - 1);
                updateTimeMeter();
            });
            te.setStateChangeCallback(() -> {
                updateTimeMeter();
                setCurrentStateLabel();
                comeToFront();
            });

            te.start();
            updateTimeMeter();
            setCurrentStateLabel();
        }
    }//GEN-LAST:event_startButtonActionPerformed

    private void updateTimeMeter() {
        timeValueLabel.setText(String.format("0:%02d", te.getMinutesToGo()));
        progressBar.setMinimum(0);
        progressBar.setMaximum(te.getCurrentPhaseDuration());
        progressBar.setValue(te.getMinutesToGo());
        bunchCounterLabel.setText(Integer.toString(te.getBunchSize()));
        trayIcon.setToolTip(getTooltipText());
    }
    
    private void disableControlsWhileRunning() {
        startButton.setEnabled(false);
        breakDurationCombo.setEnabled(false);
        bunchSizeCombo.setEnabled(false);
        tomatoDurationCombo.setEnabled(false);
    }
    
    private void enableControlsOnFinish() {
        startButton.setEnabled(true);
        breakDurationCombo.setEnabled(true);
        bunchSizeCombo.setEnabled(true);
        tomatoDurationCombo.setEnabled(true);
    }
    
    private void playWorkStartedSoundIfConfigured() {
        if(needBeepOnTomatoStart.isSelected()) {
            playSound(WORKING_TIME_SOUND);
        }
    }
    
    private void playBreakStartedSoundIfConfigured() {
        if(needBeepOnBreakStart.isSelected()) {
            playSound(BREAK_TIME_SOUND);
        }
    }
    
    private void playFinishSoundIfConfigured() {
        if(needBeepOnBunchFinish.isSelected()) {
            playSound(FINISH_SOUND);
        }
    }
    
    private static final String WORKING_TIME_SOUND = "go_working";
    private static final String BREAK_TIME_SOUND = "break";
    private static final String FINISH_SOUND = "finish";
    
    private void playSound(String name) {
        try {
            String wavFileName = String.format("tomatotimer/rc/sounds/%s.wav", name);
            Clip clip = AudioSystem.getClip();
            AudioInputStream is = AudioSystem.getAudioInputStream(TomatoTimer.class.getClassLoader().getResource(wavFileName));
            clip.open(is);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(MainDialogue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void comeToFront() {
        setAlwaysOnTop(true);
        toFront();
        requestFocus();
        setVisible(true);
        setState(JFrame.NORMAL);
        repaint();
        setAlwaysOnTop(false);
    }
    
    private void setCurrentStateLabel() {
        if(null == te) {
            timeCounterTypeLabel.setText("У нас сейчас: ждём команды");
        } else {
            switch(te.getCurrentState()) {
            case WORKING:
                timeCounterTypeLabel.setText("У нас сейчас: помидорка");
                unpredictedPauseButton.setEnabled(true);
                disableControlsWhileRunning();
                playWorkStartedSoundIfConfigured();
                break;
            case PAUSED: case PAUSED_IN_BREAK:
                timeCounterTypeLabel.setText("У нас сейчас: пауза");
                break;
            case BREAK:
                timeCounterTypeLabel.setText("У нас сейчас: перерыв");
                unpredictedPauseButton.setEnabled(false);
                playBreakStartedSoundIfConfigured();
                break;
            case FINISH:
                timeCounterTypeLabel.setText("У нас сейчас: отсчёт закончен");
                enableControlsOnFinish();
                playFinishSoundIfConfigured();
                te = null;
                break;
            case IDLE:
                timeCounterTypeLabel.setText("У нас сейчас: ждём команды");
                break;
            }
            trayIcon.setToolTip(getTooltipText());
        }
    }
    
    private String getTooltipText() {
        String toolTipText = "";
        switch(te.getCurrentState()) {
        case WORKING:
            toolTipText = "TomatoTimer: worktime";
            break;
        case BREAK:
            toolTipText = "TomatoTimer: break";
            break;
        case PAUSED:
        case PAUSED_IN_BREAK:
            return "TomatoTimer: paused";
        case FINISH:
            return "TomatoTimer: finished";
        case IDLE:
            return "TomatoTimer: idle";
        }
        return String.format("%s - %d min left", toolTipText, te.getMinutesToGo());
    }
    
    private void heyIGiveUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_heyIGiveUpButtonActionPerformed
        java.util.logging.Logger.getLogger(MainDialogue.class.getName()).log(Level.INFO, "User forces exitting application");
        clockThread.interrupt();
        System.exit(0);
    }//GEN-LAST:event_heyIGiveUpButtonActionPerformed

    private void unpredictedPauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unpredictedPauseButtonActionPerformed
        if(null != te) {
            switch (te.getCurrentState()) {
                case WORKING:
                    te.pause();
                    unpredictedPauseButton.setText("Продолжаем");
                    break;
                case PAUSED:
                    unpredictedPauseButton.setText("Пауза");
                    te.proceed();
                    break;
                case PAUSED_IN_BREAK:
                    break;
                default:
                    break;
            }
            setCurrentStateLabel();
        }
    }//GEN-LAST:event_unpredictedPauseButtonActionPerformed

    private void startClocks() {
        clockThread = new Thread(() -> {
            while(true) {
                try {
                    LocalTime now = LocalTime.now(ZoneId.of("UTC+03:00"));
                    final int hours = now.getHour();
                    final int minutes = now.getMinute();
                    final int seconds = now.getSecond();
                    clockLabel.setText(String.format("%02d:%02d", hours, minutes));
                    Thread.sleep(60 - seconds);
                } catch(InterruptedException ex) {
                    break;
                }
            }
        });
        clockThread.start();
    }
    
    public void setTrayIcon(TrayIcon trayIcon) {
        this.trayIcon = trayIcon;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> breakDurationCombo;
    private javax.swing.JLabel bunchCounterLabel;
    private javax.swing.JComboBox<String> bunchSizeCombo;
    private javax.swing.JLabel clockLabel;
    private javax.swing.JPanel helpPanel;
    private javax.swing.JButton heyIGiveUpButton;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JCheckBox needBeepOnBreakStart;
    private javax.swing.JCheckBox needBeepOnBunchFinish;
    private javax.swing.JCheckBox needBeepOnTomatoStart;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JButton startButton;
    private javax.swing.JLabel timeCounterTypeLabel;
    private javax.swing.JLabel timeValueLabel;
    private javax.swing.JPanel timerPanel;
    private javax.swing.JComboBox<String> tomatoDurationCombo;
    private javax.swing.JButton unpredictedPauseButton;
    // End of variables declaration//GEN-END:variables

    private TomatoEngine te = null;
    private Thread clockThread = null;
    private TrayIcon trayIcon = null;
}

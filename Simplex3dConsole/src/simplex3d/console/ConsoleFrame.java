/*
 * Simplex3dConsole
 * Copyright (C) 2011, Aleksey Nikiforov
 *
 * This file is part of Simplex3dConsole.
 *
 * Simplex3dConsole is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Simplex3dConsole is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package simplex3d.console;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


/**
 * @author Aleksey Nikiforov (lex)
 */
public class ConsoleFrame extends javax.swing.JFrame {

    
    public ConsoleFrame() {
        initComponents();

        javax.swing.GroupLayout layout = (javax.swing.GroupLayout) getContentPane().getLayout();
        ConsolePanel consolePanel = new ConsolePanel();
        layout.replace(this.consolePanel, consolePanel);
        this.consolePanel = consolePanel;

        runMenuItem.setAction(consolePanel.getRunAction());
        resetInterpreterMenuItem.setAction(consolePanel.getResetInterpreterAction());

        Examples.populateMenus(consolePanel.getTextComponent(), scalaExamples, simplex3dExamples);
    }

    public ConsolePanel getConsolePanel() {
        return ((ConsolePanel) consolePanel);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        consolePanel = new javax.swing.JPanel();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        runMenuItem = new javax.swing.JMenuItem();
        resetInterpreterMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        scalaExamples = new javax.swing.JMenu();
        simplex3dExamples = new javax.swing.JMenu();
        settingsMenu = new javax.swing.JMenu();
        sandboxMenuItem = new javax.swing.JCheckBoxMenuItem();
        helpMenu = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simplex3D Console");

        javax.swing.GroupLayout consolePanelLayout = new javax.swing.GroupLayout(consolePanel);
        consolePanel.setLayout(consolePanelLayout);
        consolePanelLayout.setHorizontalGroup(
            consolePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 670, Short.MAX_VALUE)
        );
        consolePanelLayout.setVerticalGroup(
            consolePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 623, Short.MAX_VALUE)
        );

        fileMenu.setMnemonic('F');
        fileMenu.setText("File");

        runMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.CTRL_MASK));
        runMenuItem.setMnemonic('R');
        runMenuItem.setText("Run");
        fileMenu.add(runMenuItem);

        resetInterpreterMenuItem.setMnemonic('I');
        resetInterpreterMenuItem.setText("Reset Interpreter");
        fileMenu.add(resetInterpreterMenuItem);

        exitMenuItem.setMnemonic('X');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        mainMenuBar.add(fileMenu);

        scalaExamples.setMnemonic('E');
        scalaExamples.setText("  Scala Examples ");
        mainMenuBar.add(scalaExamples);

        simplex3dExamples.setMnemonic('X');
        simplex3dExamples.setText(" Simplex3D Examples  ");
        mainMenuBar.add(simplex3dExamples);

        settingsMenu.setMnemonic('S');
        settingsMenu.setText("Settings");

        sandboxMenuItem.setSelected(true);
        sandboxMenuItem.setText("Sandbox");
        sandboxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sandboxMenuItemActionPerformed(evt);
            }
        });
        settingsMenu.add(sandboxMenuItem);

        mainMenuBar.add(settingsMenu);

        helpMenu.setMnemonic('H');
        helpMenu.setText("Help");

        aboutMenuItem.setMnemonic('A');
        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        mainMenuBar.add(helpMenu);

        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(consolePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(consolePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        this.dispose();
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        JOptionPane.showMessageDialog(this,
            "Interactive Scala Console,\ndeveloped for Simplex3D Project.\nhttp://www.simplex3d.org"
        );
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void sandboxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sandboxMenuItemActionPerformed
        ((ConsolePanel) consolePanel).setSandboxEnabled(sandboxMenuItem.isSelected());
        sandboxMenuItem.setSelected(System.getSecurityManager() != null);
    }//GEN-LAST:event_sandboxMenuItemActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {}

        
        final Splash splash = Splash.showSplash();

        splash.setStatusText("rebuilding jars");
        Utils.resolveDeps();

        splash.setStatusText("preloading the interpreter");
        final SimplexInterpreter interpreter = new SimplexInterpreter();

        splash.setStatusText("launching the application");
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ConsoleFrame main = new ConsoleFrame();
                    main.getConsolePanel().setInterpreter(interpreter);
                    splash.dispose();
                    positionMiddle(main);
                    main.setVisible(true);
                    main.getConsolePanel().takeFocus();
                }
                catch (Throwable t) {
                    String errorStr = t.toString();
                    for (StackTraceElement st : t.getStackTrace()) {
                        errorStr += "\n" + st.toString();
                    }
                    JOptionPane.showMessageDialog(null, errorStr, "Error!", JOptionPane.ERROR_MESSAGE);
                    System.exit(-1);
                }
            }
        });
    }

    static void positionMiddle(JFrame frame) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = dim.width/2 - frame.getWidth()/2;
        int y = dim.height/2 - frame.getHeight()/2;
        frame.setLocation(new Point(x, y));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JPanel consolePanel;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenuItem resetInterpreterMenuItem;
    private javax.swing.JMenuItem runMenuItem;
    private javax.swing.JCheckBoxMenuItem sandboxMenuItem;
    private javax.swing.JMenu scalaExamples;
    private javax.swing.JMenu settingsMenu;
    private javax.swing.JMenu simplex3dExamples;
    // End of variables declaration//GEN-END:variables
}

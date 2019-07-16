
package scilatex.View.Components;

import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import javax.swing.JDialog;
import static scilatex.Data.Constants.Constants.BORDER_COLOR;
import static scilatex.Data.Constants.Constants.COLOR_TEXT;
import static scilatex.Data.Constants.Constants.TEXT_FONT;

// Clase que permite el ingreso de un valor a través de un campo de texto
public class InsertValue extends javax.swing.JPanel {

    // Componentes graficos
    private final JDialog jDialog;      // Ventana para ingresar un valor
    private String value;               // Valor que se ingrsa en el campo de texto
    
    // Iniciar ventana
    public InsertValue(JComponent parent, String title, String text) {
        initComponents();
        
        jLabel35.setText(text);
        jDialog = new JDialog();
        jDialog.setTitle(title);
        jDialog.setContentPane(this);
        jDialog.setSize(this.getPreferredSize().width, this.getPreferredSize().height);
        jDialog.setResizable(false);
        jDialog.setUndecorated(true);
        jDialog.setLocationRelativeTo(parent);
        jDialog.setModal(true);
        jDialog.setVisible(true);
        
    }

    // Obtener campo ingresado
    public String getField() {
        return value;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        changeValuePanel = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField14 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();

        changeValuePanel.setBackground(new java.awt.Color(255, 255, 255));
        changeValuePanel.setBorder(new javax.swing.border.LineBorder(BORDER_COLOR, 2, true));
        changeValuePanel.setMinimumSize(new java.awt.Dimension(320, 400));
        changeValuePanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                changeValuePanelMouseDragged(evt);
            }
        });
        changeValuePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                changeValuePanelMousePressed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(TEXT_FONT);
        jButton3.setForeground(COLOR_TEXT);
        jButton3.setText("Aceptar");
        jButton3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 203, 255), 2, true));
        jButton3.setContentAreaFilled(false);
        jButton3.setOpaque(true);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(TEXT_FONT);
        jButton4.setForeground(COLOR_TEXT);
        jButton4.setText("Cancelar");
        jButton4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 203, 255), 2, true));
        jButton4.setContentAreaFilled(false);
        jButton4.setOpaque(true);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jTextField14.setFont(TEXT_FONT);
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField14.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jTextField14.setOpaque(false);
        jTextField14.setPreferredSize(new java.awt.Dimension(6, 15));
        jTextField14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField14FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField14FocusLost(evt);
            }
        });
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField14KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField14KeyReleased(evt);
            }
        });

        jLabel35.setFont(TEXT_FONT);
        jLabel35.setForeground(COLOR_TEXT);
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("Texto");

        javax.swing.GroupLayout changeValuePanelLayout = new javax.swing.GroupLayout(changeValuePanel);
        changeValuePanel.setLayout(changeValuePanelLayout);
        changeValuePanelLayout.setHorizontalGroup(
            changeValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeValuePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(changeValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(changeValuePanelLayout.createSequentialGroup()
                        .addGroup(changeValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(changeValuePanelLayout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 177, Short.MAX_VALUE))))
        );
        changeValuePanelLayout.setVerticalGroup(
            changeValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changeValuePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(changeValuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(changeValuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(changeValuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        jDialog.setVisible(false);
        value = jTextField14.getText();
        jDialog.setModal(false);
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        jDialog.setVisible(false);
        jDialog.setModal(false);
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField14FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField14FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14FocusGained

    private void jTextField14FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField14FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14FocusLost

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jTextField14KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14KeyReleased

    private void changeValuePanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeValuePanelMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_changeValuePanelMouseDragged

    private void changeValuePanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changeValuePanelMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_changeValuePanelMousePressed

    private void jTextField14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyPressed
        
        // Si se presionó Enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            
            jDialog.setVisible(false);
            value = jTextField14.getText();
            jDialog.setModal(false);

            
        }
        
    }//GEN-LAST:event_jTextField14KeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel changeValuePanel;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    public javax.swing.JLabel jLabel35;
    public javax.swing.JTextField jTextField14;
    // End of variables declaration//GEN-END:variables
}

package proyectozapateria;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class venta extends javax.swing.JFrame {

    /** Creates new form venta */
    public venta() {
        initComponents();
        bloquearBotonesInicio();
        BloquearTexto();
    }

    public void cargarClientes(String txtp) {
        Conexion cn = new Conexion();
        Connection cc = (Connection) cn.conectar();
        String sql = "";
        sql = "Select ID_CLIENTE from cliente";
        try {
            Statement psd = (Statement) cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);

            while (rs.next()) {
                jComboBoxCliente.addItem(rs.getString("ID_CLIENTE").trim());
            }

            jComboBoxCliente.setSelectedItem(txtp.trim());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar las Placas");
        }
    }

    public void cargarVendedor(String txtp) {
        Conexion cn = new Conexion();
        Connection cc = (Connection) cn.conectar();
        String sql = "";
        sql = "Select ID_VENDEDOR from vendedor";
        try {
            Statement psd = (Statement) cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);

            while (rs.next()) {
                jComboBoxCliente.addItem(rs.getString("ID_VENDEDOR").trim());
            }

            jComboBoxCliente.setSelectedItem(txtp.trim());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar las Vendedor");
        }
    }

    public void bloquearBotonesInicio() {
        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
    }

    public void cargarProducto(String txtp) {
        Conexion cn = new Conexion();
        Connection cc = (Connection) cn.conectar();
        String sql = "";
        sql = "Select PRO_ID from productos";
        try {
            Statement psd = (Statement) cc.createStatement();
            ResultSet rs = psd.executeQuery(sql);

            while (rs.next()) {
                jComboBoxProducto.addItem(rs.getString("PRO_ID").trim());
            }

            jComboBoxProducto.setSelectedItem(txtp.trim());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo cargar las Productos");
        }
    }

    public void limpiarTexto() {
        jTextFieldID.setText("");
        jTextFieldFecha.setText("");
        jTextFieldValor.setText("");

    }

    public void BloquearTexto() {
        jTextFieldID.setEnabled(false);
        jTextFieldFecha.setEnabled(false);
        jTextFieldValor.setEnabled(false);
        jComboBoxCliente.setEnabled(false);
        jComboBoxVendedor.setEnabled(false);
        jComboBoxProducto.setEnabled(false);
    }

    public void Texto() {
        jTextFieldID.setEnabled(true);
        jTextFieldFecha.setEnabled(true);
        jTextFieldValor.setEnabled(true);
        jComboBoxCliente.setEnabled(true);
        jComboBoxVendedor.setEnabled(true);
        jComboBoxProducto.setEnabled(true);
    }

    public void bloquearBotonesNuevo() {
        btnNuevo.setEnabled(false);
        btnGuardar.setEnabled(true);
    }

    public void GuardarProducto() {

        if (jTextFieldID.getText().isEmpty()) {

            JOptionPane.showMessageDialog(null, "Ingrese la ID");
            jTextFieldID.requestFocus(true);
        } else if (jTextFieldFecha.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la Fecha");
            jTextFieldFecha.requestFocus(true);
        } else if (jTextFieldValor.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la Valor");
            jTextFieldValor.requestFocus(true);
        } else {
            String ID_VENTA, VEN_FECHA, VEN_VALOR, ID_CLIENTE, ID_VENDEDOR;

            ID_VENTA = jTextFieldID.getText();
            VEN_FECHA = jTextFieldFecha.getText();
            VEN_VALOR = jTextFieldValor.getText();

            Conexion cc = new Conexion();
            Connection cn = (Connection) cc.conectar();
            String sql = "";
            sql = "insert into productos (ID_VENTA, VEN_FECHA, VEN_VALOR, ID_CLIENTE, ID_VENDEDOR) values (?,?,?,?,?) ";
            try {
                java.sql.PreparedStatement psd = cn.prepareStatement(sql);
                psd.setString(1, ID_VENTA);
                psd.setString(2, VEN_FECHA);
                psd.setString(3, VEN_VALOR);


                int n = psd.executeUpdate();

                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Se Inserto el dato correctamente");
                    limpiarTexto();
                    BloquearTexto();
                    bloquearBotonesInicio();

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Se Inserto el dato correctamente");
            }

        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanelProducto = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldID = new javax.swing.JTextField();
        jTextFieldFecha = new javax.swing.JTextField();
        jComboBoxCliente = new javax.swing.JComboBox();
        jTextFieldValor = new javax.swing.JTextField();
        jComboBoxVendedor = new javax.swing.JComboBox();
        jComboBoxProducto = new javax.swing.JComboBox();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        jLabel1.setText("jLabel1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("ID:");

        jLabel3.setText("Fecha:");

        jLabel4.setText("Valor:");

        jLabel5.setText("ID_Cliente:");

        jLabel6.setText("ID_Vendedor:");

        jLabel7.setText("ID_Producto:");

        jTextFieldValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldValorActionPerformed(evt);
            }
        });
        jTextFieldValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldValorKeyTyped(evt);
            }
        });

        jComboBoxVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVendedorActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelProductoLayout = new javax.swing.GroupLayout(jPanelProducto);
        jPanelProducto.setLayout(jPanelProductoLayout);
        jPanelProductoLayout.setHorizontalGroup(
            jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductoLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(21, 21, 21)
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxVendedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldValor)
                    .addComponent(jComboBoxCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldFecha)
                    .addComponent(jTextFieldID, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanelProductoLayout.setVerticalGroup(
            jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProductoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNuevo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelProductoLayout.createSequentialGroup()
                        .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addComponent(jComboBoxCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "# Venta", "Producto", "Cantidad", "Valor"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(7, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 168, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(268, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jTextFieldValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldValorActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jTextFieldValorActionPerformed

private void jComboBoxVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxVendedorActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jComboBoxVendedorActionPerformed

private void jTextFieldValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValorKeyTyped
// TODO add your handling code here:
    solonumeros(evt);
}//GEN-LAST:event_jTextFieldValorKeyTyped

private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
// TODO add your handling code here:
    bloquearBotonesNuevo();
    Texto();
}//GEN-LAST:event_btnNuevoActionPerformed

private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
// TODO add your handling code here:
    GuardarProducto();
}//GEN-LAST:event_btnGuardarActionPerformed
    public void solonumeros(KeyEvent evt) {
        char c = evt.getKeyChar();


        if (Character.isLetter(c)) {

            evt.consume();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(venta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new venta().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox jComboBoxCliente;
    private javax.swing.JComboBox jComboBoxProducto;
    private javax.swing.JComboBox jComboBoxVendedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelProducto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextFieldFecha;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldValor;
    // End of variables declaration//GEN-END:variables
}

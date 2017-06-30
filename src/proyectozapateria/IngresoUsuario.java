/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectozapateria;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.event.KeyEvent;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author G Pro
 */
public class IngresoUsuario extends javax.swing.JInternalFrame {

    /**
     * Creates new form IngresoUsuario
     */
    DefaultTableModel modelo;
    public IngresoUsuario() {

        initComponents();
        
        bloquearBotonesInicio();
        bloquearTextoInicio();
        crearTablaUsuario("");
        
        
        tblUsuario.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                //la fila getseleccionrow
                int fila = tblUsuario.getSelectedRow();
                //5.2
                if (fila != -1) {
                    try {
                        //getSeleccionRow 

                        txtUsuario.setText(tblUsuario.getValueAt(fila, 0).toString());
                        Incriptar_Desencriptar i=new Incriptar_Desencriptar();
  String n =i.Desencriptar(tblUsuario.getValueAt(fila, 1).toString());
txtClave.setText(n);

                       // txtClave.setText(tblUsuario.getValueAt(fila, 1).toString());
                        txtNombre.setText(tblUsuario.getValueAt(fila, 2).toString());
                        txtPerfil.setText(tblUsuario.getValueAt(fila, 3).toString());
                      
    //            
                        txtObservacion.setText(tblUsuario.getValueAt(fila, 4).toString());
                        txtUsuario.setEnabled(false);
                        bloquearBotonesInicio();
                        DesbloquearTexto();
                        bloquearBotonUpdate();
    //                    btnEliminarUsuario.setEnabled(true);
                        txtUsuario.setEditable(false);
                    } catch (Exception ex) {
                        Logger.getLogger(IngresoUsuario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });


    }

    public void bloquearTextoInicio() {
        txtClave.setEnabled(false);

        txtNombre.setEnabled(false);
        txtObservacion.setEnabled(false);
        txtPerfil.setEnabled(false);
        txtUsuario.setEnabled(false);
    }

    public void bloquearBotonesInicio() {
        btnActualizarUsuario.setEnabled(false);
        btnGuardarUsuario.setEnabled(false);
        btnCancelarUsuario.setEnabled(false);
//        btnEliminarUsuario.setEnabled(false);
        btnNuevo.setEnabled(true);
        btnSalir.setEnabled(true);
    }

    public void DesbloquearNuevo() {
        btnActualizarUsuario.setEnabled(true);
        btnGuardarUsuario.setEnabled(true);
        btnCancelarUsuario.setEnabled(false);
   //     btnEliminarUsuario.setEnabled(true);
        btnNuevo.setEnabled(false);

        txtClave.setEnabled(true);

        txtNombre.setEnabled(true);
        txtObservacion.setEnabled(true);
        txtPerfil.setEnabled(true);
        txtUsuario.setEnabled(true);
    }

    public void bloquearTexto() {

        txtClave.setEnabled(false);
        txtNombre.setEnabled(false);
        txtPerfil.setEnabled(false);
        txtUsuario.setEnabled(false);
//        txtAn.setEnabled(false);
        txtObservacion.setEnabled(false);
    }

    public void limpiarTexto() {
        txtClave.setText("");
        txtNombre.setText("");

        txtUsuario.setText("");
        txtPerfil.setText("");
        txtObservacion.setText("");
    }

    public void DesbloquearTexto() {
        txtClave.setEnabled(true);
        txtUsuario.setEnabled(true);
        txtNombre.setEnabled(true);
        txtPerfil.setEnabled(true);
//        txtAn.setEnabled(true);
        txtObservacion.setEnabled(true);
    }
public void bloquearBotonUpdate() {
        btnNuevo.setEnabled(false);
        btnGuardarUsuario.setEnabled(false);
        btnActualizarUsuario.setEnabled(true);
        btnCancelarUsuario.setEnabled(false);
//        btnEliminarUsuario.setEnabled(false);
        btnSalir.setEnabled(true);
    }

    public void GuardarUsuario() throws Exception {

        if (txtUsuario.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Usuario");
            txtUsuario.requestFocus(true);
        } else if (txtClave.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Ingrese la Clave");
            txtClave.requestFocus(true);
        } else if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre");
            txtNombre.requestFocus(true);

        } else if (txtPerfil.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Perfil ");
            txtPerfil.requestFocus(true);
        } // else if (txtAn.getText().isEmpty() ) {
        //  JOptionPane.showMessageDialog(null, "Ingrese la Año");
        //  txtAn.requestFocus(true);
        // }
        else if (txtObservacion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la Observacion");
            txtObservacion.requestFocus(true);
        } else {
            String USU_USUARIO, USU_LOGIN, USU_NOMBRE, USU_PERFIL, USU_OBSERVACION;
            String v =txtClave.getText();
            Incriptar_Desencriptar c= new Incriptar_Desencriptar();
            
            String clave =c.Encriptar(v);

            USU_USUARIO = txtUsuario.getText();
            USU_LOGIN = clave;
            System.out.println(clave);
            USU_NOMBRE = txtNombre.getText();
            USU_PERFIL = txtPerfil.getText();

            USU_OBSERVACION = txtObservacion.getText();

            Conexion cc = new Conexion();
            Connection cn = (Connection) cc.conectar();
            String sql = "";
            sql = "insert into usuario (USU_USUARIO, USU_LOGIN, USU_NOMBRE,USU_PERFIL, USU_OBSERVACION) values (?,?,?,?,?) ";
            try {
                java.sql.PreparedStatement psd = cn.prepareStatement(sql);



                psd.setString(1, USU_USUARIO);
                psd.setString(2, USU_LOGIN);
                psd.setString(3, USU_NOMBRE);
                psd.setString(4, USU_PERFIL);
                psd.setString(5, USU_OBSERVACION);



                int n = psd.executeUpdate();

                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Se Inserto el dato correctamente");
                    limpiarTexto();
                    bloquearTexto();
                    bloquearBotonesInicio();



                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Se Inserto el dato correctamente");
            }

        }

    }

    public void verificarDatos() {
        String id = txtUsuario.getText();
        Conexion cc = new Conexion();
        Connection cn = (Connection) cc.conectar();
        try {
            Statement consulta = cn.createStatement();
            ResultSet resultado = consulta.executeQuery("Select * from usuario where usuario.USU_USUARIO like '" + id + "'");
            if (!resultado.next()) {
                //  control();
                GuardarUsuario();
            } else {
                JOptionPane.showMessageDialog(null, "el usuario ya existe");
            }


        } catch (Exception ex) {
        }}
    

    public void crearTablaUsuario(String Dato) {
         String[] titulos = {"USUARIO", "CONTRASEÑA", "NOMBRE", "PERFIL", "OBSERVACION"};
        //2.2 cargar con el numero de campos que tengo
        String[] registros = new String[5];
        //global para la clase el modelo 1.1
        //instancio 1.1
  modelo = new DefaultTableModel(null, titulos);



        //conexion PARA CARGAR MI BD
        Conexion cc = new Conexion();
        Connection cn = (Connection) cc.conectar();

        String sql = "";


        //aumente where para buscar

        //  sql = "Select * from autos where AUT_PLACA ='"+TXT,GETTEXT()+"'"; 
        //3.1 dato mando en el select 
        //3.2 LIKE 
        sql = "Select * from usuario where USU_USUARIO LIKE '%" + Dato + "%'"; //fila por fila a continuacion de otra se debe aplicar resultset

        try {
            //hacer el statement un normal porque solo selecciono 
            Statement psd = cn.createStatement();

            //2.3 necesito una fila separada por fila debo hacer Resulset= maneja el resultado por filas
            //para eso se maneja :
            ResultSet rs = psd.executeQuery(sql); //son clases propias 
            //2.4 para manejar debo trabajar en un ciclo 
            while (rs.next()) { //2.5 mientras haya una fila siguiente
                registros[0] = rs.getString("USU_USUARIO"); // 2.6 REGISTROS ES EL VECTOR Y COLOCA CAA¡DA UNO 
                registros[1] = rs.getString("USU_LOGIN");
                registros[2] = rs.getString("USU_NOMBRE");
                registros[3] = rs.getString("USU_PERFIL");
                registros[4] = rs.getString("USU_OBSERVACION");
                //2.5 AGREGAR ESTO A MI TABLA
                modelo.addRow(registros);

            }

            //llenar model9o
            tblUsuario.setModel(modelo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void modificar() {
        if (txtClave.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la Clave");
            txtClave.requestFocus();

        } else if (txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Nombre");
            txtNombre.requestFocus();
        } else if (txtPerfil.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el Perfil");
            txtPerfil.requestFocus();
        } else if (txtObservacion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la Observacion");
            txtObservacion.requestFocus();
        } else {
            Conexion cc = new Conexion();
            Connection cn = (Connection) cc.conectar();
            String sql = "";
            Incriptar_Desencriptar i=new Incriptar_Desencriptar();
            String g= i.Encriptar(txtClave.getText());
            sql = "update usuario set USU_NOMBRE='" + txtNombre.getText() + "',"
                    + "USU_LOGIN='" + g+ "',"
                    + "USU_PERFIL='" + txtPerfil.getText() + "',"
                    + "USU_OBSERVACION='" + txtObservacion.getText() + "' "
                    + "where USU_USUARIO ='" + txtUsuario.getText() + "'";
            bloquearBotonesInicio();
            limpiarTexto();
         
            try {

                PreparedStatement psd = (PreparedStatement) cn.prepareStatement(sql);
                int n = psd.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Se actualizo Correctamente");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
  

    //NOTA SI NO INSERTA EL DATO PUEDO ENVIAR EL CAMPO SIN DATO COMO MENSAJE
    public void controlSoloLetras(KeyEvent evt) {
        if (evt.getKeyChar() < 65 || evt.getKeyChar() > 90 && evt.getKeyChar() < 97 || evt.getKeyChar() > 122 && evt.getKeyChar() == 32) {
            evt.consume();
        }
    }

    public void controlSoloNumeros(KeyEvent evt) {
        if (evt.getKeyChar() < 48 || evt.getKeyChar() > 57) {
            evt.consume();
        }
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtClave = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtPerfil = new javax.swing.JTextField();
        txtObservacion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnGuardarUsuario = new javax.swing.JButton();
        btnActualizarUsuario = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnCancelarUsuario = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblUsuario = new javax.swing.JTable();

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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("REGISTRO USUARIO");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPerfilActionPerformed(evt);
            }
        });

        txtObservacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtObservacionKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel4.setText("Observación :");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel3.setText("Perfil :");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel5.setText("Nombre :");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel2.setText("Contraseña :");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel1.setText("Usuario :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre)
                    .addComponent(txtObservacion)
                    .addComponent(txtUsuario)
                    .addComponent(txtPerfil)
                    .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel3)
                        .addGap(17, 17, 17)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtObservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGuardarUsuario.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnGuardarUsuario.setText("Guardar");
        btnGuardarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarUsuarioActionPerformed(evt);
            }
        });

        btnActualizarUsuario.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnActualizarUsuario.setText("Actualizar");
        btnActualizarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarUsuarioActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnCancelarUsuario.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnCancelarUsuario.setText("Cancelar");

        btnNuevo.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnActualizarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGuardarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardarUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnActualizarUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSalir)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblUsuario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

        DesbloquearTexto();
        DesbloquearNuevo();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnGuardarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarUsuarioActionPerformed

        verificarDatos();
        crearTablaUsuario("");

        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuardarUsuarioActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        controlSoloLetras(evt);
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPerfilActionPerformed

        controlSoloLetras(null);// TODO add your handling code here:
    }//GEN-LAST:event_txtPerfilActionPerformed

    private void txtObservacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtObservacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacionKeyTyped

    private void btnActualizarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarUsuarioActionPerformed

        modificar();
           crearTablaUsuario("");
        // TODO add your handling code here:
    }//GEN-LAST:event_btnActualizarUsuarioActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
dispose();
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         *
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IngresoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IngresoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IngresoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IngresoUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new IngresoUsuario().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarUsuario;
    private javax.swing.JButton btnCancelarUsuario;
    private javax.swing.JButton btnGuardarUsuario;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable tblUsuario;
    private javax.swing.JTextField txtClave;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtObservacion;
    private javax.swing.JTextField txtPerfil;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}

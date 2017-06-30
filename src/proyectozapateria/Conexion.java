/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectozapateria;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author PC08_Lab06_Inv
 */
public class Conexion {
     Connection connect = null;

    public Connection conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connect = DriverManager.getConnection("jdbc:mysql://localhost/ventas", "root", "");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Fallo conexión Inténtalo de nuevo más tarde.  ");
        }
        return connect;
    }
    public void escribir(String tabla, String[] codigos, String[] campos) {
        String codigos_sal = "";
        String campos_sal = "";
        String num_val = "";
        String inser = "";
        for (int i = 0; i < codigos.length; i++) {

            if (i != codigos.length - 1) {
                codigos_sal += codigos[i] + ", ";
                num_val += "?, ";
            } else {
                num_val += "?";
                codigos_sal += codigos[i];
            }
        }

        inser = "INSERT INTO " + tabla + "(" + codigos_sal + ")" + " VALUES(" + num_val + ")";
        try {
            PreparedStatement INGRESO = connect.prepareStatement(inser);
            for (int i = 0; i < campos.length; i++) {
                INGRESO.setString(i + 1, campos[i]);
            }
            int a = INGRESO.executeUpdate();
            if (a > 0) {
                JOptionPane.showMessageDialog(null, "Se Inserto el dato correctamente");
            }
            INGRESO.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error sql "+e);
            JOptionPane.showMessageDialog(null,"ingreso fallido");
        }

    }
}

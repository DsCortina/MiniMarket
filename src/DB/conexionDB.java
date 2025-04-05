package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionDB {

    Connection conectar = null;

    String usuario = "root";
    String contrasena = "";
    String bd = "marketdb";
    String ip = "localhost";
    String puerto = "3306";

    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    public Connection estableceConexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, usuario, contrasena);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error creando conexión, error: " + e.getMessage());
        }
        return conectar;
    }

    public void cierraConexion() {
        if (conectar != null) { 
            try {
                conectar.close(); 
                System.out.println("Conexión cerrada exitosamente.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        } else {
            System.out.println("No hay conexión abierta para cerrar.");
        }
    }
}

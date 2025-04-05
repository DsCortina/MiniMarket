package DB.DBCliente;

import java.sql.*;
import DB.conexionDB;
import modelo.cliente.Cliente;
import modelo.cliente.DtoCliente;

public class ConsultasCliente {

    public ConsultasCliente() {
    }

    public DtoCliente crearCliente(Cliente cliente) {

        //OBJS
        conexionDB conexion = new conexionDB();
        DtoCliente dtoCl = new DtoCliente();

        //Sentencia SQL
        String consulta = "INSERT INTO clientes (nombres_cliente,documento_cliente,telefono_cliente,correo_cliente) values (?,?,?,?)";

        try {
            CallableStatement cs = conexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, cliente.getNombres());
            cs.setString(2, cliente.getDocumento());
            cs.setString(3, cliente.getTelefono());
            cs.setString(4, cliente.getCorreoElectronico());
            cs.execute();

            dtoCl.setStatus("Ok");
            dtoCl.setMensaje("Se creo el cliente exitosamente");

        } catch (Exception e) {
            dtoCl.setStatus("Error");
            dtoCl.setMensaje("Error al generar al insertar en BD, error: " + e.getMessage());
        }

        return dtoCl;
    }

    public DtoCliente ConsultarCliente(Cliente cliente) {

        conexionDB conexion = new conexionDB();
        Cliente clientx = null;
        DtoCliente dtoCl = new DtoCliente();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from clientes WHERE documento_cliente = ?";

        try {
            
            ps = conexion.estableceConexion().prepareStatement(sql);
            ps.setString(1, cliente.getDocumento());
            rs = ps.executeQuery();
  
            while (rs.next()) {
                clientx = new Cliente();
                clientx.setId(rs.getInt(1));
                clientx.setNombres(rs.getString(2));
                clientx.setDocumento(rs.getString(3));
                clientx.setTelefono(rs.getString(4));
                clientx.setCorreoElectronico(rs.getString(5));
            }
            if (clientx != null) {
                dtoCl.setStatus("Ok");
                dtoCl.setMensaje("Se consulto el cliente correctamente");
                dtoCl.setCliente(clientx);
            } else {
                dtoCl.setStatus("Error");
                dtoCl.setMensaje("No se encontró el cliente solicitado");
            }
        } catch (Exception e) {
            dtoCl.setStatus("Error");
            dtoCl.setMensaje("Error al buscar en BD, error: " + e.getMessage());
        }

        return dtoCl;
    }

    public DtoCliente ActualizarCliente(Cliente cliente) {

        conexionDB Conexion = new conexionDB();
        DtoCliente dtoCl = new DtoCliente();

        String sql = "update clientes SET nombres_cliente =? ,documento_cliente =? ,telefono_cliente=? WHERE documento_cliente =?";

        PreparedStatement pst;

        try {
            pst = Conexion.estableceConexion().prepareStatement(sql);

            pst.setString(1, cliente.getNombres());
            pst.setString(2, cliente.getDocumento());
            pst.setString(3, cliente.getTelefono());
            pst.setString(4, cliente.getCorreoElectronico());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                dtoCl.setStatus("Ok");
                dtoCl.setMensaje("Se actuliazo el cliente correctamente");
            } else {
                dtoCl.setStatus("Error");
                dtoCl.setMensaje("No se actualizo el cliente, intentelo de nuevo");
            }
        } catch (SQLException e) {
            dtoCl.setStatus("Error");
            dtoCl.setMensaje("Error en BD, error: " + e.getMessage());
        }
        return dtoCl;
    }

    public DtoCliente eliminarCliente(Cliente cliente) {

        conexionDB Conexion = new conexionDB();
        DtoCliente dtoCl = new DtoCliente();

        String desactivar = "SET SQL_SAFE_UPDATES = 0";
        String activar = "SET SQL_SAFE_UPDATES = 1";
        String sql = "DELETE FROM clientes WHERE documento_cliente =?";
        PreparedStatement pst;

        try {
            pst = Conexion.estableceConexion().prepareStatement(desactivar);
            pst.executeUpdate();

            pst = Conexion.estableceConexion().prepareStatement(sql);
            pst.setString(1, cliente.getDocumento());
            int rowsAffected = pst.executeUpdate();

            pst = Conexion.estableceConexion().prepareStatement(activar);
            pst.executeUpdate();

            if (rowsAffected > 0) {
                dtoCl.setStatus("Ok");
                dtoCl.setMensaje("Se Factuliazo el cliente correctamente");
            } else {
                dtoCl.setStatus("Error");
                dtoCl.setMensaje("No se encontró el usuario con el documento proporcionado");
            }
        } catch (SQLException e) {
            dtoCl.setStatus("Error");
            dtoCl.setMensaje("Error en BD, error: " + e.getMessage());
        }
        return dtoCl;
    }
}

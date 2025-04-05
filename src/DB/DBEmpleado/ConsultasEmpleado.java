package DB.DBEmpleado;

import java.sql.*;
import DB.conexionDB;
import modelo.empleado.Empleado;
import modelo.empleado.DtoEmpleado;

public class ConsultasEmpleado {

    public ConsultasEmpleado() {
    }

    public DtoEmpleado crearEmpleado(Empleado empleado) {

        //Objetos
        conexionDB Conexion = new conexionDB();
        DtoEmpleado dto = new DtoEmpleado();

        //Sentencia SQL
        String sql = "INSERT INTO empleados (nombre_empleado,documento_empleado,correo_empleado) values (?,?,?)";

        try {
            PreparedStatement ps = Conexion.estableceConexion().prepareStatement(sql);
            ps.setString(1, empleado.getNombreCompleto());
            ps.setString(2, empleado.getDocumento());
            ps.setString(3, empleado.getCorreoElectronico());
            ps.execute();

            dto.setStatus("Ok");
            dto.setMensaje("Se creo el empleado correctamente");

        } catch (SQLException e) {
            dto.setStatus("Error");
            dto.setMensaje("Error al generar al insertar en BD, error: " + e.getMessage());
        }
        return dto;
    }

    public DtoEmpleado consultaEmpleado(Empleado empleado) {

        conexionDB Conexion = new conexionDB();
        DtoEmpleado dto = new DtoEmpleado();
        Empleado empleade = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //Sentencia SQL
        String sql = "select * from empleados where documento_empleado= ?";

        try {
            ps = Conexion.estableceConexion().prepareStatement(sql);
            ps.setString(1, empleado.getDocumento());
            rs = ps.executeQuery();

            //Leo los resultados de mi consulta.
            while (rs.next()) {
                empleade = new Empleado();
                empleade.setId(rs.getInt(1));
                empleade.setNombreCompleto(rs.getString(2));
                empleade.setDocumento(rs.getString(3));
                empleade.setCorreoElectronico(rs.getString(4));
            }
            if (empleade != null) {
                dto.setStatus("Ok");
                dto.setMensaje("Se consulto el usuario correctamente");
                dto.setEmpleado(empleade);
            } else {
                dto.setStatus("Error");
                dto.setMensaje("No se encontró el usuario solicitado");
            }

        } catch (SQLException e) {
            dto.setStatus("Error");
            dto.setMensaje("Error al buscar en BD, error: " + e.getMessage());
        }
        return dto;
    }

    public DtoEmpleado consultaEmpLogin(Empleado empleado) {

        // Objetos
        conexionDB Conexion = new conexionDB();
        DtoEmpleado dto = new DtoEmpleado();
        Empleado empleade = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        // Sentencia SQL
        String sql = "SELECT * FROM empleados WHERE correo_empleado = ?";

        try {
            // Establece la conexión y prepara la consulta
            pst = Conexion.estableceConexion().prepareStatement(sql);
            pst.setString(1, empleado.getCorreoElectronico());

            // Ejecuta la consulta
            rs = pst.executeQuery();

            // Lee los resultados de la consulta
            if (rs.next()) {
                empleade = new Empleado();
                empleade.setId(rs.getInt("id_empleado"));
                empleade.setNombreCompleto(rs.getString("nombre_empleado"));
                empleade.setDocumento(rs.getString("documento_empleado"));
                empleade.setCorreoElectronico(rs.getString("correo_empleado"));
            }

            if (empleade != null) {
                dto.setStatus("Ok");
                dto.setMensaje("Se consultó el usuario correctamente");
                dto.setEmpleado(empleade);
            } else {
                dto.setStatus("Error");
                dto.setMensaje("No se encontró el usuario solicitado");
            }

        } catch (SQLException e) {
            dto.setStatus("Error");
            dto.setMensaje("Error al buscar en BD, error: " + e.getMessage());
        }

        return dto;
    }

    public DtoEmpleado ActulizarEmpleado(Empleado empleado) {

        //Objetos
        conexionDB Conexion = new conexionDB();
        DtoEmpleado dto = new DtoEmpleado();

        //Sentencia SQL
        String sql = "UPDATE empleados SET nombre_empleado= ?, correo_empleado =?  WHERE documento_empleado =?";

        // Statement
        PreparedStatement pst;

        try {
            //Preparo la consulta
            pst = Conexion.estableceConexion().prepareStatement(sql);
            //Se setean los datos que se van a enviar

            pst.setString(1, empleado.getNombreCompleto());
            pst.setString(2, empleado.getCorreoElectronico());
            pst.setString(3, empleado.getDocumento());
            // Ejecuto la consulta
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                dto.setStatus("Ok");
                dto.setMensaje("Se actuliazo el usuario correctamente");
            } else {
                dto.setStatus("Error");
                dto.setMensaje("No se encontró el usuario con el documento proporcionado");
            }
        } catch (SQLException e) {
            dto.setStatus("Error");
            dto.setMensaje("Error en BD, error: " + e.getMessage());
        }
        return dto;
    }

    public DtoEmpleado elimarEmpleado(Empleado empleado) {

        //Objetos
        conexionDB Conexion = new conexionDB();
        DtoEmpleado dto = new DtoEmpleado();

        //Sentencia SQL
        String sql = "DELETE FROM empleados WHERE documento_empleado =?";

        // Statement
        PreparedStatement pst;

        try {
            //Preparo la consulta
            pst = Conexion.estableceConexion().prepareStatement(sql);
            //Se setean los datos que se van a enviar

            pst.setString(1, empleado.getDocumento());
            // Ejecuto la consulta
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                dto.setStatus("Ok");
                dto.setMensaje("Se actuliazo el usuario correctamente");
            } else {
                dto.setStatus("Error");
                dto.setMensaje("No se encontró el usuario con el documento proporcionado");
            }
        } catch (SQLException e) {
            dto.setStatus("Error");
            dto.setMensaje("Error en BD, error: " + e.getMessage());
        }
        return dto;
    }
}

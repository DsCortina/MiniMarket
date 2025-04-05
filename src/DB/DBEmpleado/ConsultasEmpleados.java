package DB.DBEmpleado;

import java.sql.*;
import DB.conexionDB;
import java.util.List;
import java.util.ArrayList;
import modelo.empleado.Empleado;
import modelo.empleado.DtoListaEmpleados;

public class ConsultasEmpleados {

    public DtoListaEmpleados consultaEmpleado(Empleado empleado) {

        //Objetos
        conexionDB Conexion = new conexionDB();
        Empleado empleade;
        DtoListaEmpleados dtoLEmpl = new DtoListaEmpleados();
        List<Empleado> listaempl = new ArrayList<>();
        int i = 0;

        //Sentencia SQL
        String sql = "select nombre_empleado,documento_empleado,correo_empleado from empleados";

        // Statement
        Statement st;

        try {
            //Ejecuto mi consulta
            st = Conexion.estableceConexion().createStatement();
            //Obtengo los resultados de mi consulta
            ResultSet rs = st.executeQuery(sql);

            //Leo los resultados de mi consulta.
            while (rs.next()) {

                empleade = new Empleado();
                empleade.setNombreCompleto(rs.getString(1));
                empleade.setDocumento(rs.getString(2));
                empleade.setCorreoElectronico(rs.getString(3));

                //Lleno el array con el objeto/s
                listaempl.add(empleade);

                i++;
            }

            if (listaempl != null) {
                dtoLEmpl.setStatus("Ok");
                dtoLEmpl.setMensaje("Se consultaron los empleados correctamente");
                dtoLEmpl.setListaEmpleados(listaempl);
            } else {
                dtoLEmpl.setStatus("Error");
                dtoLEmpl.setMensaje("No se puedo acceder a los empleados");
            }

            System.out.println("Cantidad de empleados:" + i);

        } catch (SQLException e) {
            dtoLEmpl.setStatus("Error");
            dtoLEmpl.setMensaje("Error al generar al buscar en BD, error: " + e.getMessage());
        }
        return dtoLEmpl;
    }

}

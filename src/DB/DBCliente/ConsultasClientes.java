package DB.DBCliente;

import java.sql.*;
import DB.conexionDB;
import java.util.List;
import java.util.ArrayList;
import modelo.cliente.Cliente;
import modelo.cliente.DtoListaClientes;

public class ConsultasClientes {

    public DtoListaClientes ConsultaClientes(Cliente cliente) {

        //OBJS
        conexionDB conexion = new conexionDB();
        Cliente clientx;
        DtoListaClientes dtoLCls = new DtoListaClientes();
        List<Cliente> listaCls = new ArrayList<>();
        int i = 0;

        String sql = "select nombres_cliente,documento_cliente,telefono_cliente,correo_cliente from clientes";
        Statement st;

        try {
            st = conexion.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                clientx = new Cliente();
                clientx.setNombres(rs.getString(1));
                clientx.setDocumento(rs.getString(2));
                clientx.setTelefono(rs.getString(3));
                clientx.setCorreoElectronico(rs.getString(4));

                listaCls.add(clientx);
                i++;
            }
            if (listaCls != null) {
                dtoLCls.setStatus("Ok");
                dtoLCls.setMensaje("Se consultaron los clientes correctamente");
                dtoLCls.setCliente(listaCls);
            } else {
                dtoLCls.setStatus("Error");
                dtoLCls.setMensaje("No se puedo acceder a los clientes");
            }

            System.out.println("Cantidad de clientes:" + i);
        } catch (Exception e) {
            dtoLCls.setStatus("Error");
            dtoLCls.setMensaje("Error al generar al buscar en BD, error: " + e.getMessage());
        }

        return dtoLCls;
    }

}

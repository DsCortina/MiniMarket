package DB.DBFactura;

import java.sql.*;
import DB.conexionDB;
import java.util.List;
import java.util.ArrayList;
import modelo.factura.Factura;
import modelo.factura.DtoFactura;

public class ConsultaFactura {

    public DtoFactura crearFactura(Factura factura) {

        conexionDB conexion = new conexionDB();
        DtoFactura dtoFactura = new DtoFactura();
        Factura facturx = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "INSERT INTO facturas (id_cliente,fecha_factura,total_factura) Values (?,?,?)";

        try {
            st = conexion.estableceConexion().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, factura.getIdCliente());
            st.setString(2, factura.getFecha());
            st.setDouble(3, factura.getTotal());
            st.executeUpdate();
            rs = st.getGeneratedKeys();
            
            while (rs.next()) {     
                facturx = new Factura();
                facturx.setIdFactura(rs.getInt(1));
            }

            dtoFactura.setStatus("Ok");
            dtoFactura.setMensaje("Se creo la factura correctamente");
            dtoFactura.setFactura(facturx);

        } catch (Exception e) {
            dtoFactura.setStatus("Error");
            dtoFactura.setMensaje("Error al insertar en BD, error: " + e.getMessage());
        }

        return dtoFactura;
    }

    public DtoFactura consultarFacturas(Factura factura) {

        conexionDB conexion = new conexionDB();
        Factura facturx = null;
        DtoFactura dtoFactura = new DtoFactura();
        List<Factura> listaFacturas = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM facturas;";

        try {
            st = conexion.estableceConexion().prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                facturx = new Factura();
                facturx.setIdFactura(rs.getInt(1));
                facturx.setIdCliente(rs.getInt(2));
                facturx.setFecha(rs.getString(3));
                facturx.setTotal(rs.getDouble(4));
                listaFacturas.add(facturx);

                dtoFactura.setStatus("Ok");
                dtoFactura.setMensaje("Se consultaron las facturas correctamente");
            }

        } catch (Exception e) {
            dtoFactura.setStatus("Error");
            dtoFactura.setMensaje("Error al insertar en BD, error: " + e.getMessage());
        }

        return dtoFactura;
    }

}

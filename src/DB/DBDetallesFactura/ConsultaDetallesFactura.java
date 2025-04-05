package DB.DBDetallesFactura;

import DB.conexionDB;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import modelo.detallesfactura.DetallesFactura;
import modelo.detallesfactura.DtoDetallesFactura;
import modelo.factura.DtoFactura;
import modelo.factura.Factura;

public class ConsultaDetallesFactura {

    public DtoDetallesFactura crearDtllsFactura(DetallesFactura detallesFactura) {

        conexionDB conexion = new conexionDB();
        DtoDetallesFactura dtoDtllsFactura = new DtoDetallesFactura();

        String sql = "INSERT INTO detallesfacturas (id_factura,id_producto,cantidad,precio_unitario,subtotal) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement st = conexion.estableceConexion().prepareStatement(sql);
            st.setInt(1, detallesFactura.getIdFactura());
            st.setInt(2, detallesFactura.getIdProducto());
            st.setInt(3, detallesFactura.getCantidad());
            st.setDouble(4, detallesFactura.getValorIndv());
            st.setDouble(5, detallesFactura.getSubtotal());
            st.execute();

            dtoDtllsFactura.setStatus("Ok");
            dtoDtllsFactura.setMensaje("Se insertaron los detalles de factura correctamente");

        } catch (Exception e) {
            dtoDtllsFactura.setStatus("Error");
            dtoDtllsFactura.setMensaje("Error al insertar en BD, error: " + e.getMessage());
        }

        return dtoDtllsFactura;
    }

    public DtoFactura consultaProductos(DetallesFactura detallesFactura) {

        conexionDB conexion = new conexionDB();
        Factura factura = new Factura();
        DetallesFactura detallesFc = null;
        DtoFactura dtoFactura = new DtoFactura();
        List<DetallesFactura> listaDF = new ArrayList<>();

        String sql = "SELECT f.fecha_factura, f.id_factura, df.id_producto, pd.nombre_producto, df.cantidad, pd.precio_producto,df.subtotal, f.total_factura"
                + " FROM facturas f"
                + " INNER JOIN detallesfacturas df ON df.id_factura = f.id_factura"
                + " INNER JOIN productos pd ON pd.id_producto = df.id_producto"
                + " WHERE f.id_factura = ?";

        try {
            PreparedStatement st = conexion.estableceConexion().prepareStatement(sql);
            st.setInt(1, detallesFactura.getIdFactura());
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                detallesFc = new DetallesFactura();

                factura.setFecha(rs.getString("fecha_factura"));
                factura.setTotal(rs.getDouble("total_factura"));

                detallesFc.setIdFactura(rs.getInt("id_factura"));
                detallesFc.setIdProducto(rs.getInt("id_producto"));
                detallesFc.setNombreProducto(rs.getString("nombre_producto"));
                detallesFc.setCantidad(rs.getInt("cantidad"));
                detallesFc.setValorIndv(rs.getDouble("precio_producto"));
                detallesFc.setSubtotal(rs.getDouble("subtotal"));

                listaDF.add(detallesFc);
            }
            if (detallesFc != null) {
                factura.setListaDtlls(listaDF);
                dtoFactura.setStatus("Ok");
                dtoFactura.setMensaje("Consulta realizada exitosamente");
                dtoFactura.setFactura(factura);

            } else {
                dtoFactura.setStatus("Error");
                dtoFactura.setMensaje("Erorr al realizar la consulta");
            }

        } catch (Exception e) {
            dtoFactura.setStatus("Error");
            dtoFactura.setMensaje("Error al buscar en BD, error: " + e.getMessage());
        }
        return dtoFactura;
    }

}

package DB.DBProducto;

import java.sql.*;
import DB.conexionDB;
import java.util.List;
import java.util.ArrayList;
import modelo.producto.Producto;
import modelo.producto.DtoListaProducto;

public class ConsultaProductos {

    public DtoListaProducto consultaProductos(Producto producto) {

        conexionDB conexion = new conexionDB();
        Producto pdcto = null;
        DtoListaProducto dtoPdcts = new DtoListaProducto();
        List<Producto> listaPdcts = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "select id_producto,nombre_producto,precio_producto,stock_producto,categoria_producto,descripcion_producto from productos;";

        try {
            st = conexion.estableceConexion().prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                pdcto = new Producto();
                pdcto.setIdProducto(rs.getInt(1));
                pdcto.setNombreProducto(rs.getString(2));
                pdcto.setPrecioProducto(rs.getDouble(3));
                pdcto.setStockProducto(rs.getInt(4));
                pdcto.setCategoriaProducto(rs.getString(5));
                pdcto.setDescripcionProducto(rs.getString(6));

                listaPdcts.add(pdcto);
            }
            if (pdcto != null) {
                dtoPdcts.setStatus("Ok");
                dtoPdcts.setMensaje("Se consulto exitosamente");
                dtoPdcts.setListaProductos(listaPdcts);
            } else {
                dtoPdcts.setStatus("Error");
                dtoPdcts.setMensaje("Erorr al realizar la consulta");
            }

        } catch (Exception e) {
            dtoPdcts.setStatus("Error");
            dtoPdcts.setMensaje("Error al buscar en BD, error: " + e.getMessage());
        }
        return dtoPdcts;
    }

}

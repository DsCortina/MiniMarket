package DB.DBProducto;

import java.sql.*;
import DB.conexionDB;
import modelo.producto.Producto;
import modelo.producto.DtoProducto;

public class ConsultaProducto {

    public ConsultaProducto() {
    }

    public DtoProducto crearProducto(Producto producto) {

        conexionDB conexion = new conexionDB();
        DtoProducto dtoProducto = new DtoProducto();

        String sql = "INSERT INTO productos (nombre_producto,,stock_producto,categoria_producto,descripcion_producto) VALUES (?,?,?,?,?);";

        try {
            PreparedStatement ps = conexion.estableceConexion().prepareStatement(sql);
            ps.setString(1, producto.getNombreProducto());
            ps.setDouble(2, producto.getPrecioProducto());
            ps.setInt(3, producto.getStockProducto());
            ps.setString(4, producto.getCategoriaProducto());
            ps.setString(5, producto.getDescripcionProducto());
            ps.execute();

            dtoProducto.setStatus("Ok");
            dtoProducto.setMensaje("Se creo el producto correctamente");

        } catch (Exception e) {
            dtoProducto.setStatus("Error");
            dtoProducto.setMensaje("Error al insertar en BD, error: " + e.getMessage());
        }
        return dtoProducto;
    }

    public DtoProducto consultarProducto(Producto producto) {

        conexionDB conexion = new conexionDB();
        Producto pdcto = null;
        DtoProducto dtoProducto = new DtoProducto();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from productos where id_producto= ?";

        try {
            ps = conexion.estableceConexion().prepareStatement(sql);
            ps.setInt(1, producto.getIdProducto());
            rs = ps.executeQuery();

            while (rs.next()) {
                pdcto = new Producto();
                pdcto.setIdProducto(rs.getInt(1));
                pdcto.setNombreProducto(rs.getString(2));
                pdcto.setPrecioProducto(rs.getDouble(3));
                pdcto.setStockProducto(rs.getInt(4));
                pdcto.setCategoriaProducto(rs.getString(5));
                pdcto.setDescripcionProducto(rs.getString(6));
            }
            if (pdcto != null) {
                dtoProducto.setStatus("Ok");
                dtoProducto.setMensaje("Se consulto exitosamente");
                dtoProducto.setProducto(pdcto);
            } else {
                dtoProducto.setStatus("Error");
                dtoProducto.setMensaje("Erorr al realizar la consulta");
            }

        } catch (Exception e) {
            dtoProducto.setStatus("Error");
            dtoProducto.setMensaje("Error al buscar en BD, error: " + e.getMessage());
        }
        return dtoProducto;
    }

    public DtoProducto actualizarProducto(Producto producto) {

        conexionDB conexion = new conexionDB();
        DtoProducto dtoProducto = new DtoProducto();
        PreparedStatement st = null;

        String sql = "UPDATE productos SET nombre_producto = ?, precio_producto =?, stock_producto =? WHERE id_producto =?";

        try {
            st = conexion.estableceConexion().prepareStatement(sql);
            st.setString(1, producto.getNombreProducto());
            st.setDouble(2, producto.getPrecioProducto());
            st.setInt(3, producto.getStockProducto());
            st.setInt(4, producto.getIdProducto());

            int rowsUpdated = st.executeUpdate();

            if (rowsUpdated > 0) {
                dtoProducto.setStatus("Ok");
                dtoProducto.setMensaje("Producto actualizado correctamente.");
            } else {
                dtoProducto.setStatus("Error");
                dtoProducto.setMensaje("No se encontró el producto para actualizar.");
            }
        } catch (Exception e) {
            dtoProducto.setStatus("Error");
            dtoProducto.setMensaje("Error al actualizar el producto: " + e.getMessage());
        }
        return dtoProducto;
    }

    public DtoProducto eleminarProducto(Producto producto) {

        conexionDB conexion = new conexionDB();
        DtoProducto dtoProducto = new DtoProducto();
        PreparedStatement st = null;

        String sql = "DELETE FROM productos WHERE id_producto =?";

        try {
            st = conexion.estableceConexion().prepareStatement(sql);
            st.setInt(1, producto.getIdProducto());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                dtoProducto.setStatus("Ok");
                dtoProducto.setMensaje("Se elimino el producto correctamente");
            } else {
                dtoProducto.setStatus("Error");
                dtoProducto.setMensaje("No se encontró el producto con el codigo proporcionado");
            }
        } catch (SQLException e) {
            dtoProducto.setStatus("Error");
            dtoProducto.setMensaje("Error en BD, error: " + e.getMessage());
        }
        return dtoProducto;
    }

}

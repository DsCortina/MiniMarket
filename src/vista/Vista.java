package vista;

import DB.DBDetallesFactura.ConsultaDetallesFactura;
import java.util.Scanner;
import controller.MyController;
import modelo.empleado.Empleado;
import modelo.empleado.DtoEmpleado;
import DB.DBEmpleado.ConsultasEmpleado;
import DB.DBFactura.ConsultaFactura;
import static controller.MyController.codigosCompra;
import modelo.cliente.Cliente;
import modelo.detallesfactura.DetallesFactura;
import modelo.detallesfactura.DtoDetallesFactura;
import modelo.factura.DtoFactura;
import modelo.factura.Factura;
import modelo.producto.Producto;

public class Vista {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Empleado empl = new Empleado();
        Factura factura = new Factura();
        DetallesFactura detallesFactura = new DetallesFactura();
        ConsultasEmpleado consultaempl = new ConsultasEmpleado();
        ConsultaFactura consulaFact = new ConsultaFactura();
        ConsultaDetallesFactura consultaDF = new ConsultaDetallesFactura();
        DtoFactura dtoFactura;
        String fecha = MyController.fecha();

        System.out.println(" - - - = LOGIN = - - - ");
        System.out.print("Correo: ");
        String correoLogin = sc.nextLine();
        System.out.print("Clave: ");
        String claveLogin = sc.nextLine();
        empl.setCorreoElectronico(correoLogin);
        DtoEmpleado LoginEmpleado = consultaempl.consultaEmpLogin(empl);

        if (LoginEmpleado.getStatus().equals("Ok")) {
            if (LoginEmpleado.getEmpleado().getCorreoElectronico().equals(correoLogin) && LoginEmpleado.getEmpleado().getDocumento().equals(claveLogin)) {

                System.out.println("+ - - - - - - - - - - - +");
                System.out.println("Usuario: " + LoginEmpleado.getEmpleado().getNombreCompleto());
                System.out.println("+ - - - - - - - - - - - +");

                Cliente cliente = MyController.consultarCliente();
                System.out.println("* -- -- -- -- -- -- -- -- *");

                MyController.entradaCodigos();
                double totalFactura = MyController.TotalFactura();

                //Creación de factura
                factura.setIdCliente(cliente.getId());
                factura.setFecha(fecha);
                factura.setTotal(totalFactura);
                DtoFactura crearFactura = consulaFact.crearFactura(factura);
                detallesFactura.setIdFactura(crearFactura.getFactura().getIdFactura());

                for (Producto listaCodigos : codigosCompra) {
                    detallesFactura.setIdFactura(crearFactura.getFactura().getIdFactura());
                    detallesFactura.setIdProducto(listaCodigos.getIdProducto());
                    detallesFactura.setCantidad(1);
                    detallesFactura.setValorIndv(listaCodigos.getIdProducto());
                    detallesFactura.setSubtotal(listaCodigos.getPrecioProducto());

                    DtoDetallesFactura crearDetallesFactura = consultaDF.crearDtllsFactura(detallesFactura);

                    if (!crearDetallesFactura.getStatus().equals("Ok")) {
                        System.out.println(crearDetallesFactura.getMensaje());
                    }

                }

                dtoFactura = consultaDF.consultaProductos(detallesFactura);
                if (dtoFactura.getStatus().equals("Ok")) {

                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("                  SUPERMARKET DC               ");
                    System.out.println("- - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println(dtoFactura.getFactura().getFecha());
                    System.out.println("Factura #" + crearFactura.getFactura().getIdFactura());
                    System.out.println("* - - - - - - - - - - -  - - - - - - -  - - - *");
                    System.out.println("Cajero: " + LoginEmpleado.getEmpleado().getNombreCompleto() + "      Codigo: " + LoginEmpleado.getEmpleado().getId());
                    System.out.println("Cliente: " + cliente.getNombres());
                    System.out.println("* - - - - - - - - - - -  - - - - - - -  - - - *");
                    System.out.println("Articulo       Codigo   Precio    Subtotal");
                    System.out.println("+------------+--------+---------+-------------+");
                    for (DetallesFactura listaDF : dtoFactura.getFactura().getListaDtlls()) {
                        System.out.println(listaDF.toString());
                    }
                    System.out.println("= == == == == == == == == == == == == == == == ");
                    System.out.println("Total:                        " + dtoFactura.getFactura().getTotal());
                    System.out.println("  ");
                    System.out.println("  - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("         Desarrollado por DsCortina        ");
                    System.out.println("         NIT:  1 140 884 915 - 6           ");
                    System.out.println("  - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("  ");

                } else {
                    System.out.println(dtoFactura.getMensaje());
                }
            } else {
                System.out.println("Verifique sus credenciales");
            }
        } else {
            System.out.println("Verifique sus credenciales");
        }
    }
}

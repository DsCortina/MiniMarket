package controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import modelo.empleado.Empleado;
import modelo.empleado.DtoEmpleado;
import modelo.empleado.DtoListaEmpleados;
import modelo.cliente.Cliente;
import modelo.cliente.DtoCliente;
import modelo.cliente.DtoListaClientes;
import modelo.producto.Producto;
import modelo.producto.DtoProducto;
import modelo.producto.DtoListaProducto;
import DB.DBCliente.ConsultasCliente;
import DB.DBCliente.ConsultasClientes;
import DB.DBEmpleado.ConsultasEmpleado;
import DB.DBEmpleado.ConsultasEmpleados;
import DB.DBProducto.ConsultaProducto;
import DB.DBProducto.ConsultaProductos;
import modelo.detallesfactura.DetallesFactura;

public class MyController {

    //OBJS
    public static Scanner sc = new Scanner(System.in);
    public static Cliente cliente = new Cliente();
    public static ConsultasCliente consultaCl = new ConsultasCliente();
    public static ConsultasClientes consultaCls = new ConsultasClientes();
    public static Empleado empl = new Empleado();
    public static ConsultasEmpleado consultaempl = new ConsultasEmpleado();
    public static ConsultasEmpleados consultaempls = new ConsultasEmpleados();
    public static Producto producto = new Producto();
    public static ConsultaProducto consultapr = new ConsultaProducto();
    public static ConsultaProductos consultaprs = new ConsultaProductos();
    public static List<Producto> codigosCompra = new ArrayList<>();
    public static DetallesFactura detallesFactura = new DetallesFactura();

    public static void entradaCodigos() {
        int codprod = -1;

        while (codprod != 0) {
            System.out.println("Ingrese el codigo del producto: ");
            codprod = sc.nextInt();
            producto.setIdProducto(codprod);

            DtoProducto entradacod = consultapr.consultarProducto(producto);
            if (entradacod.getStatus().equals("Ok")) {
                codigosCompra.add(entradacod.getProducto());
            }
        }
    }


    public static double TotalFactura() {
        double sumasubtotal = 0;
        for (Producto subtotal : codigosCompra) {
            sumasubtotal += subtotal.getPrecioProducto();
        }
        return sumasubtotal;
    }

    public static void crearEmpleado() {
        System.out.println(" - - - - Crear Empleado - - - - -");
        System.out.print("Nombre completo del empleado: ");
        String nombreEmpl = sc.nextLine();
        System.out.print("Numero de documento del empleado: ");
        String docuEmpl = sc.nextLine();
        System.out.print("Correo electronico del empleado: ");
        String correoEmpl = sc.nextLine();

        empl.setNombreCompleto(nombreEmpl);
        empl.setDocumento(docuEmpl);
        empl.setCorreoElectronico(correoEmpl);

        DtoEmpleado EmpleadoDto = consultaempl.crearEmpleado(empl);

        if (EmpleadoDto.getStatus().equals("Ok")) {
            System.out.println(EmpleadoDto.getMensaje());
        } else {
            System.out.println(EmpleadoDto.getMensaje());
        }
    }

    public static void consultarEmpleados() {
        System.out.println(" - - - - Consultar Empleados - - - - -");
        System.out.println("Lista de empleados:");
        DtoListaEmpleados consultaEmpls = consultaempls.consultaEmpleado(empl);
        if (consultaEmpls.getStatus().equals("Ok")) {
            System.out.println(consultaEmpls.getListaEmpleados());

        } else {
            System.out.println(consultaEmpls.getMensaje());
        }
    }

    public static void actualizarEmpleado() {
        System.out.println(" - - - - Actualizar Empleado - - - - -");
        System.out.print("Numero de documento del empleado a actualizar: ");
        String docuEmpl = sc.nextLine();
        System.out.print("Nombre completo del empleado: ");
        String nombreEmpl = sc.nextLine();
        System.out.print("Correo electronico del empleado: ");
        String correoEmpl = sc.nextLine();

        //Se llena el obj Empleado, seteandolo
        empl.setNombreCompleto(nombreEmpl);
        empl.setDocumento(docuEmpl);
        empl.setCorreoElectronico(correoEmpl);

        //Se valida que el estado del Query
        DtoEmpleado ActualizarEmpl = consultaempl.ActulizarEmpleado(empl);
        if (ActualizarEmpl.getStatus().equals("Ok")) {
            System.out.println(ActualizarEmpl.getMensaje());
        } else {
            System.out.println(ActualizarEmpl.getMensaje());
        }
    }

    public static void eliminarEmpleado() {
        System.out.println(" - - - - Eliminar Empleado - - - - -");
        System.out.print("Numero de documento del empleado a eliminar: ");
        String docuEmpl = sc.nextLine();
        System.out.println("Esta seguro de eliminar el empleado: "
                + "\n1. No estoy seguro"
                + "\n2. Estoy seguro");
        String deletempl = sc.nextLine();
        if ("2".equals(deletempl)) {
            //Se llena el obj Empleado, seteandolo
            empl.setDocumento(docuEmpl);
            //Se valida que el estado del Query
            DtoEmpleado eliminarEmpl = consultaempl.elimarEmpleado(empl);
            if (eliminarEmpl.getStatus().equals("Ok")) {
                System.out.println(eliminarEmpl.getMensaje());
            } else {
                System.out.println(eliminarEmpl.getMensaje());
            }
        }
    }

    public static Cliente crearCliente() {
        System.out.println(" - - - - Crear Cliente - - - - -");
        System.out.print("Nombre completo del cliente: ");
        String nombreCl = sc.nextLine();
        System.out.print("Numero de documento del cliente: ");
        String docuCl = sc.nextLine();
        System.out.print("Numero telefonico del cliente: ");
        String telefonocl = sc.nextLine();
        System.out.print("Correo electronico del cliente: ");
        String correocl = sc.nextLine();

        //Se llena el obj Cliente, seteandolo
        cliente.setNombres(nombreCl);
        cliente.setDocumento(docuCl);
        cliente.setTelefono(telefonocl);
        cliente.setCorreoElectronico(correocl);

        DtoCliente clienteDto = consultaCl.crearCliente(cliente);

        if (clienteDto.getStatus().equals("Ok")) {
            System.out.println(clienteDto.getMensaje());
            return cliente;
        } else {
            System.out.println(clienteDto.getMensaje());
            return null;
        }

    }

    public static Cliente consultarCliente() {
        System.out.println(" - - - - Consultar Cliente - - - - -");
        System.out.println("Ingrese el documento del cliente");
        String documentoCliente = sc.nextLine();
        cliente.setDocumento(documentoCliente);

        DtoCliente consultaCliente = consultaCl.ConsultarCliente(cliente);

        if (consultaCliente.getStatus().equals("Ok")) {
            System.out.println("Cliente: " + consultaCliente.getCliente().getNombres());
            return consultaCliente.getCliente(); // Retornar cliente consultado.
        } else {
            System.out.println("Cliente no encontrado. Se creara el cliente.");
            return crearCliente();
        }
    }

    public static void consultarClientes() {
        System.out.println(" - - - - Consultar Clientes - - - - -");
        System.out.println("Lista de clientes:");
        DtoListaClientes listaClientes = consultaCls.ConsultaClientes(cliente);
        if (listaClientes.getStatus().equals("Ok")) {
            System.out.println(listaClientes.getCliente());

        } else {
            System.out.println(listaClientes.getMensaje());
        }
    }

    public static void actualizarCliente() {
        System.out.println(" - - - - Actualizar Cliente - - - - -");
        System.out.print("Nombre completo del cliente: ");
        String clnombre = sc.nextLine();
        System.out.print("Numero de documento del cliente a actualizar: ");
        String cldocu = sc.nextLine();
        System.out.print("Numero de telefono del cliente: ");
        String cltelefono = sc.nextLine();
        System.out.print("Correo electronico del cliente: ");
        String clcorreo = sc.nextLine();

        cliente.setNombres(clnombre);
        cliente.setDocumento(cldocu);
        cliente.setTelefono(cltelefono);
        cliente.setCorreoElectronico(clcorreo);

        DtoCliente clActualizar = consultaCl.ActualizarCliente(cliente);
        if (clActualizar.getStatus().equals("Ok")) {
            System.out.println(clActualizar.getMensaje());
        } else {
            System.out.println(clActualizar.getMensaje());
        }
    }

    public static void eliminarCliente() {
        System.out.println(" - - - - Eliminar Cliente - - - - -");
        System.out.print("Numero de documento del cliente a eliminar: ");
        String cldocu = sc.nextLine();
        System.out.println("Esta seguro de eliminar el cliente: "
                + "\n1. No estoy seguro"
                + "\n2. Estoy seguro");
        int deletecl = sc.nextInt();
        if (2 == (deletecl)) {
            empl.setDocumento(cldocu);
            DtoCliente eliminarCl = consultaCl.eliminarCliente(cliente);
            if (eliminarCl.getStatus().equals("Ok")) {
                System.out.println(eliminarCl.getMensaje());
            } else {
                System.out.println(eliminarCl.getMensaje());
            }
        } else {
            System.out.println("saliendo...");
        }
    }

    public static void crearProducto() {
        System.out.println(" - - - - Crear Producto - - - - -");
        System.out.print("Nombre del producto: ");
        String nombrePr = sc.nextLine();
        System.out.print("Precio del producto: ");
        double precioPr = sc.nextDouble();
        System.out.print("Stock inicial del producto: ");
        int stockPr = sc.nextInt();
        sc.nextLine();
        System.out.print("Categoria del producto: ");
        String categoriaPr = sc.nextLine();
        System.out.print("Detalles del producto: ");
        String detallePr = sc.nextLine();

        //Se llena el obj Cliente, seteandolo
        producto.setNombreProducto(nombrePr);
        producto.setPrecioProducto(precioPr);
        producto.setStockProducto(stockPr);
        producto.setCategoriaProducto(categoriaPr);
        producto.setDescripcionProducto(detallePr);

        DtoProducto crearPr = consultapr.crearProducto(producto);

        if (crearPr.getStatus().equals("Ok")) {
            System.out.println(crearPr.getMensaje());
        } else {
            System.out.println(crearPr.getMensaje());
        }
    }

    public static void consultarProductos() {
        System.out.println(" - - - - Consultar Productos - - - - -");
        System.out.println("Lista de productos:");
        DtoListaProducto listaPdctos = consultaprs.consultaProductos(producto);
        if (listaPdctos.getStatus().equals("Ok")) {
            System.out.println(listaPdctos.getListaProductos());
        } else {
            System.out.println(listaPdctos.getMensaje());
        }
    }

    public static void actualizarProducto() {
        System.out.println(" - - - - Actualizar Producto - - - - -");
        System.out.print("Codigo del producto por actualizar: ");
        int codigoPdct = sc.nextInt();
        sc.nextLine();
        System.out.print("Nombre del producto: ");
        String nombrePr = sc.nextLine();
        System.out.print("Precio del producto: ");
        double precioPr = sc.nextDouble();
        System.out.print("Stock inicial del producto: ");
        int stockPr = sc.nextInt();
        sc.nextLine();

        producto.setNombreProducto(nombrePr);
        producto.setPrecioProducto(precioPr);
        producto.setStockProducto(stockPr);
        producto.setIdProducto(codigoPdct);

        DtoProducto actualizarPdct = consultapr.actualizarProducto(producto);

        if (actualizarPdct.getStatus().equals("Ok")) {
            System.out.println(actualizarPdct.getMensaje());
        } else {
            System.out.println(actualizarPdct.getMensaje());
        }
    }

    public static void eliminarProducto() {
        System.out.println(" - - - - Eliminar Producto - - - - -");
        System.out.print("Codigo del producto a eliminar: ");
        int codigoPdct = sc.nextInt();
        sc.nextLine();
        System.out.println("Esta seguro de eliminar el producto: "
                + "\n1. No estoy seguro"
                + "\n2. Estoy seguro");
        String deletecl = sc.nextLine();

        if ("2".equals(deletecl)) {
            producto.setIdProducto(codigoPdct);
            DtoProducto eliminarPdct = consultapr.eleminarProducto(producto);
            if (eliminarPdct.getStatus().equals("Ok")) {
                System.out.println(eliminarPdct.getMensaje());
            } else {
                System.out.println(eliminarPdct.getMensaje());
            }
        } else {
            System.out.println("saliendo...");
        }
    }

    public static String fecha() {
        Date fechaActual = new Date();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = formatoFecha.format(fechaActual);
        return fechaFormateada;
    }

}

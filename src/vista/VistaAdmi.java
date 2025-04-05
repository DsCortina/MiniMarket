package vista;

import java.util.Scanner;
import controller.MyController;
import modelo.empleado.Empleado;
import modelo.empleado.DtoEmpleado;
import DB.DBEmpleado.ConsultasEmpleado;


public class VistaAdmi {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Empleado empl = new Empleado();
        ConsultasEmpleado consultaempl = new ConsultasEmpleado();
        DtoEmpleado LoginEmpleado = null;
        int menu = 0;

        System.out.println(" - - - - LOGIN - - - - ");
        System.out.print("Correo: ");
        String correoLogin = sc.nextLine();
        System.out.print("Clave: ");
        String claveLogin = sc.nextLine();

        empl.setCorreoElectronico(correoLogin);
        LoginEmpleado = consultaempl.consultaEmpLogin(empl);

        if (LoginEmpleado.getEmpleado().getCorreoElectronico().equals(correoLogin) && LoginEmpleado.getEmpleado().getDocumento().equals(claveLogin)) {
            System.out.println(" - - - - - - - - - - - ");
            System.out.println("Usuario: " + LoginEmpleado.getEmpleado().getNombreCompleto());

            while (menu != 4) {
                System.out.println("= = Menú de Principal = ="
                        + "\n1. Modulo Clientes"
                        + "\n2. Modulo Empleados"
                        + "\n3. Modulo Productos"
                        + "\n4. Salir");
                System.out.print("Seleccione una opción:");
                menu = sc.nextInt();
                sc.nextLine();
                switch (menu) {
                    case 1:
                        System.out.println("= = Menú de Cliente = ="
                                + "\n1. Crear cliente"
                                + "\n2. Consultar clientes"
                                + "\n3. Actulizar cliente"
                                + "\n4. Eliminar cliente"
                                + "\n5. Salir");
                        System.out.print("Seleccione una opción:");
                        int menuCliente = sc.nextInt();
                        sc.nextLine();
                        switch (menuCliente) {
                            case 1:
                                MyController.crearCliente();
                                break;
                            case 2:
                                MyController.consultarClientes();
                                break;
                            case 3:
                                MyController.actualizarCliente();
                                break;
                            case 4:
                                MyController.eliminarCliente();
                                break;
                            case 5:
                                System.out.println("Saliendo del menú cliente");
                                break;
                        }
                    case 2:
                        System.out.println("= = Menú de Empleado = ="
                                + "\n1. Crear empleado"
                                + "\n2. Consultar empleados"
                                + "\n3. Actulizar empleado"
                                + "\n4. Eliminar empleado"
                                + "\n5. Salir");
                        System.out.print("Seleccione una opción:");
                        int menuEmpleado = sc.nextInt();
                        sc.nextLine();
                        switch (menuEmpleado) {
                            case 1:
                                MyController.crearEmpleado();
                                break;
                            case 2:
                                MyController.consultarEmpleados();
                                break;
                            case 3:
                                MyController.actualizarEmpleado();
                                break;
                            case 4:
                                MyController.eliminarEmpleado();
                                break;
                            case 5:
                                System.out.println("Saliendo del menú empleado");
                                break;
                        }
                    case 3:
                        System.out.println("= = Menú de producto = ="
                                + "\n1. Crear producto"
                                + "\n2. Consultar productos"
                                + "\n3. Actulizar producto"
                                + "\n4. Eliminar producto"
                                + "\n5. Salir");
                        System.out.print("Seleccione una opción:");
                        int menuProducto = sc.nextInt();
                        sc.nextLine();
                        switch (menuProducto) {
                            case 1:
                                MyController.crearProducto();
                                break;
                            case 2:
                                MyController.consultarProductos();
                                break;
                            case 3:
                                MyController.actualizarProducto();
                                break;
                            case 4:
                                MyController.eliminarProducto();
                                break;
                            case 5:
                                System.out.println("Saliendo del menú producto");
                                break;
                        }
                }
            }
        } else {
            System.out.println("verifique sus credenciales");
        }
    }
}

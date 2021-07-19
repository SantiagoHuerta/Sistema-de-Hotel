package controlador;


import archivos.Archivos;
import menus.Menu;
import model.Admin;
import model.Conserje;
import model.Hotel;
import utiles.IOGenericoUtil;

import java.util.Scanner;

/**
 * Clase primordial que controla el flujo del programa. Muestra los menus y permite al usuario introducir valores por
 * teclado para su procesamiento. Comprueba credenciales para el inicio de sesion de un usuario y modifica los datos
 * asignados al objeto Hotel. Todo cambio en comportamiento del programa debe verse reflejado en esta clase, y esta
 * misma es la que debe ser instanciada en el metodo main.
 */
public class Controlador {

    private Scanner scanner = new Scanner(System.in);
    private Hotel hotel;


    /**
     * El objeto que debe manejar es el de Hotel
     * @param hotel El hotel que el controlador manejara
     */
    public Controlador(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Metodo que provee del funcionamiento necesario para el programa. Controla la sesion del usuario, muestra los
     * menus correspondientes, solicita ingresos de datos por teclado y procesa la informacion mediante sentencias
     * 'switch'. El comportamiento del programa depende principalmente de este metodo. Modificarlo con precaucion, en
     * caso de que sea necesario.
     */
    public void inicio() {

        boolean hayAdmin = true;
        boolean loginExitoso = false;
        String opcion;

        while (!loginExitoso) {

            System.out.print("Ingrese ID: ");
            String idLogin = scanner.nextLine();
            System.out.print("Ingrese clave: ");
            String idPassword = scanner.nextLine();

            /*Autenticacion de credenciales para Admin*/
            if (idLogin.equals(hotel.getAdmin().getId()) &&
                    idPassword.equals(hotel.getAdmin().getPassword().getClave())) {

                System.out.println("Inicio sesion exitoso.");
                System.out.println("Ha iniciado sesion como Admin");

                Admin admin = hotel.getAdmin();
                if (admin.equals(Admin.proveerDefaultAdmin())) {
                    hayAdmin = false;
                }
                if (!hayAdmin) {
                    System.out.println("Usted ha iniciado sesion con credenciales por defecto. " +
                            "Se recomienda que estas sean modificadas.");
                }
                loginExitoso = true;
                /*Menu de Admin*/
                do {
                    Menu.adminMain();
                    opcion = scanner.nextLine();
                    switch (opcion) {
                        /*Submenu conserjes*/
                        case "1":
                            String subOpcion1;
                            do {
                                Menu.adminSubMenuConserjes();
                                subOpcion1 = scanner.nextLine();
                                switch (subOpcion1) {
                                    case "1":
                                        try {
                                            hotel.agregarConserje(admin.altaConserje(scanner, hotel));
                                            IOGenericoUtil.escribirObjeto(hotel.getConserjes(), Archivos.CONSERJES);
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                        break;
                                    case "2":
                                        admin.modificarDatosConserje(hotel, scanner);
                                        IOGenericoUtil.escribirObjeto(hotel.getConserjes(), Archivos.CONSERJES);
                                        break;
                                    case "3":
                                        admin.cambiarEstadoConserje(hotel, scanner);
                                        IOGenericoUtil.escribirObjeto(hotel.getConserjes(), Archivos.CONSERJES);
                                        break;
                                    case "4":
                                        admin.eliminarConserje(hotel, scanner);
                                        IOGenericoUtil.escribirObjeto(hotel.getConserjes(), Archivos.CONSERJES);
                                        break;
                                    case "5":
                                        hotel.listarTodosLosConserjes();
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        Menu.opcionIncorrecta();
                                }

                            } while (!subOpcion1.equals("0"));
                            break;
                        /*Submenu habitaciones*/
                        case "2":
                            String subOpcion2;
                            do {
                                Menu.adminSubMenuHabitaciones();
                                subOpcion2 = scanner.nextLine();
                                switch (subOpcion2) {
                                    case "1":
                                        try {
                                            hotel.agregarHabitacion(admin.altaHabitacion(scanner, hotel));
                                            IOGenericoUtil.escribirObjeto(hotel.getHabitaciones(), Archivos.HABITACIONES);
                                            System.out.println("Habtacion creada con exito.\n");
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                        break;
                                    case "2":
                                        admin.modificarTipoHabitacion(scanner, hotel);
                                        IOGenericoUtil.escribirObjeto(hotel.getHabitaciones(), Archivos.HABITACIONES);
                                        break;
                                    case "3":
                                        admin.modificarPrecioHabitacion(scanner, hotel);
                                        IOGenericoUtil.escribirObjeto(hotel.getHabitaciones(), Archivos.HABITACIONES);
                                        break;
                                    case "4":
                                        admin.eliminarHabitacion(scanner, hotel);
                                        IOGenericoUtil.escribirObjeto(hotel.getHabitaciones(), Archivos.HABITACIONES);
                                        break;
                                    case "5":
                                        hotel.listarTodasLashabitaciones();
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        Menu.opcionIncorrecta();
                                }
                            } while (!subOpcion2.equals("0"));
                            break;
                        /*Submenu clientes*/
                        case "3":
                            String subOpcion3;
                            do {
                                Menu.adminSubMenuClientes();
                                subOpcion3 = scanner.nextLine();
                                switch (subOpcion3) {
                                    case "1":
                                        hotel.listarTodosLosClientes();
                                        break;
                                    case "2":
                                        admin.eliminarCliente(scanner, hotel);
                                        IOGenericoUtil.escribirObjeto(hotel.getClientes(), Archivos.CLIENTES);
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        Menu.opcionIncorrecta();
                                }

                            } while (!subOpcion3.equals("0"));
                            break;
                        /*Submenu info propia*/
                        case "4":
                            String subOpcion4;
                            do {
                                Menu.subMenuInfoPropia();
                                subOpcion4 = scanner.nextLine();
                                switch (subOpcion4) {
                                    case "1":
                                        System.out.println(admin);
                                        break;
                                    case "2":
                                        admin.modificarDatosUsuario(scanner, hotel);
                                        IOGenericoUtil.escribirObjeto(admin, Archivos.ADMIN);
                                        break;
                                    case "0":
                                        break;
                                    default:
                                        Menu.opcionIncorrecta();
                                }

                            } while (!subOpcion4.equals("0"));
                            break;
                        case "0":
                            System.out.println("Sesion finalizada.");
                            break;
                        default:
                            Menu.opcionIncorrecta();
                    }
                } while (!opcion.equals("0"));

            } else if (hotel.getConserjes().containsKey(idLogin) &&
                    idPassword.equals(hotel.getConserjes().get(idLogin).getPassword().getClave())) {
                Conserje conserje = hotel.getConserjes().get(idLogin);
                System.out.println("Ha iniciado sesion como Conserje. Bienvenido " + conserje.getNombre());
                loginExitoso = true;

                if (!conserje.isHabilitado()) {
                    System.out.println("Usted no esta habilitado para trabajar en el sistema. Contacte al administrador\n");
                } else {
                    /*Menu de Conserje*/
                    do {
                        Menu.conserjeMain();
                        opcion = scanner.nextLine();
                        switch (opcion) {
                            /*Submenu reservas*/
                            case "1":
                                if (hotel.getHabitaciones().isEmpty()) {
                                    System.out.println("ERROR: no existen habitaciones habilitadas en el sistema." +
                                            "Contacte al administrador.");
                                } else {
                                    String subOpcion1;
                                    do {
                                        Menu.conserjeSubMenuReservas();
                                        subOpcion1 = scanner.nextLine();
                                        switch (subOpcion1) {
                                            case "1":
                                                hotel.agregarReserva(conserje.altaReserva(scanner, hotel));
                                                IOGenericoUtil.escribirObjeto(hotel.getReservas(), Archivos.RESERVAS);
                                                break;
                                            case "2":
                                                conserje.checkIn(scanner, hotel);
                                                IOGenericoUtil.escribirObjeto(hotel.getReservas(), Archivos.RESERVAS);
                                                break;
                                            case "3":
                                                try {
                                                    hotel.agregarIngreso(conserje.checkOut(scanner, hotel));
                                                    IOGenericoUtil.escribirObjeto(hotel.getTotalIngresos(), Archivos.INGRESOS);
                                                    IOGenericoUtil.escribirObjeto(hotel.getReservas(), Archivos.RESERVAS);
                                                } catch (Exception e) {
                                                    System.out.println(e.getMessage());
                                                }
                                                break;
                                            case "4":
                                                hotel.listarTodasLasReservas();
                                                break;
                                            case "5":
                                                hotel.listarReservasConfirmadas();
                                            case "6":
                                                conserje.cancelarReserva(scanner, hotel);
                                                IOGenericoUtil.escribirObjeto(hotel.getReservas(), Archivos.RESERVAS);
                                                break;
                                            case "7":
                                                conserje.agregarConsumo(scanner, hotel);
                                                IOGenericoUtil.escribirObjeto(hotel.getReservas(), Archivos.RESERVAS);
                                                break;
                                            case "0":
                                                break;
                                            default:
                                                Menu.opcionIncorrecta();
                                        }

                                    } while (!subOpcion1.equals("0"));
                                }
                                break;
                            /*Submenu clientes*/
                            case "2":
                                String subOpcion2;
                                do {
                                    Menu.conserjeSubMenuClientes();
                                    subOpcion2 = scanner.nextLine();
                                    switch (subOpcion2) {
                                        case "1":
                                            hotel.listarTodosLosClientes();
                                            break;
                                        case "2":
                                            conserje.modificarCliente(scanner, hotel);
                                            break;
                                        case "0":
                                            break;
                                        default:
                                            Menu.opcionIncorrecta();
                                    }

                                } while (!subOpcion2.equals("0"));
                                break;
                            /*Submenu habitaciones*/
                            case "3":
                                if (hotel.getHabitaciones().isEmpty()) {
                                    System.out.println("Error: no existen habitaciones cargadas en sistema." +
                                            "Contacte al administrador.");
                                }
                                String subOpcion3;
                                do {
                                    Menu.conserjeSubMenuHabitaciones();
                                    subOpcion3 = scanner.nextLine();
                                    switch (subOpcion3) {
                                        case "1":
                                            hotel.listarHabitacionesLibres();
                                            break;
                                        case "2":
                                            hotel.listarHabitacionesOcupadas();
                                            break;
                                        case "0":
                                            break;
                                        default:
                                            Menu.opcionIncorrecta();
                                    }

                                } while (!subOpcion3.equals("0"));
                                break;
                            /*Submenu info propia*/
                            case "4":
                                String subOpcion4;
                                do {
                                    Menu.subMenuInfoPropia();
                                    subOpcion4 = scanner.nextLine();
                                    switch (subOpcion4) {
                                        case "1":
                                            System.out.println(conserje);
                                            break;
                                        case "2":
                                            conserje.modificarDatosUsuario(scanner, hotel);
                                            IOGenericoUtil.escribirObjeto(hotel.getConserjes(), Archivos.CONSERJES);
                                            break;
                                        case "0":
                                            break;
                                        default:
                                            Menu.opcionIncorrecta();
                                    }

                                } while (!subOpcion4.equals("0"));
                                break;
                            case "0":
                                System.out.println("Sesion finalizada.");
                                break;
                            default:
                                Menu.opcionIncorrecta();
                        }
                    } while (!opcion.equals("0"));

                }
            } else {
                System.out.println("El ID de usuario no existe o la contraseña es incorrecta.");
            }
        }
        scanner.close();
    } // inicio()
}
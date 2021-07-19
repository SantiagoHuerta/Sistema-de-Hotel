package app;

import archivos.*;
import controlador.*;
import model.*;
import utiles.*;

/**
 * Clase cuyo unico fin es el de proveer el metodo main que le dara funcionamiento al programa.
 */
public class Main {

    public static void main(String[] args) {

        new Controlador (Hotel.getInstancia()).inicio();

    }
}
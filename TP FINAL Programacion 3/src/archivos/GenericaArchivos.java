package archivos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GenericaArchivos {

	/**
	 * Clase generica  cuyo fin es el de poder escribir y leer los datos relevantes pertenecientes al hotel
	 * y de archivos. Esta clase no es instanciable.
	 */
	 private GenericaArchivos() {
		 
	    }
	 /**
	     * Escribe un objeto serializable en un archivo.
	     *
	     * @param t    El tipo de objeto a guardar
	     * @param file La ruta correspondiente al archivo
	     * @param <T>  Cualquier tipo que extienda de la interfaz Serializable
	     */
	    public static <T extends Serializable> void escribirObjeto(T t, String file) {

	        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {

	            out.writeObject(t);
	          

	        } catch (IOException e) {
	            System.out.println("No se ha podido escribir el objeto " + t.getClass().getSimpleName() +
	                                " en el archivo. Contacte al administrador.\n");
	        }
	    }

	    /**
	     * Lee un objeto serializable en un archivo y lo devuelve.
	     *
	     * @param file La ruta correspondiente al archivo
	     * @param <T>  Cualquier tipo que extienda de la interfaz Serializable
	     */
	    @SuppressWarnings("unchecked")
	    public static <T extends Serializable> T leerObjeto(String file) {

	        T t = null;

	        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {

	            t = (T) in.readObject();

	        } catch (IOException | ClassNotFoundException e) {
	            System.out.println("Error lectura.");
	        }

	        return t;
	    }
	 
}

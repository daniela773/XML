package Usuario;

public class Menu {
    public static int menuPrincipal(){
        int opcion;
        opcion=Escaner.leerInt("Introduzca la opción que desee realizar\n" +
                "1. Agregar un alumno al fichero\n" +
                "2. Leer el fichero\n" +
                "3. Actualizar el fichero\n" +
                "4. Borrar un alumno\n" +
                "5. Consultar nota media" +
                "6. Mostrar estadística");

        return opcion;
    }
}

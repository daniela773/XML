package Usuario;

import Logica.Alumno;

public class Menu {
    public static int menuPrincipal(){
        int opcion;
        opcion=Escaner.leerInt("\nIntroduzca la opción que desee realizar\n" +
                "1. Agregar un alumno al fichero\n" +
                "2. Leer el fichero\n" +
                "3. Actualizar el fichero\n" +
                "4. Borrar un alumno\n" +
                "5. Consultar nota media\n" +
                "6. Mostrar estadística\n" +
                "7. Ajax\n" +
                "8. Salir\n");

        return opcion;
    }

    public static int elegirID(){
        int opcion;
        opcion=Escaner.leerInt("Escriba el id en el que quiera realizar la operación");

        return opcion;
    }

    public static Alumno menuAgregarAlumno(){
        int id;
        String nombre;
        int notaFinal;
        Alumno alumno;

        id=Escaner.leerInt("Escriba el id");
        nombre=Escaner.leerString("Escriba el nombre del alumno");
        notaFinal=Escaner.leerInt("Escriba la nota media del alumno");

        alumno=new Alumno(id,nombre,notaFinal);

        return alumno;

    }

    public static Alumno menuActualizarAlumno(int id){
        int idNuevo;
        String nombre;
        int notaFinal;
        Alumno alumno;

        idNuevo=Escaner.leerInt("Escriba el id del nuevo alumno");
        nombre=Escaner.leerString("Escriba el nombre del nuevo alumno");
        notaFinal=Escaner.leerInt("Escriba la nota media del nuevo alumno");

        alumno=new Alumno(idNuevo,nombre,notaFinal);

        return alumno;

    }
}

package Logica;

import DataLayer.AlumnoDAO;
import Usuario.Menu;

public class LogicaMenu {
    public static void ejecutarMenu(){
        int opcion= Menu.menuPrincipal();
        int id=Menu.elegirID();
        Alumno alumno;

        switch (opcion){
            case 1:  //Agregar un alumno
                alumno=Menu.menuAgregarAlumno();
                AlumnoDAO.agregar(alumno);
                break;

            case 2: //Leer en el fichero
                AlumnoDAO.leerTodos();
                break;

            case 3: //Actualizar el fichero
                alumno=Menu.menuActualizarAlumno();
                AlumnoDAO.actualizar(id,alumno);
                break;

            case 4: //Borrar un alumno
                AlumnoDAO.eliminar(id);
                break;

            case 5: //Consultar nota media
                Consultas.notaMedia();
                break;

            case 6: //Mostrar estadistica
                Consultas.estadistica();
                break;

        }
    }
}

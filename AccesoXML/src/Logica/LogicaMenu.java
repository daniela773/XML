package Logica;

import DataLayer.AlumnoDAO;
import Usuario.Menu;

public class LogicaMenu {
    public static void ejecutarMenu(){
        int opcion;
        int id;
        Alumno alumno;

        do {
            opcion= Menu.menuPrincipal();
            switch (opcion){
                case 1:  //Agregar un alumno
                    alumno=Menu.menuAgregarAlumno();
                    AlumnoDAO.agregar(alumno);
                    break;

                case 2: //Leer en el fichero
                    AlumnoDAO.leer();
                    break;

                case 3: //Actualizar el fichero
                    id=Menu.elegirID();
                    alumno=Menu.menuActualizarAlumno(id);
                    AlumnoDAO.actualizar(id,alumno);
                    break;

                case 4: //Borrar un alumno
                    id=Menu.elegirID();
                    AlumnoDAO.eliminar(id);
                    break;

                case 5: //Consultar nota media
                    Consultas.notaMedia();
                    break;

                case 6: //Mostrar estadistica
                    Consultas.estadistica();
                    break;

                case 7:
                    AlumnoDAO.ajax();
                    break;

                case 8:
                    System.out.println("Has salido correctamente");
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        }while (opcion<8);

    }
}

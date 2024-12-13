package Logica;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListaAlumnos {
    private static List<Alumno> listaAlumnos=new ArrayList<Alumno>();

    public ListaAlumnos(List<Alumno> listaAlumnos) {
        this.listaAlumnos =listaAlumnos;
    }


    public List<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(List<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    @Override
    public String toString() {
        return "Logica.ListaAlumnos{" +
                "listaAlumnos=" + listaAlumnos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaAlumnos that = (ListaAlumnos) o;
        return Objects.equals(listaAlumnos, that.listaAlumnos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listaAlumnos);
    }

    public static void addItem(Alumno item){
        listaAlumnos.add(item);
    }
}

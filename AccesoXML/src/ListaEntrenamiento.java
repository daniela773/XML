import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ListaEntrenamiento {
    private static List<Entrenamiento> listaEntrenamiento=new ArrayList<Entrenamiento>();

    public ListaEntrenamiento(List<Entrenamiento> listaEntrenamiento) {
        this.listaEntrenamiento =listaEntrenamiento ;
    }


    public List<Entrenamiento> getListaEntrenamiento() {
        return listaEntrenamiento;
    }

    public void setListaEntrenamiento(List<Entrenamiento> listaEntrenamiento) {
        this.listaEntrenamiento = listaEntrenamiento;
    }

    @Override
    public String toString() {
        return "ListaEntrenamiento{" +
                "listaEntrenamiento=" + listaEntrenamiento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaEntrenamiento that = (ListaEntrenamiento) o;
        return Objects.equals(listaEntrenamiento, that.listaEntrenamiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listaEntrenamiento);
    }

    public static void addItem(Entrenamiento item){
        listaEntrenamiento.add(item);
    }
}

import java.util.Objects;

public class Entrenamiento {
    private int id;
    private String nombre;
    private int duracion;
    private String nivel;


    public Entrenamiento() {

    }

    public Entrenamiento(int id, String nombre, int duracion, String nivel) {
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "Entrenamiento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                ", nivel='" + nivel + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrenamiento that = (Entrenamiento) o;
        return id == that.id && duracion == that.duracion && Objects.equals(nombre, that.nombre) && Objects.equals(nivel, that.nivel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, duracion, nivel);
    }
}

public class Equipo {
    public String nombre;


    public Equipo(String nombre) {
        this.nombre = nombre;

    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}

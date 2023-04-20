import java.util.ArrayList;
import java.util.List;

public class Ronda {
    String nombre;
    String fase;
    List<Partido> partidos;

    public Ronda(String nombre,String fase) {
        this.nombre = nombre;
        this.fase=fase;
        this.partidos=new ArrayList<>();
    }

    public Ronda(String nombre, List<Partido> partidos) {
        this.nombre = nombre;
        this.partidos = partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    @Override
    public String toString() {
        return "Ronda{" + "fase='"+fase+'\''+
                "nro='" + nombre + '\'' +
                ", partidos=" + partidos.toString() +
                '}';
    }

    public void agregarPartidos(Partido partido) {
        this.partidos.add(partido);
    }
}

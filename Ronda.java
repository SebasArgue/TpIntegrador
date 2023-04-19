import java.util.ArrayList;
import java.util.List;

public class Ronda {
    String nro;
    List<Partido> partidos;

    public Ronda(String nro) {
        this.nro = nro;
        this.partidos=new ArrayList<>();
    }

    public Ronda(String nro, List<Partido> partidos) {
        this.nro = nro;
        this.partidos = partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    @Override
    public String toString() {
        return "Ronda{" +
                "nro='" + nro + '\'' +
                ", partidos=" + partidos.toString() +
                '}';
    }

    public void agregarPartidos(Partido partido) {
        this.partidos.add(partido);
    }
}

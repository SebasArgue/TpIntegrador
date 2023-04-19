import java.util.ArrayList;
import java.util.List;

public class Pronostico {
    String nombre;
    List<Ronda> rondas;


    public Pronostico(String nombre) {
        this.nombre = nombre;

    }

    public Pronostico(String nombre, List<Ronda> rondas) {
        this.nombre = nombre;
        this.rondas = rondas;
    }

    public void setRondas(List<Ronda> rondas) {
        this.rondas = rondas;
    }

    @Override
    public String toString() {
        return "Pronostico{" +
                "nombre='" + nombre + '\'' +
                ", rondas=" + rondas.toString() +
                '}';
    }

    public void agregarRonda(Ronda ronda) {
        this.rondas.add(ronda);
    }
}

import java.util.ArrayList;
import java.util.List;

public class Pronostico {
    String nombre;
    List<Ronda> rondas;


    public Pronostico(String nombre) {
        this.nombre = nombre;

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
}

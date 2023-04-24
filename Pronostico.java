import java.util.ArrayList;
import java.util.List;

public class Pronostico {
    String nombre;
    List<Ronda> rondas;
    int puntos=0;
    int pronAcertados=0;
    boolean puntosExtra;

    int aciertoG=0;
    int aciertoO=0;
    int aciertoC=0;
    int aciertoS=0;
    int aciertoF=0;
    int aciertoFaseI=0;
    int aciertoFaseF=0;


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

    public void setPuntos(int puntos) {
        this.puntos += puntos;
        this.pronAcertados++;
    }
    public void aciertosExtra(String nombre){

        switch (nombre.toLowerCase()){
            case "octavos":
                this.aciertoO++;
                break;
            case "cuartos":
                this.aciertoC++;
                break;
            case "semis":
                this.aciertoS++;
                break;
            case "final":
                this.aciertoF++;
                break;
            case "inicial":
                this.aciertoFaseI++;
            case "finalf":
                this.aciertoFaseF++;
            default:
                this.aciertoG++;
        }
    }
    public void puntosExtra(int puntosE){
        if(aciertoG==6||aciertoO==8||aciertoC==4||aciertoS==2||aciertoF==2||aciertoFaseI==48||aciertoFaseF==16){
            this.puntos+=puntosE;
            this.puntosExtra= true;
        }
    }

}

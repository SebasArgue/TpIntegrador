
import java.util.List;

public class Pronostico {
    String nombre;
    List<Ronda> rondas;
    int puntos=0;
    int pronAcertados=0;
    boolean puntosExtra;

    int[] aciertosR= new int[]{0,0,0,0,0,0,0};



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



    public void setPuntos(int puntos) {
        this.puntos += puntos;
        this.pronAcertados++;
    }
    public void aciertosExtra(String nombre){

        switch (nombre.toLowerCase()){
            case "octavos":
                //this.aciertoO++;
                this.aciertosR[1]++;
                break;
            case "cuartos":
               // this.aciertoC++;
                this.aciertosR[2]++;
                break;
            case "semis":
               // this.aciertoS++;
                this.aciertosR[3]++;
                break;
            case "final":
               // this.aciertoF++;
                this.aciertosR[4]++;
                break;
            case "inicial":
               // this.aciertoFaseI++;
                this.aciertosR[5]++;
            case "finalf":
                //this.aciertoFaseF++;
                this.aciertosR[6]++;
            default:
                //this.aciertoG++;
                this.aciertosR[0]++;
        }
    }
    public void puntosExtra(int puntosE){
        if(aciertosR[0]==6||aciertosR[1]==8||aciertosR[2]==4||aciertosR[3]==2||aciertosR[4]==2||aciertosR[5]==48||aciertosR[6]==16){
            this.puntos+=puntosE;
            this.puntosExtra= true;
        }
    }

}

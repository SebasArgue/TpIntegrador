import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static String rutaResulatos = "Resultados.csv";
    public static String rutaPronosticos = "Pronosticos.csv";

    public static void main(String[] args) {
        Ronda ronda1=new Ronda("1");

        //leo el archivo de Resultado y creo las clases
        try {
            for (String linea: Files.readAllLines(Paths.get(rutaResulatos))){
                List<String> lineasSep = List.of(linea.split(";"));
                Equipo equipo1=new Equipo(lineasSep.get(2));
                Equipo equipo2=new Equipo(lineasSep.get(5));
                Partido partido= new Partido(lineasSep.get(1),equipo1 ,equipo2,Integer.parseInt(lineasSep.get(3)),Integer.parseInt(lineasSep.get(4)));
                ronda1.partidos.add(partido);
            }

        }catch (IOException e){
            System.out.println("Archivo no encontrado");
        }

        /*Equipo argentina=new Equipo("Argentina");
        Equipo arabia = new Equipo("Arabia Saudita");
        Equipo polonia = new Equipo("Polonia");
        Equipo mexico = new Equipo("Mexico");

        Partido partido1= new Partido("P1",argentina ,arabia,1,2);
        Partido partido2= new Partido("P2",polonia,mexico,0,0);

        ronda1.partidos.add(partido1);
        ronda1.partidos.add(partido2);*/

        ResultadoEnum resulPart1 = ronda1.partidos.get(0).resultado(ronda1.partidos.get(0).equipo1);
        ResultadoEnum resulPart2 = ronda1.partidos.get(1).resultado(ronda1.partidos.get(1).equipo1);;

        //leo el archivo de Pronostico y creo las clases
        List<Pronostico> pronosticos =new ArrayList<>();

        try {
            for (String linea: Files.readAllLines(Paths.get(rutaPronosticos))){
                List<String> lineasSep = List.of(linea.split(";"));
                Equipo equipo1=new Equipo(lineasSep.get(2));
                if(lineasSep.get(3) == "Ganador"){
                    pronosticos.add(new Pronostico(ronda1.partidos.get(1),equipo1,ResultadoEnum.GANADOR));
                } else if (lineasSep.get(3)=="Perdedor") {
                    pronosticos.add(new Pronostico(ronda1.partidos.get(1),equipo1,ResultadoEnum.PERDEDOR));

                }else pronosticos.add(new Pronostico(ronda1.partidos.get(1),equipo1,ResultadoEnum.EMPATE));


            }

        }catch (IOException e){
            System.out.println("Archivo no encontrado");
        }



        //Calculo y muestro los puntos.

        System.out.println("Puntaje= " + (puntos(pronosticos.get(0), resulPart1)+puntos(pronosticos.get(1), resulPart2)) );


    }
    public static  int puntos(Pronostico pron, ResultadoEnum res){
        if (res.toString() == pron.resultado.toString()){
            return 1;
        } else return 0;

    }
}

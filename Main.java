import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class Main {

    public static void main(String[] args) {
        List<String[]> resultados = leerResultados();
        List<String[]> pronosticos = leerPronosticos();

        System.out.println("Fase\tRonda\tNombre equipo 1\tNombre equipo 2\tGoles equipo 1\tGoles equipo 2");
        for (String[] esteResultado : resultados) {
            System.out.println(esteResultado[0] + "\t" + esteResultado[1] + "\t" + esteResultado[2] + "\t" + esteResultado[3] + "\t" + esteResultado[4] + "\t" + esteResultado[5]);
        }

        System.out.println("\n\nNombre persona\tFase\tRonda\tNombre equipo 1\tNombre equipo 2\tGanador");
        for (String[] estepronosticos : pronosticos) {
            System.out.println(estepronosticos[0] + "\t" + estepronosticos[1] + "\t" + estepronosticos[2] + "\t" + estepronosticos[3] + "\t" + estepronosticos[4] + "\t" + estepronosticos[5]);
        }

    }



    // Va a devolver una Lista con un arreglo de String que va a contener:
    // Posicion 0: Ronda
    // Posicion 1: Fase
    // Posicion 2: Nombre equipo 1
    // Posicion 3: Nombre equipo 2
    // Posicion 4: Goles equipo 1
    // Posicion 5: Goles equipo 2
    public static List<String[]> leerResultados() {
        List<String[]> resultados = new ArrayList<>();

        // Cargamos el Driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error cargando el driver");
        }

        try {
            // Creamos la conexión
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10612293",
                    "sql10612293", "ACwUKDKvbY");
            Statement stmt = con.createStatement();

            // El Query que vamos a correr
            ResultSet rs = stmt.executeQuery("SELECT FASE, RONDA, E1.EQUIPO AS EQUIPO_1, E2.EQUIPO AS EQUIPO_2, GOLES_1, GOLES_2 FROM RESULTADOS R JOIN EQUIPOS E1 on R.ID_EQUIPO_1 = E1.ID_EQUIPO JOIN EQUIPOS E2 on R.ID_EQUIPO_2 = E2.ID_EQUIPO");
            while (rs.next()) {
                String[] fila = new String[6];
                fila[0] = rs.getString("FASE");
                fila[1] = rs.getString("RONDA");
                fila[2] = rs.getString("EQUIPO_1");
                fila[3] = rs.getString("EQUIPO_2");
                fila[4] = rs.getString("GOLES_1");
                fila[5] = rs.getString("GOLES_2");
                resultados.add(fila);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error con SQL");
        }

        return resultados;
    }

    // Va a devolver una Lista con un arreglo de String que va a contener:
    // Posicion 0: Nombre de la persona
    // Posicion 1: Fase
    // Posicion 2: Ronda
    // Posicion 3: Nombre equipo 1
    // Posicion 4: Nombre equipo 2
    // Posicion 5: Ganador
    public static List<String[]> leerPronosticos() {
        List<String[]> pronosticos = new ArrayList<>();

        // Cargamos el Driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error cargando el driver");
        }

        try {
            // Creamos la conexión
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10612293",
                    "sql10612293", "ACwUKDKvbY");
            Statement stmt = con.createStatement();

            // El Query que vamos a correr
            ResultSet rs = stmt.executeQuery("SELECT NOMBRE, FASE, RONDA, E1.EQUIPO AS EQUIPO_1, E2.EQUIPO AS EQUIPO_2, GANADOR FROM PRONOSTICOS P JOIN RESULTADOS R on P.ID_RESULTADO = R.ID_RESULTADO JOIN EQUIPOS E1 on R.ID_EQUIPO_1 = E1.ID_EQUIPO JOIN EQUIPOS E2 on R.ID_EQUIPO_2 = E2.ID_EQUIPO");
            while (rs.next()) {
                String[] fila = new String[6];
                fila[0] = rs.getString("NOMBRE");
                fila[1] = rs.getString("FASE");
                fila[2] = rs.getString("RONDA");
                fila[3] = rs.getString("EQUIPO_1");
                fila[4] = rs.getString("EQUIPO_2");
                fila[5] = rs.getString("GANADOR");
                pronosticos.add(fila);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error con SQL");
        }

        return pronosticos;
    }
   /* public static String rutaResulatos = "Resultados.csv";
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

        *//*Equipo argentina=new Equipo("Argentina");
        Equipo arabia = new Equipo("Arabia Saudita");
        Equipo polonia = new Equipo("Polonia");
        Equipo mexico = new Equipo("Mexico");

        Partido partido1= new Partido("P1",argentina ,arabia,1,2);
        Partido partido2= new Partido("P2",polonia,mexico,0,0);

        ronda1.partidos.add(partido1);
        ronda1.partidos.add(partido2);*//*

        ResultadoEnum resulPart1 = ronda1.partidos.get(0).resultado(ronda1.partidos.get(0).equipo1);
        ResultadoEnum resulPart2 = ronda1.partidos.get(1).resultado(ronda1.partidos.get(1).equipo1);

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

    }*/
}

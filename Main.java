import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;


public class Main {

    public static void main(String[] args) {
        int punto=1;//se otorga un punto como default
        int puntoE=1;
        //Leo el archivo de configuracion de puntos
        List<String[]> puntos=leerConfiguracion();
        for (String[] p: puntos) {
            if (p[0].equals("punto acierto")){
                punto=Integer.parseInt(p[1]);
            } else if (p[0].equals("punto extra")) {
                puntoE=Integer.parseInt(p[1]);
            }
        }
        //Obtengo e Instancio todas las instancias de Rondas de los Resultados
        List<String[]> resultados = leerResultados();
        List<Ronda> rondas = new ArrayList<>(); // inicializo el array de rondas
        Ronda rondaActual = null; // uso esta variable de bandera para las rondas
        List<Partido> partidosActual = null; // creo la lista para almacenar los partidos
        for (String[] resultado: resultados) {
            String fase = resultado[0];
            String ronda = resultado[1];
            String equipo1 = resultado[2];
            String equipo2 = resultado[3];
            int golesEquipo1 = Integer.parseInt(resultado[4]);
            int golesEquipo2 = Integer.parseInt(resultado[5]);
            if (rondaActual == null || !rondaActual.nombre.equals(ronda)) {
                // Si la ronda actual es nula o su nombre no coincide con la ronda de la fila actual, crear una nueva ronda
                rondaActual = new Ronda(ronda,fase);
                rondas.add(rondaActual);
                partidosActual = new ArrayList<>();//inicializo la lista de partidos
                rondaActual.setPartidos(partidosActual); // Agrego los partidos a la lista
            }

            // Crear los equipos y el partido correspondiente
            Equipo equipoObj1 = new Equipo(equipo1);
            Equipo equipoObj2 = new Equipo(equipo2);
            Partido partido = new Partido(equipoObj1, equipoObj2, golesEquipo1, golesEquipo2);
            partido.resultado(equipoObj1,equipoObj2);
            partidosActual.add(partido);
        }

        //Obtengo e Instancio todos los pronosticos de los participantes.
        List<String[]> pronosticos = leerPronosticos();
        List<Ronda> rondasP = null; // declaro la lista de rondas pertenecientes a pronosticos
        List<Pronostico> pronos = new ArrayList<>(); // declaro e inicializo la lista de Pronosticos
        Ronda rondaActualP = null; // uso esta variable como bandera para las rondas
        Pronostico pronActual = null; // esta variable la uso como bandera para los pronosticos
        List<Partido> partidosActualP = null;

        for (int i = 0; i < pronosticos.size(); i++) {
            String[] pronostico = pronosticos.get(i);
            String nombre = pronostico[0];
            String fase = pronostico[1];
            String ronda = pronostico[2];
            String equipo1 = pronostico[3];
            String equipo2 = pronostico[4];
            String ganador = pronostico[5];

            //Si no existe el pronostico creo uno nuevo
            if (pronActual == null || !pronActual.nombre.equals(nombre)) {
                pronActual = new Pronostico(nombre);
                rondasP = new ArrayList<>();
                pronos.add(pronActual);
                pronActual.setRondas(rondasP);


            }
            if (rondaActualP == null || !rondaActualP.nombre.equals(ronda)) {
                rondaActualP = new Ronda(ronda,fase);
                rondasP.add(rondaActualP);
                partidosActualP = new ArrayList<>();
                rondaActualP.setPartidos(partidosActualP);
            }



                Equipo equipoObj1 = new Equipo(equipo1);
                Equipo equipoObj2 = new Equipo(equipo2);
                Partido partido = new Partido(equipoObj1, equipoObj2, ganador);
                partidosActualP.add(partido);

        }


        for (Pronostico pronost: pronos){
            int c=0;

            for (Ronda rondaP:pronost.rondas) {
                for (Ronda rondaR: rondas) {
                    if (rondaP.nombre.equals(rondaR.nombre)){
                        for (int i = 0; i < rondaP.partidos.size(); i++) {
                            if (rondaP.partidos.get(i).equipo1.nombre.equals(rondaR.partidos.get(i).equipo1.nombre) &&rondaP.partidos.get(i).equipo2.nombre.equals(rondaR.partidos.get(i).equipo2.nombre) ){
                                if (rondaP.partidos.get(i).resultadoPartido.equals(rondaR.partidos.get(i).resultadoPartido)){
                                    pronost.setPuntos(punto);
                                    pronost.aciertosExtra(rondaR.nombre);
                                    pronost.aciertosExtra(rondaR.fase);
                                }
                            }
                        }
                        pronost.puntosExtra(puntoE);
                        break;
                    }
                }
            }
            System.out.println(pronost.nombre+" acertó "+ pronost.pronAcertados +" pronosticos y obtuvo: "+ pronost.puntos+" puntos."+((pronost.puntosExtra)?" Y obtuvo puntos extra": " "));
        }



    }




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
    public static List<String[]> leerConfiguracion(){
        String rutaArchConf="configuracion.csv";
        List<String[]> puntos=new ArrayList<>();
        try {
            for (String linea : Files.readAllLines(Paths.get(rutaArchConf))){
                String[] lineaSeparada=linea.split(";");
                puntos.add(lineaSeparada);
            }
        }catch (IOException e){
            System.out.println("problema al acceder al archivo");
        }
        return puntos;
    }
}

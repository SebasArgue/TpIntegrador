public class Partido {
    public String partidoId;
    Equipo equipo1;
    Equipo equipo2;
    int golesEquipo1;
    int golesEquipo2;

    public Partido(String partidoId, Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2) {
        this.partidoId = partidoId;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
    }

    public ResultadoEnum resultado(Equipo equipo1, Equipo equipo2){

        ResultadoEnum resultadoPartido;

        if (golesEquipo1>golesEquipo2){
            resultadoPartido = new ResultadoEnum("Gana" + equipo1.nombre);
        } else if (golesEquipo2>golesEquipo1) {
            resultadoPartido = new ResultadoEnum("Gana" + equipo2.nombre);
        }else {
            resultadoPartido = new ResultadoEnum("Empate");
        }

        return resultadoPartido;
    }
}

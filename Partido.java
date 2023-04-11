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

    public ResultadoEnum resultado(Equipo equipo){

        int golesEquipoContra;
        int golesEquipo;
        ResultadoEnum resultadoPartido;
        if (equipo != this.equipo1){
            golesEquipo=this.golesEquipo2;
            golesEquipoContra=this.golesEquipo1;
        }else {
            golesEquipo=this.golesEquipo1;
            golesEquipoContra=this.golesEquipo2;
        }


        if (golesEquipo>golesEquipoContra){
            resultadoPartido = ResultadoEnum.GANADOR;
        } else if (golesEquipoContra>golesEquipo) {
            resultadoPartido = ResultadoEnum.PERDEDOR;
        }else {
            resultadoPartido = ResultadoEnum.EMPATE;
        }

        return resultadoPartido;
    }
}

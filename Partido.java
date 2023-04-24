public class Partido {

    Equipo equipo1;
    Equipo equipo2;
    int golesEquipo1;
    int golesEquipo2;
    String j;

    ResultadoEnum resultadoPartido;

    public Partido( Equipo equipo1, Equipo equipo2, int golesEquipo1, int golesEquipo2) {

        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
    }

    public Partido(Equipo equipo1, Equipo equipo2,String resultado) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.j=resultado;

        if (resultado.equals("1")){
          this.resultadoPartido= ResultadoEnum.GANA_1;
        } else if (resultado.equals("2")) {
            this.resultadoPartido=ResultadoEnum.GANA_2;
        }else {
            this.resultadoPartido=ResultadoEnum.EMPATE;
        }

    }

    public Void resultado(Equipo equipo1, Equipo equipo2){

       ResultadoEnum resultadoPartido;
        if (golesEquipo1>golesEquipo2){
            resultadoPartido = ResultadoEnum.GANA_1;
        } else if (golesEquipo2>golesEquipo1) {
            resultadoPartido = ResultadoEnum.GANA_2;
        }else {
            resultadoPartido = ResultadoEnum.EMPATE;
        }
        this.resultadoPartido = resultadoPartido;
        return null;
    }

    @Override
    public String toString() {
        return "Partido{" +
                "equipo1=" + equipo1.toString() +
                ", equipo2=" + equipo2.toString() +
                ", resultadoPartido=" + resultadoPartido +
                '}';
    }

}

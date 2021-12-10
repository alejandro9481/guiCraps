package juegoCraps;


/**
 * ModelCraps apply craps rules.
 * estado = 1 Natural winner
 * estado = 2 Craps Loser
 * estado = 3 Stablish Punto
 * estado = 4 Punto winner
 * estado = 5 Punto Loser
 * @author Alejandro Villamil
 * @version v.1.0.0 date 09/12/2021
 */
public class ModelCraps {
    private Dado dado1,dado2;

    private int tiro,punto,estado,flag;
    private String[] estadoToString;
    private int[] caras;


    /**
     * Class Constructor
     */
    public ModelCraps(){
        dado1 = new Dado();
        dado2 = new Dado();
        caras = new int[2];
        estadoToString = new String[2];
        flag = 0;
    }

    /**
     * Establish the tiro value according to each dice
     */
    public void calcularTiro(){
        caras[0] = dado1.getCara();
        caras[1] = dado2.getCara();
        tiro = caras[0] + caras[1];
    }


    /**
     * Establish the game state acording to estado atributte value.
     * estado = 1 Natural winner
     * estado = 2 Craps Loser
     * estado = 3 Stablish Punto
     */
    public void determinarJuego(){
        if(flag == 0){
            if(tiro == 7 || tiro == 11){
                estado = 1;
            }else{
                if(tiro == 3 || tiro == 2 || tiro == 12){
                    estado = 2;
                }else{
                    estado = 3;
                    punto = tiro;
                    flag = 1;
                }
            }
        }else{
            //ronda punto
            rondaPunto();

        }

    }

    /**
     * Establish game state according to estado atributte value
     * estado = 4 Punto winner
     * estado = 5 Punto Loser
     */
    private void rondaPunto() {
        if(tiro == punto){
            estado = 4;
            flag = 0;
        }
        else{
            if(tiro == 7){
                estado = 5;
                flag = 0;
            }else{
                estado = 6;
            }
        }

    }

    public int getTiro() {
        return tiro;
    }

    public int getPunto() {
        return punto;
    }

    /**
     * Establish message game state accodding to estado atributte value
     * @return Message fot the View class
     */
    public String[] getEstadoToString() {
        switch (estado){
            case 1:
                estadoToString[0] = "Tiro de salida = "+tiro;
                estadoToString[1] = "¡¡Sacaste Natural, Has ganado!!";
                break;
            case 2:
                estadoToString[0] = "Tiro de salida = "+tiro;
                estadoToString[1] = "¡¡Sacaste Craps, Has perdido!!";
                break;
            case 3:
                estadoToString[0] = "Tiro de salida = "+tiro
                        +"\nPunto = "+punto;
                estadoToString[1] = "¡¡Estableciste Punto en "+ punto +" Debes seguir lanzando!!" +
            "\n pero si sacas 7 antes que "+punto+" perderás";
                break;
            case 4:
                estadoToString[0] = "Tiro de salida = "+punto
                        +"\nPunto = "+punto
                        +"\nValor del nuevo tiro = "+tiro;
                estadoToString[1] = "¡¡Volviste a sacar "+punto+", Has perdido!!";
                break;
            case 5:
                estadoToString[0] = "Tiro de salida = "+punto
                        +"\nPunto = "+punto
                        +"\nValor del nuevo tiro = "+tiro;
                estadoToString[1] = "¡¡Sacaste 7 antes que "+punto+", Has perdido!!";
                break;
            case 6:
                estadoToString[0] = "Tiro de salida = "+punto
                        +"\nPunto = "+punto
                        +"\nValor del nuevo tiro = "+tiro;
                estadoToString[1] = "\nEstan en punto y debes seguir lanzando!!" +
                        "\n pero si sacas 7 antes que "+punto+" perderás";
                break;
        }
        return estadoToString;
    }

    public int[] getCaras() {
        return caras;
    }
}

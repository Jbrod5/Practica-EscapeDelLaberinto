//Jorge Anibal Bravo Rodríguez  Carné: 202131782
package com.jbravo.escapedellaberinto;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class escapeDelLaberinto {

    static String[][][] mapaPrincipal = new String[30][30][2]; // [x][y][cantidad] cantidad -> usada como atributo de oro y salida
    static String[][][] mapaRanuraA   = new String[15][15][2]; //por defecto los mapas creados por usuarios son de tamaño fijo 15 x 15
    static String[][][] mapaRanuraB   = new String[15][15][2];
    static String       nombreRanuraA;                         //variables para mapas
    static String       nombreRanuraB;
    static int monedasRequeridasA = 0;
    static int monedasRequeridasB = 0;
    static int contRanura         = 0;
    static int contTurnoJugador   = 0;
    static int conTurnoBot        = 0;

    static int [][] contMapaPrincipal = new int[1][3]; //0,0 veces que se juega el mapa; 0, 1 veces ganadas; 0,2 veces perdidas
    static int [][] contMapaA         = new int[1][3];
    static int [][] contMapaB         = new int[1][3];

    static String [][][] mapaEnUso30x30 = new String[30][30][2]; //variables de juego
    static String [][][] mapaEnUso15x15 = new String[15][15][2];

    static int           confGrafica = 1;//1 sin vista por turnos, 2 vista parcial, 3 vista total
    static int               contOro = 0; //cuenta la cantidad de oro recolectado
    static int         cantPlayerWin = 0;
    static int            cantBotWin = 0;
    static int     cantBotViewPlayer = 0;
    static int           contPartida = 0;
    static int          contOroTotal = 0;
    static int          contMovTotal = 0;
    static boolean    findPlayer = false; //indica si el jugador se encuentra en rango 5x5 desde el bot
    static int                 dirMovBot; //0 arriba; 1 derecha; 2 abajo; 3 izquierda
    static boolean      finjuego = false;

    // CONTROLES (son declarados como constantes por comodidad de testeo)
    final static String arriba     = "w";
    final static String abajo      = "s";
    final static String izquierda  = "a";
    final static String derecha    = "d";

    final static String coger      = "c";
    final static String mirMapa    = "o";
    final static String salir      = "e";
    final static String oroDisp    = "i";

    //----------- variables usadas para construir los mapas ----------
    static String wall =  " # "; //en el documento se especifica -> #
    static String esVa =  "   "; //en el documento se especifica -> O
    static String coin =  " - "; //en el documento se especifica -> G
    static String play =  " J "; //en el documento se especifica -> J
    static String bot  =  " B "; //en el documento se especifica -> B
    static String exit =  " S "; //en el documento se especifica -> S

    static int []  cooPlay = new int[2]; //coordenadas jugador, 0 es X; 1 es Y
    static int []  cooBot  = new int[2]; //coordenadas bot      0 es X; 1 es Y
    static int monedasRequeridas = 0;
    static int idMapa       = 0; //mapa con el que se trabaja; 0 -> Mapa Default; 1 -> mapa A; 2 -> mapa B
    static int limitador    = 0; //limita a 15 o 30 las casillas disponibles según se requiera
    static int esperaBot =  2000;

    public static void main (String[] args){

       Scanner scanner = new Scanner(System.in);
        rellenarMapaPrincipal();
        inicializarMapas();
        inicializarContadores();
        int opMenuPrincipal;
        do{
           // scanner.nextLine();
            System.out.println(" - BIENVENIDO AL JUEGO ESCAPE DEL LABERITNO - ");
            System.out.println("Por favor ingrese la opción que desee realizar");
            System.out.println("1 - - - - - - - - - - - - - - - - - - - -Jugar");
            System.out.println("2 - - - - - - - - - - - - - - - - - Crear mapa");
            System.out.println("3 - - - - - - - - - - - - - - - - - - Reportes");
            System.out.println("4 - - - - - - - - - - - - -Previsualizar mapas");
            System.out.println("5 - - - - - - - - - -Configuraciones generales");
            System.out.println("6 - - - - - - - - - - - - - - - - - - - -Salir");
            opMenuPrincipal = scanner.nextInt();
            scanner.nextLine(); //para evitar problemas al guardar lineas enteras posteriormente

            switch (opMenuPrincipal){
                case 1:
                    limpiarPantalla();
                    int opcion;
                    System.out.println("Por favor, ingrese el mapa que desea jugar");
                    switch(contRanura){
                        case 0:
                            System.out.println(" 1 - Mapa por defecto");
                            opcion = scanner.nextInt();
                            scanner.nextLine();
                            switch (opcion){
                                case 1:
                                    idMapa = 0;
                                    llamadasJugar();
                                    break;
                                default:
                                    System.out.println("El dato ingresado no es válido");
                                    break;
                            }
                            break;
                        case 1:
                            System.out.println("1 - Mapa por defecto");
                            System.out.println("2 - " + nombreRanuraA);
                            opcion = scanner.nextInt();
                            scanner.nextLine();
                            switch (opcion){
                                case 1:
                                    idMapa = 0;
                                    llamadasJugar();
                                    break;
                                case 2:
                                    idMapa = 1;
                                    llamadasJugar();
                                    break;
                                default:
                                    System.out.println("El dato ingresado no es válido");
                                    break;
                            }
                            break;
                        case 2:
                            System.out.println("1 - Mapa por defecto");
                            System.out.println("2 - " + nombreRanuraA);
                            System.out.println("3 - " + nombreRanuraB);
                            opcion = scanner.nextInt();
                            scanner.nextLine();
                            switch (opcion){
                                case 1:
                                    idMapa = 0;
                                    llamadasJugar();
                                    break;
                                case 2:
                                    idMapa = 1;
                                    llamadasJugar();
                                    break;
                                case 3:
                                    idMapa = 2;
                                    llamadasJugar();
                                    break;
                                default:
                                    System.out.println("El dato ingresado no es válido");
                                    break;
                            }
                            break;
                    }
                    break;
                case 2:
                    if(contRanura < 3){
                        crearMapa();    
                    }else{
                        System.out.println("Solamente puede crear dos mapas, opción no válida");
                    }
                    break;
                case 3:
                    reporteGeneral();
                    break;
                case 4:
                    limpiarPantalla();
                    System.out.println("Estos son los mapas que puede consultar");
                    switch(contRanura){
                        case 0:
                            System.out.println(" 1 - Mapa por defecto");
                            opcion = scanner.nextInt();
                            scanner.nextLine();
                            switch (opcion){
                                case 1:
                                    idMapa = 0;
                                    dibMapa();
                                    break;
                                default:
                                    System.out.println("El dato ingresado no es válido");
                                    break;
                            }
                            break;
                        case 1:
                            System.out.println("1 - Mapa por defecto");
                            System.out.println("2 - " + nombreRanuraA);
                            opcion = scanner.nextInt();
                            scanner.nextLine();
                            switch (opcion){
                                case 1:
                                    idMapa = 0;
                                    dibMapa();
                                    break;
                                case 2:
                                    idMapa = 1;
                                    dibMapa();
                                    break;
                                default:
                                    System.out.println("El dato ingresado no es válido");
                                    break;
                            }
                            break;
                        case 2:
                            System.out.println("1 - Mapa por defecto");
                            System.out.println("2 - " + nombreRanuraA);
                            System.out.println("3 - " + nombreRanuraB);
                            opcion = scanner.nextInt();
                            scanner.nextLine();
                            switch (opcion){
                                case 1:
                                    idMapa = 0;
                                    dibMapa();
                                    break;
                                case 2:
                                    idMapa = 1;
                                    dibMapa();
                                    break;
                                case 3:
                                    idMapa = 2;
                                    dibMapa();
                                    break;
                                default:
                                    System.out.println("El dato ingresado no es válido");
                                    break;
                            }
                            break;
                    }
                    break;
                case 5:
                    int opConf = 0;
                    System.out.println("------------ ¿Qué desea configurar? -----------");
                    System.out.println("1. - - - - - - - - - - Vista de mapa por turnos");
                    System.out.println("2. - - - - - - Cambiar tiempo de espera del bot");
                    System.out.println("3. - -Conservar la configuración actual y salir");
                    opConf = scanner.nextInt();
                    scanner.nextLine();
                    switch(opConf){
                        case 1:
                            confMapaView();
                            break;
                        case 2:
                            confTiempoBot();
                            break;
                        default:
                            System.out.println("Se mantendrán las configuraciones actuales");
                            break;
                    }       
                    break;
                case 6:
                    System.out.println("Gracias por jugar Escape del Laberinto");
                    break;
                default:
                    System.out.println("El dato ingresado no se encuentra dentro de las opciones disponibles");
                    System.out.println("Por favor intentelo de nuevo ");
                    break;
            }
        }while(opMenuPrincipal != 6);
    }

    public static void limpiarPantalla() {
        try {
            String OS = System.getProperty("os.name");
            ArrayList<String> comando = new ArrayList<String>();
            if (OS.contains("Windows")) { //Si es windows ejecutar cls
                comando.add("cmd");
                comando.add("/C");
                comando.add("cls");
            } else {
                comando.add("clear"); //Si no es windows ejecutar clear
            }
            ProcessBuilder pb = new ProcessBuilder(comando);
            Process iniciarProceso = pb.inheritIO().start();
            iniciarProceso.waitFor();
        } catch (Exception e) {
            System.err.println("Error al limpiar pantalla " + e.getMessage());
        }
    } //Metodo para limpiar pantalla
    public static void inicializarMapas(){
        for (int y = 0; y < 15; y++){
            for (int x = 0; x < 15; x++ ){
                mapaRanuraA[x][y][1] = "0";//3 identificador de casilla normal
                mapaRanuraB[x][y][1] = "0";
            }
        }
        for (int y = 0; y < 30; y++){
            for (int x = 0; x < 30; x++){
                mapaPrincipal[x][y][1] = "0";
            }
        }
        mapaPrincipal[ 0][ 1][ 1] = "15";
        mapaPrincipal[ 8][ 0][ 1] = "30";
        mapaPrincipal[13][29][ 1] = "43";
        mapaPrincipal[29][ 1][ 1] = "51";
        mapaPrincipal[29][21][ 1] = "55";
    }//para que no inicien en null todos son = 3, identificador de casilla normal
    public static void rellenarMapaPrincipal(){
        mapaPrincipal[ 0][ 0][ 0] = wall; mapaPrincipal [ 1][ 0][ 0] = wall;         mapaPrincipal [ 2][ 0][ 0] = wall; mapaPrincipal [ 3][ 0][ 0] = wall; mapaPrincipal [ 4][ 0][ 0] = wall; mapaPrincipal [ 5][ 0][ 0] = wall; mapaPrincipal [ 6][ 0][ 0] = wall; mapaPrincipal [ 7][ 0][ 0] = wall; mapaPrincipal [ 8][ 0][ 0] = exit; mapaPrincipal [ 9][ 0][ 0] = wall; mapaPrincipal [10][ 0][ 0] = wall; mapaPrincipal [11][ 0][ 0] = wall; mapaPrincipal [12][ 0][ 0] = wall; mapaPrincipal [13][ 0][ 0] = wall; mapaPrincipal [14][ 0][ 0] = wall; mapaPrincipal [15][ 0][ 0] = wall; mapaPrincipal [16][ 0][ 0] = wall; mapaPrincipal [17][ 0][ 0] = wall; mapaPrincipal [18][ 0][ 0] = wall; mapaPrincipal [19][ 0][ 0] = wall; mapaPrincipal [20][ 0][ 0] = wall; mapaPrincipal [21][ 0][ 0] = wall; mapaPrincipal [22][ 0][ 0] = wall; mapaPrincipal [23][ 0][ 0] = wall; mapaPrincipal [24][ 0][ 0] = wall; mapaPrincipal [25][ 0][ 0] = wall; mapaPrincipal [26][ 0][ 0] = wall; mapaPrincipal [27][ 0][ 0] = wall; mapaPrincipal [28][ 0][ 0] = wall; mapaPrincipal [29][ 0][ 0] = wall;
        mapaPrincipal[ 0][ 1][ 0] = exit; mapaPrincipal [ 1][ 1][ 0] = esVa;         mapaPrincipal [ 2][ 1][ 0] = esVa; mapaPrincipal [ 3][ 1][ 0] = esVa; mapaPrincipal [ 4][ 1][ 0] = esVa; mapaPrincipal [ 5][ 1][ 0] = esVa; mapaPrincipal [ 6][ 1][ 0] = esVa; mapaPrincipal [ 7][ 1][ 0] = wall; mapaPrincipal [ 8][ 1][ 0] = esVa; mapaPrincipal [ 9][ 1][ 0] = esVa; mapaPrincipal [10][ 1][ 0] = esVa; mapaPrincipal [11][ 1][ 0] = esVa; mapaPrincipal [12][ 1][ 0] = esVa; mapaPrincipal [13][ 1][ 0] = esVa; mapaPrincipal [14][ 1][ 0] = esVa; mapaPrincipal [15][ 1][ 0] = esVa; mapaPrincipal [16][ 1][ 0] = esVa; mapaPrincipal [17][ 1][ 0] = wall; mapaPrincipal [18][ 1][ 0] = esVa; mapaPrincipal [19][ 1][ 0] = esVa; mapaPrincipal [20][ 1][ 0] = esVa; mapaPrincipal [21][ 1][ 0] = esVa; mapaPrincipal [22][ 1][ 0] = esVa; mapaPrincipal [23][ 1][ 0] = esVa; mapaPrincipal [24][ 1][ 0] = esVa; mapaPrincipal [25][ 1][ 0] = esVa; mapaPrincipal [26][ 1][ 0] = wall; mapaPrincipal [27][ 1][ 0] = esVa; mapaPrincipal [28][ 1][ 0] = esVa; mapaPrincipal [29][ 1][ 0] = exit;
        mapaPrincipal[ 0][ 2][ 0] = wall; mapaPrincipal [ 1][ 2][ 0] = wall;         mapaPrincipal [ 2][ 2][ 0] = wall; mapaPrincipal [ 3][ 2][ 0] = wall; mapaPrincipal [ 4][ 2][ 0] = wall; mapaPrincipal [ 5][ 2][ 0] = wall; mapaPrincipal [ 6][ 2][ 0] = esVa; mapaPrincipal [ 7][ 2][ 0] = esVa; mapaPrincipal [ 8][ 2][ 0] = wall; mapaPrincipal [ 9][ 2][ 0] = wall; mapaPrincipal [10][ 2][ 0] = wall; mapaPrincipal [11][ 2][ 0] = esVa; mapaPrincipal [12][ 2][ 0] = wall; mapaPrincipal [13][ 2][ 0] = wall; mapaPrincipal [14][ 2][ 0] = wall; mapaPrincipal [15][ 2][ 0] = esVa; mapaPrincipal [16][ 2][ 0] = wall; mapaPrincipal [17][ 2][ 0] = esVa; mapaPrincipal [18][ 2][ 0] = esVa; mapaPrincipal [19][ 2][ 0] = wall; mapaPrincipal [20][ 2][ 0] = wall; mapaPrincipal [21][ 2][ 0] = wall; mapaPrincipal [22][ 2][ 0] = wall; mapaPrincipal [23][ 2][ 0] = wall; mapaPrincipal [24][ 2][ 0] = wall; mapaPrincipal [25][ 2][ 0] = esVa; mapaPrincipal [26][ 2][ 0] = esVa; mapaPrincipal [27][ 2][ 0] = wall; mapaPrincipal [28][ 2][ 0] = esVa; mapaPrincipal [29][ 2][ 0] = wall;
        mapaPrincipal[ 0][ 3][ 0] = wall; mapaPrincipal [ 1][ 3][ 0] = esVa;         mapaPrincipal [ 2][ 3][ 0] = esVa; mapaPrincipal [ 3][ 3][ 0] = coin; mapaPrincipal [ 4][ 3][ 0] = esVa; mapaPrincipal [ 5][ 3][ 0] = wall; mapaPrincipal [ 6][ 3][ 0] = esVa; mapaPrincipal [ 7][ 3][ 0] = wall; mapaPrincipal [ 8][ 3][ 0] = coin; mapaPrincipal [ 9][ 3][ 0] = coin; mapaPrincipal [10][ 3][ 0] = wall; mapaPrincipal [11][ 3][ 0] = esVa; mapaPrincipal [12][ 3][ 0] = wall; mapaPrincipal [13][ 3][ 0] = coin; mapaPrincipal [14][ 3][ 0] = wall; mapaPrincipal [15][ 3][ 0] = esVa; mapaPrincipal [16][ 3][ 0] = wall; mapaPrincipal [17][ 3][ 0] = coin; mapaPrincipal [18][ 3][ 0] = wall; mapaPrincipal [19][ 3][ 0] = esVa; mapaPrincipal [20][ 3][ 0] = wall; mapaPrincipal [21][ 3][ 0] = esVa; mapaPrincipal [22][ 3][ 0] = esVa; mapaPrincipal [23][ 3][ 0] = esVa; mapaPrincipal [24][ 3][ 0] = wall; mapaPrincipal [25][ 3][ 0] = wall; mapaPrincipal [26][ 3][ 0] = esVa; mapaPrincipal [27][ 3][ 0] = esVa; mapaPrincipal [28][ 3][ 0] = esVa; mapaPrincipal [29][ 3][ 0] = wall;
        mapaPrincipal[ 0][ 4][ 0] = wall; mapaPrincipal [ 1][ 4][ 0] = esVa;         mapaPrincipal [ 2][ 4][ 0] = wall; mapaPrincipal [ 3][ 4][ 0] = wall; mapaPrincipal [ 4][ 4][ 0] = esVa; mapaPrincipal [ 5][ 4][ 0] = wall; mapaPrincipal [ 6][ 4][ 0] = esVa; mapaPrincipal [ 7][ 4][ 0] = esVa; mapaPrincipal [ 8][ 4][ 0] = esVa; mapaPrincipal [ 9][ 4][ 0] = esVa; mapaPrincipal [10][ 4][ 0] = wall; mapaPrincipal [11][ 4][ 0] = coin; mapaPrincipal [12][ 4][ 0] = wall; mapaPrincipal [13][ 4][ 0] = esVa; mapaPrincipal [14][ 4][ 0] = wall; mapaPrincipal [15][ 4][ 0] = esVa; mapaPrincipal [16][ 4][ 0] = wall; mapaPrincipal [17][ 4][ 0] = wall; mapaPrincipal [18][ 4][ 0] = esVa; mapaPrincipal [19][ 4][ 0] = esVa; mapaPrincipal [20][ 4][ 0] = wall; mapaPrincipal [21][ 4][ 0] = esVa; mapaPrincipal [22][ 4][ 0] = wall; mapaPrincipal [23][ 4][ 0] = esVa; mapaPrincipal [24][ 4][ 0] = esVa; mapaPrincipal [25][ 4][ 0] = wall; mapaPrincipal [26][ 4][ 0] = esVa; mapaPrincipal [27][ 4][ 0] = wall; mapaPrincipal [28][ 4][ 0] = esVa; mapaPrincipal [29][ 4][ 0] = wall;
        mapaPrincipal[ 0][ 5][ 0] = wall; mapaPrincipal [ 1][ 5][ 0] = coin;         mapaPrincipal [ 2][ 5][ 0] = wall; mapaPrincipal [ 3][ 5][ 0] = esVa; mapaPrincipal [ 4][ 5][ 0] = esVa; mapaPrincipal [ 5][ 5][ 0] = wall; mapaPrincipal [ 6][ 5][ 0] = esVa; mapaPrincipal [ 7][ 5][ 0] = wall; mapaPrincipal [ 8][ 5][ 0] = wall; mapaPrincipal [ 9][ 5][ 0] = wall; mapaPrincipal [10][ 5][ 0] = wall; mapaPrincipal [11][ 5][ 0] = wall; mapaPrincipal [12][ 5][ 0] = wall; mapaPrincipal [13][ 5][ 0] = esVa; mapaPrincipal [14][ 5][ 0] = wall; mapaPrincipal [15][ 5][ 0] = esVa; mapaPrincipal [16][ 5][ 0] = esVa; mapaPrincipal [17][ 5][ 0] = esVa; mapaPrincipal [18][ 5][ 0] = wall; mapaPrincipal [19][ 5][ 0] = esVa; mapaPrincipal [20][ 5][ 0] = wall; mapaPrincipal [21][ 5][ 0] = esVa; mapaPrincipal [22][ 5][ 0] = wall; mapaPrincipal [23][ 5][ 0] = wall; mapaPrincipal [24][ 5][ 0] = wall; mapaPrincipal [25][ 5][ 0] = wall; mapaPrincipal [26][ 5][ 0] = esVa; mapaPrincipal [27][ 5][ 0] = wall; mapaPrincipal [28][ 5][ 0] = esVa; mapaPrincipal [29][ 5][ 0] = wall;
        mapaPrincipal[ 0][ 6][ 0] = wall; mapaPrincipal [ 1][ 6][ 0] = esVa;         mapaPrincipal [ 2][ 6][ 0] = wall; mapaPrincipal [ 3][ 6][ 0] = wall; mapaPrincipal [ 4][ 6][ 0] = esVa; mapaPrincipal [ 5][ 6][ 0] = esVa; mapaPrincipal [ 6][ 6][ 0] = esVa; mapaPrincipal [ 7][ 6][ 0] = esVa; mapaPrincipal [ 8][ 6][ 0] = esVa; mapaPrincipal [ 9][ 6][ 0] = esVa; mapaPrincipal [10][ 6][ 0] = wall; mapaPrincipal [11][ 6][ 0] = esVa; mapaPrincipal [12][ 6][ 0] = wall; mapaPrincipal [13][ 6][ 0] = esVa; mapaPrincipal [14][ 6][ 0] = esVa; mapaPrincipal [15][ 6][ 0] = esVa; mapaPrincipal [16][ 6][ 0] = esVa; mapaPrincipal [17][ 6][ 0] = esVa; mapaPrincipal [18][ 6][ 0] = esVa; mapaPrincipal [19][ 6][ 0] = esVa; mapaPrincipal [20][ 6][ 0] = esVa; mapaPrincipal [21][ 6][ 0] = esVa; mapaPrincipal [22][ 6][ 0] = wall; mapaPrincipal [23][ 6][ 0] = esVa; mapaPrincipal [24][ 6][ 0] = esVa; mapaPrincipal [25][ 6][ 0] = esVa; mapaPrincipal [26][ 6][ 0] = esVa; mapaPrincipal [27][ 6][ 0] = wall; mapaPrincipal [28][ 6][ 0] = wall; mapaPrincipal [29][ 6][ 0] = wall;
        mapaPrincipal[ 0][ 7][ 0] = wall; mapaPrincipal [ 1][ 7][ 0] = wall;         mapaPrincipal [ 2][ 7][ 0] = esVa; mapaPrincipal [ 3][ 7][ 0] = esVa; mapaPrincipal [ 4][ 7][ 0] = esVa; mapaPrincipal [ 5][ 7][ 0] = wall; mapaPrincipal [ 6][ 7][ 0] = esVa; mapaPrincipal [ 7][ 7][ 0] = wall; mapaPrincipal [ 8][ 7][ 0] = esVa; mapaPrincipal [ 9][ 7][ 0] = wall; mapaPrincipal [10][ 7][ 0] = esVa; mapaPrincipal [11][ 7][ 0] = esVa; mapaPrincipal [12][ 7][ 0] = wall; mapaPrincipal [13][ 7][ 0] = wall; mapaPrincipal [14][ 7][ 0] = esVa; mapaPrincipal [15][ 7][ 0] = wall; mapaPrincipal [16][ 7][ 0] = wall; mapaPrincipal [17][ 7][ 0] = esVa; mapaPrincipal [18][ 7][ 0] = wall; mapaPrincipal [19][ 7][ 0] = wall; mapaPrincipal [20][ 7][ 0] = wall; mapaPrincipal [21][ 7][ 0] = wall; mapaPrincipal [22][ 7][ 0] = wall; mapaPrincipal [23][ 7][ 0] = esVa; mapaPrincipal [24][ 7][ 0] = wall; mapaPrincipal [25][ 7][ 0] = esVa; mapaPrincipal [26][ 7][ 0] = wall; mapaPrincipal [27][ 7][ 0] = wall; mapaPrincipal [28][ 7][ 0] = coin; mapaPrincipal [29][ 7][ 0] = wall;
        mapaPrincipal[ 0][ 8][ 0] = wall; mapaPrincipal [ 1][ 8][ 0] = esVa;         mapaPrincipal [ 2][ 8][ 0] = esVa; mapaPrincipal [ 3][ 8][ 0] = wall; mapaPrincipal [ 4][ 8][ 0] = esVa; mapaPrincipal [ 5][ 8][ 0] = wall; mapaPrincipal [ 6][ 8][ 0] = esVa; mapaPrincipal [ 7][ 8][ 0] = esVa; mapaPrincipal [ 8][ 8][ 0] = esVa; mapaPrincipal [ 9][ 8][ 0] = wall; mapaPrincipal [10][ 8][ 0] = esVa; mapaPrincipal [11][ 8][ 0] = wall; mapaPrincipal [12][ 8][ 0] = esVa; mapaPrincipal [13][ 8][ 0] = wall; mapaPrincipal [14][ 8][ 0] = esVa; mapaPrincipal [15][ 8][ 0] = esVa; mapaPrincipal [16][ 8][ 0] = wall; mapaPrincipal [17][ 8][ 0] = esVa; mapaPrincipal [18][ 8][ 0] = wall; mapaPrincipal [19][ 8][ 0] = esVa; mapaPrincipal [20][ 8][ 0] = esVa; mapaPrincipal [21][ 8][ 0] = esVa; mapaPrincipal [22][ 8][ 0] = esVa; mapaPrincipal [23][ 8][ 0] = esVa; mapaPrincipal [24][ 8][ 0] = wall; mapaPrincipal [25][ 8][ 0] = esVa; mapaPrincipal [26][ 8][ 0] = esVa; mapaPrincipal [27][ 8][ 0] = wall; mapaPrincipal [28][ 8][ 0] = coin; mapaPrincipal [29][ 8][ 0] = wall;
        mapaPrincipal[ 0][ 9][ 0] = wall; mapaPrincipal [ 1][ 9][ 0] = esVa;         mapaPrincipal [ 2][ 9][ 0] = wall; mapaPrincipal [ 3][ 9][ 0] = wall; mapaPrincipal [ 4][ 9][ 0] = esVa; mapaPrincipal [ 5][ 9][ 0] = wall; mapaPrincipal [ 6][ 9][ 0] = wall; mapaPrincipal [ 7][ 9][ 0] = wall; mapaPrincipal [ 8][ 9][ 0] = esVa; mapaPrincipal [ 9][ 9][ 0] = wall; mapaPrincipal [10][ 9][ 0] = esVa; mapaPrincipal [11][ 9][ 0] = wall; mapaPrincipal [12][ 9][ 0] = esVa; mapaPrincipal [13][ 9][ 0] = wall; mapaPrincipal [14][ 9][ 0] = wall; mapaPrincipal [15][ 9][ 0] = esVa; mapaPrincipal [16][ 9][ 0] = wall; mapaPrincipal [17][ 9][ 0] = esVa; mapaPrincipal [18][ 9][ 0] = wall; mapaPrincipal [19][ 9][ 0] = wall; mapaPrincipal [20][ 9][ 0] = wall; mapaPrincipal [21][ 9][ 0] = esVa; mapaPrincipal [22][ 9][ 0] = wall; mapaPrincipal [23][ 9][ 0] = wall; mapaPrincipal [24][ 9][ 0] = wall; mapaPrincipal [25][ 9][ 0] = wall; mapaPrincipal [26][ 9][ 0] = coin; mapaPrincipal [27][ 9][ 0] = wall; mapaPrincipal [28][ 9][ 0] = esVa; mapaPrincipal [29][ 9][ 0] = wall;
        mapaPrincipal[ 0][10][ 0] = wall; mapaPrincipal [ 1][10][ 0] = esVa;         mapaPrincipal [ 2][10][ 0] = esVa; mapaPrincipal [ 3][10][ 0] = esVa; mapaPrincipal [ 4][10][ 0] = esVa; mapaPrincipal [ 5][10][ 0] = wall; mapaPrincipal [ 6][10][ 0] = esVa; mapaPrincipal [ 7][10][ 0] = wall; mapaPrincipal [ 8][10][ 0] = esVa; mapaPrincipal [ 9][10][ 0] = wall; mapaPrincipal [10][10][ 0] = esVa; mapaPrincipal [11][10][ 0] = wall; mapaPrincipal [12][10][ 0] = esVa; mapaPrincipal [13][10][ 0] = wall; mapaPrincipal [14][10][ 0] = esVa; mapaPrincipal [15][10][ 0] = esVa; mapaPrincipal [16][10][ 0] = wall; mapaPrincipal [17][10][ 0] = esVa; mapaPrincipal [18][10][ 0] = esVa; mapaPrincipal [19][10][ 0] = coin; mapaPrincipal [20][10][ 0] = wall; mapaPrincipal [21][10][ 0] = esVa; mapaPrincipal [22][10][ 0] = esVa; mapaPrincipal [23][10][ 0] = wall; mapaPrincipal [24][10][ 0] = esVa; mapaPrincipal [25][10][ 0] = wall; mapaPrincipal [26][10][ 0] = esVa; mapaPrincipal [27][10][ 0] = wall; mapaPrincipal [28][10][ 0] = esVa; mapaPrincipal [29][10][ 0] = wall;
        mapaPrincipal[ 0][11][ 0] = wall; mapaPrincipal [ 1][11][ 0] = wall;         mapaPrincipal [ 2][11][ 0] = wall; mapaPrincipal [ 3][11][ 0] = coin; mapaPrincipal [ 4][11][ 0] = wall; mapaPrincipal [ 5][11][ 0] = esVa; mapaPrincipal [ 6][11][ 0] = esVa; mapaPrincipal [ 7][11][ 0] = esVa; mapaPrincipal [ 8][11][ 0] = esVa; mapaPrincipal [ 9][11][ 0] = wall; mapaPrincipal [10][11][ 0] = esVa; mapaPrincipal [11][11][ 0] = wall; mapaPrincipal [12][11][ 0] = esVa; mapaPrincipal [13][11][ 0] = wall; mapaPrincipal [14][11][ 0] = wall; mapaPrincipal [15][11][ 0] = esVa; mapaPrincipal [16][11][ 0] = wall; mapaPrincipal [17][11][ 0] = wall; mapaPrincipal [18][11][ 0] = wall; mapaPrincipal [19][11][ 0] = wall; mapaPrincipal [20][11][ 0] = wall; mapaPrincipal [21][11][ 0] = esVa; mapaPrincipal [22][11][ 0] = wall; mapaPrincipal [23][11][ 0] = esVa; mapaPrincipal [24][11][ 0] = wall; mapaPrincipal [25][11][ 0] = wall; mapaPrincipal [26][11][ 0] = esVa; mapaPrincipal [27][11][ 0] = wall; mapaPrincipal [28][11][ 0] = esVa; mapaPrincipal [29][11][ 0] = wall;
        mapaPrincipal[ 0][12][ 0] = wall; mapaPrincipal [ 1][12][ 0] = esVa/*play*/; mapaPrincipal [ 2][12][ 0] = esVa; mapaPrincipal [ 3][12][ 0] = wall; mapaPrincipal [ 4][12][ 0] = esVa; mapaPrincipal [ 5][12][ 0] = esVa; mapaPrincipal [ 6][12][ 0] = wall; mapaPrincipal [ 7][12][ 0] = wall; mapaPrincipal [ 8][12][ 0] = wall; mapaPrincipal [ 9][12][ 0] = esVa; mapaPrincipal [10][12][ 0] = esVa; mapaPrincipal [11][12][ 0] = wall; mapaPrincipal [12][12][ 0] = esVa; mapaPrincipal [13][12][ 0] = wall; mapaPrincipal [14][12][ 0] = esVa; mapaPrincipal [15][12][ 0] = esVa; mapaPrincipal [16][12][ 0] = esVa; mapaPrincipal [17][12][ 0] = esVa; mapaPrincipal [18][12][ 0] = esVa; mapaPrincipal [19][12][ 0] = esVa; mapaPrincipal [20][12][ 0] = esVa; mapaPrincipal [21][12][ 0] = esVa; mapaPrincipal [22][12][ 0] = esVa; mapaPrincipal [23][12][ 0] = esVa; mapaPrincipal [24][12][ 0] = esVa; mapaPrincipal [25][12][ 0] = wall; mapaPrincipal [26][12][ 0] = esVa; mapaPrincipal [27][12][ 0] = wall; mapaPrincipal [28][12][ 0] = esVa; mapaPrincipal [29][12][ 0] = wall;
        mapaPrincipal[ 0][13][ 0] = wall; mapaPrincipal [ 1][13][ 0] = wall;         mapaPrincipal [ 2][13][ 0] = esVa; mapaPrincipal [ 3][13][ 0] = wall; mapaPrincipal [ 4][13][ 0] = wall; mapaPrincipal [ 5][13][ 0] = esVa; mapaPrincipal [ 6][13][ 0] = wall; mapaPrincipal [ 7][13][ 0] = esVa; mapaPrincipal [ 8][13][ 0] = esVa; mapaPrincipal [ 9][13][ 0] = wall; mapaPrincipal [10][13][ 0] = esVa; mapaPrincipal [11][13][ 0] = wall; mapaPrincipal [12][13][ 0] = esVa; mapaPrincipal [13][13][ 0] = esVa; mapaPrincipal [14][13][ 0] = esVa; mapaPrincipal [15][13][ 0] = wall; mapaPrincipal [16][13][ 0] = esVa; mapaPrincipal [17][13][ 0] = wall; mapaPrincipal [18][13][ 0] = wall; mapaPrincipal [19][13][ 0] = esVa; mapaPrincipal [20][13][ 0] = wall; mapaPrincipal [21][13][ 0] = esVa; mapaPrincipal [22][13][ 0] = esVa; mapaPrincipal [23][13][ 0] = wall; mapaPrincipal [24][13][ 0] = esVa; mapaPrincipal [25][13][ 0] = wall; mapaPrincipal [26][13][ 0] = esVa; mapaPrincipal [27][13][ 0] = wall; mapaPrincipal [28][13][ 0] = esVa; mapaPrincipal [29][13][ 0] = wall;
        mapaPrincipal[ 0][14][ 0] = wall; mapaPrincipal [ 1][14][ 0] = esVa;         mapaPrincipal [ 2][14][ 0] = esVa; mapaPrincipal [ 3][14][ 0] = wall; mapaPrincipal [ 4][14][ 0] = esVa; mapaPrincipal [ 5][14][ 0] = esVa; mapaPrincipal [ 6][14][ 0] = wall; mapaPrincipal [ 7][14][ 0] = esVa; mapaPrincipal [ 8][14][ 0] = wall; mapaPrincipal [ 9][14][ 0] = wall; mapaPrincipal [10][14][ 0] = esVa; mapaPrincipal [11][14][ 0] = wall; mapaPrincipal [12][14][ 0] = coin; mapaPrincipal [13][14][ 0] = wall; mapaPrincipal [14][14][ 0] = esVa; mapaPrincipal [15][14][ 0] = wall; mapaPrincipal [16][14][ 0] = wall; mapaPrincipal [17][14][ 0] = esVa; mapaPrincipal [18][14][ 0] = esVa; mapaPrincipal [19][14][ 0] = wall; mapaPrincipal [20][14][ 0] = esVa; mapaPrincipal [21][14][ 0] = esVa; mapaPrincipal [22][14][ 0] = wall; mapaPrincipal [23][14][ 0] = esVa; mapaPrincipal [24][14][ 0] = esVa; mapaPrincipal [25][14][ 0] = wall; mapaPrincipal [26][14][ 0] = esVa; mapaPrincipal [27][14][ 0] = wall; mapaPrincipal [28][14][ 0] = esVa; mapaPrincipal [29][14][ 0] = wall;
        mapaPrincipal[ 0][15][ 0] = wall; mapaPrincipal [ 1][15][ 0] = wall;         mapaPrincipal [ 2][15][ 0] = esVa; mapaPrincipal [ 3][15][ 0] = wall; mapaPrincipal [ 4][15][ 0] = wall; mapaPrincipal [ 5][15][ 0] = esVa; mapaPrincipal [ 6][15][ 0] = esVa; mapaPrincipal [ 7][15][ 0] = esVa; mapaPrincipal [ 8][15][ 0] = coin; mapaPrincipal [ 9][15][ 0] = wall; mapaPrincipal [10][15][ 0] = esVa; mapaPrincipal [11][15][ 0] = wall; mapaPrincipal [12][15][ 0] = wall; mapaPrincipal [13][15][ 0] = wall; mapaPrincipal [14][15][ 0] = esVa; mapaPrincipal [15][15][ 0] = wall; mapaPrincipal [16][15][ 0] = esVa; mapaPrincipal [17][15][ 0] = esVa; mapaPrincipal [18][15][ 0] = esVa; mapaPrincipal [19][15][ 0] = esVa; mapaPrincipal [20][15][ 0] = esVa; mapaPrincipal [21][15][ 0] = wall; mapaPrincipal [22][15][ 0] = esVa; mapaPrincipal [23][15][ 0] = esVa; mapaPrincipal [24][15][ 0] = wall; mapaPrincipal [25][15][ 0] = wall; mapaPrincipal [26][15][ 0] = esVa; mapaPrincipal [27][15][ 0] = wall; mapaPrincipal [28][15][ 0] = esVa; mapaPrincipal [29][15][ 0] = wall;
        mapaPrincipal[ 0][16][ 0] = wall; mapaPrincipal [ 1][16][ 0] = esVa;         mapaPrincipal [ 2][16][ 0] = esVa; mapaPrincipal [ 3][16][ 0] = wall; mapaPrincipal [ 4][16][ 0] = esVa; mapaPrincipal [ 5][16][ 0] = esVa; mapaPrincipal [ 6][16][ 0] = wall; mapaPrincipal [ 7][16][ 0] = esVa; mapaPrincipal [ 8][16][ 0] = wall; mapaPrincipal [ 9][16][ 0] = wall; mapaPrincipal [10][16][ 0] = esVa; mapaPrincipal [11][16][ 0] = esVa; mapaPrincipal [12][16][ 0] = wall; mapaPrincipal [13][16][ 0] = esVa; mapaPrincipal [14][16][ 0] = esVa; mapaPrincipal [15][16][ 0] = wall; mapaPrincipal [16][16][ 0] = esVa; mapaPrincipal [17][16][ 0] = wall; mapaPrincipal [18][16][ 0] = wall; mapaPrincipal [19][16][ 0] = wall; mapaPrincipal [20][16][ 0] = wall; mapaPrincipal [21][16][ 0] = wall; mapaPrincipal [22][16][ 0] = esVa; mapaPrincipal [23][16][ 0] = wall; mapaPrincipal [24][16][ 0] = esVa; mapaPrincipal [25][16][ 0] = wall; mapaPrincipal [26][16][ 0] = coin; mapaPrincipal [27][16][ 0] = wall; mapaPrincipal [28][16][ 0] = esVa; mapaPrincipal [29][16][ 0] = wall;
        mapaPrincipal[ 0][17][ 0] = wall; mapaPrincipal [ 1][17][ 0] = wall;         mapaPrincipal [ 2][17][ 0] = esVa; mapaPrincipal [ 3][17][ 0] = esVa; mapaPrincipal [ 4][17][ 0] = esVa; mapaPrincipal [ 5][17][ 0] = wall; mapaPrincipal [ 6][17][ 0] = wall; mapaPrincipal [ 7][17][ 0] = wall; mapaPrincipal [ 8][17][ 0] = esVa; mapaPrincipal [ 9][17][ 0] = wall; mapaPrincipal [10][17][ 0] = esVa; mapaPrincipal [11][17][ 0] = wall; mapaPrincipal [12][17][ 0] = esVa; mapaPrincipal [13][17][ 0] = wall; mapaPrincipal [14][17][ 0] = esVa; mapaPrincipal [15][17][ 0] = esVa; mapaPrincipal [16][17][ 0] = esVa; mapaPrincipal [17][17][ 0] = wall; mapaPrincipal [18][17][ 0] = coin; mapaPrincipal [19][17][ 0] = esVa; mapaPrincipal [20][17][ 0] = esVa; mapaPrincipal [21][17][ 0] = esVa; mapaPrincipal [22][17][ 0] = esVa; mapaPrincipal [23][17][ 0] = esVa; mapaPrincipal [24][17][ 0] = esVa; mapaPrincipal [25][17][ 0] = esVa; mapaPrincipal [26][17][ 0] = wall; mapaPrincipal [27][17][ 0] = wall; mapaPrincipal [28][17][ 0] = esVa; mapaPrincipal [29][17][ 0] = wall;
        mapaPrincipal[ 0][18][ 0] = wall; mapaPrincipal [ 1][18][ 0] = coin;         mapaPrincipal [ 2][18][ 0] = wall; mapaPrincipal [ 3][18][ 0] = esVa; mapaPrincipal [ 4][18][ 0] = wall; mapaPrincipal [ 5][18][ 0] = esVa; mapaPrincipal [ 6][18][ 0] = esVa; mapaPrincipal [ 7][18][ 0] = esVa; mapaPrincipal [ 8][18][ 0] = esVa; mapaPrincipal [ 9][18][ 0] = wall; mapaPrincipal [10][18][ 0] = esVa; mapaPrincipal [11][18][ 0] = esVa; mapaPrincipal [12][18][ 0] = wall; mapaPrincipal [13][18][ 0] = wall; mapaPrincipal [14][18][ 0] = wall; mapaPrincipal [15][18][ 0] = wall; mapaPrincipal [16][18][ 0] = wall; mapaPrincipal [17][18][ 0] = wall; mapaPrincipal [18][18][ 0] = wall; mapaPrincipal [19][18][ 0] = wall; mapaPrincipal [20][18][ 0] = wall; mapaPrincipal [21][18][ 0] = wall; mapaPrincipal [22][18][ 0] = wall; mapaPrincipal [23][18][ 0] = wall; mapaPrincipal [24][18][ 0] = wall; mapaPrincipal [25][18][ 0] = esVa; mapaPrincipal [26][18][ 0] = esVa; mapaPrincipal [27][18][ 0] = esVa; mapaPrincipal [28][18][ 0] = esVa; mapaPrincipal [29][18][ 0] = wall;
        mapaPrincipal[ 0][19][ 0] = wall; mapaPrincipal [ 1][19][ 0] = coin;         mapaPrincipal [ 2][19][ 0] = wall; mapaPrincipal [ 3][19][ 0] = esVa; mapaPrincipal [ 4][19][ 0] = esVa; mapaPrincipal [ 5][19][ 0] = esVa; mapaPrincipal [ 6][19][ 0] = wall; mapaPrincipal [ 7][19][ 0] = wall; mapaPrincipal [ 8][19][ 0] = esVa; mapaPrincipal [ 9][19][ 0] = esVa; mapaPrincipal [10][19][ 0] = esVa; mapaPrincipal [11][19][ 0] = esVa; mapaPrincipal [12][19][ 0] = esVa; mapaPrincipal [13][19][ 0] = coin; mapaPrincipal [14][19][ 0] = esVa; mapaPrincipal [15][19][ 0] = esVa; mapaPrincipal [16][19][ 0] = esVa; mapaPrincipal [17][19][ 0] = coin; mapaPrincipal [18][19][ 0] = esVa; mapaPrincipal [19][19][ 0] = esVa; mapaPrincipal [20][19][ 0] = esVa; mapaPrincipal [21][19][ 0] = coin; mapaPrincipal [22][19][ 0] = esVa; mapaPrincipal [23][19][ 0] = esVa; mapaPrincipal [24][19][ 0] = wall; mapaPrincipal [25][19][ 0] = wall; mapaPrincipal [26][19][ 0] = esVa; mapaPrincipal [27][19][ 0] = wall; mapaPrincipal [28][19][ 0] = esVa; mapaPrincipal [29][19][ 0] = wall;
        mapaPrincipal[ 0][20][ 0] = wall; mapaPrincipal [ 1][20][ 0] = esVa;         mapaPrincipal [ 2][20][ 0] = wall; mapaPrincipal [ 3][20][ 0] = wall; mapaPrincipal [ 4][20][ 0] = wall; mapaPrincipal [ 5][20][ 0] = wall; mapaPrincipal [ 6][20][ 0] = wall; mapaPrincipal [ 7][20][ 0] = esVa; mapaPrincipal [ 8][20][ 0] = esVa; mapaPrincipal [ 9][20][ 0] = wall; mapaPrincipal [10][20][ 0] = wall; mapaPrincipal [11][20][ 0] = wall; mapaPrincipal [12][20][ 0] = wall; mapaPrincipal [13][20][ 0] = wall; mapaPrincipal [14][20][ 0] = wall; mapaPrincipal [15][20][ 0] = wall; mapaPrincipal [16][20][ 0] = wall; mapaPrincipal [17][20][ 0] = wall; mapaPrincipal [18][20][ 0] = wall; mapaPrincipal [19][20][ 0] = wall; mapaPrincipal [20][20][ 0] = wall; mapaPrincipal [21][20][ 0] = wall; mapaPrincipal [22][20][ 0] = wall; mapaPrincipal [23][20][ 0] = esVa; mapaPrincipal [24][20][ 0] = esVa; mapaPrincipal [25][20][ 0] = wall; mapaPrincipal [26][20][ 0] = esVa; mapaPrincipal [27][20][ 0] = wall; mapaPrincipal [28][20][ 0] = wall; mapaPrincipal [29][20][ 0] = wall;
        mapaPrincipal[ 0][21][ 0] = wall; mapaPrincipal [ 1][21][ 0] = esVa;         mapaPrincipal [ 2][21][ 0] = wall; mapaPrincipal [ 3][21][ 0] = esVa; mapaPrincipal [ 4][21][ 0] = esVa; mapaPrincipal [ 5][21][ 0] = esVa; mapaPrincipal [ 6][21][ 0] = wall; mapaPrincipal [ 7][21][ 0] = esVa; mapaPrincipal [ 8][21][ 0] = wall; mapaPrincipal [ 9][21][ 0] = esVa; mapaPrincipal [10][21][ 0] = esVa; mapaPrincipal [11][21][ 0] = esVa; mapaPrincipal [12][21][ 0] = esVa; mapaPrincipal [13][21][ 0] = esVa; mapaPrincipal [14][21][ 0] = esVa; mapaPrincipal [15][21][ 0] = esVa; mapaPrincipal [16][21][ 0] = esVa; mapaPrincipal [17][21][ 0] = esVa; mapaPrincipal [18][21][ 0] = esVa; mapaPrincipal [19][21][ 0] = esVa; mapaPrincipal [20][21][ 0] = esVa; mapaPrincipal [21][21][ 0] = esVa; mapaPrincipal [22][21][ 0] = esVa; mapaPrincipal [23][21][ 0] = wall; mapaPrincipal [24][21][ 0] = esVa; mapaPrincipal [25][21][ 0] = wall; mapaPrincipal [26][21][ 0] = esVa; mapaPrincipal [27][21][ 0] = esVa; mapaPrincipal [28][21][ 0] = esVa; mapaPrincipal [29][21][ 0] = exit;
        mapaPrincipal[ 0][22][ 0] = wall; mapaPrincipal [ 1][22][ 0] = esVa;         mapaPrincipal [ 2][22][ 0] = esVa; mapaPrincipal [ 3][22][ 0] = esVa; mapaPrincipal [ 4][22][ 0] = wall; mapaPrincipal [ 5][22][ 0] = esVa; mapaPrincipal [ 6][22][ 0] = wall; mapaPrincipal [ 7][22][ 0] = esVa; mapaPrincipal [ 8][22][ 0] = esVa; mapaPrincipal [ 9][22][ 0] = wall; mapaPrincipal [10][22][ 0] = esVa; mapaPrincipal [11][22][ 0] = wall; mapaPrincipal [12][22][ 0] = wall; mapaPrincipal [13][22][ 0] = wall; mapaPrincipal [14][22][ 0] = wall; mapaPrincipal [15][22][ 0] = wall; mapaPrincipal [16][22][ 0] = wall; mapaPrincipal [17][22][ 0] = esVa; mapaPrincipal [18][22][ 0] = wall; mapaPrincipal [19][22][ 0] = wall; mapaPrincipal [20][22][ 0] = wall; mapaPrincipal [21][22][ 0] = esVa; mapaPrincipal [22][22][ 0] = esVa; mapaPrincipal [23][22][ 0] = esVa; mapaPrincipal [24][22][ 0] = esVa; mapaPrincipal [25][22][ 0] = wall; mapaPrincipal [26][22][ 0] = esVa; mapaPrincipal [27][22][ 0] = wall; mapaPrincipal [28][22][ 0] = wall; mapaPrincipal [29][22][ 0] = wall;
        mapaPrincipal[ 0][23][ 0] = wall; mapaPrincipal [ 1][23][ 0] = wall;         mapaPrincipal [ 2][23][ 0] = wall; mapaPrincipal [ 3][23][ 0] = wall; mapaPrincipal [ 4][23][ 0] = esVa; mapaPrincipal [ 5][23][ 0] = esVa; mapaPrincipal [ 6][23][ 0] = wall; mapaPrincipal [ 7][23][ 0] = esVa; mapaPrincipal [ 8][23][ 0] = wall; mapaPrincipal [ 9][23][ 0] = esVa; mapaPrincipal [10][23][ 0] = esVa; mapaPrincipal [11][23][ 0] = wall; mapaPrincipal [12][23][ 0] = coin; mapaPrincipal [13][23][ 0] = esVa; mapaPrincipal [14][23][ 0] = esVa; mapaPrincipal [15][23][ 0] = esVa; mapaPrincipal [16][23][ 0] = wall; mapaPrincipal [17][23][ 0] = esVa; mapaPrincipal [18][23][ 0] = esVa; mapaPrincipal [19][23][ 0] = esVa; mapaPrincipal [20][23][ 0] = wall; mapaPrincipal [21][23][ 0] = esVa; mapaPrincipal [22][23][ 0] = wall; mapaPrincipal [23][23][ 0] = wall; mapaPrincipal [24][23][ 0] = wall; mapaPrincipal [25][23][ 0] = wall; mapaPrincipal [26][23][ 0] = esVa; mapaPrincipal [27][23][ 0] = wall; mapaPrincipal [28][23][ 0] = esVa; mapaPrincipal [29][23][ 0] = wall;
        mapaPrincipal[ 0][24][ 0] = wall; mapaPrincipal [ 1][24][ 0] = esVa;         mapaPrincipal [ 2][24][ 0] = wall; mapaPrincipal [ 3][24][ 0] = esVa; mapaPrincipal [ 4][24][ 0] = wall; mapaPrincipal [ 5][24][ 0] = esVa; mapaPrincipal [ 6][24][ 0] = wall; mapaPrincipal [ 7][24][ 0] = esVa; mapaPrincipal [ 8][24][ 0] = coin; mapaPrincipal [ 9][24][ 0] = wall; mapaPrincipal [10][24][ 0] = esVa; mapaPrincipal [11][24][ 0] = wall; mapaPrincipal [12][24][ 0] = wall; mapaPrincipal [13][24][ 0] = wall; mapaPrincipal [14][24][ 0] = wall; mapaPrincipal [15][24][ 0] = esVa; mapaPrincipal [16][24][ 0] = wall; mapaPrincipal [17][24][ 0] = wall; mapaPrincipal [18][24][ 0] = wall; mapaPrincipal [19][24][ 0] = esVa; mapaPrincipal [20][24][ 0] = wall; mapaPrincipal [21][24][ 0] = esVa; mapaPrincipal [22][24][ 0] = esVa; mapaPrincipal [23][24][ 0] = esVa; mapaPrincipal [24][24][ 0] = esVa; mapaPrincipal [25][24][ 0] = wall; mapaPrincipal [26][24][ 0] = esVa; mapaPrincipal [27][24][ 0] = esVa; mapaPrincipal [28][24][ 0] = esVa; mapaPrincipal [29][24][ 0] = wall;
        mapaPrincipal[ 0][25][ 0] = wall; mapaPrincipal [ 1][25][ 0] = esVa;         mapaPrincipal [ 2][25][ 0] = wall; mapaPrincipal [ 3][25][ 0] = wall; mapaPrincipal [ 4][25][ 0] = wall; mapaPrincipal [ 5][25][ 0] = esVa; mapaPrincipal [ 6][25][ 0] = wall; mapaPrincipal [ 7][25][ 0] = wall; mapaPrincipal [ 8][25][ 0] = wall; mapaPrincipal [ 9][25][ 0] = wall; mapaPrincipal [10][25][ 0] = esVa; mapaPrincipal [11][25][ 0] = wall; mapaPrincipal [12][25][ 0] = esVa; mapaPrincipal [13][25][ 0] = esVa; mapaPrincipal [14][25][ 0] = esVa; mapaPrincipal [15][25][ 0] = esVa; mapaPrincipal [16][25][ 0] = wall; mapaPrincipal [17][25][ 0] = coin; mapaPrincipal [18][25][ 0] = wall; mapaPrincipal [19][25][ 0] = esVa; mapaPrincipal [20][25][ 0] = wall; mapaPrincipal [21][25][ 0] = wall; mapaPrincipal [22][25][ 0] = wall; mapaPrincipal [23][25][ 0] = esVa; mapaPrincipal [24][25][ 0] = wall; mapaPrincipal [25][25][ 0] = coin; mapaPrincipal [26][25][ 0] = wall; mapaPrincipal [27][25][ 0] = wall; mapaPrincipal [28][25][ 0] = esVa; mapaPrincipal [29][25][ 0] = wall;
        mapaPrincipal[ 0][26][ 0] = wall; mapaPrincipal [ 1][26][ 0] = esVa;         mapaPrincipal [ 2][26][ 0] = wall; mapaPrincipal [ 3][26][ 0] = esVa; mapaPrincipal [ 4][26][ 0] = esVa; mapaPrincipal [ 5][26][ 0] = esVa; mapaPrincipal [ 6][26][ 0] = esVa; mapaPrincipal [ 7][26][ 0] = esVa; mapaPrincipal [ 8][26][ 0] = esVa; mapaPrincipal [ 9][26][ 0] = esVa; mapaPrincipal [10][26][ 0] = esVa; mapaPrincipal [11][26][ 0] = esVa; mapaPrincipal [12][26][ 0] = esVa; mapaPrincipal [13][26][ 0] = wall; mapaPrincipal [14][26][ 0] = esVa; mapaPrincipal [15][26][ 0] = esVa; mapaPrincipal [16][26][ 0] = wall; mapaPrincipal [17][26][ 0] = esVa; mapaPrincipal [18][26][ 0] = wall; mapaPrincipal [19][26][ 0] = esVa; mapaPrincipal [20][26][ 0] = esVa; mapaPrincipal [21][26][ 0] = esVa; mapaPrincipal [22][26][ 0] = esVa; mapaPrincipal [23][26][ 0] = esVa; mapaPrincipal [24][26][ 0] = wall; mapaPrincipal [25][26][ 0] = esVa; mapaPrincipal [26][26][ 0] = wall; mapaPrincipal [27][26][ 0] = esVa; mapaPrincipal [28][26][ 0] = esVa; mapaPrincipal [29][26][ 0] = wall;
        mapaPrincipal[ 0][27][ 0] = wall; mapaPrincipal [ 1][27][ 0] = esVa;         mapaPrincipal [ 2][27][ 0] = wall; mapaPrincipal [ 3][27][ 0] = wall; mapaPrincipal [ 4][27][ 0] = wall; mapaPrincipal [ 5][27][ 0] = wall; mapaPrincipal [ 6][27][ 0] = wall; mapaPrincipal [ 7][27][ 0] = wall; mapaPrincipal [ 8][27][ 0] = wall; mapaPrincipal [ 9][27][ 0] = wall; mapaPrincipal [10][27][ 0] = wall; mapaPrincipal [11][27][ 0] = esVa; mapaPrincipal [12][27][ 0] = wall; mapaPrincipal [13][27][ 0] = esVa; mapaPrincipal [14][27][ 0] = wall; mapaPrincipal [15][27][ 0] = wall; mapaPrincipal [16][27][ 0] = wall; mapaPrincipal [17][27][ 0] = esVa; mapaPrincipal [18][27][ 0] = wall; mapaPrincipal [19][27][ 0] = esVa; mapaPrincipal [20][27][ 0] = wall; mapaPrincipal [21][27][ 0] = wall; mapaPrincipal [22][27][ 0] = wall; mapaPrincipal [23][27][ 0] = wall; mapaPrincipal [24][27][ 0] = wall; mapaPrincipal [25][27][ 0] = esVa; mapaPrincipal [26][27][ 0] = esVa; mapaPrincipal [27][27][ 0] = esVa; mapaPrincipal [28][27][ 0] = wall; mapaPrincipal [29][27][ 0] = wall;
        mapaPrincipal[ 0][28][ 0] = wall; mapaPrincipal [ 1][28][ 0] = esVa;         mapaPrincipal [ 2][28][ 0] = esVa; mapaPrincipal [ 3][28][ 0] = esVa; mapaPrincipal [ 4][28][ 0] = esVa; mapaPrincipal [ 5][28][ 0] = esVa; mapaPrincipal [ 6][28][ 0] = coin; mapaPrincipal [ 7][28][ 0] = wall; mapaPrincipal [ 8][28][ 0] = esVa; mapaPrincipal [ 9][28][ 0] = esVa; mapaPrincipal [10][28][ 0] = esVa; mapaPrincipal [11][28][ 0] = esVa; mapaPrincipal [12][28][ 0] = wall; mapaPrincipal [13][28][ 0] = esVa; mapaPrincipal [14][28][ 0] = esVa; mapaPrincipal [15][28][ 0] = esVa; mapaPrincipal [16][28][ 0] = esVa; mapaPrincipal [17][28][ 0] = esVa; mapaPrincipal [18][28][ 0] = esVa; mapaPrincipal [19][28][ 0] = esVa; mapaPrincipal [20][28][ 0] = esVa; mapaPrincipal [21][28][ 0] = esVa; mapaPrincipal [22][28][ 0] = esVa; mapaPrincipal [23][28][ 0] = esVa; mapaPrincipal [24][28][ 0] = esVa; mapaPrincipal [25][28][ 0] = esVa; mapaPrincipal [26][28][ 0] = wall; mapaPrincipal [27][28][ 0] = esVa; mapaPrincipal [28][28][ 0] = coin; mapaPrincipal [29][28][ 0] = wall;
        mapaPrincipal[ 0][29][ 0] = wall; mapaPrincipal [ 1][29][ 0] = wall;         mapaPrincipal [ 2][29][ 0] = wall; mapaPrincipal [ 3][29][ 0] = wall; mapaPrincipal [ 4][29][ 0] = wall; mapaPrincipal [ 5][29][ 0] = wall; mapaPrincipal [ 6][29][ 0] = wall; mapaPrincipal [ 7][29][ 0] = wall; mapaPrincipal [ 8][29][ 0] = wall; mapaPrincipal [ 9][29][ 0] = wall; mapaPrincipal [10][29][ 0] = wall; mapaPrincipal [11][29][ 0] = wall; mapaPrincipal [12][29][ 0] = wall; mapaPrincipal [13][29][ 0] = exit; mapaPrincipal [14][29][ 0] = wall; mapaPrincipal [15][29][ 0] = wall; mapaPrincipal [16][29][ 0] = wall; mapaPrincipal [17][29][ 0] = wall; mapaPrincipal [18][29][ 0] = wall; mapaPrincipal [19][29][ 0] = wall; mapaPrincipal [20][29][ 0] = wall; mapaPrincipal [21][29][ 0] = wall; mapaPrincipal [22][29][ 0] = wall; mapaPrincipal [23][29][ 0] = wall; mapaPrincipal [24][29][ 0] = wall; mapaPrincipal [25][29][ 0] = wall; mapaPrincipal [26][29][ 0] = wall; mapaPrincipal [27][29][ 0] = wall; mapaPrincipal [28][29][ 0] = wall; mapaPrincipal [29][29][ 0] = wall;

        mapaPrincipal[ 0][ 1][ 1] = "15";
        mapaPrincipal[ 8][ 0][ 1] = "30";
        mapaPrincipal[13][29][ 1] = "43";
        mapaPrincipal[29][ 1][ 1] = "51";
        mapaPrincipal[29][21][ 1] = "55";

    } //llena el mapa por defecto con el mapa dado en el documento de la práctica
    public static void dibMapa() { //Función para dibujar el mapa en la terminal
        switch (idMapa){
            case 0:
                for (int y = 0; y < 30; y++) {
                    for (int x = 0; x < 30; x++) {
                        System.out.print(mapaPrincipal[x][y][0]); // i = x; j = y
                    }
                    System.out.println(" ");
                }
                break;
            case 1:
                for (int y = 0; y < 15; y++) {
                    for (int x = 0; x < 15; x++) {
                        System.out.print(mapaRanuraA[x][y][0]); // i = x; j = y
                    }
                    System.out.println(" ");
                }
                break;
            case 2:
                for (int y = 0; y < 15; y++) {
                    for (int x = 0; x < 15; x++) {
                        System.out.print(mapaRanuraB[x][y][0]); // i = x; j = y
                    }
                    System.out.println(" ");
                }
                break;
        }
    } //dibuja los mapas originales en pantalla, sin elementos agregados
    public static void inicializarContadores(){
        for(int i = 0; i < 3; i++){
            contMapaA           [0][i] = 0;
            contMapaB           [0][i] = 0;
            contMapaPrincipal   [0][i] = 0;
        }
    }

    //Funciones para crear mapas
    public static void crearMapa(){
        Scanner scanner = new Scanner(System.in);
        limpiarPantalla();
        System.out.println("Bienvenido a la herramienta de creación de mapas");
        System.out.println("A continuación deberá ingresar los parámetros conforme se vayan pidiendo");
        System.out.println("Al finalizar se le notificará de posibles inconvenientes para realizar cambios");
        System.out.println("Si ingresa un carácter inválido ese espacio se reemplazará con un elemento aleatorio");
        System.out.println("Este elemento puede ser una moneda, un espacio vacío, una salida o una pared");
        System.out.println("Tome en cuenta que los mapas son de 15 x 15 casillas");
        System.out.println(" ");

        contRanura = contRanura + 1;
        switch(contRanura){
            case 1://escribe en ranura A
                System.out.println("¿Qué nombre tendrá su mapa?");
                nombreRanuraA = scanner.nextLine();
                do{
                    System.out.println("Por favor ingrese la cantidad de monedas requeridas para ganar en este mapa");
                    System.out.println("Tome en cuenta que deberá ser un número positivo");
                    monedasRequeridasA = scanner.nextInt();
                    scanner.nextLine(); //Para no tener problemas con scanner más adelante
                    monedasRequeridas = monedasRequeridasA;
                }while(monedasRequeridasA < 1);
                escribirMapaA();
                break;
            case 2://escribe en ranura B
                System.out.println("¿Qué nombre tendrá su mapa?");
                nombreRanuraB = scanner.nextLine();
                do{
                    System.out.println("Por favor ingrese la cantidad de monedas requeridas para ganar en este mapa");
                    System.out.println("Tome en cuenta que deberá ser un número positivo");
                    monedasRequeridasB = scanner.nextInt();
                    scanner.nextLine(); //Para no tener problemas con scanner más adelante
                    monedasRequeridas = monedasRequeridasB;
                }while(monedasRequeridasB < 1);
                escribirMapaB();
                break;
            default:
                System.out.println("No es posible ingresar un nuevo mapa");
                break;
        }
    } //menú para la creacion de mapas y asignacion de variables emparentadas
    public static void escribirMapaA(){
        Scanner scanner = new Scanner(System.in);
        Random  random  = new Random();
        idMapa = 1;

        final String pared    = "p"; //nombres diferentes a las variables globales|| uso: impr instrucciones y validación de la entrada
        final String moneda   = "g";
        final String vacio    = "e";
        final String salida   = "s";
        String entrada; //variable transitoria para validar la entrada, asigna valor a la casilla indicada
        int aleatorio; //usada para asignar un valor aleatorio en caso de no reconocer una entrada válida
        int contMonedas = 0; //cuenta las monedas dispuestas en el mapa por el usuario
        int contSalidas = 0; //cuenta la cantidad de salidas dispuestas por el usuario

        for(int y = 0; y < 15; y ++){
            limpiarPantalla();
            System.out.println("Deberá ingresar las casillas una por una con los siguientes elementos sin agregar espacios");
            System.out.println("Si ingresa una salida deberá especificar la cantidad de oro que esta requiere");
            System.out.println("Elementos -> Pared: " + pared + " || Moneda: " + moneda + " || Espacio: " + vacio + " || Salida: " + salida);
            System.out.println("Está ingresando la fila No. " + y);
            System.out.println(" ");
            for (int x = 0; x < 15; x++){
                entrada = scanner.nextLine();
                switch(entrada){
                    case  pared:
                        mapaRanuraA[x][y][0] = wall;
                        break;
                    case moneda:
                        mapaRanuraA[x][y][0] = coin;
                        contMonedas = contMonedas + 1;
                        break;
                    case vacio:
                        mapaRanuraA[x][y][0] = esVa;
                        break;
                    case salida:
                        mapaRanuraA[x][y][0] = exit;
                        System.out.println("¿Cuánto oro se requiere en esta salida?");
                        mapaRanuraA[x][y][1] = scanner.nextLine();
                        contSalidas = contSalidas +1;
                        System.out.println("ahora continua con las casillas");
                        break;
                    default:
                        aleatorio = random.nextInt(4);
                        switch (aleatorio){
                            case 0:
                                mapaRanuraA[x][y][0] = wall;
                                break;
                            case 1:
                                mapaRanuraA[x][y][0] = coin;
                                contMonedas = contMonedas + 1;
                                break;
                            case 2:
                                mapaRanuraA[x][y][0] = esVa;
                                break;
                            case 3:
                                mapaRanuraA[x][y][0] = exit;
                                mapaRanuraA[x][y][1] = Integer.toString(random.nextInt(15)-5);
                                contSalidas = contSalidas +1;
                                break;
                        }
                }
            }
        }
        if(contMonedas < monedasRequeridas || contSalidas < 1){
            limpiarPantalla();
            System.out.println("No ingresó suficientes monedas o salidas, puede corregirlo usando el editor");
            editorMapa(contMonedas, contSalidas);
        }
    }
    public static void escribirMapaB(){
        Scanner scanner = new Scanner(System.in);
        Random  random  = new Random();
        idMapa = 2;

        final String pared    = "p"; //nombres diferentes a las variables globales|| uso: impr instrucciones y validación de la entrada
        final String moneda   = "g";
        final String vacio    = "e";
        final String salida   = "s";
        String entrada; //variable transitoria para validar la entrada, asigna valor a la casilla indicada
        int aleatorio; //usada para asignar un valor aleatorio en caso de no reconocer una entrada válida
        int contMonedas = 0; //cuenta las monedas dispuestas en el mapa por el usuario
        int contSalidas = 0; //cuenta la cantidad de salidas dispuestas por el usuario

        for(int y = 0; y < 15; y ++){

            limpiarPantalla();
            System.out.println("Deberá ingresar las casillas una por una con los siguientes elementos sin agregar espacios");
            System.out.println("Elementos -> Pared: " + pared + " || Moneda: " + moneda + " || Espacio: " + vacio + " || Salida: " + salida);
            System.out.println("Está ingresando la fila No. " + y);
            System.out.println(" ");
            for (int x = 0; x < 15; x++){
                entrada = scanner.next();
                switch(entrada){
                    case  pared:
                        mapaRanuraB[x][y][0] = wall;
                        break;
                    case moneda:
                        mapaRanuraB[x][y][0] = coin;
                        contMonedas = contMonedas + 1;
                        break;
                    case vacio:
                        mapaRanuraB[x][y][0] = esVa;
                        break;
                    case salida:
                        mapaRanuraB[x][y][0] = exit;
                        System.out.println("¿Cuánto oro se requiere en esta salida?");
                        mapaRanuraB[x][y][1] = scanner.nextLine();
                        System.out.println("ahora continua con las casillas");
                        contSalidas = contSalidas + 1;
                        break;
                    default:
                        aleatorio = random.nextInt(4);
                        switch (aleatorio){
                            case 0:
                                mapaRanuraB[x][y][0] = wall;
                                break;
                            case 1:
                                mapaRanuraB[x][y][0] = coin;
                                contMonedas = contMonedas + 1;
                                break;
                            case 2:
                                mapaRanuraB[x][y][0] = esVa;
                                break;
                            case 3:
                                mapaRanuraB[x][y][0] = exit;
                                mapaRanuraB[x][y][1] = Integer.toString(random.nextInt(15)-5);
                                contSalidas = contSalidas +1;
                                break;
                        }
                }
            }
        }
        if(contMonedas < monedasRequeridas || contSalidas < 1){
            System.out.println("No ingresó suficientes monedas, puede corregirlo usando el editor");
            editorMapa(contMonedas, contSalidas);
        }
    }
    public static void editorMapa(int contMonedas, int contSalidas){
        Scanner scanner = new Scanner(System.in);
        int x;
        int y;
        if(contMonedas < monedasRequeridas){
            System.out.println("Las monedas requeridas son: " + monedasRequeridas + " y las ingresadas son: " + contMonedas);
            System.out.println("El mapa ingresado es el siguiente");
            System.out.println(" ");
            System.out.println(" 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14");
            dibMapa();

            System.out.println(" ");
            System.out.println("Ingrese el número de culumna donde se encuentra el elemento a cambiar por una moneda ");
            System.out.println("Tome en cuenta que columnas y filas inician en cero");
            x = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Ingrese el número de fila donde se encuentra el elemento a cambiar por una moneda ");
            y = scanner.nextInt();
            scanner.nextLine();

            switch (idMapa){
                case 1:
                    mapaRanuraA[x][y][0] = coin;
                    contMonedas = contMonedas + 1;
                    break;
                case 2:
                    mapaRanuraB[x][y][0] = coin;
                    contMonedas = contMonedas + 1;
                    break;
            }
            limpiarPantalla();
        }

        if(contSalidas < 1){
            System.out.println(" -------------      PRESTE ATENCIÓN       -------------");
            System.out.println("Se necesita al menos una salida en cada mapa para ganar");
            System.out.println("El mapa ingresado es el siguiente");
            System.out.println(" ");
            System.out.println(" 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14");
            dibMapa();

            System.out.println(" ");
            System.out.println("Ingrese el número de culumna donde se encuentra el elemento a cambiar por una salida");
            System.out.println("Tome en cuenta que columnas y filas inician en cero");
            x = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Ingrese el número de fila donde se encuentra el elemento a cambiar por una salida ");
            y = scanner.nextInt();
            scanner.nextLine();

            switch (idMapa){
                case 1:
                    mapaRanuraA[x][y][0] = exit;
                    System.out.println("¿Cuánto oro se requiere en esta salida?");
                    mapaRanuraA[x][y][1] = scanner.nextLine();
                    contSalidas = contSalidas + 1;

                    break;
                case 2:
                    mapaRanuraB[x][y][0] = exit;
                    System.out.println("¿Cuánto oro se requiere en esta salida?");
                    mapaRanuraB[x][y][1] = scanner.nextLine();
                    contSalidas = contSalidas + 1;
                    break;
            }
            limpiarPantalla();

        }

        if(contMonedas < monedasRequeridas || contSalidas < 1){
            editorMapa(contMonedas, contSalidas);//función ciclica hasta que contMonedas = monedasRequeridas
        }
    }

    //Funciones para jugar
    public static void llamadasJugar(){

        if(idMapa == 0){
            contMapaPrincipal[0][0] = contMapaPrincipal[0][0] + 1;
        }
        if (idMapa == 1){
            contMapaA[0][0] = contMapaA[0][0] + 1;
        }
        if (idMapa == 2){
            contMapaB[0][0] = contMapaB[0][0] + 1;
        }

        contOro                     = 0;
        contTurnoJugador            = 0;
        conTurnoBot                 = 0;
        cantBotViewPlayer           = 0;
        contPartida =   contPartida + 1;
        finjuego                = false;
        boolean everyElements    = true;
        copiarMapaJugar();
        generarElementosRandom(everyElements);
        revelarMonedas();
        
        try{
            while (!finjuego){
                //limpiarPantalla();
                accionesJugador();
                contMovTotal = contMovTotal + 1;
                contTurnoJugador = contTurnoJugador + 1;
                revelarMonedasEnJuego();
                System.out.println("El bot está ejecutando su turno");
                Thread.sleep(esperaBot);
                //mostrarMapa();
                if((conTurnoBot % 2) == 0){
                    mirarMapaBot();
                }else{
                   // limpiarPantalla();
                    moverBot();
                    revelarMonedasEnJuego();
                }
                System.out.println("El bot ha completado su turno");
                limpiarPantalla();
                mostrarMapa();
                compBotGana();
                conTurnoBot = conTurnoBot + 1;
            }
        }catch(Exception e){
            System.out.println(e);
        }
        contOroTotal = contOroTotal + contOro;
        reporteFinPartida();
    } //llama a todas las funciones necesarias para una partida

    public static void accionesJugador(){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        String  accion; //guarda la accion realizada en el turno

        int contErr = 0;         // cuenta la cantidad de comandos mal ingresados
        int cantComandToEnd = 3; // indica la cantidad de comandos mal ingresados para finalizar el juego
        boolean finTurno = false;

        do {
            System.out.println("Desplazamiento: Arriba: " + arriba + " || Abajo: " + abajo + " || Derecha: " + derecha + "|| Izquierda: " + izquierda);
            System.out.println("Interacción: Mirar mapa : " + mirMapa + " || Coger oro: " + coger + " || Oro disponible : "+ oroDisp + " || Salir : " + salir);
            accion = scanner.nextLine();
            //limpiarPantalla();

            if(idMapa == 0){// ------------------- jugadas para mapa 30 x 30
                limitador = 29;
                finjuego = false;
                switch(accion){
                    case arriba:
                        if((cooPlay[1] -1) <0 ){ //limitar al borde del mapa
                            System.out.println("Jugada imposible");
                        } else {
                            if( mapaEnUso30x30[cooPlay[0]][cooPlay[1]-1][0].equals(esVa) || mapaEnUso30x30[cooPlay[0]][cooPlay[1]-1][0].equals(coin)){
                                mapaEnUso30x30[cooPlay[0]][cooPlay[1]-1][0] = play;
                                mapaEnUso30x30[cooPlay[0]][cooPlay[1]][0] = esVa;
                                cooPlay[1] = cooPlay[1]-1; //solo se mueve en y
                            }else{
                                if(mapaEnUso30x30[cooPlay[0]][cooPlay[1]-1][0].equals(exit)){
                                    if(contOro >= Integer.parseInt(mapaEnUso30x30[cooPlay[0]][cooPlay[1]-1][1])){
                                        System.out.println("¡Lograste completar el laberinto!");
                                        System.out.println("¡Felicidades!");
                                        cantPlayerWin = cantPlayerWin + 1;
                                        contMapaPrincipal[0][1] = contMapaPrincipal[0][1] + 1;
                                        finjuego = true;
                                    }else{
                                        System.out.println("Jugada imposible");
                                        System.out.println("Esta salida necesita " + mapaEnUso30x30[cooPlay[0]][cooPlay[1]-1][1] + " de oro");
                                    }}else{System.out.println("Jugada imposible, la casilla ya está ocupada");}}}
                        finTurno = true;
                        break;
                    case abajo:
                        if((cooPlay[1] + 1) > limitador ){ //limitar al borde del mapa
                            System.out.println("Jugada imposible");
                            contErr = contErr +1;
                        } else {
                            if( mapaEnUso30x30[cooPlay[0]][cooPlay[1]+1][0].equals(esVa) || mapaEnUso30x30[cooPlay[0]][cooPlay[1]+1][0].equals(coin)){
                                mapaEnUso30x30[cooPlay[0]][cooPlay[1]+1][0] = play;
                                mapaEnUso30x30[cooPlay[0]][cooPlay[1]][0] = esVa;
                                cooPlay[1] = cooPlay[1]+1; //solo se mueve en y
                            }else{
                                if(mapaEnUso30x30[cooPlay[0]][cooPlay[1]+1][0].equals(exit)){
                                    if(contOro >= Integer.parseInt(mapaEnUso30x30[cooPlay[0]][cooPlay[1]+1][1])){
                                        System.out.println("¡Lograste completar el laberinto!");
                                        System.out.println("¡Felicidades!");
                                        cantPlayerWin = cantPlayerWin + 1;
                                        contMapaPrincipal[0][1] = contMapaPrincipal[0][1] + 1;
                                        finjuego = true;
                                    }else{
                                        System.out.println("Jugada imposible");
                                        System.out.println("Esta salida necesita " + mapaEnUso30x30[cooPlay[0]][cooPlay[1]+1][1] + " de oro");
                                    }}else{System.out.println("Jugada imposible, la casilla ya está ocupada");}}}
                        finTurno = true;
                        break;
                    case izquierda:
                        if((cooPlay[0] - 1) < 0 ){ //limitar al borde del mapa
                            System.out.println("Jugada imposible");
                            contErr = contErr +1;
                        } else {
                            if( mapaEnUso30x30[cooPlay[0]-1][cooPlay[1]][0].equals(esVa) || mapaEnUso30x30[cooPlay[0]-1][cooPlay[1]][0].equals(coin)){
                                mapaEnUso30x30[cooPlay[0]-1][cooPlay[1]][0] = play;
                                mapaEnUso30x30[cooPlay[0]][cooPlay[1]][0] = esVa;
                                cooPlay[0] = cooPlay[0]-1; //solo se mueve en x
                            }else{
                                if(mapaEnUso30x30[cooPlay[0]-1][cooPlay[1]][0].equals(exit)){
                                    if(contOro >= Integer.parseInt(mapaEnUso30x30[cooPlay[0]-1][cooPlay[1]][1])){
                                        System.out.println("¡Lograste completar el laberinto!");
                                        System.out.println("¡Felicidades!");
                                        cantPlayerWin = cantPlayerWin + 1;
                                        contMapaPrincipal[0][1] = contMapaPrincipal[0][1] + 1;
                                        finjuego = true;
                                    }else{
                                        System.out.println("Jugada imposible");
                                        System.out.println("Esta salida necesita " + mapaEnUso30x30[cooPlay[0]-1][cooPlay[1]][1] + " de oro");
                                    }}else{System.out.println("Jugada imposible, la casilla ya está ocupada");}}}
                        finTurno = true;
                        break;
                    case derecha:
                        if((cooPlay[0] + 1) > limitador ){ //limitar al borde del mapa
                            System.out.println("Jugada imposible");
                        } else {
                            if( mapaEnUso30x30[cooPlay[0]+1][cooPlay[1]][0].equals(esVa) || mapaEnUso30x30[cooPlay[0]+1][cooPlay[1]][0].equals(coin)){
                                mapaEnUso30x30[cooPlay[0]+1][cooPlay[1]][0] = play;
                                mapaEnUso30x30[cooPlay[0]][cooPlay[1]][0] = esVa;
                                cooPlay[0] = cooPlay[0]+1; //solo se mueve en x
                            }else{
                                if(mapaEnUso30x30[cooPlay[0]+1][cooPlay[1]][0].equals(exit)){
                                    if(contOro >= Integer.parseInt(mapaEnUso30x30[cooPlay[0]+1][cooPlay[1]][1])){
                                        System.out.println("¡Lograste completar el laberinto!");
                                        System.out.println("¡Felicidades!");
                                        cantPlayerWin = cantPlayerWin + 1;
                                        contMapaPrincipal[0][1] = contMapaPrincipal[0][1] + 1;
                                        finjuego = true;
                                    }else{
                                        System.out.println("Jugada imposible");
                                        System.out.println("Esta salida necesita " + mapaEnUso30x30[cooPlay[0]+1][cooPlay[1]][1] + " de oro");
                                    }}else{System.out.println("Jugada imposible, la casilla ya está ocupada");}}}
                        finTurno = true;
                        break;
                    case mirMapa:
                        mirarMapa();
                        finTurno = true;
                        break;
                    case coger:
                        if (mapaEnUso30x30[cooPlay[0]][cooPlay[1]][1].equals("1")){
                            contOro = contOro + random.nextInt(15 - 5) + 5;
                            mapaEnUso30x30[cooPlay[0]][cooPlay[1]][1] = "0";
                        }else{
                            System.out.println("Jugada imposible");
                        }
                        finTurno = true;
                        break;
                    case salir:
                        finjuego = true;
                        finTurno = true;
                        break;
                    case oroDisp:
                        System.out.println("El oro dispoible actual es de: " + contOro);
                        finTurno = true;
                        break;
                    default:
                        System.out.println("No se reconoce el comando, se le restará una cantidad de oro y se ubicará en el mapa");
                        boolean everyelements = false;
                        generarElementosRandom(everyelements);
                        contErr = contErr + 1;
                        if (contErr == cantComandToEnd){
                            finjuego = true;
                            finTurno = true;
                        }
                        break;
                }

            }else{ //----------------------------- jugadas para mapas 15 * 15;
                limitador = 14;
                finjuego = false;
                switch(accion){
                    case arriba:
                        if((cooPlay[1] -1) <0 ){ //limitar al borde del mapa
                            System.out.println("Jugada imposible");
                        } else {
                            if( mapaEnUso15x15[cooPlay[0]][cooPlay[1]-1][0].equals(esVa) || mapaEnUso15x15[cooPlay[0]][cooPlay[1]-1][0].equals(coin)){
                                mapaEnUso15x15[cooPlay[0]][cooPlay[1]-1][0] = play;
                                mapaEnUso15x15[cooPlay[0]][cooPlay[1]][0] = esVa;
                                cooPlay[1] = cooPlay[1]-1; //solo se mueve en y
                            }else{
                                if(mapaEnUso15x15[cooPlay[0]][cooPlay[1]-1][0].equals(exit)){
                                    if(contOro >= Integer.parseInt(mapaEnUso15x15[cooPlay[0]][cooPlay[1]-1][1])){
                                        System.out.println("¡Lograste completar el laberinto!");
                                        System.out.println("¡Felicidades!");
                                        cantPlayerWin = cantPlayerWin + 1;
                                        if(idMapa == 1){
                                            contMapaA[0][1] = contMapaA[0][1] + 1;
                                        }
                                        if(idMapa == 2){
                                            contMapaB[0][1] = contMapaB[0][1] +1;
                                        }
                                        finjuego = true;
                                    }else{
                                        System.out.println("Jugada imposible");
                                        System.out.println("Esta salida necesita " + mapaEnUso15x15[cooPlay[0]][cooPlay[1]-1][1] + " de oro");
                                    }}else{System.out.println("Jugada imposible, la casilla ya está ocupada");}}}
                        finTurno = true;
                        break;
                    case abajo:
                        if((cooPlay[1] + 1) > limitador ){ //limitar al borde del mapa
                            System.out.println("Jugada imposible");
                            contErr = contErr +1;
                        } else {
                            if( mapaEnUso15x15[cooPlay[0]][cooPlay[1]+1][0].equals(esVa) || mapaEnUso15x15[cooPlay[0]][cooPlay[1]+1][0].equals(coin)){
                                mapaEnUso15x15[cooPlay[0]][cooPlay[1]+1][0] = play;
                                mapaEnUso15x15[cooPlay[0]][cooPlay[1]][0] = esVa;
                                cooPlay[1] = cooPlay[1]+1; //solo se mueve en y
                            }else{
                                if(mapaEnUso15x15[cooPlay[0]][cooPlay[1]+1][0].equals(exit)){
                                    if(contOro >= Integer.parseInt(mapaEnUso15x15[cooPlay[0]][cooPlay[1]+1][1])){
                                        System.out.println("¡Lograste completar el laberinto!");
                                        System.out.println("¡Felicidades!");
                                        cantPlayerWin = cantPlayerWin + 1;
                                        if(idMapa == 1){
                                            contMapaA[0][1] = contMapaA[0][1] + 1;
                                        }
                                        if(idMapa == 2){
                                            contMapaB[0][1] = contMapaB[0][1] +1;
                                        }
                                        finjuego = true;
                                    }else{
                                        System.out.println("Jugada imposible");
                                        System.out.println("Esta salida necesita " + mapaEnUso15x15[cooPlay[0]][cooPlay[1]+1][1] + " de oro");
                                    }}else{System.out.println("Jugada imposible, la casilla ya está ocupada");}}}
                        finTurno = true;
                        break;
                    case izquierda:
                        if((cooPlay[0] - 1) < 0 ){ //limitar al borde del mapa
                            System.out.println("Jugada imposible");
                            contErr = contErr +1;
                        } else {
                            if( mapaEnUso15x15[cooPlay[0]-1][cooPlay[1]][0].equals(esVa) || mapaEnUso15x15[cooPlay[0]-1][cooPlay[1]][0].equals(coin)){
                                mapaEnUso15x15[cooPlay[0]-1][cooPlay[1]][0] = play;
                                mapaEnUso15x15[cooPlay[0]][cooPlay[1]][0] = esVa;
                                cooPlay[0] = cooPlay[0]-1; //solo se mueve en x
                            }else{
                                if(mapaEnUso15x15[cooPlay[0]-1][cooPlay[1]][0].equals(exit)){
                                    if(contOro >= Integer.parseInt(mapaEnUso15x15[cooPlay[0]-1][cooPlay[1]][1])){
                                        System.out.println("¡Lograste completar el laberinto!");
                                        System.out.println("¡Felicidades!");
                                        cantPlayerWin = cantPlayerWin + 1;
                                        if(idMapa == 1){
                                            contMapaA[0][1] = contMapaA[0][1] + 1;
                                        }
                                        if(idMapa == 2){
                                            contMapaB[0][1] = contMapaB[0][1] +1;
                                        }
                                        finjuego = true;
                                    }else{
                                        System.out.println("Jugada imposible");
                                        System.out.println("Esta salida necesita " + mapaEnUso15x15[cooPlay[0]-1][cooPlay[1]][1] + " de oro");
                                    }}else{System.out.println("Jugada imposible, la casilla ya está ocupada");}}}
                        finTurno = true;
                        break;
                    case derecha:
                        if((cooPlay[0] + 1) > limitador ){ //limitar al borde del mapa
                            System.out.println("Jugada imposible");
                        } else {
                            if( mapaEnUso15x15[cooPlay[0]+1][cooPlay[1]][0].equals(esVa) || mapaEnUso15x15[cooPlay[0]+1][cooPlay[1]][0].equals(coin)){
                                mapaEnUso15x15[cooPlay[0]+1][cooPlay[1]][0] = play;
                                mapaEnUso15x15[cooPlay[0]][cooPlay[1]][0] = esVa;
                                cooPlay[0] = cooPlay[0]+1; //solo se mueve en x
                            }else{
                                if(mapaEnUso15x15[cooPlay[0]+1][cooPlay[1]][0].equals(exit)){
                                    if(contOro >= Integer.parseInt(mapaEnUso30x30[cooPlay[0]+1][cooPlay[1]][1])){
                                        System.out.println("¡Lograste completar el laberinto!");
                                        System.out.println("¡Felicidades!");
                                        cantPlayerWin = cantPlayerWin + 1;
                                        if(idMapa == 1){
                                            contMapaA[0][1] = contMapaA[0][1] + 1;
                                        }
                                        if(idMapa == 2){
                                            contMapaB[0][1] = contMapaB[0][1] +1;
                                        }
                                        finjuego = true;
                                    }else{
                                        System.out.println("Jugada imposible");
                                        System.out.println("Esta salida necesita " + mapaEnUso15x15[cooPlay[0]+1][cooPlay[1]][1] + " de oro");
                                    }}else{System.out.println("Jugada imposible, la casilla ya está ocupada");}}}
                        finTurno = true;
                        break;
                    case mirMapa:
                        mirarMapa();
                        finTurno = true;
                        break;
                    case coger:
                        if (mapaEnUso15x15[cooPlay[0]][cooPlay[1]][1].equals("1")){
                            contOro = contOro + random.nextInt(15 - 5) + 5;
                            mapaEnUso15x15[cooPlay[0]][cooPlay[1]][1] = "0";
                        }else{
                            System.out.println("Jugada imposible");
                        }
                        finTurno = true;
                        break;
                    case salir:
                        finjuego = true;
                        finTurno = true;
                        break;
                    case oroDisp:
                        System.out.println("El oro dispoible actual es de: " + contOro);
                        finTurno = true;
                        break;
                    default:
                        System.out.println("No se reconoce el comando, se le restará una cantidad de oro y se ubicará en el mapa");
                        boolean everyelements = false;
                        generarElementosRandom(everyelements);
                        contErr = contErr + 1;
                        if (contErr == cantComandToEnd){
                            finjuego = true;
                            finTurno = true;
                        }
                        break;
                }
            } //limitar bordes del mapa según idMapa
        }while (!finTurno);

    }
    public static void copiarMapaJugar(){
        if(idMapa == 0){
            for (int y = 0; y < 30; y++){
                for (int x = 0; x < 30;x++){
                    mapaEnUso30x30[x][y][0] = mapaPrincipal[x][y][0];
                    mapaEnUso30x30[x][y][1] = mapaPrincipal[x][y][1];
                }
            }
        }
        if(idMapa == 1){
            for (int y = 0; y < 15; y++){
                for (int x = 0; x < 15; x++){
                    mapaEnUso15x15[x][y][0] = mapaRanuraA[x][y][0];
                    mapaEnUso15x15[x][y][1] = mapaRanuraA[x][y][1];
                }
            }
        }
        if(idMapa == 2){
            for (int y = 0; y < 15; y++){
                for (int x = 0; x < 15; x++){
                    mapaEnUso15x15[x][y][0] = mapaRanuraB[x][y][0];
                    mapaEnUso15x15[x][y][1] = mapaRanuraB[x][y][1];
                }
            }
        }
    }
    public static void generarElementosRandom(boolean everyElements){
        //si everyElements -> true, genera jugador, bot y una salida; everyElements -> false, genera una moneda por penalización
        Random random = new Random();
        int restriccionMapa;
        int distBotJugador = 4; //Determina el area excluyente para generar al bot en el mapa
        if (idMapa == 0){
            restriccionMapa = 30;

            if(everyElements){
                boolean generado = false;
                do {
                    generado = false;
                    int x = random.nextInt(restriccionMapa);
                    int y = random.nextInt(restriccionMapa);
                    if (mapaEnUso30x30[x][y][0].equals(esVa)) {
                        mapaEnUso30x30[x][y][0] = play;
                        cooPlay[0] = x;
                        cooPlay[1] = y;
                        generado = true;
                    }
                }while(!generado); //Generar jugador

                int xMen = cooPlay[0] - distBotJugador;
                int xMay = cooPlay[0] + distBotJugador;
                int yMen = cooPlay[1] - distBotJugador;
                int yMay = cooPlay[1] + distBotJugador;
                //limitar bordes del mapa
                if (xMen < 0) {
                    xMen = 0;
                }
                if (xMay > restriccionMapa) {
                    xMay = (restriccionMapa - 1);
                }
                if (yMen < 0) {
                    yMen = 0;
                }
                if (yMay > restriccionMapa) {
                    yMay = (restriccionMapa - 1);
                }

                int tokenGen = 0; //aprueba o desaprueba si coordenada aleatoria se encuentra en rango de exclusión
                do {
                    cooBot[0] = random.nextInt(restriccionMapa);  //coordenadas X e Y del bot
                    cooBot[1] = random.nextInt(restriccionMapa);
                    if (mapaEnUso30x30[cooBot[0]][cooBot[1]][0].equals(esVa)) { //Si el espacio aleatorio está vacío verificar si está en el rango de exclusión
                        if (cooBot[0] >= xMen && cooBot[1] <= xMay) {
                            if (cooBot[0] >= yMen && cooBot[1] <= yMay) {
                                tokenGen = 0; //El
                            } else {
                                tokenGen = 1;
                            }
                        } else {
                            tokenGen = 1;
                        }
                    } else {
                        tokenGen = 0;
                    }
                } while (tokenGen != 1);
                mapaEnUso30x30[cooBot[0]][cooBot[1]][0] = bot;
            }else{
                if(contOro > 0){
                    //generar solo una moenda y penalizar
                    boolean generado = false;
                    contOro = contOro - random.nextInt(6)+1;
                    do {
                        generado = false;
                        int x = random.nextInt(restriccionMapa);
                        int y = random.nextInt(restriccionMapa);
                        if (mapaEnUso30x30[x][y][0].equals(esVa)) {
                            mapaEnUso30x30[x][y][0] = coin;
                            mapaEnUso30x30[x][y][1] = "1";
                            generado = true;
                        }
                    }while(!generado); //Generar jugador
                }else {
                    System.out.println("No tiene suficiente oro para penalizarlo");
                }

            }

        }else{ //mapas de 15x15 ---------------------------------------------
            restriccionMapa = 15;
            if(everyElements){
                boolean generado = false;
                do {
                    generado = false;
                    int x = random.nextInt(restriccionMapa);
                    int y = random.nextInt(restriccionMapa);
                    if (mapaEnUso15x15[x][y][0].equals(esVa)) {
                        mapaEnUso15x15[x][y][0] = play;
                        cooPlay[0] = x;
                        cooPlay[1] = y;
                        generado = true;
                    }
                }while(!generado); //Generar jugador

                int xMen = cooPlay[0] - distBotJugador;
                int xMay = cooPlay[0] + distBotJugador;
                int yMen = cooPlay[1] - distBotJugador;
                int yMay = cooPlay[1] + distBotJugador;
                //limitar bordes del mapa
                if (xMen < 0) {
                    xMen = 0;
                }
                if (xMay > restriccionMapa) {
                    xMay = (restriccionMapa - 1);
                }
                if (yMen < 0) {
                    yMen = 0;
                }
                if (yMay > restriccionMapa) {
                    yMay = (restriccionMapa - 1);
                }

                int tokenGen = 0; //aprueba o desaprueba si coordenada aleatoria se encuentra en rango de exclusión
                do {
                    cooBot[0] = random.nextInt(restriccionMapa);  //coordenadas X e Y del bot
                    cooBot[1] = random.nextInt(restriccionMapa);
                    if (mapaEnUso15x15[cooBot[0]][cooBot[1]][0].equals(esVa)) { //Si el espacio aleatorio está vacío verificar si está en el rango de exclusión
                        if (cooBot[0] >= xMen && cooBot[1] <= xMay) {
                            if (cooBot[0] >= yMen && cooBot[1] <= yMay) {
                                tokenGen = 0; //El
                            } else {
                                tokenGen = 1;
                            }
                        } else {
                            tokenGen = 1;
                        }
                    } else {
                        tokenGen = 0;
                    }
                } while (tokenGen != 1);
                mapaEnUso15x15[cooBot[0]][cooBot[1]][0] = bot;
            }else{
                if(contOro > 0){
                    //generar solo una moenda y penalizar
                    boolean generado = false;
                    contOro = contOro - random.nextInt(6)+1;
                    do {
                        generado = false;
                        int x = random.nextInt(restriccionMapa);
                        int y = random.nextInt(restriccionMapa);
                        if (mapaEnUso15x15[x][y][0].equals(esVa)) {
                            mapaEnUso15x15[x][y][0] = coin;
                            mapaEnUso15x15[x][y][1] = "1";
                            generado = true;
                        }
                    }while(!generado); //Generar jugador
                }else {
                    System.out.println("No tiene suficiente oro para penalizar");
                }
            }
        }
    }
    public static void revelarMonedas(){
        if (idMapa == 0){
            for (int y = 0; y < 30; y++){
                for(int x = 0; x < 30; x++){
                    if(mapaEnUso30x30[x][y][0].equals(coin)){
                        mapaEnUso30x30[x][y][1] = "1"; //1 es moneda visible
                    }
                }
            }
        }else {
            for (int y = 0; y < 15; y++){
                for(int x = 0; x < 15; x++){
                    if(mapaEnUso15x15[x][y][0].equals(coin)){
                        mapaEnUso15x15[x][y][1] = "1"; //1 es moneda visible
                    }
                }
            }
        }
    } //inicia todas las monedas encontradas con el estado visible antes de ejecutar primer turno
    public static void revelarMonedasEnJuego(){
        if (idMapa == 0){
            for (int y = 0; y < 30; y++){
                for(int x = 0; x < 30; x++){
                    if(mapaEnUso30x30[x][y][1].equals("1")){
                        if(mapaEnUso30x30[x][y][0].equals(play)||mapaEnUso30x30[x][y][0].equals(bot)){
                            mapaEnUso30x30[x][y][0] = mapaEnUso30x30[x][y][0];
                            //mapaEnUso30x30[x][y][0] = play;
                        }else{
                            mapaEnUso30x30[x][y][0] = coin;
                        }
                    }
                }
            }
        }else {
            for (int y = 0; y < 15; y++){
                for(int x = 0; x < 15; x++){
                    if(mapaEnUso15x15[x][y][1].equals("1")){
                        if(mapaEnUso15x15[x][y][0].equals(play)||mapaEnUso15x15[x][y][0].equals(bot)){
                            mapaEnUso15x15[x][y][0] = mapaEnUso15x15[x][y][0];
                            //mapaEnUso15x15[x][y][0] = play;
                        }else{
                            mapaEnUso15x15[x][y][0] = coin;
                        }
                    }
                }
            }
        }
    }
    public static void dibMapaJugar() {
        switch (idMapa){
            case 0:
                for (int y = 0; y < 30; y++) {
                    for (int x = 0; x < 30; x++) {
                        System.out.print(mapaEnUso30x30[x][y][0]); // i = x; j = y
                    }
                    System.out.println(" ");
                }
                break;
            default:
                for (int y = 0; y < 15; y++) {
                    for (int x = 0; x < 15; x++) {
                        System.out.print(mapaEnUso15x15[x][y][0]); // i = x; j = y
                    }
                    System.out.println(" ");
                }
                break;
        }
    } //dibuja los mapas con elementos agregados
    public static void mirarMapa() {
        int xMen;
        int xMay;
        int yMen;
        int yMay;

        if(idMapa == 0){
            //limitar casillas 5x5
            xMen = cooPlay[0] - 2;
            xMay = cooPlay[0] + 2;
            yMen = cooPlay[1] - 2;
            yMay = cooPlay[1] + 2;
            //Limitar a los bordes del mapa
            if (xMen < 0) {
                xMen = 0;
            }
            if (yMen < 0) {
                yMen = 0;
            }
            if (xMay > 29) {
                xMay = 29;
            }
            if (yMay > 29) {
                yMay = 29;
            }

            for (int y = yMen; y <= yMay; y++) {
                for (int x = xMen; x <= xMay; x++) {
                    System.out.print(mapaEnUso30x30[x][y][0]);
                }
                System.out.println(" ");
            }
        }else{
            xMen = cooPlay[0] - 2;
            xMay = cooPlay[0] + 2;
            yMen = cooPlay[1] - 2;
            yMay = cooPlay[1] + 2;
            //Limitar a los bordes del mapa
            if (xMen < 0) {
                xMen = 0;
            }
            if (yMen < 0) {
                yMen = 0;
            }
            if (xMay > 14) {
                xMay = 14;
            }
            if (yMay > 14) {
                yMay = 14;
            }

            for (int y = yMen; y <= yMay; y++) {
                for (int x = xMen; x <= xMay; x++) {
                    System.out.print(mapaEnUso15x15[x][y][0]);
                }
                System.out.println(" ");
            }
        }
    } //imprime el comando MIRAR para el jugador (5x5 por defecto)
    public static void mirarMapaBot(){
        int xMen = cooBot[0] - 2;
        int xMay = cooBot[0] + 2;
        int yMen = cooBot[1] - 2;
        int yMay = cooBot[1] + 2;

        if(idMapa == 0){// limitador = 30;
            if (xMen < 0) {
                xMen = 0;
            }
            if (yMen < 0) {
                yMen = 0;
            }
            if (xMay > 29) {
                xMay = 29;
            }
            if (yMay > 29) {
                yMay = 29;
            }

            for (int y = yMen; y <= yMay; y++) { //Bot busca al jugador en rango definido 5 x 5
                for (int x = xMen; x <= xMay; x++) {
                    if(mapaEnUso30x30[x][y][0].equals(play)){ //i = x; j = y
                        findPlayer = true;
                        x = xMay;
                        y = yMay;
                    }else{
                        findPlayer = false;
                    }
                }
            }
        }else{
            if (xMen < 0) {
                xMen = 0;
            }
            if (yMen < 0) {
                yMen = 0;
            }
            if (xMay > 14) {
                xMay = 14;
            }
            if (yMay > 14) {
                yMay = 14;
            }
            for (int y = yMen; y <= yMay; y++) { //Bot busca al jugador en rango definido 5 x 5
                for (int x = xMen; x <= xMay; x++) {
                    if(mapaEnUso15x15[x][y][0].equals(play)){ //i = x; j = y
                        findPlayer = true;
                        x = xMay;
                        y = yMay;
                    }else{
                        findPlayer = false;
                    }
                }
            }
        }
        if (findPlayer == true){
            if (idMapa == 0){
                System.out.println("El bot te ha visto");
                cantBotViewPlayer = cantBotViewPlayer +1;
                dirMovBot = 4;
                if(cooPlay[0] == cooBot[0]){ //si las x son iguales -----------------------------------------------------------------------
                    if(cooPlay[1] < cooBot[1]){ //si jugador está arriba de bot
                        if((mapaEnUso30x30[cooBot[0]][cooBot[1]-1][0].equals(esVa)||mapaEnUso30x30[cooBot[0]][cooBot[1]-1][0].equals(play))||mapaEnUso30x30[cooBot[0]][cooBot[1]-1][0].equals(coin)){
                            dirMovBot = 0;
                        }else{

                            dirMovBot = 4;//4 aleatorio
                        }
                    }else{ //si jugador está abajo de bot
                        if((mapaEnUso30x30[cooBot[0]][cooBot[1]+1][0].equals(esVa)||mapaEnUso30x30[cooBot[0]][cooBot[1]+1][0].equals(play))||mapaEnUso30x30[cooBot[0]][cooBot[1]+1][0].equals(coin)) {
                            dirMovBot = 2;
                        }else{
                            dirMovBot = 4;
                        }
                    }
                }
                if(cooPlay[1] == cooBot[1]){ //Si las Y son iguales -----------------------------------------------------------------------
                    if(cooPlay[0] < cooBot[0]){ //si jugador está a la izquierda de bot
                        if((mapaEnUso30x30[cooBot[0]-1][cooBot[1]][0].equals(esVa)||mapaEnUso30x30[cooBot[0]-1][cooBot[1]][0].equals(play))||mapaEnUso30x30[cooBot[0]-1][cooBot[1]][0].equals(coin)){
                            dirMovBot = 3;
                        }else{
                            dirMovBot = 4;
                        }
                    }else{ //si jugador está a la derecha de bot
                        if((mapaEnUso30x30[cooBot[0]+1][cooBot[1]][0].equals(esVa)||mapaEnUso30x30[cooBot[0]+1][cooBot[1]][0].equals(play))||mapaEnUso30x30[cooBot[0]+1][cooBot[1]][0].equals(coin)) {
                            dirMovBot = 1;
                        }else{
                            dirMovBot = 4;
                        }
                    }
                }
            }else{ //si idMapa != 0
                System.out.println("El bot te ha visto");
                cantBotViewPlayer = cantBotViewPlayer + 1;
                if(cooPlay[0] == cooBot[0]){ //si las x son iguales -----------------------------------------------------------------------
                    if(cooPlay[1] < cooBot[1]){ //si jugador está arriba de bot
                        if((mapaEnUso15x15[cooBot[0]][cooBot[1]-1][0].equals(esVa)||mapaEnUso15x15[cooBot[0]][cooBot[1]-1][0].equals(play))||(mapaEnUso15x15[cooBot[0]][cooBot[1]-1][0].equals(coin))){
                            dirMovBot = 0;
                        }else{
                            dirMovBot = 4;
                        }
                    }else{ //si jugador está abajo de bot
                        if((mapaEnUso15x15[cooBot[0]][cooBot[1]+1][0].equals(esVa)||mapaEnUso15x15[cooBot[0]][cooBot[1]+1][0].equals(play))||mapaEnUso15x15[cooBot[0]][cooBot[1]+1][0].equals(coin)) {
                            dirMovBot = 2;
                        }else{
                            dirMovBot = 4;
                        }
                    }
                }
                if(cooPlay[1] == cooBot[1]){ //Si las Y son iguales -----------------------------------------------------------------------
                    if(cooPlay[0] < cooBot[0]){ //si jugador está a la izquierda de bot
                        if((mapaEnUso15x15[cooBot[0]-1][cooBot[1]][0].equals(esVa)||mapaEnUso15x15[cooBot[0]-1][cooBot[1]][0].equals(play))||mapaEnUso15x15[cooBot[0]-1][cooBot[1]][0].equals(coin)){
                            dirMovBot = 3;
                        }else{
                            dirMovBot = 4;
                        }
                    }else{ //si jugador está a la derecha de bot
                        if((mapaEnUso15x15[cooBot[0]+1][cooBot[1]][0].equals(esVa)||mapaEnUso15x15[cooBot[0]+1][cooBot[1]][0].equals(play))||mapaEnUso15x15[cooBot[0]+1][cooBot[1]][0].equals(coin)) {
                            dirMovBot = 1;
                        }else{
                            dirMovBot = 4;
                        }
                    }
                }
            }
        }else{
            dirMovBot = 4;
        }
    }
    public static void moverBot() {
        if (idMapa == 0) {
            if (findPlayer == true) {
                System.out.println("El bot se ha movido");
                if (dirMovBot == 0) {
                    mapaEnUso30x30[cooBot[0]][cooBot[1] - 1][0] = bot;
                    mapaEnUso30x30[cooBot[0]][cooBot[1]][0] = esVa;
                    cooBot[1] = cooBot[1] - 1;
                }
                if (dirMovBot == 2) {
                    mapaEnUso30x30[cooBot[0]][cooBot[1] + 1][0] = bot;
                    mapaEnUso30x30[cooBot[0]][cooBot[1]][0] = esVa;
                    cooBot[1] = cooBot[1] + 1;
                }
                if (dirMovBot == 3) {
                    mapaEnUso30x30[cooBot[0] - 1][cooBot[1]][0] = bot;
                    mapaEnUso30x30[cooBot[0]][cooBot[1]][0] = esVa;
                    cooBot[0] = cooBot[0] - 1;
                }
                if (dirMovBot == 1) {
                    mapaEnUso30x30[cooBot[0] + 1][cooBot[1]][0] = bot;
                    mapaEnUso30x30[cooBot[0]][cooBot[1]][0] = esVa;
                    cooBot[0] = cooBot[0] + 1;
                }
                if (dirMovBot == 4) {
                    movBotAleatorio();
                }
            } else {//movimiento aleatorio
                movBotAleatorio();
            }
        } else {
            if (findPlayer == true) {
                System.out.println("El bot se ha movido");
                if (dirMovBot == 0) {
                    mapaEnUso15x15[cooBot[0]][cooBot[1] - 1][0] = bot;
                    mapaEnUso15x15[cooBot[0]][cooBot[1]][0] = esVa;
                    cooBot[1] = cooBot[1] - 1;
                }
                if (dirMovBot == 2) {
                    mapaEnUso15x15[cooBot[0]][cooBot[1] + 1][0] = bot;
                    mapaEnUso15x15[cooBot[0]][cooBot[1]][0] = esVa;
                    cooBot[1] = cooBot[1] + 1;
                }
                if (dirMovBot == 3) {
                    mapaEnUso15x15[cooBot[0] - 1][cooBot[1]][0] = bot;
                    mapaEnUso15x15[cooBot[0]][cooBot[1]][0] = esVa;
                    cooBot[0] = cooBot[0] - 1;
                }
                if (dirMovBot == 1) {
                    mapaEnUso15x15[cooBot[0] + 1][cooBot[1]][0] = bot;
                    mapaEnUso15x15[cooBot[0]][cooBot[1]][0] = esVa;
                    cooBot[0] = cooBot[0] + 1;
                }
                if (dirMovBot == 4) {
                    movBotAleatorio();
                }
            } else {//movimiento aleatorio
                movBotAleatorio();
            }

        }
    }
    public static void movBotAleatorio(){
        Random random = new Random();
        int valido = 0; //valida si el movimiento del bot es posible (valido = 1 -> posible), de lo contrario lo intenta de nuevo
        int movBot; //0 arriba, 1 derecha, 2 abajo, 3 izquierda
        if(idMapa == 0){
            do {
                movBot = random.nextInt(4);
                switch (movBot){
                    case 0: //arriba
                        if((cooBot[1]) > 0){ //limitar al borde del mapa
                            if (mapaEnUso30x30[cooBot[0]][cooBot[1] - 1][0].equals(esVa) || mapaEnUso30x30[cooBot[0]][cooBot[1] - 1][0].equals(coin)) {
                                cooBot[1] = cooBot[1] - 1;
                                mapaEnUso30x30[cooBot[0]][cooBot[1]][0] = bot;
                                mapaEnUso30x30[cooBot[0]][cooBot[1]+1][0] = esVa;
                                valido = 1;
                            } else {
                                valido = 0;
                            }
                        }else{
                            valido = 0;
                        }
                        break;
                    case 1://derecha
                        if((cooBot[0]) < 29){ //limitar al borde del mapa
                            if (mapaEnUso30x30[cooBot[0]+1][cooBot[1]][0].equals(esVa) || mapaEnUso30x30[cooBot[0]+1][cooBot[1]][0].equals(coin)) {
                                cooBot[0] = cooBot[0] + 1;
                                mapaEnUso30x30[cooBot[0]][cooBot[1]][0] = bot;
                                mapaEnUso30x30[cooBot[0]-1][cooBot[1]][0] = esVa;
                                valido = 1;
                            } else {
                                valido = 0;
                            }
                        }else{
                            valido = 0;
                        }
                        break;
                    case 2: //abajo
                        if((cooBot[1]) < 29){ //limitar al borde del mapa
                            if (mapaEnUso30x30[cooBot[0]][cooBot[1]+1][0].equals(esVa) || mapaEnUso30x30[cooBot[0]][cooBot[1]+1][0].equals(coin)) {
                                cooBot[1] = cooBot[1] + 1;
                                mapaEnUso30x30[cooBot[0]][cooBot[1]][0] = bot;
                                mapaEnUso30x30[cooBot[0]][cooBot[1]-1][0] = esVa;
                                valido = 1;
                            } else {
                                valido = 0;
                            }
                        }else{
                            valido = 0;
                        }
                        break;
                    case 3: //izquierda
                        if((cooBot[0]) > 0){ //limitar al borde del mapa
                            if (mapaEnUso30x30[cooBot[0]-1][cooBot[1]][0].equals(esVa) || mapaEnUso30x30[cooBot[0]-1][cooBot[1]][0].equals(coin)) {
                                cooBot[0] = cooBot[0] - 1;
                                mapaEnUso30x30[cooBot[0]][cooBot[1]][0] = bot;
                                mapaEnUso30x30[cooBot[0]+1][cooBot[1]][0] = esVa;
                                valido = 1;
                            } else {
                                valido = 0;
                            }
                        }else{
                            valido = 0;
                        }
                        break;
                }
            }while (valido!=1);
        }else{
            do {
                movBot = random.nextInt(4);
                switch (movBot){
                    case 0: //arriba
                        if((cooBot[1]) > 0){ //limitar al borde del mapa
                            if (mapaEnUso15x15[cooBot[0]][cooBot[1] - 1][0].equals(esVa) || mapaEnUso15x15[cooBot[0]][cooBot[1] - 1][0].equals(coin)) {
                                cooBot[1] = cooBot[1] - 1;
                                mapaEnUso15x15[cooBot[0]][cooBot[1]][0] = bot;
                                mapaEnUso15x15[cooBot[0]][cooBot[1]+1][0] = esVa;
                                valido = 1;
                            } else {
                                valido = 0;
                            }
                        }else{
                            valido = 0;
                        }
                        break;
                    case 1://derecha
                        if((cooBot[0]) < 14){ //limitar al borde del mapa
                            if (mapaEnUso15x15[cooBot[0]+1][cooBot[1]][0].equals(esVa) || mapaEnUso15x15[cooBot[0]+1][cooBot[1]][0].equals(coin)) {
                                cooBot[0] = cooBot[0] + 1;
                                mapaEnUso15x15[cooBot[0]][cooBot[1]][0] = bot;
                                mapaEnUso15x15[cooBot[0]-1][cooBot[1]][0] = esVa;
                                valido = 1;
                            } else {
                                valido = 0;
                            }
                        }else{
                            valido = 0;
                        }
                        break;
                    case 2: //abajo
                        if((cooBot[1]) < 14){ //limitar al borde del mapa
                            if (mapaEnUso15x15[cooBot[0]][cooBot[1]+1][0].equals(esVa) || mapaEnUso15x15[cooBot[0]][cooBot[1]+1][0].equals(coin)) {
                                cooBot[1] = cooBot[1] + 1;
                                mapaEnUso15x15[cooBot[0]][cooBot[1]][0] = bot;
                                mapaEnUso15x15[cooBot[0]][cooBot[1]-1][0] = esVa;
                                valido = 1;
                            } else {
                                valido = 0;
                            }
                        }else{
                            valido = 0;
                        }
                        break;
                    case 3: //izquierda
                        if((cooBot[0]) > 0){ //limitar al borde del mapa
                            if (mapaEnUso15x15[cooBot[0]-1][cooBot[1]][0].equals(esVa) || mapaEnUso15x15[cooBot[0]-1][cooBot[1]][0].equals(coin)) {
                                cooBot[0] = cooBot[0] - 1;
                                mapaEnUso15x15[cooBot[0]][cooBot[1]][0] = bot;
                                mapaEnUso15x15[cooBot[0]+1][cooBot[1]][0] = esVa;
                                valido = 1;
                            } else {
                                valido = 0;
                            }
                        }else{
                            valido = 0;
                        }
                        break;
                }
            }while (valido!=1);
        }
    }
    public static void compBotGana(){
        if(cooBot[0] == cooPlay[0] && cooBot[1]== cooPlay[1]){
            System.out.println("Ha sido atrapado por el bot");
            cantBotWin = cantPlayerWin + 1;
            if(idMapa == 0){
                contMapaPrincipal[0][2] = contMapaPrincipal[0][2] + 1;
            }
            if (idMapa == 1){
                contMapaA[0][2] = contMapaA[0][2] + 1;
            }
            if (idMapa == 2){
                contMapaB[0][2] = contMapaB[0][2] +1;
            }
            finjuego = true;
        }
    }//comprueba si el bot gana la partida
      
    //configuraciones generales
    public static void confTiempoBot(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el tiempo (en milisegundos) que desea que duren los turnos del bot      ");
        System.out.println(" --------------------------------- ALERTA ------------------------------------- ");
        System.out.println("La jugabilidad puede verse afectada negativamente. Mecanicas como el tiempo de  ");
        System.out.println("visualización de monedas actuales y el mapa dependen del valor de espera del bot");
        System.out.println("Por favor ingrese el tiempo en milisegundos.");
       
        do{
        esperaBot = (scanner.nextInt());
        scanner.nextLine();
        }while(esperaBot < 0);
      
    }
    public static void confMapaView() {
        Scanner scanner = new Scanner(System.in);
        limpiarPantalla();
        System.out.println("CONFIGURACION DE VISTA DE MAPA");
        System.out.println("Por favor ingrese el tipo de vista que desea durante el juego");
        System.out.println("Puede cambiar esta vista posteriormente en: Menú Inicial -> Configuración de vista de mapa");
        System.out.println(" ");
        System.out.println("1. SIN VISTA POR  TURNOS (FIEL A LOS REQUERIMIENTOS DE LA PRÁCTICA)");
        System.out.println("   Esta opción es una representación fiel de lo requerido en la práctica.");
        System.out.println("   No muestra ninguna parte del mapa a menos de que ingresemos el comando");
        System.out.println("   para observar una parte del mapa, pero gasta un turno.");
        System.out.println(" ");
        System.out.println("2. VISTA PARCIAL PERMANENTE POR TURNOS");
        System.out.println("   Muestra una cuadrícula de 5x5 permanente con el jugador siempre en el centro.");
        System.out.println("   Puede seleccionar esta opción por comodidad de juego o calificación.");
        System.out.println(" ");
        System.out.println("3. VISTA TOTAL PERMANENTE POR TURNOS");
        System.out.println("    Muestra la totalidad del mapa después de cada turno.");
        System.out.println("    Puede seleccionar esta opción por comodidad de juego o calificación.");

        confGrafica = scanner.nextInt();
        scanner.nextLine();
        if(confGrafica < 1 || confGrafica > 3){
            confGrafica = 1;
            System.out.println("Ha ingresado un valor no válido para la vista de mapa");
            System.out.println("Se ha aplicado la configuración por defecto: SIN VISTA POR TURNOS");
        }
    }
    
    public static void mostrarMapa(){
        switch(confGrafica){
            case 1:
                //System.out.println("Debe ingresar el comando " + mirMapa + " para ver una porción del mapa");
                break;
            case 2:
                mirarMapa();
                break;
            case 3:
                dibMapaJugar();
                break;
        }
    }

    //Funciones para reportes
    public static void reporteFinPartida(){
        System.out.println(" ");
        System.out.println("Estos son los Reportes de la partida terminada: ");
        System.out.println(" ");
        System.out.println("Cantidad de oro recolectado: "+ contOro);
        System.out.println("Cantidad de movimientos realizados por el jugador: " + contTurnoJugador);
        System.out.println("Cantidad de movimientos realizados por el bot: " + conTurnoBot);
        System.out.println("Cantidad de veces que se estuvo en la visión del bot: " + cantBotViewPlayer);

    }
    public static double promOroPartida(){
        if(contPartida == 0){
            return 0;
        }else{
            return (contOroTotal/contPartida);
        }

    }
    public static double promMovimientos(){
        if(contPartida == 0){
            return 0;
        }else{
            return (contMovTotal/contPartida);
        }

    }
    public static String mapaMasJugado(){
        if(contMapaPrincipal[0][0]>contMapaA[0][0]) {
            if (contMapaPrincipal[0][0] > contMapaB[0][0]) {
                return ("El mapa más jugado es el mapa principal");
            } else {
                return ("El mapa más jugado es: " + nombreRanuraB);
            }
        }else{
            if (contMapaA[0][0] > contMapaB[0][0]){
                return ("El mapa más jugado es: " + nombreRanuraA);
            }else{
                return ("El mapa más jugado es: " + nombreRanuraB);
            }
        }
    }
    public static String mapaMasGanado(){
        if(contMapaPrincipal[0][1]>contMapaA[0][1]) {
            if (contMapaPrincipal[0][1] > contMapaB[0][1]) {
                return ("El mapa más ganado es el mapa principal");
            } else {
                return ("El mapa más ganado es: " + nombreRanuraB);
            }
        }else{
            if (contMapaA[0][0] > contMapaB[0][0]){
                return ("El mapa más ganado es: " + nombreRanuraA);
            }else{
                return ("El mapa más ganado es: " + nombreRanuraB);
            }
        }
    }
    public static String mapaMasPerdido(){
        if(contMapaPrincipal[0][2]>contMapaA[0][1]) {
            if (contMapaPrincipal[0][2] > contMapaB[0][2]) {
                return ("El mapa más perdido es el mapa principal");
            } else {
                return ("El mapa más perdido es: " + nombreRanuraB);
            }
        }else{
            if (contMapaA[0][2] > contMapaB[0][2]){
                return ("El mapa más perdido es: " + nombreRanuraA);
            }else{
                return ("El mapa más perdido es: " + nombreRanuraB);
            }
        }
    }
    public static void reporteGeneral(){
        System.out.println(" ");
        System.out.println("Estos son los reportes de la última partida");
        reporteFinPartida();
        System.out.println(" ");
        System.out.println("Estos son los reportes generales: ");
        System.out.println(" ");
        System.out.println("Partidas ganadas por el bot: " + cantBotWin);
        System.out.println("Partidas ganadas por el jugador: " + cantPlayerWin);
        System.out.println("Promedio de oro por partida: " + promOroPartida());
        System.out.println("Promedio de movimientos / partida: " +  promMovimientos());
        System.out.println("El mapa más jugado es: " + mapaMasJugado());
        System.out.println("El mapa más ganado es: " + mapaMasGanado());
        System.out.println("El mapa más perdido es: " + mapaMasPerdido());
        System.out.println("Total de mapas creados: " + contRanura);

    }
}

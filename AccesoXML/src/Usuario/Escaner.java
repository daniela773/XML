package Usuario;

import java.util.Scanner;

public class Escaner {
    public static int leerInt(String enunciado) {
        int opcionNumero;
        Scanner scn = new Scanner(System.in);
        System.out.println(enunciado);
        opcionNumero = scn.nextInt();

        return opcionNumero;
    }

    public static String leerString(String enunciado) {
        String opcion;
        Scanner scn = new Scanner(System.in);
        System.out.println(enunciado);
        opcion = scn.next();

        return opcion;
    }
}

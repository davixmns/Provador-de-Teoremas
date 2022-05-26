package Tarcizo;


public class Teste {
    public static void main(String[] args) {
//        Analisador analisador = new Analisador();
        FBF verificaFBF = new FBF();

//        String invalidos = "~aDlq.ca➝↔";

        String formula = " c ➝ (a ^ b) ";

//        System.out.println(analisador.contabilizaInvalidos(invalidos));
        System.out.println(verificaFBF.verificaFbf(formula)); //  Verificador de fbf

    }
}


/*
 *
 * "~", não
 * "^", e
 * "v", ou
 * "➝", se...então
 * "↔" se somente se
 *
 */

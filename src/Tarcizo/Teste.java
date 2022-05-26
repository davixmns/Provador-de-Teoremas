package Tarcizo;


public class Teste {
    public static void main(String[] args) {

        String formula = " c ➝ (a ^ b) ";

        //  Analisador analisador = new Analisador();  Analisador Lexico
        FBF verificaFBF = new FBF(formula);

        //   System.out.println(analisador.contabilizaInvalidos("~aDlq.ca➝↔")); Analisador Lexico
        System.out.println(verificaFBF.verificaFbf()); //  Verificador de fbf

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

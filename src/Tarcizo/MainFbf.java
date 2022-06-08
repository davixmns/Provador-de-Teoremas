package Tarcizo;


public class MainFbf {
    public static void main(String[] args) {

        String a = "A➝B";
        String b = "~(A^B) ➝ (AvB)";
        String c = "(((A ➝B)";
        String d = "B";
        String e = "A^^B➝A";
        String formula = b;

        FBF fbf = new FBF();

        System.out.println(formula);
        if(fbf.verificaFbf(formula)) System.out.print("Configurada como fbf");
        else System.out.print("Nao configurada como uma fbf");
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

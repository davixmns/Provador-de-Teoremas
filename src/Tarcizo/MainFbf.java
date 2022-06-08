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
        System.out.println(fbf.verificaFbf(formula));

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

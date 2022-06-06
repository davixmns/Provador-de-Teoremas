import Tarcizo.FBF;
import alisson.TabelaVerdade.TabelaVerdade;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        FBF fbf = new FBF();

        System.out.print("Expressão: ");
        Scanner scan = new Scanner(System.in);
        String expressao = scan.nextLine();
        scan.close();

        if(fbf.verificaFbf(expressao)) {
            TabelaVerdade.showTabela(expressao);
        }else {
            System.out.println("Expressão inválida");
        }
    }
}

package alisson;

import alisson.Arvore.ArvoreBinariaExpressao;
import alisson.TabelaVerdade.TabelaVerdade;
import Tarcizo.FBF;

// Simbolos
// ~ ^ v ➝ ↔

public class Main {
    public static void main(String[] args) {
        FBF fbf = new FBF();
        String expressao = "(A ➝ B) ↔ (~A v B)";
//        String expressao = "A^B➝C"; // Exemplo Contingência
//        String expressao = "~A^A"; // Exemplo Contingência

        TabelaVerdade.showTabela(expressao);
    }
}

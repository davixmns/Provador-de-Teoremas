package alisson;

import alisson.Arvore.Conversor;

// Simbolos
// ~ ^ v ➝ ↔

public class Main {
    public static void main(String[] args) {
//        String expressao = "(A ➝ B) ↔ (~A v B)";
        String expressao = "A^B➝C"; // Exemplo Contingência
//        String expressao = "~A^A"; // Exemplo Contingência

        //TabelaVerdade.showTabela(expressao);
        System.out.printf("Expressão infixa \"%s\"\n", expressao);
        System.out.printf("Pós-fixa \"%s\"\n",Conversor.infixParaPosfixa(expressao.replace(" ", "")));
    }
}

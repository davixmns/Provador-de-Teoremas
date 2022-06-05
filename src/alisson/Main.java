package alisson;

import alisson.Arvore.ArvoreBinariaExpressao;
import alisson.TabelaVerdade.TabelaVerdade;

// Simbolos
// ~ ^ v ➝ ↔

public class Main {
    public static void main(String[] args) {
         TabelaVerdade.showTabela("(A➝B)↔(~AvB)"); // Exemplo Tautologia
//         TabelaVerdade.showTabela("A^B➝C"); // Exemplo Contingência
//         TabelaVerdade.showTabela("~A^A"); // Exemplo Contradição
    }
}

package alisson.TabelaVerdade;

import alisson.Arvore.ArvoreBinariaExpressao;

import java.util.*;

public class TabelaVerdade extends OperacoesTabelaVerdade{
    public TabelaVerdade() {
    }

    public static void showTabela(String expressao) {
        ArvoreBinariaExpressao arvore = new ArvoreBinariaExpressao(expressao);
        int totalProposicoes = arvore.variaveis.size();
        Stack<boolean[]> casos = getCasosProposicoes(totalProposicoes);
        List<String> proposicoes = new ArrayList<>(arvore.variaveis.keySet());

        System.out.printf("Total de Proposições: %d\n", totalProposicoes);
        // Testa todos os casos
        while(!casos.isEmpty()) {
            boolean[] caso = casos.pop();
            System.out.println("---------");
            for (int i = 0; i < totalProposicoes; i++) {
                String proposicao = proposicoes.get(i);
                arvore.variaveis.replace(proposicao, caso[i]);
                System.out.printf("%s = %b\n", proposicao, caso[i]);
            }
            boolean resultado = arvore.calcular();
            System.out.println(resultado);
        }
    }

    public static Stack<boolean[]> getCasosProposicoes(int totalProposicoes) {
        Stack casos = new Stack();
        int totalCasos = (int) Math.pow(2, totalProposicoes);
        for(int i=0; i < totalCasos; i++) {
            boolean resultado[] = decimalParaBinario(i, totalProposicoes);
            casos.push(resultado);
        }

        return casos;
    }

    public static boolean[] decimalParaBinario(int num, int size) {
        boolean binario[] = new boolean[size];
        int resultado = num;
        int i=size-1;
        while(resultado != 0) {
            int resto = resultado % 2;
            binario[i] = resto == 1;
            resultado /= 2;
            i--;
        }
        return binario;
    }
}

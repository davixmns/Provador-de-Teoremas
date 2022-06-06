package alisson.TabelaVerdade;

import alisson.Arvore.ArvoreBinariaExpressao;

import java.util.*;

public class TabelaVerdade extends OperacoesTabelaVerdade{
    public TabelaVerdade() {
    }

    public static void showTabela(String expressao) {
        expressao = expressao.replace(" ", "");
        ArvoreBinariaExpressao arvore = new ArvoreBinariaExpressao(expressao);
        int totalProposicoes = arvore.variaveis.size();
        Stack<boolean[]> casos = getCasosProposicoes(totalProposicoes);
        List<String> proposicoes = new ArrayList<>(arvore.variaveis.keySet());

        System.out.printf("Total de Proposições: %d\n", totalProposicoes);
        for (int i = 0; i < totalProposicoes; i++) {
            String proposicao = proposicoes.get(i);
            System.out.printf("%s|", proposicao);
        }
        System.out.println(expressao);

        // Testa todos os casos
        Queue<Boolean> registroResultados = new LinkedList<>();
        while(!casos.isEmpty()) {
            boolean[] caso = casos.pop();
            for (int i = 0; i < totalProposicoes; i++) {
                String proposicao = proposicoes.get(i);
                arvore.variaveis.replace(proposicao, caso[i]);
            }

            boolean resultado = arvore.calcular();
            registroResultados.add(resultado);
            for (int i = 0; i < totalProposicoes; i++) {
                String estadoProposicao = caso[i] ? "V" : "F";
                System.out.printf("%s|", estadoProposicao);
            }
            System.out.println(resultado ? "V" : "F");
        }

        // Mostra se é contingência, Tautologia ou Contradição
        String resultado = "Tautologia";
        Boolean isContradicao = false;
        Boolean flagVerdade = false;
        Boolean flagFalse = false;
        while(!registroResultados.isEmpty()) {
            Boolean registro = registroResultados.poll();
            if(registro)
                flagVerdade = true;
            else
                flagFalse = true;

            if(flagVerdade && flagFalse) {
                resultado = "Contingência";
                break;
            }else if(flagVerdade == false && flagFalse == true) {
                // É Contradição ou contingência ?
                isContradicao = true;
                resultado = "Contradição";
            }
        }
        System.out.printf("A operação: %s é uma %s\n", expressao, resultado.toUpperCase());
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

package alisson.Arvore;
/*
* https://en.wikipedia.org/wiki/Binary_expression_tree
* */

import alisson.TabelaVerdade.TabelaVerdade;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static alisson.Arvore.Conversor.infixToPostfix;

public class ArvoreBinariaExpressao {
    private No raiz;
    private String lastOperacao;
    private Boolean lastOperador;
    public Map<String, Boolean> variaveis;

    public ArvoreBinariaExpressao(String expressao) {
        String expressaoPosFixa = infixToPostfix(expressao);
        inicializaVariaveis(expressaoPosFixa);
        decompoemExpressao(expressaoPosFixa);
    }

    public void decompoemExpressao(String expressaoPosfix) {
        Stack<No> pilha = new Stack();

        for (int i=0; i < expressaoPosfix.length(); i++) {
            String c = String.valueOf(expressaoPosfix.charAt(i));
            boolean simbolo = isSimbolo(c);
            No no;

            if(!simbolo) {
                no = new No(c, null, false);
                pilha.push(no);
            }else {
                no = new No(c, null, true);
                // Verifica se operação requer apenas 1 ou 2 operadores
                if(c.equals("~")) {
                    No noDireita = pilha.pop();
                    no.setDireita(noDireita);
                    noDireita.setPai(no);
                }else {
                    No noDireita = pilha.pop();
                    No noEsquerda = pilha.pop();
                    no.setDireita(noDireita);
                    no.setEsquerda(noEsquerda);
                    noDireita.setPai(no);
                    noEsquerda.setPai(no);
                }
                //System.out.printf("Criando novo nó pai: %s - esquerda %s - direita %s\n", no, noEsquerda, noDireita);
                pilha.push(no);
            }
        }
        raiz = pilha.pop();
    }

    private void inicializaVariaveis(String expressaoPosFixa) {
        this.variaveis = new HashMap<String, Boolean>();
        int tamanho = expressaoPosFixa.length();
        for(int i=0; i < tamanho; i++) {
            String caractere = String.valueOf(expressaoPosFixa.charAt(i));
            if(isSimbolo(caractere)) {continue;}

            this.variaveis.put(caractere, null);
        }
    }

    private boolean isSimbolo(String caractere) {
        return "~^v➝↔".contains(caractere);
    }

    public void show() {
        inFixa(raiz);
    }

    public Boolean calcular() {
        lastOperacao = null;
        lastOperador = null;

        Boolean resultado = calculaArvore(raiz);
        return resultado;
    }

    public Boolean calcularOperacao(Boolean a, Boolean b, String operacao) {
        Boolean resultado = null;
        switch (operacao){
            case "~":
                resultado = TabelaVerdade.nao(a);
                break;
            case "^":
                resultado = TabelaVerdade.e(a, b);
                break;
            case "v":
                resultado = TabelaVerdade.ou(a, b);
                break;
            case "➝":
                resultado = TabelaVerdade.se(a, b);
                break;
            case "↔":
                resultado = TabelaVerdade.apenaSe(a, b);
                break;
        }
        return resultado;
    }

    private void inFixa(No no) {
        if (no != null) {
            inFixa(no.getEsquerda());
            System.out.println(no.getValor());
            inFixa(no.getDireita());
        }
    }

    private Boolean calculaArvore(No no) {
        if (no != null) {
            calculaArvore(no.getEsquerda());

            if(no.isOperador()) {
                lastOperacao = no.getValor().toString();
            }else {
                String chave = no.getValor().toString();
                Boolean valor = variaveis.get(chave);
                //System.out.printf("Chave[%s] = %d\n", chave, valor);
                if(lastOperador != null && lastOperacao != null) {
                    Boolean resultado = calcularOperacao(lastOperador, valor, lastOperacao);

                    lastOperacao = null;
                    lastOperador = resultado;
                }else {
                    lastOperador = valor;
                }
            }
            calculaArvore(no.getDireita());
        }
        return lastOperador;
    }

}

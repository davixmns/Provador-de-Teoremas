package alisson.Arvore;
/*
* https://en.wikipedia.org/wiki/Binary_expression_tree
* */

import alisson.TabelaVerdade.TabelaVerdade;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static alisson.Arvore.Conversor.infixParaPosfixa;

public class ArvoreBinariaExpressao {
    private No raiz;
    private String lastOperacao;
    private Boolean lastOperador;
    private Boolean flagOperadorNegacao;
    public Map<String, Boolean> variaveis;

    public ArvoreBinariaExpressao(String expressao) {
        String expressaoPosFixa = infixParaPosfixa(expressao);
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
        this.flagOperadorNegacao = false;
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
        inOrdem(raiz);
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

//        if(!operacao.equals("~")){
//            System.out.printf("%b %s %b = %b\n", a, operacao, b, resultado);
//        }else {
//            System.out.println("Operação utiliza apenas 1 operador");
//            System.out.printf("%b %s = %b\n", a, operacao, resultado);
//        }
        return resultado;
    }

    private void inOrdem(No no) {
        if (no != null) {
            inOrdem(no.getEsquerda());
            System.out.println(no.getValor());
            inOrdem(no.getDireita());
        }
    }

    private Boolean calculaArvore(No no) {
        if (no != null) {
            calculaArvore(no.getEsquerda());
//            System.out.printf("NO -> GET VALOR -> %s\n", no.getValor());
            if(no.isOperador()) {
                String operacao = no.getValor().toString();
                if(operacao.equals("~")) {
                    this.flagOperadorNegacao = true;
                }else {
                    lastOperacao = operacao;
                }
            }else {
                String chave = no.getValor().toString();
                Boolean valor = variaveis.get(chave);
                //System.out.printf("Chave[%s] = %d\n", chave, valor);
                if(lastOperador != null && lastOperacao != null && !this.flagOperadorNegacao) {
                    Boolean resultado = calcularOperacao(lastOperador, valor, lastOperacao);
                    lastOperacao = null;
                    lastOperador = resultado;
                }else if(this.flagOperadorNegacao) {
                    // Calcula negação
                    Boolean resultado = calcularOperacao(valor, null, "~");

                    // Verifica se possui uma operação anterior à negação
                    if(lastOperador != null) {
                        resultado = calcularOperacao(lastOperador, resultado, lastOperacao);
                    }

                    lastOperacao = null;
                    lastOperador = resultado;
                    this.flagOperadorNegacao = false;
                }else {
                    lastOperador = valor;
                }
            }
            calculaArvore(no.getDireita());
        }
        return lastOperador;
    }

}

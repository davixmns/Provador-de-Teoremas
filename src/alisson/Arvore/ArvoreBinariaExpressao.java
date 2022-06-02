package alisson.Arvore;
/*
* https://en.wikipedia.org/wiki/Binary_expression_tree
* */

import java.util.Stack;

public class ArvoreBinariaExpressao {
    private No raiz;
    private String lastOperacao;
    private Integer lastOperador;

    public ArvoreBinariaExpressao(String expressao) {
        decompoemExpressao(expressao);
    }

    public void decompoemExpressao(String expressaoPosfix) {
        String caracteres[] = expressaoPosfix.split(" ");

        Stack<No> pilha = new Stack();
        for (String c : caracteres) {
            boolean simbolo = isSimbolo(c);
            No no;
            if(!simbolo) {
                Integer valor = Integer.parseInt(c.toString());
                no = new No(valor, null, false);
                pilha.push(no);
            }else {
                no = new No(c, null, true);
                No noDireita = pilha.pop();
                No noEsquerda = pilha.pop();
                noDireita.setPai(no);
                noEsquerda.setPai(no);
                no.setDireita(noDireita);
                no.setEsquerda(noEsquerda);
                System.out.printf("Criando novo nó pai: %s - esquerda %s - direita %s\n", no, noEsquerda, noDireita);
                pilha.push(no);
            }
        }
        raiz = pilha.pop();
    }

    private boolean isSimbolo(String caractere) {
        return "+-*/".contains(caractere);
    }

    public void show() {
        inFixa(raiz);
    }

    public Integer calcular() {
        lastOperacao = null;
        lastOperador = null;
        Integer resultado = calculaArvore(raiz);
        System.out.println(resultado);
        return resultado;
    }

    public Integer calcularOperacao(Integer a, Integer b, String operacao) {
        Integer resultado = null;
        System.out.printf("Operacao: %d %s %d = ?\n", a, operacao, b);

        switch (operacao){
            case "+":
                resultado = a + b;
                break;
            case "-":
                resultado = a - b;
                break;
            case "*":
                resultado = a * b;
                break;
            case "/":
                resultado = a / b;
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

    private Integer calculaArvore(No no) {
        if (no != null) {
            calculaArvore(no.getEsquerda());

            if(no.isOperador()) {
                lastOperacao = no.getValor().toString();
            }else {
                if(lastOperador != null && lastOperacao != null) {
                    Integer valor = Integer.parseInt(no.getValor().toString());
                    Integer resultado = calcularOperacao(lastOperador, valor, lastOperacao);

                    lastOperacao = null;
                    lastOperador = resultado;
                }else {
                    lastOperador = Integer.parseInt(no.getValor().toString());
                }
            }
            calculaArvore(no.getDireita());
        }
        return lastOperador;
    }
}

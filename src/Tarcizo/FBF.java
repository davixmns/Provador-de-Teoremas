package Tarcizo;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class FBF { //ANALISADOR SINTÁTICO
    private List<String> arrayDaFormula;
    private List<String> simbolos;

    public boolean verificaFbf(String formulaRecebida) {
        this.arrayDaFormula = Arrays.asList(formulaRecebida.replaceAll(" ", "").split("(?!^ )"));
        this.simbolos = List.of("~", "^", "v", "➝", "↔");

        return verificaParenteses() && verificaSimbolos();
    }

    public Boolean verificaParenteses() {
        int abrePrentese = 0;
        int fechaParentese = 0;
        boolean confirmaParentese = false;

        Stack<String> pilhaDeParentese = new Stack<>();

        for (String i : this.arrayDaFormula) {
            if (i.equals("(")) {
                abrePrentese++;
                pilhaDeParentese.push(i);
            } else if (i.equals(")")) {
                fechaParentese++;
                pilhaDeParentese.push(i);
            }
        }

        if (pilhaDeParentese.empty()) {
            confirmaParentese = true;
        } else if (pilhaDeParentese.firstElement().equals("(") && pilhaDeParentese.lastElement().equals(")") && abrePrentese == fechaParentese) {
            confirmaParentese = true;
        }

        return confirmaParentese;
    }

    public Boolean verificaSimbolos() {
        boolean confirmaSimbolo = true;
        int posicao = 0;
        Stack<String> pilhaSemParentese = new Stack<>();
        String elementoAtual;
        String elementoProximo;


        for (String i : this.arrayDaFormula) {
            if (!i.equals("(") && !i.equals(")")) {
                pilhaSemParentese.push(i);
            }
        }

        if (!verificaTipo(pilhaSemParentese.firstElement())) {

            while (posicao < pilhaSemParentese.size() - 1) {

                elementoAtual = pilhaSemParentese.elementAt(posicao);
                elementoProximo = pilhaSemParentese.elementAt(posicao + 1);

                if (verificaTipo(elementoAtual) && verificaTipo(elementoProximo)) {
                    confirmaSimbolo = false;
                }
                posicao++;
            }

        } else if (verificaTipo(pilhaSemParentese.firstElement()) && !pilhaSemParentese.firstElement().equals("~")) {
            confirmaSimbolo = false;
        }

        return confirmaSimbolo;
    }

    public boolean verificaTipo(String caracter) {
        //Verifica se o caractere é um simbolo
        return this.simbolos.contains(caracter);
    }

}
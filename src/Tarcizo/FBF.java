package Tarcizo;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class FBF {
    private List<String> arrayDaFormula;
    private List<String> simbolos;

    public FBF(String formulaRecebida) {
        this.arrayDaFormula = Arrays.asList(formulaRecebida.replaceAll(" ", "").split("(?!^ )"));
        this.simbolos = List.of("~", "^", "v", "➝", "↔");
    }

    public boolean verificaFbf() {
        if (verificaParenteses() && verificaSimbolos()) {
            return true;
        }
        return false;
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
        ;


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
        if (this.simbolos.contains(caracter)) return true;
        else return false;
    }

}

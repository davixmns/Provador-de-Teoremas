package alisson.Arvore;

import java.util.Stack;


public class Conversor {
    static int Prioridade(char ch)
    {
        switch (ch)
        {
            case '↔':
                return 1;
            case '➝':
                return 2;
            case 'v':
                return 3;
            case '^':
                return 4;
            case '~':
                return 5;
        }
        return -1;
    }

    public static String infixParaPosfixa(String exp)
    {
        String resultado = new String("");

        Stack<Character> pilha = new Stack<>();

        for (int i = 0; i<exp.length(); ++i) {
            char c = exp.charAt(i);

            if (isVariavelOuDigito(c)) {
                resultado += c;
            }
            else if (c == '(') {
                pilha.push(c);
            }
            else if (c == ')') {
                while (!pilha.isEmpty() && pilha.peek() != '(') {
                    resultado += pilha.pop();
                }
                pilha.pop();
            }
            else {
                // Garante que a pilha conterá apenas os elementos de maior prioridade
                while (!pilha.isEmpty() && Prioridade(c) <= Prioridade(pilha.peek())){
                    resultado += pilha.pop();
                }
                pilha.push(c);
            }

        }

        while (!pilha.isEmpty()){
            if(pilha.peek() == '(')
                return "Expressão inválida";
            // Monta expressão pós-fixa, colocando todos os operadores empilhados
            resultado += pilha.pop();
        }
        return resultado;
    }

    private static boolean isVariavelOuDigito(char c) {
        return Character.isLetterOrDigit(c) && c != 'v';
    }
}

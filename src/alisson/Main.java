package alisson;

import alisson.Arvore.ArvoreBinariaExpressao;

public class Main {
    public static void main(String[] args) {
        String expressao = "A v ~A";
        ArvoreBinariaExpressao arvore = new ArvoreBinariaExpressao("2 5 * 3 +");
        arvore.show();
        arvore.calcular();
    }
}

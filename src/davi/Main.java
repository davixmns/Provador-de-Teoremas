package davi;

public class Main {
    public static void main(String[] args) {
        AnalisadorLexico analisadorLexico = new AnalisadorLexico();
        String expressao = "( P v Q ) ➝ R";
        analisadorLexico.verificarInvalidos(expressao);
    }
}

package davi;

import java.util.List;

public class AnalisadorLexico {
    private final List<String> listaDeValidos;

    public AnalisadorLexico() {
        this.listaDeValidos = List.of("~", "^", "v", "->", "<->");
    }

    public void verificarInvalidos(String linha){
        String[] simbolos = linha.split(" ");

        System.out.print("Simbolos invalidos: ");

        for(String simbolo: simbolos){
            if(!listaDeValidos.contains(simbolo))
                System.out.print(simbolo + " ");
        }
    }

    public static void main(String[] args) {
        AnalisadorLexico al = new AnalisadorLexico();

        String teorema = " c -> ( a ^ b ) ";

        al.verificarInvalidos(teorema);
    }
}

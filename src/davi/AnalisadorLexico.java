package davi;

import java.util.List;

public class AnalisadorLexico {
    private final List<String> listaDeValidos;

    public AnalisadorLexico() {
        this.listaDeValidos = List.of("~", "^", "v", "➝", "↔");
    }

    public void verificarInvalidos(String linha){
        String[] vetorDeSimbolos = linha.split(" ");

        System.out.print("Simbolos invalidos: ");

        for(String simbolo: vetorDeSimbolos){
            if(!listaDeValidos.contains(simbolo))
                System.out.print(simbolo + " ");
        }
    }

}

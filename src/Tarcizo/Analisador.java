package Tarcizo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Analisador { //ANALISADOR LÉXICO
    List<String> listaDeSimbolos = List.of("~", "^", "v", "➝", "↔");

    public String contabilizaInvalidos(String simbolos) {

        String[] simbolosRecebidos = simbolos.split("(?!^)");;
        List<String> simbolosInvalidos = new ArrayList<>();

        for (String i : simbolosRecebidos) {
            if(!listaDeSimbolos.contains(i)) simbolosInvalidos.add(i);
        }

        return "Os simbolos invalidos sao: " + simbolosInvalidos;
    }

}
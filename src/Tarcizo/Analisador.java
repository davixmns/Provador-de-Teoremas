package Tarcizo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Analisador {
    List<String> listaDeSimbolos = List.of("~", "^", "v", "➝", "↔");

    public Boolean verificaExistencia(String recebido, List<String> lista) {
        return lista.contains(recebido);
    }

    public String contabilizaInvalidos(String simbolos) {
        String[] simbolosRecebidos = simbolos.split("(?!^)");
        List<String> simbolosInvalidos = new ArrayList<>();

        for (String i : simbolosRecebidos) {
            if (!verificaExistencia(i, listaDeSimbolos)) {
                if (!verificaExistencia(i, simbolosInvalidos)) {
                    simbolosInvalidos.add(i);
                }
            }
        }
        return "Os simbolos invalidos sao: " + simbolosInvalidos;
    }
}
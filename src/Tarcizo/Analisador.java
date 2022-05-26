package Tarcizo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Analisador {
    List<String> listaDeSimbolos = List.of("~","^","v","➝","↔");


    public Boolean verificaExistencia(String recebido,List<String> lista){

        if (lista.contains(recebido)){
            return true;
        }else{
            return false;
        }
    }

    public String contabilizaInvalidos(String simbolos){
        List<String> simbolosRecebidos = Arrays.asList(simbolos.split("(?!^)"));
        List<String> simbolosInvalidos = new ArrayList<>();

        for (String i: simbolosRecebidos){
            if (!verificaExistencia(i,listaDeSimbolos)){
                if(!verificaExistencia(i,simbolosInvalidos)){
                    simbolosInvalidos.add(i);
                }
            }else{
                continue;
            }
        }
        return "Os simbolos invalidos sao: " + simbolosInvalidos.toString();
    }
}
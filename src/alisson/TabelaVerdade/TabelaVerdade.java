package alisson.TabelaVerdade;

public class TabelaVerdade extends OperacoesTabelaVerdade{
    public TabelaVerdade(String expressao) {

        System.out.println(expressao);
    }

    public void showTabelaSe(String strOperacao) {
        System.out.println(strOperacao);
        for(boolean[] test: getCasosAB()) {
            boolean A = test[0];
            boolean B = test[1];
            boolean result = se(A, B);
            System.out.printf("A = %b, B = %b, A %s B = %b\n", A, B, strOperacao, result);
        }
    }

    public boolean[][] getCasosAB() {
        boolean tests[][] = {{true, true}, {true, false}, {false, true}, {false, false}};
        return tests;
    }
}

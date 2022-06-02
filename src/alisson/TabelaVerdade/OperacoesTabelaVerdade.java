package alisson.TabelaVerdade;

public abstract class OperacoesTabelaVerdade {

    public static boolean e(boolean A, boolean B) {
        return A && B;
    }

    public static boolean ou(boolean A, boolean B) {
        return A || B;
    }

    public static boolean nao(boolean A) {
        return !A;
    }

    public static boolean se(boolean A, boolean B) {
        if(A == true && B == false) {
            return false;
        }
        return true;
    }

    public static boolean apenaSe(boolean A, boolean B) {
        return A == B;
    }

}

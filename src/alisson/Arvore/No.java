package alisson.Arvore;

public class No<T> {
    private T valor;
    private boolean isOperador;
    private No pai;
    private No esquerda, direita;

    public No(T valor, No pai, boolean isOperador) {
        this.valor = valor;
        this.isOperador = isOperador;
        this.pai = pai;
        this.esquerda = null;
        this.direita = null;
    }

    public boolean isOperador() {
        return isOperador;
    }

    public void setOperador(boolean operador) {
        isOperador = operador;
    }

    public No getPai() {
        return pai;
    }

    public void setPai(No pai) {
        this.pai = pai;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    public No getDireita() {
        return direita;
    }

    public void setDireita(No direita) {
        this.direita = direita;
    }

    @Override
    public String toString() {
        return "Valor: " + valor.toString();
    }
}

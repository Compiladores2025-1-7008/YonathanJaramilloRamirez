package main.java;

public class Token {
    private int claseLexica;
    private String lexema;
    private int linea;

    public Token(int claseLexica, String lexema, int linea) {
        this.claseLexica = claseLexica;
        this.lexema = lexema;
        this.linea = linea;
    }

    public int getClaseLexica() {
        return claseLexica;
    }

    public String getLexema() {
        return lexema;
    }

    public int getLinea() {
        return linea;
    }

    @Override
    public String toString() {
        return "Token{" +
                "claseLexica=" + claseLexica +
                ", lexema='" + lexema + '\'' +
                ", linea=" + linea +
                '}';
    }
}

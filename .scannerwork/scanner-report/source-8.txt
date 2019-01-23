package kn.beautynow.dominio.controller;

public class ExceptionCases extends RuntimeException {
    public ExceptionCases() {
        super();
    }
    public ExceptionCases(String s) {
        super(s);
    }
    public ExceptionCases(String s, Throwable throwable) {
        super(s, throwable);
    }
    public ExceptionCases(Throwable throwable) {
        super(throwable);
    }
}

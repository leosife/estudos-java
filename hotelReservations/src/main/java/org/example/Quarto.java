package org.example;

import java.math.BigDecimal;

public class Quarto {
    private int numeroQuarto;
    private Status status;
    private String tipo;
    private BigDecimal valor;


    public Quarto(int numeroQuarto, String tipo, BigDecimal valor, Status st){
        this.numeroQuarto = numeroQuarto;
        this.tipo = tipo;
        this.valor =valor;
        this.status = st;
    }

    public int getNumeroQuarto(){
        return numeroQuarto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Status getStatus() {
        return status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNumeroQuarto(int numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Quarto [" +
                "numero=" + numeroQuarto +
                ", tipo='" + tipo + '\'' +
                ", diária=" + valor +
                ", status=" + status +
                ']';
    }
}



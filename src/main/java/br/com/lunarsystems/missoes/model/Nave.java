package br.com.lunarsystems.missoes.model;

import br.com.lunarsystems.missoes.model.enums.TipoNave;
import java.io.Serializable;

public class Nave implements Serializable {

    private String codigo;
    private String nome;
    private TipoNave tipo;
    private int capacidadeTripulantes;

    public Nave(String codigo, String nome, TipoNave tipo, int capacidadeTripulantes) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipo = tipo;
        this.capacidadeTripulantes = capacidadeTripulantes;
    }

    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public TipoNave getTipo() { return tipo; }
    public int getCapacidadeTripulantes() { return capacidadeTripulantes; }

    @Override
    public String toString() {
        return "[" + tipo + "] " + nome +
                " (capacidade: " + capacidadeTripulantes + ")";
    }
}

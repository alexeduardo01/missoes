package br.com.lunarsystems.missoes.model;

import java.io.Serializable;

public class Astronauta implements Serializable {

    private String nome;
    private int idade;
    private String especialidade;
    private int horasDeVoo;

    public Astronauta(String nome, int idade, String especialidade, int horasDeVoo) {
        this.nome = nome;
        this.idade = idade;
        this.especialidade = especialidade;
        this.horasDeVoo = horasDeVoo;
    }

    public String getNome() { return nome; }
    public int getIdade() { return idade; }
    public String getEspecialidade() { return especialidade; }
    public int getHorasDeVoo() { return horasDeVoo; }

    public void setHorasDeVoo(int horasDeVoo) {
        this.horasDeVoo = horasDeVoo;
    }

    @Override
    public String toString() {
        return nome + " (" + idade + " anos, " +
                especialidade + ", " + horasDeVoo + "h voo)";
    }
}

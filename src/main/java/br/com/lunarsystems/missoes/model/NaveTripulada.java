package br.com.lunarsystems.missoes.model;

import br.com.lunarsystems.missoes.model.enums.TipoNave;

public class NaveTripulada extends Nave {

    public NaveTripulada(String codigo, String nome, int capacidadeTripulantes) {
        super(codigo, nome, TipoNave.TRIPULADA, capacidadeTripulantes);
    }

}

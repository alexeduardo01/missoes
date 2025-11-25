package br.com.lunarsystems.missoes.model;

import br.com.lunarsystems.missoes.model.enums.TipoNave;

public class NaveCarga extends Nave {

    public NaveCarga(String id, String nome, TipoNave tipo, int capacidade) {
        super(id, nome, tipo, capacidade);
    }
}

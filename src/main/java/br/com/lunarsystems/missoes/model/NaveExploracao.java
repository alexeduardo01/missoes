package br.com.lunarsystems.missoes.model;

import br.com.lunarsystems.missoes.model.enums.TipoNave;

public class NaveExploracao extends Nave {

    public NaveExploracao(String id, String nome, TipoNave tipo, int capacidade) {
        super(id, nome, tipo, capacidade);
    }
}

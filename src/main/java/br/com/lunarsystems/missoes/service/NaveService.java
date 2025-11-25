package br.com.lunarsystems.missoes.service;

import br.com.lunarsystems.missoes.model.Nave;
import br.com.lunarsystems.missoes.model.NaveExploracao;
import br.com.lunarsystems.missoes.model.enums.TipoNave;
import br.com.lunarsystems.missoes.persistence.Repositorio;

import java.util.List;

public class NaveService {

    private final Repositorio<Nave> repositorio;

    public NaveService(Repositorio<Nave> repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrarNave(String nome, int capacidade) {
        Nave nave = new NaveExploracao(
                java.util.UUID.randomUUID().toString(),
                nome,
                TipoNave.EXPLORACAO,
                capacidade
        );
        repositorio.salvar(nave);
    }

    public List<Nave> listarNaves() {
        return repositorio.listar();
    }

    public Nave buscarNave(String id) {
        return repositorio.buscarPorId(id);
    }

    public void removerNave(String id) {
        repositorio.remover(id);
    }
}

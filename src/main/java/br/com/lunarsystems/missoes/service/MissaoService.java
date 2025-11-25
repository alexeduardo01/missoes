package br.com.lunarsystems.missoes.service;

import br.com.lunarsystems.missoes.model.Missao;
import br.com.lunarsystems.missoes.model.Nave;
import br.com.lunarsystems.missoes.model.NaveExploracao;
import br.com.lunarsystems.missoes.persistence.Repositorio;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class MissaoService {

    private final Repositorio<Missao> repositorio;

    public MissaoService(Repositorio<Missao> repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrarMissao(String nome, String destino, int capacidade) {
        Nave nave = new NaveExploracao(
                UUID.randomUUID().toString(),
                "Nave Padrão",
                br.com.lunarsystems.missoes.model.enums.TipoNave.EXPLORACAO,
                capacidade
        );

        Missao missao = new Missao(
                UUID.randomUUID().toString(),
                nome,
                LocalDate.now(),
                destino,
                "ATIVA",
                nave
        );

        repositorio.salvar(missao);
    }

    public List<Missao> listarMissoes() {
        return repositorio.listar();
    }

    public Missao buscarMissao(String id) {
        return repositorio.buscarPorId(id);
    }

    public void removerMissao(String id) {
        repositorio.remover(id);
    }
}

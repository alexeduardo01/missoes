package br.com.lunarsystems.missoes.service;

import br.com.lunarsystems.missoes.model.Astronauta;
import br.com.lunarsystems.missoes.persistence.Repositorio;

import java.util.List;

public class AstronautaService {

    private final Repositorio<Astronauta> repositorio;

    public AstronautaService(Repositorio<Astronauta> repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrarAstronauta(String nome, String especialidade) {
        Astronauta a = new Astronauta(nome, 0, especialidade, 0);
        repositorio.salvar(a);
    }

    public List<Astronauta> listarAstronautas() {
        return repositorio.listar();
    }

    public Astronauta buscarAstronauta(int id) {
        return repositorio.buscarPorId(String.valueOf(id));
    }

    public void removerAstronauta(int id) {
        repositorio.remover(String.valueOf(id));
    }
}

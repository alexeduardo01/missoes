package br.com.lunarsystems.missoes.persistence;

import java.util.List;

public interface Repositorio<T> {

    void salvar(T obj);

    List<T> listar();

    T buscarPorId(String id);

    void remover(String id);
}

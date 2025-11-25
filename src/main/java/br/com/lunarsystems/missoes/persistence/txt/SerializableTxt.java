package br.com.lunarsystems.missoes.persistence.txt;

import br.com.lunarsystems.missoes.persistence.Repositorio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializableTxt<T extends Serializable> implements Repositorio<T> {

    private final File arquivo;

    public SerializableTxt(String nomeArquivo) {
        this.arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Não foi possível criar o arquivo " + nomeArquivo, e);
            }
        }
    }

    @Override
    public void salvar(T obj) {
        List<T> lista = listar();
        lista.add(obj);
        gravar(lista);
    }

    @Override
    public List<T> listar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<T>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public T buscarPorId(String id) {
        for (T obj : listar()) {
            try {
                var campo = obj.getClass().getDeclaredField("id");
                campo.setAccessible(true);
                if (campo.get(obj).toString().equals(id)) {
                    return obj;
                }
            } catch (Exception ignored) {}
        }
        return null;
    }

    @Override
    public void remover(String id) {
        List<T> lista = listar();
        lista.removeIf(obj -> {
            try {
                var campo = obj.getClass().getDeclaredField("id");
                campo.setAccessible(true);
                return campo.get(obj).toString().equals(id);
            } catch (Exception e) {
                return false;
            }
        });
        gravar(lista);
    }

    private void gravar(List<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao gravar arquivo", e);
        }
    }
}

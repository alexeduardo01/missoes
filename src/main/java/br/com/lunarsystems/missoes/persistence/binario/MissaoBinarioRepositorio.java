package br.com.lunarsystems.missoes.persistence.binario;

import br.com.lunarsystems.missoes.model.Missao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MissaoBinarioRepositorio {

    private final File arquivo = new File("missoes.dat");

    public void salvar(List<Missao> lista) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(lista);
        } catch (Exception e) {
            System.err.println("Erro ao salvar missões: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Missao> carregar() {
        if (!arquivo.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Missao>) in.readObject();
        } catch (Exception e) {
            System.err.println("Erro ao carregar missões: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

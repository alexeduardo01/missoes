package br.com.lunarsystems.missoes.persistence.binario;

import br.com.lunarsystems.missoes.model.Nave;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NaveBinarioRepositorio {

    private final File arquivo = new File("naves.dat");

    public void salvar(List<Nave> lista) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(lista);
        } catch (Exception e) {
            System.err.println("Erro ao salvar naves: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Nave> carregar() {
        if (!arquivo.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Nave>) in.readObject();
        } catch (Exception e) {
            System.err.println("Erro ao carregar naves: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

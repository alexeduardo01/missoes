package br.com.lunarsystems.missoes.persistence.binario;

import br.com.lunarsystems.missoes.model.Astronauta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AstronautaBinarioRepositorio {

    private final File arquivo = new File("astronautas.dat");

    public void salvar(List<Astronauta> lista) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(lista);
        } catch (Exception e) {
            System.err.println("Erro ao salvar astronautas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Astronauta> carregar() {
        if (!arquivo.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Astronauta>) in.readObject();
        } catch (Exception e) {
            System.err.println("Erro ao carregar astronautas: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

package br.com.lunarsystems.missoes.persistence.nitrite;

import org.dizitart.no2.objects.ObjectRepository;
import java.util.List;
import br.com.lunarsystems.missoes.model.Astronauta;

public class AstronautaNitriteRepositorio {

    private final ObjectRepository<Astronauta> repo;

    public AstronautaNitriteRepositorio() {
        this.repo = NitriteConfig.getAstronautaRepo();
    }

    public void salvar(Astronauta astronauta) {
        repo.insert(astronauta);
    }

    public void atualizar(Astronauta astronauta) {
        repo.update(astronauta, true);
    }

    public List<Astronauta> listar() {
        return repo.find().toList();
    }

    public void remover(Astronauta astronauta) {
        repo.remove(astronauta);
    }
}

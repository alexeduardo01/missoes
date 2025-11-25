package br.com.lunarsystems.missoes.persistence.nitrite;

import org.dizitart.no2.objects.ObjectRepository;
import java.util.List;
import br.com.lunarsystems.missoes.model.Nave;

public class NaveNitriteRepositorio {

    private final ObjectRepository<Nave> repo;

    public NaveNitriteRepositorio() {
        this.repo = NitriteConfig.getNaveRepo();
    }

    public void salvar(Nave nave) {
        repo.insert(nave);
    }

    public void atualizar(Nave nave) {
        repo.update(nave, true);
    }

    public List<Nave> listar() {
        return repo.find().toList();
    }

    public void remover(Nave nave) {
        repo.remove(nave);
    }
}

package br.com.lunarsystems.missoes.persistence.nitrite;

import org.dizitart.no2.objects.ObjectRepository;
import java.util.List;
import br.com.lunarsystems.missoes.model.Missao;

public class MissaoNitriteRepositorio {

    private final ObjectRepository<Missao> repo;

    public MissaoNitriteRepositorio() {
        this.repo = NitriteConfig.getMissaoRepo();
    }

    public void salvar(Missao missao) {
        repo.insert(missao);
    }

    public void atualizar(Missao missao) {
        repo.update(missao, true);
    }

    public List<Missao> listar() {
        return repo.find().toList();
    }

    public void remover(Missao missao) {
        repo.remove(missao);
    }
}

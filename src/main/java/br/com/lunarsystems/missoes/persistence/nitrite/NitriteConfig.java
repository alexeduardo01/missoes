package br.com.lunarsystems.missoes.persistence.nitrite;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import br.com.lunarsystems.missoes.model.*;

public class NitriteConfig {

    private static Nitrite db;

    static {
        db = Nitrite.builder()
                .compressed()
                .filePath("data/lunar_db.db")
                .openOrCreate("admin", "123");
    }

    public static ObjectRepository<Missao> getMissaoRepo() {
        return db.getRepository(Missao.class);
    }

    public static ObjectRepository<Astronauta> getAstronautaRepo() {
        return db.getRepository(Astronauta.class);
    }

    public static ObjectRepository<Nave> getNaveRepo() {
        return db.getRepository(Nave.class);
    }

    public static void close() {
        if (db != null && !db.isClosed()) {
            db.close();
        }
    }
}

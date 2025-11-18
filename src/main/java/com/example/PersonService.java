package com.example;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;

import java.util.List;
import java.util.UUID;

/**
 * Serviço para gerenciar operações CRUD de Person
 */
public class PersonService {
    private static final String DB_PATH = "pessoas.db";
    private Nitrite db;
    private ObjectRepository<Person> personRepository;

    /**
     * Inicializa o banco de dados e o repositório
     */
    public PersonService() {
        initializeDatabase();
    }

    /**
     * Inicializa o banco de dados Nitrite
     */
    private void initializeDatabase() {
        try {
            db = Nitrite.builder()
                    .filePath(DB_PATH)
                    .openOrCreate();

            personRepository = db.getRepository(Person.class);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao inicializar o banco de dados: " + e.getMessage(), e);
        }
    }

    /**
     * Cria uma nova pessoa
     */
    public Person create(Person person) {
        if (person.getId() == null || person.getId().isEmpty()) {
            person.setId(UUID.randomUUID().toString());
        }
        personRepository.insert(person);
        return person;
    }

    /**
     * Atualiza uma pessoa existente
     */
    public Person update(Person person) {
        if (person.getId() == null || person.getId().isEmpty()) {
            throw new IllegalArgumentException("ID da pessoa não pode ser nulo para atualização");
        }
        personRepository.update(ObjectFilters.eq("id", person.getId()), person);
        return person;
    }

    /**
     * Deleta uma pessoa pelo ID
     */
    public void delete(String id) {
        personRepository.remove(ObjectFilters.eq("id", id));
    }

    /**
     * Busca uma pessoa pelo ID
     */
    public Person findById(String id) {
        return personRepository.find(ObjectFilters.eq("id", id)).firstOrDefault();
    }

    /**
     * Lista todas as pessoas
     */
    public List<Person> findAll() {
        return personRepository.find().toList();
    }

    /**
     * Fecha o banco de dados
     */
    public void close() {
        try {
            if (db != null && !db.isClosed()) {
                db.close();
            }
        } catch (Exception e) {
            System.err.println("Erro ao fechar o banco de dados: " + e.getMessage());
        }
    }

    /**
     * Verifica se o banco está fechado
     */
    public boolean isClosed() {
        return db == null || db.isClosed();
    }
}


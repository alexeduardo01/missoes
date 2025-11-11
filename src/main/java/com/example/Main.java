package com.example;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Classe principal que gerencia a persistência de pessoas no banco Nitrite
 */
public class Main {
    private static final String DB_PATH = "pessoas.db";
    private static Nitrite db;
    private static ObjectRepository<Person> personRepository;

    public static void main(String[] args) {
        initializeDatabase();
        
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("=== Sistema de Gerenciamento de Pessoas ===");
        System.out.println("Banco de dados: " + DB_PATH);

        while (continuar) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Adicionar pessoa");
            System.out.println("2 - Listar todas as pessoas");
            System.out.println("3 - Buscar pessoa por ID");
            System.out.println("4 - Sair");

            System.out.print("Opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    adicionarPessoa(scanner);
                    break;
                case "2":
                    listarPessoas();
                    break;
                case "3":
                    buscarPessoaPorId(scanner);
                    break;
                case "4":
                    continuar = false;
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
        closeDatabase();
    }

    /**
     * Inicializa o banco de dados Nitrite
     */
    private static void initializeDatabase() {
        try {
            db = Nitrite.builder()
                    .filePath(DB_PATH)
                    .openOrCreate();

            personRepository = db.getRepository(Person.class);
            System.out.println("Banco de dados inicializado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao inicializar o banco de dados: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Adiciona uma nova pessoa ao banco de dados
     */
    private static void adicionarPessoa(Scanner scanner) {
        try {
            System.out.println("\n=== Adicionar Nova Pessoa ===");
            
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            
            System.out.print("Idade: ");
            int idade = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Email: ");
            String email = scanner.nextLine();

            String id = UUID.randomUUID().toString();
            Person person = new Person(id, nome, idade, email);
            
            personRepository.insert(person);
            System.out.println("Pessoa adicionada com sucesso! ID: " + id);
        } catch (NumberFormatException e) {
            System.err.println("Erro: Idade deve ser um número válido!");
        } catch (Exception e) {
            System.err.println("Erro ao adicionar pessoa: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Lista todas as pessoas do banco de dados
     */
    private static void listarPessoas() {
        try {
            System.out.println("\n=== Lista de Pessoas ===");
            List<Person> pessoas = personRepository.find().toList();
            
            if (pessoas.isEmpty()) {
                System.out.println("Nenhuma pessoa cadastrada.");
            } else {
                System.out.println("Total de pessoas: " + pessoas.size());
                for (Person person : pessoas) {
                    System.out.println(person);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar pessoas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Busca uma pessoa por ID
     */
    private static void buscarPessoaPorId(Scanner scanner) {
        try {
            System.out.println("\n=== Buscar Pessoa por ID ===");
            System.out.print("Digite o ID: ");
            String id = scanner.nextLine();

            Person person = personRepository.find(ObjectFilters.eq("id", id)).firstOrDefault();
            
            if (person != null) {
                System.out.println("Pessoa encontrada:");
                System.out.println(person);
            } else {
                System.out.println("Pessoa não encontrada com ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar pessoa: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Fecha o banco de dados
     */
    private static void closeDatabase() {
        try {
            if (db != null && !db.isClosed()) {
                db.close();
                System.out.println("Banco de dados fechado com sucesso!");
            }
        } catch (Exception e) {
            System.err.println("Erro ao fechar o banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }
}


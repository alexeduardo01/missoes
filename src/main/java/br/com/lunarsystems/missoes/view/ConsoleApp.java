package br.com.lunarsystems.missoes.view;

import br.com.lunarsystems.missoes.model.Astronauta;
import br.com.lunarsystems.missoes.model.Missao;
import br.com.lunarsystems.missoes.model.Nave;
import br.com.lunarsystems.missoes.persistence.txt.SerializableTxt;
import br.com.lunarsystems.missoes.service.AstronautaService;
import br.com.lunarsystems.missoes.service.MissaoService;
import br.com.lunarsystems.missoes.service.NaveService;

import java.util.List;
import java.util.Scanner;

public class ConsoleApp {

    private final MissaoService missaoService;
    private final AstronautaService astronautaService;
    private final NaveService naveService;
    private final Scanner scanner;

    public ConsoleApp() {
        this.missaoService = new MissaoService(new SerializableTxt<>("missoes.txt"));
        this.astronautaService = new AstronautaService(new SerializableTxt<>("astronautas.txt"));
        this.naveService = new NaveService(new SerializableTxt<>("naves.txt"));
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("=== SISTEMA VIAGEM À LUA ===");

        int opcao;
        do {
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("1. Missões");
            System.out.println("2. Astronautas");
            System.out.println("3. Naves");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1 -> menuMissoes();
                case 2 -> menuAstronautas();
                case 3 -> menuNaves();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    // ------------------ MENU MISSÕES ------------------

    private void menuMissoes() {
        int op;
        do {
            System.out.println("\n>>> MENU MISSÕES <<<");
            System.out.println("1. Listar missões");
            System.out.println("2. Cadastrar missão");
            System.out.println("3. Buscar missão");
            System.out.println("4. Remover missão");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            op = lerInteiro();

            switch (op) {
                case 1 -> listarMissoes();
                case 2 -> cadastrarMissao();
                case 3 -> buscarMissao();
                case 4 -> removerMissao();
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        } while (op != 0);
    }

    private void listarMissoes() {
        List<Missao> lista = missaoService.listarMissoes();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma missão cadastrada.");
            return;
        }
        lista.forEach(System.out::println);
    }

    private void cadastrarMissao() {
        System.out.print("Nome da missão: ");
        String nome = scanner.nextLine();
        System.out.print("Destino: ");
        String destino = scanner.nextLine();
        System.out.print("Capacidade da nave: ");
        int cap = lerInteiro();

        missaoService.cadastrarMissao(nome, destino, cap);
        System.out.println("Missão cadastrada!");
    }

    private void buscarMissao() {
        System.out.print("ID da missão: ");
        String id = scanner.nextLine();

        Missao m = missaoService.buscarMissao(id);
        if (m == null) {
            System.out.println("Missão não encontrada!");
            return;
        }
        System.out.println(m);
    }

    private void removerMissao() {
        System.out.print("ID da missão: ");
        String id = scanner.nextLine();

        missaoService.removerMissao(id);
        System.out.println("Missão removida (se existia).");
    }

    // ------------------ MENU ASTRONAUTAS ------------------

    private void menuAstronautas() {
        int op;
        do {
            System.out.println("\n>>> MENU ASTRONAUTAS <<<");
            System.out.println("1. Listar astronautas");
            System.out.println("2. Cadastrar astronauta");
            System.out.println("3. Buscar astronauta");
            System.out.println("4. Remover astronauta");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            op = lerInteiro();

            switch (op) {
                case 1 -> listarAstronautas();
                case 2 -> cadastrarAstronauta();
                case 3 -> buscarAstronauta();
                case 4 -> removerAstronauta();
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        } while (op != 0);
    }

    private void listarAstronautas() {
        List<Astronauta> lista = astronautaService.listarAstronautas();
        if (lista.isEmpty()) {
            System.out.println("Nenhum astronauta cadastrado.");
            return;
        }
        lista.forEach(System.out::println);
    }

    private void cadastrarAstronauta() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Especialidade: ");
        String esp = scanner.nextLine();

        astronautaService.cadastrarAstronauta(nome, esp);
        System.out.println("Astronauta cadastrado!");
    }

    private void buscarAstronauta() {
        System.out.print("ID do astronauta: ");
        String id = scanner.nextLine();

        Astronauta a = astronautaService.buscarAstronauta(Integer.parseInt(id));
        if (a == null) {
            System.out.println("Astronauta não encontrado.");
            return;
        }
        System.out.println(a);
    }

    private void removerAstronauta() {
        System.out.print("ID do astronauta: ");
        String id = scanner.nextLine();

        astronautaService.removerAstronauta(Integer.parseInt(id));
        System.out.println("Astronauta removido (se existia).");
    }

    // ------------------ MENU NAVES ------------------

    private void menuNaves() {
        int op;
        do {
            System.out.println("\n>>> MENU NAVES <<<");
            System.out.println("1. Listar naves");
            System.out.println("2. Cadastrar nave");
            System.out.println("3. Buscar nave");
            System.out.println("4. Remover nave");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            op = lerInteiro();

            switch (op) {
                case 1 -> listarNaves();
                case 2 -> cadastrarNave();
                case 3 -> buscarNave();
                case 4 -> removerNave();
                case 0 -> {}
                default -> System.out.println("Opção inválida!");
            }
        } while (op != 0);
    }

    private void listarNaves() {
        List<Nave> lista = naveService.listarNaves();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma nave cadastrada.");
            return;
        }
        lista.forEach(System.out::println);
    }

    private void cadastrarNave() {
        System.out.print("Nome da nave: ");
        String nome = scanner.nextLine();
        System.out.print("Capacidade: ");
        int cap = lerInteiro();

        naveService.cadastrarNave(nome, cap);
        System.out.println("Nave cadastrada!");
    }

    private void buscarNave() {
        System.out.print("ID da nave: ");
        String id = scanner.nextLine();

        Nave nave = naveService.buscarNave(id);
        if (nave == null) {
            System.out.println("Nave não encontrada.");
            return;
        }
        System.out.println(nave);
    }

    private void removerNave() {
        System.out.print("ID da nave: ");
        String id = scanner.nextLine();

        naveService.removerNave(id);
        System.out.println("Nave removida (se existia).");
    }

    // ------------------ UTIL ------------------

    private int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        new ConsoleApp().iniciar();
    }
}

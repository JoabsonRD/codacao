import java.util.Scanner;

/**
 * Sistema de Gerenciamento de Peças e Serviços – MecaniQA
 * 
 * Centro Universitário de Excelência – Sistemas de Informação
 * Autor: Joabson RodriguesbDos Santos
 * Itabuna, 2026
 * Estrutura dos dados:
 *   - Classe Peça
 *   - Classe Serviço
 *
 * Armazenamento:
 *   - Array estático com capacidade para 100 peças
 *   - Array estático com capacidade para 50 serviços
 *   - Dados mantidos temporariamente em memória (não há persistência em arquivo/BD)
 *
 * Funcionalidades CRUD:
 *   - Cadastrar, listar, atualizar e remover peças e serviços
 *
 * Busca:
 *   - Busca linear pelo código
 */
public class MecaniQA {

    private static final int CAPACIDADE_PECAS = 100;
    private static final int CAPACIDADE_SERVICOS = 50;

    private static Peca[] pecas = new Peca[CAPACIDADE_PECAS];
    private static int totalPecas = 0;

    private static Servico[] servicos = new Servico[CAPACIDADE_SERVICOS];
    private static int totalServicos = 0;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcaoMenu(0, 2);

            switch (opcao) {
                case 1:
                    menuPecas();
                    break;
                case 2:
                    menuServicos();
                    break;
                case 0:
                    System.out.println("\nEncerrando o sistema MecaniQA. Até logo!");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n==================================================");
        System.out.println(" Sistema de Gerenciamento MecaniQA");
        System.out.println("==================================================");
        System.out.println("1 - Gerenciar Peças");
        System.out.println("2 - Gerenciar Serviços");
        System.out.println("0 - Sair");
        System.out.println("==================================================");
    }

    private static void menuPecas() {
        int opcao;
        do {
            System.out.println("\n---------- Gerenciar Peças ----------");
            System.out.println("1 - Cadastrar peça");
            System.out.println("2 - Listar peças");
            System.out.println("3 - Atualizar peça");
            System.out.println("4 - Remover peça");
            System.out.println("5 - Buscar peça por código");
            System.out.println("0 - Voltar");
            System.out.println("--------------------------------------");
            opcao = lerOpcaoMenu(0, 5);

            switch (opcao) {
                case 1:
                    cadastrarPeca();
                    break;
                case 2:
                    listarPecas();
                    break;
                case 3:
                    atualizarPeca();
                    break;
                case 4:
                    removerPeca();
                    break;
                case 5:
                    buscarPecaPorCodigoMenu();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
            }
        } while (opcao != 0);
    }

    private static void menuServicos() {
        int opcao;
        do {
            System.out.println("\n---------- Gerenciar Serviços ----------");
            System.out.println("1 - Cadastrar serviço");
            System.out.println("2 - Listar serviços");
            System.out.println("3 - Atualizar serviço");
            System.out.println("4 - Remover serviço");
            System.out.println("5 - Buscar serviço por código");
            System.out.println("0 - Voltar");
            System.out.println("-----------------------------------------");
            opcao = lerOpcaoMenu(0, 5);

            switch (opcao) {
                case 1:
                    cadastrarServico();
                    break;
                case 2:
                    listarServicos();
                    break;
                case 3:
                    atualizarServico();
                    break;
                case 4:
                    removerServico();
                    break;
                case 5:
                    buscarServicoPorCodigoMenu();
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
            }
        } while (opcao != 0);
    }

    private static void cadastrarPeca() {
        System.out.println("\n--- Cadastrar Peça ---");

        if (totalPecas >= CAPACIDADE_PECAS) {
            System.out.println("Erro: capacidade máxima de " + CAPACIDADE_PECAS
                    + " peças atingida. Não é possível cadastrar novas peças.");
            return;
        }

        int codigo = lerCodigoNovo("peça");
        if (buscarPecaPorCodigo(codigo) != null) {
            System.out.println("Erro: já existe uma peça cadastrada com o código " + codigo + ".");
            return;
        }

        String nome = lerTextoNaoVazio("Nome da peça: ");
        String fabricante = lerTextoNaoVazio("Fabricante: ");
        int quantidadeEstoque = lerInteiroNaoNegativo("Quantidade em estoque: ");
        double precoCusto = lerDoubleNaoNegativo("Preço de custo (R$): ");
        double precoVenda = lerDoubleNaoNegativo("Preço de venda (R$): ");

        Peca novaPeca = new Peca(codigo, nome, fabricante, quantidadeEstoque, precoCusto, precoVenda);
        pecas[totalPecas] = novaPeca;
        totalPecas++;

        System.out.println("Peça cadastrada com sucesso!");
    }

    private static void listarPecas() {
        System.out.println("\n--- Lista de Peças ---");
        if (totalPecas == 0) {
            System.out.println("Nenhuma peça cadastrada.");
            return;
        }
        for (int i = 0; i < totalPecas; i++) {
            System.out.println(pecas[i]);
        }
        System.out.println("Total de peças cadastradas: " + totalPecas + "/" + CAPACIDADE_PECAS);
    }

    private static void atualizarPeca() {
        System.out.println("\n--- Atualizar Peça ---");
        int codigo = lerInteiro("Informe o código da peça a ser atualizada: ");
        Peca peca = buscarPecaPorCodigo(codigo);

        if (peca == null) {
            System.out.println("Erro: nenhuma peça encontrada com o código " + codigo + ".");
            return;
        }

        System.out.println("Peça encontrada: " + peca);
        System.out.println("Informe os novos dados da peça:");

        String nome = lerTextoNaoVazio("Novo nome: ");
        String fabricante = lerTextoNaoVazio("Novo fabricante: ");
        int quantidadeEstoque = lerInteiroNaoNegativo("Nova quantidade em estoque: ");
        double precoCusto = lerDoubleNaoNegativo("Novo preço de custo (R$): ");
        double precoVenda = lerDoubleNaoNegativo("Novo preço de venda (R$): ");

        peca.setNome(nome);
        peca.setFabricante(fabricante);
        peca.setQuantidadeEstoque(quantidadeEstoque);
        peca.setPrecoCusto(precoCusto);
        peca.setPrecoVenda(precoVenda);

        System.out.println("Peça atualizada com sucesso!");
    }

    private static void removerPeca() {
        System.out.println("\n--- Remover Peça ---");
        int codigo = lerInteiro("Informe o código da peça a ser removida: ");
        int indice = indiceDaPeca(codigo);

        if (indice == -1) {
            System.out.println("Erro: nenhuma peça encontrada com o código " + codigo + ".");
            return;
        }

        System.out.println("Peça removida: " + pecas[indice]);

        for (int i = indice; i < totalPecas - 1; i++) {
            pecas[i] = pecas[i + 1];
        }
        pecas[totalPecas - 1] = null;
        totalPecas--;

        System.out.println("Peça removida com sucesso e array reorganizado!");
    }

    private static void buscarPecaPorCodigoMenu() {
        System.out.println("\n--- Buscar Peça por Código ---");
        int codigo = lerInteiro("Informe o código da peça: ");
        Peca peca = buscarPecaPorCodigo(codigo);

        if (peca == null) {
            System.out.println("Nenhuma peça encontrada com o código " + codigo + ".");
        } else {
            System.out.println("Peça encontrada: " + peca);
        }
    }

    private static Peca buscarPecaPorCodigo(int codigo) {
        for (int i = 0; i < totalPecas; i++) {
            if (pecas[i].getCodigo() == codigo) {
                return pecas[i];
            }
        }
        return null;
    }

    private static int indiceDaPeca(int codigo) {
        for (int i = 0; i < totalPecas; i++) {
            if (pecas[i].getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }

    private static void cadastrarServico() {
        System.out.println("\n--- Cadastrar Serviço ---");

        if (totalServicos >= CAPACIDADE_SERVICOS) {
            System.out.println("Erro: capacidade máxima de " + CAPACIDADE_SERVICOS
                    + " serviços atingida. Não é possível cadastrar novos serviços.");
            return;
        }

        int codigo = lerCodigoNovo("serviço");
        if (buscarServicoPorCodigo(codigo) != null) {
            System.out.println("Erro: já existe um serviço cadastrado com o código " + codigo + ".");
            return;
        }

        String nome = lerTextoNaoVazio("Nome do serviço: ");
        String descricao = lerTextoNaoVazio("Descrição: ");
        double tempoEstimado = lerDoubleNaoNegativo("Tempo estimado (em horas): ");
        double valorMaoDeObra = lerDoubleNaoNegativo("Valor da mão de obra (R$): ");

        Servico novoServico = new Servico(codigo, nome, descricao, tempoEstimado, valorMaoDeObra);
        servicos[totalServicos] = novoServico;
        totalServicos++;

        System.out.println("Serviço cadastrado com sucesso!");
    }

    private static void listarServicos() {
        System.out.println("\n--- Lista de Serviços ---");
        if (totalServicos == 0) {
            System.out.println("Nenhum serviço cadastrado.");
            return;
        }
        for (int i = 0; i < totalServicos; i++) {
            System.out.println(servicos[i]);
        }
        System.out.println("Total de serviços cadastrados: " + totalServicos + "/" + CAPACIDADE_SERVICOS);
    }

    private static void atualizarServico() {
        System.out.println("\n--- Atualizar Serviço ---");
        int codigo = lerInteiro("Informe o código do serviço a ser atualizado: ");
        Servico servico = buscarServicoPorCodigo(codigo);

        if (servico == null) {
            System.out.println("Erro: nenhum serviço encontrado com o código " + codigo + ".");
            return;
        }

        System.out.println("Serviço encontrado: " + servico);
        System.out.println("Informe os novos dados do serviço:");

        String nome = lerTextoNaoVazio("Novo nome: ");
        String descricao = lerTextoNaoVazio("Nova descrição: ");
        double tempoEstimado = lerDoubleNaoNegativo("Novo tempo estimado (em horas): ");
        double valorMaoDeObra = lerDoubleNaoNegativo("Novo valor da mão de obra (R$): ");

        servico.setNome(nome);
        servico.setDescricao(descricao);
        servico.setTempoEstimado(tempoEstimado);
        servico.setValorMaoDeObra(valorMaoDeObra);

        System.out.println("Serviço atualizado com sucesso!");
    }

    private static void removerServico() {
        System.out.println("\n--- Remover Serviço ---");
        int codigo = lerInteiro("Informe o código do serviço a ser removido: ");
        int indice = indiceDoServico(codigo);

        if (indice == -1) {
            System.out.println("Erro: nenhum serviço encontrado com o código " + codigo + ".");
            return;
        }

        System.out.println("Serviço removido: " + servicos[indice]);

        for (int i = indice; i < totalServicos - 1; i++) {
            servicos[i] = servicos[i + 1];
        }
        servicos[totalServicos - 1] = null;
        totalServicos--;

        System.out.println("Serviço removido com sucesso e array reorganizado!");
    }

    private static void buscarServicoPorCodigoMenu() {
        System.out.println("\n--- Buscar Serviço por Código ---");
        int codigo = lerInteiro("Informe o código do serviço: ");
        Servico servico = buscarServicoPorCodigo(codigo);

        if (servico == null) {
            System.out.println("Nenhum serviço encontrado com o código " + codigo + ".");
        } else {
            System.out.println("Serviço encontrado: " + servico);
        }
    }

    private static Servico buscarServicoPorCodigo(int codigo) {
        for (int i = 0; i < totalServicos; i++) {
            if (servicos[i].getCodigo() == codigo) {
                return servicos[i];
            }
        }
        return null;
    }

    private static int indiceDoServico(int codigo) {
        for (int i = 0; i < totalServicos; i++) {
            if (servicos[i].getCodigo() == codigo) {
                return i;
            }
        }
        return -1;
    }

    private static int lerOpcaoMenu(int min, int max) {
        int opcao;
        while (true) {
            System.out.print("Escolha uma opção: ");
            String entrada = scanner.nextLine().trim();
            try {
                opcao = Integer.parseInt(entrada);
                if (opcao < min || opcao > max) {
                    System.out.println("Opção inválida. Digite um número entre "
                            + min + " e " + max + ".");
                    continue;
                }
                return opcao;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
            }
        }
    }

    /** Lê um código para um novo cadastro, exigindo um valor inteiro positivo. */
    private static int lerCodigoNovo(String entidade) {
        return lerInteiroPositivo("Código da " + entidade + ": ");
    }

    /** Lê um texto genérico, não aceitando valores vazios. */
    private static String lerTextoNaoVazio(String mensagem) {
        String texto;
        while (true) {
            System.out.print(mensagem);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("Erro: o campo não pode ficar vazio. Tente novamente.");
                continue;
            }
            return texto;
        }
    }

    /** Lê um número inteiro qualquer, validando o formato. */
    private static int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Erro: valor inválido. Digite um número inteiro.");
            }
        }
    }

    private static int lerInteiroPositivo(String mensagem) {
        while (true) {
            int valor = lerInteiro(mensagem);
            if (valor <= 0) {
                System.out.println("Erro: o valor deve ser um número inteiro positivo.");
                continue;
            }
            return valor;
        }
    }

    private static int lerInteiroNaoNegativo(String mensagem) {
        while (true) {
            int valor = lerInteiro(mensagem);
            if (valor < 0) {
                System.out.println("Erro: o valor não pode ser negativo.");
                continue;
            }
            return valor;
        }
    }

    private static double lerDoubleNaoNegativo(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim().replace(",", ".");
            try {
                double valor = Double.parseDouble(entrada);
                if (valor < 0) {
                    System.out.println("Erro: o valor não pode ser negativo.");
                    continue;
                }
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Erro: valor inválido. Digite um número (ex: 10.50).");
            }
        }
    }
}
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Classe que gere o jogo, recebe as perguntas, exibi as perguntas e outras funções auxiliares
 */
public class jogo {
    private List<pergunta> perguntas;
    private int pontuacao;
    private String nomeJogador;
    private String[] jogadores = new String[]{"Ronaldo", "Pelé", "Zidane", "Messi", "Iniesta", "Benzema", "Neymar", "Mbappé", "Modric", "De Bruyne"};
    private String[] numeros = new String[]{"Camisola 7 do Real Madrid", "Camisola 10", "Camisola 8", "Camisola 30 do PSG", "Camisola 6 do Barcelona", "Camisola 9 do Real Madrid", "Camisola 10 do PSG", "Camisola 7 do PSG", "Camisola 10 da Croacia", "Camisola 10 do Man City"};

    /**
     * Construtor da classe jogo
     * @param perguntas Lista de perguntas
     * @param nomeJogador Nome do jogador
     */
    public jogo(List<pergunta> perguntas, String nomeJogador) {
        this.perguntas = perguntas;
        this.pontuacao = 0;
        this.nomeJogador = nomeJogador;
    }

    /**
     * Devolve a lista de perguntas
     * @return Lista de perguntas
     */
    public void jogar() {
        // Cria um scanner para ler o input do user
        Scanner scanner = new Scanner(System.in);
        int perguntasRestantes = 0;

        // While para dar as 5 perguntas por questionário
        while (perguntasRestantes < 5) {
            // Devolve uma pergunta random
            pergunta pergunta = getRandomPergunta();

            // Chama a função exibirPergunta, e passa-lhe os parâmetros pergunta e perguntaRestantes
            int numOps = exibirPergunta(pergunta, perguntasRestantes);

            // Recebe o input do user e verifica se está correto
            String userChoice = getUserChoice(scanner, pergunta.getOpcoes(), numOps);
            if (pergunta.getRespostaCorreta().equals(userChoice)) {
                System.out.println("Resposta correta!");
                //Atualiza a pontuação
                pontuacao += pergunta.getPontuacao();
            } else {
                System.out.println("Resposta errada. A resposta correta era: " + pergunta.getRespostaCorreta());
            }

            // Espera por um enter antes de fornecer a proxima pergunta
            Scanner sc = new Scanner(System.in);
            System.out.println("Press enter to continue");
            sc.nextLine();
            
            // Atualiza a pontuação
            pontuacao += pergunta.getPontuacao();
            // Atualiza a quantidade de perguntas restantes
            perguntasRestantes++;
        }
        
        // FIm do jogo
        System.out.println("Jogo concluído! Pontuação final: " + pontuacao);
    }

    /**
     * Devolve uma pergunta random
     * @return pergunta
     */
    private pergunta getRandomPergunta() {
        // Baralha a lista de perguntas para obter uma pergunta aleatória
        Collections.shuffle(perguntas);
        return perguntas.get(0);
    }

    /**
     * Exibe uma pergunta
     * @param pergunta Pergunta a ser exibida
     * @param numPergunta Número da pergunta
     * @return Número de opções
     */
    private int exibirPergunta(pergunta pergunta, int numPergunta) {
        // Dá print da pergunta
        System.out.println(pergunta.getTexto());

        // Exibe as opções com base no índice da pergunta
        String[] opcoes = new String[0];

        //Artes
        if (pergunta.getTipo().equals("Artes")) {
            if (numPergunta < 3) {
            // Mostra 3 opcoes se for das primeiras 3 perguntas
            opcoes = getRandomOps(pergunta.getOpcoes(), 3, pergunta);
            
            } else {
            // Mostra entre 5 a 10 opcoes de resposta
            int numOptions = (int) (Math.random() * 6) + 5;
            opcoes = getRandomOps(pergunta.getOpcoes(), numOptions, pergunta);
            
            }
        }

        //Futebol
        if (pergunta.getTipo().equals("Desporto")) {
            if (numPergunta < 3) {
                // Casos fáceis vem com o nome do jogador
                int numOptions = pergunta.getOpcoes().length;
                opcoes = getRandomOps(pergunta.getOpcoes(), numOptions, pergunta);
            } else {
                // Casos dificeis em vez de o nome a respostas são com o numero da camisola do jogador
                int numOptions = pergunta.getOpcoes().length;
                opcoes = getRandomOps(pergunta.getOpcoes(), numOptions, pergunta);
        
                // Verificar se nem pergunta.getTexto() nem pergunta.getOpcoes() contêm itens de numeros
                boolean replaceNames = true;
                for (String numero : numeros) {
                    if (pergunta.getTexto().contains(numero) || containsAny(pergunta.getOpcoes(), numeros)) {
                        replaceNames = false;
                        break;
                    }
                }
        
                // Substitui nomes pelos itens correspondentes de numeros se a condição for verdade
                if (replaceNames) {
                    for (int i = 0; i < jogadores.length; i++) {
                        pergunta.setTexto(pergunta.getTexto().replace(jogadores[i], numeros[i]));
                        for (int j = 0; j < opcoes.length; j++) {
                            opcoes[j] = opcoes[j].replace(jogadores[i], numeros[i]);
                        }
                    }
                }
            }
        }

        //Ciencias
        if (pergunta.getTipo().equals("Ciencias")) {
            if (numPergunta < 3) {
            // Mostra 3 opcoes se for das primeiras 3 perguntas
            opcoes = getRandomOps(pergunta.getOpcoes(), 3, pergunta);
            
            } else {
            // Mostra entre 5 a 10 opcoes de resposta
            int numOptions = (int) (Math.random() * 6) + 5;
            opcoes = getRandomOps(pergunta.getOpcoes(), numOptions, pergunta);
            
            }
        }

        //Mostra as opcoes
            for (int i = 0; i < opcoes.length; i++) {
                System.out.println((i + 1) + ". " + opcoes[i]);
            }
            return opcoes.length;

    }

    /**
     * Devolve um array de opções random
     * @param opcoes Opções
     * @param numOps Número de opções
     * @param pergunta Pergunta
     * @return Array de opções random
     */
    private String[] getRandomOps(String[] opcoes, int numOps, pergunta pergunta) {
        // Reduzir o tamanho do array para 3
        String[] ops = Arrays.copyOf(opcoes, numOps);
        //verifica se ops contem a resposta correta
        if (Arrays.asList(ops).contains(pergunta.getRespostaCorreta())) {
           return ops;
        }

        // Gera um número aleatório entre 0 e a variável numOps
        int randomIndex = (int) (Math.random() * numOps);
        // Substituir o índice aleatório pela resposta correta
        ops[randomIndex] = pergunta.getRespostaCorreta();
            
        return ops;
    }

    /**
     * Devolve a escolha do utilizador
     * @param scanner Scanner
     * @param opcoes Opções
     * @param numOps Número de opções
     * @return Escolha do utilizador
     */
    private String getUserChoice(Scanner scanner, String[] opcoes, int numOps) {
        String userChoice;

        // Verifica/Valida se o input do user é aceitável
        do {
            System.out.print("Escolha sua resposta (1-" + numOps + "): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next();
            }
            int choice = scanner.nextInt();
            if (choice >= 1 && choice <= opcoes.length) {
                userChoice = opcoes[choice - 1];
            } else {
                System.out.println("Escolha inválida. Por favor, escolha uma opção válida.");
                userChoice = null;
            }
        } while (userChoice == null);

        return userChoice;
    }

    /**
     * Verifica se um array contém um valor
     * @param array Array
     * @param value Valor
     * @return True se o array contiver o valor, false caso contrário
     */
    public boolean contains(int[] array, int value) {
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se um array contém um valor
     * @param array Array
     * @param value Valor
     * @return True se o array contiver o valor, false caso contrário
     */
    private boolean containsAny(String[] array, String[] elements) {
        for (String element : elements) {
            for (String item : array) {
                if (item.contains(element)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Devolve a pontuação
     * @return Pontuação
     */
    public int getPontuacao() {
        return pontuacao;
    }

    /**
     * Devolve o nome do jogador
     * @return Nome do jogador
     */
    public String getNomeJogador() {
        return nomeJogador;
    }
}

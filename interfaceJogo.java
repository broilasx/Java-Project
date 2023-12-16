import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que gere a leitura, a escrita nos ficheiros de objetos e exibe o conteudo lá presente
 */
public class interfaceJogo {
    private static gerenciadorJogos gerenciadorJogos;

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        // Carregar as perguntas do arquivo de texto
        List<pergunta> perguntas = carregarPerguntas("C:\\Users\\Tester\\Desktop\\POAO\\projetoFinal\\testes\\perguntas.txt");

        // Iniciar o gerenciador de jogos
        gerenciadorJogos = new gerenciadorJogos();
        gerenciadorJogos.carregarJogos();

        // Iniciar a interface gráfica
        SwingUtilities.invokeLater(() -> exibirMenuPrincipal(perguntas));
    }

    /**
     * Carrega as perguntas de um arquivo de texto.
     *
     * @param arquivo O caminho do arquivo de perguntas.
     * @return Uma lista de objetos Pergunta.
     */
    private static List<pergunta> carregarPerguntas(String arquivo) {
        List<pergunta> perguntas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Tester\\Desktop\\POAO\\projetoFinal\\testes\\perguntas.txt"))) {
            String line;
    
            while ((line = br.readLine()) != null) {
                String[] partes = line.split("/");
    
                if (partes.length == 4) {
                    String tipo = partes[0].trim();
                    String texto = partes[1].trim();
                    String[] opcoes = partes[2].split(";");
                    String solucao = partes[3].trim();
    
                    if ("Artes".equals(tipo)) {
                        artes perguntaArtes = new artes(tipo, texto, 5, opcoes, solucao, 10);
                        perguntas.add(perguntaArtes);
                    } else if ("Ciencias".equals(tipo)) {
                        ciencias perguntaCiencias = new ciencias(tipo, texto, 5, opcoes, solucao, 5);
                        perguntas.add(perguntaCiencias);
                    }
                }
                if (partes.length == 5) {
                    String tipo = partes[0].trim();
                    String subtipo = partes[1].trim();
                    String texto = partes[2].trim();
                    String[] opcoes = partes[3].split(";");
                    String solucao = partes[4].trim();
                    if("Desporto".equals(tipo)){
                        desporto perguntaDesporto = new desporto(tipo, subtipo, texto, 5, opcoes, solucao, 3);
                        perguntas.add(perguntaDesporto);

                    }
                }
            }
    
            //System.out.println(perguntas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return perguntas;
    }

     /**
     * Exibe o menu principal da interface gráfica.
     *
     * @param perguntas Lista de perguntas carregadas.
     */
    private static void exibirMenuPrincipal(List<pergunta> perguntas) {
        JFrame frame = new JFrame("POO Trivia Game");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(400, 200);
    
        JPanel panel = new JPanel();
        JButton novoJogoButton = new JButton("Novo Jogo");
        JButton top3Button = new JButton("Top 3");
    
        panel.add(novoJogoButton);
        panel.add(top3Button);
    
        novoJogoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Solicitar o nome do jogador
                String nomeJogador = JOptionPane.showInputDialog(frame, "Digite seu nome:");
                frame.dispose(); // Fechar o menu principal ao iniciar um novo jogo
                iniciarNovoJogo(perguntas, nomeJogador);
            }
        });
    
        top3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exibirTop3();
            }
        });
    
        frame.add(panel);
        frame.setVisible(true);
    }


    private static void iniciarNovoJogo(List<pergunta> perguntas, String nomeJogador) {
        jogo jogo = new jogo(perguntas, nomeJogador);
        jogo.jogar();
        gerenciadorJogos.salvarJogo(jogo);

        // Exibir pontuação do jogo
        JOptionPane.showMessageDialog(null, "Pontuação do jogo: " + jogo.getPontuacao(), "Fim do Jogo", JOptionPane.INFORMATION_MESSAGE);

        exibirMenuPrincipal(perguntas);
        
    }

    private static void exibirTop3() {
        gerenciadorJogos.exibirTop3();
    }
}
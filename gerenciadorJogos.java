import java.util.List;
import java.util.stream.Collectors;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Classe que gere a leitura, a escrita nos ficheiros de objetos e exibe o conteudo lá presente
 */
public class gerenciadorJogos {
    private List<jogo> jogos;
    private static final String DIRETORIO_JOGOS = "jogos/";
    private List<List<Object>> jogados = new ArrayList<>();

    /**
     * Carrega os jogos do ficheiro de objetos
     * @return Lista de jogos
     */
    public List<jogo> carregarJogos() {
        this.jogos = new ArrayList<>();

        //Obtem a lista de arquivos no diretório
        File diretorio = new File(DIRETORIO_JOGOS);
        if (diretorio.exists() && diretorio.isDirectory()) {
            File[] arquivos = diretorio.listFiles();

            //Se for diferente de null, ou seja contem informação
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    if (arquivo.isFile() && arquivo.getName().endsWith(".dat")) {
                        //Lê a informação presente no ficheiro
                        jogo jogo = lerArquivo(DIRETORIO_JOGOS + arquivo.getName());
                        //Se o jogo for diferente de null adiciona-o à lista jogos
                        if (jogo != null ) {
                            jogos.add(jogo);
                        }
                    }
                }
            }
        }

        //Ordene a lista de jogos por pontuação(+ -> -)
        Collections.sort(jogos, (jogo1, jogo2) -> Integer.compare(jogo2.getPontuacao(), jogo1.getPontuacao()));

        //Devolve lista de jogos ordenada
        return jogos;
    }

    /**
     * Salva um jogo no ficheiro de objetos
     * @param jogo Jogo a ser salvo
     */
    public void salvarJogo(jogo jogo) {
        try {
            // Cria o diretório se não existir
            File diretorio = new File(DIRETORIO_JOGOS);
            if (!diretorio.exists()) {
                diretorio.mkdirs();
            }

            //Gera um nome de arquivo único com base na data/hora e no nome do jogador
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
            String dataHora = dateFormat.format(new Date());
            String nomeJogador = jogo.getNomeJogador().replaceAll("\\s", "");
            String nomeArquivo = "pootrivia_jogo_" + dataHora + "_" + nomeJogador + ".dat";

            //Cria o arquivo
            File arquivo = new File(DIRETORIO_JOGOS + nomeArquivo);

            // Escreve no arquivo
            try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(arquivo))) {
                // Salve as informações no arquivo
                outputStream.writeObject("Data e hora: " + dataHora);
                outputStream.writeObject("Nome do jogador: " + jogo.getNomeJogador());
                outputStream.writeObject("Pontuação: " + jogo.getPontuacao());
                System.out.println("Jogo salvo com sucesso: " + nomeArquivo);
            }

            // Adiciona o jogo à lista de jogos
            jogos.add(jogo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exibe o top 3 de jogos
     * @return Top 3 de jogos
     */
    public List<List<Object>> exibirTop3() {
        // Ordene a lista "jogados" com base nos valores de "Pontuacao" em ordem decrescente
        List<List<Object>> sortedJogados = jogados.stream()
                .sorted(Comparator.comparing(list -> ((String) list.get(2)).replaceAll("[^0-9]", ""), Comparator.reverseOrder()))
                .collect(Collectors.toList());

        //Adiciona à lista top3List os 3 jogos com maior pontuação
        List<List<Object>> top3List = sortedJogados.stream()
                .limit(3)
                .collect(Collectors.toList());

        //Dá print dos top3 jogos
        System.out.println("Top 3 games:");
        for (List<Object> jogoInfo : top3List) {
            System.out.println(jogoInfo);
        }

        return top3List;
    }

    /**
     * Lê um arquivo de objetos
     * @param caminhoArquivo Caminho do arquivo a ser lido
     * @return jogo lido
     */
    private jogo lerArquivo(String caminhoArquivo) {
        //Cria o arquivo
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            System.out.println("Conteúdo do arquivo " + caminhoArquivo + ":");
            
            List<Object> jogoInfo = new ArrayList<>();
            while (true) {
                try {
                    //Lê o objeto
                    Object obj = inputStream.readObject();
                    jogoInfo.add(obj);
                } catch (EOFException e) {
                    break; 
                }
            }

            //Adiciona a jogados a informação dos jogos
            jogados.add(jogoInfo);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
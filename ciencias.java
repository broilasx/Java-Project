/** Representa uma pergunta sobre ciencias
 * @author Rui Ribeiro
 * @author Bruno Almeida
 * 
*/
public class ciencias extends pergunta {
    private int major;
    /**
     * Construtor da classe ciencias
     * @param tipo Tipo de uma pergunta
     * @param texto  Texto de uma pergunta
     * @param pont  Pontuação de uma pergunta
     * @param opcoes  Opções de resposta de uma pergunta
     * @param certaCiencias Opção de resposta correta de uma pergunta
     * @param major Majoração de uma pergunta sobre ciencias
     */
    public ciencias(String tipo, String texto, int pont, String[] opcoes, String certaCiencias, int major) {
        super(tipo, texto, pont, opcoes, certaCiencias);
        this.major = major;
    }

    /**
     * Devolve a majoração de uma pergunta sobre desporto
     * @return Majoração de uma pergunta sobre desporto
     */
    public int getPontuacao(int pont){
        int pontFinal = major + pont;
        return pontFinal;
    }
}
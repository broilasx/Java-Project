/** Representa uma pergunta sobre artes
 * @author Rui Ribeiro
 * @author Bruno Almeida
 * 
*/
public class artes extends pergunta {
    private int major;
    /**
     * Construtor da classe artes
     * @param tipo Tipo de uma pergunta
     * @param texto  Texto de uma pergunta
     * @param pont  Pontuação de uma pergunta
     * @param opcoes  Opções de resposta de uma pergunta
     * @param opCorreta Opção de resposta correta de uma pergunta
     * @param major Majoração de uma pergunta sobre artes
     */
    public artes(String tipo, String texto, int pont,  String[] opcoes, String opCorreta, int major) {
        super(tipo,texto,pont,opcoes,opCorreta);
        this.major = major;
    }

    /**
     * Devolve a majoração de uma pergunta sobre artes
     * @return Majoração de uma pergunta sobre artes
     */
    public int getPontuacao(int pont){
        int pontFinal = major * pont;
        return pontFinal;
    }
}
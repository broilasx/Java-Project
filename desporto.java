/** Representa uma pergunta sobre desporto
 * @author Rui Ribeiro
 * @author Bruno Almeida
 * 
*/
public class desporto extends pergunta{
    private String subtipo;
    private int major;
    /**
     * Construtor da classe desporto
     * @param tipo Tipo de uma pergunta
     * @param subtipo subtipo de uma pergunta de desporto
     * @param texto  Texto de uma pergunta
     * @param pont  Pontuação de uma pergunta
     * @param opcoes  Opções de resposta de uma pergunta
     * @param opCorreta Opção de resposta correta de uma pergunta
     * @param major Majoração de uma pergunta sobre desporto
     */
    public desporto(String tipo, String subtipo, String texto, int pont, String[] opcoes, String opCorreta, int major){
        super(tipo, texto, major, opcoes, opCorreta);
        this.subtipo = subtipo;
        this.major = major;
    }

    /**
     * Devolve o subtipo de uma pergunta sobre desporto
     * @return Subtipo de uma pergunta sobre desporto
     */
    public String subtipo() {
        return this.subtipo;
    }

    /**
     * Devolve a majoração de uma pergunta sobre desporto
     * @return Majoração de uma pergunta sobre desporto
     */
    public int getPontuacao(int pont, String subTipo){
        int pontFinal = 0;
        if (subTipo.equals("Futebol")) {
            pontFinal = major + pont + 1;
        }
        if (subTipo.equals("Natacao")) {
            pontFinal = major + pont + 10;
        }
        if (subTipo.equals("Ski")) {
            pontFinal = (major * 2) + pont;
        }
        return pontFinal;
    }
}

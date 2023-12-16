/** Representa uma pergunta
 * @author Rui Ribeiro
 * @author Bruno Almeida
 * 
*/
public class pergunta {
    private String tipo;
    private String texto;
    private int pont;
    private String[] options;
    private String opCorreta;

    /**
     * Construtor da classe pergunta
     * @param tipo Tipo de uma pergunta
     * @param texto  Texto de uma pergunta
     * @param pont  Pontuação de uma pergunta
     * @param options  Opções de resposta de uma pergunta
     * @param opCorreta Opção de resposta correta de uma pergunta
     */
    public pergunta(String tipo, String texto, int pont, String[] options, String opCorreta) {
        this.tipo = tipo;
        this.texto = texto;
        this.pont = 5;
        this.options = options;
        this.opCorreta = opCorreta;
    }

    /**
     * Devolve o tipo de uma pergunta
     * @return Tipo de uma pergunta
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Devolve a pontuação de uma pergunta
     * @return Pontuação de uma pergunta
     */
    public int getPontuacao() {
        return 5;
    }

    /**
     * Verifica se uma resposta está correta
     * @param resposta Resposta a uma pergunta
     * @return True se a resposta estiver correta, false caso contrário
     */
    public boolean verificarResposta(String resposta) {
        return false;
    }

    /**
     * Devolve a opção de resposta correta de uma pergunta
     * @return Opção de resposta correta de uma pergunta
     */
    public String getRespostaCorreta() {
        return opCorreta;
    }

    /**
     * Devolve o texto de uma pergunta
     * @return Texto de uma pergunta
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Define o texto de uma pergunta
     * @param texto Texto de uma pergunta
     * @return Texto de uma pergunta
     */
    public String setTexto(String texto) {
        return this.texto = texto;
    }

    /**
     * Devolve as opções de resposta de uma pergunta
     * @return Opções de resposta de uma pergunta
     */
    public String[] getOpcoes() {
        return options;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return '[' +
                tipo + '\'' +
                "," + texto + '\'' +
                "," + pont +
                "," + options + '\'' +
                "," + opCorreta + '\'' +
                ']';
    }
    
}
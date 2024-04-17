
public class Jogador  
{
    private String nome;
    private int pontuacao;
    private String tempo;

    /**
     * Construtor da classe Jogador. Inicializa o nome do jogador e define a pontuação inicial como 0.
     */
    public Jogador(String nome)
    {
        this.nome = nome;
        this.pontuacao = 0;
    }

    /**
     * Obtém o nome do jogador.
     */
    public String getNome()
    {
        return nome;
    }

    /**
     * Obtém o tempo do jogador.
     */
    public String getTempo()
    {
        return tempo;
    }

    /**
     * Obtém a pontuação do jogador.
     */
    public int getPontuacao()
    {
        return pontuacao;
    }

    /**
     * Define os dados do jogador, como pontuação e tempo.
     */
    public void setDados(int pontuacao, String tempo){
        this.pontuacao = pontuacao;
        this.tempo = tempo;
    }
}


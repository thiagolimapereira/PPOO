/**
 * Esta classe é parte da aplicação "World of Zuul".
 * "World of Zuul" é um jogo de aventura muito simples, baseado em texto.  
 *
 * Essa classe guarda informações sobre um comando que foi digitado pelo usuário.
 * Um comando atualmente consiste em duas strings: uma palavra de comando e uma 
 * segunda parte (por exemplo, se o campo for "pegar mapa pequeno", entao as duas strings
 * obviamente serao "pegar" e "mapa pequeno").
 * 
 * Isso é usado assim: comandos já estão validados como comandos válidos. Se o 
 * usuário entrou um comando inválido (uma palavra que não é conhecida) então a 
 * palavra de comando é <null>.
 *
 * Se o comando tem só uma palavra, a segunda parte é <null>.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido e adaptado por Julio César Alves)
 */

public class Comando {
    // primeira palavra que representa o comando
    private String palavraDeComando;
    // segunda parte que representa um complemento do comando
    private String segundaParte;

    /**
     * Cria um objeto comando. Primeira palavra e segunda parte devem ser  fornecidas, mas 
     * qualquer uma (ou ambas) podem ser null.
     * @param primeiraPalavra A primeira palavra do comando. Null se o comando não foi reconhecido.
     * @param segundaParte A segunda parte do comando.
     */
    public Comando(String primeiraPalavra, String segundaParte) {
        palavraDeComando = primeiraPalavra;
        this.segundaParte = segundaParte;
    }

    /**
     * Retorna a palavra de comando (a primeira palavra) deste comando.
     * Se o comando não foi entendido, o resultado eh null.
     * @return A palavra de comando.
     */
    public String getPalavraDeComando() {
        return palavraDeComando;
    }

    /**
     * @return A segunda parte deste comando. Retorna null se não existe segunda parte.
     */
    public String getSegundaParte() {
        return segundaParte;
    }

    /**
     * @return true se o comando não foi entendido.
     */
    public boolean ehDesconhecido() {
        return (palavraDeComando == null);
    }

    /**
     * @return true se o comando tem uma segunda parte.
     */
    public boolean temSegundaParte() {
        return (segundaParte != null);
    }
}


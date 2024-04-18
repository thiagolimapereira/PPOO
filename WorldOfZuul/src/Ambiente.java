import java.util.HashMap;

/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe é parte da aplicação "World of Zuul".
 * "World of Zuul" é um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localização no cenário do jogo. Ele é conectado aos 
 * outros ambientes através de saídas. As saídas são nomeadas como norte, sul, leste 
 * e oeste. Para cada direção, o ambiente guarda uma referência para o ambiente vizinho, 
 * ou null se não há saída naquela direção.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido e adaptado por Julio César Alves)
 */
public class Ambiente  {
    // descrição do ambiente
    private String descricao;
    // ambientes vizinhos de acordo com a direção
    private HashMap<String, Saida> saidas;

    private Item item;

    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele não tem saidas. 
     * "descricao" eh algo como "uma cozinha" ou "um jardim aberto".
     * 
     * @param descricao A descrição do ambiente.
     */
    public Ambiente(String descricao, Item item){
        this.descricao = descricao;
        saidas = new HashMap<>();
        this.item = item;
    }

    public Ambiente(String descricao){
        this(descricao, null);
    }

    /**
     * Define uma saída do ambiente.
     * 
     * @param direcao A direção daquela saída.
     * @param saida O ambiente para o qual a direção leva.
     */
    public void ajustarSaida(String direcao, Ambiente saida) {
        saidas.put(direcao, new Saida(saida));   
    }

    public void ajustarSaidaBloqueada(String direcao, Ambiente saida, String itemQueDesbloqueia) {
        saidas.put(direcao, new Saida(saida, true, itemQueDesbloqueia));   
    }

    /**
     * @return A descrição do ambiente.
     */
    public String getDescricao() {
        return "Voce esta " + descricao;
    }

    /**
     * Retorna uma saída do ambiente, dada uma direção (null se não existir)
     * 
     * @param direcao Direção à qual a saída se refere
     * @return Ambiente de saída naquela direção
     */
    public Ambiente getSaida(String direcao) {
        if (saidas.get(direcao).estaBloqueada()) return null;   

        return saidas.get(direcao).getDestino();
    }

    /**
     * Texto montado com todas as saídas disponíveis
     * 
     * @return Texto com as saídas
     */
    public String direcoesDeSaida() {
        String texto = "";
        for (String direcao : saidas.keySet()) {
            texto += direcao + " ";
        }
        return texto;
    }

    /**
     * Retorna uma descrição longa do ambiente. A ideia é que, quando o ambiente
     * evoluir e tiver mais coisas (como itens ou monstros) não seja necessário
     * alterar a classe Jogo para informar o que existe no ambiente.
     * 
     * @return Uma descrição longa do ambiente, incluindo saídas.
     */
    public String getDescricaoLonga() {
        String desc = getDescricao() + "\n";
        if (temItem()) desc += "Tem " + item.getDescricao() + " aqui!\n";
        else desc += "Não há nada aqui.\n";
        desc += "Saidas: " + direcoesDeSaida();
        return desc;
    }

    public boolean temItem(){
        return item != null;
    }

    public Item getItem(){
        return item;
    }

    public Item coletarItem(){
        Item aux = item;
        item = null;
        return aux;
    }

    public boolean usarItem(Item item){
        for (String saida : saidas.keySet()) {
            if (saidas.get(saida).estaBloqueada() && saidas.get(saida).getItemQueDesbloqueia().equals(item.getNome())) {
                saidas.get(saida).desbloquear();
                return true;
            }
        }
        return false;
    }

    public boolean verificarSaida (String direcao){
        for (String saida : saidas.keySet()) {
            if (saida.equals(direcao)) {
                return true;
            }
        }
        return false;
    }

}

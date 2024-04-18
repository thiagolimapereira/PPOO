import java.util.ArrayList;

public class ContraTerrorista {
    private ArrayList<Item> mochila;
    private Ambiente ambienteAtual;

    public ContraTerrorista(Ambiente ambienteAtual) {
        mochila = new ArrayList<>();
        this.ambienteAtual = ambienteAtual;
    }

    public Ambiente getAmbienteAtual() {
        return ambienteAtual;
    }

    public void setAmbienteAtual(Ambiente ambienteAtual) {
        this.ambienteAtual = ambienteAtual;
    }

    public void adicionarItem(Item item) {
        mochila.add(item);
    }

    public void removerItem(String nome) {
        for (Item item : mochila) {
            if (item.getNome().equals(nome)) {
                mochila.remove(item);
                return;
            }
        }
    }

    public Item getItem(String nome) {
        for (Item item : mochila) {
            if (item.getNome().equals(nome)) {
                Item copiaItem = new Item(item.getNome(), item.getDescricao());
                return copiaItem;
            }
        }
        return null;
    }

    public String listarItens() {
        String itens = "Mochila vazia!";
        if (mochila.isEmpty()){
            return itens;
        }
        itens = "Itens na mochila:\n";
        for (Item item : mochila) {
            itens += item.getNome() + "\n";
        }
        return itens;
    }

    public boolean temItem(String nome) {
        for (Item item : mochila) {
            if (item.getNome().equals(nome)) {
                return true;
            }
        }
        return false;
    }
}


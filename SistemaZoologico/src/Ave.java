public class Ave extends Animal {
    private boolean voaBem;

    public Ave (String nome, String especie, String som, boolean voaBem){
        super(nome, especie, 2, som);
        this.voaBem = voaBem;
    }

    public boolean getVoaBem(){
        return voaBem;
    }

    public String getDescricaoLonga(){
        String descricao = super.getDescricaoLonga() + " e voa ";
        if (voaBem) descricao += "bem";
        else descricao += "mal";
        return descricao;
    }
}
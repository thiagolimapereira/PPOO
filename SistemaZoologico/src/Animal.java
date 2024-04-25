public class Animal {
    private String nome;
    private String especie;
    private int qtdPatas;
    private String som;

    public Animal (String nome, String especie, int qtdPatas, String som){
        this.nome = nome; 
        this.especie = especie;
        this.qtdPatas = qtdPatas;
        this.som = som;
    }

    public String getNome(){
        return nome;
    }

    public String getEspecie(){
        return especie;
    }

    public int getQtdPatas(){
        return qtdPatas;
    }

    public String getSom(){
        return som;
    }

    public String getDescricao(){
        String descricao = nome + " e um(a) " + especie;
        return descricao;
    }

    public String getDescricaoLonga(){
        return getDescricao() + " que faz " + som;
    }
}
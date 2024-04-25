public class Mamifero extends Animal {
    private String corPelo;

    public Mamifero (String nome, String especie, int qtdPatas, String som, String corPelo){
        super(nome, especie, qtdPatas, som);
        this.corPelo = corPelo;
    }

    public String getCorPelo(){
        return corPelo;
    }

    public String getDescricaoLonga(){
        return super.getDescricaoLonga() + " e tem pelo " + corPelo;
    }
}
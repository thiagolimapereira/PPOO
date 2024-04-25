import java.util.HashMap;
public class Zoo{
    private HashMap<String, Avestruz> avestruzes;
    private HashMap<String, Tucano> tucanos;
    private HashMap<String, Tigre> tigres;
    private HashMap<String, Chipanze> chipanzes;

    public Zoo (){
        avestruzes = new HashMap<>();
        tucanos = new HashMap<>();
        tigres = new HashMap<>();
        chipanzes = new HashMap<>();
    }

    public void addAvestruz (String nome){
        Avestruz avestruz = new Avestruz(nome);
        avestruzes.put(nome, avestruz);
    }

    public void addTucano (String nome){
        Tucano tucano = new Tucano(nome);
        tucanos.put(nome, tucano);
    }

    public void addTigre (String nome){
        Tigre tigre = new Tigre(nome);
        tigres.put(nome, tigre);
    }

    public void addChipanze (String nome){
        Chipanze chipanze = new Chipanze(nome);
        chipanzes.put(nome, chipanze);
    }

    public String obterDescricao(String nome){
        if (avestruzes.get(nome) != null) return avestruzes.get(nome).getDescricaoLonga();
        else if(tucanos.get(nome) != null) return tucanos.get(nome).getDescricaoLonga();
        else if(tigres.get(nome) != null) return tigres.get(nome).getDescricaoLonga();
        else if(chipanzes.get(nome) != null) return chipanzes.get(nome).getDescricaoLonga();
        else return "Animal nao encontrado!";
    }

    public String listarAnimais(){
        String resultado = "";
        for (String nome : avestruzes.keySet()) {
            resultado += avestruzes.get(nome).getDescricao() + "\n";
        }
        for (String nome : tucanos.keySet()) {
            resultado += tucanos.get(nome).getDescricao() + "\n";
        }
        for (String nome : tigres.keySet()) {
            resultado += tigres.get(nome).getDescricao() + "\n";
        }
        for (String nome : chipanzes.keySet()) {
            resultado += chipanzes.get(nome).getDescricao() + "\n";
        }
        return resultado;
    }

    public String listarAnimaisCompleto(){
        String resultado = "";
        for (String nome : avestruzes.keySet()) {
            resultado += avestruzes.get(nome).getDescricaoLonga() + "\n";
        }
        for (String nome : tucanos.keySet()) {
            resultado += tucanos.get(nome).getDescricaoLonga() + "\n";
        }
        for (String nome : tigres.keySet()) {
            resultado += tigres.get(nome).getDescricaoLonga() + "\n";
        }
        for (String nome : chipanzes.keySet()) {
            resultado += chipanzes.get(nome).getDescricaoLonga() + "\n";
        }
        return resultado;
    }
}
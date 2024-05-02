public class Teste {
    public static void main(String[] args) {
        Animal animal = new Tigre("Trigo");
        getDescricaoLonga(animal);
        animal = new Avestruz("Avex");
        getDescricaoLonga(animal);
    }

    public static void getDescricaoLonga(Animal animal) { 
        System.out.println(animal.getDescricaoLonga());
    }
}
public class Saida {
    private Ambiente destino;
    private boolean bloqueada;
    private String itemQueDesbloquia;

    public Saida(Ambiente destino) {
        this(destino, false, null);
    }

    public Saida(Ambiente destino, boolean bloqueada, String itemQueDesbloquia) {
        this.destino = destino;
        this.bloqueada = bloqueada;
        this.itemQueDesbloquia = itemQueDesbloquia;
    }

    public Ambiente getDestino() {
        return destino;
    }

    public boolean estaBloqueada() {
        return bloqueada;
    }

    public String getItemQueDesbloqueia() {
        return itemQueDesbloquia;
    }

    public void desbloquear() {
        bloqueada = false;
    }
}

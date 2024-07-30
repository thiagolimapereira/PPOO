public class MensagemNaoEncontradaException extends RuntimeException {
    int idMensagem;
    public MensagemNaoEncontradaException(int idMensagem) {
        super();
        this.idMensagem = idMensagem;
    }
    public MensagemNaoEncontradaException(int idMensagem, String message) {
        super(message);
        this.idMensagem = idMensagem;
    }
    public int getIdMensagem() {
        return idMensagem;
    }
}
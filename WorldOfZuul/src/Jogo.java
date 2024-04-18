/**
 * Essa é a classe principal da aplicacao "World of Zull".
 * "World of Zuul" é um jogo de aventura muito simples, baseado em texto.
 * 
 * Usuários podem caminhar em um cenário. E é tudo! Ele realmente precisa ser 
 * estendido para fazer algo interessante!
 * 
 * Para jogar esse jogo, crie uma instancia dessa classe e chame o método "jogar".
 * 
 * Essa classe principal cria e inicializa todas as outras: ela cria os ambientes, 
 * cria o analisador e começa o jogo. Ela também avalia e  executa os comandos que 
 * o analisador retorna.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido e adaptado por Julio César Alves)
 */

public class Jogo {
    // analisador de comandos do jogo
    private Analisador analisador;
    // jogador Contra-Terrorista
    private ContraTerrorista jogador;
        
    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo()  {
        criarAmbientes();
        analisador = new Analisador();
    }

    /**
     * Cria todos os ambientes e liga as saidas deles
     */
    private void criarAmbientes() {
        Ambiente lobby, reator, salaDeAmostras, corredor, duto, salaDeReuniao, laboratorio;
        Item chaveDeFenda, kitDesarme;
        chaveDeFenda = new Item("chave de fenda", "uma chave de fenda comum");
        kitDesarme = new Item("kit", "um kit usado para desarmar bombas");
      
        // cria os ambientes
        lobby = new Ambiente("em um espaço onde pessoas costumam esperar para entrar em outro ambiente");
        reator = new Ambiente("no espaco central onde as reacoes nucleares acontecem, um duto de ar pode ser visto ao leste");
        salaDeAmostras = new Ambiente("na sala onde amostras sao armazenadas");
        corredor = new Ambiente("no um corredor que leva de um lugar a outro, acima, um duto de ar pode ser visto");
        duto = new Ambiente("em duto apertado para a passagem de ar", kitDesarme);
        salaDeReuniao = new Ambiente("na sala de reuniao da usina");
        laboratorio = new Ambiente("no laboratorio onde testes sao feitos", chaveDeFenda);
        
        // inicializa as saidas dos ambientes
        lobby.ajustarSaida("leste", reator);
        lobby.ajustarSaida("norte", laboratorio);
        

        reator.ajustarSaida("oeste", lobby);
        reator.ajustarSaidaBloqueada("leste", duto, "chave de fenda");
        reator.ajustarSaida("sul", salaDeAmostras);
        
        corredor.ajustarSaida("norte", salaDeReuniao);  
        corredor.ajustarSaidaBloqueada("cima", duto, "chave de fenda");  
        corredor.ajustarSaida("oeste", salaDeAmostras);      
        
        salaDeAmostras.ajustarSaida("norte", reator);
        salaDeAmostras.ajustarSaida("leste", corredor);
        
        duto.ajustarSaida("oeste", reator);
        duto.ajustarSaida("baixo", corredor);

        salaDeReuniao.ajustarSaida("sul", corredor);
        salaDeReuniao.ajustarSaida("oeste", laboratorio);

        laboratorio.ajustarSaida("sul", lobby);
        laboratorio.ajustarSaida("leste", salaDeReuniao);

        jogador = new ContraTerrorista(lobby); 
    }

    /**
     *  Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar()  {
        imprimirBoasVindas();

        // Entra no loop de comando principal. Aqui nós repetidamente lemos comandos e 
        // os executamos até o jogo terminar.
                
        boolean terminado = false;
        while (! terminado) {
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Até mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas() {
        System.out.println();
        System.out.println("Bem-vindo ao CS 0.5, a primeira versao de um jogo que promete fazer sucesso!");
        System.out.println("Algum terrorista colocou uma bomba ao lado do reator de uma usina nuclear.");
        System.out.println("Voce precisa achar um kit de desarme e desarmar a bomba antes que ela exploda");
        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();
        
        imprimirLocalizacaoAtual(false);
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando)  {
        boolean querSair = false;

        if(comando.ehDesconhecido()) {
            System.out.println("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        }
        else if (palavraDeComando.equals("ir")) {
            irParaAmbiente(comando);
        }
        else if (palavraDeComando.equals("observar")) {
            observar(comando);
        }
        else if (palavraDeComando.equals("pegar")) {
            pegarItem(comando);
        }
        else if (palavraDeComando.equals("inventario")) {
            System.out.println(jogador.listarItens());
        }
        else if (palavraDeComando.equals("usar")) {
            usarItem(comando);
        }
        else if (palavraDeComando.equals("desarmar")) {
            querSair = desarmarBomba();
        }
        else if (palavraDeComando.equals("sair")) {
            querSair = sair(comando);
        }

        return querSair;
    }

    /**
     * Exibe informações de ajuda.
     * Aqui nós imprimimos algo bobo e enigmático e a lista de  palavras de comando
     */
    private void imprimirAjuda()  {
        System.out.println("Voce eh a unica pessoa capaz de impedir");
        System.out.println("esse desastre. Nao ha tempo de chamar ajuda");
        System.out.println("a humanidade depende de voce!");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        System.out.println("   " + analisador.getComandosValidos());
    }

    /**
     * Trata o comando "observar", exibindo as informações da localização atual do jogador
     * 
     * @param comando Objeto comando a ser tratado
     */
    private void observar(Comando comando) {
        // se há segunda parte, explica para o usuário que não pode...
        if(comando.temSegundaParte()) {            
            System.out.println("Não é possível observar detalhes de alguma coisa");
            return;
        }

        imprimirLocalizacaoAtual(true);
    }

    /** 
     * Tenta ir em uma direcao. Se existe uma saída para lá entra no novo ambiente, 
     * caso contrário imprime mensagem de erro.
     */
    private void irParaAmbiente(Comando comando)  {
        // se não há segunda parte, não sabemos pra onde ir...
        if(!comando.temSegundaParte()) {            
            System.out.println("Ir pra onde?");
            return;
        }

        String direcao = comando.getSegundaParte();

        if (!jogador.getAmbienteAtual().verificarSaida(direcao)) {
            System.out.println("Nao ha passagem!");
            return;
        }

        // Tenta sair do ambiente atual
        Ambiente proximoAmbiente = jogador.getAmbienteAtual().getSaida(direcao);

        if (proximoAmbiente == null) {
            System.out.println("Nao ha passagem!");
        }
        else {
            jogador.setAmbienteAtual(proximoAmbiente);
            
            imprimirLocalizacaoAtual(false);
        }
    }

    /**
     * Exibe as informações da localização atual para o jogador
     */
    private void imprimirLocalizacaoAtual(boolean longa) {
        if (longa) {
            System.out.println(jogador.getAmbienteAtual().getDescricaoLonga());
        }
        else {
            System.out.println(jogador.getAmbienteAtual().getDescricao());
        }
        System.out.println();
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando pra ver se nós queremos 
     * realmente sair do jogo.
     * @return true, se este comando sai do jogo, false, caso contrário.
     */
    private boolean sair(Comando comando)  {
        if(comando.temSegundaParte()) {
            System.out.println("Sair o que?");
            return false;
        }
        else {
            return true;  // sinaliza que nós realmente queremos sair
        }
    }

    /**
     * Trata o comando "pegar", permitindo ao jogador pegar um item do ambiente
     */
    private void pegarItem(Comando comando)  {
        if (!jogador.getAmbienteAtual().temItem()) {
            System.out.println("Não há nada para pegar aqui!");
            return;
        }
        // se não há segunda parte, não sabemos o que pegar...
        if(!comando.temSegundaParte()) {            
            System.out.println("Pegar o que?");
            return;
        }

        String item = comando.getSegundaParte().toLowerCase();

        if (jogador.getAmbienteAtual().getItem().getNome().toLowerCase().equals(item)) {
            System.out.println("Voce pegou " + jogador.getAmbienteAtual().getItem().getDescricao());
            jogador.adicionarItem(jogador.getAmbienteAtual().coletarItem());
        }
        else {
            System.out.println("Não há " + item + " aqui!");
        }
    }

    /**
     * Trata o comando "usar", permitindo ao jogador usar um item da mochila
     */
    private void usarItem(Comando comando)  {
        // se não há segunda parte, não sabemos o que usar...
        if(!comando.temSegundaParte()) {            
            System.out.println("Usar o que?");
            return;
        }

        String item = comando.getSegundaParte().toLowerCase();
        
        if (!jogador.temItem(item)) {
            System.out.println("Voce nao tem " + item + " na mochila!");
            return;
        }
        else{
            if (jogador.getAmbienteAtual().usarItem(jogador.getItem(item))) {
                System.out.println("Uma passagem foi desbloqueada!");
                // não removi o item da mochila, pois ele pode ser usado novamente
            }
            else {
                System.out.println("O item " + item + " nao pode ser usado aqui!");
            }
        }
    }

    /**
     * Trata o comando "desarmar", permitindo ao jogador desarmar a bomba
     */
    private boolean desarmarBomba() {
        if (jogador.getAmbienteAtual().getDescricao().contains("reacoes nucleares")) {
            if (jogador.temItem("kit")) {
                    System.out.println("Voce desarmou a bomba e salvou a humanidade! Parabens!");
                    return true;
                }                
                System.out.println("Voce nao tem o kit de desarme!");
                
                return false;
        }
        System.out.println("A bomba nao esta aqui!");
        return false;        
    }               
}

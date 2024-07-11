package series;

/**
 * Interface para definir o que uma Série de Dados deve ter.
 * 
 * Uma série de dados tem uma periodicidade, um intervalo (dado por um período inicial e um final) e dados 
 * (valores numéricos) correspondentes a cada período.
 * 
 * A periodicidade pode ser, por exemplo, horária, diária, semanal, etc. Já os períodos, por simplicidade, 
 * são dados por números inteiros.
 *  - Exemplo: suponha que a série tenha periodicidade horária, e o período inicial seja 7 e o final 12. 
 *              Isso significa que a série terá dados de hora em hora a partir das 7h até às 12h.
 * 
 * Os dados das séries são valores correspondentes a cada período.
 * - Portanto, uma série poderia ser, por exemplo, a quantidade de visualizações de um vídeo no Youtube,
 *   por hora, da hora 7 a hora 12.
 * - Ou o valor arrecadado por uma empresa, por mês, do mês 2 ao mês 10.
 * 
 * @author Julio César Alves
 */
public interface SerieDados {
    /**
     * Retorna uma string que representa o nome ou a identificação da série de
     * dados em questão.
     * 
     * @return Nome ou identificação da série de dados
     */
    String obterIdentificacao();
    
    /**
     * Retorna o início do período para o qual a série tem dados. Exemplo: periodicidade diária
     * com valor inicial 5. Considera-se que a série tem dados para todos os dias desde o valor inicial
     * do período até o valor final.
     * 
     * @return O primeiro valor que possui dados.
     */
    int obterInicioPeriodo();
    
    /**
     * Retorna o último valor para o qual a série tem dados. Exemplo: periodicidade diária
     * com valor final 21. Considera-se que a série tem dados para todos os dias desde o valor inicial
     * do período até o valor final.
     * 
     * @return O último valor que possui dados.
     */
    int obterFimPeriodo();

    /**
     * Retorna o período em que a série está vínculada
     * @return A períodicidade do intervalo
     */
    Periodicidade obterPeriodicidade();
    
    /**
     * Retorna o dado correspondente a um valor da série de dados.
     * 
     * Obs: seria mais eficiente o retorno de um conjunto de dados, mas está
     * assim por motivos didáticos.
     * 
     * @param periodo Período para o qual se deseja obter o dado correspondente.
     *                O período deve estar compreendido entre o período inicial e o período final.
     */
    double obterDado(int periodo);
}

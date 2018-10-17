package Util;

/**
 * Classe que cria um array dinâmico, que aumenta de tamanho indeterminadamente, sempre que for necessário. Útil para
 *  armazenar valores sem saber a quantidade máxima possível. Tende a consumir mais memória que uma lista encadeada,
 *  mas funciona mais rápido, pois acessa os valores diretamente a partir do índice.
 * @author Johnny, Gustavo
 */
public interface IArray{
    
    /**
     * Adiciona um objeto no array. Caso ultrapasse o tamanho atual, ele cria um novo array com o dobro de tamanho,
     *  transfere tudo que está no array antigo para o novo, e substitui o array atual pelo array novo.
     * @param data Objeto a ser adicionado
     */
    public void add(Object data);
    
    /**
     * Define o conteúdo de um índice específico do array.
     * @param index Índice do array
     * @param data Objeto a ser adicionado
     */
    public void set(int index, Object data);
    
    /**
     * Retorna o conteúdo de um índice do array.
     * @param index Índice do array
     * @return Conteúdo do índice recebido
     */
    public Object get(int index);
    
    /**
     * Verifica se um objeto se encontra nesse array.
     * @param obj Objeto a ser procurado.
     * @return True caso esteja, False caso contrário.
     */
    public boolean contains(Object obj);
    
    /**
     * Remove um objeto do array.
     * @param obj Objeto a ser removido.
     */
    public void remove(Object obj);
    
    /**
     * Remove um objeto do array através do seu indice no array.
     * @param index Índice do objeto a ser removido
     */
    public void remove(int index);
    
    /**
     * @return Quantidade de objetos dentro do array 
     */
    public int size();
    
    /**
     * @return True caso o array esteja vazio (sem nenhum objeto), False caso contrário. 
     */
    public boolean isEmpty();
    
}

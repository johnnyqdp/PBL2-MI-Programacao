/*****************************************************************************************
Autores: Johnny Quest Dantas Pereira, Gustavo dos Santos Menezes Alves
Componente Curricular: MI-Programação
Concluido em: 27/07/2018
Declaramos que este código foi elaborado por nós de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/
package Util;
import java.io.Serializable;
import java.util.Iterator;
/**
 * Essa classe representa a estrutura de dados Lista Encadeada. Esta foi utilizada para armazenar a lista de amigos
 * ,solicitações de amizades  e lista de postagens de um usuário.
 * @author Gustavo e Johnny
 */
public class ListaEncadeada implements Serializable{
    private Node head;
    private int size;
  /**
   * Método que recebe um objeto e adiciona no início da lista.
   * @param data Objeto recebido por parâmetro.
   * 
   */
    public void add(Object data) {
        Node newNode = new Node(data);
        newNode.setNext(head);
        head=newNode;       
        size++;
    }
    /**
     * Método que pega um objeto presente na lista encadeada.
     * @param index Representa a posição do nó.
     * @return O objeto presente naquela posição.
     */
    public Object get(int index) {
        Node n;
        if((n = getNode(index)) != null)
            return n.getData();
        return null;
    }
    /**
     * Método que verifica se um objeto está presente na lista.
     * @param obj Objeto passado como parâmetro.
     * @return Verdadeiro se estiver na lista e falso se não estiver.
     */
    public boolean contains(Object obj) {
        Node aux = this.head;
        for(int i=0; i<size; i++){
            if(aux.getData().equals(obj)){
                return true;
            }
            aux=aux.getNext();            
        }
        return false;
    }
    /**
     * Método que remove um objeto presente na lista
     * @param data Objeto a ser removido.
     */
    public void remove(Object data) {         
        Node n = head;         
        for (int i = 0; n != null; i++) {             
            if (n.getData() != null && n.getData().equals(data)){                 
                removeint(i);                 
                return;             
            }             
            n = n.getNext();         
        }
    } 
    /**
     * Método que conta a quantidade de elementos na lista a partir da inserção e remoção.
     * @return Tamanho da lista.
     */
    public int size() {
        return this.size;
    }
    /**
     * Método que indica se a lista está cheia ou não.
     * @return Lista vazia.
     */
    public boolean isEmpty() {
        return this.size==0;
    }
    /**
     * Método que percorre a lista.
     * @return Iterador para percorrer a lista.
     */
    public Iterator iterator() {
        return new ListIterator();
    }
    /**
     * Método que auxilia no método de remoção de um elemento da lista,dada uma certa posição
     * @param index Posição para remover o nó.
     */
    private void removeint(int index) {                
        if(index == 0){            
            head = head.getNext();
        }else if (index > 0 && index <= (size() - 1)) {            
            Node prev= getNode(index - 1);             
            Node remover= prev.getNext();             
            prev.setNext(remover.getNext());         
        }
        size--;
    }    
    /**
     * Método que pega um nó da lista , apartir de uma posição.
     * @param index Posição para pegar nó da lista
     * @return Nó da lista.
     */
    private Node getNode(int index) {
        if(index<size){
            Node aux=head;
            for(int i=0; i<index; i++){
                aux=aux.getNext();                
            }
            return aux;                
        }
        return null;
    }
    /**
     * Classe interna que contém um iterador, utilizado para percorrer a lista,
     * verificando se tem próximo e retornando o objeto presente no nó.
     */
    private class ListIterator implements Iterator, Serializable{
        private int contador;
        
        @Override
        public boolean hasNext() {
           return getNode(contador)!=null;
        }

        @Override
        public Object next() {
           Object aux=get(contador);
           contador++;
           return aux;
        }
        
    }
    /**
     * Esta classe representa o nó da lista encadeada, que recebe um objeto e aponta para o próximo da lista.
     */
    private class Node implements Serializable{
        private Object data;
        private Node next;
        /**
         * Construtor da classe Node, que recebe um Object como argumento.
         * @param obj Objeto argumentado no método.
         */
        public Node(Object obj){
            this.data = obj;
        }
        /**
         * @return O elemneto(data) Object.
         */
        public Object getData() {
            return data;
        }
        /**
         * @param data  Objeto  a ser inserido.
         */
        public void setData(Object data) {
            this.data = data;
        }
        /**
         * @return O Nó próximo da lista
         */
        public Node getNext() {
            return next;
        }
        /**
         * @param next Nó a ser inserido 
         */
        public void setNext(Node next) {
            this.next = next;
        }
        
    }
}

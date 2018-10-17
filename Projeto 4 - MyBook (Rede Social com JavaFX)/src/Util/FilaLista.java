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
/**
 * Essa classe representa a estrutura de dados Fila, que foi utilizada no método de busca em largura do grafo.
 * Utilizando-se da ideia que insere no final e remove no início.
 * @author Gustavo e Johnny.
 */
import java.io.Serializable;

public class FilaLista implements Serializable{
    private Node head;
    private Node tail;
    /**
     * Método que adiciona um objeto no final da fila.
     * @param data Objeto a ser adicionado.
     */
    public void add(Object data){
        if(isEmpty()){
            head = tail = new Node(data);
        }else{
            Node newNode = new Node(data);
            tail.setNext(newNode);
            tail=newNode;
        }
    }
    /**
     * Método que remove um objeto do início da fila.
     * @return Objeto que estava no início .
     */
    public Object remove(){
        Node oldHead = head;
        head=head.getNext();
        return oldHead.getData();
    }
    /**
     * Método utilizado para saber quem é o primeiro elemento da fila.
     * @return O primeiro objeto da lista, mas não o remove.
     */
    public Object peek(){
        return head.getData();
    }
    /**
     * Método que indica se a fila está vazia ou não.
     * @return Fila vazia.
     */
    public boolean isEmpty(){
        return head==null;
    }    
    /**
     * Esta classe representa o nó da fila, que recebe um objeto e aponta para o próximo da lista.
     */
    private class Node{
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

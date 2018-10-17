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
 * Essa classe representa a estrutura de dados HashSet que foi utilizada para armazenar todos usuarios no grafo.
 * @author Gustavo e Johnny
 */
import Model.User;
import java.io.Serializable;
import java.util.Iterator;

public class HashSet implements Serializable{        
    /**
     *Atributos da HashSet: sendo a um array de listas para armazenar os objetos na hash,
     * o size para contar elementos da hash e um fator de carga no valor de 0.75.
     */
    private ListaEncadeada[] listas;
    private int size;        
    private final double LIMITEMAX = 0.75;
    /**
     * Construtor da hash inicialmente alocado com array de listas no tamanho 31, por se tratar de um número primo,
     * afim de reduzir o número de col
     */
    public HashSet() {
        listas = new ListaEncadeada[31];
    }
    /**
     * Método que adiciona um objeto na hash, em uma determinada posição do array de listas
     * indicada pela hashFuction e caso atinja o fator de carga, a hash é redimencionada.
     * @param u Objeto a ser inserido.
     */
    public void put(Object u) {
        if(excedeuLimite())
            redimensionar();            
        int i = hashFunction(u);            
        if(listas[i] == null)
            listas[i] = new ListaEncadeada();
        listas[i].add(u);
        size++;
    }
    /**
     * Método que remove um objeto do array de listas , através da hashFuctio que indica a posição do objeto na hash.
     * @param u Objeto a ser removido.
     */
    public void remove(Object u) {
        int i = hashFunction(u);
        if(listas[i] != null){
            Iterator it = listas[i].iterator();
            while(it.hasNext()){
                Object pego = it.next();
                if(u.equals(pego)){
                    listas[i].remove(pego);
                    size--;
                }
            }
        }
    }
    /**
     * Metódo que indica a quantidade de elementos presentes na hash, de acordo com as inserções de elementos
     * e a remoção.
     * @return Quantidade de elementos.
     */
    public int size() {
        return size;
    }
    /**
     * Método que verifica se um objeto está ou não presente na hash.
     * @param u Objeto que se deseja verificar.
     * @return Verdadeiro se estiver ou falso se não estiver.
     */
    public boolean contains(Object u) {
        int i = hashFunction(u);
        if(listas[i] != null){
            Iterator it = listas[i].iterator();
            while(it.hasNext()){
                Object pego = it.next();
                if(u.equals(pego))
                    return true;
            }            
        }
        return false;
    }
    /**
     * Método que pega um objeto que está presente na hash.
     * @param u Objeto que se deseja obter.
     * @return O objeto a ser pego.
     */
    public Object get(Object u) {
        int i = hashFunction(u);
        if(listas[i] != null){
            Iterator it = listas[i].iterator();
            while(it.hasNext()){
                Object pego = it.next();
                if(u.equals(pego))
                    return pego;
            }
        }
        return null;
    }
    /**
     * Metódo que verifica se a hash está ou não vazia.
     * @return Hash vazia.
     */
      public boolean isEmpty() {
        return size == 0;
    }
    /**
     * Método  que pega todas as listas presentes na hash.
     * @return As listas. 
     */
    public ListaEncadeada[] getListas(){
        return listas;
    }
    /**
     * Método que realiza a função hash para indicar a posição onde o elemento deve ser inserido.
     * @param u Objeto passado como parâmetro
     * @return A posição para inserir objeto.
     */
    private int hashFunction(Object u){
        return Math.abs(u.hashCode()) % listas.length;
    }
    /**
     * Método que verifica se excedeu o fator de carga ou não.
     * @return  Se excedeu ou não.
     */
    private boolean excedeuLimite() {
        return (double)size / (double)listas.length > LIMITEMAX;
    }
    /**
     * Método que redimensiona a hash quando ela superar o fator de carga.
     */
    private void redimensionar() {
        ListaEncadeada[] antigo = listas;
        listas = new ListaEncadeada[proximoPrimo(antigo.length*2)];

        for(ListaEncadeada l : antigo){
            if(l != null){
                for(int i=0; !l.isEmpty(); i++)
                    put(l.get(i));
            }
        }
    }
    /**
     * Método que verifica qual é o proximo número primo.
     * @param n Numéro passado como parâmetro.
     * @return Número primo.
     */
    private int proximoPrimo(int n) {                        
        while(!isPrimo(n))
            n++;
        return n;
    }
    /**
     * Método que auxilia na verificação do número primo.
     * @param n Inteiro passado como parãmetro para saber se é primo.
     * @return Verdadeiro se for primo e falso se não for.
     */
    private boolean isPrimo(int n) {
        for(int i=2; i<n; i++){
            if(n%i == 0)
                return false;
        }            
        return true;
    }
}
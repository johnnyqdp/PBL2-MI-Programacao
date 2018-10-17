/*****************************************************************************************
Autores: Johnny Quest Dantas Pereira, Gustavo dos Santos Menezes Alves
Componente Curricular: MI-Programação
Concluido em: 21/05/2018
Declaro que este código foi elaborado por nós de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package Util;

import java.io.Serializable;

public class Array implements IArray, Serializable{    
    private Object objetos[];
    private int next;
    private int size;
    
    /**
     * Construtor do Array dinâmico. É necessário informar o seu tamanho inicial.
     * @param size Tamanho mínimo do array.
     */
    public Array(int size){
        objetos = new Object[size];
        this.size=size;        
    }

    /**
     * Adiciona um objeto no array, duplica seu tamanho máximo caso o mesmo seja excedido.
     * @param data Objeto a ser adicionado.
     */
    @Override
    public void add(Object data) {
        if(next<size){
            objetos[next]=data;
            next++;
        }else{
            int newSize=size*2;
            Object temp[] = new Object[newSize];
            for(int i=0; i<size; i++){
                temp[i]=objetos[i];
            }
            objetos = temp;
            size=newSize;
            add(data);
        }
    }

    /**
     * Adiciona um objeto em um índice específico do array.
     * @param index Índice do array tal que o objeto será adicionado
     * @param data Objeto a ser adicionado.
     */
    @Override
    public void set(int index, Object data) {
        if(index<size){
            objetos[index]=data;
        }
    }

    /**
     * Retorna o objeto que esteja no índice solicitado.
     * @param index Índice do objeto.
     * @return Objeto a ser retornado.
     */
    @Override
    public Object get(int index) {
        if(index < size){
            return objetos[index];
        }
        return null;
    }

    /**
     * Verifica se um objeto está contido nesse array.
     * @param obj Objeto a ser verificado.
     * @return True caso o objeto esteja no array, False caso contrário.
     */
    @Override
    public boolean contains(Object obj) {
        for(int i=0; i<next; i++){
            if(objetos[i].equals(obj)){
                return true;
            }
        }
        return false;
    }

    /**
     * Remove um objeto do array.
     * @param obj Objeto a ser removido.
     */
    @Override
    public void remove(Object obj) {
        for(int i=0; i<next; i++){
            if(objetos[i].equals(obj)){
                objetos[i] = objetos[next-1];
                next--;                
            }
        }
    }

    /**
     * Remove um objeto do array através do índice.
     * @param index Indice do objeto a ser removido do array.
     */
    @Override
    public void remove(int index) {
        if(index<next){
            objetos[index] = objetos[next-1];
            next--;
        }
    }
    
    /**
     * @return Quantidade de objetos no array. 
     */
    @Override
    public int size() {
        return next;
    }
    
    /**
     * @return True caso não tenha nenhum objeto no array, False caso contrário. 
     */
    @Override
    public boolean isEmpty(){
        return next==0;
    }
    
    /**
     * Algoritmo de ordenação SelectionSort.
     */
    public void selectionSort(){
        for(int i=0; i<this.size(); i++){
            int minIndex= i;
            for(int j=i+1; j<this.size(); j++){
                Comparable x = (Comparable)this.get(j);
                Comparable y = (Comparable)this.get(minIndex);
                if(x.compareTo(y) < 1){
                    minIndex= j;
                }
            }
            swap(i, minIndex);
        }
    }
    
    /**
     * Método swap, que troca dois objetos de posição num array.
     * @param i Número que representa o índice de um objeto a ser trocado
     * @param minIndex Número que representa o índice do outro objeto a ser trocado.
     */
    private void swap(int i, int minIndex){
        Object aux = this.get(i);
        this.set(i, this.get(minIndex));
        this.set(minIndex, aux); 
    }
    
    /**
     *Verifica se esse array é igual a outro.
     * @param obj Uma array para comparar.
     * @return True caso as duas arrays sejam iguais, False caso não.
     */
    @Override
    public boolean equals(Object obj){
        Array recebido = (Array)obj;
        if(this.size() == recebido.size()){
            for(int i=0; i<this.size(); i++){
                if(!this.get(i).equals(recebido.get(i))){
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }
}


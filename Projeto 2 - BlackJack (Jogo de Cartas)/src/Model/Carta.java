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

package Model;

import java.io.Serializable;

/**
 * Um objeto Carta irá possuir um id, um naipe e um valor (todos valores inteiros).
 * A id representa qual é a carta, Exemplos: para um Ás, temos id 1; para um 6, temos id 6, para um Rei, temos id 13.
 * O naipe é representado por inteiros. 1-Espadas, 2-Copas, 3-Paus,  4-Ouros.
 * O valor da carta é atribuído por um método após receber o id dela.
 * @author Johnny, Gustavo
 */
public class Carta implements Comparable, Serializable{
    private int id;
    private int valor;
    private int naipe;
        
    /**
     * @return O id da carta. (1- Ás, 2- Dois, 3- Três ... 11- Valete, 12- Rainha, 13- Rei.)
     */
    public int getId() {
        return id;
    }

    /**
     *Define qual será o id da carta.
     * @param id - um inteiro que representa o naipe da carta. (1- Ás, 2- Dois, 3- Três ... 11- Valete, 12- Rainha, 13- Rei.)
     */
    public void setId(int id) {
        this.id = id;
        if(id<=10){
            valor = id;
        }else if (id>10){
            valor = 10;
        }
    }

    /**
     * @return Um inteiro que representa o naipe da carta. (1-Espadas, 2-Copas, 3-Paus, 4-Ouros.)
     */
    public int getNaipe() {
        return naipe;
    }

    /**
     * Define qual será o naipe da carta.
     * @param naipe Inteiro que representa o naipe da carta. (1-Espadas, 2-Copas, 3-Paus, 4-Ouros.)
     */
    public void setNaipe(int naipe) {
        this.naipe = naipe;
    }
    
     /**
     * @return O valor que aquela carta deverá somar na pontuação do jogador que recebê-la.
     */
    public int getValor() {
        return valor;
    }

    /**
     *Gera uma string que define precisamente que carta é essa.
     * Exemplo: id=12, naipe=3, toString retornará "Rainha de Paus".
     * @return Uma String que representa a carta.
     */
    @Override
    public String toString() {
        String idToString;
        String naipeToString;
        if(id==1){
            idToString = "Ás";
        }else if(id>=2 && id<=10){
            idToString = Integer.toString(id);
        }else if(id==11){
            idToString = "Valete";
        }else if(id==12){
            idToString = "Rainha";
        }else{
            idToString = "Rei";
        }


        if(naipe == 1){
            naipeToString = "Espadas";
        }else if (naipe==2){
            naipeToString = "Copas";
        }else if (naipe==3){
            naipeToString = "Paus";
        }else{
            naipeToString = "Ouros";
        }
        return idToString + " de " + naipeToString;
        
    }

    /**
     *Verifica se essa carta é igual a outra.
     * @param obj Uma carta para comparar.
     * @return True caso as duas cartas sejam iguais, False caso não.
     */
    @Override
    public boolean equals(Object obj) {
        Carta temp = (Carta)obj;
        return temp.getId() == this.getId() && temp.getNaipe() == this.getNaipe();
    }   

    /**
     * Compara essa carta com outra, para ser utilizada em um algoritmo de ordenação de cartas.
     * @param t Uma carta para comparar.
     * @return Um número negativo caso essa carta seja "menor" que a recebida, um número positivo caso ela seja "maior".
     */
    @Override
    public int compareTo(Object t) {
        Carta temp = (Carta)t;

        if(this.naipe < temp.getNaipe()){
            return -1;
        }else if(this.naipe > temp.getNaipe()){
            return 1;
        }else if(this.naipe == temp.getNaipe() && this.id < temp.getId()){
            return -1;
        }else{
            return 1;
        }
    }
}

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
import Util.Array;
import java.io.Serializable;
import java.util.Random;

/**
 *O baralho guarda um array de 52 cartas, 13 de cada naipe.
 * É possivel embaralhar essas cartas e remover a carta do topo, retornando-a.
 * @author Johnny, Gustavo
 */
public class Baralho implements Serializable{
    private Array cartas = new Array(52);
    private int cartaAtual;
    
    /**
     *O construtor preenche o array de cartas com todas as cartas
     * de um baralho convencional.
     */
    public Baralho(){
        for(int naipe=1; naipe<=4; naipe++){
            for(int id=1; id<=13; id++){
                Carta temp = new Carta();                
                temp.setNaipe(naipe);  
                temp.setId(id);
                cartas.add(temp);             
            }
        }     
    }
    
    /**
     *Embaralha o array de cartas do baralho.
     */
    public void embaralha(){
        Random gerador = new Random();
        int limite = 51;
        Carta aux;
        int numeroGerado;
        /* Esse processo de embaralhar cartas foi desenvolvido na primeira sessão do problema, ele basicamente
        pega uma carta aleatória e a posiciona no final do baralho. E as próximas cartas aleatórias
        serão posicionadas antes dessa, uma por uma. (algoritmo se encontra no relatório de mesa da 1ª sessão). */
        while(limite>0){
            numeroGerado=gerador.nextInt(limite);
            aux = (Carta)cartas.get(numeroGerado);
            cartas.set(numeroGerado, cartas.get(limite));
            cartas.set(limite, aux);
            limite--;
        }
    }   
    
    /**
     *Retorna a atual carta do topo e faz com que a nova carta do
     * topo seja a carta anterior à carta retornada.
     * @return A carta do topo.
     */
    public Carta tiraCarta(){
        Carta temp = (Carta)cartas.get(cartaAtual);
        cartaAtual++;
        return temp;
    }
    
    /**
     * @return O array de cartas desse baralho.
     */
    public Array getCartas() {
        return cartas;
    }
    
    /**
     * @return Um inteiro que representa o índice da carta do topo.
     */
    public int getCartaAtual(){
        return cartaAtual;
    }
    
    /**
     * Verifica se um baralho está ordenado da mesma forma que este, percorrendo ambos e, caso encontre 
     * uma carta diferente, retorna false.
     * @param t Baralho a ser comparado.
     * @return True caso os baralhos sejam iguais, False caso contrário.
     */
    @Override
    public boolean equals(Object t){
        Baralho b = (Baralho)t;
        Array cartas2 = b.getCartas();
        for(int i=0; i<52; i++){
            if(!cartas.get(i).equals(cartas2.get(i))){
                return false;
            }
        }
        return true;
    }
}
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

/**
 * O Croupier funciona da mesma forma que um jogador, o que diferencia é a forma que
 *  a classe Partida o usa. Existem no croupier dois métodos que servem pra facilitar
 *  o processo de recuperar as cartas de sua mão.
 * @author Johnny, Gustavo
 */
public class Croupier extends Jogador{
    /**
     * @return A primeira carta da mão do croupier. 
     */
    Carta getPrimeiraCarta() {
        return mao.getCarta(0);
    }
    
    /**
     * @return A carta oculta do croupier. 
     */
    Carta getCartaOculta() {
        return mao.getCarta(1);
    }

}

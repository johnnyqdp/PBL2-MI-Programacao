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

package Controller;

import Model.Jogador;
import Model.Partida;

/**
 * Controller que chama métodos da classe Partida.
 * @author Johnny, Gustavo
 */
public class ControllerPartida {
    Partida partida=null;

    /**
     * Cria um objeto partida e chama o início dela, onde as cartas iniciais são distribuídas.
     * @see Model.Partida#inicio() 
     * @param jogadores Array de jogadores que serão utilizados na partida.
     */
    public void novaPartida(Jogador[] jogadores) {
        partida = new Partida(jogadores);
        partida.inicio();
    }

    /**
     * Retorna o método jaAcabou() da partida atual.
     * @see Model.Partida#jaAcabou() 
     * @return True caso a partida já tenha acabado, False caso contrário.
     */
    public boolean jaAcabou() {
        return partida.jaAcabou();
    }

    /**
     * Chama o método novaRodada() da partida atual.
     * @see Model.Partida#novaRodada(int) 
     * @param numeroRodada Número que representa a rodada atual. (Ex: 3ª Rodada... numeroRodada = 3)
     */
    public void novaRodada(int numeroRodada) {
        partida.novaRodada(numeroRodada);
    }

    /**
     * Chama o método verificaSeTodosTerminaram() da partida atual.
     * @see Model.Partida#verificaSeTodosTerminaram()
     */
    public void verificaSeTodosTerminaram() {
        partida.verificaSeTodosTerminaram();
    }
    
    /**
     * Retorna o método exibeResultado() da partida atual.
     * @see Model.Partida#exibeResultado() 
     * @return O jogador vencedor. (Null caso não haja vencedor).
     */
    public Jogador exibeResultado() {
        return partida.exibeResultado();
    }

    /**
     * Retorna o método querJogarNovamente() da partida atual.
     * @see Model.Partida#querJogarNovamente() 
     * @return True caso o jogador queira jogar novamente, False caso contrário
     */
    public boolean querJogarNovamente() {
        return partida.querJogarNovamente();
    }
    
}

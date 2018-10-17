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

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes da classe Partida.
 * @author Johnny, Gustavo
 */
public class PartidaTest {
    
    Partida partida; 
    Jogador jogadores[];
    
    /**
     * Cria um array de 3 jogadores cujos nomes e senhas são números de 0 a 2
     */
    @Before
    public void setUp() {
        jogadores = new Jogador[3];
        for(int i=0; i<jogadores.length; i++){
            jogadores[i] = new Jogador(Integer.toString(i), Integer.toString(i));
        }
    }
    
    /**
     * Verifica se os jogadores tem suas respectivas pontuações como zero. Em seguida, inicia uma partida
     * e espera-se que a pontuação de todos os jogadores seja maior que zero (já que receberam duas cartas cada).
     * Por ultima, zera a pontuação dos jogadores e espera-se que estes passem a ter suas pontuações iguais à
     * zero.
     */
    @Test
    public void testInicio() {
        for (Jogador jogador : jogadores) {
            assertEquals(jogador.getPontuacao(), 0);
        }
        
        partida = new Partida(jogadores);
        partida.inicio();
        
        for (Jogador jogador : jogadores) {
            assertNotEquals(jogador.getPontuacao(), 0);
        }
        
        partida.zeraJogadores(false, 0);
        
        for (Jogador jogador : jogadores) {
            assertEquals(jogador.getPontuacao(), 0);
        }
    }
    
}

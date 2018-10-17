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
 * Testes da classe Jogador.
 * @author Johnny, Gustavo
 */
public class JogadorTest {
    
    Jogador jogador;
    Carta as = new Carta();      
    Carta valete = new Carta();
    Carta dois = new Carta();    
    
    /**
     * Cria um jogador, uma carta ás, uma carta valete e uma carta dois.
     */
    @Before
    public void setUp() {
        jogador = new Jogador(); 
        as.setId(1);
        valete.setId(11);
        dois.setId(2);
    }    

    /**
     * Testa se as cartas estão sendo adicionadas e a pontuação é atribuída corretamente,
     * inclusive com o caso do ás, que deixa de valer 11 e passa a valer 1 quando é necessário.
     */
    @Test
    public void testAddCartaEGetPontuacao() {
        jogador.addCarta(as);        
        assertEquals(jogador.getPontuacao(), 11);
        
        jogador.addCarta(valete);        
        assertEquals(jogador.getPontuacao(), 21);
        
        jogador.addCarta(dois);
        assertNotEquals(jogador.getPontuacao(), 23);
        assertEquals(jogador.getPontuacao(), 13);
    }

    /**
     * Testa se o método zerar está funcionando adequadamente, criando um jogador com 21 pontos e verificando
     * se a sua pontuação geral e se a pontuação geral foi atualizada juntamente com a quantidade de vitórias.
     */
    @Test
    public void testZerar() {       
        jogador.addCarta(as);
        jogador.addCarta(valete);
        assertEquals(jogador.getPontuacao(), 21);
        assertEquals(jogador.getPontuacaoGeral(), 0);
        assertEquals(jogador.getQuantVitorias(),0);
        
        jogador.zerar(true);
        
        assertEquals(jogador.getPontuacao(), 0);
        assertEquals(jogador.getPontuacaoGeral(), 21);
        assertEquals(jogador.getQuantVitorias(), 1);
    }
    
    /**
     * Cria um jogador com 21 pontos, zera sua pontuação chamando um zerar(true), ou seja, informando que
     * esse jogador venceu a partida, portanto espera-se que seu score tenha 1 vitória e 21 pontos na pontuação
     * geral.
     */
    @Test
    public void testScore() {
        jogador.addCarta(as);
        jogador.addCarta(valete);
        jogador.zerar(true);
        assertEquals(jogador.score(), "null, 21 pontos, 1 vitórias.");
    }
    
    /**
     * Testa se o toString() está retornando o nome de usuário do jogador.
     */
    @Test
    public void testToString() {
        jogador = new Jogador("Johnny", "123");
        assertEquals(jogador.toString(), "Johnny");
    }

    /**
     * Testa o compareTo(), criando um jogador que possui menos pontos que um segundo e vendo se
     * a comparação entro os dois retorna -1.
     */
    @Test
    public void testCompareTo() {
        jogador = new Jogador();
        Jogador jogador2 = new Jogador();
        jogador.addCarta(as);
        jogador.addCarta(valete);
        jogador.zerar(true);
        
        jogador2.addCarta(valete);
        jogador2.addCarta(valete);
        jogador2.zerar(true);
        
        assertEquals(jogador.compareTo(jogador2), -1);
    }
    
    /**
     * Testa o método temAs(), adicionando um ás na mão do jogador e vendo se o método citado 
     * passa a retornar True.
     */
    @Test
    public void testTemAs(){
        assertFalse(jogador.TemAs());
        jogador.addCarta(as);
        assertTrue(jogador.TemAs());
    }

}

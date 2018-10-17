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

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Testes da classe Croupier.
 * @author Johnny, Gustavo
 */
public class CroupierTest {
    
    Croupier croupier;
    Carta as;
    Carta valete;
    
    /**
     * Cria um croupier, uma carta Ás de Espadas e uma carta Valete de Paus.
     */
    @Before
    public void setUp() {
        croupier = new Croupier();
        as = new Carta();
        as.setId(1);
        as.setNaipe(1);
        valete = new Carta();
        valete.setId(11);
        valete.setNaipe(3);
    }
    
    /**
     * Testa se os métodos getPrimeiraCarta() e getCartaOculta() estão retornando 
     * as cartas que devem.
     */
    @Test
    public void testBasico() {
        croupier.addCarta(as);
        croupier.addCarta(valete);
        assertEquals(croupier.getPrimeiraCarta().toString(), "Ás de Espadas");
        assertEquals(croupier.getCartaOculta().toString(), "Valete de Paus");
    }
    
}

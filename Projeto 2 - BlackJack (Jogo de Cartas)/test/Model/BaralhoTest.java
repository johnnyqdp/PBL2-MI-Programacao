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
 * Testes da classe Baralho.
 * @author Johnny, Gustavo
 */
public class BaralhoTest {
    Baralho baralho1;
    Baralho baralho2;
    
    /**
     * Cria dois baralhos.
     */
    @Before
    public void setUp() {
        baralho1 = new Baralho();
        baralho2 = new Baralho();
    }    

    /**
     * Teste simples do baralho, que verifica se os dois baralhos criados iniciam-se iguais. Em seguida,
     * embaralha ambos e verifica se eles estão diferentes.
     */
    @Test
    public void testBaralho() {        
        assertEquals(baralho1, baralho2);
        
        baralho1.embaralha();
        baralho2.embaralha();
        
        assertNotEquals(baralho1, baralho2);
    }
       
    
}

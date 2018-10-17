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
 * Testes da classe Carta.
 * @author Johnny, Gustavo
 */
public class CartaTest {
    
    Carta b;
    
    /**
     * Cria uma carta
     */
    @Before
    public void setUp() {
        b = new Carta();
    }    
    
    /**
     * Verifica se os valores de Id e Naipe estão sendo atribuídos corretamente ao testar o método
     * toString().
     */
    @Test
    public void testToString() {        
        b.setId(5);
        b.setNaipe(2);
        assertEquals(b.toString(), "5 de Copas");
        
        b.setId(4);
        b.setNaipe(1);
        assertEquals(b.toString(), "4 de Espadas");
        
        b.setId(12);
        b.setNaipe(4);
        assertEquals(b.toString(), "Rainha de Ouros");
        
        b.setId(1);
        b.setNaipe(3);
        assertEquals(b.toString(), "Ás de Paus");
        
        b.setId(13);
        b.setNaipe(1);
        assertEquals(b.toString(), "Rei de Espadas");
    }
    
}

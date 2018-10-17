/*****************************************************************************************
Autores: Johnny Quest Dantas Pereira, Gustavo dos Santos Menezes Alves
Componente Curricular: MI-Programação
Concluido em: 27/07/2018
Declaramos que este código foi elaborado por nós de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/
package Util;
/**
 * Teste de unidade que verifica a inserção e remoção na fila, de modo geral.
 * São inseridos 3 objetos, verificando  se estÃ¡ vazia ou nÃ£o, o elemento que estão no inicio da fila,
 * em seguidas esses objetos sÃ£o removidos  até a fila ficar vazia.
 * @author Johnny e Gustavo
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class FilaListaTest {
    
        FilaLista fila;
	Object data1, data2, data3;
        
        @Before
	public void setUp() throws Exception {
		fila = new FilaLista();
		data1 = "Data1";
		data2 = "Data2";
		data3 = "Data3";
	}
    
    
        @Test
        public void testGeral(){
            assertTrue(fila.isEmpty());
            fila.add(data1);
            fila.add(data2);
            fila.add(data3);
            assertFalse(fila.isEmpty());
            assertEquals(data1,fila.peek());
            fila.remove();
            assertEquals(data2,fila.peek());
            fila.remove();
            assertEquals(data3,fila.peek());
            fila.remove();
            assertTrue(fila.isEmpty());
            
  
        }

    
}

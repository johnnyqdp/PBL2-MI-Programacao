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

import java.util.Iterator;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
/**
 * Teste de unidade que verifica a inserção e remoção na lista encadeada, de modo geral.
 * São inseridos 3 objetos, verificando o tamanho da lista, se está vazia ou não.
 * Em seguidas esses objetos são removidos e o tamanho é verificado, até a lista ficar vazia.
 * Além disso, existem os testes do método  Contains, que verifica se os elementos inseridos estÃ£o presentes na lista,
 * o teste do método get, que retorna um elemento da lista em uma determinada posição e o teste do iterador percorrendo
 * a lista e verificando se tem próximo.
 * @author Johnny e Gustavo
 */

public class ListaEncadeadaTest {
        ListaEncadeada lista;
	Object data1, data2, data3;
        
        @Before
	public void setUp() throws Exception {
		lista = new ListaEncadeada();
		data1 = "Data1";
		data2 = "Data2";
		data3 = "Data3";
	}

        public void testGeral(){
            assertEquals(lista.isEmpty(),true);
            
            lista.add(data1);
            assertEquals(lista.isEmpty(), false);
            assertEquals(lista.size(),1);
            lista.add(data2);
            assertEquals(lista.isEmpty(), false);
            assertEquals(lista.size(),2);
            lista.add(data3);
            assertEquals(lista.isEmpty(), false);
            assertEquals(lista.size(),3);
            lista.remove(data1);
            assertEquals(lista.isEmpty(), false);
            assertEquals(lista.size(),2);
            lista.remove(data2);
            assertEquals(lista.isEmpty(), false);
            assertEquals(lista.size(),1);
            lista.remove(data3);
            assertEquals(lista.isEmpty(), true);
            
        }
        @Test
        public void testContains(){
            lista.add(data1);
            lista.add(data2);
            assertTrue(lista.contains(data1));
            assertTrue(lista.contains(data2));
            lista.remove(data1);
            lista.remove(data2);
            assertFalse(lista.contains(data1));
            assertFalse(lista.contains(data2));
            
            
        }
        
        @Test
        public void testGet(){
            lista.add(data1);
            lista.add(data2);
            lista.add(data3);
            assertEquals(data3, lista.get(0));
            assertEquals(data2, lista.get(1));
            assertEquals(data1, lista.get(2));
        }
        @Test
	public void testIterador() {
		Iterator it = lista.iterator();
		assertFalse(it.hasNext());
		
		lista.add(data1);
		lista.add(data2);
		lista.add(data3);
		it = lista.iterator();
		assertTrue(it.hasNext());
		assertEquals(data3, it.next());
		assertTrue(it.hasNext());
		assertEquals(data2, it.next());
		assertTrue(it.hasNext());
		assertEquals(data1, it.next());
		assertFalse(it.hasNext());
	}

}

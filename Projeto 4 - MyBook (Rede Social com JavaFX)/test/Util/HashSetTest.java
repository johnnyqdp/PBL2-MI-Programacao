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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
/**
 * Teste de unidade que verifica a inserção e remoção na hash, de modo geral.
 * São inseridos 3 objetos, verificando o tamanho da hash, se está vazia ou não e se contém os objetos solicitados.
 * Em seguidas esses objetos são removidos e o tamanho é verificado, até a hash ficar vazia.Além de um teste realizado
 * para o método get, verificando se um dado elemento está presente na hash e retornando-o.
 * @author Johnny e Gustavo
 */

public class HashSetTest {
    
        HashSet hash;
	Object data1, data2, data3;
        
        @Before
	public void setUp() throws Exception {
		hash = new HashSet();
		data1 = "Data1";
		data2 = "Data2";
		data3 = "Data3";
	}
        @Test
        public void testGeral(){
        assertTrue(hash.isEmpty());
        hash.put(data1);
        hash.put(data2);
        hash.put(data3);
        assertFalse(hash.isEmpty());
        assertEquals(3,hash.size());
        assertTrue(hash.contains(data1));
        assertTrue(hash.contains(data2));
        assertTrue(hash.contains(data3));
        hash.remove(data1);
        assertFalse(hash.isEmpty());
        assertEquals(2,hash.size());
        assertFalse(hash.contains(data1));
        hash.remove(data2);
        assertFalse(hash.isEmpty());
        assertEquals(1,hash.size());
        assertFalse(hash.contains(data2));
        hash.remove(data3);
        assertTrue(hash.isEmpty());
        assertEquals(0,hash.size());
        assertFalse(hash.contains(data2));
        }
        
        @Test
        public void testGet(){
            hash.put(data1);
            hash.put(data2);
            hash.put(data3);
            assertEquals(data3, hash.get(data3));
            assertEquals(data2, hash.get(data2));
            assertEquals(data1, hash.get(data1));
        }
        
        
    }
        


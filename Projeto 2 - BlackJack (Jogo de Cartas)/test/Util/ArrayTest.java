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

package Util;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes da classe Array
 * @author Johnny, Gustavo
 */
public class ArrayTest {
    
    Array array;
    
    @Before
    public void setUp() {
        array = new Array(2);
    }

    /**
     * Teste dos métodos Add, Get e Size da classe Array.
     * Adiciona 3 valores (em um array de tamanho inicial 2) para verificar se o tamanho dele é
     * duplicado como deveria.
     */
    @Test
    public void testAdd_Get_Size() {
        array.add(1);
        array.add(2);
        array.add(3);
        
        assertEquals(array.get(0), 1);
        assertEquals(array.get(0), 1);
        assertEquals(array.get(0), 1);
        
        assertEquals(array.size(), 3);
    }

    /**
     * Test of set method, of class Array.
     */
    @Test
    public void testSet() {
        array.set(4, 1);
        array.set(0,3);
        assertEquals(array.get(4), null);
        assertEquals(array.get(0), 3);
    }

    /**
     * Test of contains method, of class Array.
     */
    @Test
    public void testContains() {
        array.add(1);
        assertTrue(array.contains(1));
        assertFalse(array.contains(2));
    }

    /**
     * Test of remove method, of class Array.
     */
    @Test
    public void testRemove_Object() {
        array.add("test");
        assertTrue(array.contains("test"));
        array.remove("test");
        assertFalse(array.contains('a'));
    }

    /**
     * Test of remove method, of class Array.
     */
    @Test
    public void testRemove_int() {
        array.add(1);
        array.add(2);
        array.add(3);
        assertTrue(array.contains(2));
        array.remove(1);
        assertFalse(array.contains(2));
    }

    /**
     * Test of isEmpty method, of class Array.
     */
    @Test
    public void testIsEmpty() {
        array.add(1);
        assertFalse(array.isEmpty());
        array.remove(0);
        assertTrue(array.isEmpty());
    }

    /**
     * Test of selectionSort method, of class Array.
     */
    @Test
    public void testSelectionSort() {
        Array arrayOrdenado = new Array(4);
        for(int i=1; i<=4; i++){
            arrayOrdenado.add(i);
        }
        
        array.add(2);
        array.add(4);
        array.add(1);
        array.add(3);
        
        assertNotEquals(arrayOrdenado, array);
        array.selectionSort();
        assertEquals(arrayOrdenado, array);
    }
    
}

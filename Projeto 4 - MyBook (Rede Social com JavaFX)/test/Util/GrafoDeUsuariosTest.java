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
 * Teste de unidade do grafo de usuarios, a estrutura de dados principal do programa, que recebe auxilio de todas as outras.
 * Existe um teste geral de inserção, remoção e verificação se o usuario existe no grafo.
 * Assim como, testes dos outros métodos  pertecentes ao grafo, que são explicados acima de cada um deles.
 * @autor Johnny e Gustavo.
 */
import Model.User;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class GrafoDeUsuariosTest {

    GrafoDeUsuarios grafo;
    User user1, user2, user3;

    @Before
    public void setUp() throws Exception {
        grafo = new GrafoDeUsuarios();
        user1 = new User("Gustavo", null, "Gustavo", null, null, null, null);
        user2 = new User("Johnny", null, "Johnny", null, null, null, null);
        user3 = new User("Firmino", null, "Firmino", null, null, null, null);
    }
    @Test
    public void testGeral() {

        grafo.add(user1);
        grafo.add(user2);
        grafo.add(user3);
        assertTrue(grafo.contains(user1));
        assertTrue(grafo.contains(user2));
        assertTrue(grafo.contains(user3));
        grafo.remove(user1);
        assertFalse(grafo.contains(user1));
        grafo.remove(user2);
        assertFalse(grafo.contains(user2));
        grafo.remove(user3);
        assertFalse(grafo.contains(user3));

    }
    /**
     * Teste que dados dois usuários, gera amizade entre eles e em seguida verifica, se realmente estão ou não
     * sendo adicionados como amigos, ou seja, se foi criada uma relação de amizade entre eles.
     *  @autor Johnny e Gustavo.
    */
    
    @Test
    public void testGerarAmizade() {
        grafo.gerarAmizade(user1, user2);
        assertTrue(grafo.saoAmigos(user1, user2));
        assertFalse(grafo.saoAmigos(user1, user3));
        grafo.gerarAmizade(user1, user3);
        assertTrue(grafo.saoAmigos(user1, user3));
    }
      /**
     * Teste que dados dois usuários, gera amizade entre eles e em seguida verifica se são amigos realmente.
     *  @autor Johnny e Gustavo.
    */  
    @Test
    public void testSaoAmigos(){
        grafo.gerarAmizade(user1, user2);
        grafo.saoAmigos(user1, user1);
        assertTrue(user1.contains(user2));
        
        
    }
    /**
     * Método que através de um username, retorna o usuário. 
     * Então o teste foi elaborado com a inserção de user, e em seguida verificando 
     * se através do método get, retornaria o mesmo, a partir do username.
     * @autor Johnny e Gustavo.
     */
    @Test
    public void testGetUser() {
        grafo.add(user1);
        assertEquals(grafo.getUser("Gustavo"),user1);

    }
    /**
     * Teste que gera uma amizade entre dois usuários e verifica se o "getAmigos" retorna o usuário cuja amizade
     * acabou de ser estabelecida.
     */
    @Test
    public void testGetAmigos(){
        grafo.add(user1);
        grafo.add(user2);
        grafo.gerarAmizade(user1, user2);
        User[] u = grafo.getAmigos(user1);
        assertEquals(u[0], user2);
        u = grafo.getAmigos(user2);
        assertEquals(u[0], user1);       
    }
    
    /**
     * Verifica se o algoritmo de busca está funcionando, simulando uma busca a partir de um usuário, verificando se a busca
     * retorna os outros 2.
     */
    @Test
    public void testBusca(){
        grafo.add(user1);
        grafo.add(user2);
        grafo.add(user3);
        ListaEncadeada l = grafo.busca(user1, "o");
        assertTrue(l.contains(user2));
        assertTrue(l.contains(user3));
        assertFalse(l.contains(user1));
    }

}

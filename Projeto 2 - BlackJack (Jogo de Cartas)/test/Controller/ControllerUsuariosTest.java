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

package Controller;

import Model.Jogador;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testes da classe ControllerUsuarios.
 * @author Johnny, Gustavo
 */
public class ControllerUsuariosTest {
    
    ControllerUsuarios controllerUsuarios;
    Jogador jogador;
    
    /**
     * Cria um controllerUsuarios e um jogador chamado "Teste_Supremo"
     */
    @Before
    public void setUp() {
        controllerUsuarios = new ControllerUsuarios();
        jogador = new Jogador("Teste_Supremo", "123");
    }
    
    /**
     * Verifica se os jogadores estão sendo salvos adequadamente pelo controller.
     */
    @Test
    public void testSalvarJogador() {
        File file = new File("players");
        assertTrue(file.exists());
        controllerUsuarios.salvarJogador(jogador);
        file = new File("players/Teste_Supremo.player");
        assertTrue(file.exists());
        Jogador temp = null;
        try{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            temp = (Jogador)ois.readObject();
        }catch(Exception ex){
            System.out.println(ex);
        }
        assertEquals(jogador, temp);
    }
    
}

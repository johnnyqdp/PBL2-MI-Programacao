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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Testes da classe PlacarGeral.
 * @author Johnny, Gustavo
 */
public class PlacarGeralTest {
    
    PlacarGeral placar;
    Jogador j1 = new Jogador("Jogador1", "123");
    Jogador j2 = new Jogador("Jogador2", "123");
    Jogador j3 = new Jogador("Jogador3", "123");
    Carta dez = new Carta();
    Carta nove = new Carta();
    
    /**
     * Chama o método criaPastaTeste().
     * @see Model.PlacarGeralTest#criaPastaTeste() 
     */
    @BeforeClass
    public static void setUpClass() {
        criaPastaTeste();        
    }
    
    /**
     * Cria as cartas nove e dez, em seguida cria três jogadores, um com um dez e um nove (19 pontos), um com
     * dois dez (20 pontos) e outro com um nove (9 pontos). Em seguida, salva os três jogadores em arquivos 
     * da pasta testPlayers através do método salvaJogador().
     */
    @Before
    public void setUp() {
        nove.setId(9);
        dez.setId(10);
        
        j1.addCarta(dez);
        j1.addCarta(nove);
        j1.zerar(true);
        
        j2.addCarta(dez);
        j2.addCarta(dez);
        j2.zerar(true);
        
        j3.addCarta(nove);
        j3.zerar(true);
        
        salvaJogador(j1);
        salvaJogador(j2);
        salvaJogador(j3);
        placar = new PlacarGeral("testPlayers");        
    }

    /**
     * Testa o método salvarPlacar, verificando se o mesmo está "pegando" todos os jogadores da pasta
     * informada e imprimindo suas pontuações no padrão correto e ordenando-os do maior pro menor.
     */
    @Test
    public void testSalvarPlacar() {
        placar.SalvarPlacar(false);
        String linha1 = "";
        String linha2 = "";
        String linha3 = "";
        
        try{
            FileReader score = new FileReader("score.txt");
            BufferedReader reader = new BufferedReader(score);
            linha1 = reader.readLine();
            linha2 = reader.readLine();
            linha3 = reader.readLine();
            reader.close();
            score.close();
        }catch(Exception ex){
            System.out.println(ex);
        }
        
        assertEquals(linha1, "Jogador2, 20 pontos, 1 vitórias.");
        assertEquals(linha2, "Jogador1, 19 pontos, 1 vitórias.");
        assertEquals(linha3, "Jogador3, 9 pontos, 1 vitórias.");
    }
    
    /**
     * Método interno utilizado no setUpClass(). Cria uma pasta chamada testPlayers.
     */
    private static void criaPastaTeste() {
        File file = new File("testplayers");
        
        try {
            file.mkdir();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Salva um jogador recebido em um arquivo .player na pasta testPlayers
     * @param j Jogador a ser salvo
     */
    private void salvaJogador(Jogador j) {
        try {
            FileOutputStream fos = new FileOutputStream("testplayers/" + j.getNomeUsuario() + ".player");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(j);
            oos.close();
            fos.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }      
    }
    
}

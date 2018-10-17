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

import Util.Array;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.PrintWriter;

/**
 * Classe responsável por gerar o arquivo score.txt, com as pontuações de todos os jogadores (ordenados do maior
 *  para o menor) e suas respectivas quantidades de vitórias.
 * @author Johnny, Gustavo
 */
public class PlacarGeral {
    File pasta;
    File arrayDeArquivos[];
    Array arrayDeJogadores = new Array(1);
    File score;
    Jogador temp;
    FileWriter fileWriter;
    FileReader leitor;   
    BufferedWriter escritor;
    
    /**
     * O construtor recebe o nome da pasta onde se encontram os arquivos .player (jogadores cadastrados) e
     * adiciona todos os jogadores dessa pasta em um Array dinâmico (que dobra de tamanho automaticamente caso
     * exceda o seu limite determinado.
     * Como o arquivo score.txt sempre será gerado do zero, inicialmente essa classe irá apagar tudo o que já
     * está escrito no arquivo score.txt, pois tudo aquilo será substituido por uma nova lista de jogadores ordenada
     * por pontuação geral.
     * @param nomeDaPasta O diretório onde se encontram os arquivos .player a serem lidos
     */
    public PlacarGeral(String nomeDaPasta){
        File pasta = new File(nomeDaPasta);
        arrayDeArquivos = pasta.listFiles();
        score = new File("score.txt");        
        limparScore();
        try {
            score.createNewFile();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        for(File x : arrayDeArquivos){
            try {
                FileInputStream arq2 = new FileInputStream(x);
                ObjectInputStream pessoaLida = new ObjectInputStream(arq2);
                temp = (Jogador)pessoaLida.readObject();
                arrayDeJogadores.add(temp);
                arq2.close();
                pessoaLida.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }           
        }
    }
    
    /**
     * Gera o arquivo score.txt com as pontuações de todos os jogadores e suas quantidades de vitórias. Tudo isso
     *  ordenado por pontuação. É possível também imprimir o score na tela de comandos, caso seja passado True como
     *  parâmetro.
     * @param querImprimir True caso queira imprimir o score na tela, False caso contrário. 
     */
    public void SalvarPlacar(boolean querImprimir){
        if(arrayDeJogadores.isEmpty()){
            System.out.println("Não há nenhum jogador cadastrado, não foi possível gerar um placar.");
        }else{
            arrayDeJogadores.selectionSort();  
            try{
                for(int i=0; i<arrayDeJogadores.size(); i++){
                    fileWriter = new FileWriter(score, true);
                    escritor = new BufferedWriter(fileWriter);
                    temp = (Jogador)arrayDeJogadores.get(i);                
                    escritor.write(temp.score());
                    escritor.newLine();   
                    if(querImprimir){
                        System.out.println(temp.score());
                    }
                    fileWriter.flush();
                    escritor.flush();
                }
            }catch(Exception ex){
                System.out.println(ex);
            }
            
            try{
                fileWriter.close();
                escritor.close();
            }catch(Exception ex){
                System.out.println(ex);
            }
        }
    }
    
    /**
     * Apaga tudo o que estiver escrito no arquivo score.txt.
     */
    private void limparScore(){
        PrintWriter pw;
        try {
            pw = new PrintWriter(score);
            pw.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
}

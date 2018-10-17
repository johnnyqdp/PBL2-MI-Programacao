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

package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Date;
import java.util.Scanner;
/**
 * Essa classe representa uma postagem, que pode ser feita por um usuário.
 * @author Johnny e Gustavo
 */
public class Postagem implements Serializable{
    
    private final User postador; //Referência ao usuario postador
    private boolean temArquivo; //True caso o usuário tenha enviado algum arquivo na postagem
    private File arquivo; //Referência para o arquivo enviado pelo usuário (que foi copiado para a pasta do programa)
    private final String texto; //A mensagem enviada pelo usuário na postagem.
    private final String titulo; //Titulo da postagem
    private final Date data; //Data da postagem

    /**
     * Cria uma nova postagem.
     * @param diretorio
     * @param postador
     * @param temArquivo
     * @throws FileNotFoundException 
     */
    public Postagem(File diretorio, User postador, boolean temArquivo) throws FileNotFoundException {
        this.postador = postador;
        titulo = diretorio.getName();
        texto = new Scanner(new File(diretorio.getPath() + "/TextoDaPostagem.txt")).useDelimiter("\\Z").next();
        this.temArquivo = temArquivo;
        if(temArquivo){
            File[] arquivosDaPasta = diretorio.listFiles();
            for(File f : arquivosDaPasta){
                if(!f.getName().equals("TextoDaPostagem.txt")){
                    this.arquivo = f;
                    break;
                }
            }
        }
        data = new Date(diretorio.lastModified());
    }

    /**
     * Retorna o usuário que postou.
     * @return 
     */
    public User getPostador() {
        return postador;
    }

    /**
     * Retorna true caso tenha algum arquivo anexado à postagem
     * @return 
     */
    public boolean temArquivo() {
        return temArquivo;
    }

    /**
     * Retorna o arquivo anexado à postagem.
     * @return 
     */
    public File getArquivo() {
        return arquivo;
    }

    /**
     * Retorna o texto da postagem
     * @return 
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Retorna o título da postagem
     * @return 
     */
    public String getTitulo() {
        return titulo;
    }   

    /**
     * Retorna a data de postagem
     * @return 
     */
    public Date getData() {
        return this.data;
    }
    
}

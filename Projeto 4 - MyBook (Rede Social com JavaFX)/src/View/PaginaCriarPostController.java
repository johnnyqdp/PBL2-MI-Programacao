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

package View;

import Model.Postagem;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Controller da cena de criar postagem
 * @author Johnny e Gustavo
 */
public class PaginaCriarPostController implements Initializable{   

    @FXML TextArea textArea;
    @FXML TextArea textArea2;
    @FXML Label labelArquivo;
    @FXML Label erro;
    File arquivo;
    
    /**
     * Faz com que o texto inserido fique "pulando linhas" ao atingir a extremidade.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textArea2.setWrapText(true);
    }
    
    /**
     * Modifica a cena para a cena do feed de noticias.
     * @param event
     * @throws IOException 
     */
    public void apertaBotaoSair(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene buscaScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaFeed.fxml")));
        primaryStage.setScene(buscaScene);
    }
    
    /**
     * Adiciona um arquivo, que posteriormente será adicionado na postagem.
     */
    public void apertaBotaoAnexar(){
        FileChooser fc = new FileChooser();
        arquivo = fc.showOpenDialog(null);
        labelArquivo.setText(arquivo.getName());
    }
    
    /**
     * Gera uma postagem a partir das informações enviadas pelo usuário.
     * @param event
     * @throws IOException 
     */
    public void apertaBotaoEnviar(ActionEvent event) throws IOException{
        String titulo = textArea.getText();
        String mensagem = textArea2.getText();
        
        if(!titulo.isEmpty() && !mensagem.isEmpty()){
            File diretorio = new File("src/postagens/" + GuardaUsuarios.getAtual().getUsername() + "/" + titulo);
            if(diretorio.exists()){
                erro.setText("ERRO: Você já criou uma postagem \ncom este título.");
            }else{
                diretorio.mkdirs(); //Cria pasta da postagem
                if(arquivo!=null && !arquivo.getName().equals("TextoDaPostagem.txt")) //Se tiver arquivo, copia ele pra pasta da postagem
                    Files.copy(arquivo.toPath(), new File(diretorio.getPath() + "/" + arquivo.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                PrintWriter escreveDescricao = new PrintWriter(new File(diretorio.getPath() + "/" + "TextoDaPostagem.txt"));
                escreveDescricao.write(mensagem);
                escreveDescricao.close();
                
                GuardaUsuarios.getController().addPostagem(GuardaUsuarios.getAtual(), new Postagem(diretorio, GuardaUsuarios.getAtual(), arquivo != null));
            }
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene buscaScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaFeed.fxml")));
            primaryStage.setScene(buscaScene);
        }
                
    }    
}
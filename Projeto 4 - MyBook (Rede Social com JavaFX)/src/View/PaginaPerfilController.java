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

import Model.User;
import java.io.File;
import java.io.IOException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * Controller da cena que representa o perfil do usuário logado.
 * @author Johnny e Gustavo
 */
public class PaginaPerfilController implements Initializable{   

    @FXML ImageView fotoUsuario;
    @FXML Label username;
    @FXML Label nome;
    @FXML Label email;
    @FXML Label data;
    @FXML Label endereco;    
    @FXML Label telefone;
    
    /**
     * Pega todas as informações do usuário e adiciona na cena.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User atual = GuardaUsuarios.getAtual();
        username.setText("Username: " + atual.getUsername());
        nome.setText(atual.getNome());
        email.setText("Email: " + atual.getEmail());
        data.setText("Nascido em " + atual.getNascimento());
        endereco.setText("Mora em " + atual.getEndereço());
        telefone.setText("Contato: " + atual.getTelefone());
        if(atual.temFoto()){
            File x = new File(atual.getFoto());
            fotoUsuario.setImage(new Image(x.toURI().toString()));
            fotoUsuario.setFitHeight(180);
            fotoUsuario.setFitWidth(180);
        }
    }
    
    /**
     * Modifica a cena para a cena da pagina de busca.
     * @param event
     * @throws IOException 
     */
    public void apertaBotaoBusca(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene buscaScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaBusca.fxml")));
        primaryStage.setScene(buscaScene);
    }
    
    /**
     * Modifica a cena para a cena do feed de noticias
     * @param event
     * @throws IOException 
     */
    public void apertaBotaoFeed(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene feedScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaFeed.fxml")));
        primaryStage.setScene(feedScene);
    }
    
    /**
     * Modifica a cena para a cena da lista de postagens do usuário
     * @param event
     * @throws IOException 
     */
    public void apertaBotaoVerPostagens(ActionEvent event) throws IOException{
        GuardaUsuarios.setUsuarioParaVerLista(GuardaUsuarios.getAtual());
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene listaPostsScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaListaPostagens.fxml")));
        primaryStage.setScene(listaPostsScene);
    }
    
    /**
     * Modifica a cena para a cena da lista de amigos.
     * @param event
     * @throws IOException 
     */
    public void apertaBotaoVerAmigos(ActionEvent event) throws IOException{
        GuardaUsuarios.setUsuarioParaVerLista(GuardaUsuarios.getAtual());
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene listaAmigosScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaListaAmigos.fxml")));
        primaryStage.setScene(listaAmigosScene);
    }
    
    /**
     * Processo de modificação da foto de perfil.
     * @throws IOException 
     */
    public void apertaFoto() throws IOException{
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Arquivos de Imagem", "*.png", "*.jpg"));
        File arquivo = fc.showOpenDialog(null);
        File arquivo2;
        if(arquivo.getName().endsWith(".png")){
            arquivo2 = new File("src/fotosDePerfil/" + GuardaUsuarios.getAtual().getUsername() + ".png");
            GuardaUsuarios.getAtual().setFoto("src/fotosDePerfil/" + GuardaUsuarios.getAtual().getUsername() + ".png");
        }else{
            arquivo2 = new File("src/fotosDePerfil/" + GuardaUsuarios.getAtual().getUsername() + ".jpg");
            GuardaUsuarios.getAtual().setFoto("src/fotosDePerfil/" + GuardaUsuarios.getAtual().getUsername() + ".jpg");
        }
        
        Files.copy(arquivo.toPath(), arquivo2.toPath(), StandardCopyOption.REPLACE_EXISTING);        
        
        fotoUsuario.setFitHeight(180);
        fotoUsuario.setFitWidth(180);
        fotoUsuario.setImage(new Image(arquivo2.toURI().toString()));
        GuardaUsuarios.getController().salvaGrafo();        
    }
    
}

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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Controller da cena de visita ao perfil de algum usuário
 * @author Johnny e Gustavo
 */
public class PaginaVisitaController implements Initializable{   

    @FXML ImageView fotoUsuario;
    @FXML Label username;
    @FXML Label nome;
    @FXML Label email;
    @FXML Label data;
    @FXML Label endereco;    
    @FXML Label telefone;
    @FXML HBox praAdd;
    
    /**
     * Pega todas as informações do usuário e adiciona na cena.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {        
        User visitado = GuardaUsuarios.getVisitado();
        username.setText("Username: " + visitado.getUsername());
        nome.setText(visitado.getNome());
        email.setText("Email: " + visitado.getEmail());
        data.setText("Nascido em " + visitado.getNascimento());
        endereco.setText("Mora em " + visitado.getEndereço());
        telefone.setText("Contato: " + visitado.getTelefone());
        //Colocando foto do usuario:
        if(visitado.temFoto()){
            File x = new File(visitado.getFoto());
            fotoUsuario.setImage(new Image(x.toURI().toString()));
            fotoUsuario.setFitHeight(180);
            fotoUsuario.setFitWidth(180);
        }
        //Definindo se o botão de "adicionar" deve aparecer ou não:
        if(!GuardaUsuarios.getController().saoAmigos(visitado, GuardaUsuarios.getAtual()) && 
                !visitado.equals(GuardaUsuarios.getAtual())){ //Se não são amigos e se ele não tá visitando ele mesmo...           
            Button botaoAdd = new Button("Add");
            botaoAdd.setOnAction(e -> {
                GuardaUsuarios.getController().enviarSolicitacao(GuardaUsuarios.getAtual(), visitado);
                botaoAdd.setVisible(false);
            });            
            praAdd.getChildren().add(botaoAdd);            
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
     * Modifica a cena para a cena do feed de noticias.
     * @param event
     * @throws IOException 
     */
    public void apertaBotaoFeed(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene feedScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaFeed.fxml")));
        primaryStage.setScene(feedScene);
    }
    
    /**
     * Modifica a cena para a cena da pagina de ver postagens do usuário visitado.
     * @param event
     * @throws IOException 
     */
    public void apertaBotaoVerPostagens(ActionEvent event) throws IOException{
        GuardaUsuarios.setUsuarioParaVerLista(GuardaUsuarios.getVisitado());
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene listaPostsScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaListaPostagens.fxml")));
        primaryStage.setScene(listaPostsScene);
    }
    
    /**
     * Modifica a cena para a cena da pagina da lista de amigos do usuario.
     * @param event
     * @throws IOException 
     */
    public void apertaBotaoVerAmigos(ActionEvent event) throws IOException{
        GuardaUsuarios.setUsuarioParaVerLista(GuardaUsuarios.getVisitado());
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene feedScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaListaAmigos.fxml")));
        primaryStage.setScene(feedScene);
    }
    
}

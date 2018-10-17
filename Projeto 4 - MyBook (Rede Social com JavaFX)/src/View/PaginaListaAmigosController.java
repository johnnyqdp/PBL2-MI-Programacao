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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Controller da página da lista de amigos de um usuário
 * @author Johnny e Gustavo
 */
public class PaginaListaAmigosController implements Initializable{   

    @FXML ListView listView;
    
    /**
     * Carrega os amigos do usuário solicitado e os adiciona no listView.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setCellFactory(param -> new Celula());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        User[] result = GuardaUsuarios.getController().getAmigos(GuardaUsuarios.getUsuarioParaVerLista());        
        ObservableList resultObservable = FXCollections.observableArrayList(result);
        listView.setItems(resultObservable);        
    }
    
    /**
     * Ao apertar em algum usuário, carrega a página de visita do mesmo.
     * @throws IOException 
     */
    public void apertaAlgumUsuario() throws IOException {
        Stage primaryStage = (Stage)listView.getScene().getWindow();
        GuardaUsuarios.setVisitado((User)listView.getSelectionModel().getSelectedItem());
        Scene visitadoScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaVisita.fxml")));
        primaryStage.setScene(visitadoScene);
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
     * Classe que representa uma célula da lista de amigos (que são Users)
     */
    public class Celula extends ListCell<User>{    
        @Override
	protected void updateItem(User u, boolean empty) {            
            if (empty) {
                setText(null);
                setGraphic(null);
            }else{
                super.updateItem(u, empty);
                Image foto;
                File x;
                if(u.temFoto()){
                    x = new File(u.getFoto());
                    foto = new Image(x.toURI().toString());
                }else{
                    x = new File("src/arquivosdainterface/semFoto.jpg");
                    foto = new Image(x.toURI().toString());
                }
                ImageView fotoView = new ImageView(foto);
                fotoView.setFitHeight(40);
                fotoView.setFitWidth(40);

                Label nome = new Label(u.getNome());
                nome.setFont(new Font("Arial Bold", 12));
                Label endereco = new Label("de " + u.getEndereço());
                VBox vbox = new VBox();
                vbox.getChildren().addAll(nome, endereco);

                HBox hbox = new HBox();
                hbox.getChildren().addAll(fotoView, vbox);            
                hbox.setSpacing(10);            

                setText(null);
                setGraphic(hbox);
            }
        }    
    }
}

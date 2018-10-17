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

import Controller.ControllerUsuarios;
import Model.User;
import Util.ListaEncadeada;
import java.io.File;
import javafx.scene.text.Font;
import java.io.IOException;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller da página de busca por usuários.
 * @author Johnny e Gustavo
 */
public class PaginaBuscaController {
    
    @FXML ListView listView;
    @FXML TextField campoBusca;    
    Stage primaryStage;
    
    /**
     * Ao apertar o botão "buscar", o programa irá exibir no listView uma lista de usuários correspondentes à busca feita
     * no textField.
     * @param event 
     */
    public void apertaBotaoBusca(ActionEvent event){
        if(campoBusca.getCharacters().length() != 0){
            primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            listView.setCellFactory(param -> new Celula());
            listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            User[] result = carregaResultados(campoBusca.getCharacters().toString());
            ObservableList resultObservable = FXCollections.observableArrayList(result);
            listView.setItems(resultObservable);
        }
    }  

    /**
     * Ao apertar em algum usuário resultado, o programa irá para a a pagina do perfil do mesmo.
     * @throws IOException 
     */
    public void apertaAlgumUsuario() throws IOException{
        GuardaUsuarios.setVisitado((User)listView.getSelectionModel().getSelectedItem());        
        Scene visitadoScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaVisita.fxml")));
        primaryStage.setScene(visitadoScene);
    }
    
    /**
     * Chama um método do controller que retorna o resultado de uma busca em listaencadeada, transforma esa lista
     * em um array para poder ser convertido em ObservableList depois.
     * @param palavraBuscada
     * @return 
     */
    private User[] carregaResultados(String palavraBuscada) {
        ListaEncadeada listResult = new ControllerUsuarios().busca(GuardaUsuarios.getAtual(), palavraBuscada);
        User[] result = new User[listResult.size()];
        
        Iterator it = listResult.iterator();
        for(int i=0; it.hasNext(); i++){
            result[i] = (User)it.next();
        }
        
        return result;
    }   
    
    /**
     * Modifica a cena para a cena do feed de noticias.
     * @param event
     * @throws IOException 
     */
    public void apertaBotaoFeed(ActionEvent event) throws IOException{
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene feedScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaFeed.fxml")));
        primaryStage.setScene(feedScene);
    }
    
    /**
     * Modifica a cena para a cena de perfil do usuário
     * @param event
     * @throws IOException 
     */
    public void apertaBotaoPerfil(ActionEvent event) throws IOException{
        primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene perfilScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaPerfil.fxml")));
        primaryStage.setScene(perfilScene);
    }
    
    /**
     * Classe que representa uma célula do listView.
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

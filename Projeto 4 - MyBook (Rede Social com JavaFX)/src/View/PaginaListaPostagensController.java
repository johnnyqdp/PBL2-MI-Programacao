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
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * Controller da cena da lista de postagens de um usuário específico.
 * @author Johnny e Gustavo
 */
public class PaginaListaPostagensController implements Initializable{   

    @FXML ListView listView;
    
    /**
     * Chama o controller principal para carregar todas as postagens do usuário solicitado, e adiciona elas
     * em um listView.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.setCellFactory(param -> new Celula());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Postagem[] result = GuardaUsuarios.getController().getPostagens(GuardaUsuarios.getUsuarioParaVerLista());        
        ObservableList resultObservable = FXCollections.observableArrayList(result);
        listView.setItems(resultObservable);        
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
     * Classe que representa uma célula da lista de postagens.
     */
    public class Celula extends ListCell<Postagem>{    
        @Override
	protected void updateItem(Postagem p, boolean empty) {            
            if (empty) {
                setText(null);
                setGraphic(null);
            }else{
                if(p==null)
                    return;
                
                super.updateItem(p, empty);
                
                Image foto;
                File x;
                if(p.getPostador().temFoto()){
                    x = new File(p.getPostador().getFoto());
                    foto = new Image(x.toURI().toString());
                }else{
                    x = new File("src/arquivosdainterface/semFoto.jpg");
                    foto = new Image(x.toURI().toString());
                }
                ImageView fotoView = new ImageView(foto);                
                fotoView.setFitHeight(50);
                fotoView.setFitWidth(50);
                
                Label nome = new Label(p.getPostador().getNome());
                nome.setFont(new Font("Arial Bold", 12));
                Label data = new Label(p.getData().toLocaleString());
                Label titulo = new Label("Titulo: " + p.getTitulo());
                Label texto = new Label(p.getTexto());
                texto.setWrapText(true);
                texto.setPrefWidth(210);
                
                VBox vbox1 = new VBox();
                vbox1.getChildren().addAll(nome, data, titulo);
                HBox hbox2 = new HBox();
                hbox2.getChildren().addAll(fotoView, vbox1);
                hbox2.setSpacing(20);      
                
                VBox vboxGeral = new VBox();
                vboxGeral.setAlignment(Pos.CENTER);
                
                if(p.temArquivo()){ //Definindo o que terá na célula, caso tenha algum arquivo na postagem.
                    File arquivo = p.getArquivo();
                    Button baixar = new Button("Baixar este arquivo");
                    baixar.setOnAction(e -> {
                        DirectoryChooser dc = new DirectoryChooser();
                        File pasta = dc.showDialog(null);
                        try {
                            Files.copy(arquivo.toPath(), pasta.toPath().resolve(arquivo.getName()));
                        } catch (IOException ex) {}
                    });
                    
                    if(arquivo.getName().endsWith(".jpg") || arquivo.getName().endsWith(".png")){
                        ImageView imagemPostada = new ImageView(new Image(arquivo.toURI().toString()));
                        imagemPostada.setFitHeight(170);
                        imagemPostada.setFitWidth(170);
                        vboxGeral.getChildren().addAll(hbox2, texto, imagemPostada, baixar);
                    }else{
                        vboxGeral.getChildren().addAll(hbox2, texto, new Label(arquivo.getName()), baixar);
                    }
                }else
                    vboxGeral.getChildren().addAll(hbox2, texto);                

                setText(null);
                setGraphic(vboxGeral);
            }
        }    
    }
}

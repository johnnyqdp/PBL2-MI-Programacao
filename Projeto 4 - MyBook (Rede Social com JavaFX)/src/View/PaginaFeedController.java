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
import Model.User;
import Util.ListaEncadeada;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Iterator;
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
 * Controller da página que possui o feed de noticias
 * @author Johnny e Gustavo
 */
public class PaginaFeedController implements Initializable{
    
    @FXML HBox solicitacoes;
    @FXML ListView feed;
    ListView listPedidos;
    int contagemPosts;
    
    /**
     * Verifica se o usuario logado possui alguma solicitação de amizade, se tiver, pega todas as solicitações e exibe
     * num listview, se não tiver, exibe uma mensagem. Alémd isso, chama o método responsável por gerar o feed de notícias
     * 
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Primeiro, ele vai criar a lista de solicitações de amizade:
        if(GuardaUsuarios.getAtual().temSolicitacoes()){
            //Adicionando a lista de solicitações em um array pra poder criar um ObservableList:
            ListaEncadeada l = GuardaUsuarios.getAtual().getSolicitacoes();
            User[] solicitacoes = new User[l.size()];
            Iterator it = l.iterator();
            for(int i=0; it.hasNext(); i++)
                solicitacoes[i] = (User)it.next();
            //Criando o listPedidos:
            listPedidos = new ListView();
            listPedidos.setCellFactory(param -> new Celula());
            listPedidos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);            
            ObservableList resultObservable = FXCollections.observableArrayList(solicitacoes);
            listPedidos.setItems(resultObservable);
            this.solicitacoes.getChildren().add(listPedidos);            
        }else{
            solicitacoes.getChildren().add(new Label("Você não tem nenhuma solicitação de amizade."));
        }
        
        //Em seguida, o feed de notícias:
        gerarFeed();        
    }
    
    /**
     * "Carrega" as postagens anteriores de cada um dos amigos, por exemplo, se estava carregando as postagens 0 (mais recentes)
     * Irá carregar as postagens do indice 1 (segundas mais recentes)
     */
    public void apertaBotaoVerMaisPosts(){
        contagemPosts++;
        gerarFeed();
    }
    
    /**
     * Carrega as postagens mais recentes de cada um dos amigos, por exemplo, se estiver carregando as postagens 1 (segundas
     * mais recentes), carregará as postagens 0 (mais recentes)
     */
    public void apertaBotaoVoltarPosts(){
        if(contagemPosts > 0)
            contagemPosts--;
        gerarFeed();
    }
    
    /**
     * Modifica a cena para a cena de busca de usuários.
     * @param event
     * @throws IOException 
     */
    public void apertaBotaoBusca(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene buscaScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaBusca.fxml")));
        primaryStage.setScene(buscaScene);
    }
    
    /**
     * Modifica a cena para a cena do perfil de usuário.
     * @param event
     * @throws IOException 
     */
    public void apertaBotaoPerfil(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene perfilScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaPerfil.fxml")));
        primaryStage.setScene(perfilScene);
    }
    
    /**
     * Modifica a cena para a cena de criar uma postagem.
     * @param event
     * @throws IOException 
     */
    public void apertaBotaoPostagem(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene criarPostScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaCriarPost.fxml")));
        primaryStage.setScene(criarPostScene);
    }

    /**
     * Solicita ao controller a lista de postagens dos amigos do usuário atual, utilizando o nível que está armazenado
     * na variável "contagemPosts", sendo que, se for zero, carregará as mais recentes, se for 1, as segundas mais
     * recentes, e assim por diante.
     */
    private void gerarFeed() {
        Postagem[] postsFeed = GuardaUsuarios.getController().gerarFeed(GuardaUsuarios.getAtual(), contagemPosts);        
        feed.setCellFactory(param -> new CelulaFeed());
        feed.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);            
        ObservableList resultObservable = FXCollections.observableArrayList(postsFeed);
        feed.setItems(resultObservable);
    }
    
    /**
     * Classe que representa uma célula da lista de usuários que enviaram solicitação de amizade
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
                fotoView.setFitHeight(57);
                fotoView.setFitWidth(57);

                Label nome = new Label(u.getNome());
                nome.setFont(new Font("Arial Bold", 12));
                Label endereco = new Label("de " + u.getEndereço());
                VBox vbox = new VBox();                

                Button botaoAceitar = new Button("Aceitar");
                botaoAceitar.setOnAction(e -> {
                    GuardaUsuarios.getController().gerarAmizade(u, GuardaUsuarios.getAtual());
                    deletaNo(u);                    
                });
                Button botaoRecusar = new Button("Recusar");
                botaoRecusar.setOnAction(e ->{
                    GuardaUsuarios.getController().removeSolicitacao(GuardaUsuarios.getAtual(), u);
                    deletaNo(u);                    
                });
                
                HBox hbox = new HBox();
                hbox.getChildren().addAll(botaoAceitar, botaoRecusar);
                
                vbox.getChildren().addAll(nome, endereco, hbox);
                
                HBox hbox2 = new HBox();
                hbox2.getChildren().addAll(fotoView, vbox);            
                hbox2.setSpacing(10);            

                setText(null);
                setGraphic(hbox2);
            }
        }
        
        private void deletaNo(User u){
            listPedidos.getItems().remove(u);
        }
    }
    
    /**
     * Classe que representa uma célula da lista de postagens do feed de noticias.
     */
    public class CelulaFeed extends ListCell<Postagem>{    
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

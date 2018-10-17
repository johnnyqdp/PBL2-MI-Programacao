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
import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller da cena de registro de um usuário
 * @author Johnny e Gustavo
 */
public class PaginaRegisterController {
    
    @FXML TextField campoUsername;
    @FXML PasswordField campoPassword;
    @FXML TextField campoNome;
    @FXML TextField campoEmail;
    @FXML DatePicker campoData;
    @FXML TextField campoEndereco;    
    @FXML TextField campoTelefone;
    @FXML Label erroUser; 
    
    /**
     * Cria um novo usuário com as informações inseridas, define o mesmo como usuário logado atual e modifica a cena para
     * a pagina do perfil do usuário criado.
     * @param event
     * @throws IOException 
     */
    public void apertarEfetuarRegistro(ActionEvent event) throws IOException{
        
        if(campoUsername.getCharacters().length() != 0 && campoPassword.getCharacters().length() != 0 && 
                campoNome.getCharacters().length() != 0 && campoEndereco.getCharacters().length() != 0 &&
                campoEmail.getCharacters().length() != 0 && campoTelefone.getCharacters().length() != 0 &&
                campoData.getValue() != null){
            
            ControllerUsuarios c = new ControllerUsuarios();
            User atual;
            if((atual = c.verificaSeJaTem(campoUsername.getCharacters().toString())) != null){
                erroUser.setVisible(true);
            }else{
                String username = campoUsername.getCharacters().toString();
                String password = campoPassword.getCharacters().toString();
                String nome = campoNome.getCharacters().toString();
                String email = campoEmail.getCharacters().toString();
                String data =  campoData.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String endereco = campoEndereco.getCharacters().toString();
                String telefone = campoTelefone.getCharacters().toString();
                
                atual = c.novoUsuario(username, password, nome, email, data, endereco, telefone);               
                
                GuardaUsuarios.setAtual(atual);
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene feedScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaPerfil.fxml")));
                primaryStage.setScene(feedScene);
                GuardaUsuarios.setController(c);
                
                new File("src/postagens/" + username).mkdir(); //cria a pasta de postagens do usuario
            }          
            
        }  
    }
    
    /**
     * Modifica a cena para a cena da pagina inicial.
     * @param event
     * @throws IOException 
     */
    public void apertarBackButton(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene inicialScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaInicial.fxml")));
        primaryStage.setScene(inicialScene);
    }
    
}

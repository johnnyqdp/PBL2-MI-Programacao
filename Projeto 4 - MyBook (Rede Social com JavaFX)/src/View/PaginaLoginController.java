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
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller da cena da pagina de fazer login
 * @author Johnny e Gustavo
 */
public class PaginaLoginController {
    
    @FXML TextField campoUsername;
    @FXML PasswordField campoPassword;
    @FXML Label errorSenha;
    @FXML Label errorInexistente;
    
    /**
     * Chama o controller para verificar se o usuário informado existe, se existir, verifica se a senha está correta,
     * caso positivo, modifica a cena para a página do perfil do usuário.
     * @param event
     * @throws IOException 
     */
    public void apertarEfetuarLogin(ActionEvent event) throws IOException{
        if(campoUsername.getCharacters().length() != 0 && campoPassword.getCharacters().length() != 0){
            User atual;
            ControllerUsuarios c = new ControllerUsuarios();
            if ((atual = c.verificaSeJaTem(campoUsername.getCharacters().toString())) != null){ //Se o usuario existe
                if(atual.getPassword().equals(campoPassword.getCharacters().toString())){ //Se a senha está correta
                    GuardaUsuarios.setAtual(atual);
                    Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene feedScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaPerfil.fxml")));
                    primaryStage.setScene(feedScene);
                    GuardaUsuarios.setController(c);
                }else{
                    errorSenha.setVisible(true);
                    errorInexistente.setVisible(false);
                }
            }else{
                errorSenha.setVisible(false);
                errorInexistente.setVisible(true);
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
        Scene loginScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaInicial.fxml")));
        primaryStage.setScene(loginScene);
    }
}

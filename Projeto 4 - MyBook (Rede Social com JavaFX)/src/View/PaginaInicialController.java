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

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller da página inicial
 * @author Johnny e Gustavo
 */
public class PaginaInicialController {    
    
    /**
     * Modifica a cena para a cena da pagina de login.
     * @param event
     * @throws IOException 
     */
    public void apertarBotaoLogin(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene loginScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaLogin.fxml")));
        primaryStage.setScene(loginScene);
    }
    
    /**
     * Modifica a cena para a cena da pagina de registro.
     * @param event
     * @throws IOException 
     */
    public void apertarBotaoRegistrar(ActionEvent event) throws IOException{
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene loginScene = new Scene(FXMLLoader.load(getClass().getResource("PaginaRegister.fxml")));
        primaryStage.setScene(loginScene);
    }
    
}

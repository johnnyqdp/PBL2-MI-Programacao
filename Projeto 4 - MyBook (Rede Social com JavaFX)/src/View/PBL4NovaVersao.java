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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A classe main só define qual será a cena inicial do programa, e alguns ajustes como tamanho do palco, nome, e define que o mesmo não pode
 * ser redimensionado pelo usuário.
 * @author Johnny e Gustavo
 */
public class PBL4NovaVersao extends Application {
    
    public static void main(String[] args) {
        launch();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {       
        Scene inicio = new Scene(FXMLLoader.load(getClass().getResource("PaginaInicial.fxml")));
        new FXMLLoader(getClass().getResource("PaginaInicial.fxml")).getController();
        primaryStage.setScene(inicio);
        primaryStage.setResizable(false);
        primaryStage.setTitle("MyBook");
        primaryStage.setWidth(300);
        primaryStage.setHeight(450);
        primaryStage.show();
    }
    
}

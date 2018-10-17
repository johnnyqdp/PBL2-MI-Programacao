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

/**
 * Essa classe armazena o usuário atual (que está logado), o usuário que está sendo visitado pelo usuário no programa,
 * O usuário que está sendo selecionado para ver a lista de postagens ou de amigos (pode ser o atual ou o visitado) e 
 * uma referência ao controller geral que foi instanciado. Tudo de forma estática, para poder ser acessado de forma mais rápida
 * dentro de qualquer cena.
 * @author Johnny e Gustavo
 */
public class GuardaUsuarios {
    
    private static User atual;
    private static User visitado;
    private static User usuarioParaVerLista;
    private static ControllerUsuarios controller;

    public static User getAtual() {
        return atual;
    }

    public static void setAtual(User atual) {
        GuardaUsuarios.atual = atual;
    }  

    public static User getVisitado() {
        return visitado;
    }

    public static void setVisitado(User visitado) {
        GuardaUsuarios.visitado = visitado;
    }

    public static ControllerUsuarios getController() {
        return controller;
    }

    public static void setController(ControllerUsuarios controller) {
        GuardaUsuarios.controller = controller;
    }   

    public static User getUsuarioParaVerLista() {
        return usuarioParaVerLista;
    }

    public static void setUsuarioParaVerLista(User usuarioParaVerLista) {
        GuardaUsuarios.usuarioParaVerLista = usuarioParaVerLista;
    }
    
    
}

/*****************************************************************************************
Autores: Johnny Quest Dantas Pereira, Gustavo dos Santos Menezes Alves
Componente Curricular: MI-Programação
Concluido em: 21/05/2018
Declaro que este código foi elaborado por nós de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package View;

import Controller.Controller;
import java.util.Scanner;

/**
 * Classe principal do programa, responsável pelo menu principal do jogo.
 * @author Johnny, Gustavo
 */
public class BlackJack {
    
    /**
     * Método principal do programa, ele imprime o menu principal e a escolha do jogador irá chamar um método
     *  da classe Controller.
     * @param args null. Este programa não usa argumentos de linha de comando.
     */
    public static void main(String[] args) {        
        Controller controller = new Controller();
        Scanner scan = new Scanner(System.in);
        int opcao=0;
        System.out.print("===== BLACKJACK =====\n");
        
        while(opcao != 6){
            System.out.print("[1] Jogar\n[2] Listar cartas do último jogo (na ordem que iriam sair)\n"
                                + "[3] Listar cartas do último jogo (ordenadas por naipe)\n[4] Placar geral\n"
                                + "[5] Ver Regras\n[6] Sair\n-> ");
            opcao = scan.nextInt();            
            switch(opcao){
                case 1:
                    controller.iniciarJogo();
                    break;
                case 2:
                    controller.listarUltimasCartas(false);
                    break;
                case 3:
                    controller.listarUltimasCartas(true);
                    break;
                case 4:
                    controller.salvarPlacar(true);
                    break;
                case 5:
                    controller.regras();
                    break;                            
            }
        }
        controller.salvarPlacar(false);        
    }
}

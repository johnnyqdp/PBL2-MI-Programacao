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

package Controller;

import Model.Baralho;
import Model.Jogador;
import Model.PlacarGeral;
import Util.Array;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

/**
 * Todos as opções do menu principal do jogo chamam métodos desse controller. Ou seja, este controlador é
 * responsável por iniciar um novo jogo, listar as cartas do último jogo (ordenadas ou não ordenadas), gerar um
 * placar geral, e imprimir as regras do jogo.
 * @author Johnny, Gustavo
 */
public class Controller {
    ControllerUsuarios controllerUsuarios = new ControllerUsuarios();
    ControllerPartida controllerPartida = new ControllerPartida();
    private int quantPartidas;
    Jogador jogadores[];
    
    /**
     * Cria um array de jogadores enquanto o jogador quiser continuar com os mesmos jogadores.
     * Em seguida, criará uma partida com esses jogadores, e ficará criando novas rodadas enquanto o jogo ainda
     * não tiver acabado. Ao acabar, ele irá exibir o resultado, se houver algum vencedor, irá atualizar a pontuação
     * geral desse jogador (salvando em seu respectivo arquivo) e, por último, verificará se o jogador quer
     * continuar jogando com os mesmos jogadores.
     */
    public void iniciarJogo(){
        boolean querJogar=true;
        while(querJogar){
            if(quantPartidas==0){
                jogadores = controllerUsuarios.recebeNovosJogadores();
            }
            
            controllerPartida.novaPartida(jogadores);
            int numeroRodada=1;
            while(!controllerPartida.jaAcabou()){
                controllerPartida.novaRodada(numeroRodada);
                controllerPartida.verificaSeTodosTerminaram();
                numeroRodada++;
            }
            Jogador vencedor = controllerPartida.exibeResultado();
            if(vencedor != null){
                controllerUsuarios.salvarJogador(vencedor);
            }
            quantPartidas++;
            querJogar = controllerPartida.querJogarNovamente();
        }
        System.out.print("\n");
    }
    
    /**
     * Lê o arquivo baralho.bar (caso exista) e imprime as cartas desse baralho na tela. Caso querOrdenar seja
     * true, as cartas serão ordenadas antes de serem impressas.
     * @param querOrdenar True caso as cartas devam ser ordenadas, False caso contrário.
     */
    public void listarUltimasCartas(boolean querOrdenar){
        File file = new File("baralho.bar");
        Baralho baralho = null;
        if(file.exists()){
            try{
                FileInputStream arq = new FileInputStream("baralho.bar");
                ObjectInputStream baralhoLido = new ObjectInputStream(arq);
                baralho = (Baralho)baralhoLido.readObject();
            }catch (Exception erro){
                System.out.println(erro);
            }
            Array cartasNovo = geraCartasNovo(baralho);
            if(querOrdenar){
                cartasNovo.selectionSort();
            }
            imprimeBaralho(cartasNovo);
        }else{
            System.out.println("ERRO: Não há nenhum baralho salvo.");
        }
    }
    
    /**
     * Criará um novo arquivo score.txt com as pontuações de todos os jogadores da pasta players. Imprime na tela
     * caso querImprimir seja True.
     * @param querImprimir True caso queira imprimir o score na tela do console, False caso contrário.
     */
    public void salvarPlacar(boolean querImprimir){
        PlacarGeral placar = new PlacarGeral("players");
        placar.SalvarPlacar(querImprimir);
    }

    /**
     * Imprime as regras do BlackJack.
     */
    public void regras(){
        System.out.println("REGRAS DO JOGO:\nDepois de receber duas cartas, o jogador tira cartas para se aproximar do valor 21 sem "
                            + "o ultrapassar. \nO objetivo do jogo é ganhar ao croupier obtendo um total de pontos "
                            + "superior a ele ou vendo-o ultrapassar 21. \nCada jogador joga contra o croupier, que "
                            + "representa a banca, ou o casino, e não contra os outros jogadores.\n" +
                            "\n" +
                            "Valor das cartas no blackjack\n" +
                            "- Cada carta numerada de 2 a 10 tem o seu valor nominal (igual ao número da carta)\n" +
                            "- Os valetes, as damas e os reis (as figuras), têm o valor de 10 pontos\n" +
                            "- O Ás vale 1 ponto ou 11 pontos, dependendo da pontuação atual do jogador.\n" +
                            "Fonte: http://www.blackjack.net.br/regras-blackjack/\n");
    }

    /**
     * Imprime todas as cartas de um array até a posição cartaDoTopo
     * @param cartas Array a ser impresso.
     * @param cartaDoTopo Último índice a ser considerado.
     */
    private void imprimeBaralho(Array cartasNovo) {
        System.out.println("================\nBARALHO RESTANTE DA ULTIMA PARTIDA:");
        for(int i=0; i<cartasNovo.size(); i++){
            System.out.println("-" + cartasNovo.get(i));
        }
        System.out.println("================");       
    }

    /**
     * Retorna um array de cartas com todas as cartas que não foram utilizadas na partida.
     * @param baralho 
     * @return 
     */
    private Array geraCartasNovo(Baralho baralho) {
        Array cartas = baralho.getCartas();
        int cartaDoTopo = baralho.getCartaAtual();
        Array cartasNovo = new Array(52);
        for(int i=cartaDoTopo; i<52; i++){
            cartasNovo.add(cartas.get(i));
        }
        return cartasNovo;
    }

}

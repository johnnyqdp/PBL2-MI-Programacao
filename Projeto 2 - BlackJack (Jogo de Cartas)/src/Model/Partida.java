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

package Model;

import Util.Array;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Classe que representa uma partida de BlackJack, ela recebe um array de jogadores participantes e com eles é possível
 *  iniciar uma partida (dar 2 cartas a cada um), criar novas rodadas (perguntar aos jogadores que ainda estão
 *  na partida se eles querem tirar mais uma carta), verificar se a partida acabou, exibir o resultado final da
 *  partida, salvar o baralho da partida em um objeto, zerar a mão de todos os jogadores e perguntar se o usuário quer
 *  jogar novamente com os mesmos jogadores.
 * @author Johnny, Gustavo
 */
public class Partida {
    
    Scanner scan = new Scanner(System.in);
    private Baralho baralho = new Baralho();        
    private Jogador jogadores[];
    private Croupier croupier = new Croupier();
    private boolean partidaAcabou;
    private int vencedor;

    /**
     * Cria uma partida com os jogadores recebidos.
     * @param jogadores Array de jogadores para esta partida.
     */
    public Partida(Jogador jogadores[]){
        this.jogadores = jogadores;
    }
    
    /**
     * Entrega duas cartas para cada jogador (inclusive para o croupier), exibe as respectivas pontuações dos
     * jogadores e as cartas recebidas por eles. No caso do croupier é exibida apenas uma das cartas.
     */
    public void inicio() {               
        System.out.println("==========\nINICIO DO JOGO:");
        baralho.embaralha();
        
        for(int i=0; i<jogadores.length; i++){
            Carta temp = baralho.tiraCarta();
            jogadores[i].addCarta(temp);
            System.out.print("\t->" + jogadores[i] + " recebe um " + temp);
            temp = baralho.tiraCarta();
            jogadores[i].addCarta(temp);
            System.out.print(" e um " + temp + ", pontuação: " + jogadores[i].getPontuacao());
            System.out.print("\n");                 
        }
        
        for(int i=0; i<2; i++){
            Carta temp = baralho.tiraCarta();
            croupier.addCarta(temp);
        }
        
        System.out.println("\tO Croupier pegou duas cartas, e uma delas é " + croupier.getPrimeiraCarta());
    }
    
    /**
     * Pergunta para cada um dos jogadores se eles querem mais uma carta ou se querem parar por ali. Exibe a carta
     * recebida e a pontuação do jogador. Caso exceda 21, é informado que esse jogador já perdeu.
     * @param rodada Número que representa a rodada que o jogo está.
     */
    public void novaRodada(int rodada){
        System.out.println("\nRodada Número " + rodada + ":");
        for(int i=0; i<jogadores.length; i++){
            if(!jogadores[i].getSaiu() && !jogadores[i].getPerdeu()){
                System.out.print("Vez de " + jogadores[i] + "(" + jogadores[i].getPontuacao() 
                                 + " pontos) Ação(carta/sair):");
                String acao = scan.nextLine();
                if(acao.equals("carta")){
                    Carta temp = baralho.tiraCarta();
                    jogadores[i].addCarta(temp);
                    System.out.print("\t->Tirou um " + temp + ", pontuação:" + jogadores[i].getPontuacao());
                    if(jogadores[i].getPontuacao() > 21)
                        System.out.print(" (Perdeu)");
                    System.out.print("\n");
                }else if(acao.equals("sair")){
                    jogadores[i].saiu();
                }else{
                    System.out.println("ERRO!");
                    i--;
                }
            }
        }
    }
    
    /**
     * Percorre todos os jogadores verificando se eles já sairam ou perderam, se não haver mais nenhum jogador
     * na partida, o método jáAcabou() passará a retornar true.
     */
    public void verificaSeTodosTerminaram(){
        boolean todosJaTerminaram = true;            
        for(int i=0; i<jogadores.length; i++){
            if(!jogadores[i].getSaiu() && !jogadores[i].getPerdeu()){
                todosJaTerminaram = false;
            }
        }            
        if(todosJaTerminaram){
            partidaAcabou=true;
        }     
    }
    
    /**
     * @return True caso não tenha mais nenhum jogador na partida, False caso contrário. 
     */
    public boolean jaAcabou(){
        return partidaAcabou;
    }
    
    /**
     * Salva o baralho dessa partida em um arquivo baralho.bar
     */
    private void salvarBaralho(){
        try {
            FileOutputStream arq = new FileOutputStream("baralho.bar");
            ObjectOutputStream baralhoSalvo = new ObjectOutputStream(arq);
            baralhoSalvo.writeObject(baralho);
            baralhoSalvo.flush();
        } 
        catch (Exception erro) {
            System.out.println(erro);
        }
    }
    
    /**
     * Exibe a carta oculta do croupier e, se necessário, fazê-lo pegar mais cartas até atingir 17. Após isso,
     * Exibe quem foi o vencedor.
     * @return O jogador vencedor. Caso o croupier tenha vencido, retorna null.
     */
    public Jogador exibeResultado(){
        int maior = 0;        
        System.out.println("\nA carta oculta do croupier era um " + croupier.getCartaOculta()
                           + " totalizando " + croupier.getPontuacao() + " pontos.");
        
        for(int i=0; i<jogadores.length; i++){
            if(jogadores[i].getPontuacao() > maior && jogadores[i].getPontuacao() <= 21){
                maior = jogadores[i].getPontuacao();
            }
        }
        if(maior==0){
            System.out.println(">>>>O Croupier venceu.<<<<");
            zeraJogadores(false, vencedor);
        }else{
            while(croupier.getPontuacao() < 17){
                Carta temp = baralho.tiraCarta();            
                croupier.addCarta(temp);
                System.out.println("\t->O croupier pega mais uma carta, é um " + temp + ", totalizando " 
                                    + croupier.getPontuacao() + " pontos.");
            }

            if(croupier.getPontuacao()==21){
                System.out.println(">>>>O Croupier venceu.<<<<");
                zeraJogadores(false, vencedor);
            }else{
                salvarBaralho();
                return verificaVencedor(maior);
            }
        }
        salvarBaralho();
        return null;
    }
    
    /**
     * Método interno que auxilia o exibeResultado(), este irá retornar o jogador vencedor (caso exista,
     *  caso contrário retorna null.)
     * @param maior Valor que corresponde a maior pontuação atingida por um jogador nessa partida
     * @return Jogador vencedor
     */
    private Jogador verificaVencedor(int maior){
        boolean flag = false;
        boolean temEmpate = false;
        Jogador temp = null;
        
        if(croupier.getPontuacao() >= maior && croupier.getPontuacao() <= 21){
            System.out.println(">>>>O Croupier venceu.<<<<");
            zeraJogadores(false, vencedor);
        }else{
            for(int i=0; i<jogadores.length; i++){
                if(jogadores[i].getPontuacao() == maior){
                    if(flag==true){
                        temEmpate=true;
                    }                   
                    temp = jogadores[i];
                    flag=true;
                    vencedor=i;
                }
            }
            if(temEmpate){
                System.out.println("\nHouve empate entre: ");
                for(int i=0; i<jogadores.length; i++){
                    if(jogadores[i].getPontuacao() == maior){
                        System.out.println("\t-> " + jogadores[i]);
                    }
                }
                zeraJogadores(false, vencedor);
            }else if(temp!=null){
                System.out.println(">>>>O vencedor foi " + temp + "<<<<");
                zeraJogadores(true, vencedor);
                return jogadores[vencedor];
            }else{
                System.out.println("Não houve vencedor.");
            }
        }
        return null;
    }    

    /**
     * Remove todas as cartas das mãos dos jogadores, e zera suas pontuações.
     * @param temVencedor True caso hava algum jogador vencedor, False caso contrário.
     * @param vencedor Índice do jogador vencedor no array. 
     */
    public void zeraJogadores(boolean temVencedor, int vencedor) {
        for(int i=0; i<jogadores.length; i++){
            if(i==vencedor && temVencedor){
                jogadores[i].zerar(true);
            }else{
                jogadores[i].zerar(false);
            }            
        }        
    }

    /**
     * Pergunta ao jogador se ele deseja jogar novamente ou não.
     * @return True caso queira jogar novamente, False caso contrário.
     */
    public boolean querJogarNovamente() {
        System.out.print("[1] Jogar novamente com os mesmos jogadores\n[2] Voltar ao Menu\n->");
        int x = scan.nextInt();
        while(x<1 || x>2){
            x=scan.nextInt();
        }
        return x==1;
    }
    
    
}

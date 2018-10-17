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

import java.io.Serializable;
/**
 * Um jogador possui nome de usuário, senha, uma mão de carta (classe interna que guarda o array de cartas que
 *  estão na mão do jogador), sua pontuação na partida, sua pontuação geral e sua quantidade total de vitórias.
 *  É possível adicionar uma carta em sua mão, ver a sua pontuação atual, zerar sua pontuação para iniciar uma
 *  nova partida (e atualizar no placar geral automaticamente), saber se ele já saiu da partida (pediu para sair
 *  ou atingiu os 21 pontos), saber se ele perdeu (ultrapassou 21 pontos), além de poder gerar uma string com a
 *  sua pontuação geral no seguinte padrão: "nome, pontos, vitórias". Essa classe também implementa Comparable, 
 *  então é possível ordenar um array de Jogadores utilizando qualquer algoritmo de ordenação.
 * @author Johnny
 */
public class Jogador implements Serializable, Comparable{
    private String nomeUsuario;
    private String senha;
    protected MaoDeCarta mao = new MaoDeCarta();
    protected int pontuacao;
    private int pontuacaoGeral;
    private int quantVitorias;
    
    private boolean saiu;
    private boolean perdeu;
    private boolean temAs;
    
    /**
     * Construtor padrão da classe.
     * @param nomeUsuario Nome do usuário
     * @param senha Senha
     */
    public Jogador(String nomeUsuario, String senha) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
    }
    
    /**
     * Outro construtor que dispensa a entrada de nome de usuário e senha, utilizado para criar o
     * croupier que dispensa essas informações.
     */
    public Jogador() {}    
    
    
    /**
     * Adiciona uma carta à mão do jogador e soma a sua pontuação com o valor dessa carta.
     * @param carta Carta a ser adicionada.
     */    
    public void addCarta(Carta carta){
        if (carta.getId() == 1){
            temAs=true;
        }        
        mao.add(carta);        
        pontuacao+=(carta.getValor());
        if(getPontuacao()==21){
            saiu=true;
        }else if(getPontuacao() > 21){
            perdeu=true;
        }
    }
    
    /**
     * @return Pontuação do jogador em uma partida espécifica. 
     */
    public int getPontuacao(){
        if(temAs && pontuacao <= 11){
            return pontuacao+10;
        }
        return pontuacao;
    }
    
    /**
     * Zera a pontuação do jogador, sempre chame esse método antes de iniciar uma nova partida
     *  com o mesmo jogador. Caso o jogador tenha sido o vencedor da partida, sua pontuação será
     *  somada à pontuação geral dele.
     * @param venceu True caso ele tenha sido o vencedor, False caso contrário.
     */
    public void zerar(boolean venceu){
        if(venceu){
            pontuacaoGeral+=getPontuacao();
            quantVitorias++;
        }
        mao.zerar();
        pontuacao=0;
        saiu=false;
        perdeu=false;
        temAs=false;
    }

    /**
     * @return True caso o jogador já tenha saído da partida (por escolha ou ao atingir 21 pontos), False
     * caso ainda esteja na partida.
     */
    public boolean getSaiu() {
        return saiu;
    }

    /**
     * Determina que esse jogador saiu da partida.
     */
    public void saiu() {
        this.saiu = true;
    }   
    
    /**
     * @return True caso o jogador tenha perdido (excedido 21 pontos), false caso contrário. 
     */
    public boolean getPerdeu(){
        return perdeu;
    }
    
    /**
     * @return String - nome do usuário 
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }
    
    /**
     * @return String - senha desse usuário 
     */
    public String getSenha() {
        return senha;
    }    

    /**
     * @return Pontuação GERAL do jogador (não a sua pontuação na partida). 
     */
    public int getPontuacaoGeral() {
        return pontuacaoGeral;
    }

    /**
     * @return Quantidade de vitórias desse jogador. 
     */
    public int getQuantVitorias() {
        return quantVitorias;
    }

    /**
     * @return True caso o jogador tenha pelo menos um ás em sua mão, False caso contrário. 
     */
    public boolean TemAs() {
        return temAs;
    }   
    
    /**
     * @return String com a pontuação geral do jogador e sua quantidade de vitórias no seguinte
     * padrão: "nome, pontos, vitórias".
     */
    public String score(){
        return nomeUsuario + ", " + pontuacaoGeral + " pontos, " + quantVitorias + " vitórias.";
    }
    
    /**
     * @return String - nome de usuário desse jogador 
     */
    @Override
    public String toString() {
        return nomeUsuario;
    }   
    
    /**
     * Compara esse jogador com outro a partir de suas pontuações gerais.
     * @param t Jogador a ser comparado.
     * @return -1 caso este jogador seja "menor" que o recebido, +1 caso este jogador seja "maior" que o recebido.
     */
    @Override
    public int compareTo(Object t) {
        Jogador temp = (Jogador) t;
        if(temp.getPontuacaoGeral() > this.getPontuacaoGeral()){
            return 1;
        }else if(temp.getPontuacaoGeral() < this.getPontuacaoGeral()){
            return -1;
        }else{
            return 0;
        }
    }
    
    /**
     * Verifica se um jogador tem o mesmo nome que esse.
     * @param t Jogador a ser comparado
     * @return True caso o nome seja o mesmo, False caso contrário.
     */
    @Override
    public boolean equals(Object t){
        Jogador temp = (Jogador)t;
        return this.nomeUsuario.equals(temp.getNomeUsuario());
    }
    
    /**
     * Uma classe interna de Jogador que possui um array que representa as cartas que estão na
     *  mão do jogador. É possível adicionar cartas, retornar uma carta específica e zerar a mão.
     */
    protected class MaoDeCarta implements Serializable{
    
        private Carta cartas[] = new Carta[11];
        private int contador;
        
        /**
         * Adiciona uma carta à mão do jogador.
         * @param carta Carta a ser adicionada
         */
        public void add(Carta carta){
            cartas[contador] = carta;
            contador++;
        }
        
        /**
         * Retorna uma carta da mão do jogador.
         * @param index índice da carta requisitada.
         * @return A carta que está no índice recebido.
         */
        public Carta getCarta(int index){
            return cartas[index];
        }
        
        /**
         * Deixa o jogador sem nenhuma carta na mão.
         */
        public void zerar(){
            Carta novasCartas[] = new Carta[11];
            cartas = novasCartas;
            contador=0;
        }
        
    }
    
}

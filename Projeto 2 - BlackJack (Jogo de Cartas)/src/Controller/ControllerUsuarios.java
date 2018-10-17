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

import Model.Jogador;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Possui todos os métodos que se responsabilizam por gerenciar os arquivos da pasta players, que armazena todos
 * os jogadores que foram cadastrados.
 * @author Johnny, Gustavo
 */
public class ControllerUsuarios {
    private Jogador jogadores[] = new Jogador[5];
    private int quantJogadores;
    Scanner scan = new Scanner(System.in);
    
    /**
     * Cria a pasta players, caso ela não exista.
     */
    public ControllerUsuarios(){
        File pasta = new File("players");
        if (!pasta.exists()){
            pasta.mkdir();
        }
    }
    
    /**
     * Solicita que o usuário insira os jogadores que ele quer para a partida, fazendo login caso já tenha sido
     * cadastrado, ou cadastrando novos. No final, retorna o array de jogadores inseridos.
     * @return O Array de Jogadores inseridos pelo jogador.
     */
    public Jogador[] recebeNovosJogadores(){        
        boolean jaTerminou=false;
        
        while(!jaTerminou){
            System.out.print("====================\nJOGADORES NA PARTIDA:" + quantJogadores + "\n[1] Inserir um Jogador\n[2] "
                              + "Iniciar a partida\n-> ");
            int escolha = scan.nextInt();
            scan.nextLine();
            
            if(escolha == 2 && quantJogadores == 0){
                System.out.print("ERRO: Você não pode iniciar uma partida sem jogadores.");
            }else if(escolha == 2 && quantJogadores > 0){
                jaTerminou=true;
                System.out.println("\n----------------------------------------------------");
            }else if(escolha == 1 && quantJogadores < 5){                
                boolean loginComSucesso = false;
                while(!loginComSucesso){
                    System.out.print("\t[3] Carregar Jogador Cadastrado\n\t[4] Cadastrar Novo Jogador\n\t->");
                    int escolha2 = scan.nextInt();
                    scan.nextLine();
                    if(escolha2 == 3){     
                        System.out.print("\tUsuário:");
                        String nome = scan.nextLine();         
                        System.out.print("\tSenha:");
                        String senha = scan.nextLine();                        
                        loginComSucesso = carregarJogadorCadastrado(nome, senha);                      
                    }else if(escolha2 == 4){
                        loginComSucesso = cadastrarNovoJogador();
                    }else{
                        System.out.println("ERRO: Comando inválido.");
                    }
                }
            }else{
                System.out.print("ERRO: Você atingiu o limite máximo de jogadores (cinco), ou inseriu um"
                                   + "comando inválido");
            }
           
        }
        
        Jogador novoArrayJogadores[];
        if(quantJogadores==5){
            novoArrayJogadores = jogadores;
        }else{
            novoArrayJogadores = consertaArray();
        }
        jogadores = novoArrayJogadores;
        return jogadores;
    }

    /**
     * Recebe um nome e senha e procura um arquivo que tenha esse nome na pasta players, caso exista, significa que
     * esse jogador foi cadastrado, se a senha inserida condiz com a senha desse jogador, ele é adicionado ao array
     * e o método retorna True.
     * @param nome Nome de Usuário
     * @param senha Senha do Usuário
     * @return True caso o login tenha sido concluído, False caso contrário.
     */
    private boolean carregarJogadorCadastrado(String nome, String senha) {
        if(jaEstaNoJogo(nome)){
            System.out.println("Esse jogador já está logado.");
            return false;
        }        
        
        if(!existeUsuario(nome)){
            System.out.println("Esse usuário não foi cadastrado.");
            return false;
        }
        
        Jogador temp = new Jogador();
        try{
            FileInputStream arq2 = new FileInputStream("players/" + nome + ".player");
            ObjectInputStream pessoaLida = new ObjectInputStream(arq2);
            temp = (Jogador)pessoaLida.readObject();
        }
        catch (Exception erro){
            System.out.println(erro);
        }
        if(temp.getSenha().equalsIgnoreCase(senha)){
            jogadores[quantJogadores] = temp;
            quantJogadores++;
            return true;
        }else{
            System.out.println("\tSenha incorreta.");
            return false;
        }          
    }

    /**
     * Cria um novo jogador e o salva como arquivo na pasta players.
     * @return True caso o cadastro tenha sido concluído, false caso contrário.
     */
    private boolean cadastrarNovoJogador() {
        boolean usuarioValido = false;
        String nome = null;
        String senha;
        
        while(!usuarioValido){
            System.out.print("\tCrie um nome de usuário:");
            nome = scan.nextLine();
            if(existeUsuario(nome)){
                System.out.println("\tJá existe um usuário com esse nome.");
            }else{
                usuarioValido=true;
            }
        }
        
        System.out.print("\tSenha:");
        senha = scan.nextLine();        
        Jogador novo = new Jogador (nome, senha);
        salvarJogador(novo);
        return carregarJogadorCadastrado(nome, senha);
    }

    /**
     * Troca o array de jogadores de tamanho 5 por um array com tamanho igual à quantidade de jogadores
     * cadastrados.
     * @return O novo array de jogadores criado.
     */
    private Jogador[] consertaArray() {
        Jogador novoArrayJogadores[] = new Jogador[quantJogadores];
        for(int i=0; i<quantJogadores; i++){
            novoArrayJogadores[i] = jogadores[i];
        }
        return novoArrayJogadores;
    }

    /**
     * Cria um arquivo com o jogador recebido. Esse arquivo será chamado "(nomeDoJogador).player" e será salvo
     * na pasta players.
     * @param novo O jogador a ser salvo.
     */
    public void salvarJogador(Jogador novo) {
        try {
            FileOutputStream arq = new FileOutputStream("players/" + novo.getNomeUsuario() + ".player");
            ObjectOutputStream pessoaSalva = new ObjectOutputStream(arq);
            pessoaSalva.writeObject(novo);
            pessoaSalva.flush();
        } 
        catch (Exception erro) {
            System.out.println(erro);
        }
    }

    /**
     * Verifica se já existe um jogador com o nome recebido nesta partida.
     * @param nome Nome do jogador a se verificar.
     * @return True caso exista um jogador na partida com o nome recebido, False caso contrário.
     */
    private boolean jaEstaNoJogo(String nome) {
        for(int i=0; i<jogadores.length; i++){
            if(jogadores[i] != null){
                if(jogadores[i].getNomeUsuario().equals(nome)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifica se já existe um jogador com o nome recebido cadastrado.
     * @param nome Nome do jogador a se verificar.
     * @return True caso exista um arquivo na pasta players com o nome recebido, False caso contrário.
     */
    private boolean existeUsuario(String nome) {
        File file = new File("players/" + nome + ".player");
        if(file.exists()){
            return true;
        }else{
            return false;
        }
    }
    
}

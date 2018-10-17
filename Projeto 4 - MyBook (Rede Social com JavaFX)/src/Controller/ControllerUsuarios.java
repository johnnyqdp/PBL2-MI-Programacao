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

package Controller;

import Model.Postagem;
import Model.User;
import Util.GrafoDeUsuarios;
import Util.ListaEncadeada;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;

/**
 * Controller principal para executar as principais funções do programa. Possui um grafo de usuários que representa todos os
 * usuários.
 * @author Johnny e Gustavo
 */
public class ControllerUsuarios implements Serializable {
    
    private GrafoDeUsuarios grafo;

    /**
     * Caso haja um grafo de usuários na pasta do programa, esse construtor irá desserializá-lo. Caso contrário, criará um novo.
     */
    public ControllerUsuarios() {
        File file = new File("GrafoTotal.graph");
        if(file.exists())
            carregaGrafo();
        else
            grafo = new GrafoDeUsuarios();
    }   
    
    /**
     * Cria um novo usuário.
     * @param username
     * @param password
     * @param nome
     * @param email
     * @param nascimento
     * @param endereço
     * @param telefone 
     */
    public User novoUsuario(String username, String password, String nome, String email, String nascimento, String endereço, String telefone){
        User novo = new User(username, password, nome, email, nascimento, endereço, telefone);
        grafo.add(novo);
        salvaGrafo();
        return novo;
    }

    /**
     * Verifica se um usuário já está cadastrado a partir do username. Se tiver, retorna o mesmo... se não, retorna null.
     * @param username - Username
     * @return User buscado ou null caso não exista.
     */
    public User verificaSeJaTem(String username) {
        return grafo.getUser(username);
    }
    
    /**
     * Realiza uma busca em largura no grafo a partir de um usuário informado, procurando usuarios que contenham a string
     * buscada em seu Nome. No final, insere todos os usuarios que não foram acessados pela busca em largura.
     * @param atual Usuario base da busca
     * @param palavraBuscada Palavra a ser buscada nos nomes dos outros usuários.
     * @return 
     */
    public ListaEncadeada busca(User atual, String palavraBuscada){
        return grafo.busca(atual, palavraBuscada);
    }

    /**
     * Desserializa o arquivo GrafoTotal.graph
     */
    private void carregaGrafo() {
        try{
            FileInputStream arq = new FileInputStream("GrafoTotal.graph");
            ObjectInputStream grafoLido = new ObjectInputStream(arq);
            grafo = (GrafoDeUsuarios)grafoLido.readObject();           
        }
        catch (Exception erro){
            System.out.println(erro);
        }
    }

    /**
     * Serializa o grafo.
     */
    public void salvaGrafo() {
        try {
            FileOutputStream arq = new FileOutputStream("GrafoTotal.graph");
            ObjectOutputStream grafoSalvo = new ObjectOutputStream(arq);
            grafoSalvo.writeObject(grafo);
            grafoSalvo.flush();
        } 
        catch (Exception erro) {
            System.out.println(erro);
        }
    }
    
    /**
     * Verifica se dois usuários, a e b, são amigos.
     * @param a 
     * @param b 
     * @return 
     */
    public boolean saoAmigos(User a, User b){
        return grafo.saoAmigos(a, b);
    }
    
    /**
     * Faz com que dois usuários informados se tornem amigos.
     * @param a
     * @param b 
     */
    public void gerarAmizade(User a, User b){
        if(!a.contains(b) && !b.contains(a)){
            grafo.gerarAmizade(grafo.getUser(a.getUsername()), grafo.getUser(b.getUsername()));
            removeSolicitacao(b, a);
        }
    }
    
    /**
     * Retorna todos os amigos de um usuário informado.
     * @param a
     * @return 
     */
    public User[] getAmigos(User a){
        return grafo.getAmigos(grafo.getUser(a.getUsername()));
    }

    /**
     * Envia uma solicitação de amizade, de a para b.
     * @param a
     * @param b 
     */
    public void enviarSolicitacao(User a, User b) {
        if(!grafo.getUser(b.getUsername()).getSolicitacoes().contains(a)) //Se esse usuário não tiver nenhuma solicitação dessas (tratamento pra que não tenham solicitações duplicadas)
            grafo.getUser(b.getUsername()).getSolicitacoes().add(grafo.getUser(a.getUsername()));
        salvaGrafo();
    }

    /**
     * Remove uma solicitação de amizade que b enviou para a.
     * @param a
     * @param b 
     */
    public void removeSolicitacao(User a, User b) {
        grafo.getUser(a.getUsername()).getSolicitacoes().remove(grafo.getUser(b.getUsername()));        
        salvaGrafo();
    }

    /**
     * Retorna todas as postagens de um usuário informado
     * @param u
     * @return 
     */
    public Postagem[] getPostagens(User u) {
        ListaEncadeada posts = u.getPostagens();
        Postagem[] retorno = new Postagem[posts.size()];
        Iterator it = posts.iterator();
        for(int i=0; it.hasNext(); i++)
            retorno[i] = (Postagem)it.next();
        return retorno;
    }

    /**
     * Adiciona uma nova postagem de autoria do usuário informado.
     * @param atual
     * @param postagem 
     */
    public void addPostagem(User atual, Postagem postagem) {
        atual.addPostagem(postagem);
        salvaGrafo();
    }
    
    /**
     * O feed pega uma postagem de cada um dos amigos de um usuário e retorna em um array.
     * @param u usuario a ser gerado o feed
     * @param contagem Postagem a ser pega dos amigos, 0 sendo a mais recente.
     * @return 
     */
    public Postagem[] gerarFeed(User u, int contagem){
        ListaEncadeada postagens = new ListaEncadeada();
        ListaEncadeada todosAmigos = u.getAmigos();
        Iterator it = todosAmigos.iterator();
        while(it.hasNext()){
            User temp = (User)it.next();
            postagens.add(temp.getPostagens().get(contagem));
        }
                
        Postagem[] postsFeed = new Postagem[postagens.size()];
        it = postagens.iterator();
        for(int i = 0; it.hasNext(); i++)
            postsFeed[i] = (Postagem)it.next();
        
        return postsFeed;
    }
    
}

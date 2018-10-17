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

package Model;

import Util.ListaEncadeada;
import java.io.Serializable;

/**
 * Classe que representa um Usuario do sistema.
 * @author Johnny e Gustavo
 */
public class User implements Serializable{
    
    private String username;
    private String password;
    private String nome;
    private String email;
    private String nascimento;
    private String endereço;
    private String telefone;
    private String foto; //Endereço do diretório onde está armazenada a foto. (src/fotosDePerfil/[nomedousuario].[extensãodoarquivo])
    private ListaEncadeada amigos;
    private ListaEncadeada solicitacoesDeAmizade;
    private ListaEncadeada postagens;

    /**
     * Construtor do usuário. Os nomes dos parâmetros são autoexplicativos.
     * @param username
     * @param password
     * @param nome
     * @param email
     * @param nascimento
     * @param endereço
     * @param telefone 
     */
    public User(String username, String password, String nome, String email, String nascimento, String endereço, String telefone) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.email = email;
        this.nascimento = nascimento;
        this.endereço = endereço;
        this.telefone = telefone;
        amigos = new ListaEncadeada();
        solicitacoesDeAmizade = new ListaEncadeada();
        postagens = new ListaEncadeada();
    }    

    /**
     * Verifica se outro usuário é igual a esse, baseando-se no username. (Já que não podem existir dois usuários com o
     * mesmo username...
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof User){
            User temp = (User) obj;
            return this.username.equals(temp.getUsername());
        }
        return false;
    }

    /**
     * Hashcode do usuário, retorna o hashcode do seu username.
     * @return 
     */
    @Override
    public int hashCode() {
        return username.hashCode();
    }
    
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }     

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getNascimento() {
        return nascimento;
    }

    public String getEndereço() {
        return endereço;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getFoto() {
        return foto;
    }

    /**
     * Retorna true se o usuário tiver foto de perfil
     * @return 
     */
    public boolean temFoto() {
        return foto != null;
    }

    /**
     * Define a foto do perfil do usuário, através do endereço do seu diretório.
     * @param foto 
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * Retorna os amigos deste usuário.
     * @return 
     */
    public ListaEncadeada getAmigos() {
        return amigos;
    }
    
    /**
     * Adiciona um usuario à lista de amigos deste usuário.
     * @param u 
     */
    public void addAmigo(User u){
        amigos.add(u);
    }
    
    /**
     * Verifica se um usuário está contido na lista de amigos deste.
     * @param u
     * @return 
     */
    public boolean contains(User u){
        return amigos.contains(u);
    }
    
    /**
     * Retorna true caso esse usuário tenha alguma solicitação de amizade pendente.
     * @return 
     */
    public boolean temSolicitacoes(){
        return !solicitacoesDeAmizade.isEmpty();
    }
    
    /**
     * Retorna uma lista de usuários que enviaram solicitação de amizade à este
     * @return 
     */
    public ListaEncadeada getSolicitacoes(){
        return solicitacoesDeAmizade;
    }
    
    /**
     * Retorna as postagens deste usuário.
     * @return 
     */
    public ListaEncadeada getPostagens(){
        return postagens;
    }
    
    /**
     * Adiciona uma postagem à lista de postagens deste usuário.
     * @param a 
     */
    public void addPostagem(Postagem a){
        postagens.add(a);
    }
    
}

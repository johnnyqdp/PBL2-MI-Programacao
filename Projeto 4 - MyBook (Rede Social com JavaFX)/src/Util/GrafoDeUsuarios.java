/** ***************************************************************************************
 * Autores: Johnny Quest Dantas Pereira, Gustavo dos Santos Menezes Alves
 * Componente Curricular: MI-Programação
 * Concluido em: 27/07/2018
 * Declaramos que este código foi elaborado por nós de forma individual e não contém nenhum
 * trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 * apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 * de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 * do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 ***************************************************************************************** */
package Util;

/**
 *
 */
import Model.User;
import java.io.Serializable;
import java.util.Iterator;

/**
 * Esta classe representa a estrututura de dados principal do programa, o grafo.
 * Ele representa todas as conexões dos usuários, que são armazenados em uma
 * hashSet, tornando assim a busca por usuários muito mais rápida.
 *
 * @author Gustavo e Johnny.
 */

public class GrafoDeUsuarios implements Serializable {

    private HashSet usuarios;

    /**
     * Construtor do grafo, criando uma HashSet para armazenar cada usuário.
     */
    public GrafoDeUsuarios() {
        usuarios = new HashSet();
    }

    /**
     * Método que adiciona um usuário no grafo.
     *
     * @param user Usuário a ser adicionado.
     */
    public void add(User user) {
        usuarios.put(user);
    }

    /**
     * Método que remove um usuário do grafo.
     *
     * @param user Usuário a ser removido.
     */
    public void remove(User user) {
        usuarios.remove(user);
    }
    /**
     * Método que verifica se o usuário está presente no grafo.
     * @param u Usuário a ser verificado
     * @return Se está contido ou não.
     */
    public boolean contains(User u) {
        return usuarios.contains(u);
    }
    /**
     * Método que cria um user com o username passado e dá um get na HashSet, procurando um objeto igual até encontrar ou não.
     * @param username Passado como parâmetro para ser comparado através dos username.
     * @return Um usuário com user name igual ao passado como parâmetro.
     */
    public User getUser(String username) {
        return (User) usuarios.get(new User(username, null, null, null, null, null, null));
    }
    /**
     * Métodoque adiciona um amigo na lista de amigos do outro e vice versa.
     * @param a Usuario 1 que vai criar laço de amizade com o outro usuário 2
     * @param b Usuario 2 que vai criar laço de amizade com o outro usuário 1
     */
    public void gerarAmizade(User a, User b) {
        a.addAmigo(b);
        b.addAmigo(a);
    }
    /**
     *  Método que retorna amigos de um usuário
     * @param a Usuário passado como parâmetro
     * @return Amigos de um usuário
     */
    public User[] getAmigos(User a) {
        User[] retorno = new User[a.getAmigos().size()];
        Iterator it = a.getAmigos().iterator();
        for (int i = 0; it.hasNext(); i++) {
            retorno[i] = (User) it.next();
        }
        return retorno;
    }
    /**
     * Método que verifica se os usuários são amigos, ou seja, se um contém o outro como amigo.
     * @param a Usuário 1
     * @param b Usuário 2
     * @return Se são ou não amigos entre si.
     */
    public boolean saoAmigos(User a, User b) {
        return a.contains(b) && b.contains(a);
    }
    /**
     * Metódo que é dividido em duas etapas de busca. A primeira etapa é a busca em largura, que vai colocando
     * todos elementos numa lista. Já a segunda etapa, percorre a hash inteira, verificando se existe algum usuário
     * que não foi visitadona primeira etapa, quando encontrar, adiciona na mesma lista.
     * @param atual Usuários percorridos
     * @param palavraBuscada Palavra de busca solicitada pelo usuário.
     * @return Lista com resultados da busca.
     */
    public ListaEncadeada busca(User atual, String palavraBuscada) {
        ListaEncadeada resultados = new ListaEncadeada();
        HashSet visited = new HashSet();
        FilaLista fila = new FilaLista();
        fila.add(atual);
        visited.put(atual);

        //Primeira etapa:
        while (!fila.isEmpty()) {
            User a = (User) fila.remove();
            Iterator i = a.getAmigos().iterator();
            while (i.hasNext()) {
                User u = (User) i.next();
                if (!visited.contains(u)) {
                    if (u.getNome().toLowerCase().contains(palavraBuscada.toLowerCase())) {
                        resultados.add(u);
                    }
                    fila.add(u);
                    visited.put(u);
                }
            }
        }
        //Segunda etapa:    
        ListaEncadeada[] todos = usuarios.getListas();
        for (ListaEncadeada l : todos) {
            if (l != null) {
                Iterator i = l.iterator();
                while (i.hasNext()) {
                    User pego = (User) i.next();
                    if (!visited.contains(pego) && pego.getNome().toLowerCase().contains(palavraBuscada.toLowerCase())) {
                        resultados.add(pego);
                    }
                }
            }
        }

        return resultados;

    }

}

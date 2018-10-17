/*****************************************************************************************
Autores: Johnny Quest Dantas Pereira, Gustavo dos Santos Menezes Alves
Componente Curricular: MI-Programação
Concluido em: 27/06/2018
Declaramos que este código foi elaborado por nós de forma individual e não contém nenhum
trecho de código de outro colega ou de outro autor, tais como provindos de livros e
apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
******************************************************************************************/

package Util;

import java.io.Serializable;

/**
 * Essa estrutura de dados foi adaptada de um código pertencente a Rodrigo Vilar, disponível
 * em https://gist.github.com/rodrigovilar/5754425#file-arvoreavl-java * 
 */
public class No implements Comparable, Serializable{
  
    private No esquerda;
    private No direita;
    private No pai;
    private Comparable chave;
    private int balanceamento;

    public No(Comparable k) {
        setEsquerda(setDireita(setPai(null)));
        setBalanceamento(0);
        setChave(k);
    }
    
    @Override
    public String toString(){
        return chave.toString();
    }
    
    @Override
    public boolean equals(Object a){
        No temp = (No) a;
        return chave.equals(temp.getChave());
    }
    
    public Comparable getChave() {
        return chave;
    }

    public void setChave(Comparable chave) {
        this.chave = chave;
    }

    public int getBalanceamento() {
        return balanceamento;
    }

    public void setBalanceamento(int balanceamento) {
        this.balanceamento = balanceamento;
    }

    public No getPai() {
        return pai;
    }

    public No setPai(No pai) {
        this.pai = pai;
        return pai;
    }

    public No getDireita() {
        return direita;
    }

    public No setDireita(No direita) {
        this.direita = direita;
        return direita;
    }

    public No getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(No esquerda) {
        this.esquerda = esquerda;
    }

    @Override
    public int compareTo(Object t) {
        No temp = (No) t;
        return chave.compareTo(temp.getChave());
    }
}
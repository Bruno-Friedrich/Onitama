/**
 * Classe que contém informações e ações básicas relacionadas aos jogadores
 */
public class Player {
    private final String name;
    private final Color pieceColor;
    private Card[] cards = new Card[2];
    //private boolean masterIsDefeated = false;
  
    /**
     * Construtor que define informações básicas do jogador
     * @param name Nome do jogador
     * @param pieceColor Cor das peças do jogador
     * @param cards Cartas na mão do jogador
     */
    public Player(String name, Color pieceColor, Card[] cards) {
        this(name,pieceColor,cards[0],cards[1]);
    }

    /**
     * Construtor que define informações básicas do jogador
     * @param name Nome do jogador
     * @param pieceColor Cor das peças do jogador
     * @param card1 A primeira carta na mão do jogador
     * @param card2 A segunda carta na mão do jogador
     */
    public Player(String name, Color pieceColor, Card card1, Card card2) {
        this.name = name;
        this.pieceColor = pieceColor;
        this.cards[0] = card1;
        this.cards[1] = card2;
    }

    /**
     * Método que devolve o nome do jogador(a)
     * @return String com o nome do jogador(a)
     */
    public String getName() {
        return name;
    }

    /**
     * Método que devolve a cor das peças do jogador
     * @return Enum Color com a cor das peças do jogador
     */
    public Color getPieceColor() {
        return pieceColor;
    }

    /**
     * Método que devolve as cartas da mão do jogador
     * @return Booleano true para caso seja um mestre e false caso contrário
     */
    public Card[] getCards() {
        return cards;
    }

    // public boolean getMasterIsDefeated(){
    //   return masterIsDefeated;
    // }

    // public void setMasterIsDefeated(boolean masterIsDefeated){
    //   this.masterIsDefeated = masterIsDefeated;
    // }
  
    /**
     * Método que troca uma carta da mão por outra carta (idealmente da mesa)
     * @param oldCard A carta que será substituída
     * @param newCard A carta que irá substituir
     * @exception InvalidCardException Caso a carta não esteja na mão do jogador e/ou na mesa
     */
    
    //ERRO 1: A carta trocada não está na mão do jogador
    protected void swapCard(Card oldCard, Card newCard) throws InvalidCardException {
        if(oldCard.equals(cards[0])){
            cards[0] = newCard;
        }
        else if(oldCard.equals(cards[1])){
            cards[1] = newCard;
        }
        else throw new InvalidCardException("ERRO: Carta não está na mão do jogador.");  
    }
}

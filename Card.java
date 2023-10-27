
/**
 * Classe que contém informações das cartas
 */
import java.util.*;

public class Card {
  /**
   * Construtor que define os principais atributos de uma cara
   * 
   * @param name      Nome da carta
   * @param color     Cor da carta
   * @param positions Todas as posições relativas de movimento
   */
  private String name;
  private Color color;
  private Position[] positions;

  public Card(String name, Color color, Position[] positions) {
    this.name = new String(name);
    this.positions = new Position[positions.length];
    this.color = color;
    for (int i = 0; i < positions.length; i++) {
      this.positions[i] = new Position(positions[i].getRow(), positions[i].getCol());
    }
  }
  public Card(Card card){
    this(card.name,card.color,card.positions);
  }
  /**
   * Método que devolve o nome da carta
   * 
   * @return String que contém o nome da carta
   */
  public String getName() {
    return name;
  }

  /**
   * Método que devolve a cor da carta
   * 
   * @return Enum Color que contém a cor da carta
   */
  public Color getColor() {
    return color;
  }

  /**
   * Método que devolve todas as possíveis posições relativas de movimento.
   * A posição atual da peça é o ponto de origem (0,0). Uma carta possui as
   * possíveis posições de movimento em relação ao ponto de origem.
   * 
   * @return Um array de Position contendo todas as possíveis posições de
   *         movimento em relação ao ponto de origem
   */
  public Position[] getPositions() {
    return positions;
  }

  /**
   * Método que cria todas as cartas do jogo, embaralha-as e devolve as 5 que
   * serão utilizadas na partida.
   * 
   * @return Vetor de cartas com todas as cartas do jogo
   */
  public static Card[] cardsDistShuffle(Card[] deck) {
    List<Integer> key = new ArrayList<Integer>();
    int keyBuffer;
    Card[] handle = new Card[5];
    
    for (int i = 0; i < deck.length; i++) {
      key.add(i);
    }
    Collections.shuffle(key);
    ListIterator<Integer> it = key.listIterator();
    for (int i = 0; i < 5; i++) {
      keyBuffer = it.next();
      if(deck[keyBuffer] == null){
        throw new NullPointerException("Sua carta" + (keyBuffer+1) + "não existe");
      }
      handle[i] = new Card(deck[keyBuffer].getName(), deck[keyBuffer].getColor(), deck[keyBuffer].getPositions());
    }
    return handle;
  }

  public static Card[] createCards() {
    Card[] allCards = new Card[8];
    String[] name = { "Tiger", "Dragon", "Frog", "Rabbit", "Crab", "Elephant", "Goose", "Rooster" };
    Color[] cor = { Color.BLUE, Color.RED, Color.RED, Color.BLUE, Color.BLUE, Color.RED, Color.BLUE, Color.RED };
    Position[][] possiblePositions = { { new Position(-2, 0), new Position(1, 0) },
        { new Position(-1, -2), new Position(-1, 2), new Position(1, -1), new Position(1, 1) },
        { new Position(0, -2), new Position(1, 1), new Position(-1, -1) },
        { new Position(0, 2), new Position(1, -1), new Position(-1, 1) },
        { new Position(0, -2), new Position(0, 2), new Position(-1, 0) },
        { new Position(0, -1), new Position(0, 1), new Position(-1, -1), new Position(-1, 1) },
        { new Position(0, 1), new Position(0, -1), new Position(1, 1), new Position(-1, -1) },
        { new Position(0, 1), new Position(0, -1), new Position(-1, 1), new Position(1, -1) }
    };

    for (int i = 0; i < 8; i++) {
      allCards[i] = new Card(name[i], cor[i], possiblePositions[i]);
    }
    // lembrar de consertar

    return cardsDistShuffle(allCards);
    //return allCards;
  }
  // public static void main(String[] args){
  // Card[] all = createCards();
  // Card[] wrongDesk = {new
  // Card(all[1].getName(),all[1].getColor(),all[1].getPositions())};
  // }
}

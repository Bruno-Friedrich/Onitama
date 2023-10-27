import java.util.Scanner;

class Main {
  public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    Color antiTurn;

    GameImpl game = new GameImpl("Bruno", "Matheus");

    System.out.println("Jogador 1 (RED): " + game.getRedPlayer().getName());
    System.out.println("Jogador 2 (BLUE): " + game.getBluePlayer().getName());
    
    do{
      antiTurn = game.getTurn();
      
      showCards(game, game.getRedPlayer().getCards(), game.getBluePlayer().getCards(), game.getTableCard());
      
      game.printBoard();
      
      System.out.println("Vez das " + game.getTurn() + " de jogarem");
      
      System.out.println("Digite a carta que vai jogar: ");
      
      String nameCardUsing = scanner.next();
      System.out.println(nameCardUsing);
        
      System.out.println("Digite a posição da carta(linha depois coluna): ");

      int row = scanner.nextInt();
      int col = scanner.nextInt();
      Position positionToGo = new Position(row, col);
      System.out.println(positionToGo);
        
      System.out.println("Digite a posição da peça que deseja mover(linha depois coluna): ");
      
      int row1 = scanner.nextInt();
      int col1 = scanner.nextInt();
      Position piecePos = new Position (row1, col1);
      System.out.println(piecePos);
        
      switch (game.getTurn()){
        case RED:{
          Card[] deck = game.getRedPlayer().getCards();
          Card cardToPlay = null;
          if(deck[0].getName().equals(nameCardUsing)){
            cardToPlay = deck[0]; 
            game.makeMove(cardToPlay, positionToGo, piecePos);
          }else if(deck[1].getName().equals(nameCardUsing)){
            cardToPlay = deck[1]; 
            game.makeMove(cardToPlay, positionToGo, piecePos);
          }
          else System.out.println("Carta digitada errada, tente novamente.");
        } break;
          
        case BLUE:{
          Card[] deck = game.getBluePlayer().getCards();
          Card cardToPlay = null;
          if(deck[0].getName().equals(nameCardUsing)){
            cardToPlay = deck[0]; 
            game.makeMove(cardToPlay, positionToGo, piecePos);
          }else if(deck[1].getName().equals(nameCardUsing)){
            cardToPlay = deck[1]; 
            game.makeMove(cardToPlay, positionToGo, piecePos);
          }
          else System.out.println("Carta digitada errada, tente novamente.");
        } break;
      }
    } while(!game.checkVictory(antiTurn));
    System.out.println("Olá humano");
  }
    
    public static void showCards(GameImpl game, Card[] cardRedPlayer, Card[] cardBluePlayer, Card tableCard){
      System.out.println("Cartas do jogador " + game.getRedPlayer().getName());
      for (Card i: cardRedPlayer){
        System.out.println("Cor da carta: " + i.getColor());
        System.out.println("Nome da carta: " + i.getName());
        Position[] posi = i.getPositions();
        for(Position p : posi){
          System.out.printf("(%+d,%+d)\n",p.getRow(),p.getCol());
        }
      }
      
      System.out.println("Cartas do jogador " + game.getBluePlayer().getName());
      for (Card i: cardBluePlayer){
        System.out.println("Cor da carta: " + i.getColor());
        System.out.println("Nome da carta: " + i.getName());
        Position[] posi = i.getPositions();
        for(Position p : posi){
          System.out.printf("(%+d,%+d)\n",p.getRow(),p.getCol());
        }
      }

      System.out.println("Cartas da mesa");
        System.out.println("Cor da carta: " + tableCard.getColor());
        System.out.println("Nome da carta: " + tableCard.getName());
        Position[] posi = tableCard.getPositions();
        for(Position p : posi){
          System.out.printf("(%+d,%+d)\n",p.getRow(),p.getCol());
        }
    }
}

  /*public static void main(String[] args) {
    Card[] allCards = Card.createCards();
    Card[] cards = new Card[5];
    System.arraycopy(allCards,0,cards,0,5);
    GameImpl game = new GameImpl("Bruno", "Matheus", cards);
    Position[] posi;
     Card cardT = game.getTableCard();
  game.printBoard();
  System.out.println(cardT.getName());
      posi = cardT.getPositions();
      for(Position i:posi){
        System.out.println("(" + i.getRow() + "," + i.getCol() + ")" );
      }
    // Card[] cards = game.getCards();
    //System.out.println(card.getName());
  //   for(Card c: cards){
  //     System.out.println(c.getName());
  //     posi = c.getPositions();
  //     for(Position i:posi){
  //       System.out.println("(" + i.getRow() + "," + i.getCol() + ")" );
  //     }
  //     System.out.println(c.getColor());
  // }
  Card[] redCards = game.getRedPlayer().getCards();
  Position masterPosi = new Position(4,2);
  Position[] posiCards = redCards[0].getPositions();
  Position newPosition = posiCards[0];
  //   Card[] bluePlayerCards = game.getBluePlayer().getCards();
  //   Card[] redPlayerCards = game.getRedPlayer().getCards();
  //   Position posiMove = new Position(4,2);
  game.makeMove(redCards[0],newPosition,masterPosi);
  cardT = game.getTableCard();
  game.printBoard();
  System.out.println(cardT.getName());
      posi = cardT.getPositions();
      for(Position i:posi){
        System.out.println("(" + i.getRow() + "," + i.getCol() + ")" );
      }
  //   for(Card c: cards){
  //     System.out.println(c.getName());
  //     posi = c.getPositions();
  //     for(Position i:posi){
  //       System.out.println("(" + i.getRow() + "," + i.getCol() + ")" );
  //     }
  //     System.out.println(c.getColor());
  // }
  //}
}*/


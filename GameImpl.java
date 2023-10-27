public class GameImpl implements Game {
  private Player redPlayer;
  private Player bluePlayer;
  private Spot[][] board;
  private Card cards[];
  private Card tableCard;
  private Color turn;
  private Color colorMasterDefeat;
  public GameImpl() {
    this("RED", "BLUE");
  }

  public GameImpl(String redPlayer, String bluePlayer) {
    this.cards = Card.createCards();
    gameLoad(redPlayer, bluePlayer);
  }

  public GameImpl(String redPlayer, String bluePlayer, Card[] cards) {
    if(cards == null){
      throw new NullPointerException("Erro: Seu deck é inválido");
    }
    if(cards.length < 5){
      throw new IllegalArgumentException("Erro: Deck passado possui tamanho insuficiente");
    }
    gameLoad(redPlayer, bluePlayer);
  }

  private void gameLoad(String redPlayer, String bluePlayer) {
    this.board = initializeBoard();
    this.redPlayer = new Player(redPlayer, Color.RED, cards[0], cards[1]);
    this.bluePlayer = new Player(bluePlayer, Color.BLUE, cards[3], cards[4]);
    this.tableCard = cards[2];
    this.turn = this.tableCard.getColor();
    this.colorMasterDefeat = Color.NONE;
  }

  /**
   * Método que devolve a cor da posição do tabuleiro. Se possui uma cor,
   * significa que é um templo. Caso contrário, é um espaço normal
   * 
   * @param position Posição do tabuleiro
   * @return O enum Color que representa a cor da posição
   */
  @Override
  public Color getSpotColor(Position position) {
    int row = position.getRow();
    int col = position.getCol();
    return board[row][col].getColor();
  }

  /**
   * Método que devolve a peça que está na posição do tabuleiro
   * 
   * @param position Posição do tabuleiro
   * @return Um objeto Piece que representa a peça na posição indicada. Se não
   *         tiver peça, devolve null
   */
  @Override
  public Piece getPiece(Position position) {
    int row = position.getRow();
    int col = position.getCol();
    return board[row][col].getPiece();
  }

  /**
   * Método que devolve a carta que está na mesa, que será substituída após a
   * próxima jogada
   * 
   * @return Um objeto Card que representa a carta na mesa
   */
  @Override
  public Card getTableCard() {
    return tableCard;
  }

  /**
   * Método que devolve as informações sobre o jogador com as peças vermelhas
   * 
   * @return Um objeto Player que representa o jogador vermelho
   */
  @Override
  public Player getRedPlayer() {
    return redPlayer;
  }

  /**
   * Método que devolve as informações sobre o jogador com as peças azuis
   * 
   * @return Um objeto Player que representa o jogador azul
   */
  @Override
  public Player getBluePlayer() {
    return bluePlayer;
  }

  public Card[] getCards() {
    return cards;
  }

  public Color getTurn() {
    return turn;
  }

  /**
   * Método que move uma peça
   * 
   * @param card       A carta de movimento que será usada
   * @param cardMove   A posição da carta para onde a peça irá se mover
   * @param currentPos A posição da peça que irá se mover
   * @exception IncorrectTurnOrderException Caso não seja a vez de um jogador
   * @Override fazer um movimento
   * @exception IllegalMovementException Caso uma peça seja movida para fora do
   *                                     tabuleiro ou para uma posição onde já
   *                                     tem uma peça da mesma cor
   * @exception InvalidCardException     Caso uma carta que não está na mão do
   *                                     jogador seja usada
   * @exception InvalidPieceException    Caso uma peça que não está no
   *                                     tabuleiro seja usada
   */
  @Override
  public void makeMove(Card card, Position cardMove, Position currentPos)
      throws IncorrectTurnOrderException, IllegalMovementException, InvalidCardException, InvalidPieceException {
      Card[] cardsOfTurnPlayer = null;
      if(turn == Color.RED){
        cardsOfTurnPlayer = redPlayer.getCards();
      }
      else if (turn == Color.BLUE){
        cardsOfTurnPlayer = bluePlayer.getCards();
      }
        if(!cardsOfTurnPlayer[0].equals(card) && !cardsOfTurnPlayer[1].equals(card))
            throw new InvalidCardException("ERRO: Jogador não possui a carta");
      
      if (board[currentPos.getRow()][currentPos.getCol()].getPiece() == null)
      throw new InvalidPieceException("ERRO: Peça que deseja jogar não existe.");

      if (!board[currentPos.getRow()][currentPos.getCol()].getPiece().getColor().equals(this.turn))
      throw new IncorrectTurnOrderException("ERRO: Não é a vez do jogador que irá mover a peça.");
    
    boolean validPosition = false;

    Position[] cardPositions = card.getPositions();

    //System.out.println(card.getName());
    
    for (Position p : cardPositions) {
     // System.out.println(p);  
      // if ((p.getRow() == cardMove.getRow()) &&
      //     (p.getCol() == cardMove.getCol())){
      //   validPosition = true;
      // }
      if(p.equals(cardMove)){
        validPosition = true;
      }
    }
    if (!validPosition)
      throw new IllegalMovementException("ERRO: O movimento não é permitido  pela carta selecionada.");
        
    Position finalPosition = cardMove.sum(currentPos);
    
    if (finalPosition.getCol() > 4 || finalPosition.getCol() < 0)
      throw new IllegalMovementException("ERRO: Peça foi movida para uma posição que extrapola o LIMITE HORIZONTAL");

      if (finalPosition.getRow() > 4 || finalPosition.getRow() < 0)
      throw new IllegalMovementException("ERRO: Peça foi movida para uma posição que extrapola o LIMITE VERTICAL.");





    Piece movePiece = new Piece(board[currentPos.getRow()][currentPos.getCol()].getPiece());
    Piece originalFinalPositionPiece = Piece.makePiece(board[finalPosition.getRow()][finalPosition.getCol()].getPiece());
    board[finalPosition.getRow()][finalPosition.getCol()].occupySpot(movePiece);
    if(originalFinalPositionPiece != null && originalFinalPositionPiece.isMaster()){
      colorMasterDefeat = originalFinalPositionPiece.getColor();
    }
    
    board[currentPos.getRow()][currentPos.getCol()].releaseSpot();
    
    
    // System.out.println(aux.getColor());
    // board[finalPosition.getRow()][finalPosition.getCol()].releaseSpot();
      
    // Piece originalFinalPositionPiece = null;
    // if(board[finalPosition.getRow()][finalPosition.getCol()].getPiece() != null){
    //  originalFinalPositionPiece =  new Piece(board[finalPosition.getRow()][finalPosition.getCol()].getPiece());
    // }
    
    // System.out.println(originalFinalPositionPiece.getColor());
    // System.out.println(originalFinalPositionPiece.getColor());
        
    // if(originalFinalPositionPiece   != null && originalFinalPositionPiece.isMaster()){
    //   colorMasterDefeat = originalFinalPositionPiece.getColor();
    //   System.out.println("\n\n" + colorMasterDefeat + "foi vencido\n\n");
    // }
    // board[finalPosition.getRow()][finalPosition.getCol()].occupySpot(movePiece);
    // board[currentPos.getRow()][currentPos.getCol()].releaseSpot();
    
    // if (checkMaster != null) {
    //   System.out.println("entrou");
    //   if (checkMaster.isMaster()) {
    //     System.out.println("entrou");
    //     if (checkMaster.getColor().equals(Color.RED)) {
    //       redPlayer.setMasterIsDefeated(true);
    //       System.out.println("entrou");
    //     } else
    //       bluePlayer.setMasterIsDefeated(true);
    //     System.out.println("entrou");
    //   }
    // }
        
    Card swapAuxCard = new Card(card);
    if (turn.equals(Color.BLUE)) {
      bluePlayer.swapCard(card, tableCard);
      turn = Color.RED;
    } else {
      redPlayer.swapCard(card, tableCard);
      turn = Color.BLUE;
    }
    this.tableCard = swapAuxCard;
  }

  /**
   * Método que confere se um jogador de uma determinada cor venceu o jogo.
   * Critérios de vitória:
   * — Derrotou a peça de mestre adversária
   * — Posicionou o seu mestre na posição da base adversária
   * 
   * @param color Cor das peças do jogador que confere a condição de vitória
   * @return Um booleano true para caso esteja em condições de vencer e false caso
   *         contrário
   */
  @Override
  public boolean checkVictory(Color color) {
    if (color == Color.RED) {
      if (this.board[0][2].getPiece() != null)
        if (this.board[0][2].getPiece().isMaster() && this.board[0][2].getPiece().getColor() == Color.RED)
          return true;
     if (colorMasterDefeat == Color.BLUE)
        return true;
    } else if (color == Color.BLUE) {
      if (this.board[4][2].getPiece() != null)
        if (this.board[4][2].getPiece().isMaster() &&
            this.board[4][2].getPiece().getColor() == Color.BLUE)
          return true;
     if (colorMasterDefeat == Color.RED)
        return true;
    }
    return false;
  }

  /**
   * Método que imprime o tabuleiro no seu estado atual
   * OBS: Esse método é opcional não será utilizado na correção, mas serve para
   * acompanhar os resultados parciais do jogo
   */
  @Override
  public void printBoard() {
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        Spot spot = board[row][col];
        if (spot.getPiece() != null) {
          if (spot.getPiece().getColor().equals(Color.RED)) {
            if (spot.getPiece().isMaster())
              System.out.print("\u001B[31mX\u001B[0m ");
            else
              System.out.print("\u001B[31m1\u001B[0m ");
          } else if (spot.getPiece().getColor().equals(Color.BLUE)) {
            if (spot.getPiece().isMaster())
              System.out.print("\u001B[34mX\u001B[0m ");
            else
              System.out.print("\u001B[34m1\u001B[0m ");
          }
        } else {
          System.out.print("0 ");
        }
      }
      System.out.println();
    }

  }

  private void initializeRow(Spot[][] board, Color color, int row) {
    Color temple = Color.NONE;
    for (int col = 0; col < 5; col++) {
      Position position = new Position(row, col);

      switch (row) {
        case 0: {
          temple = (col == 2) ? Color.BLUE : Color.NONE;
        }
          break;

        case 4: {
          temple = (col == 2) ? Color.RED : Color.NONE;
        }
          break;
      }

      boolean isMaster = (col == 2) ? true : false;
      Piece piece = new Piece(color, isMaster);
      board[row][col] = new Spot(piece, position, temple);
    }
  }

  private Spot[][] initializeBoard() {
    Spot[][] board = new Spot[5][5];

    // Preencher a primeira linha com peças azuis
    initializeRow(board, Color.BLUE, 0);

    // Preencher a última linha com peças vermelhas
    initializeRow(board, Color.RED, 4);

    // Preencher as demais casas com espaços vazios
    for (int row = 1; row < 4; row++) {
      for (int col = 0; col < 5; col++) {
        Position position = new Position(row, col);
        board[row][col] = new Spot(position);
      }
    }

    return board;
  }
}

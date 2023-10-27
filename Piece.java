  /**
 * Classe que contém informações das peças de jogo
 */
public class Piece {
    private Color color;
    private boolean isMaster;
  
    /**
     * Construtor que define a cor e o tipo da peça
     * @param color Cor da peça
     * @param isMaster Se o tipo da peça é mestre ou não
     */
  
    public Piece(Color color, boolean isMaster) {
        this.color = color;
        this.isMaster = isMaster;
    }
    public Piece(Piece piece) {
      this(piece.color,piece.isMaster);
    }
    public static Piece makePiece(Piece piece){
      Piece newPiece = null;
      if(piece != null){
        newPiece = new Piece(piece);
      }
      return newPiece;
    }

    /**
     * Método que devolve a cor da peça
     * @return Enum Color com a cor da peça
     */
    public Color getColor() {
        return color;
    }

  @Override
  public String toString(){
    String iString = ("Color =" + this.color + "isMaster =" + this.isMaster);
    return iString;
  }
    /**
     * Método que devolve se é um mestre ou não
     * @return Booleano true para caso seja um mestre e false caso contrário
     */
    public boolean isMaster() {
        return isMaster;
    }
}
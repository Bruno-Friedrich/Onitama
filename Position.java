/**
 * Classe usada para definição de estrutura de posições e movimentos do jogo
 */
public class Position {
  /**
   * Construtor que define o valor da Linha e da Coluna da posição, baseado no
   * plano cartesiano]
   * 
   * @param row Linha
   * @param col Coluna
   */
  private int row, col;

  public Position(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * Método que devolve o valor do eixo X da posição
   * 
   * @return Um valor int representando o eixo X
   */
  public int getRow() {
    return row;
  }

  /**
   * Método que devolve o valor do eixo Y da posição
   * 
   * @return Um valor int representando o eixo Y
   */
  public int getCol() {
    return col;
  }

  
  public Position sum(Position move){
    
    return new Position(this.row + move.row,this.col + move.col);
  } 
  @Override
  public String toString(){
    String dados = ("(" + this.row + "," + this.col + ")"); 
    return dados;
  }
  @Override
  public boolean equals(Object obj){
  if(obj != null && (obj instanceof Position)){  
    Position position = (Position)obj;
    if(this.row == position.row &&
       this.col == position.col){
        return true;
       }
  }
    return false;
  }
}

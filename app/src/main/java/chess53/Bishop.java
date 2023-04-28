package chess53;

/**
 * The {@code Bishop} class represents a Bishop piece in the game of chess.
 * It extends the {@code Piece} class and inherits its attributes and methods.
 *
 * @author Krish Patel
 * @author Roshan Varadhan
 * @see Piece
 */
public class Bishop extends Piece {

    /**
     * Constructs a Bishop piece with the given color.
     *
     * @param white true if the piece is white, false if it is black
     */
    public Bishop(boolean white) {
        super(white);
    }

    @Override
    public boolean isLegalMove(int x0, int x1, int y0, int y1) {
        boolean valid = Math.abs(x1 - x0) == Math.abs(y1 - y0) && x0 != x1;
        int i = x0;
        int j = y0;
        if (valid) {
            if (x1 > x0) {
                i++;
                if (y1 > y0) {
                    j++;
                    while (i < x1 && j < y1) {
                        if (Chess.board[i][j] != null) {
                            valid = false;
                            break;
                        }
                        i++;
                        j++;
                    }
                } else {
                    j--;
                    while (i < x1 && j > y1) {
                        if (Chess.board[i][j] != null) {
                            valid = false;
                            break;
                        }
                        i++;
                        j--;
                    }
                }
            } else {
                i--;
                if (y1 > y0) {
                    j++;
                    while (i > x1 && j < y1) {
                        if (Chess.board[i][j] != null) {
                            valid = false;
                            break;
                        }
                        i--;
                        j++;
                    }
                } else {
                    j--;
                    while (i > x1 && j > y1) {
                        if (Chess.board[i][j] != null) {
                            valid = false;
                            break;
                        }
                        i--;
                        j--;
                    }
                }
            }
        }
        if (valid) {
            if (Chess.board[x1][y1] != null) {
                if (isWhite() == Chess.board[x1][y1].isWhite()) valid = false;
            }
        }
        if (valid) {
            Chess.board[x1][y1] = Chess.board[x0][y0];
            Chess.board[x0][y0] = null;
        }
        return valid;
    }

    public String getType(){
        return "BISHOP";
    }

    @Override
    public String toString() {
        return (isWhite()) ? "wB" : "bB";
    }

}
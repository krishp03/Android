package chess53;

/**
 * The {@code Pawn} class represents a Pawn piece in the game of chess.
 * It extends the {@code Piece} class and inherits its attributes and methods.
 *
 * @author Krish Patel
 * @author Roshan Varadhan
 * @see Piece
 */
public class Pawn extends Piece {

    /**
     * Constructs a Pawn piece with the given color.
     *
     * @param white true if the piece is white, false if it is black
     */
    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean isLegalMove(int x0, int x1, int y0, int y1) {
        boolean valid;
        if (Chess.board[x1][y1] != null) {
            if (isWhite() == Chess.board[x1][y1].isWhite()) return false;
            if (Math.abs(y1 - y0) != 1) return false;
            if (!isWhite() && x1 - x0 != 1) return false;
            if (isWhite() && x0 - x1 != 1) return false;
            Chess.board[x1][y1] = Chess.board[x0][y0];
            Chess.board[x0][y0] = null;
            setEnPassant(false);
            return true;
        }
        if (Math.abs(y1 - y0) == 1) {
            if (!isWhite() && x1 - x0 != 1) return false;
            if (isWhite() && x0 - x1 != 1) return false;
            if (!(Chess.board[x0][y1] instanceof Pawn)) return false;
            if (Chess.board[x0][y1].isWhite() == isWhite()) return false;
            if (!Chess.board[x0][y1].enPassantable()) return false;
            if (Chess.board[x0][y1] != Chess.lastMoved) return false;
            Chess.board[x1][y1] = Chess.board[x0][y0];
            Chess.board[x0][y0] = null;
            Chess.board[x0][y1] = null;
            setEnPassant(false);
            return true;
        }
        if (y1 != y0) return false;
        else if (!this.hasMoved) {
            if (!isWhite()) {
                valid = x1 - x0 <= 2;
                if (Chess.board[x0 + 1][y1] != null) valid = false;
            } else {
                valid = x0 - x1 <= 2;
                if (Chess.board[x0 - 1][y1] != null) valid = false;
            }
            if (valid) {
                Chess.board[x1][y1] = Chess.board[x0][y0];
                Chess.board[x0][y0] = null;
                if (Math.abs(x1 - x0) == 2) setEnPassant(true);
            }
        } else {
            if (!isWhite()) valid = x1 - x0 == 1;
            else valid = x0 - x1 == 1;

            if (valid) {
                Chess.board[x1][y1] = Chess.board[x0][y0];
                Chess.board[x0][y0] = null;
                setEnPassant(false);
            }
        }
        return valid;

    }

    public String getType(){
        return "PAWN";
    }

    @Override
    public String toString() {
        return (isWhite()) ? "wp" : "bp";
    }

}

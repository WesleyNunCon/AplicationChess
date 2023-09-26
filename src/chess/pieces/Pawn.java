package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	private boolean canMove(Position postion) {

		ChessPiece p = (ChessPiece) getBoard().piece(postion);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		if (getColor() == Color.WHITE) {
			p.setValues(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsaPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			p.setValues(position.getRow() - 2, position.getColumn());
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsaPiece(p)
					&& (getBoard().positionExists(p2) && !getBoard().thereIsaPiece(p2)) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOppenentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOppenentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			// #specialMove en passant white
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOppenentPiece(left)
						&& getBoard().piece(left) == chessMatch.getenPassantVulnerable()) {
					mat[left.getRow() - 1][left.getColumn()] = true;
				}

				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(right) && isThereOppenentPiece(right)
						&& getBoard().piece(right) == chessMatch.getenPassantVulnerable()) {
					mat[right.getRow() - 1][right.getColumn()] = true;
				}
			}
		}

		else {

			p.setValues(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsaPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			p.setValues(position.getRow() + 2, position.getColumn());
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExists(p) && !getBoard().thereIsaPiece(p)
					&& (getBoard().positionExists(p2) && !getBoard().thereIsaPiece(p2)) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBoard().positionExists(p) && isThereOppenentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBoard().positionExists(p) && isThereOppenentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			// #specialMove en passant black
			if (position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExists(left) && isThereOppenentPiece(left)
						&& getBoard().piece(left) == chessMatch.getenPassantVulnerable()) {
					mat[left.getRow() + 1][left.getColumn()] = true;
				}

				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExists(right) && isThereOppenentPiece(right)
						&& getBoard().piece(right) == chessMatch.getenPassantVulnerable()) {
					mat[right.getRow() + 1][right.getColumn()] = true;
				}
			}

		}

		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}
}

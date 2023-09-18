package aplicationChess;

import chess.ChessMatch;

public class Aplication {

	public static void main(String[] args) {

		ChessMatch chessMatch = new ChessMatch();

		UI.printBoard(chessMatch.getPieces());
		
	}


}

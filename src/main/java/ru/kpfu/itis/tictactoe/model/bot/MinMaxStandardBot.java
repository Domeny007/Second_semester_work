package ru.kpfu.itis.tictactoe.model.bot;

import ru.kpfu.itis.tictactoe.model.game.*;
import ru.kpfu.itis.tictactoe.model.players.PlayerRole;
import ru.kpfu.itis.tictactoe.model.sessions.GameSession;
import ru.kpfu.itis.tictactoe.model.utils.StandardWinChecker;

import java.util.LinkedList;

public class MinMaxStandardBot extends StandardBot {
    class EstimatedMove {
        private Move move;
        private int value;
        private LinkedList<EstimatedMove> moves;
        private Field field;

        public EstimatedMove(int value) {
            this.value = value;
        }

        public Move getMove() {
            return move;
        }

        public EstimatedMove setMove(Move move) {
            this.move = move;
            return this;
        }

        public int getValue() {
            return value;
        }

        public EstimatedMove setValue(int value) {
            this.value = value;
            return this;
        }

        public LinkedList<EstimatedMove> getMoves() {
            return moves;
        }

        public void setMoves(LinkedList<EstimatedMove> moves) {
            this.moves = moves;
        }

        public void setField(Field field) {
            this.field = new Field(field);
        }

        @Override
        public String toString() {
            return value + " " + move;
        }
    }
    private CellState botCellState;
    private StandardWinChecker winChecker;

    public Point minMaxMove(Field field) {
        Point move = null;
        Field cloneField = new Field(field);
        winChecker = new StandardWinChecker(cloneField);
        LinkedList<Cell> emptyCells = getEmptyCells(field.getCells());

        EstimatedMove bestMove = minMax(cloneField, emptyCells, null, true);
        move = bestMove.getMove().getCellCoordinates();
        return move;
    }

    private EstimatedMove minMax(Field field, LinkedList<Cell> emptyCells, Move lastMove, boolean botMove) {
        PlayerRole winner = lastMove != null ? winChecker.getWinner(lastMove) : null;
        if (winner != null) {
            return winner == botRole ? new EstimatedMove(1) : new EstimatedMove(-1);
        }
        if (emptyCells.isEmpty()) {
            return new EstimatedMove(0);
        }
        EstimatedMove bestMove = new EstimatedMove(botMove ? -9999 : 9999);
        LinkedList<EstimatedMove> moves = new LinkedList<>();
        for (Cell emptyCell : emptyCells) {
            Move move = new Move(emptyCell.getCoordinates(), botMove ? botCellState : botCellState.inverse());
            field.registerMove(move);
            LinkedList<Cell> newEmptyCells = new LinkedList<>(emptyCells);
            newEmptyCells.remove(emptyCell);

            EstimatedMove tmp = minMax(field, newEmptyCells, move, !botMove);
            field.setCell(move.getCellCoordinates(), CellState.EMPTY);
            tmp.setMove(move);
            moves.add(tmp);
            if ((botMove && tmp.getValue() > bestMove.getValue()) ||
                    (!botMove && tmp.getValue() < bestMove.getValue())) {
                bestMove = tmp;
            }
        }
        return bestMove;
    }

    @Override
    public void makeMove() {
        Point move = minMaxMove(session.getGameField());
        if (move == null) {
            throw new NullPointerException();
        }
        session.makeMove(move);
    }

    @Override
    public void setSession(GameSession session) {
        super.setSession(session);
        botCellState = botRole == PlayerRole.X ? CellState.X : CellState.O;
    }

    public CellState getBotCellState() {
        return botCellState;
    }

    public void setBotCellState(CellState botCellState) {
        this.botCellState = botCellState;
    }
}

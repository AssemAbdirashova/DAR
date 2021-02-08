package com.example.tictac

class GameBoard {
    private val boardSize = 3
    private var myBoard  = Array(boardSize) { CharArray(boardSize) }
    fun getBoard() = myBoard

    fun isMatched(r: Int, c: Int):Boolean {
        return myBoard[r][c] == ' '
    }
    fun setMove(i: Int, j: Int, turn: Char){
        myBoard[i][j] = turn
    }

    fun gameOver(): Int{
        for(i in 0 until boardSize){
            if(rowCrossed(i, 'x') || rowCrossed(i, 'o')){
                return 1
            }
            if(columnCrossed(i, 'x') || columnCrossed(i, 'o')) {
                return 2
            }
            if(diagonalCrossed('x') || diagonalCrossed('o')) {
                return 3
            }
        }
        var boardFull = true
        for (i in 0 until boardSize) {
            for (j in 0 until boardSize) {
                if (myBoard[i][j] == ' ') boardFull = false
            }
        }
        return if (boardFull) -1
        else 0
    }
    fun fillBoard() {
        for (i in 0 until boardSize) {
            for (j in 0 until boardSize) {
                myBoard[i][j] = ' '
            }
        }
    }
    private fun columnCrossed(c: Int, turn: Char): Boolean{
        var cnt = 0
        for(i in 0 until boardSize)
            if(myBoard[i][c] == turn) cnt++
        return cnt == boardSize
    }

    private fun rowCrossed(r: Int, turn: Char): Boolean{
        var cnt = 0
        for(i in 0 until boardSize)
            if(myBoard[r][i] == turn) cnt++
        return cnt == boardSize
    }
    private fun diagonalCrossed(turn: Char): Boolean {
        var cnt = 0
        var cnt2 = 0
        for(i in 0 until boardSize)
            if (myBoard[i][i] == turn) cnt++
        for (i in 0 until boardSize)
            if (myBoard[i][boardSize-i-1] == turn) cnt2++
        return cnt == boardSize || cnt2 == boardSize
    }
}
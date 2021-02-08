package com.example.tictac.view
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TableRow
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tictac.GameBoard
import com.example.tictac.R
import com.example.tictac.UserRepository
import com.example.tictac.databinding.GameFragmentBinding

class GameFragment: BindingFragment<GameFragmentBinding>(GameFragmentBinding::inflate) {
    private val randomizer = (1..2).random()
    private var turn = 'x'
    private var firstPlayer = ""
    private var secondPlayer = ""
    private val args: GameFragmentArgs by navArgs()
    private val repository = UserRepository
    private var gameBoard =  GameBoard()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameBoard.fillBoard()
        setTurn()
        binding.run {
            Toast.makeText(context, "$firstPlayer is your turn", Toast.LENGTH_SHORT).show()
            for (i in 0 until glGameBoard.childCount) {
                val row: TableRow = glGameBoard.getChildAt(i) as TableRow
                for (j in 0 until row.childCount) {
                    val view = row.getChildAt(j)
                    view.setOnClickListener(Move(i, j, view))
                }
            }
        }
    }

    private fun Move(i: Int, j: Int, view: View): View.OnClickListener{
        return View.OnClickListener {
            if(gameBoard.isMatched(i, j )) {
                gameBoard.getBoard()[i][j] = turn
                if (turn == 'x') {
                    view.setBackgroundResource(R.drawable.ic_x)
                    turn = 'o'
                } else if (turn == 'o') {
                    view.setBackgroundResource(R.drawable.ic_zero)
                    turn = 'x'
                }
                if (gameBoard.gameOver() == 0) {
                    Log.d("go on", "play play and play")
                } else if (gameBoard.gameOver() == -1) {
                    Toast.makeText(context, "Ничья", Toast.LENGTH_SHORT).show()
                    stopGame()
                } else {
                    win(turn)
                    stopGame()
                }
            }
            else{
                Toast.makeText(context, "Нажми другое поле", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun win(whoseTurn: Char){
        if(whoseTurn == 'o'){
            Toast.makeText(context, "$firstPlayer win this game", Toast.LENGTH_SHORT).show()
            repository.getUser(args.user1).increaseWin()
            repository.getUser(args.user2).increaseLose()
        }
        else{
            Toast.makeText(context, "$secondPlayer win this game", Toast.LENGTH_SHORT).show()
            repository.getUser(args.user2).increaseWin()
            repository.getUser(args.user1).increaseLose()
        }
    }
    private fun stopGame(){
        binding.run {
            for (i in 0 until glGameBoard.childCount) {
                val row = glGameBoard.getChildAt(i) as TableRow
                for (j in 0 until row.childCount) {
                    val view = row.getChildAt(j)
                    view.setOnClickListener(null)
                }
            }
            val btnContinue = Button(context)
            btnContinue.text = "continue"
            glGameBoard.addView(btnContinue)
            btnContinue.setOnClickListener {
                findNavController().navigate(GameFragmentDirections.actionGameFragmentToRecordsFragment())
            }
        }
    }

    private fun setTurn(){
        if(randomizer == 1 ){
            firstPlayer = args.user1
            secondPlayer = args.user2
        }
        else{
            firstPlayer = args.user2
            secondPlayer = args.user1
        }
    }
}
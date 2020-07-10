package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main2.*

var PLAYER=true //ya toh pehle pplayer ki baari hogi ya dusre ki
var TURN_COUNT=0 //agr 9 times click ho gya toh game draw wrna koi na koi winner hai ya game abhi puri ni hui
var board_status=Array(3){IntArray(3)}
lateinit var board:Array<Array<Button>> //pehle create kiya

class MainActivity2 : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        DisplayScreen.text= intent.getStringExtra("Player1")+"'s Turn"

        //yahan initialise kiya array ke andr array with the views(Buttons)
        board= arrayOf(
            arrayOf(button,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button7,button8,button9)
        )

        for (i in board){
            for(b in i){
                b.setOnClickListener (this)  //this jo hai bulaega onclick function ko
            }
        }
        intializebaordstatus()

        resetBtn.setOnClickListener{
            PLAYER=true
            TURN_COUNT=0
            DisplayScreen.text= intent.getStringExtra("Player1")+"'s Turn"
            intializebaordstatus()
        }

        exitBtn.setOnClickListener {
            finish()
            System.exit(0)
        }
    }

    private fun intializebaordstatus() {
        for(i in 0..2) {
            for (j in 0..2) {
                board_status[i][j] = -1
            }
        }//abhi koi button click ni hua toh sbko -1 krdiya board status mey

        for(i in board) {
            for (b in i) {
                //ab agr koi button click nhi hua toh uss button ko enable krdo
                b.isEnabled = true
                b.text =""
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id){ //Koi bhi button agr click hoga, button he ek view hai
            R.id.button ->{
                updateValue(row=0,col=0,player= PLAYER)
            }
            R.id.button2 ->{
                updateValue(row=0,col=1,player= PLAYER)
            }
            R.id.button3 ->{
                updateValue(row=0,col=2,player= PLAYER)
            }
            R.id.button4 ->{
                updateValue(row=1,col=0,player= PLAYER)
            }
            R.id.button5 ->{
                updateValue(row=1,col=1,player= PLAYER)
            }
            R.id.button6 ->{
                updateValue(row=1,col=2,player= PLAYER)
            }
            R.id.button7 ->{
                updateValue(row=2,col=0,player= PLAYER)
            }
            R.id.button8 ->{
                updateValue(row=2,col=1,player= PLAYER)
            }
            R.id.button9 ->{
                updateValue(row=2,col=2,player= PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER= !PLAYER
        if(PLAYER){
            updatePlayerName(intent.getStringExtra("Player1")+"'s Turn")
        }
        else{
            updatePlayerName(intent.getStringExtra("Player2")+"'s Turn")
        }
        if(TURN_COUNT==9) {
            updatePlayerName("Game Draw")
        }

        checkWinner()
    }

    private fun checkWinner() {
        //Horizontal rows
        for(i in 0..2){
            if(board_status[i][0]== board_status[i][1] && board_status[i][0]== board_status[i][2]){
                if(board_status[i][0]==1){
                    updatePlayerName("Winner is "+  intent.getStringExtra("Player1"))
                    break
                }
                else if(board_status[i][0]==0){
                    updatePlayerName("Winner is "+intent.getStringExtra("Player2"))
                    break
                }
            }
        }
        //Vertical Columns
        for(i in 0..2){
            if(board_status[0][i]== board_status[1][i] && board_status[0][i]== board_status[2][i]){
                if(board_status[0][i]==1){
                    updatePlayerName("Winner is "+  intent.getStringExtra("Player1"))
                    break
                }
                else if(board_status[0][i]==0){
                    updatePlayerName("Winner is "+ intent.getStringExtra("Player2"))
                    break
                }
            }
        }
        //First Diagonal
        if(board_status[0][0]== board_status[1][1] && board_status[0][0]== board_status[2][2]){
            if(board_status[0][0]==1){
                updatePlayerName("Winner is "+  intent.getStringExtra("Player1"))
            }
            else if(board_status[0][0]==0){
                updatePlayerName("Winner is "+ intent.getStringExtra("Player2"))
            }
        }
        //Second Diagonal
        if(board_status[0][2]== board_status[1][1] && board_status[0][2]== board_status[2][0]){
            if(board_status[0][2]==1){
                updatePlayerName("Winner is "+  intent.getStringExtra("Player1"))
            }
            else if(board_status[0][2]==0){
                updatePlayerName("Winner is "+intent.getStringExtra("Player2"))
            }
        }
    }
    private fun disableButton(){
        for( i in board){
            for(b in i){
                b.isEnabled=false
            }
        }
    }

    private fun updatePlayerName(text: String) {
        DisplayScreen.text= text //Displayscreen is text view
        if(text.contains("Winner")){
            disableButton()
        }
    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val text= if(player)"X" else "0"
        val value= if(player) 1 else 0
        board[row][col].apply {
            isEnabled=false
            setText(text)
        }
        board_status[row][col]=value
    }
}



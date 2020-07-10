package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Playbtn.setOnClickListener {
            val p1:String=Player1.text.toString()
            val p2:String=Player2.text.toString()
            val i= Intent(this, MainActivity2::class.java)
            i.putExtra("Player1",p1)
            i.putExtra("Player2",p2)
            startActivity(i)
        }


    }
}
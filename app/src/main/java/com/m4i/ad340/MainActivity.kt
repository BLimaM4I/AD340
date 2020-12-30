package com.m4i.ad340

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // region of setup methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zipCodeEditText : EditText =  findViewById(R.id.zipCodeEditText)
        val enterButton : Button = findViewById(R.id.ButtonSubmitZipCode)
        enterButton.setOnClickListener {
            Toast.makeText(this,"Amo-te muito. Diverte-te hoje com a Blenda. Sem segundas intenções da minha parte :)))",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }
    // endregion of setup methods

    // region teardown methods

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    // endregion teardown methods

}
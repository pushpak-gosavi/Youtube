package com.playlist.youtube

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class FirstActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var edtURL:EditText
    lateinit var btnURL: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        findView()
    }
    fun findView(){
        edtURL= findViewById(R.id.edtURL)
        btnURL= findViewById(R.id.btnURL)
        btnURL.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.btnURL -> {
                if(validateET()) {
                    var intent: Intent = Intent(this@FirstActivity, MainActivity::class.java)
                    intent.putExtra("video_id", edtURL.text.toString().takeLast(11))
                    startActivity(intent)
                }
            }
        }
    }
   private fun validateET():Boolean{
        if(edtURL.text.toString().isEmpty()){
            Toast.makeText(this@FirstActivity,"Please pest the URL",Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
}
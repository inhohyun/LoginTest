package com.ihh.logintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button : Button = findViewById(R.id.loginBtn)
        val id : EditText = findViewById(R.id.emailArea)
        val pw : EditText = findViewById(R.id.passwordArea)


        button.setOnClickListener {
            var textId = id.text.toString()
            var textPw = pw.text.toString()

            //gson 통신을 할 retrofit 객체 생성
            var retrofit = Retrofit.Builder()
                .baseUrl("연결 할 서버의 url")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            var loginService = retrofit.create(LoginService::class.java)


            //interface를 활용해 웹 통신하기
            loginService.requestLogin(textId, textPw).enqueue(object : Callback<Login>{
                //웹통신 성공
                override fun onResponse(call: Call<Login>, response: Response<Login>) {
                    Toast.makeText(this@MainActivity, "웹 통신 성공", Toast.LENGTH_LONG).show()
                    var Login = response.body()// : 서버에서 보내줄 데이터 받기

                    if (Login?.isSueccess == "sueccess"){
                        var dialog = AlertDialog.Builder(this@MainActivity)
                        dialog.setTitle("로그인 성공")
                        dialog.setMessage("로그인에 성공하셨습니다.")
                        dialog.show()
                    }
                    else{
                        var dialog = AlertDialog.Builder(this@MainActivity)
                        dialog.setTitle("로그인 실패")
                        dialog.setMessage("로그인에 실패하셨습니다.")
                        dialog.show()
                    }

                }
                //웹통신 실패
                override fun onFailure(call: Call<Login>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "웹 통신 실패", Toast.LENGTH_LONG).show()

                    }

            })
        }

    }
}
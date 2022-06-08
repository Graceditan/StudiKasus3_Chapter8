package binar.and.lima.studikasus3_chapter8

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import binar.and.lima.studikasus3_chapter8.model.GetAllUserItem
import binar.and.lima.studikasus3_chapter8.network.ApiClient
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var sf : SharedPreferences
    lateinit var dataUser : List<GetAllUserItem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val dataUser = getSharedPreferences("datauser" , Context.MODE_PRIVATE)
        if (dataUser.contains("username")){
            startActivity(Intent(this, MainActivity::class.java))
        }
        logindaftar.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
        btnlogin.setOnClickListener {
            login()
        }
        sf = this.getSharedPreferences("datauser", Context.MODE_PRIVATE)
    }
    private fun login(){
        ApiClient.instance.allUser()
            .enqueue(object : Callback<List<GetAllUserItem>> {
                override fun onResponse(
                    call: Call<List<GetAllUserItem>>,
                    response: Response<List<GetAllUserItem>>
                ) {
                    val nama = loginemail.text.toString()
                    val  password = loginpassword.text.toString()

                    if (response.isSuccessful){
                        dataUser = response.body()!!
                        for(i in dataUser.indices){
                            if (nama == dataUser[i].username && password == dataUser[i].password) {
                                sf = getSharedPreferences("datauser" , Context.MODE_PRIVATE)
                                val sfe = sf.edit()
                                sfe.putString("username", nama).apply()
                                Toast.makeText(this@LoginActivity, "Welcome", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                            }
                        }
                    }else{
                        Toast.makeText(this@LoginActivity, "gagal", Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<List<GetAllUserItem>>, t: Throwable) {
                    Toast.makeText(this@LoginActivity,t.message, Toast.LENGTH_LONG).show()
                }
            })
    }

}
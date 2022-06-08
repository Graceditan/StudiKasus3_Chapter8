package binar.and.lima.studikasus3_chapter8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import binar.and.lima.studikasus3_chapter8.model.GetAllUserItem
import binar.and.lima.studikasus3_chapter8.model.ResponseRegister
import binar.and.lima.studikasus3_chapter8.network.ApiClient
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnregis.setOnClickListener {
            GlobalScope.launch {
                register()
            }

            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun register(){
        val nama = regisemail.text.toString()
        val pass = regispassword.text.toString()
        val user = regisusername.text.toString()

        ApiClient.instance.postDataUser(ResponseRegister(nama,pass,user))
            .enqueue(object : Callback<GetAllUserItem>{
                override fun onResponse(
                    call: Call<GetAllUserItem>,
                    response: Response<GetAllUserItem>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(this@RegisterActivity,response.message(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<GetAllUserItem>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity,t.message, Toast.LENGTH_LONG).show()
                }

            })
    }
    override fun onBackPressed() {
        super.onBackPressed()
        }
}
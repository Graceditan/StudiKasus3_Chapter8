package binar.and.lima.studikasus3_chapter8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import binar.and.lima.studikasus3_chapter8.model.GetAllNewsResponseItem
import binar.and.lima.studikasus3_chapter8.network.ApiClient
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(this,"Welcome", Toast.LENGTH_LONG).show()
        getdataNews()

        btnloginmain.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }

    private fun getdataNews(){
        ApiClient.instance.getAllNews()
            .enqueue(object : Callback<List<GetAllNewsResponseItem>>
            {
                override fun onResponse(
                    call: Call<List<GetAllNewsResponseItem>>,
                    response: Response<List<GetAllNewsResponseItem>>
                ) {
                    if (response.isSuccessful){
                        val dataNews = response.body()
                        val adapterNews = AdapterNews(dataNews!!){
                            val pindah = Intent(applicationContext, DetailActivity::class.java)
                            pindah.putExtra("detailnews",it)
                            startActivity(pindah)
                        }
                        val lm = LinearLayoutManager(applicationContext,
                            LinearLayoutManager.VERTICAL,false)
                        rv_news.layoutManager=lm
                        rv_news.adapter = adapterNews

                    }else{
                        Toast.makeText(this@MainActivity,response.message(), Toast.LENGTH_LONG).show()
                    }


                }

                override fun onFailure(call: Call<List<GetAllNewsResponseItem>>, t: Throwable) {
                    Toast.makeText(this@MainActivity,t.message, Toast.LENGTH_LONG).show()
                }
            }

            )
    }

}
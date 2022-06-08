package binar.and.lima.studikasus3_chapter8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import binar.and.lima.studikasus3_chapter8.model.GetAllNewsResponseItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        Toast.makeText(this,"Halaman Detail", Toast.LENGTH_SHORT).show()
        val detailnews = intent.getParcelableExtra("detailnews") as GetAllNewsResponseItem?
        tv_detailjudul.text = detailnews?.title
        detail_writer.text = detailnews?.author
        detail_date.text = detailnews?.createdAt
        detail_keterangan.text = detailnews?.description
        Glide.with(this).load(detailnews?.image).into(image_detail)
    }
}


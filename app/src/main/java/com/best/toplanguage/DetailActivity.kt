package com.best.toplanguage

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    companion object {
        const val KEY_COLLECTION = "key_collection"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        val dataCollection = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Collection>(KEY_COLLECTION, Collection::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Collection>(KEY_COLLECTION)
        }


        val tvDetailName: TextView = findViewById(R.id.tx_name)
        val tvSubDeailDescription: TextView = findViewById(R.id.tx_sub_description)
        val imgDetailPhoto: ImageView = findViewById(R.id.img_detail)

        tvDetailName.text = dataCollection!!.name
        tvSubDeailDescription.text = dataCollection.description
        imgDetailPhoto.setImageResource(dataCollection.photo)

        val btnActionShareTwo: Button = findViewById(R.id.action_share)

        btnActionShareTwo.setOnClickListener {
            val appMsg: String = "Hey !, Check out this app for share Button: " +
                    "https://play.google.com/store/apps/details?id=com.best.toplanguage"
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_TEXT, appMsg)
            shareIntent.type = "text/plan"
            startActivity(shareIntent)
        }
    }
}
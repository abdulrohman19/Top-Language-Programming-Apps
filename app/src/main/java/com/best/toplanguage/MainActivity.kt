package com.best.toplanguage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvFirst: RecyclerView
    private val list = ArrayList<Collection>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(2000)
        installSplashScreen()
        setContentView(R.layout.activity_main)


        rvFirst = findViewById(R.id.rv_first)
        rvFirst.setHasFixedSize(true)

        list.addAll(getListCollection())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cardview, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvFirst.layoutManager = LinearLayoutManager(this)
            }

            R.id.action_grid -> {
                rvFirst.layoutManager = GridLayoutManager(this, 2)
            }

            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, MenuAbout::class.java)
                startActivity(moveIntent)
            }

            R.id.action_share_menu -> {
                shareApp()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareApp() {
        val appMassanger: String = "Hey !, Check out this app for share Button :- " +
                "https://play.google.com/store/apps/details?id=com.best.toplanguage"
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, appMassanger)
        intent.type = "text/plain"
        startActivity(intent)
    }

    private fun getListCollection(): ArrayList<Collection> {
        val dataLanguage = resources.getStringArray(R.array.data_language)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listCollection = ArrayList<Collection>()
        for (i in dataLanguage.indices) {
            val collection =
                Collection(dataLanguage[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listCollection.add(collection)
        }
        return listCollection
    }

    private fun showRecyclerList() {
        rvFirst.layoutManager = LinearLayoutManager(this)
        val listCollectionAdapter = ListCollectionAdapter(list)
        rvFirst.adapter = listCollectionAdapter

        listCollectionAdapter.setOnItemClickCallback(object :
            ListCollectionAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Collection) {
                showSelectedCollection(data)
            }
        })
    }

    private fun showSelectedCollection(collection: Collection) {
        Toast.makeText(this, "You Choose " + collection.name, Toast.LENGTH_SHORT).show()
    }
}
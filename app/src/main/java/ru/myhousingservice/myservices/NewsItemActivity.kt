package ru.myhousingservice.myservices

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import ru.myhousingservice.myservices.databinding.ActivityNewsItemBinding

class NewsItemActivity : AppCompatActivity() {
    private lateinit var binding            : ActivityNewsItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        binding = ActivityNewsItemBinding.inflate(this.layoutInflater)

        when (item.itemId) {
            R.id.profile -> {
                val intent = Intent(this, UserProfileActivity::class.java)
                startActivity(intent)
            }
            R.id.news -> {
                val intent = Intent(this, NewsListActivity::class.java)
                startActivity(intent)
            }
            R.id.tickets -> {
                val intent = Intent(this, TicketsListActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
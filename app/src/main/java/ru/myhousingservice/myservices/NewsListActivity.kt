package ru.myhousingservice.myservices

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import ru.myhousingservice.myservices.databinding.ActivityNewsListBinding

class NewsListActivity : AppCompatActivity() {
    private lateinit var binding            : ActivityNewsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        binding = ActivityNewsListBinding.inflate(this.layoutInflater)

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
//                val intent = Intent(this, TicketsActivity::class.java)
//                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
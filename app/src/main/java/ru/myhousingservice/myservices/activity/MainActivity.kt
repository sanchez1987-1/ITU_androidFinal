package ru.myhousingservice.myservices.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import ru.myhousingservice.myservices.R
import ru.myhousingservice.myservices.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding            : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(this.layoutInflater)
        binding.goNews.setOnClickListener {
            val intent = Intent(this, CActivityList::class.java)
            startActivity(intent)
        }
        binding.goTickets.setOnClickListener {
            val intent = Intent(this, CActivityTicketsList::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        binding = ActivityMainBinding.inflate(this.layoutInflater)

        when (item.itemId) {
            R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.profile -> {
                val intent = Intent(this, CActivityUserProfile::class.java)
                startActivity(intent)
            }
            R.id.news -> {
                val intent = Intent(this, CActivityList::class.java)
                startActivity(intent)
            }
            R.id.tickets -> {
                val intent = Intent(this, CActivityTicketsList::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
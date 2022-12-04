package ru.myhousingservice.myservices

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.myhousingservice.myservices.databinding.ActivityTicketsListBinding

class TicketsListActivity : AppCompatActivity() {
    private lateinit var binding            : ActivityTicketsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets_list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TicketsAdapter(fillList())
    }

    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        (0..30).forEach { i -> data.add("$i element") }
        return data
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        binding = ActivityTicketsListBinding.inflate(this.layoutInflater)

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
                val intent = Intent(this, CActivityList::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
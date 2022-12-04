package ru.myhousingservice.myservices

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import ru.myhousingservice.myservices.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding            : ActivityMainBinding
    private lateinit var resultLauncher : ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                // There are no request codes
                val data: Intent? = result.data
                //doSomeOperations()
            }
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
                resultLauncher.launch(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
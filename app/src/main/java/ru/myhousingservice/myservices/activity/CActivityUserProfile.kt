package ru.myhousingservice.myservices.activity

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import ru.myhousingservice.myservices.R
import ru.myhousingservice.myservices.databinding.ActivityMainBinding
import ru.myhousingservice.myservices.databinding.ActivityTicketsListBinding
import ru.myhousingservice.myservices.databinding.ActivityUserProfileBinding

class CActivityUserProfile : AppCompatActivity() {
    private lateinit var resultLauncherPermission
            : ActivityResultLauncher<Array<String>>

    private lateinit var binding            : ActivityUserProfileBinding

    //Ссылка а объект для работы с настройками приложения.
    private lateinit var pref               : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding                             = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_user_profile)

        binding.logout.setOnClickListener {
            doLogout()
        }
    }
    /****************************************************************************************************
     * Проверка наличия и запрос необходимых разрешений.                                                *
     ***************************************************************************************************/
    private fun checkAndRequestPermissions()
    {
        val allPermissions                  = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
        val permissionsToAsk                = allPermissions
            .filter {
                return@filter ContextCompat.checkSelfPermission(
                    this,
                    it
                ) == PackageManager.PERMISSION_DENIED
            }
        if (permissionsToAsk.isNotEmpty())
            resultLauncherPermission.launch(
                permissionsToAsk.toTypedArray()
            )
    }

    private fun doLogout()
    {
        //Сохраняем в файл с настройками приложения факт отсутствия учётной записи.
        with (pref.edit()) {
            putString(getString(R.string.KEY_USER_NAME), "")
            apply()
        }
        //Закрываем данную активность.
        finish()
        //Опционально можем вызвать активность ввода учётных данных.
        val intent                  = Intent(this, CActivityLogin::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        binding = ActivityUserProfileBinding.inflate(this.layoutInflater)

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
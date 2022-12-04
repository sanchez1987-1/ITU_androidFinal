package ru.myhousingservice.myservices

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import ru.myhousingservice.myservices.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {
    private lateinit var resultLauncherPermission
            : ActivityResultLauncher<Array<String>>

    private lateinit var binding            : ActivityUserProfileBinding

    //Ссылка а объект для работы с настройками приложения.
    private lateinit var pref               : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
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
    /****************************************************************************************************
     * Привязка файла с описанием структуры меню к данной активности при создании активности.           *
     ***************************************************************************************************/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_activity_list, menu)
        return true
    }
    /****************************************************************************************************
     * Обработка нажатия на элементы меню.                                                              *
     ***************************************************************************************************/
    override fun onOptionsItemSelected(
        item                                : MenuItem
    )                                       : Boolean
    {
        return when (item.itemId) {
            R.id.miLogout -> {
                doLogout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    /****************************************************************************************************
     * Обработка нажатия кнопки "Выход из учётной записи" в меню.                                       *
     ***************************************************************************************************/
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
        val intent                  = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
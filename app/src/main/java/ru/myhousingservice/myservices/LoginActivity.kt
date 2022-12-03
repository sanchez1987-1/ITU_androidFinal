package ru.myhousingservice.myservices

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import ru.myhousingservice.myservices.databinding.ActivityLoginBinding

/********************************************************************************************************
 * Активность для ввода учётных данных.                                                                 *
 *******************************************************************************************************/

class LoginActivity : AppCompatActivity() {
    //Ссылка на объект для работы с настройками приложения.

    private lateinit var pref               : SharedPreferences
    private lateinit var binding            : ActivityLoginBinding

    /****************************************************************************************************
     * Обработка события создания объекта активности.                                                   *
     ***************************************************************************************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Приложение", "Активность открывается")
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonLogin.setOnClickListener {
//            val text = "Пора покормить кота!"
//            val duration = Toast.LENGTH_SHORT
//            val toast = Toast.makeText(applicationContext, text, duration)
//            toast.show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}


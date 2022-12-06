package ru.myhousingservice.myservices.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.myhousingservice.myservices.CApplication
import ru.myhousingservice.myservices.R
import ru.myhousingservice.myservices.databinding.ActivityObjectInfoBinding
import ru.myhousingservice.myservices.viewmodels.CViewModelTicketsFactory
import ru.myhousingservice.myservices.viewmodels.CViewModelTicketsInfo
import java.util.*

/********************************************************************************************************
 * Активность с отображением информации по одному объекту.                                              *
 *******************************************************************************************************/
class CActivityTicketsInfo                   : AppCompatActivity()
{
    //Объект с сылками на элементы графического интерфейса пользователя.
    private lateinit var binding            : ActivityObjectInfoBinding

    //Получение ссылки на экземляр класса CViewModelObjectInfo
    private val viewModel                   : CViewModelTicketsInfo by viewModels {
        CViewModelTicketsFactory((application as CApplication).repositoryTicket)
    }

    /****************************************************************************************************
     * Обработка события создания объекта активности.                                                   *
     ***************************************************************************************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Связь объектов, реализующих внешний вид с объектом активности.
        binding                             = ActivityObjectInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Получение переданных параметров
        intent.extras?.let{
            viewModel.setId(UUID.fromString(it.getString(getString(R.string.KEY_OBJECT_ID))))
        }?:
        //Этот кусок кода выполняется, если intent.extras==null (т.е. параметры не переданы).
        run{
            //Говорим модели представления, что идентификатора объекта нет.
            viewModel.setId(null)
        }
        lifecycleScope.launch {
            viewModel.item.collect{
                binding.inputName.editText!!.setText(it.title)
            }
        }

        /************************************************************************************************
         * Обработка события нажатия кнопки "назад".                                                    *
         ***********************************************************************************************/
        onBackPressedDispatcher.addCallback(
            this /* lifecycle owner */,
            object                          : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    //Передаём модели представления содержимое полей с формы для записи в объект.
                    viewModel.save(
                        binding.inputName.editText?.text.toString()
                    )
                    finish()
                }
            }
        )
    }
}
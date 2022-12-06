package ru.myhousingservice.myservices.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ru.myhousingservice.myservices.databinding.RecyclerviewobjectsItemBinding
import ru.myhousingservice.myservices.model.CTickets

/********************************************************************************************************
 * Класс адаптер для списка элементов. Содержит логику выбора элементов, логику вывода информации       *
 * об элементе в строку списка.                                                                         *
 * @link https://developer.alexanderklimov.ru/android/views/recyclerview-kot.php                        *
 * @link https://medium.com/nuances-of-programming/kotlin-реализация-recyclerview-на-android-6c93981e9abf
 * @link https://medium.com/android-gate/recyclerview-item-click-listener-the-right-way-daecc838fbb9    *
 * @link https://antonioleiva.com/recyclerview-adapter-kotlin/                                          *
 * @author Селетков И.П. 2022 1101.                                                                     *
 *******************************************************************************************************/
class CRecyclerViewAdapterTickets
/********************************************************************************************************
 * Конструктор.                                                                                         *
 * @param onItemClickListener - обработчик кликов на элементы списка.                                   *
 * @param onItemRemoveListener - обработчик кликов на кнопку "удалить" элементов списка.                *
 *******************************************************************************************************/
    (
    private val onItemClickListener         : (CTickets) -> Unit,
    private val onItemRemoveListener        : (CTickets) -> Unit
)                                           : RecyclerView.Adapter<CRecyclerViewAdapterTickets.CTicketsHolderObject>()
{
    /****************************************************************************************************
     * Вспомогательный класс, оотвечающий за визуальное отображение одного элемента данных.             *
     ***************************************************************************************************/
    inner class CTicketsHolderObject
    /****************************************************************************************************
     * Конструктор.                                                                                     *
     * @param binding - объект, хранящий ссылки на элементы интерфейса, у которых указан идентификатор. *
     * @param onItemClickListener - обработчик кликов на элемент списка.                                *
     * @param onItemRemoveListener - обработчик кликов на кнопку "удалить" элемента списка.             *
     ***************************************************************************************************/
        (
        private val binding                 : RecyclerviewobjectsItemBinding,
        private val onItemClickListener     : (CTickets) -> Unit,
        private val onItemRemoveListener    : (CTickets) -> Unit
    )                                       : RecyclerView.ViewHolder(binding.root)
    {
        //Элемент данных, который отображается в текущем элементе списка.
        private lateinit var item           : CTickets
        init{
            //Обработка клика на все поля элемента, кроме кнопки с корзиной.
            binding.linearLayoutObject.setOnClickListener {
                onItemClickListener(item)
            }
            //Обработка клика на кнопку с корзиной
            binding.buttonRemove.setOnClickListener {
                onItemRemoveListener(item)
            }
        }

        /************************************************************************************************
         * Метод описывает логику вывода элемента данных в строку списка.                               *
         * @param newItem - элемент данных для вывода.                                                  *
         ***********************************************************************************************/
        fun bind(
            newItem                         : CTickets
        )
        {
            item                            = newItem
            binding.textViewName.text       = newItem.title
            binding.textViewDescription.text= newItem.description
        }
    }
    /****************************************************************************************************
     * Метод вызывается в момент создания новой строки в списке.                                        *
     * Указывает, какой файл с разметкой внешнего вида использовать.                                    *
     * @param parent - ссылка на родительский элемент - RecyclerView.                                   *
     ***************************************************************************************************/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CTicketsHolderObject {
        val binding                         = RecyclerviewobjectsItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return CTicketsHolderObject(
            binding,
            onItemClickListener,
            onItemRemoveListener
        )
    }

    /****************************************************************************************************
     * Метод вызывается в момент назначения элемента данных с порядковым номером position на вывод в    *
     * строке списка holder.                                                                            *
     * @param holder - строка списка с управляющими графическими элементами.                            *
     * @param position - порядковый номер элемента данных в списке.                                     *
     ***************************************************************************************************/
    override fun onBindViewHolder(holder: CTicketsHolderObject, position: Int) {
        holder.bind(differ.currentList[position])
    }
    /****************************************************************************************************
     * Возвращает актуальное количество элементов в списке.                                             *
     * @return общее количество элементов данных в списке.                                              *
     ***************************************************************************************************/
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    /****************************************************************************************************
     * Объект для автоматического поиска разницы в двух списках и обновления только нужных элементов.   *
     ***************************************************************************************************/
    private val differ                      = AsyncListDiffer(this,differCallback)
    /****************************************************************************************************
     * Обновление содержимого списка на форме в соответствии с передынным списком items.                *
     * @param items новый список элментов для отображения.                                              *
     ***************************************************************************************************/
    fun submitList(
        items                               : List<CTickets>
    ) //Ретранслирует вызов методу из объекта differ
            = differ.submitList(items)
    //Статическая часть класса,
    //поле differCallback будет доступно относительно самого класса,
    //а не его экземпляров.
    companion object {
        /************************************************************************************************
         * Анонимный объект, реализующий методы, необходимые для сравнения двух элементов.              *
         ***********************************************************************************************/
        private val differCallback          = object
            : DiffUtil.ItemCallback<CTickets>(){
            override fun areItemsTheSame(oldItem: CTickets, newItem: CTickets): Boolean {
                return  oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: CTickets, newItem: CTickets): Boolean {
                return oldItem == newItem
            }

        }
    }
}
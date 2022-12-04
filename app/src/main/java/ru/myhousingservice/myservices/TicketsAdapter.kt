package ru.myhousingservice.myservices

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.myhousingservice.myservices.databinding.ActivityTicketsListItemBinding

/********************************************************************************************************
 * Класс адаптер для списка элементов. Содержит логику выбора элементов, логику вывода информации       *
 * об элементе в строку списка.                                                                         *
 * @link https://developer.alexanderklimov.ru/android/views/recyclerview-kot.php                        *
 * @link https://medium.com/nuances-of-programming/kotlin-реализация-recyclerview-на-android-6c93981e9abf
 *******************************************************************************************************/
class TicketsAdapter(private val items: MutableList<CObject>, private val listener: IItemClickListener) :
    RecyclerView.Adapter<TicketsAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ActivityTicketsListItemBinding, private val listener: IItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var item           : CObject
        private var index                   : Int = -1
        init{
            binding.linearLayoutObject.setOnClickListener {
                listener.onItemClick(index, item)
            }
        }
        fun bind(
            newItem                         : CObject,
            position                        : Int
        )
        {
            index                           = position
            item                            = newItem
            binding.textViewName.text       = newItem.name
            binding.textViewDescription.text= newItem.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding                         = ActivityTicketsListItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface IItemClickListener
    {
        fun onItemClick(index : Int, item : CObject)
    }
}
package ru.myhousingservice.myservices

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.myhousingservice.myservices.R
import ru.myhousingservice.myservices.CRecyclerViewAdapterObjects
import ru.myhousingservice.myservices.databinding.ActivityListBinding
import ru.myhousingservice.myservices.CObject

/********************************************************************************************************
 * Активность с отображением списка объектов на карте.                                                  *
 *******************************************************************************************************/
class CActivityList : AppCompatActivity() {
    //Объект класса, содержащий сылки на управляющие графические элементы интерфейса пользователя.
    private lateinit var binding : ActivityListBinding

    /****************************************************************************************************
     * Обработка события создания объекта активности.                                                   *
     ***************************************************************************************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Связываем код актиности с файлом, описывающим внешний вид активности.
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Тестовый список объектов, которые будт выводится пользователю.
        val items = mutableListOf<CObject>()
        items.add(CObject("Кунгу́рская ледяна́я пеще́ра", "Одна из крупнейших карстовых пещер в Европейской части России, седьмая в мире гипсовая пещера по протяжённости. Протяжённость пещеры по данным на 2021 год составляет около 8153 м, из них около 2 километров оборудовано для посещений туристами."))
        items.add(CObject("Белого́рский Свято-Никола́евский монасты́рь", "Мужской монастырь на Белой горе в Кунгурском районе Пермского края. Относится к Пермской епархии Русской православной церкви. За строгость устава эту обитель некогда называли Уральским Афономм."))
        items.add(CObject("Пермский краеведческий музей","Старейший и крупнейший музей Пермского края. Насчитывает 600 000 единиц хранения и включает более 50 коллекций регионального, российского и мирового значения, в числе объектов музея 22 памятника истории и культуры, из них 16 памятников федерального значения и 6 местного значения."))
        items.add(CObject("Каменный город", "Это массив из песчаника, который за тысячи лет ветер превратил в скопление массивных столбов-останцев. Перед этим над естественной архитектурой потрудилась река, пробившая в скалах арки и расщелины, делающие естественное образование удивительно схожим с рукотворным городом. По аналогии ущелья названы улицами, а отдельные скалы имеют собственные имена. Местные прозвали достопримечательность Чертовым Городищем."))
        items.add(CObject("Хохловка", "Здесь находятся свезенные со всего края 23 объекта деревянного зодчества, расположившиеся на 35 гектарах музея под открытым небом у берега Камы. Внутри самих строений открыты выставки предметов местных ремесел и творчества, восстановлены интерьеры эпох, к которым относятся здания."))
        items.add(CObject("Усьвинские столбы", "Известняковый массив высотой 120 метров протянулся на километры по правому берегу Усьвы. Как туристический объект интересует скалолазов, спелеологов и любителей археологии. Здесь множество пещер и гротов, причем регулярно открываются новые: скала довольно сложна для восхождения и не вся обследована. Отдельная достопримечательность — Чертов Палец, вертикальный скальный выступ высотой 70 метров."))

        binding.rvObjects.layoutManager          = LinearLayoutManager(this)
        binding.rvObjects.adapter                = CRecyclerViewAdapterObjects(items)
    }
}
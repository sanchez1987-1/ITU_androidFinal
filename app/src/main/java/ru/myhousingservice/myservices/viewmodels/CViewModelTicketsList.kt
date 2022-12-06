package ru.myhousingservice.myservices.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.myhousingservice.myservices.model.CTickets
import ru.myhousingservice.myservices.repositories.CRepositoryTickets

/********************************************************************************************************
 * Модель представления (отображаемых данных и состояния) для активности списка объектов.               *
 *******************************************************************************************************/
class CViewModelTicketsList(
    private val repositoryTicket            : CRepositoryTickets
)                                           : ViewModel()
{
    //Поток со списками всех объектов.
    val allObjects                          : Flow<List<CTickets>>
            = repositoryTicket.getAll()

    //Статус загрузки данных с сервера
    private val _status                     = MutableStateFlow("")
    val status                              : StateFlow<String>
        get()                               = _status

    /************************************************************************************************
     * Загрузка данных с сервера.                                                                 *
     ***********************************************************************************************/
    fun getObjectsFromServer()
    {
        viewModelScope.launch(Dispatchers.IO) {
            //Вызываем метод загрузки из репозитория,
            //по результатам выполнения актуализируем статус.
            _status.value                   = repositoryTicket.updateFromServer()
        }
    }

    /************************************************************************************************
     * Удаление элемента из списка.                                                                 *
     ***********************************************************************************************/
    fun delete(
        item                                : CTickets
    )                                       = viewModelScope.launch {
        repositoryTicket.delete(item)
    }


}
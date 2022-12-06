package ru.myhousingservice.myservices.repositories

import androidx.annotation.WorkerThread
import ru.myhousingservice.myservices.dao.IDAOTickets
import ru.myhousingservice.myservices.model.CTickets
import java.util.UUID
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import ru.myhousingservice.myservices.utils.network.ITicketsAPI

/********************************************************************************************************
 * Репозиторий для работы с данными об объектах.                                                        *
 * @author Селетков И.П. 2022 1116.                                                                     *
 *******************************************************************************************************/
class CRepositoryTickets(
    private val daoObjects                  : IDAOTickets,
    private val ticketAPI                   : ITicketsAPI,
)
{
    /****************************************************************************************************
     * Получение списка всех элементов.                                                                 *
     ***************************************************************************************************/
    fun getAll()                            : Flow<List<CTickets>>
    {
        return daoObjects.getAllFlow()
    }
    suspend fun updateFromServer()          : String
    {
        return try{
            ticketAPI.getObjectsOnMap()
                .forEach { objectFromServer ->
                    daoObjects.getById(objectFromServer.id)?.let {
                        daoObjects.update(objectFromServer)
                    } ?: run {
                        daoObjects.insertAll(objectFromServer)
                    }
                }
            "Данные обновлены с сервера"
        }
        catch(e : Exception) {
            Log.d("OBJECTS_ON_MAP", "Не удалось загрузить данные с сервера!",e)
            "Не удалось загрузить данные с сервера!"
        }

    }

    /****************************************************************************************************
     * Получение элемента по идентификатору.                                                            *
     * @param id - идентификатор элемента для поиска.                                                   *
     * @return объект с идентификатором id или ??? (упадёт) в случае отсутствия.                        *
     ***************************************************************************************************/
    fun getById(
        id                                  : UUID
    )                                       = daoObjects.getByIdFlow(id)

    /****************************************************************************************************
     * Сохранение нового элемента в БД.                                                                 *
     * @param item - объект для сохранения.                                                             *
     ***************************************************************************************************/
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(
        item                                : CTickets
    ) {
        daoObjects.insertAll(item)
    }
    /****************************************************************************************************
     * Удаление существующего объекта.                                                                  *
     * @param item - объект для удаления.                                                               *
     ***************************************************************************************************/
    @WorkerThread
    suspend fun delete(
        item                            : CTickets
    ) {
        daoObjects.delete(item)
    }
    /****************************************************************************************************
     * Обновление существующего объекта.                                                                *
     * @param item - объект для обновления.                                                             *
     ***************************************************************************************************/
    suspend fun update(
        item                            : CTickets
    ) {
        daoObjects.update(item)
    }

}
package ru.myhousingservice.myservices

import android.app.Application
import ru.myhousingservice.myservices.repositories.CRepositoryTickets
import ru.myhousingservice.myservices.utils.network.ServiceAPIFactory
import ru.myhousingservice.myservices.utils.room.CDatabase

/********************************************************************************************************
 * Основной класс программы.                                                                            *
 *******************************************************************************************************/
class CApplicationTickets                          : Application()
{
    private val database by lazy { CDatabase.getDatabase(this) }
    val repositoryTicket by lazy {
        CRepositoryTickets(
            database.daoTickets(),
            ServiceAPIFactory.ticketAPIObjects
        )
    }
}
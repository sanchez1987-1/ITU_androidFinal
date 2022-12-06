package ru.myhousingservice.myservices

import android.app.Application
import ru.myhousingservice.myservices.repositories.CRepositoryObjects
import ru.myhousingservice.myservices.repositories.CRepositoryTickets
import ru.myhousingservice.myservices.utils.network.ServiceAPIFactory
import ru.myhousingservice.myservices.utils.room.CDatabase

/********************************************************************************************************
 * Основной класс программы.                                                                            *
 *******************************************************************************************************/
class CApplication                          : Application()
{
    private val database by lazy { CDatabase.getDatabase(this) }
    val repositoryObjects by lazy {
        CRepositoryObjects(
            database.daoObjects(),
            ServiceAPIFactory.serviceAPIObjects
        )
    }
    val repositoryTicket by lazy {
        CRepositoryTickets(
            database.daoTickets(),
            ServiceAPIFactory.ticketAPIObjects
        )
    }
}
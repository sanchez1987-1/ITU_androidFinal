package ru.myhousingservice.myservices

import android.app.Application
import ru.myhousingservice.myservices.repositories.CRepositoryObjects
import ru.myhousingservice.myservices.room.CDatabase

/********************************************************************************************************
 * Основной класс программы.                                                                            *
 *******************************************************************************************************/
class CApplication                          : Application()
{
    private val database by lazy { CDatabase.getDatabase(this) }
    val repositoryObjects by lazy { CRepositoryObjects(database.daoObjects()) }
}
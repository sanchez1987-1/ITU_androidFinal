package ru.myhousingservice.myservices.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

/********************************************************************************************************
 * Объект, который может быть отмечен на карте.                                                         *
 *******************************************************************************************************/
@Entity(
    tableName                               = "tickets"
)
class CTickets(
    /****************************************************************************************************
     * Идентификатор.                                                                                   *
     ***************************************************************************************************/
    @PrimaryKey
    var id                                  : UUID
    = UUID.randomUUID(),
    /****************************************************************************************************
     * Наименование объекта.                                                                            *
     ***************************************************************************************************/
    @ColumnInfo(
        name                                = "title"
    ) //Название поля в таблице из БД
    var title                                : String
    = "",
    /****************************************************************************************************
     * Описание объекта.                                                                                *
     ***************************************************************************************************/
    @ColumnInfo(
        name                                = "description"
    ) //Название поля в таблице из БД
    var description                         : String
    = "",
    @ColumnInfo(
        name                                = "date_tickets"
    ) //Название поля в таблице из БД
    var date_tickets                         : String
    = "",
    @ColumnInfo(
        name                                = "status"
    ) //Название поля в таблице из БД
    var status                         : Int
    = 0
)
{
    /****************************************************************************************************
     * Перевод в строку.                                                                                *
     ***************************************************************************************************/
    override fun toString(): String {
//        return "title: $title, description: $description, date_tickets: $date_tickets, status: $status"
        return "title: $title, description: $description"
    }
}
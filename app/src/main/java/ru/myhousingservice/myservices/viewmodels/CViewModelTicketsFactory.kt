package ru.myhousingservice.myservices.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.myhousingservice.myservices.repositories.CRepositoryTickets

class CViewModelTicketsFactory (
    private val repositoryTicket           : CRepositoryTickets
)                                           : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    )                                       : T
    {
        if (modelClass.isAssignableFrom(CViewModelTicketsList::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CViewModelTicketsList(repositoryTicket) as T
        }
        if (modelClass.isAssignableFrom(CViewModelTicketsInfo::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CViewModelTicketsInfo(repositoryTicket) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
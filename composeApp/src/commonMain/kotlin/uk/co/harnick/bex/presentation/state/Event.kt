package uk.co.harnick.bex.presentation.state

sealed interface Event {
    data class DisplayErrorReportMenu(val throwable: Throwable) : Event
    data class DisplayErrorSnackbar(val message: String, val throwable: Throwable) : Event
}
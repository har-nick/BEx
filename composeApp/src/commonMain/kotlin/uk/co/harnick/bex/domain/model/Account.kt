package uk.co.harnick.bex.domain.model

data class Account(
    val id: Long,
    val username: String,
    val avatarId: Long?,
    val displayName: String = username
)

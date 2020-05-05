package service.presentation.dto

import domain.entity.User

internal data class UserData(val id: String, val name: String, val address: String?) {

    companion object {
        fun of(user: User): UserData {
            return UserData(
                user.id.value(),
                user.name.value(),
                user.address?.value()
            )
        }
    }
}
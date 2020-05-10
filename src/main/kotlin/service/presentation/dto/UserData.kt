package main.kotlin.service.presentation.dto

import main.kotlin.domain.entity.User

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

    override fun toString(): String {
        return "User(id:$id, name:$name, address:$address"
    }
}
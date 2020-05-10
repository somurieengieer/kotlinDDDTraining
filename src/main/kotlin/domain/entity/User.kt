package main.kotlin.domain.entity

import main.kotlin.domain.value.circle.CircleName
import main.kotlin.domain.value.user.UserAddress
import main.kotlin.domain.value.user.UserId
import main.kotlin.domain.value.user.UserName
import java.util.*


class User(val id: UserId, val name: UserName, val address: UserAddress? = null) {

    // TODO: コンストラクタをprivateにしてstaticなファクトリメソッドだけをpublicにできないか？

    val isPremium: Boolean = true // Dummy（premiumの実装は後回し。一旦全員premium会員とする

    companion object {
        fun of(id: String, name: String, address: String? = null): User {
            return User(
                UserId(id),
                UserName(name),
                address?.let { UserAddress(address) }
            )
        }

        fun create(name: String, address: String? = null): User {
            return of(
                UUID.randomUUID().toString(),
                name,
                address
            )
        }
    }

    fun changeName(changedName: String): User {
        return User(id, UserName(changedName), address)
    }

    fun changeAddress(changedAddress: String): User {
        return User(id, name, UserAddress(changedAddress))
    }

    fun createCircle(circleName: CircleName): Circle {
        return Circle.of(this, circleName)
    }

    fun joinCircle(circle: Circle) {
        circle.addUser(this)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false

        return true
    }

    override fun toString(): String {
        return "$name($id)"
    }
}
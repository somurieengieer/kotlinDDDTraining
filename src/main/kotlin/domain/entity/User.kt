package main.kotlin.domain.entity

import main.kotlin.domain.value.UserAddress
import main.kotlin.domain.value.UserId
import main.kotlin.domain.value.UserName
import java.util.*


internal class User(val id: UserId, val name: UserName, val address: UserAddress? = null) {

    // TODO: コンストラクタをprivateにしてstaticなファクトリメソッドだけをpublicにできないか？

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
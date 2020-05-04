package entity

import value.UserId
import value.UserName


internal class User(private val id: UserId, private val name: UserName) {

    // TODO: コンストラクタをprivateにしてstaticなファクトリメソッドだけをpublicにできないか？

    companion object {
        fun of(id: Int, name: String): User {
            return User(
                UserId(id),
                UserName(name)
            )
        }
    }

    fun changeUserName(changedName: String): User {
        return User(id, UserName(changedName))
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
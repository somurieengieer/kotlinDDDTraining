package value


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

    override fun toString(): String {
        return "$name($id)"
    }
}
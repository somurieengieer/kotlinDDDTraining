package value

internal class UserId(val id: Int) {
    init {
        validate()
    }

    private fun validate() {
        takeUnless { id >= 100 }?.apply { throw AssertionError("ユーザーIDは100より大きい数値です。") }
    }

    override fun toString(): String {
        return id.toString()
    }
}
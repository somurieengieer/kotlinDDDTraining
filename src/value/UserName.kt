package value

internal class UserName(val name: String) {
    init {
        validate()
    }

    private fun validate() {
        takeUnless { name.length >= 3 }?.apply { throw AssertionError("ユーザー名は3文字以上です。") }
    }

    override fun toString(): String {
        return name
    }
}
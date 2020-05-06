package main.kotlin.domain.value

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

    fun value(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserName

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
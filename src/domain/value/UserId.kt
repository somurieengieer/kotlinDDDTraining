package domain.value

internal class UserId(val id: String) {
    init {
        validate()
    }

    private fun validate() {
        takeUnless { id.length >= 3 }?.apply { throw AssertionError("ユーザーIDは3桁以上です。") }
    }

    override fun toString(): String {
        return id.toString()
    }

    fun value(): String {
        return id
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserId

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
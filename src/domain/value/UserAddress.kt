package domain.value

internal class UserAddress(val address: String) {
    init {
        validate()
    }

    private fun validate() {
        takeUnless { address.length >= 3 }?.apply { throw AssertionError("住所は3文字以上です。") }
    }

    override fun toString(): String {
        return address
    }

    fun value(): String {
        return address
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserAddress

        if (address != other.address) return false

        return true
    }

    override fun hashCode(): Int {
        return address.hashCode()
    }
}
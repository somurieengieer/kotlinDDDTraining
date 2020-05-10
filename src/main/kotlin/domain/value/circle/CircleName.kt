package main.kotlin.domain.value.circle

class CircleName(val name: String) {

    init {
        validation()
    }

    private fun validation() {
        takeUnless { name.length >= 3 }?.let { throw AssertionError("サークル名は３文字以上です") }
    }

    fun value(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CircleName

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }


}
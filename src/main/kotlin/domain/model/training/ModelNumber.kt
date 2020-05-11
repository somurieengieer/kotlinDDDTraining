package main.kotlin.domain.value

internal class ModelNumber(val productCode: String, val branch: String, val lot: String) {
    init {
        // NULLセーフのみのため記述は特に特になし
    }

    override fun toString(): String {
        return "$productCode-$branch-$lot"
    }
}
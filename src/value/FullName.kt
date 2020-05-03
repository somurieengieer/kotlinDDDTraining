package value

// 何も付けないとpublic扱いになる。どこからでもアクセス可能。スコープが広すぎる
// internalは同一モジュールから参照可能。同一モジュールは一度にコンパイルされるファイル群内を指す
// privateはパッケージプライベート
// クラス名の右にコンストラクタのフィールド名を列挙する書き方をプライマリコンストラクタと呼ぶ
internal class FullName (val firstName: String, val lastName: String) {

    // コンストラクタが動くタイミングで呼ばれる。プライマリコンストラクタを使う場合でも有効
    init {
        validate()
    }

    private fun validate() {
        val reg = Regex("^[a-zA-Z]+$")
        takeUnless { reg.containsMatchIn(firstName) }?.let{ throw AssertionError("指名は英字のみ使用可能です") }
        takeUnless { reg.containsMatchIn(lastName) }?.let{ throw AssertionError("指名は英字のみ使用可能です") }
//        reg.containsMatchIn(lastName).takeUnless { throw AssertionError("指名は英字のみ使用可能です") }
//        reg.containsMatchIn(firstName).let { throw AssertionError("指名は英字のみ使用可能です") }
//        reg.containsMatchIn(lastName).let { throw AssertionError("指名は英字のみ使用可能です") }
    }

    fun fullName(): String {
        // ${〜}で文字列に変数埋め込み。型類推できる場合は{}を省略も可能
        return "${firstName} $lastName"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FullName

        if (firstName!= other.firstName) return false
        if (lastName!= other.lastName) return false

        return true
    }
}
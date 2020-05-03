import value.FullName
import java.lang.AssertionError

fun main() {

    // val はimmutableな変数を定義。nullは許容しない
    val greetingText1 = "Hello"

    // 方の後ろにスペースを付けるとnullを許容する
    var spacer: String? = " "

    // var はmutableな変数を定義
    var greetingText2 = "Kotlin"

    println(greetingText1 + spacer + greetingText2)

    // 型を定義。プリミティブな型は定義できないが、コンパイラがプリミティブ型に変換する
    var height: Int = 180
    var weight = 60 // 暗黙的にIntになる

    println("height is " + height + ", weight is " + weight)

    valueObject()
}

fun valueObject() {

    println("ValueクラスであるFullNameを実装")

    val fullName1 = FullName("john", "smith")
    println(fullName1.fullName())

    val fullName2 = FullName("john", "notSmith")
    // == はequalsメソッドを実行した結果を返す
    println("fullName1 equals fullName1 "+ (fullName1 == fullName1))
    println("fullName1 equals fullName2 "+ (fullName1 == fullName2))


    println("コンストラクタでValidationをかける")
    try {
        val badName = FullName("john", "notSmith3")

    } catch (e: AssertionError) {
        println("Error: $e")
    }
}
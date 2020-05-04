import domainService.TransportService
import domainService.UserService
import entity.Baggage
import entity.PhysicalDistributionBase
import entity.User
import value.FullName
import value.ModelNumber
import value.Money
import value.UserId

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

    entityObject()

    domainServiceObject()
}

fun valueObject() {

    println("-- ValueクラスであるFullNameを実装")

    val fullName1 = FullName("john", "smith")
    println(fullName1.fullName())

    val fullName2 = FullName("john", "notSmith")
    // == はequalsメソッドを実行した結果を返す
    println("fullName1 equals fullName1 " + (fullName1 == fullName1))
    println("fullName1 equals fullName2 " + (fullName1 == fullName2))


    println("-- コンストラクタでValidationをかける")
    try {
        val badName = FullName("john", "notSmith3")

    } catch (e: AssertionError) {
        println("Error: $e")
    }

    println("-- 機能を持ったMoneyクラス")
    val money1 = Money("JPY", 100)
    val money2 = Money("JPY", 220)
    println(money1.add(money2))

    println("-- 製造番号クラス")
    val modelNumber = ModelNumber("1234", "567", "9988")
    println(modelNumber)

    println("-- ユーザークラス")
    val user = User.of(1234, "ユーザーの名前")
    println(user)
    try {
        val user2 = User.of(5678, "名前")
    } catch (e: AssertionError) {
        println("Error: $e")
    }
}

fun entityObject() {

    println("Userクラス")
    val user1 = User.of(1234, "ユーザー名")
    println(user1)
    val user2 = user1.changeUserName("変更後のユーザー名")
    println(user2)
}

fun domainServiceObject() {

    println("--ユーザーサービスクラス")
    val userService = UserService()
    println(userService.exists(UserId(111)))

    println("--配送サービス")
    val baggage = Baggage("配送される荷物")
    val fromBase = PhysicalDistributionBase("拠点A")
    val toBase = PhysicalDistributionBase("拠点B")
    val transportService = TransportService()
    transportService.transport(fromBase, toBase, baggage)
}


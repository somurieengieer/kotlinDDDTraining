package main.kotlin

import main.kotlin.domain.entity.Baggage
import main.kotlin.domain.entity.PhysicalDistributionBase
import main.kotlin.domain.entity.User
import main.kotlin.domain.value.FullName
import main.kotlin.domain.value.ModelNumber
import main.kotlin.domain.value.Money
import main.kotlin.domain.value.user.UserId
import main.kotlin.infrastracture.UserRepository
import main.kotlin.infrastracture.inmemory.InMemoryUserRepository
import main.kotlin.service.application.user.UserRegisterService
import main.kotlin.service.domain.TransportService
import main.kotlin.service.domain.UserService

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

    repository()

    applicationService()
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
    val user = User.of("1234", "ユーザーの名前")
    println(user)
    try {
        val user2 = User.of("5678", "名前")
    } catch (e: AssertionError) {
        println("Error: $e")
    }
}

fun entityObject() {

    println()
    println("Userクラス")
    val user1 = User.of("1234", "ユーザー名")
    println(user1)
    val user2 = user1.changeName("変更後のユーザー名")
    println(user2)
}

fun domainServiceObject() {

    println()
    println("--ユーザーサービスクラス")
    val userService = UserService(UserRepository())
    println(userService.exists(UserId("111")))

    println("--配送サービス")
    val baggage = Baggage("配送される荷物")
    val fromBase = PhysicalDistributionBase("拠点A")
    val toBase = PhysicalDistributionBase("拠点B")
    val transportService = TransportService()
    transportService.transport(fromBase, toBase, baggage)
}

fun repository() {

    println()
    println("-- ユーザーリポジトリ")
    val userService = UserService(UserRepository())
    val user1 = User.of("111", "スズキ太郎")
    val user2 = User.of("222", "スズキ二郎")
    println("register: $user1")
    userService.register(user1)
    println("${user1.id} exists: ${userService.exists(user1.id)}")
    println("${user2.id} exists: ${userService.exists(user2.id)}")
    println("${user1.id} is: ${userService.find(user1.id)}")
    println("${user2.id} is: ${userService.find(user2.id)}")
    println("delete: $user1")
    userService.delete(user1)
    println("${user1.id} is: ${userService.find(user1.id)}")

    println("-- ユーザーリポジトリInMemoryテスト")
    val userServiceTest = UserService(InMemoryUserRepository())
    println("register: $user1")
    userServiceTest.register(user1)
    println("${user1.id} exists: ${userServiceTest.exists(user1.id)}")
    println("${user2.id} exists: ${userServiceTest.exists(user2.id)}")
    println("${user1.id} is: ${userServiceTest.find(user1.id)}")
    println("${user2.id} is: ${userServiceTest.find(user2.id)}")
    println("register: $user2")
    userServiceTest.register(user2)
    val allUser = userServiceTest.findAll()
    val allUserNames = allUser.joinToString()
    println("allUser are $allUserNames")
    println("delete: $user1")
    userServiceTest.delete(user1)
    println("${user1.id} is: ${userServiceTest.find(user1.id)}")
    println("${user2.id} is: ${userServiceTest.find(user2.id)}")

}

fun applicationService() {
    println()
    println("-- ユーザーアプリケーションサービス。ユーザー作成")
    val userApplicationService = UserRegisterService()
    val userName = "ユーザー名テスト"
    val user = userApplicationService.register(userName)
    println("created user: $user")
    try {
        userApplicationService.register(userName)
    } catch (e: AssertionError) {
        println("ユーザー（$user）は登録済みのため登録エラー。$e")
    }
}


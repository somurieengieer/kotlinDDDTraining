package main.kotlin

import main.kotlin.service.application.user.UserGetInfoService
import main.kotlin.service.application.user.UserRegisterService

fun main(args: Array<String>) {

    val registerService = UserRegisterService()
    val getInfoService = UserGetInfoService()

    while (true) {
        println("Please input user name")
        print("> ")
        var name = readLine()
        println("Please input user address")
        print("> ")
        var address = readLine()

        registerService.register(name!!, address)

        getInfoService.getAllUsers().forEach { userData -> println(userData) }

        println("Want to register more user? (y/n)")
        print("> ")
        val wantContinue = readLine()
        if (wantContinue == "n") {
            break
        }
    }
    println("finish.")
}

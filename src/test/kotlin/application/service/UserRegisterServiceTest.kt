package test.service.applications

import main.kotlin.service.application.UserRegisterService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class UserRegisterServiceTest : UserServiceCommonTest() {

    @Test
    fun register() {
        val userApplicationService = UserRegisterService()
        val userName = "ユーザー名テスト"
        val userData = userApplicationService.register(userName)
        assertEquals(userData.name, userName)

        assertEquals(getUserList().size, 1)
        assertEquals(getUserList()[0].id.value(), userData.id)
    }

    @Test
    fun register_failed_because_of_duplicate_userName() {
        val userApplicationService = UserRegisterService()
        val userName = "ユーザー名テスト"
        val user = userApplicationService.register(userName)

        try {
            userApplicationService.register(userName)
            kotlin.test.fail()
        } catch (e: AssertionError) {
            assertEquals("ユーザー（ユーザー名テスト）は既に登録済みです", e.message)
        }
    }

    @Test
    fun register_failed_because_of_duplicate_address() {
        val userApplicationService = UserRegisterService()
        val name = "ユーザー名テスト"
        val address = "住所１"
        val userData = userApplicationService.register(name, address)

        try {
            val name2 = "ユーザー名テスト２"
            userApplicationService.register(name2, address)
            kotlin.test.fail()
        } catch (e: AssertionError) {
            assertEquals("ユーザー（ユーザー名テスト２）は既に登録済みです", e.message)
        }
    }
}

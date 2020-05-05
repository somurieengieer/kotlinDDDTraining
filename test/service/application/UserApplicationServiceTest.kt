package service.application

import domain.value.UserId
import domain.value.UserName
import infrastracture.UserDB
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import service.application.command.UserAppCommand

internal class UserApplicationServiceTest {

    @BeforeEach
    fun before() {
        val userDB = UserDB
        userDB.clear()
    }

    @Test
    fun register() {
        val userApplicationService = UserApplicationService()
        val userName = "ユーザー名テスト"
        val userData = userApplicationService.register(userName)
        assertEquals(userData.name, userName)
    }

    @Test
    fun register_failed_because_of_duplicate_userName() {
        val userApplicationService = UserApplicationService()
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
        val userApplicationService = UserApplicationService()
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

    @Test
    fun get_success() {
        val userApplicationService = UserApplicationService()
        val userName = "ユーザー名テスト"
        val createdUserData = userApplicationService.register(userName)
        val gottenUser = userApplicationService.get(UserName(userName))
        assertNotNull(gottenUser)
        assertEquals(gottenUser?.id, createdUserData.id)
        assertEquals(gottenUser?.name, createdUserData.name)
    }

    @Test
    fun update_address_success() {
        val userApplicationService = UserApplicationService()
        val userName = "ユーザー名テスト"
        val createdUserData = userApplicationService.register(userName)

        val address = "ユーザー住所"
        val updatedUserData = userApplicationService.update(
            UserAppCommand(
                createdUserData.id,
                address = address
            )
        )
        assertEquals(address, updatedUserData.address)
        assertEquals(createdUserData.id, updatedUserData.id)
        assertEquals(createdUserData.name, updatedUserData.name)
    }

    @Test
    fun update_name_success() {
        val userApplicationService = UserApplicationService()
        val userName = "ユーザー名テスト"
        val createdUserData = userApplicationService.register(userName)

        val updatedUserName = "ユーザー名テストUpdate"
        val updatedUserData = userApplicationService.update(
            UserAppCommand(
                createdUserData.id,
                name = updatedUserName
            )
        )
        assertNull(updatedUserData.address)
        assertEquals(createdUserData.id, updatedUserData.id)
        assertEquals(updatedUserName, updatedUserData.name)
    }

    @Test
    fun delete_success() {
        val userApplicationService = UserApplicationService()
        val userName = "ユーザー名テスト"
        val createdUserData = userApplicationService.register(userName)

        val existsUser = userApplicationService.get(UserId(createdUserData.id))

        assertNotNull(existsUser)

        userApplicationService.delete(
            UserAppCommand(
                createdUserData.id
            )
        )

        val deletedUser = userApplicationService.get(UserId(createdUserData.id))

        assertNull(deletedUser)
    }
}
package service.application.circle

import main.kotlin.domain.entity.User
import main.kotlin.infrastracture.CircleDB
import main.kotlin.service.application.circle.CircleRegisterService
import main.kotlin.service.application.circle.command.CircleCreateCommand
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import test.service.applications.CircleServiceCommonTest

internal class CircleRegisterServiceTest : CircleServiceCommonTest() {

    @Test
    fun register() {
        val service = CircleRegisterService()
        val creatorId = "1111"
        val circleName = "サークル名"
        service.register(CircleCreateCommand(creatorId, circleName))

        assertEquals(CircleDB.getAll().size, 1)
        assertEquals(CircleDB.getAll()[0].creatorId.value(), creatorId)
        assertEquals(CircleDB.getAll()[0].circleName.value(), circleName)
    }

    @Test
    fun register_failed() {
        val service = CircleRegisterService()
        val creatorId = "1111"
        val circleName = "サー"
        try {
            service.register(CircleCreateCommand(creatorId, circleName))
            kotlin.test.fail()
        } catch (e: AssertionError) {
            assertEquals(e.message, "サークル名は３文字以上です")
        }
    }

    @Test
    fun addMember() {
        val circle = createCircle()
        val member = User.of("2222", "作成者１", "住所１")

        val service = CircleRegisterService()

        service.addMember(circle, member)

        assertEquals(CircleDB.getCircle(circle.circleId!!)!!.members.list()[0], member)
    }
}
package main.kotlin.infrastracture

import main.kotlin.domain.entity.Circle
import main.kotlin.domain.value.circle.CircleId
import main.kotlin.service.repository.ICircleRepository

// DBの代わり
internal object CircleDB {

    private var list: MutableList<Circle> = mutableListOf()

    fun getAll(): List<Circle> {
        return list
    }

    fun getCircle(circleId: CircleId): Circle? {
        return list.find { c -> c.circleId == circleId }
    }

    fun insert(circle: Circle) {
        list.add(circle)
    }

    fun delete(circle: Circle) {
        list.remove(circle)
    }

    fun clear() {
        list = mutableListOf()
    }
}

internal class CircleRepository : ICircleRepository {

    private val circleDB = CircleDB

    override fun create(circle: Circle): Circle {
        circleDB.insert(circle)
        return circle
    }

    override fun update(circle: Circle): Circle {
        circleDB.delete(circle)
        return create(circle)
    }

    override fun find(circleId: CircleId): Circle? {
        return circleDB.getAll().find { c -> c.circleId == circleId }
    }
}
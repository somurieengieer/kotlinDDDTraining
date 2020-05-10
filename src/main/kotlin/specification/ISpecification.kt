package main.kotlin.specification

interface ISpecification<T> {

    fun isSatisfiedBy(value: T): Boolean
}
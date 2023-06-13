package com.example.wallnoire.domain.mapper


@FunctionalInterface
interface Mapper<in DomainObject, out UIObject> {
    fun mapFrom(item: DomainObject): UIObject
}
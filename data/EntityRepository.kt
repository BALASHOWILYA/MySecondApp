package data

import domain.Entity

interface EntityRepository {
    fun updateEntity(entity: Entity): Boolean
} 
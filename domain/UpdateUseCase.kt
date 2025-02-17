package domain

interface UpdateUseCase {
    fun update(entity: Entity): Boolean
} 
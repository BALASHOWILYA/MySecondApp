package domain

import data.EntityRepository

class UpdateUseCaseImpl(private val repository: EntityRepository) : UpdateUseCase {
    override fun update(entity: Entity): Boolean {
        return repository.updateEntity(entity)
    }
} 
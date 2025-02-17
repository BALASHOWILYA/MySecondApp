package presentation

import domain.Entity
import domain.UpdateUseCase

class EntityViewModel(private val updateUseCase: UpdateUseCase) {

    fun updateEntity(entity: Entity): Boolean {
        return updateUseCase.update(entity)
    }
} 
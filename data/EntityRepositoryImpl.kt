package data

import domain.Entity

class EntityRepositoryImpl : EntityRepository {
    override fun updateEntity(entity: Entity): Boolean {
        // Реализация обновления данных
        // Например, обновление в базе данных
        println("Updating entity: $entity")
        return true // Возвращаем true, если обновление прошло успешно
    }
} 
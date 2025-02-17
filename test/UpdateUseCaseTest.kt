package test

import data.EntityRepositoryImpl
import domain.Entity
import domain.UpdateUseCaseImpl
import presentation.EntityViewModel

fun main() {
    val repository = EntityRepositoryImpl()
    val updateUseCase = UpdateUseCaseImpl(repository)
    val viewModel = EntityViewModel(updateUseCase)

    val entity = Entity(id = 1, name = "Test", value = "Value")
    val result = viewModel.updateEntity(entity)

    println("Update result: $result")
} 
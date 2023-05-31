package io.parrotsoftware.qatest.domain.usescases

import io.parrotsoftware.qatest.domain.repositories.UserRepository
import javax.inject.Inject

class GetStoreUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke() = userRepository.getStore()
}

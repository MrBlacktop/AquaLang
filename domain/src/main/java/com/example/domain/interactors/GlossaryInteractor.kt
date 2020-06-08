package com.example.domain.interactors

import com.example.domain.models.GlossaryWord
import com.example.domain.repositories.GlossaryRepository
import com.example.domain.repositories.UserRepository
import java.lang.Exception

class GlossaryInteractor(
    private val glossaryRepository: GlossaryRepository,
    private val userRepository: UserRepository
) {
    fun getGlossary() = glossaryRepository.getGlossary()

    suspend fun sync() {
        val userId = userRepository.getUserFromDb()?.id ?: throw Exception("Current user not found")
        val token = userRepository.getUserToken()
        glossaryRepository.sync(userId,token)
    }

    suspend fun deleteWord(glossaryWord: GlossaryWord){
        val token = userRepository.getUserToken()
        glossaryRepository.deleteWord(token,glossaryWord)
        sync()
    }

    suspend fun addWord(glossaryWord: GlossaryWord){
        val token = userRepository.getUserToken()
        glossaryRepository.addWord(token,glossaryWord)
        sync()
    }
}
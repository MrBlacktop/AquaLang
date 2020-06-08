package com.example.domain.repositories

import com.example.domain.models.GlossaryWord
import kotlinx.coroutines.flow.Flow

interface GlossaryRepository {
    fun getGlossary(): Flow<List<GlossaryWord>>

    suspend fun sync(userId: Int, token: String)

    suspend fun deleteWord(token: String, glossaryWord: GlossaryWord)

    suspend fun addWord(token: String, glossaryWord: GlossaryWord)
}
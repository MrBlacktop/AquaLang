package com.example.data.repositories

import android.util.Log
import com.example.data.database.AquaLangDatabaseDao
import com.example.data.database.models.asDbModel
import com.example.data.database.models.asDomainModel
import com.example.data.network.apiServices.GlossaryApiService
import com.example.data.network.models.asDbModel
import com.example.data.network.models.asWebModel
import com.example.domain.models.GlossaryWord
import com.example.domain.repositories.GlossaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GlossaryRepositoryImpl @Inject constructor(
    private val dao: AquaLangDatabaseDao,
    private val glossaryApiService: GlossaryApiService
) : GlossaryRepository {

    override fun getGlossary(): Flow<List<GlossaryWord>> {
        return dao.getGlossary().map { it.map { entity -> entity.asDomainModel() } }
    }

    override suspend fun sync(userId: Int, token: String) {
        withContext(Dispatchers.IO) {
            val glossary = glossaryApiService.getGlossaryAsync(userId, token).await()
            dao.insertGlossary(glossary.map { it.asDbModel() })
        }
    }

    override suspend fun deleteWord(token: String, glossaryWord: GlossaryWord) {
        withContext(Dispatchers.IO) {
            dao.deleteWord(glossaryWord.asDbModel())
            glossaryApiService.deleteWordAsync(token, glossaryWord.id)
        }
    }

    override suspend fun addWord(token: String, glossaryWord: GlossaryWord) {
        withContext(Dispatchers.IO) {
            glossaryApiService.postWordAsync(token, glossaryWord.asWebModel())
        }
    }
}
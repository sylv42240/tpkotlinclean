package com.example.tpkotlinclean.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.tpkotlinclean.data.networking.HttpClientManager
import com.example.tpkotlinclean.data.networking.api.CharacterAPI
import com.example.tpkotlinclean.data.networking.createApi
import com.example.tpkotlinclean.data.networking.datasource.CharacterDataSource
import com.example.tpkotlinclean.data.model.Character
import kotlinx.coroutines.CoroutineScope

private class CharacterRepositoryImpl(
    private val api: CharacterAPI
) : CharacterRepository {
    private val paginationConfig = PagedList.Config
        .Builder()
        .setEnablePlaceholders(false)
        .setPageSize(20)
        .build()
    override fun getPaginatedList(scope: CoroutineScope): LiveData<PagedList<Character>> {
        return LivePagedListBuilder(
            CharacterDataSource.Factory(api, scope),
            paginationConfig
        ).build()
    }
}
interface CharacterRepository {
    fun getPaginatedList(scope: CoroutineScope): LiveData<PagedList<Character>>
    companion object {
        val instance: CharacterRepository by lazy {
            CharacterRepositoryImpl(HttpClientManager.instance.createApi())
        }
    }
}
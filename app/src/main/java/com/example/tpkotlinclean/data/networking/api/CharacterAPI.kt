package com.example.tpkotlinclean.data.networking.api

import com.example.tpkotlinclean.data.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterAPI {

    @GET(GET_ALL_CHARACTER_PATH)
    suspend fun getAllCharacters(
        @Query("page") page: Int
    ): Response<List<Character>>

    companion object {
        const val GET_ALL_CHARACTER_PATH = "character/"
    }

}
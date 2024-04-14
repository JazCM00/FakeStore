package com.example.fakestore

import com.example.fakestore.model.modelCategoryResponse.CategoryResponse
import com.example.fakestore.model.modelCategoryResponse.CategoryResponseItem
import com.example.fakestore.service.ApiCategory
import com.example.fakestore.service.ApiProfiles
import com.example.fakestore.service.CategoryFactory
import com.example.fakestore.service.ProfileFactory
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    lateinit var apiProfileService: ApiProfiles
    lateinit var apiCategoryService: ApiCategory
    private val categoryInit = mutableListOf<CategoryResponseItem>()
    @Before
    fun onBefore(){
        apiProfileService = ProfileFactory.getProfileRetrofit()
        apiCategoryService = CategoryFactory.getCategoryRetrofit()
    }

    @Test
    fun devuelveDatosUsuarioLogeado () = runBlocking {
        val data = apiProfileService.getProfile(
            "profile",
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsImlhdCI6MTcxMjYyMDAzMSwiZXhwIjoxNzE0MzQ4MDMxfQ.DTisri1KIarLDvd5rex10mXfbN2oQma4MDyp93b6QJw"
        )
        assertTrue(data.name.isNotEmpty())
        assertTrue(data.role.isNotEmpty())

    }

    @Test
    fun devuelveDatosCategory () = runBlocking {
        val dataCategory: CategoryResponse = apiCategoryService.getCategory("categories")
        assertTrue(dataCategory.size>0)
    }

    }
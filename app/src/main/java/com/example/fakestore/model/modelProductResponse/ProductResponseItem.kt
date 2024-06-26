package com.example.fakestore.model.modelProductResponse

import com.example.fakestore.model.modelCategoryResponse.CategoryResponseItem
data class ProductResponseItem(
    val category: Category,
    val creationAt: String,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val title: String,
    val updatedAt: String
)
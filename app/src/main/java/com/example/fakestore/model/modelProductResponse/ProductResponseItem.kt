package com.example.fakestore.model.modelProductResponse
import com.example.fakestore.modelCategoryResponse.CategoryResponseItem
data class ProductResponseItem(
    val category: CategoryResponseItem,
    val creationAt: String,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val title: String,
    val updatedAt: String
)
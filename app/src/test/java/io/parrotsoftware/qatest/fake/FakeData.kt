package io.parrotsoftware.qatest.fake

import io.parrotsoftware.qanetwork.data.responses.ApiCredentials
import io.parrotsoftware.qanetwork.data.responses.ApiStore
import io.parrotsoftware.qanetwork.data.responses.ApiUserWithStores
import io.parrotsoftware.qatest.data.toProductEntity
import io.parrotsoftware.qatest.domain.models.Category
import io.parrotsoftware.qatest.domain.models.Credentials
import io.parrotsoftware.qatest.domain.models.Product
import io.parrotsoftware.qatest.domain.models.Store
import io.parrotsoftware.qatest.presentation.list.EnabledProduct
import io.parrotsoftware.qatest.presentation.list.ExpandableCategory

object FakeData {

    private const val access =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoicmVmcmVzaCIsImV4cCI6..."
    private const val refresh =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0b2tlbl90eXBlIjoiYWNjZXNzIiwiZXhwIjo..."

    private const val storeId = "e7f46731-1654-4ba3-be83-408ac5255838"

    private const val storeName = "Store Android Challenge"

    private const val userId = "c85c8719-8c14-478d-bab0-ffd55a0632e0"

    private const val userEmail = "android-challenge@parrotsoftware.io"

    private val category = Category("bbc22898-7bd3-4512-8b09-64c4e19d7a9b", "Combos Especiales", 99)

    private val product1 = Product(
        "2618ec65-f996-4b12-898b-b6cf1cc32384",
        "Combo Amigos",
        "2 Subs de 15 cm (elige entre Jamón de Pavo, Sub de Pollo",
        "https://d1ralsognjng37.cloudfront.net/b49451f6-4f81-404e-bb97-2e486100b2b8.jpeg",
        189.00f,
        false,
        category,
    )

    private val product2 = Product(
        "9d1e3446-f536-4842-8adf-8a06e96ab0a9",
        "Combo Amigos - COPIA",
        "2 Subs de 15 cm (elige entre Jamón de Pavo, Sub de Pollo o Atún)",
        "https://d1ralsognjng37.cloudfront.net/b49451f6-4f81-404e-bb97-2e486100b2b8.jpeg",
        189.00f,
        false,
        category,
    )

    fun givenStoreId() = storeId

    fun givenAccessToken() = access

    fun givenRefreshToken() = refresh

    fun givenCredentials() = Credentials(access, refresh)

    fun givenApiCredentials() = ApiCredentials(refresh, access)

    fun givenStore() = Store(storeId, storeName)

    fun givenApiStore() = ApiStore(storeId, storeName)

    fun givenApiUserWithStores() = ApiUserWithStores(userId, userEmail, listOf(givenApiStore()))

    fun givenListProduct() = listOf(product1, product2)

    fun givenListProductEntity() = givenListProduct().map { it.toProductEntity() }

    fun givenEnabledProduct() = EnabledProduct(product1, false)

    fun givenExpandableCategory() = ExpandableCategory(category, true, listOf(givenEnabledProduct()))
}

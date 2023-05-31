package io.parrotsoftware.qatest.data

import io.parrotsoftware.qa_network.domain.responses.ApiCategory
import io.parrotsoftware.qa_network.domain.responses.ApiProduct
import io.parrotsoftware.qa_network.domain.responses.ApiProductAvailability
import io.parrotsoftware.qatest.domain.models.Category
import io.parrotsoftware.qatest.domain.models.Product

fun ApiProduct.toProduct() = Product(
    uuid,
    name,
    description,
    imageUrl,
    price,
    availability == ApiProductAvailability.AVAILABLE,
    category.toCategory()
)

fun ApiCategory.toCategory() = Category(uuid, name, sortPosition)

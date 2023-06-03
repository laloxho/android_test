package io.parrotsoftware.qatest.presentation.list

interface CategoryListener {
    fun onCategorySelected(category: ExpandableCategory)
    fun onProductSelected(product: EnabledProduct)
    fun onProductSelected(product: String) { }
}

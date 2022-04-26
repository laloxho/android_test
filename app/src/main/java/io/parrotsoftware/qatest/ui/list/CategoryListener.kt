package io.parrotsoftware.qatest.ui.list

interface CategoryListener {
    fun onCategorySelected(category: ExpandableCategory)
    fun onProductSelected(product: EnabledProduct)
}
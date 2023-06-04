package io.parrotsoftware.qatest.presentation.list

sealed class ListViewState {

    object Loading : ListViewState()
    object ErrorLoadingItems : ListViewState()
    object ErrorUpdatingItem : ListViewState()
    object ItemUpdated : ListViewState()
    object InvalidSession : ListViewState()
    class ItemsLoaded(val categories: List<ExpandableCategory>) : ListViewState()
}

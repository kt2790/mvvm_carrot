package com.example.mvvmcarrot

import com.example.mvvmcarrot.model.Item
import com.example.mvvmcarrot.repository.ItemRepository
import com.example.mvvmcarrot.ui.viewmodel.DetailViewModel
import org.junit.Before
import org.junit.Test



class DetailViewModelTest {
    private lateinit var mockItemRepository: ItemRepository
    private lateinit var detailViewModel: DetailViewModel

    @Before
    fun init() {
        mockItemRepository = MockItemRepository()
        detailViewModel = DetailViewModel(mockItemRepository)
    }

}
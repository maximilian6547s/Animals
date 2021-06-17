package com.maximcuker.animal.di

import com.maximcuker.animal.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ViewModelComponent {

    fun inject(viewModel: ListViewModel)
}
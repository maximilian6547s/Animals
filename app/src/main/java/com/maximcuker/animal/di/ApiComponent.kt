package com.maximcuker.animal.di

import com.maximcuker.animal.model.AnimalApiService
import dagger.Component

@Component(modules = [ApiModule::class]) // need component interface to inject AnimalApi
interface ApiComponent {
    fun inject(service:AnimalApiService) //tells system where the functionality defined
}
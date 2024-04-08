package com.example.todoapp.model.module

import com.example.todoapp.model.iml.AccountServiceImpl
import com.example.todoapp.model.iml.StorageServiceImpl
import com.example.todoapp.model.service.AccountService
import com.example.todoapp.model.service.StorageService
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService

    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService


}
package com.melaniadev.rickandmorty.usecase

import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope

sealed class CommonUseCaseTest {
    protected val testCoroutineDispatcher = TestScope(StandardTestDispatcher())
}
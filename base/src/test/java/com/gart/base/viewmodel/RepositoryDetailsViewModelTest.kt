package com.gart.base.viewmodel

import android.app.Application
import com.gart.base.repository.GartRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations.initMocks

class RepositoryDetailsViewModelTest {

    private lateinit var viewModel: RepositoryDetailsViewModel

    @Mock
    private lateinit var mockApplication: Application
    @Mock
    lateinit var mockGartRepository: GartRepository

    @Before
    fun setUp() {
        initMocks(this)

        viewModel = RepositoryDetailsViewModel(mockApplication, mockGartRepository)
    }

    @Test
    fun getGithubRepository_when_the_viewModel_getGithubRepository_is_invoked() {
        val repositoryId = 1

        viewModel.getGithubRepository(repositoryId)

        Mockito.verify(mockGartRepository).getGithubRepository(repositoryId)
    }

}
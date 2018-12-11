package com.gart.app.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gart.app.R
import com.gart.base.model.GithubRepositoryItem
import com.gart.base.viewmodel.RepositoryListViewModel
import kotlinx.android.synthetic.main.repository_list_fragment.*

class RepositoryListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.repository_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val repositoryListViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
            .create(RepositoryListViewModel::class.java)

        repositoryListViewModel.getGithubRepository().observe(this, Observer<List<GithubRepositoryItem>> { list ->
            repositoryListRecyclerView.adapter = RepositoryListAdapter(list)
        })
    }

}
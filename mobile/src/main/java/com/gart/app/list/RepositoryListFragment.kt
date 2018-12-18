package com.gart.app.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gart.app.R
import com.gart.base.model.GithubRepository
import com.gart.base.viewmodel.RepositoryListViewContract
import com.gart.base.viewmodel.RepositoryListViewModel
import com.gart.base.viewmodel.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.repository_list_fragment.*
import kotlinx.android.synthetic.main.repository_list_fragment.view.*


class RepositoryListFragment : Fragment(), RepositoryListViewContract, SwipeRefreshLayout.OnRefreshListener {

    private lateinit var rootView: View
    private lateinit var viewModel: RepositoryListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory { RepositoryListViewModel(this.activity!!.application, this) }
        ).get(RepositoryListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.repository_list_fragment, container, false)

        rootView.repositoryListSwipeRefreshLayout.setColorSchemeColors(resources.getColor(R.color.colorSecondary, resources.newTheme()))
        rootView.repositoryListSwipeRefreshLayout.setOnRefreshListener(this)
        rootView.repositoryListSwipeRefreshLayout.isRefreshing = true

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        displayLocalRepositories()
    }

    override fun onRefresh() {
        viewModel.fetchGithubRepositories()
        displayLocalRepositories()
    }

    override fun displayLocalRepositories() {
        viewModel.getGithubRepositories().observe(this, Observer<List<GithubRepository>> {
            if (it == null || it.isEmpty()) {
                viewModel.fetchGithubRepositories()
            } else {
                repositoryListRecyclerView.adapter = RepositoryListAdapter(it)

                repositoryListRecyclerView.visibility = View.VISIBLE
                noItemsFoundContainer.visibility = View.GONE
                rootView.repositoryListSwipeRefreshLayout.isRefreshing = false
            }
        })
    }

    override fun update() {
        Snackbar.make(rootView, getString(R.string.repository_successful_update_message), Snackbar.LENGTH_LONG).apply {
            this.view.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
            this.show()
        }
        rootView.repositoryListSwipeRefreshLayout.isRefreshing = false
    }

    override fun displayUpdateErrorNotification() {
        Snackbar.make(rootView, getString(R.string.repository_network_error_message), Snackbar.LENGTH_LONG).show()
        rootView.repositoryListSwipeRefreshLayout.isRefreshing = false
    }

    override fun displayNoLocalRepositoriesErrorNotification() {
        displayUpdateErrorNotification()

        repositoryListErrorMessageTextView.text = getString(R.string.repository_not_found_error_message)
        repositoryListRecyclerView.visibility = View.GONE
        noItemsFoundContainer.visibility = View.VISIBLE
        rootView.repositoryListSwipeRefreshLayout.isRefreshing = false
    }

}
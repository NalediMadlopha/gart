package com.gart.app.details


import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gart.app.R
import com.gart.app.list.RepositoryListAdapter.Companion.SELECTED_GITHUB_REPOSITORY_ID
import com.gart.base.model.GithubRepository
import com.gart.base.utils.Utils
import com.gart.base.viewmodel.RepositoryDetailsViewModel
import com.gart.base.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.repository_details_fragment.*
import kotlinx.android.synthetic.main.repository_summary_details_layout.*

class RepositoryDetailsFragment : Fragment() {

    private lateinit var viewModel: RepositoryDetailsViewModel
    private lateinit var rootView: View
    private var selectedGithubRepositoryId: Int = 0

    fun getInstance(selectedGithubRepositoryId: Int): RepositoryDetailsFragment {
        val fragment = RepositoryDetailsFragment()
        val bundle = Bundle()

        bundle.putInt(SELECTED_GITHUB_REPOSITORY_ID, selectedGithubRepositoryId)
        fragment.arguments = bundle

        return fragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments!!.containsKey(SELECTED_GITHUB_REPOSITORY_ID)) {
            selectedGithubRepositoryId = arguments!!.getInt(SELECTED_GITHUB_REPOSITORY_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.repository_details_fragment, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory { RepositoryDetailsViewModel(this.activity!!.application) }
        ).get(RepositoryDetailsViewModel::class.java)

        viewModel.getGithubRepository(selectedGithubRepositoryId).observe(this, Observer<GithubRepository> {
            val elapsedTime = Utils().elapseTime(it.updated_at!!).toString().toLowerCase()

            listItemRepositoryFullNameTextView.text = it.full_name
            listItemRepositoryDescriptionTextView.text = it.description
            listItemRepositoryLastUpdateTextView.text = rootView.context.getString(R.string.repository_last_update_text, elapsedTime)
            listItemRepositoryStarGazersTextView.text = Utils().numberSuffixConverter(it.stargazers_count?.toDouble(), 0)

            detailsRepositoryStarsLabelValueWidget.setTopText(it.stargazers_count.toString())
            detailsRepositoryForksLabelValueWidget.setTopText(it.forks_count.toString())
            detailsRepositoryIssuesLabelValueWidget.setTopText(it.open_issues_count.toString())
            detailsRepositoryWatchersLabelValueWidget.setTopText(it.watchers_count.toString())

            setupLanguageTextView(it)
        })
    }

    private fun setupLanguageTextView(githubRepository: GithubRepository) {
        if (!TextUtils.isEmpty(githubRepository.language)) {
            listItemRepositoryLanguageTextView.text = githubRepository.language
        } else {
            listItemRepositoryLanguageTextView.visibility = View.GONE
        }
    }

}

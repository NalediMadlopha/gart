package com.gart.app.list

import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gart.app.R
import com.gart.app.details.RepositoryDetailsActivity
import com.gart.base.model.GithubRepository
import com.gart.base.utils.Utils
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.repository_list_item.*


class RepositoryListAdapter(private var githubRepositoryList: List<GithubRepository>): RecyclerView.Adapter<RepositoryListAdapter.RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository_list_item, parent, false)

        return RepositoryViewHolder(view)
    }

    override fun getItemCount() = githubRepositoryList.size

    override fun onBindViewHolder(viewHolder: RepositoryViewHolder, position: Int) {
        val githubRepositoryItem = githubRepositoryList[position]
        viewHolder.bind(githubRepositoryItem)
    }

    class RepositoryViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer, View.OnClickListener {

        private lateinit var githubRepository: GithubRepository

        fun bind(githubRepository: GithubRepository) {
            this.githubRepository = githubRepository
            val elapsedTime = Utils().elapseTime(githubRepository.updated_at!!).toString().toLowerCase()

            listItemRepositoryFullNameTextView.text = githubRepository.full_name
            listItemRepositoryDescriptionTextView.text = githubRepository.description
            listItemRepositoryLastUpdateTextView.text = containerView.context.getString(R.string.repository_last_update_text, elapsedTime)
            listItemRepositoryStarGazersTextView.text = Utils().numberSuffixConverter(githubRepository.stargazers_count?.toDouble(), 0)

            setupLanguageTextView(githubRepository)
            containerView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val context = view.context
            val intent = Intent(context, RepositoryDetailsActivity::class.java)
            intent.putExtra(SELECTED_GITHUB_REPOSITORY_ID, this.githubRepository.id)
            context.startActivity(intent)
        }

        private fun setupLanguageTextView(item: GithubRepository) {
            if (!TextUtils.isEmpty(item.language)) {
                listItemRepositoryLanguageTextView.text = item.language
            } else {
                listItemRepositoryLanguageTextView.visibility = View.GONE
            }

            containerView.setOnClickListener(this)
        }

    }

    companion object {
        const val SELECTED_GITHUB_REPOSITORY_ID = "selected_github_repository_id"
    }

}
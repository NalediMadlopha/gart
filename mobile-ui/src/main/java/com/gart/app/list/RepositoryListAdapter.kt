package com.gart.app.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gart.app.R
import com.gart.model.GithubRepositoryItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.repository_summary_details_layout.*


class RepositoryListAdapter(private var githubRepositoryList: List<GithubRepositoryItem>): RecyclerView.Adapter<RepositoryListAdapter.RepositoryViewHolder>() {

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

    class RepositoryViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(item: GithubRepositoryItem) {
            listItemRepositoryFullNameTextView.text = item.fullName
            listItemRepositoryDescriptionTextView.text = item.description
            listItemRepositoryLanguageTextView.text = item.language
            listItemRepositoryLastUpdateTextView.text = item.updatedAt
            listItemRepositoryStarGazersTextView.text = item.stargazersCount.toString()
        }

    }
}
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
import kotlinx.android.synthetic.main.repository_summary_details_layout.*


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

        fun bind(item: GithubRepository) {
            val elapsedTime = Utils().elapseTime(item.updated_at!!).toString().toLowerCase()

            listItemRepositoryFullNameTextView.text = item.full_name
            listItemRepositoryDescriptionTextView.text = item.description
            listItemRepositoryLastUpdateTextView.text = containerView.context.getString(R.string.repository_last_update_text, elapsedTime)
            listItemRepositoryStarGazersTextView.text = Utils().numberSuffixConverter(item.stargazers_count?.toDouble(), 0)

            setupLanguageTextView(item)
            containerView.setOnClickListener(this)
        }

        private fun setupLanguageTextView(item: GithubRepository) {
            if (!TextUtils.isEmpty(item.language)) {
                listItemRepositoryLanguageTextView.text = item.language
            } else {
                listItemRepositoryLanguageTextView.visibility = View.GONE
            }

            listItemRepositoryLastUpdateTextView.text = item.updated_at
            listItemRepositoryStarGazersTextView.text = Utils().numberSuffixConverter(item.stargazers_count?.toDouble(), 0)

            containerView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val context = view.getContext()

            val intent = Intent(context, RepositoryDetailsActivity::class.java)
            context.startActivity(intent)
        }

    }
}
package com.gart.app.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gart.app.R
import com.gart.app.list.RepositoryListAdapter
import kotlinx.android.synthetic.main.app_bar_layout.*


class RepositoryDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository_details_activity)
        setupScreenTitle()

        val intent = intent
        val selectedGithubRepositoryId = intent.getIntExtra(RepositoryListAdapter.SELECTED_GITHUB_REPOSITORY_ID, 0)

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.repositoryListFragment, RepositoryDetailsFragment().getInstance(selectedGithubRepositoryId))
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun setupScreenTitle() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}

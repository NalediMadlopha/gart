package com.gart.app.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gart.app.R
import kotlinx.android.synthetic.main.app_bar_layout.*


class RepositoryDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository_details_activity)
        setupScreenTitle()
    }

    private fun setupScreenTitle() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}

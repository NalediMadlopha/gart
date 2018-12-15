package com.gart.app.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gart.app.R

class RepositoryListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repository_list_activity)

//        ViewModelProviders.of(this, viewModelFactory).get(RepositoryListViewModel::class.java)

//        ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(RepositoryListViewModel::class.java)
    }

}
package com.gart.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


inline fun <VM : ViewModel> ViewModelFactory(crossinline f: () -> VM) =
    object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(aClass: Class<T>):T = f() as T
    }
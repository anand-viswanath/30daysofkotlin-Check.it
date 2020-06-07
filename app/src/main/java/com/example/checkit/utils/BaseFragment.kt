package com.example.checkit.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import butterknife.ButterKnife

abstract class BaseFragment : Fragment() {

    abstract fun getLayoutResource(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutView = inflater.inflate(getLayoutResource(), container, false)
        ButterKnife.bind(this, layoutView)
        return layoutView
    }

    fun hideActionBar() {
        (activity as AppCompatActivity).supportActionBar?.hide()
    }
}
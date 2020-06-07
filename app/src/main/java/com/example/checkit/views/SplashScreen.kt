package com.example.checkit.views

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.checkit.R
import com.example.checkit.utils.BaseFragment

class SplashScreen : BaseFragment() {

    override fun getLayoutResource(): Int {
        return R.layout.fragment_splashscreen
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideActionBar()
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashScreen_to_viewMovieFragment)
        }, 3000)
    }
}
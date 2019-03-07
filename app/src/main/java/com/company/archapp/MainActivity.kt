package com.company.archapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout

class MainActivity : AppCompatActivity(), DetailFragment.OnSwipedLineListener {
    private lateinit var imageView: ImageView
    private lateinit var frameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add DetailFragment
        addFragment(R.id.fragment_container, DetailFragment.newInstance(), false, false)

        // Init views
        imageView = findViewById<ImageView>(R.id.image_view)
        frameLayout = findViewById(R.id.fragment_container)
    }

    /*
    Method for adding fragments to activity
     */
    @SuppressLint("CommitTransaction")
    protected fun addFragment(
        containerRes: Int?,
        fragment: Fragment,
        addToBackStack: Boolean,
        animate: Boolean
    ) {
        var transaction = this
            .supportFragmentManager.beginTransaction()
            .replace(
                containerRes!!, fragment,
                fragment.javaClass.simpleName
            )
        if (addToBackStack) {
            transaction = transaction.addToBackStack(
                fragment.javaClass.simpleName
            )
        }
        if (animate) {
            transaction = transaction.setTransition(
                FragmentTransaction.TRANSIT_FRAGMENT_OPEN
            )
        }
        transaction.commitAllowingStateLoss()
    }

    /**
     * Change weight of ImageView and FrameLayout
     * when line swiped
     */
    override fun onLineSwiped(imageWeight: Float?, fragmentWeight: Float?) {
        // We need to use LinearLayout.LayoutParams class
        val imageParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            0,
            imageWeight!!
        )
        val fragmentParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            0,
            fragmentWeight!!
        )

        // set layout params to views
        imageView.layoutParams = imageParams
        frameLayout.layoutParams = fragmentParams
    }
}

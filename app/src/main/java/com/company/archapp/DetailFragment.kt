package com.company.archapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

class DetailFragment : Fragment() {
    lateinit var lineView: View

    companion object {
        fun newInstance(): DetailFragment {
            val detailFragment = DetailFragment()
            return detailFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_detail, container, false)
        lineView = root.findViewById(R.id.line)
        lineView.setOnTouchListener(object : OnSwipeTouchListener(activity!!.applicationContext) {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                return super.onTouch(v, event)
            }

            override fun onSwipeTop() {
                val listener: OnSwipedLineListener =activity as OnSwipedLineListener
                listener.onLineSwiped(1f, 2f)
            }

            override fun onSwipeBottom() {
                val listener: OnSwipedLineListener =activity as OnSwipedLineListener
                listener.onLineSwiped(2f, 1f)
            }

        })
        return root
    }

    interface OnSwipedLineListener {
        fun onLineSwiped(imageWeight: Float?, fragmentWeight: Float?)
    }
}

package com.berke.expensevsincome

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import com.berke.expensevsincome.R
import kotlinx.android.synthetic.main.fragment_animation.*


@Suppress("DEPRECATION")
class AnimationFragment : Fragment() {


    private val splashScreen = 4300

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_animation, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Create Animation

        val animation1 = AnimationUtils.loadAnimation(this.context, R.anim.animation1)
        val animation2 = AnimationUtils.loadAnimation(this.context, R.anim.animation2)
        val animation3 = AnimationUtils.loadAnimation(this.context, R.anim.animation3)
        val animation4 = AnimationUtils.loadAnimation(this.context, R.anim.animation4)
        val animation5 = AnimationUtils.loadAnimation(this.context, R.anim.animation5)

        imageView.animation = animation1
        imageView2.animation= animation2
        textView1.animation = animation3
        textView2.animation = animation4
        textView3.animation = animation5

        //Create SplashScreen
        Handler().postDelayed({
            val action =
                com.berke.expensevsincome.AnimationFragmentDirections.actionAnimationFragmentToLoginFragment()
            Navigation.findNavController(view).navigate(action)
        },splashScreen.toLong())

    }


}
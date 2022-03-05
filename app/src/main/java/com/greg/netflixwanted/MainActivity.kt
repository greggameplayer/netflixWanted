package com.greg.netflixwanted

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animationView = findViewById<LottieAnimationView>(R.id.animationView)

        animationView.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
                //
            }

            override fun onAnimationEnd(animation: Animator?) {
                val intent = Intent(this@MainActivity, SearchActivity::class.java)
                startActivity(intent)
            }

            override fun onAnimationCancel(animation: Animator?) {
                //
            }

            override fun onAnimationStart(animation: Animator?) {
                //
            }

        })
    }

}

package com.example.juanmartinezleongreenflagkotlin


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.example.juanmartinezleongreenflagkotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    //region Variable declaration
    private var binding: ActivityMainBinding? = null
    private var btn: View? = null
    //endregion

    //region Main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportActionBar != null) supportActionBar!!.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        btn = findViewById(R.id.btn_create_account)

        getFeatures();
        disableViewListScroll(); //to avoid nested scroll
        setupListener();
        setupAnimations();
    }

    //endregion

    //region program logic
    private fun getFeatures() {
        val features = arrayOf("Car health updates", "Request a rescue online", "PolicyInformation")
        val featureArrayList: ArrayList<FeatureItem?> = ArrayList()
        for (feature in features) {
            val featureObj = FeatureItem(feature)
            featureArrayList.add(featureObj)
        }
        val listAdapter = ListAdapter(this@MainActivity, featureArrayList)
        binding!!.lvFeatures.adapter = listAdapter
    }

    private fun disableViewListScroll() {
        val listView = findViewById<View>(R.id.lvFeatures)
        listView.setOnTouchListener { v, event -> event.action == MotionEvent.ACTION_MOVE }
    }

    private fun setupListener() {
        btn!!.setOnClickListener { View: View? ->
            val intent = Intent(this@MainActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
    //endregion

    //endregion
    //region animations
    private fun setupAnimations() {
        val anim: Animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_anim)
        val logoFrag = findViewById<FragmentContainerView>(R.id.fragment_container_view)
        logoFrag.startAnimation(anim)
        val animLeftRight: Animation = AnimationUtils.loadAnimation(this, R.anim.leftto_right_anim)
        val textAnim = findViewById<TextView>(R.id.textToAnim)
        textAnim.startAnimation(animLeftRight)
        val animLeftRightDelay: Animation =
            AnimationUtils.loadAnimation(this, R.anim.leftto_right_anim_delay)
        val list: ListView = findViewById(R.id.lvFeatures)
        list.startAnimation(animLeftRightDelay)
        val fadeAnimDelay: Animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_anim_delay)
        btn!!.startAnimation(fadeAnimDelay)
    }
    //endregion

}
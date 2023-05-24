package com.example.optemates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.optemates.databinding.ActivityUserDetailedBinding

class UserDetailedActivity : AppCompatActivity() {

    companion object{
        const val  EXTRA_USERNAME   = "extra_username"
    }
    private lateinit var vinding : ActivityUserDetailedBinding
    private lateinit var viewModel : viewModelDetailUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vinding = ActivityUserDetailedBinding.inflate(layoutInflater)
        setContentView(vinding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        Log.d("userdetailactivity hasil intent", username.toString())

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(viewModelDetailUser::class.java)
        if (username != null) {
            viewModel.setUserDetail(username)
        }
        viewModel.getUserDetail().observe(this) {
            if (it != null) {
                Log.d("UserDetailAct", it.toString())
                vinding.apply {
                    tvName.text = it.name
                    tvUnique.text = it.login
                    tvFolwed.text = it.followers.toString()+" Followers"
                    tvFolwing.text = it.following.toString()+" Following"
                    Glide.with(this@UserDetailedActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(imgProf)
                }
            }
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        vinding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabsLayout.setupWithViewPager(viewPager)
        }
    }
}
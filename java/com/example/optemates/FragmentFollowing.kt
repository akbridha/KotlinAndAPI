package com.example.optemates

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.optemates.databinding.FragmentFollowBinding

class FragmentFollowing: Fragment(R.layout.fragment_follow){

    private  var _vinding : FragmentFollowBinding? = null

    private val binding get() = _vinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _vinding= FragmentFollowBinding.bind(view)
    }


    }



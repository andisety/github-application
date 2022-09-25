package com.andi.githubuserapplication.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andi.githubuserapplication.databinding.ActivityDetaiBinding
import com.andi.githubuserapplication.model.User

class DetaiActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetaiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetaiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent?.getParcelableExtra<User>("data")
        supportActionBar?.title=user?.name
        binding.tvName.text = user?.name
        binding.tvRepo.text = user?.repository.toString()
        binding.tvFollowing.text = user?.following.toString()
        binding.tvFollowers.text = user?.follower.toString()
        binding.tvCompany.text = user?.company
        binding.tvLocation.text = user?.location
        binding.ivProfile.setImageResource(user?.avatar!!.toInt())
    }
}
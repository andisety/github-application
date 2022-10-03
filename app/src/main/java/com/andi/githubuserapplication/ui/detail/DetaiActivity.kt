package com.andi.githubuserapplication.ui.detail


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.andi.githubuserapplication.MainActivity
import com.andi.githubuserapplication.R
import com.andi.githubuserapplication.databinding.ActivityDetaiBinding
import com.andi.githubuserapplication.model.MainViewModel
import com.andi.githubuserapplication.model.response.UserResponseDetail
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator

class DetaiActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetaiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetaiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(MainActivity.DATA)

        supportActionBar?.title=username

        val mainModel  = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]

        mainModel.isLoading.observe(this){isloading->
            showLoading(isloading)
        }
        mainModel.getUserDetail(username!!)
        mainModel.userDetail.observe(this){userDetail->
            setDetailUser(userDetail)
        }

        val title = arrayOf("Followers","Following")
        val adapter = SectionPageAdapter(this)
        adapter.setUpData(username)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tbLayout,binding.viewPager){tab,position ->
            tab.text = title[position]
        }.attach()

    }

    private fun setDetailUser(user:UserResponseDetail?) {
        binding.tvName.text = user?.name
        binding.tvUsername.text = user?.login
        binding.tvRepo.text = user?.publicRepos.toString()
        binding.tvFollowing.text = user?.following.toString()
        binding.tvFollowers.text = user?.followers.toString()

        if (user?.location == null){
            binding.tvLocation.visibility = View.GONE
            binding.tvLocation.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0)
        }else{
            binding.tvLocation.text = user.location
            binding.tvLocation.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_location,0,0,0)
        }

        if (user?.company == null){
            binding.tvCompany.visibility = View.GONE
            binding.tvCompany.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0)
        }else{
            binding.tvCompany.text = user.company
            binding.tvCompany.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_home_work,0,0,0)
        }
        Glide.with(this@DetaiActivity)
            .load(user?.avatarUrl)
            .into(binding.ivProfile)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


}
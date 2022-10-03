package com.andi.githubuserapplication


import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.andi.githubuserapplication.adapter.AdapterUser
import com.andi.githubuserapplication.databinding.ActivityMainBinding
import com.andi.githubuserapplication.model.MainViewModel
import com.andi.githubuserapplication.model.response.UsersResponse
import com.andi.githubuserapplication.ui.detail.DetaiActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    companion object{
        const val DATA="DATA"
        lateinit var mainModel:MainViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory())[MainViewModel::class.java]

        mainModel.users.observe(this){users ->
            setDataUsers(users)
        }
        mainModel.search.observe(this@MainActivity){search->
            setDataUsers(search.items)
        }

        mainModel.isLoading.observe(this){isloading->
            showLoading(isloading)
        }

    }

    private fun setDataUsers(users:UsersResponse){
         val userAdapter = AdapterUser(users,this,object:AdapterUser.OnAdapterListener{
            override fun itemClick(username: String) {
                val intent = Intent(this@MainActivity,DetaiActivity::class.java)
                intent.putExtra(DATA,username)
                startActivity(intent)
            }
        })
        binding.rcList.adapter = userAdapter
        binding.rcList.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_option,menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu?.findItem(R.id.search)?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                mainModel.getUserSearch(query!!)
                binding.apply {
                    ivTitle.visibility = View.GONE
                    tvTitle.visibility = View.GONE
                    tvsubTitle.visibility = View.GONE
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                mainModel.users.observe(this@MainActivity){users ->
                    binding.apply {
                        ivTitle.visibility = View.VISIBLE
                        tvTitle.visibility = View.VISIBLE
                        tvsubTitle.visibility = View.VISIBLE
                    }
                    setDataUsers(users)
                }
                return false
            }
        })
        return true
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }


}
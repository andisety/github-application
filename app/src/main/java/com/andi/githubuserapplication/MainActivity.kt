package com.andi.githubuserapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.andi.githubuserapplication.adapter.AdapterUser
import com.andi.githubuserapplication.databinding.ActivityMainBinding
import com.andi.githubuserapplication.model.User
import com.andi.githubuserapplication.model.Users
import com.andi.githubuserapplication.ui.detail.DetaiActivity
import com.google.gson.Gson
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val jsonData=jsonGEtData(applicationContext,"users.json")
        val users = Gson().fromJson(jsonData, Users::class.java)
        for (i in users.indices){
            val resId = resources.getIdentifier(users[i].avatar,"drawable",applicationContext.packageName)
            users[i].avatar = resId.toString()
        }
        val userAdapter = AdapterUser(users,object : AdapterUser.OnAdapterListener{
            override fun itemClick(user: User) {
                val intent = Intent(this@MainActivity,DetaiActivity::class.java)
                intent.putExtra("data",user)
                startActivity(intent)
            }
        })

        binding.rcList.adapter = userAdapter
        binding.rcList.layoutManager = LinearLayoutManager(this)

    }


    private fun jsonGEtData(context: Context, fileName:String): String {
        val json:String
        try {
            json = context.assets.open(fileName).bufferedReader().use {
                it.readText()
            }
        }catch (ioException:IOException){
            ioException.printStackTrace()
            return "tidak ada"
        }
        return json
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
package com.example.hackme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.hackme.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val activityMain = binding.root
        setContentView(activityMain)



        val MainA = Intent(this, MainActivity::class.java)
        val MapA = Intent(this, MainMap::class.java)
        val ChatA = Intent(this, ChatActivity::class.java)
        val SettingsA = Intent(this, SettingsActivity::class.java)
        binding.bottomNavigationM.selectedItemId = R.id.ic_baseline_format_list_bulleted_24
        binding.bottomNavigationM.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_baseline_map_24 -> startActivity(MapA)
                R.id.ic_baseline_comment_24 -> startActivity(ChatA)
                R.id.ic_baseline_settings_24 -> startActivity(SettingsA)
                R.id.ic_baseline_format_list_bulleted_24 -> startActivity(MainA)
            }
            true
        }

    }
    fun goMainMap (view: View){
        val goMap = Intent(this, SetAreaActivity::class.java)
        startActivity(goMap)
    }


}
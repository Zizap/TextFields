package com.example.textfields

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import com.example.textfields.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.enterCity?.setOnKeyListener { v, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                binding?.cityTv?.text = binding?.enterCity?.text.toString()
                binding?.enterCity?.setText("")

            }
            return@setOnKeyListener true
        }

        binding?.popup?.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        showMenu(view,R.menu.popup_menu)
    }
    //@MenuRes
    private fun showMenu(view: View, menuRes: Int){
        val popup = PopupMenu(this,view)
        popup.menuInflater.inflate(menuRes,popup.menu)

        popup.setOnMenuItemClickListener {  menuItem: MenuItem ->
            when (menuItem.itemId){
                R.id.catalogMenuPopup -> {
                    binding?.selectedItems?.text = getString(R.string.catalog)
                    true
                }
                R.id.favoritMenuPopup -> {
                    binding?.selectedItems?.text = getString(R.string.favorit)
                    true
                }
                R.id.settingMenuPopup -> {
                    binding?.selectedItems?.text = getString(R.string.setting)
                    true
                }
                else -> true
            }
        }
        popup.setOnDismissListener {
            binding?.condition?.text = getString(R.string.closed)
        }
        popup.show()
    }
}
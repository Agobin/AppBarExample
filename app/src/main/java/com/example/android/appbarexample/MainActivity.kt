package com.example.android.appbarexample

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    companion object{
        var itemsList = mutableListOf<String>("One", "Two", "Three", "Four", "Item 2", "Item 3", "Item 4")
    }


    lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //This sets the cutomized toolbar created as the default action bar of this activity
        setSupportActionBar(toolBar)

        buttonGoSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        buttonAddItem.setOnClickListener {
            val msg = itemInput.text.toString()
            MainActivity.itemsList.add(msg)
            adapter = RecyclerAdapter(this, MainActivity.itemsList)
            recyclerView.adapter = adapter
            itemInput.setText("")
        }

        //RecyclerView
        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutManager

        adapter = RecyclerAdapter(this, MainActivity.itemsList)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        var menuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.app_bar_menu, menu)

        class OnExpand(val context: Context) : MenuItem.OnActionExpandListener{
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {

                Toast.makeText(context, "Action view expanded", Toast.LENGTH_SHORT).show()

                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                Toast.makeText(context, "Action view collapsed", Toast.LENGTH_SHORT).show()

                return true
            }

        }

        menu?.findItem(R.id.action_search)?.setOnActionExpandListener(OnExpand(context = applicationContext))

        //menu?.findItem(R.id.action_search).setOnActionExpandListener(onActionExpandListener)

        val menuItem = menu?.findItem(R.id.action_search)
        var searchView: SearchView = menuItem?.actionView as SearchView

        searchView?.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val userInput = newText?.toLowerCase()
        //Toast.makeText(this, userInput, Toast.LENGTH_SHORT).show()

        var newList = mutableListOf<String>()

        itemsList.forEach {
            if (it.toLowerCase().contains(userInput.toString()))
                newList.add(it)
        }

        Log.i("Agobin", "New list: " + newList.toString())
        adapter.updateList(newList)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.action_search -> {
                //Toast.makeText(this, "Search menu option was clicked", Toast.LENGTH_SHORT).show()
                return true
            }


            R.id.action_share -> {
                Toast.makeText(this, "Share menu option was clicked", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.action_settings0 -> {
                Toast.makeText(this, "Share menu option 0 was clicked", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.action_settings1 -> {
                Toast.makeText(this, "Share menu option 1 was clicked", Toast.LENGTH_SHORT).show()
                return true
            }

            R.id.action_settings2 -> {
                Toast.makeText(this, "Share menu option 2 was clicked", Toast.LENGTH_SHORT).show()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }


}

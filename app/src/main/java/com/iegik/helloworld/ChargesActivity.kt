package com.iegik.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ChargesActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
  private lateinit var chargesList: ListView
  private lateinit var charges: MutableList<String>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_charges)

    chargesList = findViewById(R.id.charges)

    charges = mutableListOf("Jack", "Queen", "King")
    charges.add("Foo")
//    val charges = listOf("Jack", "Queen", "King");

    val chargesAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, charges);
    chargesList.adapter = chargesAdapter

    chargesList.onItemClickListener = this
  }

  override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    println("Not yet implemented")
    Toast.makeText(this, charges[position], Toast.LENGTH_SHORT).show()
  }
}

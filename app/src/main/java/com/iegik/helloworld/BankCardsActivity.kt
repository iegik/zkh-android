package com.iegik.helloworld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class BankCardsActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
  private lateinit var bankCardSelect: Spinner
  private lateinit var bankCards: MutableList<String>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_bank_cards)

    bankCardSelect = findViewById(R.id.chargesSpinner)

    bankCards = mutableListOf("Jack", "Queen", "King")
    bankCards.add("Foo")

    val bankCardSelectAdapter = ArrayAdapter(this, android.R.layout.select_dialog_item, bankCards);
    bankCardSelect.adapter = bankCardSelectAdapter
    bankCardSelect.onItemSelectedListener = this
  }

  override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    println("Not yet implemented")
    Toast.makeText(this, "Item ${bankCards[position]} selected", Toast.LENGTH_SHORT).show()
  }

  override fun onNothingSelected(parent: AdapterView<*>?) {
    println("Not yet implemented")
  }
}

package com.iegik.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class SearchActivity : AppCompatActivity(), View.OnClickListener {
  private lateinit var goToPaymentBtn: Button
  private lateinit var findByELSBtn: Button

  //  private lateinit var hamburgerBtn: Button
  private lateinit var selectBankCardBtn: Button

  private lateinit var yandexPayScreen: Intent
  private lateinit var chargesScreen: Intent
  private lateinit var sidebarScreen: Intent
  private lateinit var bankCardsScreen: Intent


  override fun onClick(v: View) {
    when (v.id) {
      R.id.goToPaymentBtn ->
//        setContentView(R.layout.activity_yandex_pay)
        startActivity(yandexPayScreen)
      R.id.findByELSBtn -> startActivity(chargesScreen)
      R.id.selectBankCardBtn -> startActivity(bankCardsScreen)
      else -> TODO("Not implemented")
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_search)
    // TODO: start here
    goToPaymentBtn = findViewById(R.id.goToPaymentBtn)
    goToPaymentBtn.setOnClickListener(this)

    findByELSBtn = findViewById(R.id.findByELSBtn)
    findByELSBtn.setOnClickListener(this)

//    hamburgerBtn = findViewById(R.id.hamburgerBtn)
//    hamburgerBtn.setOnClickListener(this)

    selectBankCardBtn = findViewById(R.id.selectBankCardBtn)
    selectBankCardBtn.setOnClickListener(this)

    // Navigation
    yandexPayScreen = Intent(this, PaymentActivity::class.java)
    yandexPayScreen.putExtra("pay", "pay")

    chargesScreen = Intent(this, ChargesActivity::class.java)
    chargesScreen.putExtra("charges", "charges")

    sidebarScreen = Intent(this, SidebarActivity::class.java)
    sidebarScreen.putExtra("menu", "menu")

    bankCardsScreen = Intent(this, BankCardsActivity::class.java)
    bankCardsScreen.putExtra("card", "card")
  }
}

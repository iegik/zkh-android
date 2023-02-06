package com.iegik.helloworld

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.PermissionChecker
import com.yandex.pay.*


class PaymentActivity : AppCompatActivity(), View.OnClickListener {
  private lateinit var payYandexBtn: Button

  // Register the permissions callback, which handles the user's response to the
// system permissions dialog. Save the return value, an instance of
// ActivityResultLauncher. You can use either a val, as shown in this snippet,
// or a lateinit var in your onAttach() or onCreate() method.
  private val requestPermissionLauncher =
    registerForActivityResult(
      ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
      if (isGranted) {
        // Permission is granted. Continue the action or workflow in your
        // app.
        Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
      } else {
        // Explain to the user that the feature is unavailable because the
        // feature requires a permission that the user has denied. At the
        // same time, respect the user's decision. Don't link to system
        // settings in an effort to convince the user to change their
        // decision.
        Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
      }
    }

  private fun showToast(text: String) {
    Toast.makeText(this@PaymentActivity, text, Toast.LENGTH_SHORT).show()
  }

  private val yandexPayLauncher =
    registerForActivityResult(YPayContract()) { result: YPayResult ->
      when (result) {
        is YPayResult.Success -> showToast("Finished with success:\nOrderID: ${result.orderId}\n${result.metadata}")
        is YPayResult.Cancelled -> showToast("Finished with cancelled event")
        is YPayResult.Failure -> showToast("Finished with domain error:\n${result.errorMsg}\n${result.metadata}")
      }
    }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    YPaySdk.init(
      context = this,
      config = YPayConfig(
        merchantData = MerchantData(
          id = MerchantId("bbb9c171-2fab-45e6-b1f8-6212980aa9bb"),
          name = MerchantName("test-merchant-name"),
          url = MerchantUrl("https://oplatagosuslug.ru/"),
        ),
        environment = YPayApiEnvironment.SANDBOX,
        locale = Locale.SYSTEM,
      )
    )

    setContentView(R.layout.activity_payment)

    println("onCreate called")

    payYandexBtn = findViewById(R.id.payYandexBtn)
    payYandexBtn.setOnClickListener(this)

    // Internet required for Yandex Pay
    when {
      PermissionChecker.checkSelfPermission(
        this,
        Manifest.permission.INTERNET
      ) == PermissionChecker.PERMISSION_GRANTED -> {
        // You can use the API that requires the permission.
        println("Internet permission granted")
      }
      shouldShowRequestPermissionRationale(Manifest.permission.INTERNET) -> {
        // In an educational UI, explain to the user why your app requires this
        // permission for a specific feature to behave as expected. In this UI,
        // include a "cancel" or "no thanks" button that allows the user to
        // continue using your app without granting the permission.
        println("Internet permission denied")
      }
      else -> {
        // You can directly ask for the permission.
        // The registered ActivityResultCallback gets the result of this request.
        requestPermissionLauncher.launch(
          Manifest.permission.INTERNET
        )
        println("Internet permission requested")
      }
    }
  }

  override fun onClick(v: View) {
    println(v.id)
    when (v.id) {
      R.id.payYandexBtn -> this.onPay()
      else -> println("click somewhere2")
    }
  }


  private fun onPay() {
    println("onPay called")
    // детали платежа, если есть orderId
    val paymentData = PaymentData(
      orderId = OrderId("12345678901234567890"),
      // (опционально) данные о корзине
      cart = PaymentCart(
        // список продуктов/услуг
        items = listOf(
          // данные продукта
          Product(
            // идентификатор продукта
            id = "0",
            // количество
            quantity = Quantity(count = "1")
          )
        )
      ),
      // ваши метаданные, может быть любая строка
      metadata = Metadata("any metadata"),
      currencyCode = CurrencyCode.RUB,
    )

    yandexPayLauncher.launch(paymentData)
  }

}

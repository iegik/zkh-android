package com.iegik.helloworld

import android.database.DataSetObserver
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter

abstract class ChargesAdapter(chargesActivity: ChargesActivity, charges: MutableList<String>) :
  ListAdapter {
  override fun registerDataSetObserver(observer: DataSetObserver?) {
    TODO("Not yet implemented")
  }

  override fun getCount(): Int {
    TODO("Not yet implemented")
  }

  override fun getItem(position: Int): Any {
    TODO("Not yet implemented")
  }

  override fun getItemId(position: Int): Long {
    TODO("Not yet implemented")
  }

  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    TODO("Not yet implemented")
  }
}

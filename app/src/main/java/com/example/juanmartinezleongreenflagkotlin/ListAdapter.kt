package com.example.juanmartinezleongreenflagkotlin
import android.R.layout
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class ListAdapter(context: Context?, featureItemArrayList: ArrayList<FeatureItem?>?) :
    ArrayAdapter<FeatureItem?>(
        context!!, R.layout.feature_item, R.id.tvFeatureItem,
        featureItemArrayList!!
    ) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val feature = getItem(position)
        Log.d("featureA", feature!!.description)
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.feature_item, parent, false)
        }
        val tvItem = convertView!!.findViewById<TextView>(R.id.tvFeatureItem)
        tvItem.text = feature.description
        return convertView
    }
}

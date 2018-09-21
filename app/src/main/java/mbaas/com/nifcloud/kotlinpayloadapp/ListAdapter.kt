package mbaas.com.nifcloud.kotlinpayloadapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class ListAdapter internal constructor(internal var main: MainActivity, json: JSONObject?) : BaseAdapter() {
    internal var listJson: MutableList<JSONObject> = ArrayList()

    init {
        if (json != null) {
            val keys = json.keys()
            while (keys.hasNext()) {
                val key = keys.next() as String
                try {
                    if (json.get(key) != null) {
                        val tmpJson = JSONObject()
                        tmpJson.put("key", key)
                        tmpJson.put("value", json.get(key).toString())
                        listJson.add(tmpJson)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        }
    }

    override fun getCount(): Int {
        return listJson.size
    }

    override fun getItem(position: Int): Any {
        return listJson[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    internal class ViewHolderItem {
        var key: TextView? = null
        var value: TextView? = null
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        var holder = ViewHolderItem()

        if (convertView == null) {
            val inflater = main.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.cell, null)

            holder.key = convertView!!.findViewById<View>(R.id.key) as TextView
            holder.value = convertView.findViewById<View>(R.id.value) as TextView

            convertView.tag = holder
        } else {
            holder = convertView.tag as ViewHolderItem
        }
        try {
            holder.key!!.text = "Key: " + this.listJson[position].get("key").toString()
            holder.value!!.text = "Value: " + this.listJson[position].get("value").toString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return convertView
    }

}
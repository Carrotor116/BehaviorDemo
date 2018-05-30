package com.example.nonu.behaviordemo.base

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*
import kotlin.collections.HashMap

/**
 * <pre>
 *     author : Nonu
 *     e-mail : 1162365377@qq.com
 *     time   : 2018/5/6
 *     desc   :
 *     version: 1.0
 * </pre>
 */

class BaseRecyclerAdapter(private val mContext: Context, @LayoutRes private val mLayoutRes: Int) : RecyclerView.Adapter<BaseRecyclerAdapter.Companion.BaseViewHolder>() {

    private var mDataList: MutableList<Map<String, Any>> = ArrayList()
    private val mOnItemClickMap: HashMap<Int, (v: View, position: Int) -> Unit> = HashMap()
    private val mOnItemLongClickMap: HashMap<Int, (v: View, position: Int) -> Boolean> = HashMap()
    private val mOnBindItemViewHelpers = LinkedList<(holder: BaseViewHolder, position: Int) -> Unit>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(mContext).inflate(mLayoutRes, parent, false)
        return BaseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        mOnItemClickMap.forEach({
            val view = holder.itemView.findViewById<View?>(it.key)
            val onClick = it.value
            view?.setOnClickListener { onClick(it, position) }
        })
        mOnItemLongClickMap.forEach {
            val view = holder.itemView.findViewById<View?>(it.key)
            val onLongClick = it.value
            view?.setOnLongClickListener { onLongClick(it, position) }
        }

        mOnBindItemViewHelpers.forEach {
            it.invoke(holder, position)
        }
    }


    fun addItemClick(resId: Int, onClick: (v: View, position: Int) -> Unit) {
        mOnItemClickMap[resId] = onClick
    }

    fun addItemLongClick(resId: Int, onClick: (v: View, position: Int) -> Boolean) {
        mOnItemLongClickMap[resId] = onClick
    }

    fun removeItemClick(resId: Int) {
        mOnItemClickMap.remove(resId)
    }

    fun removeItemLongClick(resId: Int) {
        mOnItemLongClickMap.remove(resId)
    }


    fun addBindViewHelper(onBindView: (holder: BaseViewHolder, position: Int) -> Unit) {
        mOnBindItemViewHelpers.add(onBindView)
    }

    fun addData(data: Map<String, Any>) {
        mDataList.add(data)
    }

    fun newDataList(dataList: MutableList<Map<String, Any>>) {
        mDataList = dataList
    }

    fun addDataList(dataList: List<Map<String, Any>>) {
        mDataList.addAll(dataList)
    }

    fun clearDataList() {
        mDataList.clear()
    }

    fun getDataList(): MutableList<Map<String, Any>> {
        return mDataList
    }

    companion object {
        class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    }
}
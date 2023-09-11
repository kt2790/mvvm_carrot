package com.example.mvvmcarrot.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmcarrot.databinding.ItemDetailBinding
import com.example.mvvmcarrot.ui.listener.ItemDeleteListener
import com.example.mvvmcarrot.model.Item
import com.example.mvvmcarrot.ui.activity.DetailActivity
import com.example.mvvmcarrot.ui.activity.MainActivity
import com.example.mvvmcarrot.ui.dialog.ConfirmDialog
import com.example.mvvmcarrot.util.PriceConverter

class ItemAdapter(private val itemDeleteListener: ItemDeleteListener) : RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {

    private var itemList = emptyList<Item>()

    class MyViewHolder(private val binding: ItemDetailBinding, private val itemDeleteListener: ItemDeleteListener) : RecyclerView.ViewHolder(binding.root) {
        lateinit var item : Item

        fun bind(currentItem : Item) {
            binding.detailItemTitle.text = currentItem.title
            binding.detailItemAddress.text = currentItem.address
            binding.detailItemPrice.text = PriceConverter.convert(currentItem.price)
            binding.detailItemChat.text = currentItem.chatCnt.toString()
            binding.detailItemLike.text = currentItem.likeCnt.toString()

            val imgId = binding.root.context.resources.getIdentifier(currentItem.img, "drawable", binding.root.context.packageName)
            binding.itemImg.setImageResource(imgId)
            binding.itemImg.clipToOutline = true

            binding.itemLayout.setOnClickListener {
                val intent = Intent(binding.root.context, DetailActivity::class.java)
                intent.putExtra("itemId", currentItem.id)
                binding.root.context.startActivity(intent)
            }

            binding.itemLayout.setOnLongClickListener {
                val dialog = ConfirmDialog(currentItem, itemDeleteListener)
                dialog.isCancelable = false
                dialog.show((binding.root.context as MainActivity).supportFragmentManager, "ConfirmDialog")
                true
            }


            val favBorder = binding.root.context.resources.getIdentifier("ic_favorite_border_foreground", "drawable", binding.root.context.packageName)
            val fav = binding.root.context.resources.getIdentifier("ic_favorite_foreground", "drawable", binding.root.context.packageName)

            if (currentItem.bookmark) {
                binding.detailItemBookmark.setImageResource(fav)
            }
            else {
                binding.detailItemBookmark.setImageResource(favBorder)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, itemDeleteListener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setData(item : List<Item>) {
        itemList = item
        notifyDataSetChanged()
    }
}
package com.example.mvvmcarrot.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.example.mvvmcarrot.R
import com.example.mvvmcarrot.databinding.ActivityDetailBinding
import com.example.mvvmcarrot.model.Item
import com.example.mvvmcarrot.util.PriceConverter
import com.example.mvvmcarrot.viewmodel.DetailViewModel
import com.example.mvvmcarrot.viewmodel.DetailViewModelFactory
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding
    private lateinit var curItem : Item
    private val detailViewModel : DetailViewModel by viewModels { DetailViewModelFactory() }
    var id = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = intent.getIntExtra("itemId", 1)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeView()
    }

    private fun initializeView() {
        detailViewModel.readItem(id).asLiveData().observe(this, Observer {
            curItem = it
            binding.detailPageTitle.text = it.title
            binding.detailPageAddress.text = it.address
            binding.detailPageContent.text = it.content
            binding.detailPageSeller.text = it.seller
            binding.detailPagePrice.text = PriceConverter.convert(it.price)
            binding.detailPageImg.setImageResource(resources.getIdentifier(it.img, "drawable", packageName))

            val favBorder = resources.getIdentifier("ic_favorite_border_foreground", "drawable", packageName)
            val fav = resources.getIdentifier("ic_favorite_foreground", "drawable", packageName)


            if (it.bookmark) {
                binding.detailPageBookmark.setImageResource(fav)
            }
            else {
                binding.detailPageBookmark.setImageResource(favBorder)
            }
        })

        binding.detailPageBookmark.setOnClickListener {
            if (!curItem.bookmark) {
                Snackbar.make(it, "관심 목록에 추가되었습니다.", Snackbar.LENGTH_LONG).show()
            }
            detailViewModel.updateBookmark(curItem)
        }

        binding.detailPageBack.setOnClickListener {
            finish()
        }


    }
}
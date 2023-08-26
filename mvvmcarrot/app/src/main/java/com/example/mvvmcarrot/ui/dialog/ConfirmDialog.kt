package com.example.mvvmcarrot.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.mvvmcarrot.R
import com.example.mvvmcarrot.databinding.FragmentConfirmDialogBinding
import com.example.mvvmcarrot.listener.ItemDeleteListener
import com.example.mvvmcarrot.model.Item


class ConfirmDialog(
    item: Item, itemDeleteListener: ItemDeleteListener
) : DialogFragment() {

    private var _binding: FragmentConfirmDialogBinding? = null
    private val binding get() = _binding!!

    private var itemDeleteListener : ItemDeleteListener
    private var item : Item

    init {
        this.itemDeleteListener = itemDeleteListener
        this.item = item
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConfirmDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.confirmTextView.text = "상품을 정말로 삭제하시겠습니까?"

        binding.noButton.setOnClickListener {
            dismiss()
        }

        binding.yesButton.setOnClickListener {
            itemDeleteListener.itemDeleteListener(item)
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
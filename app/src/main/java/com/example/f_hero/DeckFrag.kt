package com.example.f_hero

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.f_hero.databinding.FragDeckBinding


class DeckFrag : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragDeckBinding>(
            inflater, R.layout.frag_deck, container, false)

        binding.deck = this
        binding.androidButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER") {
                view:View -> findNavController().navigate(R.id.action_deckFrag_to_flashCardFragment)
        }

        binding.createDeckButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER") {
                view:View -> findNavController().navigate(R.id.action_deckFrag_to_flashCardFragment)
        }



        // Inflate the layout for this fragment
        return binding.root
    }

}

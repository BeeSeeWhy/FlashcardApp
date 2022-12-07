package com.example.f_hero

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.f_hero.databinding.FragmentFlashCardBinding


class FlashCardFragment : Fragment() {
    private lateinit var frontIn: AnimatorSet
    private lateinit var frontOut: AnimatorSet
    private lateinit var backIn: AnimatorSet
    private lateinit var backOut: AnimatorSet

    lateinit var currentCard: Flashcard
    private var flashcardIndex = 0
    private var isFront = true

    data class Flashcard(
        val front: String,
        val back: String
    )

    private val flashcards: MutableList<Flashcard> = mutableListOf (
        Flashcard(front = "What is ADB?",
                  back = "Android Debug Bridge. A command line tool that lets you communicate with" +
                          " a device"),
        Flashcard(front = "What is Gradle?",
                  back = "Build system. Allows multiple build type and flavors"),
        Flashcard(front = "What is the AndroidManifest.xml file?",
                  back = "Located in the root folder of the project. It contains: name and icon, " +
                          "Activities, Services, ContentProviders, and Broadcast Receivers" +
                          "Permissions and version info")
            )



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

       val binding = DataBindingUtil.inflate<FragmentFlashCardBinding>(
            inflater,R.layout.fragment_flash_card, container, false)
        binding.fcard = this

        setFlashcard()
        requireContext().applicationContext
        val scale = resources.displayMetrics.density

        val front = binding.root.findViewById(R.id.card_front) as TextView
        val back = binding.root.findViewById(R.id.card_back) as TextView


        front.cameraDistance = 8000 * scale
        back.cameraDistance = 8000 * scale

        frontIn = AnimatorInflater.loadAnimator(
            binding.root.context,
            R.animator.front_into_view
        ) as AnimatorSet
        frontOut = AnimatorInflater.loadAnimator(
            binding.root.context,
            R.animator.front_out_of_view
        ) as AnimatorSet
        backIn = AnimatorInflater.loadAnimator(
            binding.root.context,
            R.animator.back_into_view
        ) as AnimatorSet
        backOut = AnimatorInflater.loadAnimator(
            binding.root.context,
            R.animator.back_out_of_view
        ) as AnimatorSet


        binding.cardFront.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER") {
            if(isFront) {
                frontOut.setTarget(front)
                backIn.setTarget(back)
                frontOut.start()
                backIn.start()
                isFront = false
            } else {
                backOut.setTarget(back)
                frontIn.setTarget(front)
                backOut.start()
                frontIn.start()
                isFront = true
            }
        }

        binding.nextButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER") {
                nextOnClick(front, back)
                setText(front, back)
        }

        binding.prevButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER") {
            prevOnClick(front, back)
            setText(front, back)
        }

        return binding.root



    }


    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setFlashcard() {
        currentCard = flashcards[flashcardIndex]
    }

    private fun setText(front: TextView, back: TextView) {
        setFlashcard()
        front.text = currentCard.front
        back.text = currentCard.back
    }

    private fun nextOnClick(front: TextView, back: TextView) {
        if(flashcardIndex == flashcards.size - 1) {
            if(isFront) {
                flashcardIndex = 0
            } else {
                backOut.setTarget(back)
                frontIn.setTarget(front)
                backOut.start()
                frontIn.start()
                flashcardIndex = 0
                isFront = true
            }
        } else  {
            if(isFront) {
                flashcardIndex += 1
            } else {
                backOut.setTarget(back)
                frontIn.setTarget(front)
                backOut.start()
                frontIn.start()
                flashcardIndex += 1
                isFront = true
            }
        }
    }

    private fun prevOnClick(front: TextView, back: TextView) {
        if(flashcardIndex == 0) {
            if(isFront) {
                flashcardIndex = flashcards.size - 1
            } else {
                backOut.setTarget(back)
                frontIn.setTarget(front)
                backOut.start()
                frontIn.start()
                flashcardIndex = flashcards.size - 1
                isFront = true
            }
        } else  {
            if(isFront) {
                flashcardIndex -= 1
            } else {
                backOut.setTarget(back)
                frontIn.setTarget(front)
                backOut.start()
                frontIn.start()
                flashcardIndex -= 1
                isFront = true
            }
        }

    }


}
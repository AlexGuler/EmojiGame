package com.example.emojigame.PuzzlePage

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.emojigame.Model.Puzzle
import com.example.emojigame.R


class EmojiPuzzleAdapter : RecyclerView.Adapter<EmojiPuzzleAdapter.EmojiPuzzleViewHolder>() {

    private val emojiPuzzles: ArrayList<Puzzle> = ArrayList()

    override fun getItemCount(): Int = emojiPuzzles.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiPuzzleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_emoji_puzzle, parent, false)
        return EmojiPuzzleViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmojiPuzzleViewHolder, position: Int) {
        holder.bind(emojiPuzzles[position])
    }

    fun submitList(newEmojiPuzzles: List<Puzzle>) {
        emojiPuzzles.clear()
        emojiPuzzles.addAll(newEmojiPuzzles)
        notifyDataSetChanged()
    }

    inner class EmojiPuzzleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val emojiPuzzleText: TextView = itemView.findViewById(R.id.emojiPuzzleText)
        private val answerText: EditText = itemView.findViewById(R.id.answerText)
        private val guessButton: TextView = itemView.findViewById(R.id.guessButton)
        private val feedbackText: TextView = itemView.findViewById(R.id.feedbackText)
        private val descriptionText: TextView = itemView.findViewById(R.id.decriptionText)

        fun bind(puzzle: Puzzle) {
            emojiPuzzleText.text = puzzle.emojiPuzzle
            descriptionText.text = puzzle.description
            answerText.addTextChangedListener(getOnTextChangedListener())
            guessButton.setOnClickListener {
                val inputText = answerText.text.toString().trim().toLowerCase()

                val isCorrect = inputText == puzzle.answer.toLowerCase()
                feedbackText.text = if (isCorrect) "Correct!" else "Incorrect"
                feedbackText.setTextColor(getCorrectOrIncorrectColor(isCorrect))
            }
        }

        private fun getCorrectOrIncorrectColor(correct: Boolean): Int {
            return if (correct) {
                ContextCompat.getColor(itemView.context, R.color.successGreen)
            } else {
                ContextCompat.getColor(itemView.context, R.color.unsuccessRed)
            }
        }

        private fun getOnTextChangedListener(): TextWatcher {
            return object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) { }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    feedbackText.text = ""
                }
            }
        }
    }
}

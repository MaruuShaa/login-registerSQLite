package com.example.myapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityNoteBinding
    private lateinit var db:NoteDatabaseHelper
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDatabaseHelper(this)
        notesAdapter = NotesAdapter(db.getAllNotes(), this)

        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerView.adapter = notesAdapter

        binding.addButton.setOnClickListener{
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }

}
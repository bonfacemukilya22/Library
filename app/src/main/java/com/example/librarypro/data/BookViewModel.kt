package com.example.librarypro.data

import android.content.Context
import androidx.navigation.NavHostController
import android.app.ProgressDialog
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.text.input.KeyboardType
import com.example.librarypro.Navigation.ROUTE_ADD_BOOK
import com.example.librarypro.Navigation.ROUTE_BORROWED_BOOKS
import com.example.librarypro.Navigation.ROUTE_BORROW_BOOK
import com.example.librarypro.Navigation.ROUTE_LOGIN
import com.example.librarypro.Navigation.ROUTE_VIEW_BOOK
import com.example.librarypro.Navigation.ROUTE_VIEW_UPLOAD
import com.example.librarypro.model.Books
import com.example.librarypro.model.Upload
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage


class BookViewModel(var navController: NavHostController, var context: Context) {
    var authRepository: AuthViewModel
    var progress: ProgressDialog

    init {
        authRepository = AuthViewModel(navController, context)
        if (!authRepository.isloggedin()) {
            navController.navigate(ROUTE_LOGIN)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait a moment...")
    }


    fun saveBook(title: String, author: String, isbn: String) {
        var id = System.currentTimeMillis().toString()
        var bookData = Books(title, author, isbn, id)
        var bookRef = FirebaseDatabase.getInstance().getReference()
            .child("books/$id")
        progress.show()
        bookRef.setValue(bookData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Book added successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    fun viewBooks(
        book: MutableState<Books>,
        books: SnapshotStateList<Books>): SnapshotStateList<Books> {
        var ref = FirebaseDatabase.getInstance().getReference().child("books")

//        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
//                progress.dismiss()
                books.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Books::class.java)
                    book.value = value!!
                    books.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return books
    }


    fun deleteBook(id: String) {
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("books/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "the book is successfully deleted", Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_VIEW_BOOK)
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_VIEW_BOOK)
            }
        }
    }

    fun updateBook(title: String, author: String, isbn: String,id: String) {
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("books/$id")
        progress.show()
        var updateData = Books(title, author, isbn, id)
        updateRef.setValue(updateData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_VIEW_BOOK)
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_VIEW_BOOK)
            }
        }
    }
    fun saveBookWithImage(title: String, author: String, isbn: String, filePath: Uri){
        var id = System.currentTimeMillis().toString()
        var storageReference = FirebaseStorage.getInstance().getReference().child("Uploads/$id")
        progress.show()

        storageReference.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Proceed to store other data into the db
                storageReference.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var houseData = Upload(title,author,isbn,imageUrl,id)
                    var dbRef = FirebaseDatabase.getInstance()
                        .getReference().child("Uploads/$id")
                    dbRef.setValue(houseData)
                    Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT).show()
                    navController.navigate(ROUTE_VIEW_UPLOAD)
                }
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
                navController.navigate(ROUTE_ADD_BOOK)
            }
        }
    }

    fun viewUpload(upload: MutableState<Upload>, uploads: SnapshotStateList<Upload>): SnapshotStateList<Upload> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Uploads")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                uploads.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Upload::class.java)
                    upload.value = value!!
                    uploads.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return uploads
    }


    fun borrowBook(bookTitle: String, borrowedBooksRef: DatabaseReference, callback: (String) -> Unit) {
        borrowedBooksRef.orderByChild("title").equalTo(bookTitle).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    // Book is not borrowed, add it to the borrowed books
//                val book = Book(title = bookTitle, author = bookAuthor, isbn = bookISBN)
                    borrowedBooksRef.push().setValue(Books())
                    callback("Book borrowed successfully.")
                    navController.navigate(ROUTE_BORROWED_BOOKS)
                } else {
                    callback("Sorry, this book is not available for now. Already borrowed. Try a different one.")
                    navController.navigate(ROUTE_BORROW_BOOK)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                callback("Error: ${error.message}")
            }
        })
    }

    fun viewBorrowedBooks(
        book: MutableState<Books>,
        books: SnapshotStateList<Books>): SnapshotStateList<Books> {
        var ref = FirebaseDatabase.getInstance().getReference().child("borrowedBooks")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                books.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Books::class.java)
                    book.value = value!!
                    books.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return books
    }


}




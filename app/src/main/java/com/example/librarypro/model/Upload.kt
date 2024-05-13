package com.example.librarypro.model

class Upload {
    var title:String=""
    var auther:String=""
    var isbn:String=""
    var imageUrl:String=""
    var id:String=""

    constructor(title:String,auther:String,isbn:String,imageUrl:String,id:String) {

        this.title = title
        this.auther =auther
        this.isbn=isbn
        this.imageUrl=imageUrl
        this.id=id
    }
    constructor()
}
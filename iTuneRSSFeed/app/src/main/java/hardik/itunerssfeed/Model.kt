package hardik.itunerssfeed

public class Model{
    lateinit var artistName:String
    lateinit var name:String
    lateinit var url:String

    constructor(artistName: String,name:String,url:String) {
        this.artistName = artistName
        this.name = name
        this.url = url
    }

    constructor()
}
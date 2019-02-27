package hardik.itunerssfeed


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.media_list_item.view.*


  class CustomAdapter(val items: ArrayList<Model>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of items in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.media_list_item, p0, false))
    }

    // Binds each item in the ArrayList to a view
    override fun onBindViewHolder(p0: ViewHolder, position: Int) {
        p0?.name?.text = items.get(position).name
        p0?.artistName?.text = items.get(position).artistName

        // load the image with Picasso
        Picasso.get()
                .load(items.get(position).url) // load the image
                .into(p0?.iv_album_cover) // select the ImageView to load it into
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the views that will add each item to
    val iv_album_cover = view.iv_album_cover
    val name = view.name
    val artistName = view.artistName
}
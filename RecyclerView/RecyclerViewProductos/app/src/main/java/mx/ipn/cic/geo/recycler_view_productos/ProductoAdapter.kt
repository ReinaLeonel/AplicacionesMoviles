package mx.ipn.cic.geo.recycler_view_productos

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import mx.ipn.cic.geo.recycler_view_productos.databinding.ItemProductoBinding
import java.io.ByteArrayInputStream

class ProductoAdapter(private val onClick: (Producto) -> Unit) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {
    var productos = listOf<Producto>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val article = productos[position]
        holder.bind(article)
    }

    override fun getItemCount() = productos.size

    class ProductoViewHolder(itemView: View, val onClick: (Producto) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemProductoBinding.bind(itemView)
        private var productoSelect: Producto? = null

        init {
            itemView.setOnClickListener {
                productoSelect?.let{
                    onClick(it) }
            }
        }

        fun bind(producto: Producto) {
            productoSelect = producto

            var imagen = base64ToBitmap(producto.imagen)
            //Picasso.get().load(imagen).into(binding.productoImage)
            binding.productoImage.setImageBitmap(imagen)
            binding.productoText.text = producto.nombre
        }

        private fun base64ToBitmap(base64Image: String): Bitmap {
            val decodedBytes = Base64.decode(base64Image, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
            return  bitmap
        }
    }
}



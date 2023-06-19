package mx.ipn.cic.geo.recycler_view_productos

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import mx.ipn.cic.geo.recycler_view_productos.databinding.ActivityProductoDetallesBinding

class ProductoDetallesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductoDetallesBinding
    private lateinit var producto: Producto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_producto_detalles)

        binding = ActivityProductoDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Obteniendo id del producto que fue seleccionado
        var idProductoSelect: Int? = null
        idProductoSelect = intent.getIntExtra("idProducto", -1)
        Log.d("ID Producto", "idProducto: $idProductoSelect")

        //Mensaje en caso de que no se obtenga el id del producto
        if (idProductoSelect == -1 || idProductoSelect == null)
            showMessage("A ocurrido un error")

        // Obteniendo producto y convirtiendo la imagen
        producto = getProducto(idProductoSelect)
        Log.d("Producto Encontrado", "producto: $producto")
        var imagen = base64ToBitmap(producto.imagen)

        //Enlazando los elementos del producto
        binding.nombreProducto.text = producto.nombre
        binding.imagenProductoDetalle.setImageBitmap(imagen)
        binding.productoDescripcion.text = producto.descripcion
        binding.productoMarca.text = producto.marca
        binding.productoPrecio.text = producto.precio.toString()




    }

    //Funcion para mostrar Toast con mensaje
    private fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun getProducto(id: Int): Producto{
        lateinit var producto: Producto
        for (item in Producto.data) {
            if ( item.id == id )
                producto = item
        }
        return producto
    }

    private fun base64ToBitmap(base64Image: String): Bitmap {
        val decodedBytes = Base64.decode(base64Image, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        return  bitmap
    }
}
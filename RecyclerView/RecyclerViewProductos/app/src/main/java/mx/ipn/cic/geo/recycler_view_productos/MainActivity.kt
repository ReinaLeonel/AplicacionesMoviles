package mx.ipn.cic.geo.recycler_view_productos

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.FirebaseDatabase
import mx.ipn.cic.geo.recycler_view_productos.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ProductoAdapter
    private val productos = mutableListOf<Producto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        //Agregando el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()



        //Codigo para probar la escritura en real console firebase
        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("RecyclerViewProductos/Usuarios/message")

        myRef.setValue("Hello, World!")
    }

    //Funcion para iniciar el recyclerView
    private fun initRecyclerView(){
        adapter = ProductoAdapter { producto -> onClickItem(producto) }
        readProducts()
        binding.listaProductos.layoutManager = LinearLayoutManager(this)
        binding.listaProductos.adapter = adapter
        adapter.productos = productos
    }

    //Funcion para leer los datos de los productos
    private fun readProducts(){
        val products: List<Producto> = Producto?.data ?: emptyList()
        if ( products.isEmpty() ) showMessage("No hay productos por mostrar")
        else Log.d("Lista productos", "Productos: ${Producto.data}")
            //else showMessage("Los productos son: Producto.data")
        productos.clear()
        productos.addAll(products)
        adapter.notifyDataSetChanged()

    }

    //Funcion para mostrar Toast con mensaje
    private fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    //Abrir los detalles del producto cuando se presiona un item del RecyclerView
    private fun onClickItem(producto: Producto){
        val intent = Intent(this, ProductoDetallesActivity()::class.java)
        intent.putExtra("idProducto", producto.id)
        startActivity(intent)
    }

    //
}
package com.example.mobilevynils.ui.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.mobilevynils.R
import com.example.mobilevynils.viewModels.AlbumCreateViewModel
import org.json.JSONObject
import java.util.Calendar

class AlbumCreateFragment : Fragment() {

    private lateinit var viewModel: AlbumCreateViewModel
    private lateinit var dateEdt: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater!!.inflate(R.layout.album_create, container, false)

        //Seleccionador de fecha
        dateEdt =  view.findViewById<EditText>(R.id.fechaAlbum)

        dateEdt.setOnClickListener {

            val c = Calendar.getInstance()

            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                view.context,
                { view, year, monthOfYear, dayOfMonth ->
                    var day = "";
                    if (dayOfMonth.toString().length==1)
                    {
                        day = "0"+dayOfMonth.toString()
                    }
                    else
                    {
                        day = dayOfMonth.toString()
                    }
                    val dat = (year.toString() + "-"+ (monthOfYear + 1) +"-"+day)
                    dateEdt.setText(dat)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }


        val botonGuardar = view.findViewById<Button>(R.id.botonGuardarAlbum)
        botonGuardar.setOnClickListener{
            var nombreAlbum = view.findViewById<EditText>(R.id.nombreAlbum).text.toString();
            var urlAlbum = view.findViewById<EditText>(R.id.urlAlbum).text.toString();
            var fechaAlbum = view.findViewById<EditText>(R.id.fechaAlbum).text.toString();
            var generoAlbum = view.findViewById<Spinner>(R.id.generoAlbum).selectedItem.toString();
            var empresaAlbum = view.findViewById<Spinner>(R.id.empresaAlbum).selectedItem.toString();
            var descripcionAlbum = view.findViewById<EditText>(R.id.descripcionAlbum).text.toString();

            var errores = "";
            if (nombreAlbum.isEmpty())
            {
                errores+="Debes Ingresar el nombre del album\n";
            }
            if (urlAlbum.isEmpty())
            {
                errores+="Debes Ingresar la URL del album\n";
            }
            if (fechaAlbum.isEmpty())
            {
                errores+="Debes Ingresar la Fecha del album\n";
            }
            if (generoAlbum.isEmpty())
            {
                errores+="Debes Ingresar el genero del album\n";
            }
            if (empresaAlbum.isEmpty())
            {
                errores+="Debes Ingresar la empresa discrografica del album\n";
            }
            if (descripcionAlbum.isEmpty())
            {
                errores+="Debes Ingresar la descripción del album\n";
            }

            if(!errores.isEmpty())
            {
                val builder = AlertDialog.Builder(this.context)

                with(builder)
                {
                    setTitle("Se encontraron los siguientes errores")
                    setMessage(errores)
                    setNegativeButton("Cerrar",null)
                    show()
                }
            }
            else
            {
                viewModel.crearAlbum(nombreAlbum,urlAlbum,fechaAlbum,generoAlbum,empresaAlbum,descripcionAlbum,{

                    Toast.makeText(this.context,"Se creo el album correctamente"  , Toast.LENGTH_SHORT).show()
                    view.findNavController().popBackStack(R.id.albumCreateFragment,true)
                    view.findNavController().navigate(R.id.albumes_fragment)
                },{
                    var responseError = it.networkResponse
                    var respuesta = ""
                    if(responseError != null) respuesta  = String(responseError.data)
                    val responseObject = JSONObject(respuesta)
                    val message = responseObject.optString("message")
                    val builder = AlertDialog.Builder(this.context)

                    with(builder)
                    {
                        setTitle("Se genero un error al crear el album")
                        setMessage(message)
                        setNegativeButton("Cerrar",null)
                        show()
                    }
                })
            }
        }



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_albumes)
        viewModel = ViewModelProvider(this, AlbumCreateViewModel.Factory(activity.application)).get(AlbumCreateViewModel::class.java)


    }
}
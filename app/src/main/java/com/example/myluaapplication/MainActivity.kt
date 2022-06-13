package com.example.myluaapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myluaapplication.component.CustomValue
import com.example.myluaapplication.component.Hyperbolic
import org.luaj.vm2.Globals
import org.luaj.vm2.LoadState
import org.luaj.vm2.LuaValue
import org.luaj.vm2.compiler.LuaC
import org.luaj.vm2.lib.*
import org.luaj.vm2.lib.jse.*
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private val globals: Globals = JsePlatform.standardGlobals()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupStringLua()
        setupFileLua()
        setupHyperbolic()
        setupStringReturn()
        setupCustomInput()
    }

    private fun setupCustomInput() {
        findViewById<Button>(R.id.btn_file_custom_input_to_luo).setOnClickListener {
//            val inputStream: InputStream = assets.open("ListExample.lua")
//
//            val chuck: LuaValue = globals.load(inputStream, "stringOutput", "bt", globals)
//            chuck.invoke()

            val text = findViewById<EditText>(R.id.edt_custom_input).text.toString()

            var value = 1
            try {
                val parseValue = Integer.parseInt(text)
                if (parseValue in 2..9) {
                    value = parseValue
                }
            } catch (e: Exception) {
                // Do nothing
            }

            Toast.makeText(this, value.toString(), Toast.LENGTH_SHORT).show()

            CustomValue.myValue = value

            val inputStream: InputStream = assets.open("CustomValue.lua")

            val chuck: LuaValue = globals.load(inputStream, "customValue", "bt", globals)
            chuck.invoke()

        }
    }

    private fun setupStringReturn() {
        findViewById<Button>(R.id.btn_file_input_to_luo).setOnClickListener {
            val inputStream: InputStream = assets.open("ListExample.lua")

            val chuck: LuaValue = globals.load(inputStream, "stringOutput", "bt", globals)
            chuck.invoke()
        }
    }

    private fun setupHyperbolic() {
        findViewById<Button>(R.id.btn_file_custom_lua).setOnClickListener {
            val globals: Globals = customEvn()

            val inputStream: InputStream = assets.open("HyperbolicApp.lua")

            // init global before
            // create an environment to run in
            // Globals globals = JsePlatform.standardGlobals();
            // Use the convenience function on Globals to load a chunk.
            /** Load the content form an input stream as a binary chunk or text file.
             * @param inputStream InputStream containing a lua script or compiled lua"
             * @param chunkname Name that will be used within the chunk as the source.
             * @param mode String containing 'b' or 't' or both to control loading as binary or text or either.
             * @param environment LuaTable to be used as the environment for the loaded function.
             * */
            val chunk = globals.load(inputStream, "@" + "hyperbolicapp", "bt", globals)
            // Use any of the "call()" or "invoke()" functions directly on the chunk.
            // Use any of the "call()" or "invoke()" functions directly on the chunk.
            chunk.invoke()
        }
    }

    private fun setupFileLua() {
        findViewById<Button>(R.id.btn_file_lua).setOnClickListener {
            val inputStream: InputStream = assets.open("SimpleExample.lua")

            val chuck: LuaValue = globals.load(inputStream, "basic", "bt", globals)
            chuck.invoke()
        }
    }

    private fun setupStringLua() {
        findViewById<Button>(R.id.btn_string_lua).setOnClickListener {
            val command = """
    
        -- defines a factorial function
        function fact (n)
          if n == 0 then
            return 1
          else
            return n * fact(n-1)
          end
        end
        
        LuajLog = luajava.bindClass("com.example.myluaapplication.component.LuajLog")
        LuajLog:d("hello luaj...1")
        LuajLog:d("hello luaj...2")
        LuajLog:d("hello luaj...3")
        LuajLog:d(fact(5))
            """.trimIndent()

            val chuck: LuaValue = globals.load(command, "basic")
            chuck.invoke()
        }
    }

    private fun customEvn(): Globals {
        val globals = Globals()
        globals.load(JseBaseLib())
        globals.load(PackageLib())
        globals.load(Bit32Lib())
        globals.load(TableLib())
        globals.load(StringLib())
        globals.load(CoroutineLib())
        globals.load(JseMathLib())
        globals.load(JseIoLib())
        globals.load(JseOsLib())
        globals.load(LuajavaLib())
        //todo register library
        globals.load(Hyperbolic())
        LoadState.install(globals)
        LuaC.install(globals)
        return globals
    }
}
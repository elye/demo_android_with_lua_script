package com.example.myluaapplication.component;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;

public class Hyperbolic extends TwoArgFunction {

    /** Public constructor.  To be loaded via require(), the library class
     * must have a public constructor.
     */
    public Hyperbolic() {}

    /** The implementation of the TwoArgFunction interface.
     * This will be called once when the library is loaded via require().
     * @param modname LuaString containing the name used in the call to require().
     * @param env LuaValue containing the environment for this function.
     * @return Value that will be returned in the require() call.  In this case,
     * it is the library itself.
     */
    public LuaValue call(LuaValue modname, LuaValue env) {
        LuaValue library = tableOf();
        library.set( "sinh", new sinh() );
        library.set( "cosh", new cosh() );
        env.set( "hyperbolic", library );
        //todo 官方这个例子少了如下的注册函数
        env.get("package").get("loaded").set("hyperbolic", library);
        return library;
    }

    /* Each library function is coded as a specific LibFunction based on the
     * arguments it expects and returns.  By using OneArgFunction, rather than
     * LibFunction directly, the number of arguments supplied will be coerced
     * to match what the implementation expects.  */

    /** Mathematical sinh function provided as a OneArgFunction.  */
    static class sinh extends OneArgFunction {
        public LuaValue call(LuaValue x) {
            return LuaValue.valueOf(Math.sinh(x.checkdouble()));
        }
    }

    /** Mathematical cosh function provided as a OneArgFunction.  */
    static class cosh extends OneArgFunction {
        public LuaValue call(LuaValue x) {
            return LuaValue.valueOf(Math.cosh(x.checkdouble()));
        }
    }
}

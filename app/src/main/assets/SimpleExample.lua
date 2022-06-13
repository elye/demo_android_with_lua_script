 -- defines a factorial function
function fact (n)
  if n == 0 then
    return 1
  else
    return n * fact(n-1)
  end
end

LuajLog = luajava.bindClass("com.example.myluaapplication.component.LuajLog")
LuajLog:d("hello luaj file...1")
LuajLog:d("hello luaj file...2")
LuajLog:d("hello luaj file...3")
LuajLog:d(fact(5))

LuajLog = luajava.bindClass("com.example.myluaapplication.component.LuajLog")
CustomValue = luajava.bindClass("com.example.myluaapplication.component.CustomValue")

 -- defines a factorial function
function fact (n)
  if n == 0 then
    return 1
  else
    return n * fact(n-1)
  end
end

local customValue = CustomValue:getCustomValue()
LuajLog:d("type of string_list type: "..type(customValue))
LuajLog:d(string.format('value : '..customValue))
LuajLog:d(string.format("Factorial of %d is "..fact(customValue), customValue))

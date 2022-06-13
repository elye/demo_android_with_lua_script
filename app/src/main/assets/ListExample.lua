LuajLog = luajava.bindClass("com.example.myluaapplication.component.LuajLog")
ListReturn = luajava.bindClass("com.example.myluaapplication.component.ListReturn")

local string_list = ListReturn:getStringTest()
LuajLog:d("type of string_list type: "..type(string_list))
LuajLog:d("type of string_list length: "..#string_list)
LuajLog:d('show all element #1 ')
for i=1, #string_list do
    LuajLog:d(string.format('string_list[%d] : '..string_list[i], i))
end

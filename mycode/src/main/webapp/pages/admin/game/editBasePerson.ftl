<form>
    <div>
            <label for="name">姓名:</label>
            <input class="easyui-validatebox" type="text" name="name"
                   data-options="required:true,validType:['length[1,5]']"  />
    </div>
    <div style="margin-top: 5px;">
        <label for="name">年龄:</label>
        <input class="easyui-validatebox" type="text" name="age"
               data-options="required:true,validType:['range[5,49]']"  />
    </div>
</form>
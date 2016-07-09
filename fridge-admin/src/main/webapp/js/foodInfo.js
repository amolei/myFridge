function choseKind(id,name){
    var h = '<span class="caret"></span>';
    $('#dropdownMenu1').html(name + h);
    $.ajax({
        type:'post',
        url:'queryFoodListByKindId.do',
        dataType:'json',
        data:{'food_kind_id':id},
        success : function(data){
            var txt = "";
            $.each(data.items, function (index,element) {
                var num = index + 1;
                var hot = "否";
                if(element.hot == 1){
                    hot = "是";
                }
                txt = txt + "<tr><td>" + element.food_name + "</td><td>" + element.food_des + "</td><td>" + element.simple_name + "</td><td>" + element.kind_name + "</td><td><img width=\"80\" height=\"80\" src=\"http://139.196.171.209/" + element.food_img + "\"></td><td>" + hot + "</td><td><button type=\"button\" class=\"btn btn-primary\" onclick=\"openModifyModal(" + element.food_id + ");\">修改</button><button onclick=\"delFoodInfo(" + element.food_id + ");\" class=\"btn btn-warning delZoneButton\" type=\"button\">删除</button></td></tr>";
            });
            $("table tbody").html(txt);
        },
        error: function (err) {
         var txt = '出错了';
            alert(txt);
        }
    });
}

function validateFoodInfoName(){
    var food_name = $('#food_name').val();
    $.post(
        "validateFoodInfoName.do",
        {
            "food_name":food_name
        },
        function(data){
            if(data.flag == 1){
                $('#food_name').val("");
                alert("食品已存在!");
            }
        });
}
function submitForm(){
    var food_name = $('#food_name').val();
    var food_info = $('#food_info').val();
    var simple_name = $('#simple_name').val();
    if(food_name == null || food_name == ''){
        alert("名称不能为空");
        return ;
    }
    if(food_info == null || food_info == ''){
        alert("描述不能为空");
        return ;
    }
    if(simple_name == null || simple_name == ''){
        alert("简称不能为空");
        return;
    }
    $('#createFoodInfoForm').submit();
}

function delFoodInfo(id){
    window.location.href = 'delFoodInfoById.do?food_id=' + id;
}

function openModifyModal(id) {
    $.ajax({
        type : "post",
        url : "getFoodInfoById.do",
        dataType : "json",
        data : {
            "id" : id
        },
        cache : false,
        success : function(data) {
            if (data != null) {
                var obj = data.foodInfo;
                $('input[name=modify_food_id]').val(obj.food_id);
                $('input[name=modify_food_name]').val(obj.food_name);
                $('input[name=modify_food_info]')
                    .val(obj.food_des);
                $('input[name=modify_simple_name]')
                    .val(obj.simple_name);
                if(obj.hot == 1){
                    $('#modify_hot').prop("checked",true);
                }else{
                    $('#modify_hot').prop("checked",false);
                }
                var kindList = "";
                $.each(data.foodKindList, function (index,element) {
                    if(element.food_kind_id == obj.kind_id){
                        kindList = kindList + "<option value=" + element.food_kind_id + " selected='selected'>" + element.food_kind_name+ "</option>";
                    }else{
                        kindList = kindList + "<option value=" + element.food_kind_id + ">" + element.food_kind_name+ "</option>";
                    }

                })
                $('#modify_food_kind').html(kindList);
                $('#modifyModal').modal('show');
            }
        },
        error : function(err) {
            var txt = "出错了";
            alert('error');
        }
    });
}

function submitModifyModalForm() {
    $('#modifyFoodInfoForm').submit();
}
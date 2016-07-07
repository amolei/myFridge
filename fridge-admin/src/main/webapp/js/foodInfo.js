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
                txt = txt + "<tr><td>" + num + "</td><td>" + element.food_name + "</td><td>" + element.food_des + "</td><td>" + element.simple_name + "</td><td>" + element.kind_name + "</td><td><img width=\"80\" height=\"80\" src=\"http://139.196.171.209/" + element.food_img + "\"></td><td>" + hot + "</td><td><button type=\"button\" class=\"btn btn-primary\" onclick=\"openModifyModal(" + element.food_id + ");\">修改</button><button onclick=\"delFoodInfo(" + element.food_id + ");\" class=\"btn btn-warning delZoneButton\" type=\"button\">删除</button></td></tr>";
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

function openModifyModal(id){
    alert(id);
}
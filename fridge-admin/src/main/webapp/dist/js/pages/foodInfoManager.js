$(document).ready(function(){
    $.get("queryFoodKindList4Ajax.do",function(data){

        $.each(data.items, function (index,element) {
            var txt = "<option value=\"" + element.food_kind_id + "\">" + element.food_kind_name + "</option>";
            $('#foodKindSelect').append(txt);
        });
    });

    $('#foodKindSelect').change(function(){
        var food_kind_id = $('#foodKindSelect').val();
        $.post(
            "queryFoodListByKindId.do",
            {'food_kind_id':food_kind_id},
            function(data){
                var txt = "";
                $.each(data.items, function (index,element) {
                    var hot = "否";
                    if(element.hot == 1){
                        hot = "是";
                    }
                    txt = txt + "<tr><td>" + element.food_id + "</td><td>" + element.food_name + "</td><td>" + element.food_des + "</td><td>" + element.simple_name + "</td><td>" + element.foodKindDto.food_kind_name + "</td><td><img width=\"60\" height=\"60\" src=\"http://139.196.171.209/" + element.food_img + "\"></td><td>" + hot + "</td><td><button class=\"btn btn-block btn-danger\" type=\"button\">删除</button></td></tr>";
                });
                $("table tbody").html(txt);
            }
        );
    });
});



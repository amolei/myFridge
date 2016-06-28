$(document).ready(function() {
    $.get("../queryFoodKindList4Ajax.do", function (data) {

        $.each(data.items, function (index, element) {
            var txt = "<option value=\"" + element.food_kind_id + "\">" + element.food_kind_name + "</option>";
            $('#food_kind').append(txt);
        });
    });
})
function validateFoodInfoName(){
    var food_name = $('#food_name').val();
    $.post(
        "../validateFoodInfoName.do",
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
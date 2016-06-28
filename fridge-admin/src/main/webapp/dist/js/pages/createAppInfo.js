function validateAppVersion(){
    var app_version = $('#app_version').val();
    $.post(
        "../validateAppVersion.do",
        {
            "app_version":app_version
        },
        function(data){
            if(data.flag == 1){
                $('#app_version').val("");
                alert("版本已存在");
            }
        }
    );
}
function validateAppInfoName(){
    var app_name = $('#app_name').val();
    $.post(
        "../validateAppInfoName.do",
        {
            "app_name":app_name
        },
        function(data){
            if(data.flag == 1){
                $('#app_name').val("");
                alert("名称已存在!");
            }
        });
}
function submitForm(){
    var app_name = $('#app_name').val();
    var app_info = $('#app_info').val();
    var app_version = $('#app_version').val();
    if(app_name == null || app_name == ''){
        alert("名称不能为空");
        return ;
    }
    if(app_info == null || app_info == ''){
        alert("描述不能为空");
        return ;
    }
    if(app_version == null || app_version == ''){
        alert("版本号不能为空");
        return;
    }
    $('#createAppInfoForm').submit();
}
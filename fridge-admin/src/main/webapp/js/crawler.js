function submitForm(){
    var source_name = $('#source_name').val();
    if(source_name == null || source_name == ''){
        alert("名称不能为空");
        return ;
    }
    $('#createCrawlerSourceForm').submit();
}

function del(id){
    window.location.href = 'delCrawlerSource.do?source_id=' + id;
}

function openModifyModal(id) {
    $.ajax({
        type : "post",
        url : "getById.do",
        dataType : "json",
        data : {
            "source_id" : id
        },
        cache : false,
        success : function(data) {
            if (data != null) {
                var obj = data.crawlerSource;
                $('input[name=modifyModal_source_id]').val(obj.source_id);
                $('input[name=modifyModal_source_name]').val(obj.source_name);
                $('input[name=modifyModal_source_path]').val(obj.source_path);
                var txt = "";
                if(obj.source_type == 0){
                    txt = "<option value='0' selected='true'>微博九宫格</option><option value='1'>普通非微博</option>";
                }else {
                    txt = "<option value='0'>微博九宫格</option><option value='1' selected='true'>普通非微博</option>";
                }
                $('#modifyModal_source_type').html(txt);
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
    $('#modifyModalCrawlerSourceForm').submit();
}


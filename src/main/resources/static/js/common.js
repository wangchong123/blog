function createIndex(){
    $.ajax({
        url:"/article/createLuceneIndex.do",
        type:"post",
        dateType:"json",
        success:function(data){
            if(data.result){
                alert("生成成功");
            }
        }
    })
}
//渲染文章分类
function renderType(){
    $.ajax({
        url:"/type/typeList.do",
        type:"post",
        dateType:"json",
        success:function(data){
            var html="";
            $.each(data.list,function(i,v){
                html+="<a href=/article/index.do?type="+v.id+"><li>"+v.name+"("+v.count+")"+"</li></a>";
            })
            $("#right-type").html(html);
        }
    })
}

function renderRank(){
    $.ajax({
        url:"/article/queryRank.do",
        type:"post",
        dateType:"json",
        success:function(data){
            var html="";
            $.each(data.list,function(i,v){
                html+="<a href=/article/articleDetail.do?id="+v.id+"><li>"+v.title+"</li></a>";
            })
            $("#right-rank").html(html);
        }
    })
}
$(document).ready(function(){
    renderType();
    renderRank();
})

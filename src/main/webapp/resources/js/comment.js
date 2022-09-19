/**
 * 댓글 
 * 2-07_watchAnswer.jsp에서 호출
 */

var commentService = (function() {
    
    // 댓글 등록
    function add(comment, callback, error) {
        console.log("댓글 등록하기");
        
        $.ajax({
            type: 'post',
            url: '/comment/new',
            data: JSON.stringify(comment),
            contentType: "application/json;charset=utf-8",
            success: function(result, status, xhr) {
                if(callback) { callback(result); }
            }, 
            error: function(xhr, status, e) {
                if(error) { error(e); }
            }
        }) // ajax
    } // add
    
    // 댓글 리스트 출력
    function getList(param, callback, error) {
        var a_number = param.a_number;
        var currPage = param.currPage || 1;
        
        $.getJSON(
            "/comment/list/" + a_number + "/" + currPage,
            function(data) { if(callback) { callback(data); }}
        ).fail(
            function(xhr, status, err) { if(error) { error(); }}
        );
    } // getList

    return { 
        add : add, 
        getList : getList
    };


})(); // commentService



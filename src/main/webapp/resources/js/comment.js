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

    // 시간 처리
    // 해당일은 '시/분/초', 전날부터는 '년/월/일'을 출력
    function displayTime(timeValue) {
        var today = new Date();
        var gap = today.getTime() - timeValue;
        var dateObj = new Date(timeValue);
        var str= "";
        
        if(gap < (1000*60*60*24)) {
            var hh = dateObj.getHours();
            var mi = dateObj.getMinutes();
            var ss = dateObj.getSeconds();
            
            return [(hh>9 ? '' : '0') + hh, ':', (mi>9 ? '' : '0') + mi, ':', (ss>9? '' : '0') + ss].join('');
        } else {
            var yy = dateObj.getFullYear();
            var mm = dateObj.getMonth() + 1; // getMonth는 zero-based이므로 +1 해줌
            var dd = dateObj.getDate();
            return [yy, '/', (mm>9 ? '': '0') + mm, '/', (dd>9? '':'0') + dd].join('');
        }
    };

    return { 
        add : add, 
        getList : getList,
        displayTime : displayTime
    };
    

})(); // commentService



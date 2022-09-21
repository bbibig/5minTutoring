// 결제 API
var IMP = window.IMP; // 생략 가능
IMP.init("imp88781134"); // 예: imp00000000

function requestPay() {
    let user_name = '${__LOGIN_USER__.user_name}';
    let h_name = '${_HandVO_.h_name}';

    // IMP.request_pay(param, callback) 결제창 호출
    IMP.request_pay({ // param
        pg: "html5_inicis",     // PG사 선택
        pay_method: "card",
        merchant_uid: "33",   // 주문번호
        name: h_name, // 상품명
        amount: 6600,  // 결제금액
        buyer_email: "test@gmail.com",
        // buyer_name: "이윤정",
        buyer_name: user_name,
        buyer_tel: "010-3333-3333"
    }, function (rsp) { // callback
        console.log(rsp);
        if (rsp.success) {
          var msg = '결제가 완료되었습니다.';
          alert(msg);
          location.href = "/hand/buyhands"
        } else {
          var msg = '결제에 실패하였습니다.';
          msg += '에러내용 : ' + rsp.error_msg;
          alert(msg);
        }
    });
  }


// 수량
let basket = {
    totalCount: 0, 
    totalPrice: 0,

    //재계산
    reCalc: function(){
        this.totalCount = 0;
        this.totalPrice = 0;
        document.querySelectorAll(".p_num").forEach(function (item) {
            if(item.parentElement.parentElement.parentElement.previousElementSibling.firstElementChild.firstElementChild.checked == true){
                var count = parseInt(item.getAttribute('value'));
                this.totalCount += count;
                var price = item.parentElement.parentElement.previousElementSibling.firstElementChild.getAttribute('value');
                this.totalPrice += count * price;
            }
        }, this); // forEach 2번째 파라메터로 객체를 넘겨서 this 가 객체리터럴을 가리키도록 함. - thisArg
    },
    //화면 업데이트
    updateUI: function () {
        document.querySelector('#sum_p_num').textContent = '상품갯수: ' + this.totalCount.formatNumber() + '개';
        document.querySelector('#sum_p_price').textContent = '합계금액: ' + this.totalPrice.formatNumber() + '원';
    },
    //개별 수량 변경
    changePNum: function () {
        var item = document.querySelector('input[name=p_num]');
        var p_num = parseInt(item.getAttribute('value'));
        var newval = event.target.classList.contains('up') ? p_num+1 : event.target.classList.contains('down') ? p_num-1 : event.target.value;
        
        if (parseInt(newval) < 1 || parseInt(newval) > 99) { return false; }

        item.setAttribute('value', newval);
        item.value = newval;

        var price = item.parentElement.parentElement.previousElementSibling.firstElementChild.getAttribute('value');
        item.parentElement.parentElement.nextElementSibling.textContent = (newval * price).formatNumber()+"원";
        //AJAX 업데이트 전송

        //전송 처리 결과가 성공이면    
        this.reCalc();
        this.updateUI();
    },
    total: function(item) {
        var result = document.querySelector('#sum_p_price').value;
        document.querySelector('#total').textContent = '합계금액: ' + this.result.formatNumber() + '원';
    }
}


// 숫자 3자리 콤마찍기
Number.prototype.formatNumber = function(){
    if(this==0) return 0;
    let regex = /(^[+-]?\d+)(\d{3})/;
    let nstr = (this + '');
    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
    return nstr;
};

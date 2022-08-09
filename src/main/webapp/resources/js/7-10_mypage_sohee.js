/* ============================= */
/*      기본정보(7-10~7-14)      */   
/* ============================= */


/* ============================= */
/*      기간검색 유효성           */   
/* ============================= */

function dateCheck() {
    const dateFrom = document.querySelector("#dateFrom");
    const dateTo = document.querySelector("#dateTo");
    const date = new Date();
    const currentDate = date.getFullYear - date.getHours - date.getMinutes;

    console.log(currentDate);

    if (currentDate < dateTo.value) {
        alert("기간 설정이 잘못 되었습니다.")

        return false
    }

    if (dateFrom.value > dateTo.value) {
        Swal.fire({
            icon: 'warning',
            title: '기간 지정이 잘못 되었습니다.'
        })

        //틀리면 제출 막음
        return false
    }// if

}// dateCheck()

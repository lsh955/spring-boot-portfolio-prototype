/*!
 **
 * Copyright 2020. 이승환(Lee Seunghwan) All pictures cannot be copied without permission.
 *
 * name : 이승환(Lee Seunghwan)
 * description : index.html 문서에 대한 layout js
 **
 */


// TODO
// 1. 페이지가 완전히 상단으로 붙지않은 현상수정하기
// 2. 페이지 새로고침 스크립트 미완성
// 3. 스크롤 시 메뉴 활성화에 대한 수정이 필요
// 4. 종료되는 시각에 알림이 계속 울림. 한번만 울릴 수 있도록 개선이 필요.
// 5. 로그인 창 닫기버튼 마우스가 벋어나면 초기화가 안된다.
// 6. 로그인 창 input 빈 값 체크하는 스크립트 넣기.
// 7. 로그인 버튼 누르면 열고/닫는 스크립트 넣기.
// 8. 사이트운영 자동알림은 소켓통신으로 있어보이게 만들자.

$(document).ready(function () {

    const Site_Notice_Text = '사이트 알림',
        Site_Warning_Text = '사이트 경고',
        Darkmode_Help = $('.darkmode_help'),
        Functions_Li_01 = $('.dark_mode'),
        Functions_Li_02 = $('.question'),
        Functions_Li_03 = $('.login, .login_background, .message_login_btn');

    //********************************
    //**** 부가 이벤트 ****************
    //********************************

    // 서버운영 종료 자동알림
    setInterval("autotime()", 1000);

    // 리로드시 animated 효과
    $('.message_photo').addClass('animated rubberBand faster delay-1s');        // 메시지 박스 프로필 사진
    $('.popup_title .fa-bell').addClass('animated swing faster');               // 데스크탑 팝업 벨 아이콘
    $('.domain_title span').addClass('animated fadeIn faster');                 // 영문메뉴 큰 타이틀
    $('.popup_btn .fa-times').addClass('animated rotateIn faster');             // 모바일 팝업 닫기 아이콘

    // 브라우저의 온라인 여부 반환 (네트워크 연결상태 여부)
    if (navigator.onLine == false) {
        alert("현재 네트워크에 연결되어 있지 않습니다.");
        site_alert(Site_Warning_Text, '현재 네트워크에 연결되어 있지 않습니다.', 7000);
    }

    // Internet Explorer 브라우저 체크
    if (navigator.userAgent.toLowerCase().indexOf('.net') != -1) {
        console.log('사용하고 계신 Internet Explorer 브라우저는 지원하지 않습니다. Chrome, Firefox, Safari 브라우저를 이용해주시기 바랍니다.');
        site_alert(Site_Warning_Text, '사용하고 계신 Internet Explorer 브라우저는 지원하지 않습니다. Chrome, Firefox, Safari 브라우저를 이용해주시기 바랍니다.', 7000);
    }

    // 모바일_스크롤(밑으로 내렸을 시 버튼표시)
    $(this).scroll(function (event) {
        // 브라우저 전체 길이를 계산한다.
        let html = document.documentElement,
            body = document.body,
            height = Math.max(body.scrollHeight, body.offsetHeight, html.clientHeight, html.scrollHeight, html.offsetHeight),
            max_value = height - window.innerHeight;

        // 스크롤시 progress bar 가 움직인다.
        $('.process').attr('max', max_value)
            .val($(this).scrollTop());

        // 일정영역(1페이지)에 지나가면 상단버튼 표시.
        if ($(this).scrollTop() > 250) {
            $('.site_up').fadeIn('fast');
        } else {
            $('.site_up').fadeOut('fast');
        }

        // 스크롤 값 볼려고 찍어둔것임.(추후 삭제할것.)
        $('#01, #02').html($(this).scrollTop());

        // 페이지 없음알림
        if ($(this).scrollTop() < -10) {
            setTimeout(function () {
                $('.not_found .not_found_text').html('404 Not Found'); // 에러아님(시각적인 요소)
            }, 1500);
            return false;
        }
    });


    // 다크모드 도움말
    Functions_Li_01.on({
        'mouseenter': function () {
            // 연속적인 에니메이션 큐를 제거하기 위한 .stop()를 사용
            Darkmode_Help.stop().animate({width: 'toggle'}, 300);
        },
        'mouseleave': function () {
            // 연속적인 에니메이션 큐를 제거하기 위한 .stop()를 사용
            Darkmode_Help.stop().animate({width: 'toggle'}, 300);
        }
    });

    // 사이트 알림
    Functions_Li_01.on('click', function () {    // 다크모드,나이트 모드
        site_alert(Site_Notice_Text, '다크모드 기능은 준비중 입니다.', 3000);
        // site_alert(Site_Notice_Text, '라이트모드 기능은 준비중 입니다.', 3000);
        // site_alert(Site_Notice_Text, '오토모드 기능은 준비중 입니다.', 3000);
        return false;
    });
    Functions_Li_02.on('click', function () {    // 문의사항
        site_alert(Site_Notice_Text, '문의사항 기능은 준비중 입니다.', 3000);
        return false;
    });
    Functions_Li_03.on('click', function () {    // 로그인
        // site_alert(Site_Notice_Text, '로그인 기능은 준비중 입니다.', 3000);
        setTimeout(function () {
            $('.left_login').stop().fadeIn('fast');
            $('.right_login').stop().fadeIn('fast');
            $('.left_find').stop().fadeOut('fast');
            $('.right_find').stop().fadeOut('fast');
        }, 200);
        return false;
    });

    $('.menu_list .07, .menu_list .08').on('click', function () {    // 로그인
        site_alert(Site_Notice_Text, '준비중인 페이지 입니다.', 3000);
    });


    //********************************
    //**** 메시지 박스 이벤트 ********
    //********************************

    $('.small_photo').on('click', function () {
        if ($('.message_box').css('display') == 'none') {     // 메시지 박스가 있는지 없는지 검증한다.
            $('.message_box').stop().fadeIn('fast');        // none이면 보이게 하고
        } else {
            $('.message_box').stop().fadeOut('fast');       // black이면 숨기게 한다.
        }
        return false;   // 이벤트를 종료시켜야 다음 이벤트로 안넘어 간다.
    });


    //********************************
    //**** 로그인 이벤트 *************
    //********************************

    var para = document.location.href.split("?");
    console.log(para[1]);

    // TODO : 로그아웃 메시지는 이렇게 처리하면 안된다. 로그아웃된 상태에서 계속 새로고침하면 알림창이 계속뜬다. 생각해서 꼭 바꿀것.
    if (para[1] === "state=logout") {
        site_alert(Site_Notice_Text, '안전하게 로그아웃 되었습니다.', 5000);
    }

    // 로그인 되었을 시 알림
    //site_alert(Site_Notice_Text, '이승환님, 안녕하세요. 2019년00월00일 00시00분 로그인되었습니다.', 5000);

    $('.right_find').stop().fadeOut('fast');

    // 계정만들기
    $('.user_add p').on('click', function () {
        site_alert(Site_Notice_Text, '관리자만 로그인 되며, 계정생성은 당분간 지원하지 않습니다.', 5000);
        return false;
    });

    // 아이디/비밀번호 찾기
    // $('.user_find p').on('click', function () {
    //     site_alert(Site_Notice_Text, '아이디/비밀번호 찾기 기능은 준비중 입니다.', 3000);
    //     return false;
    // });

    // 아이디 input
    $('.input_id').on({
        'focus': function () {      // 포커스가 있으면 테두리 색상이 컬러로 변한다.
            $('.id_edge').css('border', '2px solid #b00020');
            $('.id_title').css('color', '#b00020');
            $('.pwd_edge').css('border', '2px solid #c2c2c2');
            $('.pwd_title').css('color', '#333');
        },
        'focusout': function () {   // 포커스가 벋어나면 input 색상이 흑백으로 변한다.
            $('.id_edge').css('border', '2px solid #c2c2c2');
            $('.id_title').css('color', '#333');
        }
    });

    // 비밀번호 input
    $('.input_password').on({
        'focus': function () {      // 포커스가 있으면 테두리 색상이 컬러로 변한다.
            $('.pwd_edge').css('border', '2px solid #b00020');
            $('.pwd_title').css('color', '#b00020');
            $('.id_edge').css('border', '2px solid #c2c2c2');
            $('.id_title').css('color', '#333');
        },
        'focusout': function () {   // 포커스가 벋어나면 input 색상이 흑백으로 변한다.
            $('.pwd_edge').css('border', '2px solid #c2c2c2');
            $('.pwd_title').css('color', '#333');
        }
    });

    // 로그인창 input값 체크
    $('#form_btn').on('click', function () {
        let form_result = login_input_check() == true ? true : false;
    });

    // 로그인창 닫기버튼 - 잠시기능을 주석한다.(삭제할 가능성 있다.)
    // $('.logpage .close .fa-times').on({
    //     'mouseenter': function () {     // 마우스오버 시 회전한다.
    //         document.querySelector('.logpage .close .fa-times').classList.add('animated', 'rotateIn', 'faster');
    //     },
    //     'mouseleave': function () {     // 영역이 벋어날 시 초기화된다.
    //         // 작동안됨 개선방안 찾기
    //         $('logpage .close > .fa-times').removeClass('animated rotateIn faster');
    //         $('ogpage .close > .fa-times').addClass('fas fa-times');
    //     }
    // });

    Functions_Li_03.on('click', function () {
        if ($('.logpage').css('display') == 'none') {
            $('.functions .login .fa-lock').stop().hide();
            $('.functions .login .fa-times').stop().fadeIn('fast');
            $('.login_background').stop().fadeIn('fast');
            $('.logpage').stop().fadeIn('fast');
            $('.login_shadow_off').attr('class', 'login_shadow_on');
            $('#form input[id^=user_]').val('');
            $('.login_submit').css('background-color', '#e6e6e6');
            $('.message_box').stop().fadeOut('fast');       // 로그인이 뜨면 message_box를 숨긴다.
            login_effect();
            document.querySelector('.functions .login .fa-times').classList.add('animated', 'rotateIn', 'faster');
        } else {
            $('.functions .login .fa-times').stop().hide();
            $('.functions .login .fa-lock').stop().fadeIn('fast');
            $('.login_background').stop().fadeOut('fast');
            $('.logpage').stop().fadeOut('fast');
            $('.login_shadow_on').attr('class', 'login_shadow_off');
            document.querySelector('.functions .login .fa-lock').classList.add('animated', 'fadeInRight', 'faster');
        }
    });

    // 아이디/비밀번호 입력값 실시간 감지(나중에 캡차 반환값 얻어서 추가 해야됨)
    $('#form').on({
        'propertychange change keyup paste input': function () {         // 이 라인은 검색해서 갔다 쓴거임.
            let id_currentVal = $('input[id=user_id]').val();           // 아이디
            let pwd_currentVal = $('input[id=user_password]').val();    // 비밀번호
            if ('' == id_currentVal || null == id_currentVal || '' == pwd_currentVal || null == pwd_currentVal) {
                $('.login_submit').css('background-color', '#e6e6e6');  // 모든항목에 input값이 없을시
            } else {
                $('.login_submit').css('background-color', '#ffb500');  // 모든항목에 input값이 있을시
            }
        },
        'keydown': function (e) {
            if (e.keyCode == 13) {
                $(this).clearQueue().submit();  // Enter 입력시 정보가 전송된다.
            }
        }
    });

    $('.user_find').on('click', function () {
        setTimeout(function () {
            $('.left_login').stop().fadeOut('fast');
            $('.right_login').stop().fadeOut('fast');
        }, 0);
        setTimeout(function () {
            $('.left_find').stop().fadeIn('fast');
            $('.right_find').stop().fadeIn('fast');
        }, 300);
        return false;   // 이벤트 종료를 안해주면 div창이 닫아진다.
    });

    $('.backward').on('click', function () {
        setTimeout(function () {
            $('.left_login').stop().fadeIn('fast');
            $('.right_login').stop().fadeIn('fast');

            $('.find_category ul li p').eq(0).addClass('on');
            $('.find_category ul li p').eq(1).removeClass('on');
            $('.id_find').show().fadeIn('fast');
            $('.pwd_find').show().fadeOut('fast');
        }, 300);
        setTimeout(function () {
            $('.left_find').stop().fadeOut('fast');
            $('.right_find').stop().fadeOut('fast');
        }, 0);
        return false;   // 이벤트 종료를 안해주면 div창이 닫아진다.
    });

    // 아이디/비밀번로 찾기
    $('.find_category ul li').on('click', function () {
        let li_value = $(this).index();
        $('.find_category ul li p').removeClass('on');
        $('.find_category ul li p').eq(li_value).addClass('on');
        if (li_value == 0) {
            $('.id_find').show();
            $('.pwd_find').hide();
        } else {
            $('.pwd_find').show();
            $('.id_find').hide();
        }
        return false;
    });

    // 아이디/비밀번로 찾기 왼쪽문구 이벤트(1회성)
    $('.user_find a p').on('click', function () {
        setTimeout(function () {
            $('.login_title_find').stop().animate({top: '230'}, 900);
        }, 500);
        setTimeout(function () {
            $('.login_text_find_ko').stop().fadeIn('slow');
        }, 800);
        setTimeout(function () {
            $('.login_text_find_en').stop().fadeIn('slow');
        }, 1000);
    });


    //********************************
    //**** 메뉴 이벤트 ****************
    //********************************

    // ...클릭 시 백그라운드 처리
    // .menu_button         : 데스크탑_메뉴_열기/닫기
    // .mobile_menu_button  : 모바일_메뉴_열기/닫기
    // .mobile_menu_close   : 모바일_메뉴닫기_버튼
    // .menu_background     : 메뉴_백그라운드_공통
    // .menu_list a         : 메뉴_Depth(뎁스)_공통
    $('.menu_button, .mobile_menu_button, .mobile_menu_close, .menu_background, .menu_list a').on('click', function () {
        menu_background();
    });

    // 왼쪽메뉴 Depth(뎁스) 클릭시 색상유지
    $('.menu_list p').on('click', function () {
        $(this).removeClass()
            .addClass(' on');
    });

    // 오른쪽메뉴 Depth(뎁스) 색상유지
    $('.right_side a').on('click', function () {
        $(this).removeClass()
            .addClass('on');
    });

    // 메뉴클릭시 부드러운 스크롤
    // .menu_title a       :  헤더_클릭시_상단바로가기_모바일
    // .site_up a          :  상단_바로가기_모바일
    // .right_side a       :  오른쪽메뉴_데스크탑
    // .menu_list a        :  왼쪽메뉴_데스크탑
    $('.menu_title a, .site_up a, .right_side li, .menu_list a').on('click', function () {
        let class_value = $(this).attr('class');
        $('html').stop().animate({
            scrollTop: $('#' + class_value).offset().top - 43
        }, 700);
        return false;
    });

    // 스크롤 위치에 맞는 메뉴활성화
    $(window).on('scroll', function () {
        $('.contents_guide > div').each(function () {
            if ($(window).scrollTop() >= $(this).offset().top) {
                let id_value = $(this).attr('id');
                $('.right_side a').removeClass('on');
                $('.menu_list p').removeClass('on');
                $('.right_side a').eq(id_value).addClass('on');
                $('.menu_list p').eq(id_value).addClass('on');
            }
        });
    });


    //********************************
    //**** 팝업 이벤트 ****************
    //********************************

    // ...클릭 시 백그라운드 처리
    // .popup_title         : 팝업타이틀 바
    // .popup_btn           : 데스크탑_팝업버튼
    // .mobile_popup_btn    : 모바일_팝업버튼
    // .popup_background    : 팝업백그라운드_공통
    $('.popup_title, .popup_btn, .mobile_popup_btn, .popup_background').on('click', function () {
        // 팝업_백그라운드_처리
        if ($('.popup_shadow_off').attr('class') == 'popup_shadow_off') {
            //layer_fixing();
            $('.popup_btn .fa-sort-down').stop().hide();
            $('.popup_btn .fa-times').stop().fadeIn('fast');
            $('.mobile_popup_btn .fa-bell').stop().hide();
            $('.mobile_popup_btn .fa-times-circle').stop().fadeIn('fast');
            $('.popup_list').stop().animate({height: 'toggle'}, 400);
            $('.popup_background').stop().fadeIn('fast');
            $('.popup_shadow_off').attr('class', 'popup_shadow_on');
            document.querySelector('.popup_btn .fa-sort-down').classList.add('animated', 'slideInDown', 'faster');
        } else {
            //layer_release();
            $('.popup_btn .fa-times').stop().hide();
            $('.popup_btn .fa-sort-down').stop().fadeIn('fast');
            $('.mobile_popup_btn .fa-times-circle').stop().hide();
            $('.mobile_popup_btn .fa-bell').stop().fadeIn('fast');
            $('.popup_list').stop().animate({height: 'toggle'}, 400);
            $('.popup_background').stop().fadeOut('fast');
            $('.popup_shadow_on').attr('class', 'popup_shadow_off');
        }
    });
});


//************** 중복된 구문은 함수(function) 처리한다. **************

// 디바이스_해상도_처리
function device_display() {
    const menu_contents = $('.menu_contents');
    if (matchMedia('screen and (min-width: 991px)').matches) {
        // 991px 이상에서 사용할 데스크탑 Script
        menu_contents.stop().animate({width: 'toggle'}, 300);
    } else {
        // 991px 미만에서 사용할 모바일 Script
        menu_contents.stop().animate({height: 'toggle'}, 300);
        $('.window_bax').css('display', 'none');    // 모바일에서는 데스크탑 전용 알림창을 보이지 않을 것이다.
    }
}


// 메뉴_백그라운드_처리
function menu_background() {
    if ($('.menu_shadow_off').attr('class') == 'menu_shadow_off') {
        device_display();
        //layer_fixing();
        $('.menu_button .fa-bars').stop().hide();
        $('.menu_button .fa-times').stop().fadeIn('fast');
        $('.menu_background').stop().fadeIn('fast');
        $('.menu_shadow_off').attr('class', 'menu_shadow_on');
        $('.menu_list ul').attr('class', 'on');
        document.querySelector('.menu_button .fa-times').classList.add('animated', 'rotateIn', 'faster');
    } else {
        device_display();
        //layer_release();
        $('.menu_button .fa-times').stop().hide();
        $('.menu_button .fa-bars').stop().fadeIn('fast');
        $('.menu_background').stop().fadeOut('fast');
        $('.menu_shadow_on').attr('class', 'menu_shadow_off');
        $('.menu_list ul').attr('class', 'off');
        document.querySelector('.menu_button .fa-bars').classList.add('animated', 'fadeInRight', 'faster');
    }
}

// 팝업 레이어 고정/해제
let scrollHeight = 0;

function layer_fixing() {   // 고정
    scrollHeight = $('body').scrollTop();   // [let사용하지 않았으므로 전역스코프로 정의됨]열렸을떄 scrollTop 체크
    $('body').addClass('layer-open');       // overflow:hidden 추가
    $('.wrap').css('position', 'fixed')     // 최상위 div 고정
        .css('top', -scrollHeight);  // 최상위 div에 현재 스크롤된값 = 보이는화면만큼 top값 추가
}

function layer_release() { // 해제
    $('body').removeClass('layer-open');    // overflow-hidden 해제(스크롤 해제)
    $('.wrap').css('top', 0)                // 최상위 div 고정해제
        .css('position', 'relative')  // top값 해제
    $('body').scrollTop(scrollHeight);      // [popupOpen()일때의 의도적 전역변수 scrollHeight값]현재 스크롤된값=보이는화면
}

// 서버운영 자동알림
function autotime() {
    const Site_Notice_Text = '사이트 알림';

    var date = new Date(),
        currentTime = date.getHours() + '' + date.getMinutes();

    switch (currentTime) {
        case '2050':
            site_alert(Site_Notice_Text, '잠시 후(21시00분) 서버 운영이 종료됩니다.', 7000);
            break;
        case '2059':
            site_alert(Site_Notice_Text, '1분 후 서버 운영이 종료됩니다.', 10000);
            break;
    }
}

// 사이트 알림
function site_alert(title, text, time) {
    $('.title_text').text(title);       //  제목
    $('.bax_text').text(text);          //  내용
    $('.window_bax').css('display', 'block')
        .removeClass('animated fadeOutUp faster')
        .addClass('animated fadeInDown faster');
    $('.fa-exclamation-triangle').removeClass('animated jello delay-1s')
        .addClass('animated jello delay-1s');
    setTimeout(function () {
        $('.window_bax').removeClass('animated fadeInDown faster')
            .addClass('animated fadeOutUp faster');
    }, time); // 시간
}

// form안의 모든 input 조회
function login_input_check() {
    const Site_Notice_Text = '사이트 알림';
    // 모든 input 값을 대입한다.
    const Input_Type = $('#form :input');
    for (let i = 0; i < Input_Type.length; i++) {
        if ('' == $(Input_Type[i]).val() || null == $(Input_Type[i]).val()) {
            let input_id_value = $(Input_Type[i]).attr('id');   // 값이없는 input id값을 얻어온다.
            $('#' + input_id_value).focus();                    // 값이없는 input에 포커스가 이동한다.
            if (input_id_value == 'user_id') {
                site_alert(Site_Notice_Text, '아이디 입력은 필수입력 입니다.', 3000);
                return false;   // return false;를 안해주면 마지막 값 먼저 포커스 이동 현상이 나타난다.
            }
            if (input_id_value == 'user_password') {
                site_alert(Site_Notice_Text, '비밀번호 입력은 필수입력 입니다.', 3000);
                return false;   // return false;를 안해주면 마지막 값 먼저 포커스 이동 현상이 나타난다.
            }
        }
    }
    // 정보전송
    // 연속적인 큐를 제거하기 위해 .clearQueue()를 사용.
    $('#form').clearQueue().submit();
}

// 로그인 효과
function login_effect() {
    // 왼쪽 문구이벤트는 1회성
    setTimeout(function () {
        $('.login_title_login').stop().animate({top: '230'}, 900);
    }, 500);
    setTimeout(function () {
        $('.login_text_login_ko').stop().fadeIn('slow');
    }, 800);
    setTimeout(function () {
        $('.login_text_login_en').stop().fadeIn('slow');
    }, 1000);
    $('#user_id').focus();
    $('.id_edge').css('border', '2px solid #b00020');
    $('.id_title').css('color', '#b00020');
}


// 테스트중....
$(function () {
    $.ajaxSetup({
        beforeSend: function (xhr) {
            xhr.setRequestHeader("AJAX", "true");
        },
        error: function (e, xhr, settings, exception) {
            if (e.status === 400) {
                alert("400");
            } else if (e.status === 401) {
                window.location.href = "/";
                alert("401");
            } else if (e.status === 403) {
                $.growlUI('warning', '이 기능을 사용할 권한이 없습니다.');
                alert("403");
            }
            if (callback_ajaxError) {
                callback_ajaxError(e, xhr, settings, exception);
            }
        }
    });
});
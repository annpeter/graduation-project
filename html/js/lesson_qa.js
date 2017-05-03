$(document).ready(function(){

	// 查看问题答案
	$('.lesson_main').on('click', '.q_check', function() {
		var answer_index=$(this).parent().parent().index();
		$(".answer").eq(answer_index).slideToggle().parents('.quesitem').siblings().find('.answer').slideUp();
	});


	var detail_check=false;
	$(".q_detail").click(function(){
		if(!detail_check){
			$(this).siblings(".q_description").css("height","auto");
		}
		else{
			$(this).siblings(".q_description").css("height","30px");
		}
		detail_check=!detail_check;
	});



	// 提交问题
	var $submit = $(".lesson_qa_form .lesson_qa_button_div [type='submit']");
	$submit.on( 'click', function() {
		// 提交问题点击
		var content = $('#lessson_textarea').val();
		var id = localStorage.getItem('id');

		$.ajax({
            url: '/api/question/add.htm',
            data: {
            	content : content,
            	courseId : id
            },
            dataType: "json",
            type: 'post',
            success: function( res ) {
            	var str = res.result_msg || "请稍后重试";
            	$('#lessson_textarea').val("")
            	questionLoad();
            }
        })

		return false;
	});
	


	// 底部问题渲染
	function questionLoad() {
		var id = localStorage.getItem('id');

		$.get("/api/question/list.htm",{courseId:id},function( res ) {

			var dataArr = res.data || [];

			// 遍历
			$('.lesson_qa_list > ul').html("");
			$.each( dataArr, function( index, item ) {

				if ( !item.answer ) {
					item.answer = "尚未解答";
				}
				
				var str = `<li class='quesitem'  data-id='${item.id}'>
							<div class="question">
								<div class="q_label"><p>Q:</p></div>
								<div class="q_description">
									<p>${item.content}</p>
								</div>
								<div class="q_check"><p>查看解答</p></div>
								<div class="clear"></div>
							</div>
							<div class="answer">
								<div class="a_label"><p>A:</p></div>
								<div class="a_description">
									<p>${item.answer}</p>
								</div>
								<div class="clear"></div>
							</div>
						</li>`;
				$('.lesson_qa_list > ul').prepend( str );
			})


		}, "json");
	}

	questionLoad();


	// 回答问题
	$('.lesson_qa_list').on('click', '.a_explain_button > [type=submit]', function() {
		var content = $(this).parent().siblings('textarea').val();
		var id = $(this).parents('.quesitem').attr('data-id');
		console.log( id );

		var data = {
			answer: content, 
			questionId: id 
		}

		$.post( '/api/question/answer.htm', data , function( res ) {

			console.log( res );

		}, "json");

		return false;
	})



});
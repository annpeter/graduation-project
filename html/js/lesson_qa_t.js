$(document).ready(function(){

	$('.lesson_main').on('click', '.q_check', function() {
		var answer_index=$(this).parent().parent().index();
		$(".answer").eq(answer_index).slideToggle().parents('.quesitem').siblings().find('.answer').slideUp();
	});


	var detail_check=false;

	$('.lesson_main').on('click', '.q_detail', function() {
		if(!detail_check){
			$(this).siblings(".q_description").css("height","auto");
		}
		else{
			$(this).siblings(".q_description").css("height","30px");
		}
		detail_check=!detail_check;
	})

	// 回答问题渲染

	function questionAnsLoad() {
		var id = localStorage.getItem('id');

		$.get("/api/question/list.htm",{courseId:id},function( res ) {

			var dataArr = res.data || [];

			// 遍历
			$('.lesson_qa_t_list > ul').html("");

			$.each( dataArr, function( index, item ) {
				if(item.answer!=null){
					return;
				}

				var str = `<li class='quesitem'  data-id='${item.id}'>
							<div class="question">
								<div class="q_label"><p>Q:</p></div>
								<div class="q_description">
									<p>${item.content}</p>
								</div>
								<div class="q_check"><p>解答</p></div>
								<div class="q_detail"><p>查看问题详情</p></div>
								<div class="clear"></div>
							</div>
							<div class="answer">
								<div class="a_label"><p>A:</p></div>
								<div class="a_explain">
									<form>
										<textarea></textarea>
										<div class="a_explain_button">
											<button type="submit">提交</button><button type="reset">重置</button>
										</div>
									</form>
								</div>
								<div class="clear"></div>
							</div>
						</li>`;


				$('.lesson_qa_t_list > ul').prepend( str );
			})

		}, "json");
	}

	questionAnsLoad();


	// 回答问题
	$('.lesson_qa_t_list').on('click', '.a_explain_button > [type=submit]', function() {
		var content = $(this).parent().siblings('textarea').val();
		var id = $(this).parents('.quesitem').attr('data-id');
		console.log( id );

		var data = {
			answer: content, 
			questionId: id 
		}

		$.post( '/api/question/answer.htm', data , function( res ) {

			questionAnsLoad();

		}, "json");

		return false;
	})



});
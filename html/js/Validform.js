/*
    ͨ�ñ���֤����
    Validform version 5.3.2
	By sean during April 7, 2010 - March 26, 2013
	For more information, please visit http://validform.rjboy.cn
	Validform is available under the terms of the MIT license.
	
	Demo:
	$(".demoform").Validform({//$(".demoform")ָ������һ����Ҫ��֤,���������form����;
		btnSubmit:"#btn_sub", //#btn_sub�Ǹñ���Ҫ�󶨵���ύ���¼��İ�ť;���form�ں���submit��ť�ò�����ʡ��;
		btnReset:".btn_reset",//��ѡ�� .btn_reset�Ǹñ���Ҫ�󶨵�����ñ��¼��İ�ť;
		tiptype:1, //��ѡ�� 1=>pop box,2=>side tip(parent.next.find; with default pop),3=>side tip(siblings; with default pop),4=>side tip(siblings; none pop)��Ĭ��Ϊ1��Ҳ���Դ���һ��function�������Զ�����ʾ��Ϣ����ʾ��ʽ������ʵ������Ҫ���κ�Ч��������μ�demoҳ��;
		ignoreHidden:false,//��ѡ�� true | false Ĭ��Ϊfalse����Ϊtrueʱ��:hidden�ı�Ԫ�ؽ�������֤;
		dragonfly:false,//��ѡ�� true | false Ĭ��false����Ϊtrueʱ��ֵΪ��ʱ������֤��
		tipSweep:true,//��ѡ�� true | false Ĭ��Ϊfalse��ֻ�ڱ��ύʱ������⣬blur�¼������ᴥ����⣨ʵʱ��֤���ں�̨���У�������ʾ�������;
		label:".label",//��ѡ�� ѡ�������û�а�nullmsgʱ����Ҫ��ʾ����ʾ���֣�Ĭ�ϲ���".Validform_label"�µ�����;
		showAllError:false,//��ѡ�� true | false��true���ύ��ʱ���д�����ʾ��Ϣ������ʾ��false��һ������֤��ͨ���ľ�ֹͣ�������Ԫ�أ�ֻ��ʾ��Ԫ�صĴ�����Ϣ;
		postonce:true, //��ѡ�� ���Ƿ�ֻ���ύһ�Σ�true������������Ĭ�Ϲر�;
		ajaxPost:true, //ʹ��ajax��ʽ�ύ�����ݣ�Ĭ��false���ύ��ַ����actionָ����ַ;
		datatype:{//�����Զ���datatype���ͣ�����������Ҳ�����Ǻ����������ڻᴫ��һ��������;
			"*6-20": /^[^\s]{6,20}$/,
			"z2-4" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,4}$/,
			"username":function(gets,obj,curform,regxp){
				//����gets�ǻ�ȡ���ı�Ԫ��ֵ��objΪ��ǰ��Ԫ�أ�curformΪ��ǰ��֤�ı���regxpΪ���õ�һЩ������ʽ������;
				var reg1=/^[\w\.]{4,16}$/,
					reg2=/^[\u4E00-\u9FA5\uf900-\ufa2d]{2,8}$/;
				
				if(reg1.test(gets)){return true;}
				if(reg2.test(gets)){return true;}
				return false;
				
				//ע��return���Է���true �� false �� �ַ������֣�true��ʾ��֤ͨ���������ַ�����ʾ��֤ʧ�ܣ��ַ�����Ϊ������ʾ��ʾ������false����errmsg��Ĭ�ϵĴ�����ʾ;
			},
			"phone":function(){
				// 5.0 �汾֮��Ҫʵ�ֶ�ѡһ����֤Ч����datatype ������ �� ��Ҫ�� "option_" ��ͷ;	
			}
		},
		usePlugin:{
			swfupload:{},
			datepicker:{},
			passwordstrength:{},
			jqtransform:{
				selector:"select,input"
			}
		},
		beforeCheck:function(curform){
			//�ڱ��ύִ����֤֮ǰִ�еĺ�����curform�����ǵ�ǰ������
			//������ȷreturn false�Ļ����������ִ����֤����;	
		},
		beforeSubmit:function(curform){
			//����֤�ɹ��󣬱��ύǰִ�еĺ�����curform�����ǵ�ǰ������
			//������ȷreturn false�Ļ����������ύ;	
		},
		callback:function(data){
			//��������data��json��ʽ��{"info":"demo info","status":"y"}
			//info: �����ʾ��Ϣ;
			//status: �����ύ���ݵ�״̬,�Ƿ��ύ�ɹ����������"y"��ʾ�ύ�ɹ���"n"��ʾ�ύʧ�ܣ���ajax_post.php�ļ������������Զ��ַ�����Ҫ����callback��������ݸ�ִֵ����Ӧ�Ļص�����;
			//��Ҳ������ajax_post.php�ļ����ظ�����Ϣ�������ȡ��������Ӧ������
			//ajax��������˴���ʱҲ��ִ�лص�����ʱ��data��{ status:**, statusText:**, readyState:**, responseText:** }��
			
			//����ִ�лص�����;
			//ע�⣺�������ajax��ʽ�ύ��������callback����ʱdata�����ǵ�ǰ�����󣬻ص��������ڱ���֤ȫ��ͨ����ִ�У�Ȼ���ж��Ƿ��ύ�������callback����ȷreturn false����������ύ�����return true��û��return������ύ����
		}
	});
	
	Validform����ķ��������ԣ�
	tipmsg���Զ�����ʾ��Ϣ��ͨ���޸�Validform������������ֵ����ͬһ��ҳ��Ĳ�ͬ��ʹ�ò�ͬ����ʾ���֣�
	dataType����ȡ���õ�һЩ����
	eq(n)����ȡValidform����ĵ�n��Ԫ��;
	ajaxPost(flag,sync,url)����ajax��ʽ�ύ����flagΪtrueʱ��������ֱ֤���ύ��syncΪtrueʱ����ͬ���ķ�ʽ����ajax�ύ��������url��ַʱ�������ύ�������ַ��
	abort()����ֹajax���ύ��
	submitForm(flag,url)���Բ��������õķ�ʽ�ύ����flagΪtrueʱ��������ֱ֤���ύ��������url��ַʱ�������ύ�������ַ��
	resetForm()�����ñ���
	resetStatus()�����ñ����ύ״̬��������postonce�����Ļ������ɹ��ύ��״̬������Ϊ"posted"�������ύ״̬�����ñ����������ύ��
	getStatus()����ȡ�����ύ״̬��normal��δ�ύ��posting�������ύ��posted���ѳɹ��ύ����
	setStatus(status)�����ñ����ύ״̬����������normal��posting��posted����״̬��������������״̬Ϊposting�����״̬��������֤���������ύ��
	ignore(selector)�����Զ���ѡ��������֤��
	unignore(selector)����ignore������������֤�Ķ������»�ȡ��֤Ч����
	addRule(rule)������ͨ��Validform������������������Ԫ�ذ���֤����
	check(bool,selector):��ָ�����������֤(Ĭ����֤��ǰ������)��ͨ������true�����򷵻�false����ʵʱ��֤�Ķ��󣬸�ʽ����Ҫ��ʱ����true���������ajax�ķ��ؽ������boolΪtrueʱ��ֻ��֤����ʾ��ʾ��Ϣ��
	config(setup):����ͨ������������޸ĳ�ʼ��������ָ�������ύ��ַ������ajax��ʵʱ��֤��ajax�����ò�����
*/

(function($,win,undef){
	var errorobj=null,//ָʾ��ǰ��֤ʧ�ܵı�Ԫ��;
		msgobj=null,//pop box object 
		msghidden=true;//msgbox hidden?

	var tipmsg={//Ĭ����ʾ����;
		tit:"��ʾ��Ϣ",
		w:{
			"*":"����Ϊ�գ�",
			"*6-16":"����д6��16λ�����ַ���",
			"n":"����д���֣�",
			"n6-16":"����д6��16λ���֣�",
			"s":"�������������ַ���",
			"s6-18":"����д6��18λ�ַ���",
			"p":"����д�������룡",
			"m":"����д�ֻ����룡",
			"e":"�����ַ��ʽ���ԣ�",
			"url":"����д��ַ��"
		},
		def:"����д��ȷ��Ϣ��",
		undef:"datatypeδ���壡",
		reck:"������������ݲ�һ�£�",
		r:"ͨ����Ϣ��֤��",
		c:"���ڼ����Ϣ��",
		s:"��{��д|ѡ��}{0|��Ϣ}��",
		v:"������Ϣû�о�����֤�����Ժ�",
		p:"�����ύ���ݡ�"
	}
	$.Tipmsg=tipmsg;
	
	var Validform=function(forms,settings,inited){
		var settings=$.extend({},Validform.defaults,settings);
		settings.datatype && $.extend(Validform.util.dataType,settings.datatype);
		
		var brothers=this;
		brothers.tipmsg={w:{}};
		brothers.forms=forms;
		brothers.objects=[];
		
		//�����Ӷ���ʱ���ٰ��¼�;
		if(inited===true){
			return false;
		}
		
		forms.each(function(){
			//�Ѿ����¼�ʱ�����������¼��ظ���;
			if(this.validform_inited=="inited"){return true;}
			this.validform_inited="inited";
			
			var curform=this;
			curform.settings=$.extend({},settings);
			
			var $this=$(curform);
			
			//��ֹ����ť˫���ύ����;
			curform.validform_status="normal"; //normal | posting | posted;
			
			//��ÿ��Validform�������Զ���tipmsg;	
			$this.data("tipmsg",brothers.tipmsg);

			//bind the blur event;
			$this.delegate("[datatype]","blur",function(){
				//�ж��Ƿ������ύ������ʱ��������֤����
				var subpost=arguments[1];
				Validform.util.check.call(this,$this,subpost);
			});
			
			$this.delegate(":text","keypress",function(event){
				if(event.keyCode==13 && $this.find(":submit").length==0){
					$this.submit();
				}
			});
			
			//�����Ԫ�أ�Ĭ��������ʧЧ��;
			//��Ԫ��ֵ�Ƚ�ʱ����Ϣ��ʾ��ǿ;
			//radio��checkbox��ʾ��Ϣ��ǿ;
			//��������ʼ��;
			Validform.util.enhance.call($this,curform.settings.tiptype,curform.settings.usePlugin,curform.settings.tipSweep);
			
			curform.settings.btnSubmit && $this.find(curform.settings.btnSubmit).bind("click",function(){
				$this.trigger("submit");
				return false;
			});
						
			$this.submit(function(){
				var subflag=Validform.util.submitForm.call($this,curform.settings);
				subflag === undef && (subflag=true);
				return subflag;
			});
			
			$this.find("[type='reset']").add($this.find(curform.settings.btnReset)).bind("click",function(){
				Validform.util.resetForm.call($this);
			});
			
		});
		
		//Ԥ����pop box;
		if( settings.tiptype==1 || (settings.tiptype==2 || settings.tiptype==3) && settings.ajaxPost ){		
			creatMsgbox();
		}
	}
	
	Validform.defaults={
		tiptype:1,
		tipSweep:false,
		showAllError:false,
		postonce:false,
		ajaxPost:false
	}
	
	Validform.util={
		dataType:{
			"*":/[\w\W]+/,
			"*6-16":/^[\w\W]{6,16}$/,
			"n":/^\d+$/,
			"n6-16":/^\d{6,16}$/,
			"s":/^[\u4E00-\u9FA5\uf900-\ufa2d\w\.\s]+$/,
			"s6-18":/^[\u4E00-\u9FA5\uf900-\ufa2d\w\.\s]{6,18}$/,
			"p":/^[0-9]{6}$/,
			"m":/^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$/,
			"e":/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
			"url":/^(\w+:\/\/)?\w+(\.\w+)+.*$/
		},
		
		toString:Object.prototype.toString,
		
		isEmpty:function(val){
			return val==="" || val===$.trim(this.attr("tip"));
		},
		
		getValue:function(obj){
			var inputval,
				curform=this;
				
			if(obj.is(":radio")){
				inputval=curform.find(":radio[name='"+obj.attr("name")+"']:checked").val();
				inputval= inputval===undef ? "" : inputval;
			}else if(obj.is(":checkbox")){
				inputval="";
				curform.find(":checkbox[name='"+obj.attr("name")+"']:checked").each(function(){ 
					inputval +=$(this).val()+','; 
				})
				inputval= inputval===undef ? "" : inputval;
			}else{
				inputval=obj.val();
			}
			inputval=$.trim(inputval);
			
			return Validform.util.isEmpty.call(obj,inputval) ? "" : inputval;
		},
		
		enhance:function(tiptype,usePlugin,tipSweep,addRule){
			var curform=this;
			
			//ҳ���ϲ�������ʾ��Ϣ�ı�ǩʱ���Զ�����;
			curform.find("[datatype]").each(function(){
				if(tiptype==2){
					if($(this).parent().next().find(".Validform_checktip").length==0){
						$(this).parent().next().append("<span class='Validform_checktip' />");
						$(this).siblings(".Validform_checktip").remove();
					}
				}else if(tiptype==3 || tiptype==4){
					if($(this).siblings(".Validform_checktip").length==0){
						$(this).parent().append("<span class='Validform_checktip' />");
						$(this).parent().next().find(".Validform_checktip").remove();
					}
				}
			})
			
			//��Ԫ��ֵ�Ƚ�ʱ����Ϣ��ʾ��ǿ;
			curform.find("input[recheck]").each(function(){
				//�Ѿ����¼�ʱ����;
				if(this.validform_inited=="inited"){return true;}
				this.validform_inited="inited";
				
				var _this=$(this);
				var recheckinput=curform.find("input[name='"+$(this).attr("recheck")+"']");
				recheckinput.bind("keyup",function(){
					if(recheckinput.val()==_this.val() && recheckinput.val() != ""){
						if(recheckinput.attr("tip")){
							if(recheckinput.attr("tip") == recheckinput.val()){return false;}
						}
						_this.trigger("blur");
					}
				}).bind("blur",function(){
					if(recheckinput.val()!=_this.val() && _this.val()!=""){
						if(_this.attr("tip")){
							if(_this.attr("tip") == _this.val()){return false;}	
						}
						_this.trigger("blur");
					}
				});
			});
			
			//hasDefaultText;
			curform.find("[tip]").each(function(){//tip�Ǳ�Ԫ�ص�Ĭ����ʾ��Ϣ,���ǵ�����Ч��;
				//�Ѿ����¼�ʱ����;
				if(this.validform_inited=="inited"){return true;}
				this.validform_inited="inited";
				
				var defaultvalue=$(this).attr("tip");
				var altercss=$(this).attr("altercss");
				$(this).focus(function(){
					if($(this).val()==defaultvalue){
						$(this).val('');
						if(altercss){$(this).removeClass(altercss);}
					}
				}).blur(function(){
					if($.trim($(this).val())===''){
						$(this).val(defaultvalue);
						if(altercss){$(this).addClass(altercss);}
					}
				});
			});
			
			//enhance info feedback for checkbox & radio;
			curform.find(":checkbox[datatype],:radio[datatype]").each(function(){
				//�Ѿ����¼�ʱ����;
				if(this.validform_inited=="inited"){return true;}
				this.validform_inited="inited";
				
				var _this=$(this);
				var name=_this.attr("name");
				curform.find("[name='"+name+"']").filter(":checkbox,:radio").bind("click",function(){
					//�������¼���ʱ��ȡֵ�ͺ�����;
					setTimeout(function(){
						_this.trigger("blur");
					},0);
				});
				
			});
			
			//select multiple;
			curform.find("select[datatype][multiple]").bind("click",function(){
				var _this=$(this);
				setTimeout(function(){
					_this.trigger("blur");
				},0);
			});
			
			//plugins here to start;
			Validform.util.usePlugin.call(curform,usePlugin,tiptype,tipSweep,addRule);
		},
		
		usePlugin:function(plugin,tiptype,tipSweep,addRule){
			/*
				plugin:settings.usePlugin;
				tiptype:settings.tiptype;
				tipSweep:settings.tipSweep;
				addRule:�Ƿ���addRuleʱ����;
			*/

			var curform=this,
				plugin=plugin || {};
			//swfupload;
			if(curform.find("input[plugin='swfupload']").length && typeof(swfuploadhandler) != "undefined"){
				
				var custom={
						custom_settings:{
							form:curform,
							showmsg:function(msg,type,obj){
								Validform.util.showmsg.call(curform,msg,tiptype,{obj:curform.find("input[plugin='swfupload']"),type:type,sweep:tipSweep});	
							}	
						}	
					};

				custom=$.extend(true,{},plugin.swfupload,custom);
				
				curform.find("input[plugin='swfupload']").each(function(n){
					if(this.validform_inited=="inited"){return true;}
					this.validform_inited="inited";
					
					$(this).val("");
					swfuploadhandler.init(custom,n);
				});
				
			}
			
			//datepicker;
			if(curform.find("input[plugin='datepicker']").length && $.fn.datePicker){
				plugin.datepicker=plugin.datepicker || {};
				
				if(plugin.datepicker.format){
					Date.format=plugin.datepicker.format; 
					delete plugin.datepicker.format;
				}
				if(plugin.datepicker.firstDayOfWeek){
					Date.firstDayOfWeek=plugin.datepicker.firstDayOfWeek; 
					delete plugin.datepicker.firstDayOfWeek;
				}

				curform.find("input[plugin='datepicker']").each(function(n){
					if(this.validform_inited=="inited"){return true;}
					this.validform_inited="inited";
					
					plugin.datepicker.callback && $(this).bind("dateSelected",function(){
						var d=new Date( $.event._dpCache[this._dpId].getSelected()[0] ).asString(Date.format);
						plugin.datepicker.callback(d,this);
					});
					$(this).datePicker(plugin.datepicker);
				});
			}
			
			//passwordstrength;
			if(curform.find("input[plugin*='passwordStrength']").length && $.fn.passwordStrength){
				plugin.passwordstrength=plugin.passwordstrength || {};
				plugin.passwordstrength.showmsg=function(obj,msg,type){
					Validform.util.showmsg.call(curform,msg,tiptype,{obj:obj,type:type,sweep:tipSweep});
				};
				
				curform.find("input[plugin='passwordStrength']").each(function(n){
					if(this.validform_inited=="inited"){return true;}
					this.validform_inited="inited";
					
					$(this).passwordStrength(plugin.passwordstrength);
				});
			}
			
			//jqtransform;
			if(addRule!="addRule" && plugin.jqtransform && $.fn.jqTransSelect){
				if(curform[0].jqTransSelected=="true"){return;};
				curform[0].jqTransSelected="true";
				
				var jqTransformHideSelect = function(oTarget){
					var ulVisible = $('.jqTransformSelectWrapper ul:visible');
					ulVisible.each(function(){
						var oSelect = $(this).parents(".jqTransformSelectWrapper:first").find("select").get(0);
						//do not hide if click on the label object associated to the select
						if( !(oTarget && oSelect.oLabel && oSelect.oLabel.get(0) == oTarget.get(0)) ){$(this).hide();}
					});
				};
				
				/* Check for an external click */
				var jqTransformCheckExternalClick = function(event) {
					if ($(event.target).parents('.jqTransformSelectWrapper').length === 0) { jqTransformHideSelect($(event.target)); }
				};
				
				var jqTransformAddDocumentListener = function (){
					$(document).mousedown(jqTransformCheckExternalClick);
				};
				
				if(plugin.jqtransform.selector){
					curform.find(plugin.jqtransform.selector).filter('input:submit, input:reset, input[type="button"]').jqTransInputButton();
					curform.find(plugin.jqtransform.selector).filter('input:text, input:password').jqTransInputText();			
					curform.find(plugin.jqtransform.selector).filter('input:checkbox').jqTransCheckBox();
					curform.find(plugin.jqtransform.selector).filter('input:radio').jqTransRadio();
					curform.find(plugin.jqtransform.selector).filter('textarea').jqTransTextarea();
					if(curform.find(plugin.jqtransform.selector).filter("select").length > 0 ){
						 curform.find(plugin.jqtransform.selector).filter("select").jqTransSelect();
						 jqTransformAddDocumentListener();
					}
					
				}else{
					curform.jqTransform();
				}
				
				curform.find(".jqTransformSelectWrapper").find("li a").click(function(){
					$(this).parents(".jqTransformSelectWrapper").find("select").trigger("blur");	
				});
			}

		},
		
		getNullmsg:function(curform){
			var obj=this;
			var reg=/[\u4E00-\u9FA5\uf900-\ufa2da-zA-Z\s]+/g;
			var nullmsg;
			
			var label=curform[0].settings.label || ".Validform_label";
			label=obj.siblings(label).eq(0).text() || obj.siblings().find(label).eq(0).text() || obj.parent().siblings(label).eq(0).text() || obj.parent().siblings().find(label).eq(0).text();
			label=label.replace(/\s(?![a-zA-Z])/g,"").match(reg);
			label=label? label.join("") : [""];

			reg=/\{(.+)\|(.+)\}/;
			nullmsg=curform.data("tipmsg").s || tipmsg.s;
			
			if(label != ""){
				nullmsg=nullmsg.replace(/\{0\|(.+)\}/,label);
				if(obj.attr("recheck")){
					nullmsg=nullmsg.replace(/\{(.+)\}/,"");
					obj.attr("nullmsg",nullmsg);
					return nullmsg;
				}
			}else{
				nullmsg=obj.is(":checkbox,:radio,select") ? nullmsg.replace(/\{0\|(.+)\}/,"") : nullmsg.replace(/\{0\|(.+)\}/,"$1");
			}
			nullmsg=obj.is(":checkbox,:radio,select") ? nullmsg.replace(reg,"$2") : nullmsg.replace(reg,"$1");
			
			obj.attr("nullmsg",nullmsg);
			return nullmsg;
		},
		
		getErrormsg:function(curform,datatype,recheck){
			var regxp=/^(.+?)((\d+)-(\d+))?$/,
				regxp2=/^(.+?)(\d+)-(\d+)$/,
				regxp3=/(.*?)\d+(.+?)\d+(.*)/,
				mac=datatype.match(regxp),
				temp,str;
			
			//�����ֵ��һ��������;
			if(recheck=="recheck"){
				str=curform.data("tipmsg").reck || tipmsg.reck;
				return str;
			}
			
			var tipmsg_w_ex=$.extend({},tipmsg.w,curform.data("tipmsg").w);
			
			//���ԭ�����У�ֱ����ʾ�������ʾ��Ϣ;
			if(mac[0] in tipmsg_w_ex){
				return curform.data("tipmsg").w[mac[0]] || tipmsg.w[mac[0]];
			}
			
			//û�еĻ�����ʾ�������������;
			for(var name in tipmsg_w_ex){
				if(name.indexOf(mac[1])!=-1 && regxp2.test(name)){
					str=(curform.data("tipmsg").w[name] || tipmsg.w[name]).replace(regxp3,"$1"+mac[3]+"$2"+mac[4]+"$3");
					curform.data("tipmsg").w[mac[0]]=str;
					
					return str;
				}
				
			}
			
			return curform.data("tipmsg").def || tipmsg.def;
		},

		_regcheck:function(datatype,gets,obj,curform){
			var curform=curform,
				info=null,
				passed=false,
				reg=/\/.+\//g,
				regex=/^(.+?)(\d+)-(\d+)$/,
				type=3;//default set to wrong type, 2,3,4;
				
			//datatype��������������򣬺�����ֱ�Ӱ󶨵�����;
			
			//ֱ��������;
			if(reg.test(datatype)){
				var regstr=datatype.match(reg)[0].slice(1,-1);
				var param=datatype.replace(reg,"");
				var rexp=RegExp(regstr,param);

				passed=rexp.test(gets);

			//function;
			}else if(Validform.util.toString.call(Validform.util.dataType[datatype])=="[object Function]"){
				passed=Validform.util.dataType[datatype](gets,obj,curform,Validform.util.dataType);
				if(passed === true || passed===undef){
					passed = true;
				}else{
					info= passed;
					passed=false;
				}
			
			//�Զ�������;	
			}else{
				//�Զ���չdatatype;
				if(!(datatype in Validform.util.dataType)){
					var mac=datatype.match(regex),
						temp;
						
					if(!mac){
						passed=false;
						info=curform.data("tipmsg").undef||tipmsg.undef;
					}else{
						for(var name in Validform.util.dataType){
							temp=name.match(regex);
							if(!temp){continue;}
							if(mac[1]===temp[1]){
								var str=Validform.util.dataType[name].toString(),
									param=str.match(/\/[mgi]*/g)[1].replace("\/",""),
									regxp=new RegExp("\\{"+temp[2]+","+temp[3]+"\\}","g");
								str=str.replace(/\/[mgi]*/g,"\/").replace(regxp,"{"+mac[2]+","+mac[3]+"}").replace(/^\//,"").replace(/\/$/,"");
								Validform.util.dataType[datatype]=new RegExp(str,param);
								break;
							}	
						}
					}
				}
				
				if(Validform.util.toString.call(Validform.util.dataType[datatype])=="[object RegExp]"){
					passed=Validform.util.dataType[datatype].test(gets);
				}
					
			}
			
			
			if(passed){
				type=2;
				info=obj.attr("sucmsg") || curform.data("tipmsg").r||tipmsg.r;
				
				//������֤ͨ���󣬻���Ҫ�԰�recheck�Ķ������ֵ�Ƚ�;
				if(obj.attr("recheck")){
					var theother=curform.find("input[name='"+obj.attr("recheck")+"']:first");
					if(gets!=theother.val()){
						passed=false;
						type=3;
						info=obj.attr("errormsg")  || Validform.util.getErrormsg.call(obj,curform,datatype,"recheck");
					}
				}
			}else{
				info=info || obj.attr("errormsg") || Validform.util.getErrormsg.call(obj,curform,datatype);
				
				//��֤��ͨ����Ϊ��ʱ;
				if(Validform.util.isEmpty.call(obj,gets)){
					info=obj.attr("nullmsg") || Validform.util.getNullmsg.call(obj,curform);
				}
			}
			
			return{
					passed:passed,
					type:type,
					info:info
			};
			
		},
		
		regcheck:function(datatype,gets,obj){
			/*
				datatype:datatype;
				gets:inputvalue;
				obj:input object;
			*/
			var curform=this,
				info=null,
				passed=false,
				type=3;//default set to wrong type, 2,3,4;
				
			//ignore;
			if(obj.attr("ignore")==="ignore" && Validform.util.isEmpty.call(obj,gets)){				
				if(obj.data("cked")){
					info="";	
				}
				
				return {
					passed:true,
					type:4,
					info:info
				};
			}

			obj.data("cked","cked");//do nothing if is the first time validation triggered;
			
			var dtype=Validform.util.parseDatatype(datatype);
			var res;
			for(var eithor=0; eithor<dtype.length; eithor++){
				for(var dtp=0; dtp<dtype[eithor].length; dtp++){
					res=Validform.util._regcheck(dtype[eithor][dtp],gets,obj,curform);
					if(!res.passed){
						break;
					}
				}
				if(res.passed){
					break;
				}
			}
			return res;
			
		},
		
		parseDatatype:function(datatype){
			/*
				�ַ�������ֻ�ܺ���һ��������ʽ;
				Datatype���Ʊ�������ĸ�����֡��»��߻�*�����;
				datatype="/regexp/|phone|tel,s,e|f,e";
				==>[["/regexp/"],["phone"],["tel","s","e"],["f","e"]];
			*/

			var reg=/\/.+?\/[mgi]*(?=(,|$|\||\s))|[\w\*-]+/g,
				dtype=datatype.match(reg),
				sepor=datatype.replace(reg,"").replace(/\s*/g,"").split(""),
				arr=[],
				m=0;
				
			arr[0]=[];
			arr[0].push(dtype[0]);
			for(var n=0;n<sepor.length;n++){
				if(sepor[n]=="|"){
					m++;
					arr[m]=[];
				}
				arr[m].push(dtype[n+1]);
			}
			
			return arr;
		},

		showmsg:function(msg,type,o,triggered){
			/*
				msg:��ʾ����;
				type:��ʾ��Ϣ��ʾ��ʽ;
				o:{obj:��ǰ����, type:1=>���ڼ�� | 2=>ͨ��, sweep:true | false}, 
				triggered:��blur���ύ����������֤�У���Щ�������Ҫ��ʾ��ʾ���֣����Զ��嵯����ʾ�����ʾ��ʽ������Ҫÿ��blurʱ�����ϵ�����ʾ;
				
				tiptype:1\2\3ʱ���п��ܻᵯ���Զ�����ʾ��
				tiptype:1ʱ��triggered bycheckʱ������
				tiptype:2\3ʱ��ajaxʱ����
				tipSweepΪtrueʱ��triggered bycheckʱ������showmsg����ajax����������Ҫ��ʾ
			*/
			
			//���msgΪundefined����ô��û��Ҫִ�к���Ĳ�����ignore�п��ܻ���������;
			if(msg==undef){return;}
			
			//tipSweepΪtrue���ҵ�ǰ���Ǵ��ڴ���״̬ʱ��blur�¼���������Ϣ��ʾ;
			if(triggered=="bycheck" && o.sweep && (o.obj && !o.obj.is(".Validform_error") || typeof type == "function")){return;}

			$.extend(o,{curform:this});
				
			if(typeof type == "function"){
				type(msg,o,Validform.util.cssctl);
				return;
			}
			
			if(type==1 || triggered=="byajax" && type!=4){
				msgobj.find(".Validform_info").html(msg);
			}
			
			//tiptypt=1ʱ��blur����showmsg����֤�Ƿ�ͨ�����������ύ�������Ļ���ֻҪ��֤�����͵���;
			if(type==1 && triggered!="bycheck" && o.type!=2 || triggered=="byajax" && type!=4){
				msghidden=false;
				msgobj.find(".iframe").css("height",msgobj.outerHeight());
				msgobj.show();
				setCenter(msgobj,100);
			}

			if(type==2 && o.obj){
				o.obj.parent().next().find(".Validform_checktip").html(msg);
				Validform.util.cssctl(o.obj.parent().next().find(".Validform_checktip"),o.type);
			}
			
			if((type==3 || type==4) && o.obj){
				o.obj.siblings(".Validform_checktip").html(msg);
				Validform.util.cssctl(o.obj.siblings(".Validform_checktip"),o.type);
			}

		},

		cssctl:function(obj,status){
			switch(status){
				case 1:
					obj.removeClass("Validform_right Validform_wrong").addClass("Validform_checktip Validform_loading");//checking;
					break;
				case 2:
					obj.removeClass("Validform_wrong Validform_loading").addClass("Validform_checktip Validform_right");//passed;
					break;
				case 4:
					obj.removeClass("Validform_right Validform_wrong Validform_loading").addClass("Validform_checktip");//for ignore;
					break;
				default:
					obj.removeClass("Validform_right Validform_loading").addClass("Validform_checktip Validform_wrong");//wrong;
			}
		},
		
		check:function(curform,subpost,bool){
			/*
				��ⵥ����Ԫ��;
				��֤ͨ������true�����򷵻�false��ʵʱ��֤����ֵΪajax;
				bool������true��ֻ��ⲻ��ʾ��ʾ��Ϣ;
			*/
			var settings=curform[0].settings;
			var subpost=subpost || "";
			var inputval=Validform.util.getValue.call(curform,$(this));
			
			//���ػ��dataIgnore�ı���������֤;
			if(settings.ignoreHidden && $(this).is(":hidden") || $(this).data("dataIgnore")==="dataIgnore"){
				return true;
			}
			
			//dragonfly=trueʱ��û�а�ignore��ֵΪ�ղ�����֤������֤��ͨ��;
			if(settings.dragonfly && !$(this).data("cked") && Validform.util.isEmpty.call($(this),inputval) && $(this).attr("ignore")!="ignore"){
				return false;
			}
			
			var flag=Validform.util.regcheck.call(curform,$(this).attr("datatype"),inputval,$(this));
			
			//ֵû�仯������⣬��ʱҪ����recheck���;
			//�������ύ��ʱ������ajax��֤;
			if(inputval==this.validform_lastval && !$(this).attr("recheck") && subpost==""){
				return flag.passed ? true : false;
			}

			this.validform_lastval=inputval;//�洢��ǰֵ;
			
			var _this;
			errorobj=_this=$(this);
			
			if(!flag.passed){
				//ȡ�����ڽ��е�ajax��֤;
				Validform.util.abort.call(_this[0]);
				
				if(!bool){
					//����"bycheck"��ָʾ��ǰ��check��������õģ���tiptype=1ʱ��blur�¼����ô���������Ϣ��ʾ;
					Validform.util.showmsg.call(curform,flag.info,settings.tiptype,{obj:$(this),type:flag.type,sweep:settings.tipSweep},"bycheck");
					
					!settings.tipSweep && _this.addClass("Validform_error");
				}
				return false;
			}
			
			//��֤ͨ���Ļ����������ajaxurl��Ҫִ��ajax���;
			//��ignore="ignore"ʱ��Ϊ��ֵ����ͨ����֤����ʱ����Ҫajax���;
			var ajaxurl=$(this).attr("ajaxurl");
			if(ajaxurl && !Validform.util.isEmpty.call($(this),inputval) && !bool){
				var inputobj=$(this);

				//���ύ��ʱ�����е�ĳ���Ѿ���ִ��ajax��⣬��ʱ��Ҫ�ø���ajax����������ύ��;
				if(subpost=="postform"){
					inputobj[0].validform_subpost="postform";
				}else{
					inputobj[0].validform_subpost="";
				}
				
				if(inputobj[0].validform_valid==="posting" && inputval==inputobj[0].validform_ckvalue){return "ajax";}
				
				inputobj[0].validform_valid="posting";
				inputobj[0].validform_ckvalue=inputval;
				Validform.util.showmsg.call(curform,curform.data("tipmsg").c||tipmsg.c,settings.tiptype,{obj:inputobj,type:1,sweep:settings.tipSweep},"bycheck");
				
				Validform.util.abort.call(_this[0]);
				
				var ajaxsetup=$.extend(true,{},settings.ajaxurl || {});
								
				var localconfig={
					type: "POST",
					cache:false,
					url: ajaxurl,
					data: "param="+encodeURIComponent(inputval)+"&name="+encodeURIComponent($(this).attr("name")),
					success: function(data){
						if($.trim(data.status)==="y"){
							inputobj[0].validform_valid="true";
							data.info && inputobj.attr("sucmsg",data.info);
							Validform.util.showmsg.call(curform,inputobj.attr("sucmsg") || curform.data("tipmsg").r||tipmsg.r,settings.tiptype,{obj:inputobj,type:2,sweep:settings.tipSweep},"bycheck");
							_this.removeClass("Validform_error");
							errorobj=null;
							if(inputobj[0].validform_subpost=="postform"){
								curform.trigger("submit");
							}
						}else{
							inputobj[0].validform_valid=data.info;
							Validform.util.showmsg.call(curform,data.info,settings.tiptype,{obj:inputobj,type:3,sweep:settings.tipSweep});
							_this.addClass("Validform_error");
						}
						_this[0].validform_ajax=null;
					},
					error: function(data){
						if(data.status=="200"){
							if(data.responseText=="y"){
								ajaxsetup.success({"status":"y"});
							}else{
								ajaxsetup.success({"status":"n","info":data.responseText});	
							}
							return false;
						}
						
						//���ڼ��ʱ��Ҫ�������ݷ����ı䣬��ʱҪ��ֹ��ǰ��ajax������������������ajax������ô��ʾ��ش�����Ϣ;
						if(data.statusText!=="abort"){
							var msg="status: "+data.status+"; statusText: "+data.statusText;
						
							Validform.util.showmsg.call(curform,msg,settings.tiptype,{obj:inputobj,type:3,sweep:settings.tipSweep});
							_this.addClass("Validform_error");
						}
						
						inputobj[0].validform_valid=data.statusText;
						_this[0].validform_ajax=null;
						
						//localconfig.error����true��ʾ����Ҫִ��temp_err;
						return true;
					}
				}
				
				if(ajaxsetup.success){
					var temp_suc=ajaxsetup.success;
					ajaxsetup.success=function(data){
						localconfig.success(data);
						temp_suc(data,inputobj);
					}
				}
				
				if(ajaxsetup.error){
					var temp_err=ajaxsetup.error;
					ajaxsetup.error=function(data){
						//localconfig.error����false��ʾ����Ҫִ��temp_err;
						localconfig.error(data) && temp_err(data,inputobj);
					}	
				}

				ajaxsetup=$.extend({},localconfig,ajaxsetup,{dataType:"json"});
				_this[0].validform_ajax=$.ajax(ajaxsetup);
				
				return "ajax";
			}else if(ajaxurl && Validform.util.isEmpty.call($(this),inputval)){
				Validform.util.abort.call(_this[0]);
				_this[0].validform_valid="true";
			}
			
			if(!bool){
				Validform.util.showmsg.call(curform,flag.info,settings.tiptype,{obj:$(this),type:flag.type,sweep:settings.tipSweep},"bycheck");
				_this.removeClass("Validform_error");
			}
			errorobj=null;
			
			return true;
		
		},
		
		submitForm:function(settings,flg,url,ajaxPost,sync){
			/*
				flg===trueʱ������ֱ֤���ύ;
				ajaxPost==="ajaxPost"ָʾ��ǰ����ajax��ʽ�ύ;
			*/
			var curform=this;
			
			//�������ύʱ����ύ��ť������Ӧ;
			if(curform[0].validform_status==="posting"){return false;}
			
			//Ҫ��ֻ���ύһ��ʱ;
			if(settings.postonce && curform[0].validform_status==="posted"){return false;}
			
			var beforeCheck=settings.beforeCheck && settings.beforeCheck(curform);
			if(beforeCheck===false){return false;}
			
			var flag=true,
				inflag;
				
			curform.find("[datatype]").each(function(){
				//������֤;
				if(flg){
					return false;
				}
				
				//���ػ��dataIgnore�ı���������֤;
				if(settings.ignoreHidden && $(this).is(":hidden") || $(this).data("dataIgnore")==="dataIgnore"){
					return true;
				}
				
				var inputval=Validform.util.getValue.call(curform,$(this)),
					_this;
				errorobj=_this=$(this);
				
				inflag=Validform.util.regcheck.call(curform,$(this).attr("datatype"),inputval,$(this));
				
				if(!inflag.passed){
					Validform.util.showmsg.call(curform,inflag.info,settings.tiptype,{obj:$(this),type:inflag.type,sweep:settings.tipSweep});
					_this.addClass("Validform_error");
					
					if(!settings.showAllError){
						_this.focus();
						flag=false;
						return false;
					}
					
					flag && (flag=false);
					return true;
				}
				
				//��ignore="ignore"ʱ��Ϊ��ֵ����ͨ����֤����ʱ����Ҫajax���;
				if($(this).attr("ajaxurl") && !Validform.util.isEmpty.call($(this),inputval)){
					if(this.validform_valid!=="true"){
						var thisobj=$(this);
						Validform.util.showmsg.call(curform,curform.data("tipmsg").v||tipmsg.v,settings.tiptype,{obj:thisobj,type:3,sweep:settings.tipSweep});
						_this.addClass("Validform_error");
						
						thisobj.trigger("blur",["postform"]);//continue the form post;
						
						if(!settings.showAllError){
							flag=false;
							return false;
						}
						
						flag && (flag=false);
						return true;
					}
				}else if($(this).attr("ajaxurl") && Validform.util.isEmpty.call($(this),inputval)){
					Validform.util.abort.call(this);
					this.validform_valid="true";
				}

				Validform.util.showmsg.call(curform,inflag.info,settings.tiptype,{obj:$(this),type:inflag.type,sweep:settings.tipSweep});
				_this.removeClass("Validform_error");
				errorobj=null;
			});
			
			if(settings.showAllError){
				curform.find(".Validform_error:first").focus();
			}

			if(flag){
				var beforeSubmit=settings.beforeSubmit && settings.beforeSubmit(curform);
				if(beforeSubmit===false){return false;}
				
				curform[0].validform_status="posting";
							
				if(settings.ajaxPost || ajaxPost==="ajaxPost"){
					//��ȡ���ò���;
					var ajaxsetup=$.extend(true,{},settings.ajaxpost || {});
					//�п�����Ҫ��̬�ĸı��ύ��ַ�����԰�action��ָ����url�㼶��Ϊ���;
					ajaxsetup.url=url || ajaxsetup.url || settings.url || curform.attr("action");
					
					//byajax��ajaxʱ��tiptyeΪ1��2��3��Ҫ������ʾ��;
					Validform.util.showmsg.call(curform,curform.data("tipmsg").p||tipmsg.p,settings.tiptype,{obj:curform,type:1,sweep:settings.tipSweep},"byajax");

					//����������ȼ�Ҫ��;
					//��undefined���;
					if(sync){
						ajaxsetup.async=false;
					}else if(sync===false){
						ajaxsetup.async=true;
					}
					
					if(ajaxsetup.success){
						var temp_suc=ajaxsetup.success;
						ajaxsetup.success=function(data){
							settings.callback && settings.callback(data);
							curform[0].validform_ajax=null;
							if($.trim(data.status)==="y"){
								curform[0].validform_status="posted";
							}else{
								curform[0].validform_status="normal";
							}
							
							temp_suc(data,curform);
						}
					}
					
					if(ajaxsetup.error){
						var temp_err=ajaxsetup.error;
						ajaxsetup.error=function(data){
							settings.callback && settings.callback(data);
							curform[0].validform_status="normal";
							curform[0].validform_ajax=null;
							
							temp_err(data,curform);
						}	
					}
					
					var localconfig={
						type: "POST",
						async:true,
						data: curform.serializeArray(),
						success: function(data){
							if($.trim(data.status)==="y"){
								//�ɹ��ύ;
								curform[0].validform_status="posted";
								Validform.util.showmsg.call(curform,data.info,settings.tiptype,{obj:curform,type:2,sweep:settings.tipSweep},"byajax");
							}else{
								//�ύ����;
								curform[0].validform_status="normal";
								Validform.util.showmsg.call(curform,data.info,settings.tiptype,{obj:curform,type:3,sweep:settings.tipSweep},"byajax");
							}
							
							settings.callback && settings.callback(data);
							curform[0].validform_ajax=null;
						},
						error: function(data){
							var msg="status: "+data.status+"; statusText: "+data.statusText;
									
							Validform.util.showmsg.call(curform,msg,settings.tiptype,{obj:curform,type:3,sweep:settings.tipSweep},"byajax");
							
							settings.callback && settings.callback(data);
							curform[0].validform_status="normal";
							curform[0].validform_ajax=null;
						}
					}
					
					ajaxsetup=$.extend({},localconfig,ajaxsetup,{dataType:"json"});
					
					curform[0].validform_ajax=$.ajax(ajaxsetup);

				}else{
					if(!settings.postonce){
						curform[0].validform_status="normal";
					}
					
					var url=url || settings.url;
					if(url){
						curform.attr("action",url);
					}
					
					return settings.callback && settings.callback(curform);
				}
			}
			
			return false;
			
		},
		
		resetForm:function(){
			var brothers=this;
			brothers.each(function(){
				this.reset && this.reset();
				this.validform_status="normal";
			});
			
			brothers.find(".Validform_right").text("");
			brothers.find(".passwordStrength").children().removeClass("bgStrength");
			brothers.find(".Validform_checktip").removeClass("Validform_wrong Validform_right Validform_loading");
			brothers.find(".Validform_error").removeClass("Validform_error");
			brothers.find("[datatype]").removeData("cked").removeData("dataIgnore").each(function(){
				this.validform_lastval=null;
			});
			brothers.eq(0).find("input:first").focus();
		},
		
		abort:function(){
			if(this.validform_ajax){
				this.validform_ajax.abort();	
			}
		}
		
	}
	
	$.Datatype=Validform.util.dataType;
	
	Validform.prototype={
		dataType:Validform.util.dataType,
		
		eq:function(n){
			var obj=this;
			
			if(n>=obj.forms.length){
				return null;	
			}
			
			if(!(n in obj.objects)){
				obj.objects[n]=new Validform($(obj.forms[n]).get(),{},true);
			}
			
			return obj.objects[n];

		},
		
		resetStatus:function(){
			var obj=this;
			$(obj.forms).each(function(){
				this.validform_status="normal";	
			});
			
			return this;
		},
		
		setStatus:function(status){
			var obj=this;
			$(obj.forms).each(function(){
				this.validform_status=status || "posting";	
			});
			
			return this;
		},
		
		getStatus:function(){
			var obj=this;
			var status=$(obj.forms)[0].validform_status;
			
			return status;
		},
		
		ignore:function(selector){
			var obj=this;
			var selector=selector || "[datatype]"
			
			$(obj.forms).find(selector).each(function(){
				$(this).data("dataIgnore","dataIgnore").removeClass("Validform_error");
			});
			
			return this;
		},
		
		unignore:function(selector){
			var obj=this;
			var selector=selector || "[datatype]"
			
			$(obj.forms).find(selector).each(function(){
				$(this).removeData("dataIgnore");
			});
			
			return this;
		},
		
		addRule:function(rule){
			/*
				rule => [{
					ele:"#id",
					datatype:"*",
					errormsg:"������ʾ���֣�",
					nullmsg:"Ϊ��ʱ����ʾ���֣�",
					tip:"Ĭ����ʾ����ʾ����",
					altercss:"gray",
					ignore:"ignore",
					ajaxurl:"valid.php",
					recheck:"password",
					plugin:"passwordStrength"
				},{},{},...]
			*/
			var obj=this;
			var rule=rule || [];
			
			for(var index=0; index<rule.length; index++){
				var o=$(obj.forms).find(rule[index].ele);
				for(var attr in rule[index]){
					attr !=="ele" && o.attr(attr,rule[index][attr]);
				}
			}
			
			$(obj.forms).each(function(){
				var $this=$(this);
				Validform.util.enhance.call($this,this.settings.tiptype,this.settings.usePlugin,this.settings.tipSweep,"addRule");
			});
			
			return this;
		},
		
		ajaxPost:function(flag,sync,url){
			var obj=this;
			
			$(obj.forms).each(function(){
				//����pop box;
				if( this.settings.tiptype==1 || this.settings.tiptype==2 || this.settings.tiptype==3 ){
					creatMsgbox();
				}
				
				Validform.util.submitForm.call($(obj.forms[0]),this.settings,flag,url,"ajaxPost",sync);
			});
			
			return this;
		},
		
		submitForm:function(flag,url){
			/*flag===trueʱ������ֱ֤���ύ*/
			

			var obj=this;
			
			$(obj.forms).each(function(){
				var subflag=Validform.util.submitForm.call($(this),this.settings,flag,url);
				subflag === undef && (subflag=true);
				if(subflag===true){
					this.submit();
				}
			});
			
			return this;
		},
		
		resetForm:function(){
			var obj=this;
			Validform.util.resetForm.call($(obj.forms));
			
			return this;
		},
		
		abort:function(){
			var obj=this;
			$(obj.forms).each(function(){
				Validform.util.abort.call(this);
			});
			
			return this;
		},
		
		check:function(bool,selector){
			/*
				bool������true��ֻ��ⲻ��ʾ��ʾ��Ϣ;
			*/
			
			var selector=selector || "[datatype]",
				obj=this,
				curform=$(obj.forms),
				flag=true;
			
			curform.find(selector).each(function(){
				Validform.util.check.call(this,curform,"",bool) || (flag=false);
			});
			
			return flag;
		},
		
		config:function(setup){
		/*
			config={
				url:"ajaxpost.php",//ָ����url�����ݻ��ύ�������ַ;
				ajaxurl:{
					timeout:1000,
					...
				},
				ajaxpost:{
					timeout:1000,
					...
				}
			}
		*/
			var obj=this;
			setup=setup || {};
			$(obj.forms).each(function(){
				var $this=$(this);
				this.settings=$.extend(true,this.settings,setup);
				Validform.util.enhance.call($this,this.settings.tiptype,this.settings.usePlugin,this.settings.tipSweep);
			});
			
			return this;
		}
	}

	$.fn.Validform=function(settings){
		return new Validform(this,settings);
	};
	
	function setCenter(obj,time){
		var left=($(window).width()-obj.outerWidth())/2,
			top=($(window).height()-obj.outerHeight())/2,
			
		top=(document.documentElement.scrollTop?document.documentElement.scrollTop:document.body.scrollTop)+(top>0?top:0);

		obj.css({
			left:left
		}).animate({
			top : top
		},{ duration:time , queue:false });
	}
	
	function creatMsgbox(){
		if($("#Validform_msg").length!==0){return false;}
		msgobj=$('<div id="Validform_msg"><div class="Validform_title">'+tipmsg.tit+'<a class="Validform_close" href="javascript:void(0);">&chi;</a></div><div class="Validform_info"></div><div class="iframe"><iframe frameborder="0" scrolling="no" height="100%" width="100%"></iframe></div></div>').appendTo("body");//��ʾ��Ϣ��;
		msgobj.find("a.Validform_close").click(function(){
			msgobj.hide();
			msghidden=true;
			if(errorobj){
				errorobj.focus().addClass("Validform_error");
			}
			return false;
		}).focus(function(){this.blur();});

		$(window).bind("scroll resize",function(){
			!msghidden && setCenter(msgobj,400);
		});
	};
	
	//���÷�����ʾ&�ر���Ϣ��ʾ��;
	$.Showmsg=function(msg){
		creatMsgbox();
		Validform.util.showmsg.call(win,msg,1,{});
	};
	
	$.Hidemsg=function(){
		msgobj.hide();
		msghidden=true;
	};
	
})(jQuery,window);

			"z2-4" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,4}$/,
			"username":function(gets,obj,curform,regxp){
				//²ÎÊýgetsÊÇ»ñÈ¡µ½µÄ±íµ¥ÔªËØÖµ£¬objÎªµ±Ç°±íµ¥ÔªËØ£¬curformÎªµ±Ç°ÑéÖ¤µÄ±íµ¥£¬regxpÎªÄÚÖÃµÄÒ»Ð©ÕýÔò±í´ïÊ½µÄÒýÓÃ;
				var reg1=/^[\w\.]{4,16}$/,
					reg2=/^[\u4E00-\u9FA5\uf900-\ufa2d]{2,8}$/;
				
				if(reg1.test(gets)){return true;}
				if(reg2.test(gets)){return true;}
				return false;
				
				//×¢Òâreturn¿ÉÒÔ·µ»Øtrue »ò false »ò ×Ö·û´®ÎÄ×Ö£¬true±íÊ¾ÑéÖ¤Í¨¹ý£¬·µ»Ø×Ö·û´®±íÊ¾ÑéÖ¤Ê§°Ü£¬×Ö·û´®×÷Îª´íÎóÌáÊ¾ÏÔÊ¾£¬·µ»ØfalseÔòÓÃerrmsg»òÄ¬ÈÏµÄ´íÎóÌáÊ¾;
			},
			"phone":function(){
				// 5.0 °æ±¾Ö®ºó£¬ÒªÊµÏÖ¶þÑ¡Ò»µÄÑéÖ¤Ð§¹û£¬datatype µÄÃû³Æ ²» ÐèÒªÒÔ "option_" ¿ªÍ·;	
			}
		},
		usePlugin:{
			swfupload:{},
			datepicker:{},
			passwordstrength:{},
			jqtransform:{
				selector:"select,input"
			}
		},
		beforeCheck:function(curform){
			//ÔÚ±íµ¥Ìá½»Ö´ÐÐÑéÖ¤Ö®Ç°Ö´ÐÐµÄº¯Êý£¬curform²ÎÊýÊÇµ±Ç°±íµ¥¶ÔÏó¡£
			//ÕâÀïÃ÷È·return falseµÄ»°½«²»»á¼ÌÐøÖ´ÐÐÑéÖ¤²Ù×÷;	
		},
		beforeSubmit:function(curform){
			//ÔÚÑéÖ¤³É¹¦ºó£¬±íµ¥Ìá½»Ç°Ö´ÐÐµÄº¯Êý£¬curform²ÎÊýÊÇµ±Ç°±íµ¥¶ÔÏó¡£
			//ÕâÀïÃ÷È·return falseµÄ»°±íµ¥½«²»»áÌá½»;	
		},
		callback:function(data){
			//·µ»ØÊý¾ÝdataÊÇjson¸ñÊ½£¬{"info":"demo info","status":"y"}
			//info: Êä³öÌáÊ¾ÐÅÏ¢;
			//status: ·µ»ØÌá½»Êý¾ÝµÄ×´Ì¬,ÊÇ·ñÌá½»³É¹¦¡£Èç¿ÉÒÔÓÃ"y"±íÊ¾Ìá½»³É¹¦£¬"n"±íÊ¾Ìá½»Ê§°Ü£¬ÔÚajax_post.phpÎÄ¼þ·µ»ØÊý¾ÝÀï×Ô¶¨×Ö·û£¬Ö÷ÒªÓÃÔÚcallbackº¯ÊýÀï¸ù¾Ý¸ÃÖµÖ´ÐÐÏàÓ¦µÄ»Øµ÷²Ù×÷;
			//ÄãÒ²¿ÉÒÔÔÚajax_post.phpÎÄ¼þ·µ»Ø¸ü¶àÐÅÏ¢ÔÚÕâÀï»ñÈ¡£¬½øÐÐÏàÓ¦²Ù×÷£»
			//ajaxÓöµ½·þÎñ¶Ë´íÎóÊ±Ò²»áÖ´ÐÐ»Øµ÷£¬ÕâÊ±µÄdataÊÇ{ status:**, statusText:**, readyState:**, responseText:** }£»
			
			//ÕâÀïÖ´ÐÐ»Øµ÷²Ù×÷;
			//×¢Òâ£ºÈç¹û²»ÊÇajax·½Ê½Ìá½»±íµ¥£¬´«Èëcallback£¬ÕâÊ±data²ÎÊýÊÇµ±Ç°±íµ¥¶ÔÏó£¬»Øµ÷º¯Êý»áÔÚ±íµ¥ÑéÖ¤È«²¿Í¨¹ýºóÖ´ÐÐ£¬È»ºóÅÐ¶ÏÊÇ·ñÌá½»±íµ¥£¬Èç¹ûcallbackÀïÃ÷È·return false£¬Ôò±íµ¥²»»áÌá½»£¬Èç¹ûreturn true»òÃ»ÓÐreturn£¬Ôò»áÌá½»±íµ¥¡£
		}
	});
	
	Validform¶ÔÏóµÄ·½·¨ºÍÊôÐÔ£º
	tipmsg£º×Ô¶¨ÒåÌáÊ¾ÐÅÏ¢£¬Í¨¹ýÐÞ¸ÄValidform¶ÔÏóµÄÕâ¸öÊôÐÔÖµÀ´ÈÃÍ¬Ò»¸öÒ³ÃæµÄ²»Í¬±íµ¥Ê¹ÓÃ²»Í¬µÄÌáÊ¾ÎÄ×Ö£»
	dataType£º»ñÈ¡ÄÚÖÃµÄÒ»Ð©ÕýÔò£»
	eq(n)£º»ñÈ¡Validform¶ÔÏóµÄµÚn¸öÔªËØ;
	ajaxPost(flag,sync,url)£ºÒÔajax·½Ê½Ìá½»±íµ¥¡£flagÎªtrueÊ±£¬Ìø¹ýÑéÖ¤Ö±½ÓÌá½»£¬syncÎªtrueÊ±½«ÒÔÍ¬²½µÄ·½Ê½½øÐÐajaxÌá½»£¬´«ÈëÁËurlµØÖ·Ê±£¬±íµ¥»áÌá½»µ½Õâ¸öµØÖ·£»
	abort()£ºÖÕÖ¹ajaxµÄÌá½»£»
	submitForm(flag,url)£ºÒÔ²ÎÊýÀïÉèÖÃµÄ·½Ê½Ìá½»±íµ¥£¬flagÎªtrueÊ±£¬Ìø¹ýÑéÖ¤Ö±½ÓÌá½»£¬´«ÈëÁËurlµØÖ·Ê±£¬±íµ¥»áÌá½»µ½Õâ¸öµØÖ·£»
	resetForm()£ºÖØÖÃ±íµ¥£»
	resetStatus()£ºÖØÖÃ±íµ¥µÄÌá½»×´Ì¬¡£´«ÈëÁËpostonce²ÎÊýµÄ»°£¬±íµ¥³É¹¦Ìá½»ºó×´Ì¬»áÉèÖÃÎª"posted"£¬ÖØÖÃÌá½»×´Ì¬¿ÉÒÔÈÃ±íµ¥¼ÌÐø¿ÉÒÔÌá½»£»
	getStatus()£º»ñÈ¡±íµ¥µÄÌá½»×´Ì¬£¬normal£ºÎ´Ìá½»£¬posting£ºÕýÔÚÌá½»£¬posted£ºÒÑ³É¹¦Ìá½»¹ý£»
	setStatus(status)£ºÉèÖÃ±íµ¥µÄÌá½»×´Ì¬£¬¿ÉÒÔÉèÖÃnormal£¬posting£¬postedÈýÖÖ×´Ì¬£¬²»´«²ÎÔòÉèÖÃ×´Ì¬Îªposting£¬Õâ¸ö×´Ì¬±íµ¥¿ÉÒÔÑéÖ¤£¬µ«²»ÄÜÌá½»£»
	ignore(selector)£ººöÂÔ¶ÔËùÑ¡Ôñ¶ÔÏóµÄÑéÖ¤£»
	unignore(selector)£º½«ignore·½·¨ËùºöÂÔÑéÖ¤µÄ¶ÔÏóÖØÐÂ»ñÈ¡ÑéÖ¤Ð§¹û£»
	addRule(rule)£º¿ÉÒÔÍ¨¹ýValidform¶ÔÏóµÄÕâ¸ö·½·¨À´¸ø±íµ¥ÔªËØ°ó¶¨ÑéÖ¤¹æÔò£»
	check(bool,selector):¶ÔÖ¸¶¨¶ÔÏó½øÐÐÑéÖ¤(Ä¬ÈÏÑéÖ¤µ±Ç°Õû¸ö±íµ¥)£¬Í¨¹ý·µ»Øtrue£¬·ñÔò·µ»Øfalse£¨°ó¶¨ÊµÊ±ÑéÖ¤µÄ¶ÔÏó£¬¸ñÊ½·ûºÏÒªÇóÊ±·µ»Øtrue£¬¶ø²»»áµÈajaxµÄ·µ»Ø½á¹û£©£¬boolÎªtrueÊ±ÔòÖ»ÑéÖ¤²»ÏÔÊ¾ÌáÊ¾ÐÅÏ¢£»
	config(setup):¿ÉÒÔÍ¨¹ýÕâ¸ö·½·¨À´ÐÞ¸Ä³õÊ¼»¯²ÎÊý£¬Ö¸¶¨±íµ¥µÄÌá½»µØÖ·£¬¸ø±íµ¥ajaxºÍÊµÊ±ÑéÖ¤µÄajaxÀïÉèÖÃ²ÎÊý£»
*/

(function($,win,undef){
	var errorobj=null,//Ö¸Ê¾µ±Ç°ÑéÖ¤Ê§°ÜµÄ±íµ¥ÔªËØ;
		msgobj=null,//pop box object 
		msghidden=true;//msgbox hidden?

	var tipmsg={//Ä¬ÈÏÌáÊ¾ÎÄ×Ö;
		tit:"ÌáÊ¾ÐÅÏ¢",
		w:{
			"*":"²»ÄÜÎª¿Õ£¡",
			"*6-16":"ÇëÌîÐ´6µ½16Î»ÈÎÒâ×Ö·û£¡",
			"n":"ÇëÌîÐ´Êý×Ö£¡",
			"n6-16":"ÇëÌîÐ´6µ½16Î»Êý×Ö£¡",
			"s":"²»ÄÜÊäÈëÌØÊâ×Ö·û£¡",
			"s6-18":"ÇëÌîÐ´6µ½18Î»×Ö·û£¡",
			"p":"ÇëÌîÐ´ÓÊÕþ±àÂë£¡",
			"m":"ÇëÌîÐ´ÊÖ»úºÅÂë£¡",
			"e":"ÓÊÏäµØÖ·¸ñÊ½²»¶Ô£¡",
			"url":"ÇëÌîÐ´ÍøÖ·£¡"
		},
		def:"ÇëÌîÐ´ÕýÈ·ÐÅÏ¢£¡",
		undef:"datatypeÎ´¶¨Òå£¡",
		reck:"Á½´ÎÊäÈëµÄÄÚÈÝ²»Ò»ÖÂ£¡",
		r:"Í¨¹ýÐÅÏ¢ÑéÖ¤£¡",
		c:"ÕýÔÚ¼ì²âÐÅÏ¢¡­",
		s:"Çë{ÌîÐ´|Ñ¡Ôñ}{0|ÐÅÏ¢}£¡",
		v:"ËùÌîÐÅÏ¢Ã»ÓÐ¾­¹ýÑéÖ¤£¬ÇëÉÔºó¡­",
		p:"ÕýÔÚÌá½»Êý¾Ý¡­"
	}
	$.Tipmsg=tipmsg;
	
	var Validform=function(forms,settings,inited){
		var settings=$.extend({},Validform.defaults,settings);
		settings.datatype && $.extend(Validform.util.dataType,settings.datatype);
		
		var brothers=this;
		brothers.tipmsg={w:{}};
		brothers.forms=forms;
		brothers.objects=[];
		
		//´´½¨×Ó¶ÔÏóÊ±²»ÔÙ°ó¶¨ÊÂ¼þ;
		if(inited===true){
			return false;
		}
		
		forms.each(function(){
			//ÒÑ¾­°ó¶¨ÊÂ¼þÊ±Ìø¹ý£¬±ÜÃâÊÂ¼þÖØ¸´°ó¶¨;
			if(this.validform_inited=="inited"){return true;}
			this.validform_inited="inited";
			
			var curform=this;
			curform.settings=$.extend({},settings);
			
			var $this=$(curform);
			
			//·ÀÖ¹±íµ¥°´Å¥Ë«»÷Ìá½»Á½´Î;
			curform.validform_status="normal"; //normal | posting | posted;
			
			//ÈÃÃ¿¸öValidform¶ÔÏó¶¼ÄÜ×Ô¶¨Òåtipmsg;	
			$this.data("tipmsg",brothers.tipmsg);

			//bind the blur event;
			$this.delegate("[datatype]","blur",function(){
				//ÅÐ¶ÏÊÇ·ñÊÇÔÚÌá½»±íµ¥²Ù×÷Ê±´¥·¢µÄÑéÖ¤ÇëÇó£»
				var subpost=arguments[1];
				Validform.util.check.call(this,$this,subpost);
			});
			
			$this.delegate(":text","keypress",function(event){
				if(event.keyCode==13 && $this.find(":submit").length==0){
					$this.submit();
				}
			});
			
			//µã»÷±íµ¥ÔªËØ£¬Ä¬ÈÏÎÄ×ÖÏûÊ§Ð§¹û;
			//±íµ¥ÔªËØÖµ±È½ÏÊ±µÄÐÅÏ¢ÌáÊ¾ÔöÇ¿;
			//radio¡¢checkboxÌáÊ¾ÐÅÏ¢ÔöÇ¿;
			//Íâµ÷²å¼þ³õÊ¼»¯;
			Validform.util.enhance.call($this,curform.settings.tiptype,curform.settings.usePlugin,curform.settings.tipSweep);
			
			curform.settings.btnSubmit && $this.find(curform.settings.btnSubmit).bind("click",function(){
				$this.trigger("submit");
				return false;
			});
						
			$this.submit(function(){
				var subflag=Validform.util.submitForm.call($this,curform.settings);
				subflag === undef && (subflag=true);
				return subflag;
			});
			
			$this.find("[type='reset']").add($this.find(curform.settings.btnReset)).bind("click",function(){
				Validform.util.resetForm.call($this);
			});
			
		});
		
		//Ô¤´´½¨pop box;
		if( settings.tiptype==1 || (settings.tiptype==2 || settings.tiptype==3) && settings.ajaxPost ){		
			creatMsgbox();
		}
	}
	
	Validform.defaults={
		tiptype:1,
		tipSweep:false,
		showAllError:false,
		postonce:false,
		ajaxPost:false
	}
	
	Validform.util={
		dataType:{
			"*":/[\w\W]+/,
			"*6-16":/^[\w\W]{6,16}$/,
			"n":/^\d+$/,
			"n6-16":/^\d{6,16}$/,
			"s":/^[\u4E00-\u9FA5\uf900-\ufa2d\w\.\s]+$/,
			"s6-18":/^[\u4E00-\u9FA5\uf900-\ufa2d\w\.\s]{6,18}$/,
			"p":/^[0-9]{6}$/,
			"m":/^13[0-9]{9}$|14[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$/,
			"e":/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
			"url":/^(\w+:\/\/)?\w+(\.\w+)+.*$/
		},
		
		toString:Object.prototype.toString,
		
		isEmpty:function(val){
			return val==="" || val===$.trim(this.attr("tip"));
		},
		
		getValue:function(obj){
			var inputval,
				curform=this;
				
			if(obj.is(":radio")){
				inputval=curform.find(":radio[name='"+obj.attr("name")+"']:checked").val();
				inputval= inputval===undef ? "" : inputval;
			}else if(obj.is(":checkbox")){
				inputval="";
				curform.find(":checkbox[name='"+obj.attr("name")+"']:checked").each(function(){ 
					inputval +=$(this).val()+','; 
				})
				inputval= inputval===undef ? "" : inputval;
			}else{
				inputval=obj.val();
			}
			inputval=$.trim(inputval);
			
			return Validform.util.isEmpty.call(obj,inputval) ? "" : inputval;
		},
		
		enhance:function(tiptype,usePlugin,tipSweep,addRule){
			var curform=this;
			
			//Ò³ÃæÉÏ²»´æÔÚÌáÊ¾ÐÅÏ¢µÄ±êÇ©Ê±£¬×Ô¶¯´´½¨;
			curform.find("[datatype]").each(function(){
				if(tiptype==2){
					if($(this).parent().next().find(".Validform_checktip").length==0){
						$(this).parent().next().append("<span class='Validform_checktip' />");
						$(this).siblings(".Validform_checktip").remove();
					}
				}else if(tiptype==3 || tiptype==4){
					if($(this).siblings(".Validform_checktip").length==0){
						$(this).parent().append("<span class='Validform_checktip' />");
						$(this).parent().next().find(".Validform_checktip").remove();
					}
				}
			})
			
			//±íµ¥ÔªËØÖµ±È½ÏÊ±µÄÐÅÏ¢ÌáÊ¾ÔöÇ¿;
			curform.find("input[recheck]").each(function(){
				//ÒÑ¾­°ó¶¨ÊÂ¼þÊ±Ìø¹ý;
				if(this.validform_inited=="inited"){return true;}
				this.validform_inited="inited";
				
				var _this=$(this);
				var recheckinput=curform.find("input[name='"+$(this).attr("recheck")+"']");
				recheckinput.bind("keyup",function(){
					if(recheckinput.val()==_this.val() && recheckinput.val() != ""){
						if(recheckinput.attr("tip")){
							if(recheckinput.attr("tip") == recheckinput.val()){return false;}
						}
						_this.trigger("blur");
					}
				}).bind("blur",function(){
					if(recheckinput.val()!=_this.val() && _this.val()!=""){
						if(_this.attr("tip")){
							if(_this.attr("tip") == _this.val()){return false;}	
						}
						_this.trigger("blur");
					}
				});
			});
			
			//hasDefaultText;
			curform.find("[tip]").each(function(){//tipÊÇ±íµ¥ÔªËØµÄÄ¬ÈÏÌáÊ¾ÐÅÏ¢,ÕâÊÇµã»÷Çå¿ÕÐ§¹û;
				//ÒÑ¾­°ó¶¨ÊÂ¼þÊ±Ìø¹ý;
				if(this.validform_inited=="inited"){return true;}
				this.validform_inited="inited";
				
				var defaultvalue=$(this).attr("tip");
				var altercss=$(this).attr("altercss");
				$(this).focus(function(){
					if($(this).val()==defaultvalue){
						$(this).val('');
						if(altercss){$(this).removeClass(altercss);}
					}
				}).blur(function(){
					if($.trim($(this).val())===''){
						$(this).val(defaultvalue);
						if(altercss){$(this).addClass(altercss);}
					}
				});
			});
			
			//enhance info feedback for checkbox & radio;
			curform.find(":checkbox[datatype],:radio[datatype]").each(function(){
				//ÒÑ¾­°ó¶¨ÊÂ¼þÊ±Ìø¹ý;
				if(this.validform_inited=="inited"){return true;}
				this.validform_inited="inited";
				
				var _this=$(this);
				var name=_this.attr("name");
				curform.find("[name='"+name+"']").filter(":checkbox,:radio").bind("click",function(){
					//±ÜÃâ¶à¸öÊÂ¼þ°ó¶¨Ê±µÄÈ¡ÖµÖÍºóÎÊÌâ;
					setTimeout(function(){
						_this.trigger("blur");
					},0);
				});
				
			});
			
			//select multiple;
			curform.find("select[datatype][multiple]").bind("click",function(){
				var _this=$(this);
				setTimeout(function(){
					_this.trigger("blur");
				},0);
			});
			
			//plugins here to start;
			Validform.util.usePlugin.call(curform,usePlugin,tiptype,tipSweep,addRule);
		},
		
		usePlugin:function(plugin,tiptype,tipSweep,addRule){
			/*
				plugin:settings.usePlugin;
				tiptype:settings.tiptype;
				tipSweep:settings.tipSweep;
				addRule:ÊÇ·ñÔÚaddRuleÊ±´¥·¢;
			*/

			var curform=this,
				plugin=plugin || {};
			//swfupload;
			if(curform.find("input[plugin='swfupload']").length && typeof(swfuploadhandler) != "undefined"){
				
				var custom={
						custom_settings:{
							form:curform,
							showmsg:function(msg,type,obj){
								Validform.util.showmsg.call(curform,msg,tiptype,{obj:curform.find("input[plugin='swfupload']"),type:type,sweep:tipSweep});	
							}	
						}	
					};

				custom=$.extend(true,{},plugin.swfupload,custom);
				
				curform.find("input[plugin='swfupload']").each(function(n){
					if(this.validform_inited=="inited"){return true;}
					this.validform_inited="inited";
					
					$(this).val("");
					swfuploadhandler.init(custom,n);
				});
				
			}
			
			//datepicker;
			if(curform.find("input[plugin='datepicker']").length && $.fn.datePicker){
				plugin.datepicker=plugin.datepicker || {};
				
				if(plugin.datepicker.format){
					Date.format=plugin.datepicker.format; 
					delete plugin.datepicker.format;
				}
				if(plugin.datepicker.firstDayOfWeek){
					Date.firstDayOfWeek=plugin.datepicker.firstDayOfWeek; 
					delete plugin.datepicker.firstDayOfWeek;
				}

				curform.find("input[plugin='datepicker']").each(function(n){
					if(this.validform_inited=="inited"){return true;}
					this.validform_inited="inited";
					
					plugin.datepicker.callback && $(this).bind("dateSelected",function(){
						var d=new Date( $.event._dpCache[this._dpId].getSelected()[0] ).asString(Date.format);
						plugin.datepicker.callback(d,this);
					});
					$(this).datePicker(plugin.datepicker);
				});
			}
			
			//passwordstrength;
			if(curform.find("input[plugin*='passwordStrength']").length && $.fn.passwordStrength){
				plugin.passwordstrength=plugin.passwordstrength || {};
				plugin.passwordstrength.showmsg=function(obj,msg,type){
					Validform.util.showmsg.call(curform,msg,tiptype,{obj:obj,type:type,sweep:tipSweep});
				};
				
				curform.find("input[plugin='passwordStrength']").each(function(n){
					if(this.validform_inited=="inited"){return true;}
					this.validform_inited="inited";
					
					$(this).passwordStrength(plugin.passwordstrength);
				});
			}
			
			//jqtransform;
			if(addRule!="addRule" && plugin.jqtransform && $.fn.jqTransSelect){
				if(curform[0].jqTransSelected=="true"){return;};
				curform[0].jqTransSelected="true";
				
				var jqTransformHideSelect = function(oTarget){
					var ulVisible = $('.jqTransformSelectWrapper ul:visible');
					ulVisible.each(function(){
						var oSelect = $(this).parents(".jqTransformSelectWrapper:first").find("select").get(0);
						//do not hide if click on the label object associated to the select
						if( !(oTarget && oSelect.oLabel && oSelect.oLabel.get(0) == oTarget.get(0)) ){$(this).hide();}
					});
				};
				
				/* Check for an external click */
				var jqTransformCheckExternalClick = function(event) {
					if ($(event.target).parents('.jqTransformSelectWrapper').length === 0) { jqTransformHideSelect($(event.target)); }
				};
				
				var jqTransformAddDocumentListener = function (){
					$(document).mousedown(jqTransformCheckExternalClick);
				};
				
				if(plugin.jqtransform.selector){
					curform.find(plugin.jqtransform.selector).filter('input:submit, input:reset, input[type="button"]').jqTransInputButton();
					curform.find(plugin.jqtransform.selector).filter('input:text, input:password').jqTransInputText();			
					curform.find(plugin.jqtransform.selector).filter('input:checkbox').jqTransCheckBox();
					curform.find(plugin.jqtransform.selector).filter('input:radio').jqTransRadio();
					curform.find(plugin.jqtransform.selector).filter('textarea').jqTransTextarea();
					if(curform.find(plugin.jqtransform.selector).filter("select").length > 0 ){
						 curform.find(plugin.jqtransform.selector).filter("select").jqTransSelect();
						 jqTransformAddDocumentListener();
					}
					
				}else{
					curform.jqTransform();
				}
				
				curform.find(".jqTransformSelectWrapper").find("li a").click(function(){
					$(this).parents(".jqTransformSelectWrapper").find("select").trigger("blur");	
				});
			}

		},
		
		getNullmsg:function(curform){
			var obj=this;
			var reg=/[\u4E00-\u9FA5\uf900-\ufa2da-zA-Z\s]+/g;
			var nullmsg;
			
			var label=curform[0].settings.label || ".Validform_label";
			label=obj.siblings(label).eq(0).text() || obj.siblings().find(label).eq(0).text() || obj.parent().siblings(label).eq(0).text() || obj.parent().siblings().find(label).eq(0).text();
			label=label.replace(/\s(?![a-zA-Z])/g,"").match(reg);
			label=label? label.join("") : [""];

			reg=/\{(.+)\|(.+)\}/;
			nullmsg=curform.data("tipmsg").s || tipmsg.s;
			
			if(label != ""){
				nullmsg=nullmsg.replace(/\{0\|(.+)\}/,label);
				if(obj.attr("recheck")){
					nullmsg=nullmsg.replace(/\{(.+)\}/,"");
					obj.attr("nullmsg",nullmsg);
					return nullmsg;
				}
			}else{
				nullmsg=obj.is(":checkbox,:radio,select") ? nullmsg.replace(/\{0\|(.+)\}/,"") : nullmsg.replace(/\{0\|(.+)\}/,"$1");
			}
			nullmsg=obj.is(":checkbox,:radio,select") ? nullmsg.replace(reg,"$2") : nullmsg.replace(reg,"$1");
			
			obj.attr("nullmsg",nullmsg);
			return nullmsg;
		},
		
		getErrormsg:function(curform,datatype,recheck){
			var regxp=/^(.+?)((\d+)-(\d+))?$/,
				regxp2=/^(.+?)(\d+)-(\d+)$/,
				regxp3=/(.*?)\d+(.+?)\d+(.*)/,
				mac=datatype.match(regxp),
				temp,str;
			
			//Èç¹ûÊÇÖµ²»Ò»Ñù¶ø±¨´í;
			if(recheck=="recheck"){
				str=curform.data("tipmsg").reck || tipmsg.reck;
				return str;
			}
			
			var tipmsg_w_ex=$.extend({},tipmsg.w,curform.data("tipmsg").w);
			
			//Èç¹ûÔ­À´¾ÍÓÐ£¬Ö±½ÓÏÔÊ¾¸ÃÏîµÄÌáÊ¾ÐÅÏ¢;
			if(mac[0] in tipmsg_w_ex){
				return curform.data("tipmsg").w[mac[0]] || tipmsg.w[mac[0]];
			}
			
			//Ã»ÓÐµÄ»°ÔÚÌáÊ¾¶ÔÏóÀï²éÕÒÏàËÆ;
			for(var name in tipmsg_w_ex){
				if(name.indexOf(mac[1])!=-1 && regxp2.test(name)){
					str=(curform.data("tipmsg").w[name] || tipmsg.w[name]).replace(regxp3,"$1"+mac[3]+"$2"+mac[4]+"$3");
					curform.data("tipmsg").w[mac[0]]=str;
					
					return str;
				}
				
			}
			
			return curform.data("tipmsg").def || tipmsg.def;
		},

		_regcheck:function(datatype,gets,obj,curform){
			var curform=curform,
				info=null,
				passed=false,
				reg=/\/.+\//g,
				regex=/^(.+?)(\d+)-(\d+)$/,
				type=3;//default set to wrong type, 2,3,4;
				
			//datatypeÓÐÈýÖÖÇé¿ö£ºÕýÔò£¬º¯ÊýºÍÖ±½Ó°ó¶¨µÄÕýÔò;
			
			//Ö±½ÓÊÇÕýÔò;
			if(reg.test(datatype)){
				var regstr=datatype.match(reg)[0].slice(1,-1);
				var param=datatype.replace(reg,"");
				var rexp=RegExp(regstr,param);

				passed=rexp.test(gets);

			//function;
			}else if(Validform.util.toString.call(Validform.util.dataType[datatype])=="[object Function]"){
				passed=Validform.util.dataType[datatype](gets,obj,curform,Validform.util.dataType);
				if(passed === true || passed===undef){
					passed = true;
				}else{
					info= passed;
					passed=false;
				}
			
			//×Ô¶¨ÒåÕýÔò;	
			}else{
				//×Ô¶¯À©Õ¹datatype;
				if(!(datatype in Validform.util.dataType)){
					var mac=datatype.match(regex),
						temp;
						
					if(!mac){
						passed=false;
						info=curform.data("tipmsg").undef||tipmsg.undef;
					}else{
						for(var name in Validform.util.dataType){
							temp=name.match(regex);
							if(!temp){continue;}
							if(mac[1]===temp[1]){
								var str=Validform.util.dataType[name].toString(),
									param=str.match(/\/[mgi]*/g)[1].replace("\/",""),
									regxp=new RegExp("\\{"+temp[2]+","+temp[3]+"\\}","g");
								str=str.replace(/\/[mgi]*/g,"\/").replace(regxp,"{"+mac[2]+","+mac[3]+"}").replace(/^\//,"").replace(/\/$/,"");
								Validform.util.dataType[datatype]=new RegExp(str,param);
								break;
							}	
						}
					}
				}
				
				if(Validform.util.toString.call(Validform.util.dataType[datatype])=="[object RegExp]"){
					passed=Validform.util.dataType[datatype].test(gets);
				}
					
			}
			
			
			if(passed){
				type=2;
				info=obj.attr("sucmsg") || curform.data("tipmsg").r||tipmsg.r;
				
				//¹æÔòÑéÖ¤Í¨¹ýºó£¬»¹ÐèÒª¶Ô°ó¶¨recheckµÄ¶ÔÏó½øÐÐÖµ±È½Ï;
				if(obj.attr("recheck")){
					var theother=curform.find("input[name='"+obj.attr("recheck")+"']:first");
					if(gets!=theother.val()){
						passed=false;
						type=3;
						info=obj.attr("errormsg")  || Validform.util.getErrormsg.call(obj,curform,datatype,"recheck");
					}
				}
			}else{
				info=info || obj.attr("errormsg") || Validform.util.getErrormsg.call(obj,curform,datatype);
				
				//ÑéÖ¤²»Í¨¹ýÇÒÎª¿ÕÊ±;
				if(Validform.util.isEmpty.call(obj,gets)){
					info=obj.attr("nullmsg") || Validform.util.getNullmsg.call(obj,curform);
				}
			}
			
			return{
					passed:passed,
					type:type,
					info:info
			};
			
		},
		
		regcheck:function(datatype,gets,obj){
			/*
				datatype:datatype;
				gets:inputvalue;
				obj:input object;
			*/
			var curform=this,
				info=null,
				passed=false,
				type=3;//default set to wrong type, 2,3,4;
				
			//ignore;
			if(obj.attr("ignore")==="ignore" && Validform.util.isEmpty.call(obj,gets)){				
				if(obj.data("cked")){
					info="";	
				}
				
				return {
					passed:true,
					type:4,
					info:info
				};
			}

			obj.data("cked","cked");//do nothing if is the first time validation triggered;
			
			var dtype=Validform.util.parseDatatype(datatype);
			var res;
			for(var eithor=0; eithor<dtype.length; eithor++){
				for(var dtp=0; dtp<dtype[eithor].length; dtp++){
					res=Validform.util._regcheck(dtype[eithor][dtp],gets,obj,curform);
					if(!res.passed){
						break;
					}
				}
				if(res.passed){
					break;
				}
			}
			return res;
			
		},
		
		parseDatatype:function(datatype){
			/*
				×Ö·û´®ÀïÃæÖ»ÄÜº¬ÓÐÒ»¸öÕýÔò±í´ïÊ½;
				DatatypeÃû³Æ±ØÐëÊÇ×ÖÄ¸£¬Êý×Ö¡¢ÏÂ»®Ïß»ò*ºÅ×é³É;
				datatype="/regexp/|phone|tel,s,e|f,e";
				==>[["/regexp/"],["phone"],["tel","s","e"],["f","e"]];
			*/

			var reg=/\/.+?\/[mgi]*(?=(,|$|\||\s))|[\w\*-]+/g,
				dtype=datatype.match(reg),
				sepor=datatype.replace(reg,"").replace(/\s*/g,"").split(""),
				arr=[],
				m=0;
				
			arr[0]=[];
			arr[0].push(dtype[0]);
			for(var n=0;n<sepor.length;n++){
				if(sepor[n]=="|"){
					m++;
					arr[m]=[];
				}
				arr[m].push(dtype[n+1]);
			}
			
			return arr;
		},

		showmsg:function(msg,type,o,triggered){
			/*
				msg:ÌáÊ¾ÎÄ×Ö;
				type:ÌáÊ¾ÐÅÏ¢ÏÔÊ¾·½Ê½;
				o:{obj:µ±Ç°¶ÔÏó, type:1=>ÕýÔÚ¼ì²â | 2=>Í¨¹ý, sweep:true | false}, 
				triggered:ÔÚblur»òÌá½»±íµ¥´¥·¢µÄÑéÖ¤ÖÐ£¬ÓÐÐ©Çé¿ö²»ÐèÒªÏÔÊ¾ÌáÊ¾ÎÄ×Ö£¬Èç×Ô¶¨Òåµ¯³öÌáÊ¾¿òµÄÏÔÊ¾·½Ê½£¬²»ÐèÒªÃ¿´ÎblurÊ±¾ÍÂíÉÏµ¯³öÌáÊ¾;
				
				tiptype:1\2\3Ê±¶¼ÓÐ¿ÓÄÜ»áµ¯³ö×Ô¶¨ÒåÌáÊ¾¿ò
				tiptype:1Ê±ÔÚtriggered bycheckÊ±²»µ¯¿ò
				tiptype:2\3Ê±ÔÚajaxÊ±µ¯¿ò
				tipSweepÎªtrueÊ±ÔÚtriggered bycheckÊ±²»´¥·¢showmsg£¬µ«ajax³ö´íµÄÇé¿öÏÂÒªÌáÊ¾
			*/
			
			//Èç¹ûmsgÎªundefined£¬ÄÇÃ´¾ÍÃ»±ØÒªÖ´ÐÐºóÃæµÄ²Ù×÷£¬ignoreÓÐ¿ÉÄÜ»á³öÏÖÕâÇé¿ö;
			if(msg==undef){return;}
			
			//tipSweepÎªtrue£¬ÇÒµ±Ç°²»ÊÇ´¦ÓÚ´íÎó×´Ì¬Ê±£¬blurÊÂ¼þ²»´¥·¢ÐÅÏ¢ÏÔÊ¾;
			if(triggered=="bycheck" && o.sweep && (o.obj && !o.obj.is(".Validform_error") || typeof type == "function")){return;}

			$.extend(o,{curform:this});
				
			if(typeof type == "function"){
				type(msg,o,Validform.util.cssctl);
				return;
			}
			
			if(type==1 || triggered=="byajax" && type!=4){
				msgobj.find(".Validform_info").html(msg);
			}
			
			//tiptypt=1Ê±£¬blur´¥·¢showmsg£¬ÑéÖ¤ÊÇ·ñÍ¨¹ý¶¼²»µ¯¿ò£¬Ìá½»±íµ¥´¥·¢µÄ»°£¬Ö»ÒªÑéÖ¤³ö´í£¬¾Íµ¯¿ò;
			if(type==1 && triggered!="bycheck" && o.type!=2 || triggered=="byajax" && type!=4){
				msghidden=false;
				msgobj.find(".iframe").css("height",msgobj.outerHeight());
				msgobj.show();
				setCenter(msgobj,100);
			}

			if(type==2 && o.obj){
				o.obj.parent().next().find(".Validform_checktip").html(msg);
				Validform.util.cssctl(o.obj.parent().next().find(".Validform_checktip"),o.type);
			}
			
			if((type==3 || type==4) && o.obj){
				o.obj.siblings(".Validform_checktip").html(msg);
				Validform.util.cssctl(o.obj.siblings(".Validform_checktip"),o.type);
			}

		},

		cssctl:function(obj,status){
			switch(status){
				case 1:
					obj.removeClass("Validform_right Validform_wrong").addClass("Validform_checktip Validform_loading");//checking;
					break;
				case 2:
					obj.removeClass("Validform_wrong Validform_loading").addClass("Validform_checktip Validform_right");//passed;
					break;
				case 4:
					obj.removeClass("Validform_right Validform_wrong Validform_loading").addClass("Validform_checktip");//for ignore;
					break;
				default:
					obj.removeClass("Validform_right Validform_loading").addClass("Validform_checktip Validform_wrong");//wrong;
			}
		},
		
		check:function(curform,subpost,bool){
			/*
				¼ì²âµ¥¸ö±íµ¥ÔªËØ;
				ÑéÖ¤Í¨¹ý·µ»Øtrue£¬·ñÔò·µ»Øfalse¡¢ÊµÊ±ÑéÖ¤·µ»ØÖµÎªajax;
				bool£¬´«ÈëtrueÔòÖ»¼ì²â²»ÏÔÊ¾ÌáÊ¾ÐÅÏ¢;
			*/
			var settings=curform[0].settings;
			var subpost=subpost || "";
			var inputval=Validform.util.getValue.call(curform,$(this));
			
			//Òþ²Ø»ò°ó¶¨dataIgnoreµÄ±íµ¥¶ÔÏó²»×öÑéÖ¤;
			if(settings.ignoreHidden && $(this).is(":hidden") || $(this).data("dataIgnore")==="dataIgnore"){
				return true;
			}
			
			//dragonfly=trueÊ±£¬Ã»ÓÐ°ó¶¨ignore£¬ÖµÎª¿Õ²»×öÑéÖ¤£¬µ«ÑéÖ¤²»Í¨¹ý;
			if(settings.dragonfly && !$(this).data("cked") && Validform.util.isEmpty.call($(this),inputval) && $(this).attr("ignore")!="ignore"){
				return false;
			}
			
			var flag=Validform.util.regcheck.call(curform,$(this).attr("datatype"),inputval,$(this));
			
			//ÖµÃ»±ä»¯²»×ö¼ì²â£¬ÕâÊ±Òª¿¼ÂÇrecheckÇé¿ö;
			//²»ÊÇÔÚÌá½»±íµ¥Ê±´¥·¢µÄajaxÑéÖ¤;
			if(inputval==this.validform_lastval && !$(this).attr("recheck") && subpost==""){
				return flag.passed ? true : false;
			}

			this.validform_lastval=inputval;//´æ´¢µ±Ç°Öµ;
			
			var _this;
			errorobj=_this=$(this);
			
			if(!flag.passed){
				//È¡ÏûÕýÔÚ½øÐÐµÄajaxÑéÖ¤;
				Validform.util.abort.call(_this[0]);
				
				if(!bool){
					//´«Èë"bycheck"£¬Ö¸Ê¾µ±Ç°ÊÇcheck·½·¨Àïµ÷ÓÃµÄ£¬µ±tiptype=1Ê±£¬blurÊÂ¼þ²»ÈÃ´¥·¢´íÎóÐÅÏ¢ÏÔÊ¾;
					Validform.util.showmsg.call(curform,flag.info,settings.tiptype,{obj:$(this),type:flag.type,sweep:settings.tipSweep},"bycheck");
					
					!settings.tipSweep && _this.addClass("Validform_error");
				}
				return false;
			}
			
			//ÑéÖ¤Í¨¹ýµÄ»°£¬Èç¹û°ó¶¨ÓÐajaxurl£¬ÒªÖ´ÐÐajax¼ì²â;
			//µ±ignore="ignore"Ê±£¬Îª¿ÕÖµ¿ÉÒÔÍ¨¹ýÑéÖ¤£¬ÕâÊ±²»ÐèÒªajax¼ì²â;
			var ajaxurl=$(this).attr("ajaxurl");
			if(ajaxurl && !Validform.util.isEmpty.call($(this),inputval) && !bool){
				var inputobj=$(this);

				//µ±Ìá½»±íµ¥Ê±£¬±íµ¥ÖÐµÄÄ³ÏîÒÑ¾­ÔÚÖ´ÐÐajax¼ì²â£¬ÕâÊ±ÐèÒªÈÃ¸ÃÏîajax½áÊøºó¼ÌÐøÌá½»±íµ¥;
				if(subpost=="postform"){
					inputobj[0].validform_subpost="postform";
				}else{
					inputobj[0].validform_subpost="";
				}
				
				if(inputobj[0].validform_valid==="posting" && inputval==inputobj[0].validform_ckvalue){return "ajax";}
				
				inputobj[0].validform_valid="posting";
				inputobj[0].validform_ckvalue=inputval;
				Validform.util.showmsg.call(curform,curform.data("tipmsg").c||tipmsg.c,settings.tiptype,{obj:inputobj,type:1,sweep:settings.tipSweep},"bycheck");
				
				Validform.util.abort.call(_this[0]);
				
				var ajaxsetup=$.extend(true,{},settings.ajaxurl || {});
								
				var localconfig={
					type: "POST",
					cache:false,
					url: ajaxurl,
					data: "param="+encodeURIComponent(inputval)+"&name="+encodeURIComponent($(this).attr("name")),
					success: function(data){
						if($.trim(data.status)==="y"){
							inputobj[0].validform_valid="true";
							data.info && inputobj.attr("sucmsg",data.info);
							Validform.util.showmsg.call(curform,inputobj.attr("sucmsg") || curform.data("tipmsg").r||tipmsg.r,settings.tiptype,{obj:inputobj,type:2,sweep:settings.tipSweep},"bycheck");
							_this.removeClass("Validform_error");
							errorobj=null;
							if(inputobj[0].validform_subpost=="postform"){
								curform.trigger("submit");
							}
						}else{
							inputobj[0].validform_valid=data.info;
							Validform.util.showmsg.call(curform,data.info,settings.tiptype,{obj:inputobj,type:3,sweep:settings.tipSweep});
							_this.addClass("Validform_error");
						}
						_this[0].validform_ajax=null;
					},
					error: function(data){
						if(data.status=="200"){
							if(data.responseText=="y"){
								ajaxsetup.success({"status":"y"});
							}else{
								ajaxsetup.success({"status":"n","info":data.responseText});	
							}
							return false;
						}
						
						//ÕýÔÚ¼ì²âÊ±£¬Òª¼ì²âµÄÊý¾Ý·¢Éú¸Ä±ä£¬ÕâÊ±ÒªÖÕÖ¹µ±Ç°µÄajax¡£²»ÊÇÕâÖÖÇé¿öÒýÆðµÄajax´íÎó£¬ÄÇÃ´ÏÔÊ¾Ïà¹Ø´íÎóÐÅÏ¢;
						if(data.statusText!=="abort"){
							var msg="status: "+data.status+"; statusText: "+data.statusText;
						
							Validform.util.showmsg.call(curform,msg,settings.tiptype,{obj:inputobj,type:3,sweep:settings.tipSweep});
							_this.addClass("Validform_error");
						}
						
						inputobj[0].validform_valid=data.statusText;
						_this[0].validform_ajax=null;
						
						//localconfig.error·µ»Øtrue±íÊ¾»¹ÐèÒªÖ´ÐÐtemp_err;
						return true;
					}
				}
				
				if(ajaxsetup.success){
					var temp_suc=ajaxsetup.success;
					ajaxsetup.success=function(data){
						localconfig.success(data);
						temp_suc(data,inputobj);
					}
				}
				
				if(ajaxsetup.error){
					var temp_err=ajaxsetup.error;
					ajaxsetup.error=function(data){
						//localconfig.error·µ»Øfalse±íÊ¾²»ÐèÒªÖ´ÐÐtemp_err;
						localconfig.error(data) && temp_err(data,inputobj);
					}	
				}

				ajaxsetup=$.extend({},localconfig,ajaxsetup,{dataType:"json"});
				_this[0].validform_ajax=$.ajax(ajaxsetup);
				
				return "ajax";
			}else if(ajaxurl && Validform.util.isEmpty.call($(this),inputval)){
				Validform.util.abort.call(_this[0]);
				_this[0].validform_valid="true";
			}
			
			if(!bool){
				Validform.util.showmsg.call(curform,flag.info,settings.tiptype,{obj:$(this),type:flag.type,sweep:settings.tipSweep},"bycheck");
				_this.removeClass("Validform_error");
			}
			errorobj=null;
			
			return true;
		
		},
		
		submitForm:function(settings,flg,url,ajaxPost,sync){
			/*
				flg===trueÊ±Ìø¹ýÑéÖ¤Ö±½ÓÌá½»;
				ajaxPost==="ajaxPost"Ö¸Ê¾µ±Ç°±íµ¥ÒÔajax·½Ê½Ìá½»;
			*/
			var curform=this;
			
			//±íµ¥ÕýÔÚÌá½»Ê±µã»÷Ìá½»°´Å¥²»×ö·´Ó¦;
			if(curform[0].validform_status==="posting"){return false;}
			
			//ÒªÇóÖ»ÄÜÌá½»Ò»´ÎÊ±;
			if(settings.postonce && curform[0].validform_status==="posted"){return false;}
			
			var beforeCheck=settings.beforeCheck && settings.beforeCheck(curform);
			if(beforeCheck===false){return false;}
			
			var flag=true,
				inflag;
				
			curform.find("[datatype]").each(function(){
				//Ìø¹ýÑéÖ¤;
				if(flg){
					return false;
				}
				
				//Òþ²Ø»ò°ó¶¨dataIgnoreµÄ±íµ¥¶ÔÏó²»×öÑéÖ¤;
				if(settings.ignoreHidden && $(this).is(":hidden") || $(this).data("dataIgnore")==="dataIgnore"){
					return true;
				}
				
				var inputval=Validform.util.getValue.call(curform,$(this)),
					_this;
				errorobj=_this=$(this);
				
				inflag=Validform.util.regcheck.call(curform,$(this).attr("datatype"),inputval,$(this));
				
				if(!inflag.passed){
					Validform.util.showmsg.call(curform,inflag.info,settings.tiptype,{obj:$(this),type:inflag.type,sweep:settings.tipSweep});
					_this.addClass("Validform_error");
					
					if(!settings.showAllError){
						_this.focus();
						flag=false;
						return false;
					}
					
					flag && (flag=false);
					return true;
				}
				
				//µ±ignore="ignore"Ê±£¬Îª¿ÕÖµ¿ÉÒÔÍ¨¹ýÑéÖ¤£¬ÕâÊ±²»ÐèÒªajax¼ì²â;
				if($(this).attr("ajaxurl") && !Validform.util.isEmpty.call($(this),inputval)){
					if(this.validform_valid!=="true"){
						var thisobj=$(this);
						Validform.util.showmsg.call(curform,curform.data("tipmsg").v||tipmsg.v,settings.tiptype,{obj:thisobj,type:3,sweep:settings.tipSweep});
						_this.addClass("Validform_error");
						
						thisobj.trigger("blur",["postform"]);//continue the form post;
						
						if(!settings.showAllError){
							flag=false;
							return false;
						}
						
						flag && (flag=false);
						return true;
					}
				}else if($(this).attr("ajaxurl") && Validform.util.isEmpty.call($(this),inputval)){
					Validform.util.abort.call(this);
					this.validform_valid="true";
				}

				Validform.util.showmsg.call(curform,inflag.info,settings.tiptype,{obj:$(this),type:inflag.type,sweep:settings.tipSweep});
				_this.removeClass("Validform_error");
				errorobj=null;
			});
			
			if(settings.showAllError){
				curform.find(".Validform_error:first").focus();
			}

			if(flag){
				var beforeSubmit=settings.beforeSubmit && settings.beforeSubmit(curform);
				if(beforeSubmit===false){return false;}
				
				curform[0].validform_status="posting";
							
				if(settings.ajaxPost || ajaxPost==="ajaxPost"){
					//»ñÈ¡ÅäÖÃ²ÎÊý;
					var ajaxsetup=$.extend(true,{},settings.ajaxpost || {});
					//ÓÐ¿ÉÄÜÐèÒª¶¯Ì¬µÄ¸Ä±äÌá½»µØÖ·£¬ËùÒÔ°ÑactionËùÖ¸¶¨µÄurl²ã¼¶ÉèÎª×îµÍ;
					ajaxsetup.url=url || ajaxsetup.url || settings.url || curform.attr("action");
					
					//byajax£ºajaxÊ±£¬tiptyeÎª1¡¢2»ò3ÐèÒªµ¯³öÌáÊ¾¿ò;
					Validform.util.showmsg.call(curform,curform.data("tipmsg").p||tipmsg.p,settings.tiptype,{obj:curform,type:1,sweep:settings.tipSweep},"byajax");

					//·½·¨ÀïµÄÓÅÏÈ¼¶Òª¸ß;
					//ÓÐundefinedÇé¿ö;
					if(sync){
						ajaxsetup.async=false;
					}else if(sync===false){
						ajaxsetup.async=true;
					}
					
					if(ajaxsetup.success){
						var temp_suc=ajaxsetup.success;
						ajaxsetup.success=function(data){
							settings.callback && settings.callback(data);
							curform[0].validform_ajax=null;
							if($.trim(data.status)==="y"){
								curform[0].validform_status="posted";
							}else{
								curform[0].validform_status="normal";
							}
							
							temp_suc(data,curform);
						}
					}
					
					if(ajaxsetup.error){
						var temp_err=ajaxsetup.error;
						ajaxsetup.error=function(data){
							settings.callback && settings.callback(data);
							curform[0].validform_status="normal";
							curform[0].validform_ajax=null;
							
							temp_err(data,curform);
						}	
					}
					
					var localconfig={
						type: "POST",
						async:true,
						data: curform.serializeArray(),
						success: function(data){
							if($.trim(data.status)==="y"){
								//³É¹¦Ìá½»;
								curform[0].validform_status="posted";
								Validform.util.showmsg.call(curform,data.info,settings.tiptype,{obj:curform,type:2,sweep:settings.tipSweep},"byajax");
							}else{
								//Ìá½»³ö´í;
								curform[0].validform_status="normal";
								Validform.util.showmsg.call(curform,data.info,settings.tiptype,{obj:curform,type:3,sweep:settings.tipSweep},"byajax");
							}
							
							settings.callback && settings.callback(data);
							curform[0].validform_ajax=null;
						},
						error: function(data){
							var msg="status: "+data.status+"; statusText: "+data.statusText;
									
							Validform.util.showmsg.call(curform,msg,settings.tiptype,{obj:curform,type:3,sweep:settings.tipSweep},"byajax");
							
							settings.callback && settings.callback(data);
							curform[0].validform_status="normal";
							curform[0].validform_ajax=null;
						}
					}
					
					ajaxsetup=$.extend({},localconfig,ajaxsetup,{dataType:"json"});
					
					curform[0].validform_ajax=$.ajax(ajaxsetup);

				}else{
					if(!settings.postonce){
						curform[0].validform_status="normal";
					}
					
					var url=url || settings.url;
					if(url){
						curform.attr("action",url);
					}
					
					return settings.callback && settings.callback(curform);
				}
			}
			
			return false;
			
		},
		
		resetForm:function(){
			var brothers=this;
			brothers.each(function(){
				this.reset && this.reset();
				this.validform_status="normal";
			});
			
			brothers.find(".Validform_right").text("");
			brothers.find(".passwordStrength").children().removeClass("bgStrength");
			brothers.find(".Validform_checktip").removeClass("Validform_wrong Validform_right Validform_loading");
			brothers.find(".Validform_error").removeClass("Validform_error");
			brothers.find("[datatype]").removeData("cked").removeData("dataIgnore").each(function(){
				this.validform_lastval=null;
			});
			brothers.eq(0).find("input:first").focus();
		},
		
		abort:function(){
			if(this.validform_ajax){
				this.validform_ajax.abort();	
			}
		}
		
	}
	
	$.Datatype=Validform.util.dataType;
	
	Validform.prototype={
		dataType:Validform.util.dataType,
		
		eq:function(n){
			var obj=this;
			
			if(n>=obj.forms.length){
				return null;	
			}
			
			if(!(n in obj.objects)){
				obj.objects[n]=new Validform($(obj.forms[n]).get(),{},true);
			}
			
			return obj.objects[n];

		},
		
		resetStatus:function(){
			var obj=this;
			$(obj.forms).each(function(){
				this.validform_status="normal";	
			});
			
			return this;
		},
		
		setStatus:function(status){
			var obj=this;
			$(obj.forms).each(function(){
				this.validform_status=status || "posting";	
			});
			
			return this;
		},
		
		getStatus:function(){
			var obj=this;
			var status=$(obj.forms)[0].validform_status;
			
			return status;
		},
		
		ignore:function(selector){
			var obj=this;
			var selector=selector || "[datatype]"
			
			$(obj.forms).find(selector).each(function(){
				$(this).data("dataIgnore","dataIgnore").removeClass("Validform_error");
			});
			
			return this;
		},
		
		unignore:function(selector){
			var obj=this;
			var selector=selector || "[datatype]"
			
			$(obj.forms).find(selector).each(function(){
				$(this).removeData("dataIgnore");
			});
			
			return this;
		},
		
		addRule:function(rule){
			/*
				rule => [{
					ele:"#id",
					datatype:"*",
					errormsg:"³ö´íÌáÊ¾ÎÄ×Ö£¡",
					nullmsg:"Îª¿ÕÊ±µÄÌáÊ¾ÎÄ×Ö£¡",
					tip:"Ä¬ÈÏÏÔÊ¾µÄÌáÊ¾ÎÄ×Ö",
					altercss:"gray",
					ignore:"ignore",
					ajaxurl:"valid.php",
					recheck:"password",
					plugin:"passwordStrength"
				},{},{},...]
			*/
			var obj=this;
			var rule=rule || [];
			
			for(var index=0; index<rule.length; index++){
				var o=$(obj.forms).find(rule[index].ele);
				for(var attr in rule[index]){
					attr !=="ele" && o.attr(attr,rule[index][attr]);
				}
			}
			
			$(obj.forms).each(function(){
				var $this=$(this);
				Validform.util.enhance.call($this,this.settings.tiptype,this.settings.usePlugin,this.settings.tipSweep,"addRule");
			});
			
			return this;
		},
		
		ajaxPost:function(flag,sync,url){
			var obj=this;
			
			$(obj.forms).each(function(){
				//´´½¨pop box;
				if( this.settings.tiptype==1 || this.settings.tiptype==2 || this.settings.tiptype==3 ){
					creatMsgbox();
				}
				
				Validform.util.submitForm.call($(obj.forms[0]),this.settings,flag,url,"ajaxPost",sync);
			});
			
			return this;
		},
		
		submitForm:function(flag,url){
			/*flag===trueÊ±²»×öÑéÖ¤Ö±½ÓÌá½»*/
			

			var obj=this;
			
			$(obj.forms).each(function(){
				var subflag=Validform.util.submitForm.call($(this),this.settings,flag,url);
				subflag === undef && (subflag=true);
				if(subflag===true){
					this.submit();
				}
			});
			
			return this;
		},
		
		resetForm:function(){
			var obj=this;
			Validform.util.resetForm.call($(obj.forms));
			
			return this;
		},
		
		abort:function(){
			var obj=this;
			$(obj.forms).each(function(){
				Validform.util.abort.call(this);
			});
			
			return this;
		},
		
		check:function(bool,selector){
			/*
				bool£º´«Èëtrue£¬Ö»¼ì²â²»ÏÔÊ¾ÌáÊ¾ÐÅÏ¢;
			*/
			
			var selector=selector || "[datatype]",
				obj=this,
				curform=$(obj.forms),
				flag=true;
			
			curform.find(selector).each(function(){
				Validform.util.check.call(this,curform,"",bool) || (flag=false);
			});
			
			return flag;
		},
		
		config:function(setup){
		/*
			config={
				url:"ajaxpost.php",//Ö¸¶¨ÁËurlºó£¬Êý¾Ý»áÌá½»µ½Õâ¸öµØÖ·;
				ajaxurl:{
					timeout:1000,
					...
				},
				ajaxpost:{
					timeout:1000,
					...
				}
			}
		*/
			var obj=this;
			setup=setup || {};
			$(obj.forms).each(function(){
				var $this=$(this);
				this.settings=$.extend(true,this.settings,setup);
				Validform.util.enhance.call($this,this.settings.tiptype,this.settings.usePlugin,this.settings.tipSweep);
			});
			
			return this;
		}
	}

	$.fn.Validform=function(settings){
		return new Validform(this,settings);
	};
	
	function setCenter(obj,time){
		var left=($(window).width()-obj.outerWidth())/2,
			top=($(window).height()-obj.outerHeight())/2,
			
		top=(document.documentElement.scrollTop?document.documentElement.scrollTop:document.body.scrollTop)+(top>0?top:0);

		obj.css({
			left:left
		}).animate({
			top : top
		},{ duration:time , queue:false });
	}
	
	function creatMsgbox(){
		if($("#Validform_msg").length!==0){return false;}
		msgobj=$('<div id="Validform_msg"><div class="Validform_title">'+tipmsg.tit+'<a class="Validform_close" href="javascript:void(0);">&chi;</a></div><div class="Validform_info"></div><div class="iframe"><iframe frameborder="0" scrolling="no" height="100%" width="100%"></iframe></div></div>').appendTo("body");//ÌáÊ¾ÐÅÏ¢¿ò;
		msgobj.find("a.Validform_close").click(function(){
			msgobj.hide();
			msghidden=true;
			if(errorobj){
				errorobj.focus().addClass("Validform_error");
			}
			return false;
		}).focus(function(){this.blur();});

		$(window).bind("scroll resize",function(){
			!msghidden && setCenter(msgobj,400);
		});
	};
	
	//¹«ÓÃ·½·¨ÏÔÊ¾&¹Ø±ÕÐÅÏ¢ÌáÊ¾¿ò;
	$.Showmsg=function(msg){
		creatMsgbox();
		Validform.util.showmsg.call(win,msg,1,{});
	};
	
	$.Hidemsg=function(){
		msgobj.hide();
		msghidden=true;
	};
	
})(jQuery,window);

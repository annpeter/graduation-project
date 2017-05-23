define({ "api": [  {    "type": "post",    "url": "/api/course/add",    "title": "添加课程",    "name": "add",    "group": "Course",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "name",            "description": "<p>课程名字</p>"          },          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "imgUrl",            "description": "<p>课程logo</p>"          },          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "intro",            "description": "<p>课程简介</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": null,\n    \"result_msg\": \"添加成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/CourseController.java",    "groupTitle": "Course"  },  {    "type": "get",    "url": "/api/course/delete",    "title": "课程删除",    "name": "delete",    "group": "Course",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "courseId",            "description": "<p>课程id</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": null,\n    \"result_msg\": \"删除成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/CourseController.java",    "groupTitle": "Course"  },  {    "type": "post",    "url": "/api/course/info",    "title": "课程信息",    "name": "info",    "group": "Course",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "courseId",            "description": "<p>课程id</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": {\n        \"id\": 1,\n        \"name\": \"软件工程\",\n        \"seq\": 0,\n        \"intro\": \"这是软件工程吃介绍啊。。。。\",\n        \"create_time\": \"2017-04-15 16:58:59\",\n        \"update_time\": \"2017-04-15 16:59:18\"\n    },\n    \"result_msg\": \"执行成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/CourseController.java",    "groupTitle": "Course"  },  {    "type": "get",    "url": "/api/course/infoByName",    "title": "根据课程名称获取信息",    "name": "infoByName",    "group": "Course",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "courseName",            "description": "<p>课程名</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": {\n        \"id\": 1,\n        \"name\": \"软件工程\",\n        \"seq\": 0,\n        \"intro\": \"这是软件工程吃介绍啊。。。。\",\n        \"create_time\": \"2017-04-15 16:58:59\",\n        \"update_time\": \"2017-04-15 16:59:18\"\n    },\n    \"result_msg\": \"执行成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/CourseController.java",    "groupTitle": "Course"  },  {    "type": "get",    "url": "/api/course/list",    "title": "课程列表",    "name": "list",    "group": "Course",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "currPage",            "description": "<p>当前页(默认值0)</p>"          },          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "pageSize",            "description": "<p>页的大小(默认值10)</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": [\n      {\n        \"id\": 1,\n        \"name\": \"软件工程\",\n        \"seq\": 0,\n        \"intro\": \"这是软件工程吃介绍啊。。。。\",\n        \"create_time\": \"2017-04-15 16:58:59\",\n        \"update_time\": \"2017-04-15 16:59:18\"\n      }\n    ],\n    \"result_msg\": \"执行成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/CourseController.java",    "groupTitle": "Course"  },  {    "type": "post",    "url": "/api/homework/add",    "title": "添加作业",    "name": "add",    "group": "HomeWork",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "courseId",            "description": "<p>课程Id</p>"          },          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "title",            "description": "<p>作业title</p>"          },          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "url",            "description": "<p>作业文档url</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": null,\n    \"result_msg\": \"添加成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/HomeWorkController.java",    "groupTitle": "HomeWork"  },  {    "type": "post",    "url": "/api/homework/check",    "title": "批改作业",    "name": "check",    "group": "HomeWork",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "homeWorkCommitId",            "description": "<p>作业id</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": null,\n    \"result_msg\": \"提交成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/HomeWorkController.java",    "groupTitle": "HomeWork"  },  {    "type": "post",    "url": "/api/homework/commit",    "title": "提交作业",    "name": "commit",    "group": "HomeWork",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "homeWorkId",            "description": "<p>作业id</p>"          },          {            "group": "Parameter",            "type": "url",            "optional": false,            "field": "url",            "description": "<p>作业url</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": {\n         \"id\": 1,\n         \"course_id\": 1,\n         \"title\": \"作业标题\",\n         \"url\": \"http://www.baidu.com\",\n         \"state\": 1\n     },\n    \"result_msg\": \"执行成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/HomeWorkController.java",    "groupTitle": "HomeWork"  },  {    "type": "get",    "url": "/api/homework/commitList",    "title": "学生完成作业列表",    "name": "commitList",    "group": "HomeWork",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "currPage",            "description": "<p>当前页(默认值0)</p>"          },          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "pageSize",            "description": "<p>页的大小(默认值10)</p>"          },          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "homeWorkId",            "description": "<p>作业id</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n  \"code\": 200,\n  \"data\": {\n    \"currPage\": 0,\n    \"sliderList\": [\n      \"1\"\n    ],\n    \"prePage\": 1,\n    \"nextPage\": 1,\n    \"dataList\": [\n      {\n        \"id\": 1,\n        \"userId\": 1,\n        \"url\": \"www.baidu.com\",\n        \"score\": null,\n        \"comment\": null,\n        \"homeWorkId\": 1,\n        \"createTime\": \"2017-05-10 12:22:27\",\n        \"updateTime\": \"2017-05-10 12:22:27\"\n      }\n    ]\n  },\n  \"resultMsg\": \"执行成功\",\n  \"errorStackTrace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/HomeWorkController.java",    "groupTitle": "HomeWork"  },  {    "type": "get",    "url": "/api/homework/list",    "title": "可用作业列表",    "name": "list",    "group": "HomeWork",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "currPage",            "description": "<p>当前页(默认值0)</p>"          },          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "pageSize",            "description": "<p>页的大小(默认值10)</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n  \"code\": 200,\n  \"data\": {\n    \"currPage\": 0,\n    \"sliderList\": [\n      \"1\"\n    ],\n    \"prePage\": 1,\n    \"nextPage\": 1,\n    \"dataList\": [\n      {\n        \"id\": 4,\n        \"courseId\": 1,\n        \"title\": \"请同学们于4月底完成Word测试练习\",\n        \"url\": \"/fileUpload/1/2017-05-02/230.docx\",\n        \"state\": 1,\n        \"createTime\": \"2017-05-02 10:40:52\",\n        \"updateTime\": \"2017-05-02 10:40:52\"\n      }\n    ]\n  },\n  \"resultMsg\": \"执行成功\",\n  \"errorStackTrace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/HomeWorkController.java",    "groupTitle": "HomeWork"  },  {    "type": "post",    "url": "/api/notice/add",    "title": "添加公告",    "name": "add",    "group": "Notice",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "courseId",            "description": "<p>课程id</p>"          },          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "type",            "description": "<p>公告类型(0: 校内公告, 1:院内公告)</p>"          },          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "content",            "description": "<p>公告内容</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": null,\n    \"result_msg\": \"执行成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/NoticeController.java",    "groupTitle": "Notice"  },  {    "type": "get",    "url": "/api/notice/delete",    "title": "公告删除",    "name": "delete",    "group": "Notice",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "noticeId",            "description": "<p>公告id</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": null,\n    \"result_msg\": \"执行成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/NoticeController.java",    "groupTitle": "Notice"  },  {    "type": "get",    "url": "/api/notice/list",    "title": "公告列表",    "name": "list",    "group": "Notice",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "courseId",            "description": "<p>课程id</p>"          },          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "type",            "description": "<p>公告类型(0: 校内公告, 1:院内公告)</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": [\n      {\n        \"id\": 1,\n        \"type\": 0,                   公告类型(0: 校内公告, 1:院内公告)\n        \"course_id\": 1,\n        \"title\": \"公告标题呀\",\n        \"content\": \"这是公告的内容呀\",\n        \"create_time\": \"2017-04-15 16:58:04\",\n        \"update_time\": \"2017-04-22 14:44:21\"\n      }\n    ],\n    \"result_msg\": \"执行成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/NoticeController.java",    "groupTitle": "Notice"  },  {    "type": "post",    "url": "/api/question/add",    "title": "添加问题",    "name": "add",    "group": "Question",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "content",            "description": "<p>问题内容</p>"          },          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "courseId",            "description": "<p>课程id</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": null,\n    \"result_msg\": \"添加成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/QuestionController.java",    "groupTitle": "Question"  },  {    "type": "post",    "url": "/api/question/answer",    "title": "回答问题",    "name": "answer",    "group": "Question",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "answer",            "description": "<p>回答内容</p>"          },          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "questionId",            "description": "<p>问题id</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": null,\n    \"result_msg\": \"回答成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/QuestionController.java",    "groupTitle": "Question"  },  {    "type": "post",    "url": "/api/question/delete",    "title": "删除问题",    "name": "delete",    "group": "Question",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "questionId",            "description": "<p>问题id</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n  \"code\": 200,\n  \"data\": null,\n  \"resultMsg\": \"执行成功\",\n  \"errorStackTrace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/QuestionController.java",    "groupTitle": "Question"  },  {    "type": "post",    "url": "/api/question/list",    "title": "问题列表",    "name": "list",    "group": "Question",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "currPage",            "description": "<p>当前页(默认值0)</p>"          },          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "pageSize",            "description": "<p>页的大小(默认值10)</p>"          },          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "courseId",            "description": "<p>课程id</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n  \"code\": 200,\n  \"data\": {\n    \"currPage\": 0,\n    \"sliderList\": [\n      \"1\"\n    ],\n    \"prePage\": 1,\n    \"nextPage\": 1,\n    \"dataList\": [\n      {\n        \"id\": 6,\n        \"content\": \"你好 我有个问题\",\n        \"answer\": null,\n        \"createTime\": \"2017-05-03 17:02:57\",\n        \"updateTime\": \"2017-05-03 17:02:57\",\n        \"courseId\": 1\n      }\n    ]\n  },\n  \"resultMsg\": \"执行成功\",\n  \"errorStackTrace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/QuestionController.java",    "groupTitle": "Question"  },  {    "type": "post",    "url": "/api/resource/add",    "title": "添加资源",    "name": "add",    "group": "Resource",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "type",            "description": "<p>资源类型</p>"          },          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "name",            "description": "<p>资源名称</p>"          },          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "url",            "description": "<p>资源URL</p>"          },          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "courseId",            "description": "<p>课程id</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": null,\n    \"result_msg\": \"执行成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/ResourceController.java",    "groupTitle": "Resource"  },  {    "type": "get",    "url": "/api/resource/list",    "title": "资源列表",    "name": "list",    "group": "Resource",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "currPage",            "description": "<p>当前页(默认值0)</p>"          },          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "pageSize",            "description": "<p>页的大小(默认值10)</p>"          },          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "courseId",            "description": "<p>课程id</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n  \"code\": 200,\n  \"data\": {\n    \"currPage\": 0,\n    \"sliderList\": [\n      \"1\"\n    ],\n    \"prePage\": 1,\n    \"nextPage\": 1,\n    \"dataList\": [\n      {\n        \"id\": 4,\n        \"type\": \"公共资源\",\n        \"name\": \"江苏科技大学博士、硕士学位论文抽检评议结果处理办法\",\n        \"url\": \"/fileUpload/1/2017-05-02/225.docx\",\n        \"courseId\": 2,\n        \"createTime\": \"2017-05-02 10:20:05\",\n        \"updateTime\": \"2017-05-02 10:20:03\"\n      },\n      {\n        \"id\": 1,\n        \"type\": \"公共资源\",\n        \"name\": \"国务院学位委员会关于开展2017年博士硕士学位授权审核工作的通知\",\n        \"url\": \"/fileUpload/1/2017-05-02/245.docx\",\n        \"courseId\": 1,\n        \"createTime\": \"2017-04-22 15:30:14\",\n        \"updateTime\": \"2017-05-02 09:23:39\"\n      }\n    ]\n  },\n  \"resultMsg\": \"执行成功\",\n  \"errorStackTrace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/ResourceController.java",    "groupTitle": "Resource"  },  {    "type": "post",    "url": "/api/statistics/get",    "title": "浏览量统计",    "name": "get",    "group": "Statistics",    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n  \"code\": 200,\n  \"data\": {\n    \"total\": {\n      \"date\": \"2017-04-30\",\n      \"view\": 2\n    },\n    \"today\": {\n      \"date\": \"2017-04-30\",\n      \"view\": 2\n    },\n    \"days\": [\n      {\n        \"date\": \"2017-04-01\",\n        \"view\": 0\n      },\n      {\n        \"date\": \"2017-03-31\",\n        \"view\": 3\n      }\n    ]\n  },\n  \"result_msg\": \"执行成功\",\n  \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/StatisticsController.java",    "groupTitle": "Statistics"  },  {    "type": "post",    "url": "/api/file/upload/multiple",    "title": "上传多个文件",    "name": "uploadMultiple",    "group": "Upload",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "MultipartFile",            "optional": false,            "field": "file",            "description": "<p>文件(MultipartFile的key可随意规定)</p>"          }        ]      }    },    "success": {      "fields": {        "Code": [          {            "group": "Code",            "type": "string",            "optional": false,            "field": "200",            "description": "<p>上传成功</p>"          }        ]      },      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": [\n        {\n          \"filename\": \"257.pdf\",\n          \"file_url\": \"http://upload.annpeter.cn/1/2017-03-12/257_1706.pdf\"\n        },\n        {\n          \"filename\": \"Alibaba-Java-minibook-v1.1.0.pdf\",\n          \"file_url\": \"http://upload.annpeter.cn/1/2017-03-12/Alibaba-Java-minibook-v1.1.0.pdf\"\n        }\n    ],\n    \"result_msg\": \"执行成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/FileController.java",    "groupTitle": "Upload"  },  {    "type": "post",    "url": "/api/file/upload/single",    "title": "上传单个文件",    "name": "uploadSingle",    "group": "Upload",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "MultipartFile",            "optional": false,            "field": "file",            "description": "<p>文件(MultipartFile的key必须为file)</p>"          }        ]      }    },    "success": {      "fields": {        "Code": [          {            "group": "Code",            "type": "string",            "optional": false,            "field": "200",            "description": "<p>上传成功</p>"          }        ]      },      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": {\n        \"file_url\": \"http://upload.annpeter.cn/1/2017-03-12/245.xlsx\"\n    },\n    \"result_msg\": \"执行成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/FileController.java",    "groupTitle": "Upload"  },  {    "type": "post",    "url": "/api/user/changePwd",    "title": "修改用户密码",    "name": "changePwd",    "group": "User",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "userId",            "description": "<p>用户id，可以为空，即当用户id为空的时候修改自己的密码</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": null,\n    \"result_msg\": \"执行成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/UserController.java",    "groupTitle": "User"  },  {    "type": "post",    "url": "/api/user/delete",    "title": "用户删除",    "name": "delete",    "group": "User",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "id",            "description": "<p>用户id</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": null,\n    \"result_msg\": \"删除成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/UserController.java",    "groupTitle": "User"  },  {    "type": "post",    "url": "/api/user/getTeacherList",    "title": "课程的老师列表列表",    "name": "getTeacherList",    "group": "User",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "courseId",            "description": "<p>课程id</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n  \"code\": 200,\n  \"data\": [\n    {\n      \"id\": 1,\n      \"name\": \"曹文浩\",\n      \"is_admin\": 1,\n      \"course_id\": 1,\n      \"create_time\": \"2017-05-02 09:26:34\",\n      \"update_time\": \"2017-05-02 09:26:57\"\n    }\n  ],\n  \"result_msg\": \"执行成功\",\n  \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/UserController.java",    "groupTitle": "User"  },  {    "type": "post",    "url": "/api/user/list",    "title": "用户列表",    "name": "list",    "group": "User",    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n  \"code\": 200,\n  \"data\": [\n    {\n      \"id\": 1,\n      \"name\": \"曹文浩\",\n      \"is_admin\": 0,\n      \"course_id\": 1,\n      \"create_time\": \"2017-05-02 09:26:34\",\n      \"update_time\": \"2017-05-02 09:26:57\"\n    },\n    {\n      \"id\": 3,\n      \"name\": \"蔡智焱\",\n      \"is_admin\": 0,\n      \"course_id\": 1,\n      \"create_time\": \"2017-05-02 09:26:34\",\n      \"update_time\": \"2017-05-02 09:26:58\"\n    }\n  ],\n  \"result_msg\": \"执行成功\",\n  \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/UserController.java",    "groupTitle": "User"  },  {    "type": "post",    "url": "/api/user/login",    "title": "用户登录",    "name": "login",    "group": "User",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "name",            "description": "<p>用户名称</p>"          },          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "pwd",            "description": "<p>用户密码</p>"          },          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "courseId",            "description": "<p>课程id</p>"          }        ]      }    },    "success": {      "fields": {        "Code": [          {            "group": "Code",            "type": "string",            "optional": false,            "field": "200",            "description": "<p>登录成功</p>"          },          {            "group": "Code",            "type": "string",            "optional": false,            "field": "403",            "description": "<p>密码错误</p>"          },          {            "group": "Code",            "type": "string",            "optional": false,            "field": "404",            "description": "<p>无此用户</p>"          }        ]      },      "examples": [        {          "title": "Response 200 Example",          "content": "{\n  \"code\": 200,\n  \"data\": {\n    \"id\": 1,\n    \"name\": \"曹文浩\",\n    \"is_admin\": 0,\n    \"course_id\": 1,\n    \"create_time\": \"2017-05-02 09:26:34\",\n    \"update_time\": \"2017-05-02 09:26:57\"\n  },\n  \"result_msg\": \"登录成功\",\n  \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/UserController.java",    "groupTitle": "User"  },  {    "type": "post",    "url": "/api/user/logout",    "title": "用户退出登录",    "name": "logout",    "group": "User",    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": null,\n    \"result_msg\": \"登出成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/UserController.java",    "groupTitle": "User"  },  {    "type": "post",    "url": "/api/user/register",    "title": "用户注册",    "name": "register",    "group": "User",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "name",            "description": "<p>用户名称</p>"          },          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "pwd",            "description": "<p>用户密码</p>"          },          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "isAdmin",            "description": "<p>是否为管理员, 0否 1是</p>"          },          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "courseId",            "description": "<p>课程id</p>"          },          {            "group": "Parameter",            "type": "int",            "optional": false,            "field": "teacherId",            "description": "<p>老师id</p>"          }        ]      }    },    "success": {      "fields": {        "Code": [          {            "group": "Code",            "type": "string",            "optional": false,            "field": "200",            "description": "<p>注册成功</p>"          },          {            "group": "Code",            "type": "string",            "optional": false,            "field": "409",            "description": "<p>此用户名已被注册</p>"          }        ]      },      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": null,\n    \"result_msg\": \"注册成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/UserController.java",    "groupTitle": "User"  },  {    "success": {      "fields": {        "Success 200": [          {            "group": "Success 200",            "optional": false,            "field": "varname1",            "description": "<p>No type.</p>"          },          {            "group": "Success 200",            "type": "String",            "optional": false,            "field": "varname2",            "description": "<p>With type.</p>"          }        ]      }    },    "type": "",    "url": "",    "version": "0.0.0",    "filename": "./doc/main.js",    "group": "_Users_annpeter_Desktop_graduation_project_doc_main_js",    "groupTitle": "_Users_annpeter_Desktop_graduation_project_doc_main_js",    "name": ""  },  {    "type": "post",    "url": "/api/poi/ppt",    "title": "ppt转换图片",    "name": "poi",    "group": "ppt",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "url",            "description": "<p>ppt路径</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": [\n        \"url\"       图片url\n    ],\n    \"result_msg\": \"获取成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/PoiConvertController.java",    "groupTitle": "ppt"  },  {    "type": "post",    "url": "/api/poi/word",    "title": "word转换为html",    "name": "poi",    "group": "word",    "parameter": {      "fields": {        "Parameter": [          {            "group": "Parameter",            "type": "string",            "optional": false,            "field": "url",            "description": "<p>word路径</p>"          }        ]      }    },    "success": {      "examples": [        {          "title": "Response 200 Example",          "content": "{\n    \"code\": 200,\n    \"data\": \"html\",\n    \"result_msg\": \"获取成功\",\n    \"error_stack_trace\": null\n}",          "type": "json"        }      ]    },    "version": "0.0.0",    "filename": "./project-web/src/main/java/cn/annpeter/graduation/project/web/controller/PoiConvertController.java",    "groupTitle": "word"  }] });

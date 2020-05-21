let app = new Vue({
    el: '#phoneApp',
    data: {
        phones: null,
        phone: {
            id: null,
            brand: null,
            model: null,
            color: null,
            storage: null,
            price: null,
            image: null,
        },
        updating: false,
        addPage:false,
    },
    //获取用户列表数据
    beforeMount: function () {
        axios.get("/phone/")
            .then(res => {
                if (res.data.status == "success") {
                    this.phones = res.data.data
                } else {
                    alert("数据错误，稍后重试");
                    location.reload();
                }
            })
            .catch(err => {
                alert("网络故障");
                location.href = "/manage/index";
                console.error(err);
            })
    },
    updated: function () {
        $("table").DataTable({
            oLanguage: {
                sProcessing: "处理中...",
                sLengthMenu: "_MENU_ 记录/页",
                sZeroRecords: "没有匹配的记录",
                sInfo: "显示第 _START_ 至 _END_ 项记录，共 _TOTAL_ 项",
                sInfoEmpty: "显示第 0 至 0 项记录，共 0 项",
                sInfoFiltered: "(由 _MAX_ 项记录过滤)",
                sInfoPostFix: "",
                sSearch: "从当前数据中检索:",
                sUrl: "",
                oPaginate: {
                    sFirst: "首页",
                    sPrevious: "上页",
                    sNext: "下页",
                    sLast: "末页"
                },
                oAria: {
                    sSortAscending: ": 以升序排列此列",
                    sSortDescending: ": 以降序排列此列"
                }
            },
            retrieve: true
        });
    },
    methods: {
        deletePhone: function (phoneId) {
            axios.delete("/phone/" + phoneId)
                .then(res => {
                    if (res.data.status == "success") {
                        location.reload();
                    } else {
                        alert("删除失败，稍后再试");
                        location.reload();
                    }
                })
                .catch(err => {
                    alert("您的网络异常，请刷新后重试")
                    location.href = "/manage/index";
                    console.error(err);
                })
        },
        updatePhone: function (phone) {
            let brands = ["华为", "荣耀", "苹果", "小米", "OPPO", "三星", "VIVO", "魅族", "酷派", "金立", "锤子", "一加"];
            this.phone = JSON.parse(JSON.stringify(phone));
            this.phone.brand = brands.indexOf(this.phone.brand) + 1;
            this.updating = true;
        },
        addPhone:function(phone){
            this.phone = JSON.parse(JSON.stringify(phone));
            this.addPage=true;
        },
        submitUpdate: function () {
            axios.put("/phone/" + this.phone.id, this.phone)
                .then(res => {
                    if (res.data.status == "filed")
                        alert("数据错误，请稍后重试")
                    location.reload();
                })
                .catch(err => {
                    console.error(err);
                    alert("您的网络异常，请刷新后重试")
                    location.href = "/manage/index";
                })
        },
        submitAdd:function(){
            // let brands = ["华为", "荣耀", "苹果", "小米", "OPPO", "三星", "VIVO", "魅族", "酷派", "金立", "锤子", "一加"];
            // this.phone.brand = brands.indexOf(this.phone.brand);
            console.log(this.phone);
            
            axios.post("/phone/",this.phone)
            .then(res => {
                alert("添加成功");
                location.reload();
                
            })
            .catch(err => {
                console.error(err); 
                alert("添加失败");
                location.reload();
            })
        }
       
    },
    watch: {
        phones: function (newValue, oldValue) {
            let brands = ["华为", "荣耀", "苹果", "小米", "OPPO", "三星", "VIVO", "魅族", "酷派", "金立", "锤子", "一加"];
            for (let i in newValue) {
                newValue[i].brand = brands[newValue[i].brand - 1];
            }
        },
    }
});

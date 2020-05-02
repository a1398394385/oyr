let app = new Vue({
    el: '#app',
    data: {
        // page => 0: 账户管理 / 1: 我的订单 / 2: 修改信息
        page: 0,
        user: {
            id: null,
            username: null,
            telephone: null,
            address: null,
            password: null,
        },
        orders: null,
        // 通用数据
        auth: false,
        session: {
            username: null
        },
    },
    beforeMount: function () {
        let session = JSON.parse(localStorage.getItem("session"))
        if (session == null) {
            location.href = "/login"
        } else {
            this.session = session
            this.user.id = this.session.id
            this.user.username = this.session.username
            // this.user.telephone = this.session.telephone
            this.user.address = this.session.address
        }
    },
    methods: {
        update: function () {
            axios.put("/user/" + this.user.id, this.user)
                .then(res => {
                    if (res.data.status == "success") {
                        alert("更新成功")
                        this.page = 0
                    } else {
                        alert("数据错误，请稍后重试")
                        location.reload();
                    }
                })
                .catch(err => {
                    alert("您的网络异常，请刷新后重试")
                    location.href = "/home";
                    console.error(err);
                })
        },
        logout: function () {
            axios.post("/logout")
                .then(res => {
                    localStorage.removeItem("session")
                    alert(res.data.data)
                    location.reload()
                })
                .catch(err => {
                    alert("您的网络异常，请刷新后重试")
                    location.href = "/home";
                    console.error(err);
                })
        },
    },
    watch: {
        page: function (newValue, oldValue) {
            if (newValue == 1)
                axios.get("/orders/user/" + this.user.id)
                    .then(res => {
                        if (res.data.status == "success") {
                            this.orders = res.data.data
                        } else {
                            alert("数据错误，请稍后重试")
                            location.reload()
                        }
                    })
                    .catch(err => {
                        alert("您的网络异常，请刷新后重试")
                        location.href = "/home";
                        console.error(err);
                    })
        },
        orders: function (newValue, oldValue) {
            let status = ["订单创立", "快递上门取货", "回收方收到手机", "订单完成", "退货中", "退货完成"]
            for (var i in newValue) {
                newValue[i].state = status[newValue[i].state]
            }
        },
        'user.telephone': {
            handler: function (newValue, oldValue) {
                if (newValue != null)
                    this.user.telephone = newValue.replace(/\s+/g, "") == "" ?
                        null : newValue.replace(/\s+/g, "");
            },
            deep: true
        },
        'user.username': {
            handler: function (newValue, oldValue) {
                if (newValue != null)
                    this.user.username = newValue.replace(/\s+/g, "") == "" ?
                        null : newValue.replace(/\s+/g, "");
            },
            deep: true
        },
        'user.address': {
            handler: function (newValue, oldValue) {
                if (newValue != null)
                    this.user.address = newValue.replace(/\s+/g, "") == "" ?
                        null : newValue.replace(/\s+/g, "");
            },
            deep: true
        },
        'user.password': {
            handler: function (newValue, oldValue) {
                if (newValue != null)
                    this.user.password = newValue.replace(/\s+/g, "") == "" ?
                        null : newValue.replace(/\s+/g, "");
            },
            deep: true
        },
    },
    computed: {
        disabled: function () {
            return !this.isCorrectName
                || !this.isCorrectTel
                || !this.isCorrectPass
                || !this.isCorrectAddress
        },
        isCorrectName: function () {
            return this.user.username == null || this.user.username != ""
        },
        isCorrectTel: function () {
            return this.user.telephone == null
                || /^1(3|4|5|6|7|8|9)\d{9}$/.test(this.user.telephone)
        },
        isCorrectPass: function () {
            return this.user.password == null || this.user.password.length >= 8
        },
        isCorrectAddress: function () {
            return this.user.address == null || this.user.address != ""
        },
        disabledStyle: function () {
            if (this.disabled)
                return 'color: red;';
        },
        usernameStyle: function () {
            if (false)
                return 'border: 1px solid red; color: red;';
        },
        telephoneStyle: function () {
            if (this.user.telephone != null && !this.isCorrectTel)
                return 'border: 1px solid red; color: red;';
        },
        passwordStyle: function () {
            if (this.user.password != null && !this.isCorrectPass)
                return 'border: 1px solid red; color: red;';
        },
        addressStyle: function () {
            if (false)
                return 'border: 1px solid red; color: red;';
        },
        tel: function () {
            return this.session.telephone.slice(0, 3)
                + "*".repeat(4)
                + this.session.telephone.slice(7);
        }
    }
});
let app = new Vue({
    el: '#app',
    data: {
        name: "",
        password: "",
    },
    computed: {
        isCorrectPass: function () {
            return this.password.length >= 8
        },
        passwordStyle: function () {
            if (this.password.length != 0 && !this.isCorrectPass)
                return 'border: 1px solid red';
        }
    },
    methods: {
        login: function () {
            axios.post("/admin/login", {
                name: this.name,
                password: this.password
            }).then(res => {
                if (res.data.status == "success") {
                    localStorage.setItem("adminName", this.name)
                    location.href = "/manage/index";
                } else {
                    console.log(res.data.status);
                    alert("用户或密码错误");
                }
            }).catch(err => {
                console.error(err);
            })
        }
    }
});